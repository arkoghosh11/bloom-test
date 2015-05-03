package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.consumer.User;
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
 * The type When user conversion then test user converter domain dTO methods.
 * <p/>
 * Created by Bloom/Rono on 04/29/2015 14:08 PM.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenUserConversionThenTestUserConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenUserConversionThenTestUserConverterDomainDTOMethods.class );

    /**
     * The User dTO.
     */
    private User userDTO, /**
     * The User dTO 2.
     */
    userDTO2;
    /**
     * The User domain.
     */
    private com.mana.innovative.domain.consumer.User userDomain, /**
     * The User domain 2.
     */
    userDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        userDomain2 = new com.mana.innovative.domain.consumer.User( );
        userDTO2 = new User( );

        // Set Values for tempValues
        userDTO = TestDummyDTOObjectGenerator.getTestUserDTOObject( );
        userDomain = TestDummyDomainObjectGenerator.getTestUserDomainObject( );


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

        userDTO2 = UserDomainDTOConverter.getConvertedDTOFromDomain( userDTO2, userDomain );

        Assert.assertNotNull( TestConstants.nullMessage, userDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDTO, userDTO2 );

        userDTO2 = UserDomainDTOConverter.getConvertedDTOFromDomain( null, userDomain );

        Assert.assertNotNull( TestConstants.nullMessage, userDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDTO, userDTO2 );

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
        List< User > userDTOList;
        List< com.mana.innovative.domain.consumer.User > userDomainList = new ArrayList<>( );
        userDomainList.add( userDomain );
        if ( userDomain.getUserId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestUserDomainZEROIDObject( userDomain );
        }
        userDTOList = UserDomainDTOConverter.getConvertedListDTOFromDomain( userDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, userDTOList );
        Assert.assertFalse( TestConstants.trueMessage, userDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDTO, userDTOList.get( TestConstants.ZERO ) );


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


        userDomain2 = UserDomainDTOConverter.getConvertedDomainFromDTO( userDomain2, userDTO );
        TestDummyDomainObjectGenerator.setTestUserDomainZEROIDObject( userDomain );

        Assert.assertNotNull( TestConstants.nullMessage, userDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDomain, userDomain2 );

        userDomain2 = UserDomainDTOConverter.getConvertedDomainFromDTO( null, userDTO );
        TestDummyDomainObjectGenerator.setTestUserDomainZEROIDObject( userDomain );

        Assert.assertNotNull( TestConstants.nullMessage, userDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDomain, userDomain2 );

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

        List< User > userDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.User > userDomainList;
        userDTOList.add( userDTO );
        TestDummyDomainObjectGenerator.setTestUserDomainZEROIDObject( userDomain );

        userDomainList = UserDomainDTOConverter.getConvertedListDomainFromDTO( userDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, userDomainList );
        Assert.assertFalse( TestConstants.trueMessage, userDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userDomain, userDomainList.get( TestConstants.ZERO ) );


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
        User user = new User( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            userDomain2 = UserDomainDTOConverter.getConvertedDomainFromDTO( null, user );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            userDomain2 = UserDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            userDTO2 = UserDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}