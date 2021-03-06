package com.mana.innovative.dao.client;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.domain.client.ItemImage;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex1 on 1/28/2015. This is a domain class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration(/* transactionManager = "transactionManager", */defaultRollback = true )
@Transactional
public class WhenCreateAnItemTestItemDAOCreateItem {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateAnItemTestItemDAOCreateItem.class );

    /**
     * The Item dAO impl.
     */
    @Resource
    private ItemDAO itemDAOImpl;

    /**
     * The Shop dAO impl.
     */
    @Resource
    private ShopDAO shopDAOImpl;

    @Resource
    private ItemDiscountDAO itemDiscountDAOImpl;

    @Resource
    private ItemImageDAO itemImageDAOImpl;

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    private RequestParams requestParams;

    /**
     * The Dummy item.
     */
    private Item dummyItem;

    /**
     * This method is to initialize Objects and configuration files before testing test method
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );

        requestParams = new RequestParams( );
        requestParams.setIsError( TestConstants.IS_ERROR );

        dummyItem = new Item( );
        dummyItem.setItemId( TestConstants.MINUS_ONE );
        dummyItem.setItemName( TestConstants.TEST_VALUE );
        dummyItem.setItemDescription( TestConstants.TEST_DESCRIPTION );
        dummyItem.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
        dummyItem.setItemType( TestConstants.TEST_VALUE );
        dummyItem.setItemSubType( TestConstants.TEST_ITEM_TYPE );
        dummyItem.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

        dummyItem.setItemPrice( ( double ) TestConstants.THREE );
        dummyItem.setWeight( TestConstants.TEST_WEIGHT );
        dummyItem.setQuantity( TestConstants.TEST_QUANTITY );

        dummyItem.setQuantityType( QuantityType.UNIT.toString( ) );
        dummyItem.setWeightedUnit( WeightedUnit.POUND.toString( ) );

        List< ItemDiscount > itemDiscountList = new ArrayList<>( );
        List< ItemImage > itemImageList = new ArrayList<>( );

        itemDiscountList.add( TestDummyDomainObjectGenerator.getTestItemDiscountDomainObject( ) );
        itemImageList.add( TestDummyDomainObjectGenerator.getTestItemImageDomainObject( TestConstants.ZERO ) );

        dummyItem.setItemImageList( itemImageList );
//        itemImageList.get( TestConstants.ZERO ).setItemImageId( TestConstants.ONE );
        dummyItem.setItemDiscountList( itemDiscountList );
//        itemDiscountList.get( TestConstants.ZERO ).setItemDiscountId( TestConstants.ONE );

        dummyItem.setCreatedDate( new Date( ) );
        dummyItem.setBoughtDate( new Date( ) );
    }

    /**
     * Test item dAO not null.
     */
    @Test
    public void testItemDAONotNull( ) {

        Assert.assertNotNull( itemDAOImpl );
    }

    /**
     * Test item dAO create without exception.
     * Note ItemDAOCreateWithExistingShopIDIIWithoutException ID = item discount, II = item image
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDAOCreateWithExistingShopIDIIWithoutException( ) {

        logger.debug( "Starting test for ItemDAOCreateWithoutException" );
        DAOResponse< Item > itemDAOResponse;
        DAOResponse< Shop > shopDAOResponse;
        DAOResponse< ItemDiscount > itemDiscountDAOResponse;
        DAOResponse< ItemImage > itemImageDAOResponse;

        itemDAOResponse = itemDAOImpl.getItemByItemId( dummyItem.getItemId( ), requestParams );

        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getResults( ).isEmpty( ) );
        shopDAOResponse = shopDAOImpl.getShopByShopId( TestConstants.ZERO, requestParams );

        itemImageDAOResponse = itemImageDAOImpl.getItemImageByItemImageId( TestConstants.ONE, requestParams );
        itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscountByItemDiscountId( TestConstants.ONE, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.getResults( ).isEmpty( ) );

        dummyItem.setItemImageList( itemImageDAOResponse.getResults( ) );
        dummyItem.setItemDiscountList( itemDiscountDAOResponse.getResults( ) );

        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.getResults( ).isEmpty( ) );
        Shop shop = shopDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( shop );
        dummyItem.setShopItem( shop );
        itemDAOResponse = itemDAOImpl.createItem( dummyItem, requestParams );

        // Test itemDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );

        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemDAOResponse.getCount( ) );
        logger.debug( "Finishing test for ItemDAOCreateWithoutException" );

    }


    @Test
    public void testItemDAOCreateWithExistingShopOnlyNErrorContainer( ) throws Exception {

        logger.debug( "Starting test for ItemDAOCreateWithExistingShopOnlyNErrorContainer" );
        DAOResponse< Item > itemDAOResponse;
        DAOResponse< Shop > shopDAOResponse;
//        DAOResponse<ItemDiscount> itemDiscountDAOResponse;
//        DAOResponse<ItemImage> itemImageDAOResponse;

        itemDAOResponse = itemDAOImpl.getItemByItemId( dummyItem.getItemId( ), requestParams );

        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getResults( ).isEmpty( ) );
        shopDAOResponse = shopDAOImpl.getShopByShopId( TestConstants.ZERO, requestParams );

//        itemImageDAOResponse = itemImageDAOImpl.getItemImageByItemImageId( TestConstants.ONE, requestParams );
//        itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscountByItemDiscountId( TestConstants.ONE, requestParams );
//
//        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
//        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
//        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.getResults( ).isEmpty( ) );
//        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.getResults( ).isEmpty( ) );
//
//        dummyItem.setItemImageList( itemImageDAOResponse.getResults() );
//        dummyItem.setItemDiscountList( itemDiscountDAOResponse.getResults( ) );

        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.getResults( ).isEmpty( ) );
        Shop shop = shopDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( shop );
        dummyItem.setShopItem( shop );
        itemDAOResponse = itemDAOImpl.createItem( dummyItem, requestParams );

        // Test itemDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );

        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemDAOResponse.getCount( ) );
        logger.debug( "Finishing test for ItemDAOCreateWithExistingShopOnlyNErrorContainer" );
    }

    /**
     * Test item dAO create throws exception.
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDAOCreateThrowsException( ) {

        logger.debug( "Starting test for ItemDAOCreateThrowsException" );

        DAOResponse< Item > itemDAOResponse;
        dummyItem = new Item( );

        itemDAOResponse = itemDAOImpl.createItem( dummyItem, requestParams );

        // Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getResults( ).isEmpty( ) );
        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemDAOResponse.getCount( ) );

        logger.debug( "Finishing test for ItemDAOCreateThrowsException" );

    }

    /**
     * Test item dAO create throws exception n error container.
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDAOCreateThrowsExceptionNErrorContainer( ) {

        logger.debug( "Starting test for ItemDAOCreateThrowsExceptionNErrorContainer" );
        DAOResponse< Item > itemDAOResponse;
        dummyItem = new Item( );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        itemDAOResponse = itemDAOImpl.createItem( dummyItem, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
        // Test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemDAOResponse.getCount( ) );

        logger.debug( "Finishing test for ItemDAOCreateThrowsExceptionNErrorContainer" );
    }

    /**
     * This method is to release objects and shut down OR close any connections after Test is completed before testing
     * test method
     *
     * @throws Exception the exception
     */
    @SuppressWarnings( "unchecked" )
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Item where itemId=:item_id" );
        query.setLong( "item_id", TestConstants.MINUS_ONE );

        List< Item > items = query.list( );
        Assert.assertTrue( " List is not Empty, Hib created a dummy Row ", items.isEmpty( ) );
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}
