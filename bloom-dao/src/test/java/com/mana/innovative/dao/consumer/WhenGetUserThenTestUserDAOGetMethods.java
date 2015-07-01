package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/4/2015 2:55 PM. This class WhenGetUserThenTestUserDAOGetMethods is a test class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetUserThenTestUserDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetUserThenTestUserDAOGetMethods.class );

    /**
     * The User dAO.
     */
    @Resource
    private UserDAO userDAO;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        requestParams = new RequestParams( );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );

    }

    /**
     * Test get users with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUsersWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetUsersWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< User > userDAOResponse = userDAO.getUsers( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.getResults( ).isEmpty( ) );
        // user list and its size with DAOResponse<T> class count
        List< User > users = userDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, users );
        Assert.assertFalse( TestConstants.trueMessage, users.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDAOResponse.getCount( ), users.size( ) );

        for ( User user : users ) {
            Assert.assertNotNull( TestConstants.nullMessage, user );
        }

        logger.debug( "Finishing test for GetUsersWithErrorDisabled" );
    }

    /**
     * Test get user with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetUserWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< User > userDAOResponse = userDAO.getUserByUserId( TestConstants.ZERO, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.getResults( ).isEmpty( ) );
        // user list and its size with DAOResponse<T> class count
        List< User > users = userDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, users );
        Assert.assertFalse( TestConstants.trueMessage, users.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDAOResponse.getCount( ), users.size( ) );
        Assert.assertEquals( TestConstants.ONE, users.size( ) );
        // test user
        User user = users.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, user );
        Assert.assertNotNull( TestConstants.nullMessage, user.getUserId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getUserName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getEmail( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getPassword( ) );

        Assert.assertNotNull( TestConstants.nullMessage, user.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetUserWithErrorDisabled" );
    }

    /**
     * Test get users with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUsersWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetUsersWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< User > userDAOResponse = userDAO.getUsers( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.getResults( ).isEmpty( ) );

        // user list and its size with DAOResponse<T> class count
        List< User > users = userDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, users );
        Assert.assertFalse( TestConstants.trueMessage, users.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDAOResponse.getCount( ), users.size( ) );

        for ( User user : users ) {
            Assert.assertNotNull( TestConstants.nullMessage, user );
        }

        logger.debug( "Finishing test for GetUsersWithErrorEnabled" );
    }

    /**
     * Test get user with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetUserWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< User > userDAOResponse = userDAO.getUserByUserId( TestConstants.ZERO, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.getResults( ).isEmpty( ) );

        // user list and its size with DAOResponse<T> class count
        List< User > users = userDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, users );
        Assert.assertFalse( TestConstants.trueMessage, users.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDAOResponse.getCount( ), users.size( ) );
        Assert.assertEquals( TestConstants.ONE, users.size( ) );
        // test user
        User user = users.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, user );
        Assert.assertNotNull( TestConstants.nullMessage, user.getUserId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getUserName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getEmail( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getPassword( ) );

        Assert.assertNotNull( TestConstants.nullMessage, user.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, user.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetUserWithErrorEnabled" );
    }
}