package com.mana.innovative.dao.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The type When delete a shop test delete methods.
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WhenDeleteShopThenTestShopDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteShopThenTestShopDAODeleteMethods.class );

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
     * Sets up method is to initialize Objects and configuration files before testing test method
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
    }

    /**
     * Test shop dAO not null.
     */
    @Test
    public void testShopDAONotNull( ) {
        Assert.assertNotNull( shopDAOImpl );
    }

    /**
     * Test delete all shops with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllShopsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllShopsWithErrorEnabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteAllShops( TestConstants.IS_DELETE_ALL, TestConstants
                .IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, shopDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllShopsWithErrorEnabled" );
    }

    /**
     * Test delete all shops with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllShopsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllShopsWithErrorDisabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteAllShops( TestConstants.IS_DELETE_ALL, TestConstants
                .IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, shopDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllShopsWithErrorDisabled" );
    }

    /**
     * Test delete all shops with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllShopsWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllShopsWithDeleteAllTrueWithErrorEnabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteAllShops( TestConstants.IS_DELETE_ALL_TRUE, TestConstants
                .IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllShopsWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all shops with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllShopsWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllShopsWithDeleteAllTrueWithErrorDisabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteAllShops( TestConstants.IS_DELETE_ALL_TRUE, TestConstants
                .IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllShopsWithDeleteAllTrueWithErrorDisabled" );
    }


    /**
     * Test delete shop by shop id with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteShopByShopIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteShopByShopIdWithErrorEnabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteShopByShopId( TestConstants.ZERO, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, shopDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteShopByShopIdWithErrorEnabled" );
    }

    /**
     * Test delete shop by shop id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteShopByShopIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteShopByShopIdWithErrorDisabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteShopByShopId( TestConstants.ZERO, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, shopDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteShopByShopIdWithErrorDisabled" );
    }

    /**
     * Test delete shops with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteShopsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteShopsWithErrorDisabled" );
        List< Long > shopIds = new ArrayList<>( );
        shopIds.add( ( long ) TestConstants.ZERO );
        shopIds.add( ( long ) TestConstants.ONE );
        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteShopsByShopIds( shopIds, TestConstants.IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.TWO, shopDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Starting test for DeleteShopsWithErrorDisabled" );
    }

    /**
     * Test delete shops with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteShopsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteShopsWithErrorEnabled" );
        List< Long > shopIds = new ArrayList<>( );
        shopIds.add( ( long ) TestConstants.ZERO );
        shopIds.add( ( long ) TestConstants.ONE );
        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.deleteShopsByShopIds( shopIds, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );

        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, shopDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.TWO, shopDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteShopsWithErrorEnabled" );
    }

    /**
     * Tear down method is to release objects and shut down OR close any connections after Test is completed before
     * testing test method
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Shop where shopId=:shop_id" );
        query.setLong( "shop_id", DAOConstants.ZERO );
        List< Shop > shops = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", shops.isEmpty( ) );
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}
