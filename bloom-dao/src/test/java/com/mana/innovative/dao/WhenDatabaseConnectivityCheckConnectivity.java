package com.mana.innovative.dao;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alex1 on 1/23/2015. This is a domain class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = JUnit4.class )
public class WhenDatabaseConnectivityCheckConnectivity {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDatabaseConnectivityCheckConnectivity.class );

    /**
     * The constant JDBC_URL.
     */
    private static final String JDBC_URL = "jdbc_url";
    /**
     * The constant JDBC_DRIVER.
     */
    private static final String JDBC_DRIVER = "jdbc_driver";
    /**
     * The constant USERNAME.
     */
    private static final String USERNAME = "username";
    /**
     * The constant PASSWORD.
     */
    private static final String PASSWORD = "password";

    /**
     * The Connection.
     */
    private Connection connection;
    /**
     * The Properties.
     */
    private Properties properties;

    /**
     * Sets up.
     */
    @Before
    public void setUp( ) {

        properties = new Properties( );

    }

    /**
     * Sets properties.
     */
    private void setProperties( ) {

        Exception exception = null;
        if ( properties.isEmpty( ) ) {
            exception = loadDatabaseProperties( );
        }
        if ( exception != null ) {
            Assert.fail( "No Database property file found or no properties defined in existing file" );
        }
    }

    /**
     * Gets connection.
     *
     * @param user the user
     * @param pass the pass
     *
     * @throws ClassNotFoundException the class not found exception
     * @throws ClassNotFoundException the sQL exception
     */
    private void getConnection( String user, String pass ) throws ClassNotFoundException, SQLException {

        Class.forName( properties.getProperty( JDBC_DRIVER ) );
        user = user != null ? user : properties.getProperty( USERNAME );
        pass = pass != null ? pass : properties.getProperty( PASSWORD );
        connection = DriverManager
                .getConnection( properties.getProperty( JDBC_URL ), user, pass );

    }

    /**
     * Load database properties.
     *
     * @return exception
     */
    private Exception loadDatabaseProperties( ) {

        Exception exception = null;
        try {

            String fileName = System.getenv( "database" );
            fileName = fileName.startsWith( "file:/" ) ? fileName.replaceFirst( "^file:/", "" ) : fileName;
            InputStream inputStream = new FileInputStream( new File( fileName ) );
            properties.load( inputStream );
        } catch ( IOException e ) {
            e.printStackTrace( );
            exception = e;
        }
        return exception;
    }

    /**
     * Test database properties.
     */
    @Test
    public void testDatabaseProperties( ) {

        Exception exception = loadDatabaseProperties( );
        Assert.assertNull( exception );
    }


    /**
     * Test Class to check the Driver Manager for a particular database
     */
    @Test
    public void testDriverClass( ) {

        Assert.assertNotNull( properties );
        setProperties( );

        try {
            getConnection( null, null );
            Assert.assertNotNull( connection );
        } catch ( ClassNotFoundException e ) {
            Assert.fail( "Class Not Found exception occurred" );
        } catch ( SQLException e ) {

            e.printStackTrace( );
            Assert.fail( "SQLException occurred" );
        }
    }

    /**
     * To check if the database is on or not, this method will through SQL except with message connection refused if
     * connection cannot be established or database is down or some other connectivity issue is present
     */
    @Test
    public void testDatabaseIsOn( ) {

        Assert.assertNotNull( properties );
        setProperties( );

        try {
            getConnection( null, null );
            Assert.assertNotNull( connection );
            Assert.assertFalse( connection.isClosed( ) );
        } catch ( ClassNotFoundException e ) {
            Assert.fail( "Class Not Found exception occurred" );
        } catch ( SQLException e ) {

            e.printStackTrace( );
            Assert.fail( "SQLException occurred" );
        }

    }

    /**
     * This method is to check the credentials given through property files is correct for database
     */
    @Test
    public void testDatabaseWithWrongCredentials( ) {

        Exception exception1 = null;
        this.setProperties( );
        try {
            getConnection( "fake", "fail" );
            Assert.assertNull( connection );
        } catch ( ClassNotFoundException exception ) {
            exception1 = exception;
            Assert.fail( "Class Not Found exception occurred" );
        } catch ( SQLException exception ) {
            exception1 = exception;
        }
        Assert.assertNotNull( exception1 );
    }

    /**
     * This method is the check a proper exception is thrown if a database error occurs
     */
    @Test
    public void testDatabaseWithRightCredentials( ) {

        Exception exception = null;
        this.setProperties( );
        try {
            getConnection( null, null );
            Assert.assertNotNull( connection );
        } catch ( ClassNotFoundException e ) {
            Assert.fail( "Class Not Found exception occurred" );
        } catch ( SQLException e ) {
            exception = e;
        }
        Assert.assertNull( exception );
    }


    /**
     * Close void.
     */
    @After
    public void close( ) {

        if ( connection != null ) {
            try {
                connection.close( );
            } catch ( SQLException e ) {
                logger.error( "Exception occurred while testing connection", e );
            } finally {
                logger.debug( "Closing Test Class" );
            }
        }

    }
}
