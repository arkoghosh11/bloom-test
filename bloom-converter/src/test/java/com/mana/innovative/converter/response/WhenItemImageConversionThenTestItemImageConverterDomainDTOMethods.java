package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.client.ItemImage;
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
 * The class WhenItemImageConversionThenTestItemImageConverterDomainDTOMethods is for todo.
 * Created by BLOOM on 9/21/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenItemImageConversionThenTestItemImageConverterDomainDTOMethods {
    private Logger logger = LoggerFactory.getLogger( WhenItemImageConversionThenTestItemImageConverterDomainDTOMethods.class );

    /**
     * The ItemImage dTO.
     */
    private ItemImage itemImageDomainDTO, /**
     * The ItemImage dTO 2.
     */
    itemImageDomainDTO2;
    /**
     * The ItemImage domain.
     */
    private com.mana.innovative.domain.client.ItemImage itemImageDomain, /**
     * The ItemImage domain 2.
     */
    itemImageDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );

        itemImageDomain2 = new com.mana.innovative.domain.client.ItemImage( );
        itemImageDomainDTO2 = new ItemImage( );

        // Set Values for tempValues
        itemImageDomainDTO = TestDummyDTOObjectGenerator.getTestItemImageDTOObject( );
        itemImageDomain = TestDummyDomainObjectGenerator.getTestItemImageDomainObject( );
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

        itemImageDomainDTO2 = ItemImageDomainDTOConverter.getConvertedDTOFromDomain( itemImageDomainDTO2, itemImageDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageDomainDTO, itemImageDomainDTO2 );

        itemImageDomainDTO2 = ItemImageDomainDTOConverter.getConvertedDTOFromDomain( null, itemImageDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageDomainDTO, itemImageDomainDTO2 );

        itemImageDomainDTO2 = ItemImageDomainDTOConverter.getConvertedDTOFromDomain( null, itemImageDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainDTO2 );

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

        List< ItemImage > itemImageDomainDTOList;
        List< com.mana.innovative.domain.client.ItemImage > itemImageDomainList = new ArrayList<>( );
        itemImageDomainList.add( itemImageDomain );
        if ( itemImageDomain.getItemImageId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestItemImageDomainZEROIDObject( itemImageDomain );
        }
        itemImageDomainDTOList = ItemImageDomainDTOConverter.getConvertedListDTOFromDomain( itemImageDomainList );
        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainDTOList );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDomainDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageDomainDTO, itemImageDomainDTOList.get( TestConstants.ZERO ) );

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

        itemImageDomain2 = ItemImageDomainDTOConverter.getConvertedDomainFromDTO( itemImageDomain2, itemImageDomainDTO );
        TestDummyDomainObjectGenerator.setTestItemImageDomainZEROIDObject( itemImageDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageDomain, itemImageDomain2 );

        itemImageDomain2 = ItemImageDomainDTOConverter.getConvertedDomainFromDTO( null, itemImageDomainDTO );
        TestDummyDomainObjectGenerator.setTestItemImageDomainZEROIDObject( itemImageDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageDomain, itemImageDomain2 );

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

        List< ItemImage > itemImageDomainDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.client.ItemImage > itemImageDomainList;
        itemImageDomainDTOList.add( itemImageDomainDTO );
        TestDummyDomainObjectGenerator.setTestItemImageDomainZEROIDObject( itemImageDomain );

        itemImageDomainList = ItemImageDomainDTOConverter.getConvertedListDomainFromDTO( itemImageDomainDTOList );

        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainList );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemImageDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageDomain, itemImageDomainList.get( TestConstants.ZERO ) );

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
        ItemImage itemDis = new ItemImage( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            itemImageDomain2 = ItemImageDomainDTOConverter.getConvertedDomainFromDTO( itemImageDomain2, itemDis );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.notNullMessage, illegalArgumentValueException );
        try {
            itemImageDomain2 = ItemImageDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            itemImageDomainDTO2 = ItemImageDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}