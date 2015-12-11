package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.dto.request.RequestParams;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
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
 * The class WhenCreateAnItemDiscountDisThenTestItemDiscountDisDAOCreateMethods is for todo.
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
public class WhenCreateAnItemDiscountDisThenTestItemDiscountDisDAOCreateMethods {

    private Logger logger = LoggerFactory.getLogger( WhenCreateAnItemDiscountDisThenTestItemDiscountDisDAOCreateMethods.class );

    /**
     * The ItemDiscount dAO impl.
     */
    @Resource
    private ItemDiscountDAO itemDiscountDAOImpl;

    @Resource
    private ItemDAO itemDAOImpl;

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    private RequestParams requestParams;

    private Date startDate, endDate;

    /**
     * The Dummy item.
     */
    private ItemDiscount dummyItemDiscount;

    private Item item;

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

        dummyItemDiscount = TestDummyDomainObjectGenerator.getTestItemDiscountDomainObject( );
    }

    /**
     * Test item dAO not null.
     */
    @Test
    public void testItemDiscountDAONotNull( ) {

        Assert.assertNotNull( itemDiscountDAOImpl );
    }

    /**
     * Test item dAO create without exception.
     * Note ItemDiscountDAOCreateWithExistingItemIDIIWithoutException ID = item discount, II = item image
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDiscountDAOCreateWithExistingItemIDWithoutException( ) {

        logger.debug( "Starting test for ItemDiscountDAOCreateWithoutException" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItemByItemId( TestConstants.ZERO, requestParams );

        Assert.assertNotNull( itemDAOResponse );
        Assert.assertNotNull( itemDAOResponse.getResults( ) );
        Assert.assertNull( itemDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( itemDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        Item item = itemDAOResponse.getResults( ).get( TestConstants.ZERO );
//        Assert.assertTrue( TestConstants.falseMessage, checkItem( item ));
        DAOResponse< ItemDiscount > itemDiscountDAOResponse;

        if ( item.getItemDiscountList( ) == null ) {
            item.setItemDiscountList( new ArrayList< ItemDiscount >( ) );
        }

        itemDiscountDAOResponse
                = itemDiscountDAOImpl.getItemDiscountByItemDiscountId( dummyItemDiscount.getItemDiscountId( ), requestParams );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.getResults( ).isEmpty( ) );

        item.getItemDiscountList( ).clear( );
        item.getItemDiscountList( ).add( dummyItemDiscount );
        dummyItemDiscount.setItem( item );

        itemDiscountDAOResponse = itemDiscountDAOImpl.createItemDiscount( dummyItemDiscount, requestParams );

        // Test itemDiscountDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );

        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );

        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemDiscountDAOResponse.getCount( ) );
        logger.debug( "Finishing test for ItemDiscountDAOCreateWithoutException" );

    }

    @Test
    public void testItemDiscountDAOCreateWithExistingItemOnlyNErrorContainer( ) throws Exception {

        logger.debug( "Starting test for ItemDiscountDAOCreateWithExistingItemOnlyNErrorContainer" );

        DAOResponse< ItemDiscount > itemDiscountDAOResponse;
        itemDiscountDAOResponse = itemDiscountDAOImpl.getItemDiscountByItemDiscountId( dummyItemDiscount.getItemDiscountId( ), requestParams );

        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.getResults( ).isEmpty( ) );

        itemDiscountDAOResponse = itemDiscountDAOImpl.createItemDiscount( dummyItemDiscount, requestParams );

        // Test itemDiscountDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );

        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );

        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemDiscountDAOResponse.getCount( ) );
        logger.debug( "Finishing test for ItemDiscountDAOCreateWithExistingItemOnlyNErrorContainer" );
    }

    /**
     * Test item dAO create throws exception.
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDiscountDAOCreateThrowsException( ) {

        logger.debug( "Starting test for ItemDiscountDAOCreateThrowsException" );

        DAOResponse< ItemDiscount > itemDiscountDAOResponse;
        dummyItemDiscount = new ItemDiscount( );

        itemDiscountDAOResponse = itemDiscountDAOImpl.createItemDiscount( dummyItemDiscount, requestParams );

        // Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );

        logger.debug( "Finishing test for ItemDiscountDAOCreateThrowsException" );

    }

    /**
     * Test item dAO create throws exception n error container.
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemDiscountDAOCreateThrowsExceptionNErrorContainer( ) {

        logger.debug( "Starting test for ItemDiscountDAOCreateThrowsExceptionNErrorContainer" );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse;
        dummyItemDiscount = new ItemDiscount( );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        itemDiscountDAOResponse = itemDiscountDAOImpl.createItemDiscount( dummyItemDiscount, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse );
        // Test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemDiscountDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemDiscountDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemDiscountDAOResponse.getResults( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemDiscountDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemDiscountDAOResponse.getCount( ) );

        logger.debug( "Finishing test for ItemDiscountDAOCreateThrowsExceptionNErrorContainer" );
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
        Query query = session.createQuery( " from ItemDiscount where itemDiscountId=:item_discount_id" );
        query.setLong( "item_discount_id", TestConstants.MINUS_ONE );

        List< ItemDiscount > items = query.list( );
        Assert.assertTrue( " List is not Empty, Hib created a dummy Row ", items.isEmpty( ) );
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}