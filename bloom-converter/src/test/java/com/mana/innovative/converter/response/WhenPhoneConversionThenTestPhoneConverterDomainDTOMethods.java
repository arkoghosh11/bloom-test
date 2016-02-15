package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.common.Phone;
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
 * Created by Bloom/Rono on 4/30/2015 7:29 PM. This class WhenPhoneConversionThenTestPhoneConverter is a test class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenPhoneConversionThenTestPhoneConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenPhoneConversionThenTestPhoneConverterDomainDTOMethods.class );

    /**
     * The Phone dTO.
     */
    private Phone phoneDTO, /**
     * The Phone dTO 2.
     */
    phoneDTO2;
    /**
     * The Phone domain.
     */
    private com.mana.innovative.domain.common.Phone phoneDomain, /**
     * The Phone domain 2.
     */
    phoneDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        phoneDomain2 = new com.mana.innovative.domain.common.Phone( );
        phoneDTO2 = new Phone( );

        // Set Values for tempValues
        phoneDTO = TestDummyDTOObjectGenerator.getTestPhoneDTOObject( );
        phoneDomain = TestDummyDomainObjectGenerator.getTestPhoneDomainObject( );

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

        phoneDTO2 = PhoneDomainDTOConverter.getConvertedDTOFromDomain( null, phoneDomain );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDTO, phoneDTO2 );

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

        List< Phone > phoneDTOList;
        List< com.mana.innovative.domain.common.Phone > phoneDomainList = new ArrayList<>( );
        phoneDomainList.add( phoneDomain );
        if ( phoneDomain.getPhoneId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestPhoneDomainZEROIDObject( phoneDomain );
        }
        phoneDTOList = PhoneDomainDTOConverter.getConvertedListDTOFromDomain( phoneDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDTOList );
        Assert.assertFalse( TestConstants.trueMessage, phoneDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDTO, phoneDTOList.get( TestConstants.ZERO ) );

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

        phoneDomain2 = PhoneDomainDTOConverter.getConvertedDomainFromDTO( null, phoneDTO );
        TestDummyDomainObjectGenerator.setTestPhoneDomainZEROIDObject( phoneDomain );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDomain, phoneDomain2 );

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

        List< Phone > phoneDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.common.Phone > phoneDomainList;
        phoneDTOList.add( phoneDTO );
        TestDummyDomainObjectGenerator.setTestPhoneDomainZEROIDObject( phoneDomain );

        phoneDomainList = PhoneDomainDTOConverter.getConvertedListDomainFromDTO( phoneDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDomainList );
        Assert.assertFalse( TestConstants.trueMessage, phoneDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDomain, phoneDomainList.get( TestConstants.ZERO ) );

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
        Phone phone = new Phone( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            phoneDomain2 = PhoneDomainDTOConverter.getConvertedDomainFromDTO( null, phone );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            phoneDomain2 = PhoneDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
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
            phoneDTO2 = PhoneDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}