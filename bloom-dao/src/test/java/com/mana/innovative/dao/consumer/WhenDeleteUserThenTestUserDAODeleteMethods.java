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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by Bloom/Rono on 5/15/2015 11:28 PM. This class WhenDeleteUserThenTestUserDAODeleteMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteUserThenTestUserDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteUserThenTestUserDAODeleteMethods.class );

    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The User dAO.
     */
    @Resource
    private UserDAO userDAO;

    /**
     * The Test id.
     */
    private long testId;

    /**
     * The Test ids.
     */
    private List< Long > testIds;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.ZERO;
        testIds = new ArrayList<>( );
        testIds.add( testId );
        requestParams = new RequestParams( );
        logger.debug( "Test SetUp completed" );

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
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        try {
            DAOResponse< User > userDAOResponse = userDAO.getUserByUserId( testId, requestParams );
            Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
            Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, userDAOResponse.getCount( ) );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while trying to test if row was deleted during test", exception );
            fail( "Failed to test if row was deleted during test" );
        }
    }

    /**
     * Test delete user with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteUserWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteUserWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< User > userDAOResponse = userDAO.deleteUserByUserId( testId, requestParams, null );

        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );

        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, userDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteUserWithErrorDisabled" );
    }

    /**
     * Test delete user with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteUserWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteUserWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< User > userDAOResponse = userDAO.deleteUserByUserId( testId, requestParams, null );

        Assert.assertNotNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, userDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, userDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteUserWithErrorEnabled" );
    }

    /**
     * Test delete users by user ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteUsersByUserIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteUsersByUserIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< User > userDAOResponse = userDAO.deleteUsersByUserIds( testIds, requestParams, null );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), userDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteUsersByUserIdsErrorEnabled" );
    }

    /**
     * Test delete users by user ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteUsersByUserIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteUsersByUserIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< User > userDAOResponse = userDAO.deleteUsersByUserIds( testIds, requestParams, null );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), userDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteUsersByUserIdsErrorDisabled" );
    }


    /**
     * Test delete all user with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllUserWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllUserWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< User > userDAOResponse = userDAO.deleteAllUsers( requestParams, null );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, userDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllUserWithErrorEnabled" );
    }

    /**
     * Test delete all user with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllUserWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllUserWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< User > userDAOResponse = userDAO.deleteAllUsers( requestParams, null );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, userDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllUserWithErrorDisabled" );
    }

    /**
     * Test delete all user with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllUserWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllUserWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< User > userDAOResponse = userDAO
                .deleteAllUsers( requestParams, null );

        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( userDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.getCount( ) > TestConstants.ZERO );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllUserWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all user with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllUserWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllUserWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< User > userDAOResponse = userDAO
                .deleteAllUsers( requestParams, null );
        Assert.assertNotNull( TestConstants.nullMessage, userDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, userDAOResponse.getCount( ) > TestConstants.ZERO );
        Assert.assertNull( TestConstants.notNullMessage, userDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllUserWithDeleteAllTrueWithErrorDisabled" );
    }
}