package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.common.Tab;
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
 * Created by Bloom/Rono on 4/30/2015 7:45 PM. This class WhenTabConversionThenTestTabConverterDomainDTOMethods is a
 * test class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenTabConversionThenTestTabConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenTabConversionThenTestTabConverterDomainDTOMethods.class );

    /**
     * The Tab dTO.
     */
    private Tab tabDTO, /**
     * The Tab dTO 2.
     */
    tabDTO2;
    /**
     * The Tab domain.
     */
    private com.mana.innovative.domain.common.Tab tabDomain, /**
     * The Tab domain 2.
     */
    tabDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        tabDomain2 = new com.mana.innovative.domain.common.Tab( );
        tabDTO2 = new Tab( );

        // Set Values for tempValues
        tabDTO = TestDummyDTOObjectGenerator.getTestTabDTOObject( );
        tabDomain = TestDummyDomainObjectGenerator.getTestTabDomainObject( );

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

        tabDTO2 = TabDomainDTOConverter.getConvertedDTOFromDomain( null, tabDomain );

        Assert.assertNotNull( TestConstants.nullMessage, tabDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDTO, tabDTO2 );

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

        List< Tab > tabDTOList;
        List< com.mana.innovative.domain.common.Tab > tabDomainList = new ArrayList<>( );
        tabDomainList.add( tabDomain );
        if ( tabDomain.getTabId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestTabDomainZEROIDObject( tabDomain );
        }
        tabDTOList = TabDomainDTOConverter.getConvertedListDTOFromDomain( tabDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, tabDTOList );
        Assert.assertFalse( TestConstants.trueMessage, tabDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDTO, tabDTOList.get( TestConstants.ZERO ) );

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

        tabDomain2 = TabDomainDTOConverter.getConvertedDomainFromDTO( null, tabDTO );
        TestDummyDomainObjectGenerator.setTestTabDomainZEROIDObject( tabDomain );

        Assert.assertNotNull( TestConstants.nullMessage, tabDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDomain, tabDomain2 );

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

        List< Tab > tabDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.common.Tab > tabDomainList;
        tabDTOList.add( tabDTO );
        TestDummyDomainObjectGenerator.setTestTabDomainZEROIDObject( tabDomain );

        tabDomainList = TabDomainDTOConverter.getConvertedListDomainFromDTO( tabDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, tabDomainList );
        Assert.assertFalse( TestConstants.trueMessage, tabDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDomain, tabDomainList.get( TestConstants.ZERO ) );

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
        Tab tab = new Tab( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            tabDomain2 = TabDomainDTOConverter.getConvertedDomainFromDTO( null, tab );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            tabDomain2 = TabDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            tabDTO2 = TabDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}