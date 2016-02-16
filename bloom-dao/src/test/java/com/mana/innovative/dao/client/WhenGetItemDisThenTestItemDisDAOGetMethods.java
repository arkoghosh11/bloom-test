package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The class WhenGetItemDisThenTestItemDisDAOGetMethods is for todo.
 * Created by BLOOM on 11/25/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration(/* transactionManager = "transactionManager", */defaultRollback = true )
@Transactional
public class WhenGetItemDisThenTestItemDisDAOGetMethods {

    /**
     * The ItemDiscount id.
     */
    long itemDiscountId;
    private Logger logger = LoggerFactory.getLogger( WhenGetItemDisThenTestItemDisDAOGetMethods.class );
    /**
     * The ItemDiscount dAO.
     */
    @Resource
    private ItemDiscountDAO itemDiscountDAOImpl;

    private RequestParams requestParams;

    private String key;

    private List< Long > values;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        itemDiscountId = TestConstants.ZERO;

        requestParams = new RequestParams( );
        requestParams.setIsError( TestConstants.IS_ERROR );

        key = TestConstants.KEY_ITEM_DISCOUNT_ID;
        values = new ArrayList<>( );
        values.add( ( long ) TestConstants.ZERO );
        values.add( ( long ) TestConstants.ONE );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {

        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    /**
     * Test get itemDiscount by itemDiscount id.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemDiscountByItemDiscountIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetItemDiscountByItemDiscountIdWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscountByItemDiscountId( itemDiscountId, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertFalse( itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        List< ItemDiscount > itemDiscounts = itemDiscountDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, itemDiscounts.size( ) );
        // Test ItemDiscount
        ItemDiscount itemDiscount = itemDiscounts.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountId, itemDiscount.getItemDiscountId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_USER_ROLE_NAME, itemDiscount.getUserRole( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_ITEM_DISCOUNT_TYPE, itemDiscount.getDiscountType( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_ITEM_DISCOUNT_PERCENT, itemDiscount.getDiscountPercent( ) );

        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount.getStartDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount.getEndDate( ) );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IS_ACTIVE, itemDiscount.isActive( ) );


        // Test itemDiscount items
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount.getItem( ) );

        logger.debug( "Finishing test for GetItemDiscountByItemDiscountIdWithErrorEnabled" );
    }

    /**
     * Test get itemDiscounts.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemDiscountsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetItemDiscountsWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscounts( requestParams );

        // test DAO Response errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );

        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertFalse( itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetItemDiscountsWithErrorEnabled" );
    }

    /**
     * Test get itemDiscount by itemDiscount id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemDiscountByItemDiscountIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetItemDiscountByItemDiscountIdWithErrorDisabled" );

        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscountByItemDiscountId( itemDiscountId, requestParams );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertFalse( itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        List< ItemDiscount > itemDiscounts = itemDiscountDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, itemDiscounts.size( ) );
        // Test ItemDiscount
        ItemDiscount itemDiscount = itemDiscounts.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDiscountId, itemDiscount.getItemDiscountId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_USER_ROLE_NAME, itemDiscount.getUserRole( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_ITEM_DISCOUNT_TYPE, itemDiscount.getDiscountType( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_ITEM_DISCOUNT_PERCENT, itemDiscount.getDiscountPercent( ) );

        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount.getStartDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount.getEndDate( ) );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IS_ACTIVE, itemDiscount.isActive( ) );


        // Test itemDiscount items
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscount.getItem( ) );

        logger.debug( "Finishing test for GetItemDiscountByItemDiscountIdWithErrorDisabled" );
    }

    /**
     * Test get itemDiscounts with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemDiscountsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetItemDiscountsWithErrorDisabled" );

        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscounts( requestParams );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertFalse( itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        logger.debug( "Finishing test for GetItemDiscountsWithErrorDisabled" );
    }

    @Test
    @Transactional( readOnly = true )
    public void testGetItemDiscountByParams( ) throws Exception {

        logger.debug( "Starting test for GetItemDiscountByParams" );

        DAOResponse< ItemDiscount > itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscountsByParams( key,
                values, requestParams );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemDiscountDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertFalse( itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, values.size( ), itemDiscountDAOResponse.getResults( ).size( ) );

        logger.info( "******* Data 0 is\n " + itemDiscountDAOResponse.getResults( ).get( 0 ).toString( ) );
        logger.info( "******* Data 1 is\n " + itemDiscountDAOResponse.getResults( ).get( 1 ).toString( ) );
        logger.debug( "Completing test for GetItemDiscountByParams" );
    }
}
