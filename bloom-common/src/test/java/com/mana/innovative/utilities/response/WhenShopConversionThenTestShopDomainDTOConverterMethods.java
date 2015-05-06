package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.client.Shop;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a test class for testing class todo...
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class WhenShopConversionThenTestShopDomainDTOConverterMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( WhenShopConversionThenTestShopDomainDTOConverterMethods
            .class );

    /**
     * The Shop dTO.
     */
    private Shop shopDTO, /**
     * The Shop dTO 2.
     */
    shopDTO2;
    /**
     * The Shop domain.
     */
    private com.mana.innovative.domain.client.Shop shopDomain, /**
     * The Shop domain 2.
     */
    shopDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

        shopDomain = new com.mana.innovative.domain.client.Shop( );
        shopDTO = new Shop( );

        shopDomain2 = new com.mana.innovative.domain.client.Shop( );
        shopDTO2 = new Shop( );

        // Set Values for tempValues
        shopDTO = TestDummyDTOObjectGenerator.getTestShopDTOObject( );
        shopDomain = TestDummyDomainObjectGenerator.getTestShopDomainObject( );
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

        logger.debug( "Starting  testGetConvertedDTOFromDomain" );

        shopDTO2 = ShopDomainDTOConverter.getConvertedDTOFromDomain( shopDTO2, shopDomain );

        Assert.assertNotNull( TestConstants.nullMessage, shopDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, shopDTO, shopDTO2 );

        logger.debug( "Finishing testGetConvertedDTOFromDomain" );

    }

    /**
     * Test get converted list dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  testGetConvertedListDTOFromDomain" );

        List< Shop > shopDTOList;
        List< com.mana.innovative.domain.client.Shop > shopDomainList = new ArrayList<>( );
        shopDomainList.add( shopDomain );

        shopDTOList = ShopDomainDTOConverter.getConvertedListDTOFromDomain( shopDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, shopDTOList );
        Assert.assertFalse( TestConstants.trueMessage, shopDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, shopDTO, shopDTOList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing testGetConvertedListDTOFromDomain" );
    }

    /**
     * Test get converted domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTO" );

        shopDomain2 = ShopDomainDTOConverter.getConvertedDomainFromDTO( shopDomain2, shopDTO );
        TestDummyDomainObjectGenerator.setTestShopDomainZEROIDObject( shopDomain );

        Assert.assertNotNull( TestConstants.nullMessage, shopDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, shopDomain, shopDomain2 );

        logger.debug( "Finishing testGetConvertedDomainFromDTO" );
    }

    /**
     * Test get converted list domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  testGetConvertedListDomainFromDTO" );

        List< Shop > shopDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.client.Shop > shopDomainList;
        shopDTOList.add( shopDTO );
        TestDummyDomainObjectGenerator.setTestShopDomainZEROIDObject( shopDomain );

        shopDomainList = ShopDomainDTOConverter.getConvertedListDomainFromDTO( shopDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, shopDomainList );
        Assert.assertFalse( TestConstants.trueMessage, shopDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, shopDomain, shopDomainList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing testGetConvertedListDomainFromDTO" );
    }

    /**
     * Test get converted domain from dTO for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOForError" );
        Shop shop = new Shop( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            shopDomain2 = ShopDomainDTOConverter.getConvertedDomainFromDTO( shopDomain2, shop );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );
        try {
            shopDomain2 = ShopDomainDTOConverter.getConvertedDomainFromDTO( null, null );
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
            shopDTO2 = ShopDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }

}