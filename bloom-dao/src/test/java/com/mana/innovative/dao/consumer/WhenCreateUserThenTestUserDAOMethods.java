package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.domain.consumer.UserRole;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Bloom/Rono on 5/4/2015 8:22 PM. This class WhenCreateUserThenTestUserDAOMethods is a test class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateUserThenTestUserDAOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateUserThenTestUserDAOMethods.class );

    /**
     * The Dummy user.
     */
    private User dummyUser;

    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The User dAO.
     */
    @Resource
    private UserDAO userDAO;

    @Resource
    private UserRoleDAO userRoleDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyUser = TestDummyDomainObjectGenerator.getTestUserDomainObject( );
        requestParams = new RequestParams( );

        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.getUserRoleByUserRoleId( TestConstants.ONE,
                requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, userRoleDAOResponse.getCount( ) );

        dummyUser.setUserRole( userRoleDAOResponse.getResults( ).get( TestConstants.ZERO ) );

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );

    }

    /**
     * Test create user with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateUserWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateUserWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< User > userDAOResponse = userDAO.createUser( dummyUser, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );

        User user = userDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, user );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyUser, user );

        logger.debug( "Finishing test for CreateUserWithErrorDisabled" );
    }

    /**
     * Test create user with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateUserWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateUserWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< User > userDAOResponse = userDAO.createUser( dummyUser, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );

        User user = userDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, user );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyUser, user );

        logger.debug( "Finishing test for CreateUserWithErrorEnabled" );
    }
}