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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/1/2015 2:03 AM. This class WhenGetCustomerThenTestCustomerDAOGetMethods is a test class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetCustomerThenTestCustomerDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetCustomerThenTestCustomerDAOGetMethods.class );

    /**
     * The Customer dAO.
     */
    @Resource
    private CustomerDAO customerDAO;
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
     * Test get customers with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCustomersWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCustomersWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Customer > customerDAOResponse = customerDAO.getCustomers( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.getResults( ).isEmpty( ) );
        // customer list and its size with DAOResponse<T> class count
        List< Customer > customers = customerDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, customers );
        Assert.assertFalse( TestConstants.trueMessage, customers.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDAOResponse.getCount( ), customers.size( ) );

        for ( Customer customer : customers ) {
            Assert.assertNotNull( TestConstants.nullMessage, customer );
        }

        logger.debug( "Finishing test for GetCustomersWithErrorDisabled" );
    }

    /**
     * Test get customer with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCustomerWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCustomerWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Customer > customerDAOResponse = customerDAO.getCustomerByUserId( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, customerDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.getResults( ).isEmpty( ) );
        // customer list and its size with DAOResponse<T> class count
        List< Customer > customers = customerDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, customers );
        Assert.assertFalse( TestConstants.trueMessage, customers.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDAOResponse.getCount( ), customers.size( ) );
        Assert.assertEquals( TestConstants.ONE, customers.size( ) );
        // test customer
        Customer customer = customers.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, customer );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getUserId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getUserName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getEmail( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getPassword( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customer.getShippingAddress( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getCards( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getPreferences( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customer.getFirstName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getMiddleName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getLastName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customer.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetCustomerWithErrorDisabled" );
    }

    /**
     * Test get customers with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCustomersWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCustomersWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Customer > customerDAOResponse = customerDAO.getCustomers( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.getResults( ).isEmpty( ) );

        // customer list and its size with DAOResponse<T> class count
        List< Customer > customers = customerDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, customers );
        Assert.assertFalse( TestConstants.trueMessage, customers.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDAOResponse.getCount( ), customers.size( ) );

        for ( Customer customer : customers ) {
            Assert.assertNotNull( TestConstants.nullMessage, customer );
        }

        logger.debug( "Finishing test for GetCustomersWithErrorEnabled" );
    }

    /**
     * Test get customer with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCustomerWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCustomerWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Customer > customerDAOResponse = customerDAO.getCustomerByUserId( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customerDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, customerDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, customerDAOResponse.getResults( ).isEmpty( ) );

        // customer list and its size with DAOResponse<T> class count
        List< Customer > customers = customerDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, customers );
        Assert.assertFalse( TestConstants.trueMessage, customers.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDAOResponse.getCount( ), customers.size( ) );
        Assert.assertEquals( TestConstants.ONE, customers.size( ) );
        // test customer
        Customer customer = customers.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, customer );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getUserId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getUserName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getEmail( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getPassword( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customer.getShippingAddress( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getCards( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getPreferences( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customer.getFirstName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getMiddleName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getLastName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customer.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customer.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetCustomerWithErrorEnabled" );
    }
}