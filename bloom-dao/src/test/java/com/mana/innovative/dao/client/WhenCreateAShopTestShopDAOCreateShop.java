package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.domain.client.ItemImage;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.domain.client.WorkingHour;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The type When create a shop test shop dAO create shop. Need to make a cyclic chain link to allow for creation of
 * shop
 * or will run into issues
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true/*, transactionManager = "transactionManager" */ )
@Transactional
public class WhenCreateAShopTestShopDAOCreateShop {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateAShopTestShopDAOCreateShop.class );
    /**
     * The Shop dAO impl.
     */
    @Resource
    private ShopDAO shopDAOImpl;

    /**
     * The Dummy shop.
     */
    private Shop dummyShop;
    /**
     * The Dummy item.
     */
    private Item dummyItem;
    /**
     * The Dummy address.
     */
    private Address dummyAddress;
    /**
     * The Dummy working hour.
     */
    private WorkingHour dummyWorkingHour;

    private RequestParams requestParams;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        dummyShop = TestDummyDomainObjectGenerator.getTestShopDomainObject( );
        dummyShop.setShopOwnId( -1111L );
        requestParams = new RequestParams( );
        requestParams.setIsError( TestConstants.IS_ERROR );

        dummyItem = dummyShop.getItems( ).get( TestConstants.ZERO );
        dummyItem.setItemDiscountList( new ArrayList< ItemDiscount >( ) );
        dummyItem.getItemDiscountList( ).add( TestDummyDomainObjectGenerator.getTestItemDiscountDomainObject( ) );
        dummyItem.setItemImageList( new ArrayList< ItemImage >( ) );
        dummyItem.getItemImageList( )
                .add( TestDummyDomainObjectGenerator.getTestItemImageDomainObject( TestConstants.ZERO ) );

        dummyWorkingHour = dummyShop.getWorkingHours( ).get( TestConstants.ZERO );
        dummyAddress = dummyShop.getAddress( );

    }

    /**
     * Test create shop with new items address n working hour.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateShopWithNewItemsAddressNWorkingHour( ) throws Exception {

        logger.debug( "Starting test for CreateShopWithNewItemsAddressNWorkingHour" );

        // set shop inside address and vice versa for making them connected/related
        dummyShop.setAddress( dummyAddress );
        dummyAddress.setShopAddress( dummyShop );

        // set shop to each working hour and set the list of WHours for making them connected/related
        List< WorkingHour > workingHours = new ArrayList<>( );
        workingHours.add( dummyWorkingHour );
        dummyShop.setWorkingHours( workingHours );
        dummyWorkingHour.setShopWorkingHour( dummyShop );

        // set shop to each items and set the list of items for making them connected/related
        List< Item > items = new ArrayList<>( );
        items.add( dummyItem );
        dummyShop.setItems( items );
        dummyItem.setShopItem( dummyShop );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        try {
            shopDAOResponse = shopDAOImpl.createShop( dummyShop, requestParams );
        } catch ( HibernateException e ) {
            logger.error( "Failed to test createShop() ", e );
            Assert.fail( "Create Shop with new dummy item, working Hour and address failed" );
        }
        // Test shopDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        // test shop
        List< Shop > shops = shopDAOResponse.getResults( );
        Assert.assertFalse( TestConstants.trueMessage, shops.isEmpty( ) );
        Shop shop = shops.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, shop );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getItems( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getAddress( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getWorkingHours( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getShopName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getShopWebLink( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getItems( ).isEmpty( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getWorkingHours( ).isEmpty( ) );

        // test address
        Address address = shop.getAddress( );
        Assert.assertNotNull( TestConstants.nullMessage, address.getAddress1( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getAddress2( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getCity( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getState( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getDistrict( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getZipCode( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getShopAddress( ) );

        // test item
        for ( Item item : shop.getItems( ) ) {
            Assert.assertNotNull( TestConstants.nullMessage, item );
            Assert.assertNotNull( item.getItemName( ) );
            Assert.assertNotNull( item.getItemPrice( ) );
            Assert.assertNotNull( item.getItemPriceCurrency( ) );
            Assert.assertNotNull( item.getItemType( ) );
            Assert.assertNotNull( item.getBoughtFrom( ) );
            Assert.assertNotNull( item.getBoughtDate( ) );
            Assert.assertNotNull( item.getQuantity( ) );
            Assert.assertNotNull( item.getQuantityType( ) );
            Assert.assertNotNull( item.getWeight( ) );
            Assert.assertNotNull( item.getWeightedUnit( ) );
        }
        // test workingHour
        for ( WorkingHour workingHour : shop.getWorkingHours( ) ) {
            Assert.assertNotNull( TestConstants.nullMessage, workingHour );
            Assert.assertNotNull( workingHour.getDay( ) );
            Assert.assertNotNull( workingHour.getStartTime( ) );
            Assert.assertNotNull( workingHour.getEndTime( ) );
            Assert.assertNotNull( workingHour.isHoliday( ) );
            Assert.assertNotNull( workingHour.isOffline( ) );
            Assert.assertNotNull( workingHour.isWeekend( ) );
        }
        logger.debug( "Finishing test for CreateShopWithNewItemsAddressNWorkingHour" );
    }

    /**
     * Test create shop without any items.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateShopWithoutAnyItems( ) throws Exception {

        logger.debug( "Starting test for CreateShopWithoutAnyItems" );

        dummyShop.setItems( null );
        dummyWorkingHour.setShopWorkingHour( dummyShop );
        dummyAddress.setShopAddress( dummyShop );


        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        try {
            shopDAOResponse = shopDAOImpl.createShop( dummyShop, requestParams );
        } catch ( HibernateException exception ) {
            logger.error( "Failed to test createShop() ", exception );
            Assert.fail( "Create Shop with new dummy working Hour and address failed" );
        }
        // Test shopDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        // test shop
        List< Shop > shops = shopDAOResponse.getResults( );
        Assert.assertFalse( TestConstants.trueMessage, shops.isEmpty( ) );
        Shop shop = shops.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, shop );
        Assert.assertNull( TestConstants.notNullMessage, shop.getItems( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getAddress( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getWorkingHours( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getShopName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getShopWebLink( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getWorkingHours( ).isEmpty( ) );

        // test address
        Address address = shop.getAddress( );
        Assert.assertNotNull( TestConstants.nullMessage, address.getAddress1( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getAddress2( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getCity( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getState( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getDistrict( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getZipCode( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getShopAddress( ) );

        // test working hours
        for ( WorkingHour workingHour : shop.getWorkingHours( ) ) {
            Assert.assertNotNull( TestConstants.nullMessage, workingHour );
            Assert.assertNotNull( workingHour.getDay( ) );
            Assert.assertNotNull( workingHour.getStartTime( ) );
            Assert.assertNotNull( workingHour.getEndTime( ) );
            Assert.assertNotNull( workingHour.isHoliday( ) );
            Assert.assertNotNull( workingHour.isOffline( ) );
            Assert.assertNotNull( workingHour.isWeekend( ) );
        }
        logger.debug( "Finishing test for CreateShopWithoutAnyItems" );
    }

    /**
     * Test create shop without any address.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateShopWithAddressNull( ) throws Exception {

        logger.debug( "Starting test for CreateShopWithAddressNull" );

        dummyShop.setAddress( null );
        dummyWorkingHour.setShopWorkingHour( dummyShop );
        dummyItem.setShopItem( dummyShop );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        try {
            shopDAOResponse = shopDAOImpl.createShop( dummyShop, requestParams );
        } catch ( Exception exception ) {
            logger.error( "Failed to test createShop() ", exception );
            Assert.assertTrue( TestConstants.falseMessage, exception instanceof HibernateException );
        }
        // Test shopDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        logger.debug( "Finishing test for CreateShopWithAddressNull" );
    }

    /**
     * Test create shop without any address.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateShopWithoutAnyAddress( ) throws Exception {

        logger.debug( "Starting test for CreateShopWithoutAnyAddress" );

        dummyShop.setAddress( new Address( ) );
        dummyWorkingHour.setShopWorkingHour( dummyShop );
        dummyItem.setShopItem( dummyShop );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        try {
            shopDAOResponse = shopDAOImpl.createShop( dummyShop, requestParams );
        } catch ( Exception exception ) {
            logger.error( "Failed to test createShop() ", exception );
            Assert.assertTrue( TestConstants.falseMessage, exception instanceof HibernateException );
        }
        // Test shopDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        logger.debug( "Finishing test for CreateShopWithoutAnyAddress" );
    }

    /**
     * Test create shop without any working hour.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateShopWithoutAnyWorkingHour( ) throws Exception {

        logger.debug( "Starting test for CreateShopWithoutAnyWorkingHour" );

        dummyShop.setWorkingHours( null );

        dummyAddress.setShopAddress( dummyShop );
        dummyItem.setShopItem( dummyShop );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        try {
            shopDAOResponse = shopDAOImpl.createShop( dummyShop, requestParams );
        } catch ( HibernateException exception ) {
            logger.error( "Failed to test createShop() ", exception );
            Assert.fail( "Create Shop with new dummy item and working Hour failed" );
        }
        // Test shopDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        // test shop
        List< Shop > shops = shopDAOResponse.getResults( );
        Assert.assertFalse( TestConstants.trueMessage, shops.isEmpty( ) );
        Shop shop = shops.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, shop );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getItems( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getAddress( ) );

        // must be null since we are creating without a workingHour
        Assert.assertNull( TestConstants.notNullMessage, shop.getWorkingHours( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getShopName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shop.getShopWebLink( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getItems( ).isEmpty( ) );

        // test address
        Address address = shop.getAddress( );
        Assert.assertNotNull( TestConstants.nullMessage, address.getAddress1( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getAddress2( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getCity( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getState( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getDistrict( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getZipCode( ) );
        Assert.assertNotNull( TestConstants.nullMessage, address.getShopAddress( ) );

        // test each item
        for ( Item item : shop.getItems( ) ) {
            Assert.assertNotNull( TestConstants.nullMessage, item );
            Assert.assertNotNull( TestConstants.nullMessage, item.getItemName( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getItemPrice( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getItemPriceCurrency( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getItemType( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getBoughtFrom( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getBoughtDate( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getQuantity( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getQuantityType( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getWeight( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getWeightedUnit( ) );
            Assert.assertNotNull( TestConstants.nullMessage, item.getShopItem( ) );
        }

        logger.debug( "Finishing test for CreateShopWithoutAnyWorkingHour" );
    }
}
