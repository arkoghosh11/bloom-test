package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.common.Phone;
import com.mana.innovative.dto.consumer.CreditCard;
import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.Preference;
import com.mana.innovative.exception.IllegalArgumentValueException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type When customer conversion then test customer converter.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenCustomerConversionThenTestCustomerConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCustomerConversionThenTestCustomerConverterDomainDTOMethods.class );

    /**
     * The Customer dTO.
     */
    private Customer customerDTO, /**
     * The Customer dTO 2.
     */
    customerDTO2;
    /**
     * The Customer domain.
     */
    private com.mana.innovative.domain.consumer.Customer customerDomain, /**
     * The Customer domain 2.
     */
    customerDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        customerDomain2 = new com.mana.innovative.domain.consumer.Customer( );
        customerDTO2 = new Customer( );

        // Set Values for tempValues
        customerDTO = TestDummyDTOObjectGenerator.getTestCustomerDTOObject( );
        customerDomain = TestDummyDomainObjectGenerator
                .getTestCustomerDomainObject( );
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
     * Test get converted dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomain( ) throws Exception {

        logger.debug( "Starting test for GetConvertedDTOFromDomain" );

        customerDTO2 = CustomerDomainDTOConverter.getConvertedDTOFromDomain( customerDTO2, customerDomain );

        Assert.assertNotNull( TestConstants.nullMessage, customerDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDTO, customerDTO2 );

        logger.debug( "Finishing test for GetConvertedDTOFromDomain" );
    }

    /**
     * Test get converted list dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDTOFromDomain( ) throws Exception {

        logger.debug( "Starting test for GetConvertedListDTOFromDomain" );
        List< Customer > customerDTOList;
        List< com.mana.innovative.domain.consumer.Customer > customerDomainList = new ArrayList<>( );
        customerDomainList.add( customerDomain );
        if ( customerDomain.getUserId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestUserDomainZEROIDObject( customerDomain );
        }
        customerDTOList = CustomerDomainDTOConverter.getConvertedListDTOFromDomain( customerDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, customerDTOList );
        Assert.assertFalse( TestConstants.trueMessage, customerDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDTO, customerDTOList.get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetConvertedListDTOFromDomain" );
    }

    /**
     * Test get converted domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTO( ) throws Exception {

        logger.debug( "Starting test for GetConvertedDomainFromDTO" );

        customerDomain2 = CustomerDomainDTOConverter.getConvertedDomainFromDTO( customerDomain2, customerDTO );

        Assert.assertNotNull( TestConstants.nullMessage, customerDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDomain, customerDomain2 );

        logger.debug( "Finishing test for GetConvertedDomainFromDTO" );
    }

    /**
     * Test get converted list domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDomainFromDTO( ) throws Exception {

        logger.debug( "Starting test for GetConvertedListDomainFromDTO" );

        List< Customer > customerDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.Customer > customerDomainList;
        customerDTOList.add( customerDTO );
        TestDummyDomainObjectGenerator.setTestUserDomainZEROIDObject( customerDomain );

        customerDomainList = CustomerDomainDTOConverter.getConvertedListDomainFromDTO( customerDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, customerDomainList );
        Assert.assertFalse( TestConstants.trueMessage, customerDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, customerDomain, customerDomainList.get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetConvertedListDomainFromDTO" );
    }

    /**
     * Test get converted domain from dTO for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOForError" );
        Customer customer = new Customer( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            customerDomain2 = CustomerDomainDTOConverter.getConvertedDomainFromDTO( null, customer );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            customerDomain2 = CustomerDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
    }

    /**
     * Test get converted domain from dTO with property errors.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOWithPropertyErrors( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOWithPropertyErrors" );
        Customer customer2 = customerDTO;
        String temp = customer2.getFirstName( );
        customer2.setMiddleName( null );
        customer2.setLastName( null );
        customer2.setFirstName( null );
        IllegalArgumentValueException illegalArgumentValueException = null;

        try {
            customerDomain2 = CustomerDomainDTOConverter.getConvertedDomainFromDTO( null, customer2 );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        customer2 = customerDTO;

        customer2.setMiddleName( temp );
        customer2.setLastName( temp );
        customer2.setFirstName( temp );

        customer2.getCards( ).clear( );
        customer2.getCards( ).add( new CreditCard( ) );

        customer2.getPreferences( ).clear( );
        customer2.getPreferences( ).add( new Preference( ) );

        customer2.getPhones( ).clear( );
        customer2.getPhones( ).add( new Phone( ) );

        illegalArgumentValueException = null;

        try {
            customerDomain2 = CustomerDomainDTOConverter.getConvertedDomainFromDTO( null, customer2 );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOWithPropertyErrors" );
    }

    /**
     * Test get converted dTO from domain for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        NullPointerException nullPointerException = null;
        try {
            customerDTO2 = CustomerDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }

    /**
     * Test get converted dTO from domain with property errors.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomainWithPropertyErrors( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainWithPropertyErrors" );
        RuntimeException runtimeException = null;

        customerDomain.setCards( null );
        customerDomain.setPhones( null );
        customerDomain.setPreferences( null );
        customerDomain.setShippingAddress( null );
        try {
            customerDTO2 = CustomerDomainDTOConverter.getConvertedDTOFromDomain( null, customerDomain );
        } catch ( NullPointerException | IllegalArgumentValueException exception ) {
            runtimeException = exception;
        }

        Assert.assertNotNull( TestConstants.nullMessage, customerDTO2.getCards( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDTO2.getPhones( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDTO2.getPreferences( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customerDTO2.getShippingAddress( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customerDTO2.getCards( ) );
        Assert.assertNull( TestConstants.nullMessage, runtimeException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainWithPropertyErrors" );
    }

}