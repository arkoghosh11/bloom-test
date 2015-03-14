package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.impl.ItemDAOImpl;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import junit.framework.Assert;
import org.apache.log4j.Level;
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
import java.util.List;

/**
 * Created by alex1 on 1/28/2015. This class is for testing given {@link ItemDAOImpl#deleteItem(Item, boolean)} & {@link
 * ItemDAOImpl#deleteItemByItemId(long, boolean)}
 * <p/>
 * Please uncomment the following lines to enable Spring Integration Test the 2nd line requires location on Context
 * Config Files for beans and properties extra, the 1st one is to enable Spring for the Class
 *
 * @ RunWith(value = SpringJUnit4ClassRunner.class | MockitoWithJunitRunner.Class)
 * @ ContextConfiguration(location {"loc1"."loc2"})
 * @ TransactionConfiguration   <--- Only If required
 * @ Transactional              <--- Only If required
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WhenDeleteItemThenTestItemDAODeleteMethods {

    private static final Logger logger = Logger.getLogger( WhenDeleteItemThenTestItemDAODeleteMethods.class );

    @Resource
    private ItemDAO itemDAOImpl;

    @Resource
    private SessionFactory sessionFactory;

    private Item dummyItem;


    /**
     * This method is to initialize Objects and configuration files before testing test method
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        try {
            DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItemByItemId( TestConstants.ZERO, TestConstants.IS_ERROR );

            List< Item > items = itemDAOResponse.getResults( );
            Assert.assertFalse( TestConstants.trueMessage, items.isEmpty( ) );
            dummyItem = items.get( TestConstants.ZERO );
        } catch ( Exception e ) {
            logger.log( Level.ERROR, "Exception occurred in test initializer while try to fetch the deleting record from DB", e );
        }
    }

    @Test
    public void testItemDAONotNull( ) {
        Assert.assertNotNull( itemDAOImpl );
    }

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


    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemByItemIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemByItemIdWithErrorEnabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItemByItemId( TestConstants.ZERO, TestConstants.IS_ERROR_TRUE );
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

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemByItemIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemByItemIdWithErrorDisabled" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItemByItemId( TestConstants.ZERO, TestConstants.IS_ERROR );
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
     * This method is to test deletion of an item
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testDeleteItemWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteItemWithErrorDisabled" );
        DAOResponse< Item > itemDAOResponse = itemDAOImpl.deleteItem( dummyItem, TestConstants.IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getResults( ) );

        logger.debug( "Starting test for DeleteItemWithErrorDisabled" );
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
        query.setLong( "item_id", DAOConstants.ZERO );
        List< Item > items = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", items.isEmpty( ) );
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}



