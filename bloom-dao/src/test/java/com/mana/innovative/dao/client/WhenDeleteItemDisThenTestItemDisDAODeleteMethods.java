package com.mana.innovative.dao.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemDiscount;
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
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The class WhenDeleteItemDisThenTestItemDisDAODeleteMethods is for todo.
 * Created by BLOOM on 11/25/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WhenDeleteItemDisThenTestItemDisDAODeleteMethods {

    private Logger logger = LoggerFactory.getLogger( WhenDeleteItemDisThenTestItemDisDAODeleteMethods.class );
    
    
    /**
     * The Working hour dAO impl.
     */
    @Resource
    private ItemDiscountDAO itemDiscountDAOImpl;
    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;
    
    private RequestParams requestParams;
    
    /**
     * The Test id.
     */
    private long testId;
    /**
     * The Test ids.
     */
    private List< Long > testIds;
    
    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.TEST_ID;
        testIds = new ArrayList<>( );
        testIds.add( ( long ) TestConstants.ZERO );
        testIds.add( testId );
        
        requestParams = new RequestParams( );
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
        Query query = session.createQuery( " from ItemDiscount where itemDiscountId=:itemDiscount_id" );
        query.setLong( "itemDiscount_id", DAOConstants.ZERO );
        List< ItemDiscount > itemDiscounts = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", itemDiscounts.isEmpty( ) );
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        
    }
    
    /**
     * Test item discount dAO not null.
     *
     * @throws Exception the exception
     */
    @Test
    public void testItemDiscountDAONotNull( ) throws Exception {
        
        logger.debug( "Starting test for ItemDiscountDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOImpl );
        logger.debug( "Finishing test for ItemDiscountDAONotNull" );
    }
    
    /**
     * Test delete item discount by item discount id error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteItemDiscountByItemDiscountIdErrorEnabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteItemDiscountByItemDiscountIdErrorEnabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteItemDiscountByItemDiscountId( testId, requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemDiscountDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteItemDiscountByItemDiscountIdErrorEnabled" );
    }
    
    /**
     * Test delete item discount by item discount id error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteItemDiscountByItemDiscountIdErrorDisabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteItemDiscountByItemDiscountIdErrorDisabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR );
        
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteItemDiscountByItemDiscountId( testId, requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemDiscountDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteItemDiscountByItemDiscountIdErrorDisabled" );
    }
    
    /**
     * Test delete item discounts by item discount ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteItemDiscountsByItemDiscountIdsErrorEnabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteItemDiscountsByItemDiscountIdsErrorEnabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteItemDiscountsByItemDiscountIds( testIds, requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), itemDiscountDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteItemDiscountsByItemDiscountIdsErrorEnabled" );
    }
    
    /**
     * Test delete item discounts by item discount ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteItemDiscountsByItemDiscountIdsErrorDisabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteItemDiscountsByItemDiscountIdsErrorDisabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteItemDiscountsByItemDiscountIds( testIds,
                requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), itemDiscountDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteItemDiscountsByItemDiscountIdsErrorDisabled" );
    }
    
    /**
     * Test delete all item discounts with error enabled.
     *
     * @throws Exception the exception
     */
    // IMP Delete all ItemDiscount Test Cases
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemDiscountsWithErrorEnabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteAllItemDiscountsWithErrorEnabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL );
        
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteAllItemDiscounts( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteAllItemDiscountsWithErrorEnabled" );
    }
    
    /**
     * Test delete all item discounts with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemDiscountsWithErrorDisabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteAllItemDiscountsWithErrorDisabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteAllItemDiscounts( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteAllItemDiscountsWithErrorDisabled" );
    }
    
    /**
     * Test delete all item discounts with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemDiscountsWithDeleteAllTrueWithErrorEnabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteAllItemDiscountsWithDeleteAllTrueWithErrorEnabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL_TRUE );
        
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteAllItemDiscounts( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteAllItemDiscountsWithDeleteAllTrueWithErrorEnabled" );
    }
    
    /**
     * Test delete all item discounts with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllItemDiscountsWithDeleteAllTrueErrorDisabled( ) throws Exception {
        
        logger.debug( "Starting test for DeleteAllItemDiscountsWithDeleteAllTrueWithErrorDisabled" );
        
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL_TRUE );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.deleteAllItemDiscounts( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getResults( ) );
        
        logger.debug( "Finishing test for DeleteAllItemDiscountsWithDeleteAllTrueWithErrorDisabled" );
    }

}
