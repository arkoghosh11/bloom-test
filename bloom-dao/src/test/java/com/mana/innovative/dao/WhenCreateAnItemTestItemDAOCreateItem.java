package com.mana.innovative.dao;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.domain.Shop;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by alex1 on 1/28/2015. This is a domain class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration(/* transactionManager = "transactionManager", */  defaultRollback = true )
@Transactional
public class WhenCreateAnItemTestItemDAOCreateItem {

    @Resource
    private ItemDAO itemDAOImpl;

    @Resource
    private ShopDAO shopDAO;

    @Resource
    private SessionFactory sessionFactory;

    private Item dummyItem;


    /**
     * This method is to initialize Objects and configuration files before testing test method
     */
    @Before
    public void setUp( ) throws Exception {


        dummyItem = new Item( );
        dummyItem.setItemId( TestConstants.MINUS_ONE );
        dummyItem.setItemName( TestConstants.TEST_VALUE );
        dummyItem.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
        dummyItem.setItemType( TestConstants.TEST_VALUE );
        dummyItem.setItemSubType( TestConstants.TEST_ITEM_TYPE );
        dummyItem.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

        dummyItem.setItemPrice( TestConstants.THREE );
        dummyItem.setWeight( TestConstants.TEST_WEIGHT );
        dummyItem.setQuantity( TestConstants.TEST_QUANTITY );

        dummyItem.setQuantityType( QuantityType.UNIT.toString( ) );
        dummyItem.setWeightedUnit( WeightedUnit.POUND.toString( ) );

        dummyItem.setCreatedDate( new Date( ) );
        dummyItem.setBoughtDate( new Date( ) );
    }

    @Test
    public void testItemDAONotNull( ) {

        Assert.assertNotNull( itemDAOImpl );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDAOCreate( ) {

        DAOResponse< Item > itemDAOResponse;
        DAOResponse< Shop > shopDAOResponse;
        itemDAOResponse = itemDAOImpl.getItemByItemId( dummyItem.getItemId( ), TestConstants.IS_ERROR );

        Assert.assertTrue( itemDAOResponse.getResults( ).isEmpty( ) );
        shopDAOResponse = shopDAO.getShopByShopId( TestConstants.ZERO, TestConstants.IS_ERROR );

        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );
        Shop shop = shopDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( shop );
        dummyItem.setShopItem( shop );
        itemDAOResponse = itemDAOImpl.createItem( dummyItem, TestConstants.IS_ERROR );


        Assert.assertTrue( itemDAOResponse.isCreate( ) );
        Assert.assertTrue( itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( itemDAOResponse.getResults( ) );
        Assert.assertNotNull( itemDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        Assert.assertNull( itemDAOResponse.getErrorContainer( ) );
        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemDAOResponse
                .getCount( ) );

    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDAOCreateThrowsException( ) {

        DAOResponse< Item > itemDAOResponse;
        dummyItem = new Item( );

        itemDAOResponse = itemDAOImpl.createItem( dummyItem, TestConstants.IS_ERROR );

        Assert.assertTrue( itemDAOResponse.isCreate( ) );
        Assert.assertFalse( itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( itemDAOResponse.getResults( ) );
        Assert.assertTrue( "Size Of List is not Zero", itemDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNull( itemDAOResponse.getErrorContainer( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemDAOResponse
                .getCount( ) );

    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDAOCreateThrowsExceptionNErrorContainer( ) {

        DAOResponse< Item > itemDAOResponse;
        dummyItem = new Item( );

        itemDAOResponse = itemDAOImpl.createItem( dummyItem, TestConstants.IS_ERROR_TRUE );

        Assert.assertTrue( itemDAOResponse.isCreate( ) );
        Assert.assertFalse( itemDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( itemDAOResponse.isDelete( ) );
        Assert.assertNotNull( itemDAOResponse.getResults( ) );
        Assert.assertTrue( itemDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( itemDAOResponse.getErrorContainer( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemDAOResponse
                .getCount( ) );

    }


    /**
     * This method is to release objects and shut down OR close any connections after Test is completed before testing
     * test method
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Item where itemId=:item_id" );
        query.setLong( "item_id", TestConstants.MINUS_ONE );

        List< Item > items = query.list( );
//        if(items.isEmpty()) {
        Assert.assertTrue( " List is not Empty, Hib created a dummy Row ", items.isEmpty( ) );
//        }
//        } else if(items.size() == TestConstants.ONE) {
//            Item item =items.get(TestConstants.ZERO);
//            Assert.assertNotNull(item);
//            Assert.assertEquals(" Data modified by Hibernate", 2.0,item.getItemPrice());
//        } else {
//            Assert.fail(" Unique result expected but got duplicate data");
//        }
    }
}
