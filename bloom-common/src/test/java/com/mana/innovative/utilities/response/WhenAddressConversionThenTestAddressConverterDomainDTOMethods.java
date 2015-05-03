package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.common.Address;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * The type When address conversion then test address converter domain dTO methods.
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenAddressConversionThenTestAddressConverterDomainDTOMethods {

    private static final Logger logger = Logger.getLogger( WhenAddressConversionThenTestAddressConverterDomainDTOMethods.class );

    private Address addressDTO, addressDTO2;
    private com.mana.innovative.domain.common.Address addressDomain, addressDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        addressDomain2 = new com.mana.innovative.domain.common.Address( );
        addressDTO2 = new Address( );

        // Set Values for tempValues
        addressDTO = TestDummyDTOObjectGenerator.getTestAddressDTOObject( );
        addressDomain = TestDummyDomainObjectGenerator.getTestAddressDomainObject( );

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
     * Test get converted dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  GetConvertedDTOFromDomain" );
        if ( addressDomain.getAddressId( ) != 1 ) {
            addressDomain = TestDummyDomainObjectGenerator.getTestAddressDomainObject( );
        }
        addressDTO2 = AddressDomainDTOConverter.getConvertedDTOFromDomain( addressDomain );

        Assert.assertNotNull( TestConstants.nullMessage, addressDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, addressDTO, addressDTO2 );

        logger.debug( "Finishing GetConvertedDTOFromDomain" );
    }

    /**
     * Test get converted list dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDTOFromDomain" );

        List< Address > addressDTOList;
        List< com.mana.innovative.domain.common.Address > addressDomainList = new ArrayList<>( );
        addressDomainList.add( addressDomain );
        if ( addressDomain.getAddressId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestAddressDomainZEROIDObject( addressDomain );
        }
        addressDTOList = AddressDomainDTOConverter.getConvertedListDTOFromDomain( addressDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, addressDTOList );
        Assert.assertFalse( TestConstants.trueMessage, addressDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, addressDTO, addressDTOList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDTOFromDomain" );
    }

    /**
     * Test get converted domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  GetConvertedDomainFromDTO" );

        addressDomain2 = AddressDomainDTOConverter.getConvertedDomainFromDTO( addressDTO );
        TestDummyDomainObjectGenerator.setTestAddressDomainZEROIDObject( addressDomain );

        Assert.assertNotNull( TestConstants.nullMessage, addressDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, addressDomain, addressDomain2 );

        logger.debug( "Finishing GetConvertedDomainFromDTO" );
    }

    /**
     * Test get converted list domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDomainFromDTO" );

        List< Address > addressDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.common.Address > addressDomainList;
        addressDTOList.add( addressDTO );
        TestDummyDomainObjectGenerator.setTestAddressDomainZEROIDObject( addressDomain );

        addressDomainList = AddressDomainDTOConverter.getConvertedListDomainFromDTO( addressDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, addressDomainList );
        Assert.assertFalse( TestConstants.trueMessage, addressDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, addressDomain, addressDomainList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDomainFromDTO" );
    }

    /**
     * Test get converted domain from dTO for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOForError" );
        Address address = new Address( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            addressDomain2 = AddressDomainDTOConverter.getConvertedDomainFromDTO( address );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            addressDomain2 = AddressDomainDTOConverter.getConvertedDomainFromDTO( null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
    }

    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        NullPointerException nullPointerException = null;
        try {
            addressDTO2 = AddressDomainDTOConverter.getConvertedDTOFromDomain( null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}