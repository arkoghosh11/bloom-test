package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.ItemImage;
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
 * The class WhenCreateAnItemImgThenTestItemImgDAOCreateMethods is for todo.
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
public class WhenCreateAnItemImgThenTestItemImgDAOCreateMethods {

    private Logger logger = LoggerFactory.getLogger( WhenCreateAnItemImgThenTestItemImgDAOCreateMethods.class );

    /**
     * The ItemImage dAO impl.
     */
    @Resource
    private ItemImageDAO itemImageDAOImpl;

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
    private ItemImage dummyItemImage;

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

        dummyItemImage = TestDummyDomainObjectGenerator.getTestItemImageDomainObject( TestConstants.MINUS_ONE );
    }

    /**
     * Test item dAO not null.
     */
    @Test
    public void testItemImageDAONotNull( ) {

        Assert.assertNotNull( itemImageDAOImpl );
    }

    /**
     * Test item dAO create without exception.
     * Note ItemImageDAOCreateWithExistingItemIDIIWithoutException ID = item discount, II = item image
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemImageDAOCreateWithExistingItemIDWithoutException( ) {

        logger.debug( "Starting test for ItemImageDAOCreateWithoutException" );

        DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItemByItemId( TestConstants.ZERO, requestParams );

        Assert.assertNotNull( itemDAOResponse );
        Assert.assertNotNull( itemDAOResponse.getResults( ) );
        Assert.assertNull( itemDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( itemDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        Item item = itemDAOResponse.getResults( ).get( TestConstants.ZERO );
//        Assert.assertTrue( TestConstants.falseMessage, checkItem( item ));
        DAOResponse< ItemImage > itemImageDAOResponse;

        if ( item.getItemImageList( ) == null ) {
            item.setItemImageList( new ArrayList< ItemImage >( ) );
        }

        itemImageDAOResponse
                = itemImageDAOImpl.getItemImageByItemImageId( dummyItemImage.getItemImageId( ), requestParams );
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.getResults( ).isEmpty( ) );

        item.getItemImageList( ).clear( );
        item.getItemImageList( ).add( dummyItemImage );
        dummyItemImage.setItem( item );

        itemImageDAOResponse = itemImageDAOImpl.createItemImage( dummyItemImage, requestParams );

        // Test itemImageDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );

        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );

        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemImageDAOResponse.getCount( ) );
        logger.debug( "Finishing test for ItemImageDAOCreateWithoutException" );

    }

    @Test
    public void testItemImageDAOCreateWithExistingItemOnlyNErrorContainer( ) throws Exception {

        logger.debug( "Starting test for ItemImageDAOCreateWithExistingItemOnlyNErrorContainer" );

        DAOResponse< ItemImage > itemImageDAOResponse;
        itemImageDAOResponse = itemImageDAOImpl.getItemImageByItemImageId( dummyItemImage.getItemImageId( ), requestParams );

        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.getResults( ).isEmpty( ) );

        itemImageDAOResponse = itemImageDAOImpl.createItemImage( dummyItemImage, requestParams );

        // Test itemImageDAOResponse
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );

        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );

        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, itemImageDAOResponse.getCount( ) );
        logger.debug( "Finishing test for ItemImageDAOCreateWithExistingItemOnlyNErrorContainer" );
    }

    /**
     * Test item dAO create throws exception.
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemImageDAOCreateThrowsException( ) {

        logger.debug( "Starting test for ItemImageDAOCreateThrowsException" );

        DAOResponse< ItemImage > itemImageDAOResponse;
        dummyItemImage = new ItemImage( );

        itemImageDAOResponse = itemImageDAOImpl.createItemImage( dummyItemImage, requestParams );

        // Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.getResults( ).isEmpty( ) );
        // Test ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemImageDAOResponse.getCount( ) );

        logger.debug( "Finishing test for ItemImageDAOCreateThrowsException" );

    }

    /**
     * Test item dAO create throws exception n error container.
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRED )
    public void testItemImageDAOCreateThrowsExceptionNErrorContainer( ) {

        logger.debug( "Starting test for ItemImageDAOCreateThrowsExceptionNErrorContainer" );
        DAOResponse< ItemImage > itemImageDAOResponse;
        dummyItemImage = new ItemImage( );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        itemImageDAOResponse = itemImageDAOImpl.createItemImage( dummyItemImage, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
        // Test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, itemImageDAOResponse.getCount( ) );

        logger.debug( "Finishing test for ItemImageDAOCreateThrowsExceptionNErrorContainer" );
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
        Query query = session.createQuery( " from ItemImage where itemImageId=:item_discount_id" );
        query.setLong( "item_discount_id", TestConstants.MINUS_ONE );

        List< ItemImage > items = query.list( );
        Assert.assertTrue( " List is not Empty, Hib created a dummy Row ", items.isEmpty( ) );
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}