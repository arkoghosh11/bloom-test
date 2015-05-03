package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.common.SidebarType;
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
 * Created by Bloom/Rono on 4/30/2015 7:46 PM. This class WhenSidebarConversionThenTestSiderbarConverterDomainDTOMethods
 * is a test class
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenSidebarConversionThenTestSiderbarConverterDomainDTOMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenSidebarConversionThenTestSiderbarConverterDomainDTOMethods.class );

    private SidebarType sidebarDTO, sidebarDTO2;
    private com.mana.innovative.domain.common.SidebarType sidebarDomain, sidebarDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        sidebarDomain2 = new com.mana.innovative.domain.common.SidebarType( );
        sidebarDTO2 = new SidebarType( );

        // Set Values for tempValues
        sidebarDTO = TestDummyDTOObjectGenerator.getTestSidebarTypeDTOObject( );
        sidebarDomain = TestDummyDomainObjectGenerator.getTestSidebarTypeDomainObject( );

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
        if ( sidebarDomain.getSidebarTypeId( ) != 1 ) {
            sidebarDomain = TestDummyDomainObjectGenerator.getTestSidebarTypeDomainObject( );
        }
        sidebarDTO2 = SidebarDomainDTOConverter.getConvertedDTOFromDomain( null, sidebarDomain );

        Assert.assertNotNull( TestConstants.nullMessage, sidebarDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, sidebarDTO, sidebarDTO2 );

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

        List< SidebarType > sidebarDTOList;
        List< com.mana.innovative.domain.common.SidebarType > sidebarDomainList = new ArrayList<>( );
        sidebarDomainList.add( sidebarDomain );
        if ( sidebarDomain.getSidebarTypeId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestSidebarTypeDomainZEROIDObject( sidebarDomain );
        }
        sidebarDTOList = SidebarDomainDTOConverter.getConvertedListDTOFromDomain( sidebarDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, sidebarDTOList );
        Assert.assertFalse( TestConstants.trueMessage, sidebarDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, sidebarDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, sidebarDTO, sidebarDTOList.get( TestConstants.ZERO ) );

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

        sidebarDomain2 = SidebarDomainDTOConverter.getConvertedDomainFromDTO( null, sidebarDTO );
        TestDummyDomainObjectGenerator.setTestSidebarTypeDomainZEROIDObject( sidebarDomain );

        Assert.assertNotNull( TestConstants.nullMessage, sidebarDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, sidebarDomain, sidebarDomain2 );

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

        List< SidebarType > sidebarDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.common.SidebarType > sidebarDomainList;
        sidebarDTOList.add( sidebarDTO );
        TestDummyDomainObjectGenerator.setTestSidebarTypeDomainZEROIDObject( sidebarDomain );

        sidebarDomainList = SidebarDomainDTOConverter.getConvertedListDomainFromDTO( sidebarDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, sidebarDomainList );
        Assert.assertFalse( TestConstants.trueMessage, sidebarDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, sidebarDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, sidebarDomain, sidebarDomainList.get( TestConstants.ZERO ) );

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
        SidebarType sidebar = new SidebarType( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            sidebarDomain2 = SidebarDomainDTOConverter.getConvertedDomainFromDTO( null, sidebar );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            sidebarDomain2 = SidebarDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
    }

    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        NullPointerException nullPointerException = null;
        try {
            sidebarDTO2 = SidebarDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}