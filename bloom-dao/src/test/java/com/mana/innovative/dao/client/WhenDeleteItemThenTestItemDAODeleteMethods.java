package com.mana.innovative.dao.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import junit.framework.Assert;
import org.apache.log4j.Logger;
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
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The type When delete item then test item dAO delete methods. </p> Created by bloom on 1/28/2015. This class is for
 * testing given {@link ItemDAO#deleteItemsByItemIds(List, boolean)} & {@link ItemDAO#deleteItemByItemId(long, boolean)}
 * &{@link ItemDAO#deleteAllItems(boolean, boolean)}
 * <p/>
 * Please uncomment the following lines to enable Spring Integration Test the 2nd line requires location on Context
 * Config Files for beans and properties extra, the 1st one is to enable Spring for the Class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WhenDeleteItemThenTestItemDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( WhenDeleteItemThenTestItemDAODeleteMethods.class );

    /**
     * The Item dAO impl.
     */
    @Resource
    private ItemDAO itemDAOImpl;

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * The Item id.
     */
    private Long itemId;
    /**
     * The Item ids.
     */
    private List< Long > itemIds;


    /**
     * This method is to initialize Objects and configuration files before testing test method
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        itemId = TestConstants.TEST_ID;
        itemIds = new ArrayList<>( );
        itemIds.add( ( long ) TestConstants.ZERO );
        itemIds.add( TestConstants.TEST_ID );
    }

    /**
     * Test item dAO not null.
     */
    @Test
    public void testItemDAONotNull( ) {
        Assert.assertNotNull( itemDAOImpl );
    }

    /**
     * Test delete all items with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllItemsWithErrorEnabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteAllItems( TestConstants.IS_DELETE_ALL, TestConstants
                .IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllItemsWithErrorEnabled" );
    }

    /**
     * Test delete all items with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllItemsWithErrorDisabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteAllItems( TestConstants.IS_DELETE_ALL, TestConstants
                .IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllItemsWithErrorDisabled" );
    }

    /**
     * Test delete all items with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemsWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllItemsWithDeleteAllTrueWithErrorEnabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteAllItems( TestConstants.IS_DELETE_ALL_TRUE, TestConstants
                .IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllItemsWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all items with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemsWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllItemsWithDeleteAllTrueWithErrorDisabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteAllItems( TestConstants.IS_DELETE_ALL_TRUE, TestConstants
                .IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllItemsWithDeleteAllTrueWithErrorDisabled" );
    }


    /**
     * Test delete item by item id with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemByItemIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemByItemIdWithErrorEnabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItemByItemId( itemId, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteItemByItemIdWithErrorEnabled" );
    }

    /**
     * Test delete item by item id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemByItemIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemByItemIdWithErrorDisabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItemByItemId( itemId, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteItemByItemIdWithErrorDisabled" );
    }

    /**
     * Test delete item with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemsByItemIdsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemsByItemIdsWithErrorDisabled" );
        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItemsByItemIds( itemIds, TestConstants.IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, itemIds.size( ), itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Starting test for DeleteItemsByItemIdsWithErrorDisabled" );
    }

    /**
     * Test delete item with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemsByItemIdsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemsByItemIdsWithErrorEnabled" );
        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItemsByItemIds( itemIds, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, itemIds.size( ), itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Starting test for DeleteItemsByItemIdsWithErrorEnabled" );
    }

    /**
     * Tear down. This method is to release objects and shut down OR close any connections after Test is completed
     * before testing test method
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Item where itemId=:item_id" );
        query.setLong( "item_id", DAOConstants.ZERO );
        List< Item > items = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", items.isEmpty( ) );
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}



