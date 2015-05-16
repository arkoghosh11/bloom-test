package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Customer;
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
 * Created by Bloom/Rono on 5/15/2015 11:26 PM. This class WhenDeleteCustomerThenTestCustomerDAODeleteMethods is a test
 * class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteCustomerThenTestCustomerDAODeleteMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteCustomerThenTestCustomerDAODeleteMethods.class );

    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Customer dAO.
     */
    @Resource
    private CustomerDAO customerDAO;

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
        testId = TestConstants.TEST_ID;
        testIds = new ArrayList<>( );
//        testIds.add( ( long ) TestConstants.ZERO );
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
            DAOResponse< Customer > customerDAOResponse = customerDAO.getCustomerByUserId( testId, requestParams );
            Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
            Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customerDAOResponse.getCount( ) );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while trying to test if row was deleted during test", exception );
            fail( "Failed to test if row was deleted during test" );
        }
    }

    /**
     * Test delete customer with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteCustomerWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCustomerWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteCustomerByUserId( testId, requestParams );

        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );

        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customerDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteCustomerWithErrorDisabled" );
    }

    /**
     * Test delete customer with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteCustomerWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCustomerWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteCustomerByUserId( testId, requestParams );

        Assert.assertNotNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customerDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteCustomerWithErrorEnabled" );
    }

    /**
     * Test delete customers by customer ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCustomersByCustomerIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCustomersByCustomerIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteCustomersByUserIds( testIds, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), customerDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCustomersByCustomerIdsErrorEnabled" );
    }

    /**
     * Test delete customers by customer ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCustomersByCustomerIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCustomersByCustomerIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteCustomersByUserIds( testIds, requestParams );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), customerDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCustomersByCustomerIdsErrorDisabled" );
    }


    /**
     * Test delete all customer with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCustomerWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCustomerWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteAllCustomers( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, customerDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCustomerWithErrorEnabled" );
    }

    /**
     * Test delete all customer with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCustomerWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCustomerWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Customer > customerDAOResponse = customerDAO
                .deleteAllCustomers( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, customerDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCustomerWithErrorDisabled" );
    }

    /**
     * Test delete all customer with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCustomerWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCustomerWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteAllCustomers( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.getCount( ) > TestConstants.ZERO );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCustomerWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all customer with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCustomerWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCustomerWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Customer > customerDAOResponse = customerDAO.deleteAllCustomers( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );

        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.getCount( ) > TestConstants.ZERO );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCustomerWithDeleteAllTrueWithErrorDisabled" );
    }
}