package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Created by Bloom/Rono on 5/1/2015 1:31 AM. This class WhenResponseUtilityThenTestItsMethods is a test class
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenResponseUtilityThenTestItsMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenResponseUtilityThenTestItsMethods.class );

    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );

    }

    @Test
    public void testInternalServerErrorMsg( ) throws Exception {

        logger.debug( "Starting test for InternalServerErrorMsg" );

        Response response = ResponseUtility.internalServerErrorMsg( TestConstants.TEST_MESSAGE );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_MESSAGE, response.getEntity( ).toString( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode( ), response
                .getStatus( ) );

        logger.debug( "Finishing test for InternalServerErrorMsg" );
    }

    @Test
    public void testUnauthorizedAccess( ) throws Exception {

        logger.debug( "Starting test for UnauthorizedAccess" );

        Response response = ResponseUtility.unauthorizedAccess( TestConstants.TEST_MESSAGE );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_MESSAGE, response.getEntity( ).toString( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, Response.Status.UNAUTHORIZED.getStatusCode( ), response
                .getStatus( ) );

        logger.debug( "Finishing test for UnauthorizedAccess" );
    }

    @Test
    public void testNoResourceFound( ) throws Exception {

        logger.debug( "Starting test for NoResourceFound" );

        Response response = ResponseUtility.noResourceFound( TestConstants.TEST_MESSAGE );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_MESSAGE, response.getEntity( ).toString( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, Response.Status.NOT_FOUND.getStatusCode( ), response
                .getStatus( ) );

        logger.debug( "Finishing test for NoResourceFound" );
    }

    @Test
    public void testBadRequest( ) throws Exception {

        logger.debug( "Starting test for BadRequest" );

        Response response = ResponseUtility.badRequest( TestConstants.TEST_MESSAGE );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_MESSAGE, response.getEntity( ).toString( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, Response.Status.BAD_REQUEST.getStatusCode( ), response
                .getStatus( ) );

        logger.debug( "Finishing test for BadRequest" );
    }

    @Test
    public void testForbiddenRequest( ) throws Exception {

        logger.debug( "Starting test for ForbiddenRequest" );

        Response response = ResponseUtility.forbiddenRequest( TestConstants.TEST_MESSAGE );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_MESSAGE, response.getEntity( ).toString( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, Response.Status.FORBIDDEN.getStatusCode( ), response
                .getStatus( ) );

        logger.debug( "Finishing test for ForbiddenRequest" );
    }
}