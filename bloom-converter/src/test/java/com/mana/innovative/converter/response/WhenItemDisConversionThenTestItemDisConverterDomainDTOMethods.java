package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.client.ItemDiscount;
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
 * The class WhenItemDiscountDisConversionThenTestItemDiscountDisConverterDomainDTOMethods is for todo.
 * Created by BLOOM on 9/20/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenItemDisConversionThenTestItemDisConverterDomainDTOMethods {

    private Logger logger = LoggerFactory.getLogger( WhenItemDisConversionThenTestItemDisConverterDomainDTOMethods.class );

    /**
     * The ItemDiscount dTO.
     */
    private ItemDiscount itemDiscountDTO, /**
     * The ItemDiscount dTO 2.
     */
    itemDiscountDTO2;
    /**
     * The ItemDiscount domain.
     */
    private com.mana.innovative.domain.client.ItemDiscount itemDiscountDomain, /**
     * The ItemDiscount domain 2.
     */
    itemDiscountDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );

        itemDiscountDomain2 = new com.mana.innovative.domain.client.ItemDiscount( );
        itemDiscountDTO2 = new ItemDiscount( );

        // Set Values for tempValues
        itemDiscountDTO = TestDummyDTOObjectGenerator.getTestItemDiscountDTOObject( );
        itemDiscountDomain = TestDummyDomainObjectGenerator.getTestItemDiscountDomainObject( );
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

        itemDiscountDTO2 = ItemDiscountDomainDTOConverter.getConvertedDTOFromDomain( itemDiscountDTO2, itemDiscountDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountDTO, itemDiscountDTO2 );

        itemDiscountDTO2 = ItemDiscountDomainDTOConverter.getConvertedDTOFromDomain( null, itemDiscountDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountDTO, itemDiscountDTO2 );

        itemDiscountDTO2 = ItemDiscountDomainDTOConverter.getConvertedDTOFromDomain( null, itemDiscountDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDTO2 );

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

        List< ItemDiscount > itemDiscountDTOList;
        List< com.mana.innovative.domain.client.ItemDiscount > itemDiscountDomainList = new ArrayList<>( );
        itemDiscountDomainList.add( itemDiscountDomain );
        if ( itemDiscountDomain.getItemDiscountId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestItemDiscountDomainZEROIDObject( itemDiscountDomain );
        }
        itemDiscountDTOList = ItemDiscountDomainDTOConverter.getConvertedListDTOFromDomain( itemDiscountDomainList );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDTOList );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountDTO, itemDiscountDTOList.get( TestConstants.ZERO ) );

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

        itemDiscountDomain2 = ItemDiscountDomainDTOConverter.getConvertedDomainFromDTO( itemDiscountDomain2, itemDiscountDTO );
        TestDummyDomainObjectGenerator.setTestItemDiscountDomainZEROIDObject( itemDiscountDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountDomain, itemDiscountDomain2 );

        itemDiscountDomain2 = ItemDiscountDomainDTOConverter.getConvertedDomainFromDTO( null, itemDiscountDTO );
        TestDummyDomainObjectGenerator.setTestItemDiscountDomainZEROIDObject( itemDiscountDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountDomain, itemDiscountDomain2 );

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

        List< ItemDiscount > itemDiscountDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.client.ItemDiscount > itemDiscountDomainList;
        itemDiscountDTOList.add( itemDiscountDTO );
        TestDummyDomainObjectGenerator.setTestItemDiscountDomainZEROIDObject( itemDiscountDomain );

        itemDiscountDomainList = ItemDiscountDomainDTOConverter.getConvertedListDomainFromDTO( itemDiscountDTOList );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDomainList );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDiscountDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountDomain, itemDiscountDomainList.get( TestConstants.ZERO ) );

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
        ItemDiscount itemDis = new ItemDiscount( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            itemDiscountDomain2 = ItemDiscountDomainDTOConverter.getConvertedDomainFromDTO( itemDiscountDomain2, itemDis );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.notNullMessage, illegalArgumentValueException );
        try {
            itemDiscountDomain2 = ItemDiscountDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            itemDiscountDTO2 = ItemDiscountDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}