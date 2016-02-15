package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.consumer.Privilege;
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
 * Created by Bloom/Rono on 5/14/2015 11:03 PM. This class is WhenPrivilegeThenTestPrivilegeConverterDomainDTOMethods
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenPrivilegeThenTestPrivilegeConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenPrivilegeThenTestPrivilegeConverterDomainDTOMethods.class );

    /**
     * The Privilege dTO.
     */
    private Privilege privilegeDTO, /**
     * The Privilege dTO 2.
     */
    privilegeDTO2;
    /**
     * The Privilege domain.
     */
    private com.mana.innovative.domain.consumer.Privilege privilegeDomain, /**
     * The Privilege domain 2.
     */
    privilegeDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        privilegeDomain2 = new com.mana.innovative.domain.consumer.Privilege( );
        privilegeDTO2 = new Privilege( );

        // Set Values for tempValues
        privilegeDTO = TestDummyDTOObjectGenerator.getTestPrivilegeDTOObject( );
        privilegeDomain = TestDummyDomainObjectGenerator.getTestPrivilegeDomainObject( );

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

        privilegeDTO2 = PrivilegeDomainDTOConverter.getConvertedDTOFromDomain( null, privilegeDomain );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDTO, privilegeDTO2 );

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

        List< Privilege > privilegeDTOList;
        List< com.mana.innovative.domain.consumer.Privilege > privilegeDomainList = new ArrayList<>( );
        privilegeDomainList.add( privilegeDomain );

        privilegeDTOList = PrivilegeDomainDTOConverter.getConvertedListDTOFromDomain( privilegeDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDTOList );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDTO, privilegeDTOList.get( TestConstants.ZERO ) );

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

        privilegeDomain2 = PrivilegeDomainDTOConverter.getConvertedDomainFromDTO( null, privilegeDTO );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDomain, privilegeDomain2 );

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

        List< Privilege > privilegeDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.Privilege > privilegeDomainList;
        privilegeDTOList.add( privilegeDTO );

        privilegeDomainList = PrivilegeDomainDTOConverter.getConvertedListDomainFromDTO( privilegeDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDomainList );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDomain, privilegeDomainList.get( TestConstants.ZERO ) );

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
        Privilege privilege = new Privilege( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            privilegeDomain2 = PrivilegeDomainDTOConverter.getConvertedDomainFromDTO( null, privilege );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            privilegeDomain2 = PrivilegeDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
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
            privilegeDTO2 = PrivilegeDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }

}
