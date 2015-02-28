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
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 */
@RunWith(value = JUnit4.class)
public class WhenDatabaseConnectivityCheckConnectivity {

    private static final Logger logger = LoggerFactory.getLogger(WhenDatabaseConnectivityCheckConnectivity.class);

    private static final String JDBC_URL = "jdbc_url";
    private static final String JDBC_DRIVER = "jdbc_driver";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private Connection connection;
    private Properties properties;

    @Before
    public void setUp() {

        properties = new Properties();

    }

    /**
     *
     */
    private void setProperties() {

        Exception exception = null;
        if (properties.isEmpty()) {
            exception = loadDatabaseProperties();
        }
        if (exception != null) {
            Assert.fail("No Database property file found or no properties defined in existing file");
        }
    }

    /**
     *
     * @param user {@link String}
     * @param pass {@link String}
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void getConnection(String user, String pass) throws ClassNotFoundException, SQLException {

        Class.forName(properties.getProperty(JDBC_DRIVER));
        user = user != null ? user : properties.getProperty(USERNAME);
        pass = pass != null ? pass : properties.getProperty(PASSWORD);
        connection = DriverManager
                .getConnection(properties.getProperty(JDBC_URL), user, pass);

    }

    /**
     *
     * @return {@link Exception}
     */
    private Exception loadDatabaseProperties() {

        Exception exception = null;
        try {

            String fileName = System.getenv("database");
            fileName = fileName.startsWith("file:/")?fileName.replaceFirst("^file:/",""):fileName;
            InputStream inputStream = new FileInputStream(new File(fileName));
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            exception = e;
        }
        return exception;
    }

    @Test
    public void testDatabaseProperties() {

        Exception exception = loadDatabaseProperties();
        Assert.assertNull(exception);
    }



    /**
     * Test Class to check the Driver Manager for a particular database
     */
    @Test
    public void testDriverClass() {

        Assert.assertNotNull(properties);
        setProperties();

        try {
            getConnection(null, null);
            Assert.assertNotNull(connection);
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found exception occurred");
        } catch (SQLException e) {

            e.printStackTrace();
            Assert.fail("SQLException occurred");
        }
    }

    /**
     * To check if the database is on or not, this method will through SQL except with message
     * connection refused if connection cannot be established
     * or database is down or some other connectivity issue is present
     */
    @Test
    public void testDatabaseIsOn() {

        Assert.assertNotNull(properties);
        setProperties();

        try {
            getConnection(null, null);
            Assert.assertNotNull(connection);
            Assert.assertFalse(connection.isClosed());
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found exception occurred");
        } catch (SQLException e) {

            e.printStackTrace();
            Assert.fail("SQLException occurred");
        }

    }

    /**
     * This method is to check the credentials given through property files is correct for database
     */
    @Test
    public void testDatabaseWithWrongCredentials() {

        Exception exception = null;
        this.setProperties();
        try {
            getConnection("fake", "fail");
            Assert.assertNull(connection);
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found exception occurred");
        } catch (SQLException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception instanceof SQLException);
    }

    /**
     * This method is the check a proper exception is thrown if a database error occurs
     */
    @Test
    public void testDatabaseWithRightCredentials() {

        Exception exception = null;
        this.setProperties();
        try {
            getConnection(null, null);
            Assert.assertNotNull(connection);
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found exception occurred");
        } catch (SQLException e) {
            exception = e;
        }
        Assert.assertNull(exception);
    }


    @After
    public void close() {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Exception occurred while testing connection", e);
            } finally {
                logger.debug("Closing Test Class");
            }
        }

    }
}
