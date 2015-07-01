package com.mana.innovative.exception.response;

import com.mana.innovative.constants.TestConstants;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alex1 on 1/28/2015. This is a domain class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class WhenErrorOccursTestError {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenErrorOccursTestError.class );

    /**
     * The Error.
     */
    private Error error;
    /**
     * The Class cast exception.
     */
    private Exception classCastException;

    /**
     * Sets up.
     */
    @Before
    public void setUp( ) {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        error = new Error( );
        classCastException = new ClassCastException( );
        error.setErrorMessage( classCastException.getMessage( ) );
        error.setErrorType( classCastException.getClass( ).getCanonicalName( ) );
        error.setErrorData( classCastException );
        error.setErrorLocation( classCastException.getLocalizedMessage( ) );
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown( ) {

        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    /**
     * Test get error.
     */
    @Test
    public void testGetError( ) {

        logger.debug( "Starting test for GetError" );

        Assert.assertNotNull( classCastException );
        // Test LocalMsg , Msg, Error Object, ErrorType(Error Class Type)
        Assert.assertEquals( classCastException.getMessage( ), error.getErrorMessage( ) );
        Assert.assertEquals( classCastException.getLocalizedMessage( ), error.getErrorLocation( ) );
        Assert.assertEquals( classCastException, error.getErrorData( ) );
        Assert.assertEquals( classCastException.getClass( ).getCanonicalName( ), error.getErrorType( ) );

        logger.debug( "Finishing test for GetError" );

    }
}
