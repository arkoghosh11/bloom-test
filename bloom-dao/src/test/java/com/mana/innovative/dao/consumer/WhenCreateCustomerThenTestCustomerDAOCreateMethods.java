package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
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

/**
 * Created by Bloom/Rono on 5/1/2015 2:02 AM. This class WhenCreateCustomerThenTestCustomerDAOGetMethods is a test
 * class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateCustomerThenTestCustomerDAOCreateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateCustomerThenTestCustomerDAOCreateMethods.class );

    /**
     * The Dummy customer.
     */
    private Customer dummyCustomer;
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
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyCustomer = TestDummyDomainObjectGenerator.getTestCustomerDomainObject( );

        requestParams = new RequestParams( );

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
     * Test create customer with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCustomerWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateCustomerWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Customer > customerDAOResponse = customerDAO.createCustomer( dummyCustomer, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );
        
        Customer customer = customerDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, customer );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCustomer, customer );

        logger.debug( "Finishing test for CreateCustomerWithErrorDisabled" );
    }

    /**
     * Test create customer with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCustomerWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateCustomerWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Customer > customerDAOResponse = customerDAO.createCustomer( dummyCustomer, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.isRequestSuccess( ) );

        Customer customer = customerDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, customer );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCustomer, customer );

        logger.debug( "Finishing test for CreateCustomerWithErrorEnabled" );
    }
}