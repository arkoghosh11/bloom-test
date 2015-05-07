package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
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
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * This class is a test class for testing class todo...
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateAddressThenTestAddressDAOCreate {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateAddressThenTestAddressDAOCreate.class );

    /**
     * The Dummy address.
     */
    private Address dummyAddress;

    /**
     * The Address dAO impl.
     */
    @Resource
    private AddressDAO addressDAOImpl;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );


        dummyAddress = new Address( );
        dummyAddress.setAddress1( TestConstants.TEST );
        dummyAddress.setAddress2( TestConstants.TEST );
        dummyAddress.setDistrict( TestConstants.TEST_DISTRICT );
        dummyAddress.setCity( TestConstants.TEST_CITY );
        dummyAddress.setState( TestConstants.TEST_STATE );
        dummyAddress.setZipCode( TestConstants.TEST_ZIPCODE );
        logger.debug( "Initialized dummyAddress for creation" );

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
    }

    /**
     * Test create a address with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateAAddressWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateAAddressWithErrorEnabled" );

        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse = addressDAOImpl.createAddress( dummyAddress, TestConstants.IS_ERROR_TRUE );

        // Test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.getErrorContainer( ).getErrors( )
                .isEmpty( ) );

        //Test AddressDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, addressDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, addressDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ).get( TestConstants.ZERO
        ) );
        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, addressDAOResponse.getCount( ) );

        Address address = addressDAOResponse.getResults( ).get( TestConstants.ZERO );
        dummyAddress.setAddressId( address.getAddressId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyAddress, address );

        logger.debug( "Finishing test for CreateAAddressWithErrorEnabled" );
    }

    /**
     * Test create a address with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateAAddressWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateAAddressWithErrorDisabled" );

        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse = addressDAOImpl.createAddress( dummyAddress, TestConstants.IS_ERROR );

        // Test Error Container
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );

        //Test AddressDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, addressDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, addressDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ).get( TestConstants.ZERO
        ) );
        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, addressDAOResponse.getCount( ) );

        Address address = addressDAOResponse.getResults( ).get( TestConstants.ZERO );
        dummyAddress.setAddressId( address.getAddressId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyAddress, address );

        logger.debug( "Finishing test for CreateAAddressWithErrorDisabled" );
    }
}