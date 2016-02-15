package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.client.Item;
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
 * The type When item conversion then test item converter domain dTO methods.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenItemConversionThenTestItemConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenItemConversionThenTestItemConverterDomainDTOMethods.class );

    /**
     * The Item dTO.
     */
    private Item itemDTO, /**
     * The Item dTO 2.
     */
    itemDTO2;
    /**
     * The Item domain.
     */
    private com.mana.innovative.domain.client.Item itemDomain, /**
     * The Item domain 2.
     */
    itemDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );

        itemDomain2 = new com.mana.innovative.domain.client.Item( );
        itemDTO2 = new Item( );

        // Set Values for tempValues
        itemDTO = TestDummyDTOObjectGenerator.getTestItemDTOObject( );
        itemDomain = TestDummyDomainObjectGenerator.getTestItemDomainObject( );
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

        itemDTO2 = ItemDomainDTOConverter.getConvertedDTOFromDomain( itemDTO2, itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDTO, itemDTO2 );

        itemDTO2 = ItemDomainDTOConverter.getConvertedDTOFromDomain( null, itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDTO, itemDTO2 );

        itemDomain.setItemSubType( null );
        itemDTO2 = ItemDomainDTOConverter.getConvertedDTOFromDomain( null, itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, "", itemDTO2.getItemSubType( ) );

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

        List< Item > itemDTOList;
        List< com.mana.innovative.domain.client.Item > itemDomainList = new ArrayList<>( );
        itemDomainList.add( itemDomain );
        if ( itemDomain.getItemId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestItemDomainZEROIDObject( itemDomain );
        }
        itemDTOList = ItemDomainDTOConverter.getConvertedListDTOFromDomain( itemDomainList );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDTOList );
        Assert.assertFalse( TestConstants.trueMessage, itemDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDTO, itemDTOList.get( TestConstants.ZERO ) );

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

        itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain2, itemDTO );
        TestDummyDomainObjectGenerator.setTestItemDomainZEROIDObject( itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDomain, itemDomain2 );

        itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( null, itemDTO );
        TestDummyDomainObjectGenerator.setTestItemDomainZEROIDObject( itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDomain, itemDomain2 );

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

        List< Item > itemDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.client.Item > itemDomainList;
        itemDTOList.add( itemDTO );
        TestDummyDomainObjectGenerator.setTestItemDomainZEROIDObject( itemDomain );

        itemDomainList = ItemDomainDTOConverter.getConvertedListDomainFromDTO( itemDTOList );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDomainList );
        Assert.assertFalse( TestConstants.trueMessage, itemDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDomain, itemDomainList.get( TestConstants.ZERO ) );

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
        Item item = new Item( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain2, item );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.notNullMessage, illegalArgumentValueException );
        try {
            itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            itemDTO2 = ItemDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}