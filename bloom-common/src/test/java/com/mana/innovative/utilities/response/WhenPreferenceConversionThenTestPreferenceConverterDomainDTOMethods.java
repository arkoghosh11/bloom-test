package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.consumer.Preference;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
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
 * Created by Bloom/Rono on 4/30/2015 7:48 PM. This class WhenPreferenceConversionThenTestPreferenceConverterDomainDTOMethods
 * is a test class
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenPreferenceConversionThenTestPreferenceConverterDomainDTOMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenPreferenceConversionThenTestPreferenceConverterDomainDTOMethods.class );

    private Preference preferenceDTO, preferenceDTO2;
    private com.mana.innovative.domain.consumer.Preference preferenceDomain, preferenceDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        preferenceDomain2 = new com.mana.innovative.domain.consumer.Preference( );
        preferenceDTO2 = new Preference( );

        // Set Values for tempValues
        preferenceDTO = TestDummyDTOObjectGenerator.getTestPreferenceDTOObject( );
        preferenceDomain = TestDummyDomainObjectGenerator.getTestPreferenceDomainObject( );

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

        preferenceDTO2 = PreferenceDomainDTOConverter.getConvertedDTOFromDomain( null, preferenceDomain );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDTO, preferenceDTO2 );

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

        List< Preference > preferenceDTOList;
        List< com.mana.innovative.domain.consumer.Preference > preferenceDomainList = new ArrayList<>( );
        preferenceDomainList.add( preferenceDomain );
        if ( preferenceDomain.getPreferenceId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestPreferenceDomainZEROIDObject( preferenceDomain );
        }
        preferenceDTOList = PreferenceDomainDTOConverter.getConvertedListDTOFromDomain( preferenceDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDTOList );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDTO, preferenceDTOList.get( TestConstants.ZERO ) );

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

        preferenceDomain2 = PreferenceDomainDTOConverter.getConvertedDomainFromDTO( null, preferenceDTO );
        TestDummyDomainObjectGenerator.setTestPreferenceDomainZEROIDObject( preferenceDomain );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDomain, preferenceDomain2 );

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

        List< Preference > preferenceDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.Preference > preferenceDomainList;
        preferenceDTOList.add( preferenceDTO );
        TestDummyDomainObjectGenerator.setTestPreferenceDomainZEROIDObject( preferenceDomain );

        preferenceDomainList = PreferenceDomainDTOConverter.getConvertedListDomainFromDTO( preferenceDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDomainList );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDomain, preferenceDomainList.get( TestConstants.ZERO ) );

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
        Preference preference = new Preference( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            preferenceDomain2 = PreferenceDomainDTOConverter.getConvertedDomainFromDTO( null, preference );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            preferenceDomain2 = PreferenceDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            preferenceDTO2 = PreferenceDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}