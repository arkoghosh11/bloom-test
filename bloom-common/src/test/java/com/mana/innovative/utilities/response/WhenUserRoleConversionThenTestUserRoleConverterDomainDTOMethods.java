package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.consumer.UserRole;
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
 * Created by Bloom/Rono on 5/14/2015 11:02 PM. This class is WhenUserRoleConversionThenTestUserRoleConverterDomainDTOMethods
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenUserRoleConversionThenTestUserRoleConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenUserRoleConversionThenTestUserRoleConverterDomainDTOMethods.class );

    /**
     * The UserRole dTO.
     */
    private UserRole userRoleDTO, /**
     * The UserRole dTO 2.
     */
    userRoleDTO2;
    /**
     * The UserRole domain.
     */
    private com.mana.innovative.domain.consumer.UserRole userRoleDomain, /**
     * The UserRole domain 2.
     */
    userRoleDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        userRoleDomain2 = new com.mana.innovative.domain.consumer.UserRole( );
        userRoleDTO2 = new UserRole( );

        // Set Values for tempValues
        userRoleDTO = TestDummyDTOObjectGenerator.getTestUserRoleDTOObject( );
        userRoleDomain = TestDummyDomainObjectGenerator.getTestUserRoleDomainObject( );

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

        userRoleDTO2 = UserRoleDomainDTOConverter.getConvertedDTOFromDomain( null, userRoleDomain );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDTO, userRoleDTO2 );

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

        List< UserRole > userRoleDTOList;
        List< com.mana.innovative.domain.consumer.UserRole > userRoleDomainList = new ArrayList<>( );
        userRoleDomainList.add( userRoleDomain );

        userRoleDTOList = UserRoleDomainDTOConverter.getConvertedListDTOFromDomain( userRoleDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDTOList );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDTO, userRoleDTOList.get( TestConstants.ZERO ) );

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

        userRoleDomain2 = UserRoleDomainDTOConverter.getConvertedDomainFromDTO( null, userRoleDTO );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDomain, userRoleDomain2 );

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

        List< UserRole > userRoleDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.UserRole > userRoleDomainList;
        userRoleDTOList.add( userRoleDTO );

        userRoleDomainList = UserRoleDomainDTOConverter.getConvertedListDomainFromDTO( userRoleDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDomainList );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDomain, userRoleDomainList.get( TestConstants.ZERO ) );

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
        UserRole userRole = new UserRole( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            userRoleDomain2 = UserRoleDomainDTOConverter.getConvertedDomainFromDTO( null, userRole );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            userRoleDomain2 = UserRoleDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            userRoleDTO2 = UserRoleDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }

    /**
     * Test get converted domain from dTO with zero id.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOWithZeroId( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDomainFromDTOWithZeroId" );

        userRoleDTO.setUserRoleId( TestConstants.ZERO );

        userRoleDomain2 = UserRoleDomainDTOConverter.getConvertedDomainFromDTO( userRoleDomain2, userRoleDTO );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDomain2 );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDomain2.isActive( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_USER_ROLE_NAME, userRoleDomain2.getUserRoleName
                ( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_USER_ROLE_ID, userRoleDomain2
                .getUserRoleId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, PrivilegeDomainDTOConverter
                .getConvertedListDomainFromDTO( userRoleDTO.getPrivileges( ) ), userRoleDomain2
                .getPrivileges( ) );

        logger.debug( "Finishing GetConvertedListDomainFromDTOWithZeroId" );
    }
}
