package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.client.impl.ShopDAOImpl;
import com.mana.innovative.dao.response.DAOResponse;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * This class is for testing given {@link ShopDAOImpl#updateShop(Shop, boolean)}
 * <p/>
 * Please uncomment the following lines to enable Spring Integration Test the 2nd line requires location on Context
 * Config Files for beans and properties extra, the 1st one is to enable Spring for the Class
 *
 * @author Rono, Ankur Bhardwaj
 * @ RunWith(value = SpringJUnit4ClassRunner.class | MockitoWithJunitRunner.Class) @ ContextConfiguration(location
 * {"loc1"."loc2"}) @ TransactionConfiguration <--- Only If required @ Transactional <--- Only If required
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration
@Transactional
public class WhenUpdateAShopThenTestShopDAOUpdateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenUpdateAShopThenTestShopDAOUpdateMethods.class );
    /**
     * The Id.
     */
    private final long id = TestConstants.ONE;

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
     * The Dummy shop.
     */
    private Shop dummyShop;

    /**
     * This method is to initialize Objects and configuration files before testing test method
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyShop = new Shop( );
        dummyShop.setShopId( id );
        // dummyShop.setShopName(TestConstants.TEST_VALUE);
        // dummyShop.setShopPriceCurrency(TestConstants.TEST_PRICE_CURRENCY);
        // dummyShop.setShopType( TestConstants.UPDATED_TEST_VALUE );
        // dummyShop.setShopPrice(TestConstants.THREE);

    }

    /**
     * Test shop dAO not null.
     */
    @Test
    public void testShopDAONotNull( ) {

        Assert.assertNotNull( shopDAOImpl );
    }

    /**
     * todo This method is to test the behavior of ...
     *
     * @throws Exception the exception
     */
    // @Test
    // @Rollback( value = true )
    // @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public void testShopDAOUpdate( ) throws Exception {

        // dummyShop.setShopPrice(TestConstants.UPDATED_ITEM_PRICE);
        dummyShop.setShopName( TestConstants.UPDATED_TEST_VALUE );
        // dummyShop.setShopPriceCurrency( TestConstants.UPDATED_TEST_VALUE );

        // DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShopByShopId( id, TestConstants.IS_ERROR );
        // Assert.assertNotNull( shopDAOResponse );
        // Assert.assertNotNull( shopDAOResponse.getResults( ) );
        // Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );

        // dummyShop.setShopShop( shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.updateShop( dummyShop, TestConstants.IS_ERROR );

        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.isDelete( ) );

        dummyShop = shopDAOResponse.getResults( ).get( TestConstants.ZERO );
        // dummyShop = shopDAO.getShopByShopId(dummyShop.getShopId(), TestConstants.IS_ERROR).getResults().get
        // (TestConstants.ZERO);

        Assert.assertNotNull( dummyShop );
        // Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, dummyShop.getShopName(
        // ) );
        // Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE,
        // dummyShop.getShopPriceCurrency( ) );
        // Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, dummyShop.getShopType(
        // ) );
        // Assert.assertEquals( TestConstants.notEqualsMessage, 2.0, dummyShop.getShopPrice( ) );
    }

    /**
     * Test update shop with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateShopWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for UpdateShopWithErrorEnabled" );

        logger.debug( "Finishing test for UpdateShopWithErrorEnabled" );
    }

    /**
     * Test update shop with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateShopWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for UpdateShopWithErrorDisabled" );

        logger.debug( "Finishing test for UpdateShopWithErrorDisabled" );
    }

    /**
     * Test update shop with item only.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateShopWithItemOnly( ) throws Exception {

        logger.debug( "Starting test for UpdateShopWithItemOnly" );

        logger.debug( "Finishing test for UpdateShopWithItemOnly" );
    }

    /**
     * Test update shop with address only.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateShopWithAddressOnly( ) throws Exception {

        logger.debug( "Starting test for UpdateShopWithAddressOnly" );

        logger.debug( "Finishing test for UpdateShopWithAddressOnly" );
    }

    /**
     * Test update shop with working hour only.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateShopWithWorkingHourOnly( ) throws Exception {

        logger.debug( "Starting test for UpdateShopWithWorkingHourOnly" );

        logger.debug( "Finishing test for UpdateShopWithWorkingHourOnly" );
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
        Query query = session.createQuery( " from Shop where shopId=:shop_id" );
        query.setLong( "shop_id", id );
        List< Shop > shops = query.list( );
        if ( shops.isEmpty( ) ) {
            Assert.assertTrue( TestConstants.falseMessage + ", Hib created a dummy Row ", shops.isEmpty( ) );
        } else if ( shops.size( ) == TestConstants.ONE ) {
            Shop shop = shops.get( TestConstants.ZERO );
            Assert.assertNotNull( shop );
            // Assert.assertEquals( " Data modified by Hibernate", TestConstants.TEST_PRICE_CURRENCY,
            // shop.getShopPriceCurrency( ) );
        } else {
            Assert.fail( " Unique result expected but got duplicate data" );
        }
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}
