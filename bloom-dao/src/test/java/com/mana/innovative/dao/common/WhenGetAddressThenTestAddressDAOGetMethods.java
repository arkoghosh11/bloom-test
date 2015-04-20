package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetAddressThenTestAddressDAOGetMethods {

    private static final Logger logger = Logger.getLogger( WhenGetAddressThenTestAddressDAOGetMethods.class );
    /**
     * The Address id.
     */
    long addressId;
    /**
     * The Address dAO.
     */
    @Resource
    private AddressDAO addressDAO;

    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        addressId = ( long ) TestConstants.ONE;

    }

    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test get address by address id.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetAddressByAddressIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting testGetAddressByAddressIdWithErrorEnabled" );
        DAOResponse< Address > addressDAOResponse = addressDAO.getAddressByAddressId( addressId, TestConstants.IS_ERROR_TRUE );

        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( addressDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, addressDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, addressDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ) );
        Assert.assertFalse( addressDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        List< Address > addresses = addressDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, addresses.size( ) );
        // Test Address
        Address address = addresses.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, address );
        Assert.assertEquals( TestConstants.notEqualsMessage, addressId, address.getAddressId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_DISTRICT, address.getDistrict( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_STATE, address.getState( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_CITY, address.getCity( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_ZIPCODE, address.getZipCode( ).intValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST, address.getAddress1( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST, address.getAddress2( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getShopAddress( ) );

        logger.debug( "Finishing testGetAddressByAddressIdWithErrorEnabled" );
    }

    /**
     * Test get address.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetAddressWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting testGetAddressWithErrorEnabled" );
        DAOResponse< Address > addressDAOResponse = addressDAO.getAddress( TestConstants.IS_ERROR_TRUE );

        // test DAO Response errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );

        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( addressDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, addressDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, addressDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ) );
        Assert.assertFalse( addressDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ).get( TestConstants.ZERO ) );


        logger.debug( "Finishing testGetAddressWithErrorEnabled" );
    }

    /**
     * Test get address by address id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetAddressByAddressIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting testGetAddressByAddressIdWithErrorDisabled" );

        DAOResponse< Address > addressDAOResponse = addressDAO.getAddressByAddressId( addressId, TestConstants.IS_ERROR );
        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, addressDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, addressDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ) );
        Assert.assertFalse( addressDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        // Test Address List
        List< Address > addresses = addressDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, addresses.size( ) );
        // Test Address
        Address address = addresses.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, address );
        Assert.assertEquals( TestConstants.notEqualsMessage, addressId, address.getAddressId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_DISTRICT, address.getDistrict( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_STATE, address.getState( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_CITY, address.getCity( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_ZIPCODE, address.getZipCode( ).intValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST, address.getAddress1( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST, address.getAddress2( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getShopAddress( ) );
        logger.debug( "Finishing testGetAddressByAddressIdWithErrorDisabled" );
    }

    /**
     * Test get address with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetAddressWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting testGetAddressWithErrorDisabled" );
        DAOResponse< Address > addressDAOResponse = addressDAO.getAddress( TestConstants.IS_ERROR );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, addressDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, addressDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ) );
        Assert.assertFalse( addressDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        logger.debug( "Finishing testGetAddressWithErrorDisabled" );
    }

}