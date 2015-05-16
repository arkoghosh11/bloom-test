package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.Shop;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type When update an item test item dAO update item.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration
@Transactional
public class WhenUpdateAnItemTestItemDAOUpdateItem {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenUpdateAnItemTestItemDAOUpdateItem.class );
    /**
     * The Id.
     */
    private final long id = TestConstants.ONE;

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
    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;
    /**
     * The Dummy item.
     */
    private Item dummyItem;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyItem = new Item( );
        dummyItem.setItemId( id );
        // dummyItem.setItemName(TestConstants.TEST_VALUE);
        // dummyItem.setItemPriceCurrency(TestConstants.TEST_PRICE_CURRENCY);
        dummyItem.setItemType( TestConstants.UPDATED_TEST_VALUE );
        // dummyItem.setItemPrice(TestConstants.THREE);

    }

    /**
     * Test item dAO not null.
     */
    @Test
    public void testItemDAONotNull( ) {

        Assert.assertNotNull( itemDAOImpl );
    }

    /**
     * Test item dAO update with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public void testItemDAOUpdateWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for ItemDAOUpdateWithErrorDisabled" );
        // dummyItem.setItemPrice(TestConstants.UPDATED_ITEM_PRICE);
        dummyItem.setItemName( TestConstants.UPDATED_TEST_VALUE );
        dummyItem.setItemPriceCurrency( TestConstants.UPDATED_TEST_VALUE );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShopByShopId( id, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );
        // check shopDAOResponse
        Assert.assertNotNull( shopDAOResponse );
        Assert.assertNotNull( shopDAOResponse.getResults( ) );
        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );

        dummyItem.setShopItem( shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.updateItem( dummyItem, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );

        // check itemDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isDelete( ) );

        dummyItem = itemDAOResponse.getResults( ).get( TestConstants.ZERO );

        // check dummyItem's updated values
        Assert.assertNotNull( dummyItem );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemName( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE,
                dummyItem.getItemPriceCurrency( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemType( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, ( double ) TestConstants.TWO, dummyItem.getItemPrice( ) );

        logger.debug( "Finishing test for ItemDAOUpdateWithErrorDisabled" );
    }

    /**
     * Test item dAO update with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testItemDAOUpdateWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for ItemDAOUpdateWithErrorEnabled" );

        dummyItem.setItemName( TestConstants.UPDATED_TEST_VALUE );
        dummyItem.setItemPriceCurrency( TestConstants.UPDATED_TEST_VALUE );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShopByShopId( id, TestConstants.IS_ERROR_TRUE );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check shopDAOResponse
        Assert.assertNotNull( shopDAOResponse );
        Assert.assertNotNull( shopDAOResponse.getResults( ) );
        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );

        dummyItem.setShopItem( shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        DAOResponse< Item > itemDAOResponse = itemDAOImpl.updateItem( dummyItem, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check itemDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isDelete( ) );

        dummyItem = itemDAOResponse.getResults( ).get( TestConstants.ZERO );

        // check dummyItem's updated values
        Assert.assertNotNull( dummyItem );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemName( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE,
                dummyItem.getItemPriceCurrency( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, dummyItem.getItemType( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, ( double ) TestConstants.TWO, dummyItem.getItemPrice( ) );

        logger.debug( "Finishing test for ItemDAOUpdateWithErrorEnabled" );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @SuppressWarnings( "unchecked" )
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Item where itemId=:item_id" );
        query.setLong( "item_id", id );
        List< Item > items = query.list( );
        if ( items.isEmpty( ) ) {
            Assert.assertTrue( TestConstants.falseMessage + ", Hib created a dummy Row ", items.isEmpty( ) );
        } else if ( items.size( ) == TestConstants.ONE ) {
            Item item = items.get( TestConstants.ZERO );
            Assert.assertNotNull( item );
            Assert.assertEquals( " Data modified by Hibernate", TestConstants.TEST_PRICE_CURRENCY,
                    item.getItemPriceCurrency( ) );
        } else {
            Assert.fail( " Unique result expected but got duplicate data" );
        }
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}
