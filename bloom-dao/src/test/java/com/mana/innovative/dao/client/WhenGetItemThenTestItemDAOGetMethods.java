package com.mana.innovative.dao.client;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.client.impl.ItemDAOImpl;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.domain.client.ItemImage;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
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
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex1 on 1/21/2015. This is a domain class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "/dbConfig-test.xml" )
@TransactionConfiguration( transactionManager = "transactionManager", defaultRollback = true )
public class WhenGetItemThenTestItemDAOGetMethods {

	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( WhenGetItemThenTestItemDAOGetMethods.class );

	/**
	 * The Default item.
	 */
	private Item defaultItem;
	private RequestParams requestParams;

	/**
	 * The Item dAO impl.
	 */
	@Resource
	private ItemDAO itemDAOImpl;

	private String key;

	private List< String > values;

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * Sets up.
	 */
	@Before
	@BeforeTransaction
	public void setUp( ) {

		logger.debug( TestConstants.setUpMethodLoggerMsg );
		requestParams = new RequestParams( );
		requestParams.setIsError( TestConstants.IS_ERROR );

		defaultItem = new Item( );
		defaultItem.setItemId( TestConstants.ZERO );
		defaultItem.setItemName( TestConstants.DEFAULT_ITEM_NAME );
		defaultItem.setItemPrice( TestConstants.DEFAULT_PRICE );
		defaultItem.setItemDescription( TestConstants.DEFAULT_DESCRIPTION );
		defaultItem.setItemPriceCurrency( TestConstants.DEFAULT_ITEM_PRICE_CURRENCY );
		defaultItem.setItemType( TestConstants.DEFAULT_ITEM_TYPE );
		defaultItem.setItemSubType( TestConstants.DEFAULT_ITEM_SUB_TYPE );
		defaultItem.setBoughtFrom( TestConstants.DEFAULT_BROUGHT_FROM );

		defaultItem.setQuantity( TestConstants.DEFAULT_QUANTITY );
		defaultItem.setQuantityType( QuantityType.UNIT.toString( ) );

		defaultItem.setWeight( TestConstants.DEFAULT_WEIGHT );
		defaultItem.setWeightedUnit( WeightedUnit.KG.toString( ) );

		DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		defaultItem.setItemDiscountList( new ArrayList< ItemDiscount >( ) );
		defaultItem.setItemImageList( new ArrayList< ItemImage >( ) );

		defaultItem.getItemDiscountList( ).add( TestDummyDomainObjectGenerator.getTestItemDiscountDomainObject( ) );
		defaultItem.getItemImageList( ).add( TestDummyDomainObjectGenerator.getTestItemImageDomainObject( 0 ) );
		defaultItem.getItemImageList( ).get( 0 ).setItemImageId( 0 );

		try {
			defaultItem.setCreatedDate( dateFormat.parse( TestConstants.DEFAULT_CREATED_DATE ) );
			defaultItem.setUpdatedDate( dateFormat.parse( TestConstants.DEFAULT_UPDATED_DATE ) );
			defaultItem.setBoughtDate( dateFormat.parse( TestConstants.DEFAULT_BOUGHT_DATE ) );

		} catch ( ParseException e ) {
			logger.error( "Failed to initialize objects before testing", e );
		}

		values = new ArrayList<>( );
		values.add( TestConstants.DEFAULT );
		values.add( TestConstants.TEST );

		key = TestConstants.KEY_GEM_NAME;

		logger.debug( "Finishing setup " );
	}


	/**
	 * Test item dAO not null.
	 */
	@Test
	public void testItemDAONotNull( ) {

		Assert.assertNotNull( TestConstants.nullMessage, itemDAOImpl );
	}

	/**
	 * Test item dAO get item ny item id with error disabled.
	 */
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testItemDAOGetItemNyItemIdWithErrorDisabled( ) {

		logger.debug( "Starting testItemDAOGetItemNyItemIdWithErrorDisabled" );
		Item item;
		DAOResponse< Item > itemDAOResponse = itemDAOImpl
				.getItemByItemId( TestConstants.ZERO, requestParams );

		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
		// test error container
		Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.getResults( ).isEmpty( ) );
		// item list and its size with DAOResponse<T> class count
		List< Item > items = itemDAOResponse.getResults( );

		Assert.assertNotNull( TestConstants.nullMessage, items );
		Assert.assertFalse( TestConstants.trueMessage, items.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, itemDAOResponse.getCount( ), items.size( ) );
		Assert.assertEquals( TestConstants.ONE, items.size( ) );
		// test item
		item = items.get( TestConstants.ZERO );

		Assert.assertNotNull( TestConstants.nullMessage, item );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemId( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemName( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemPrice( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemPriceCurrency( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemType( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getCreatedDate( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getUpdatedDate( ) );

		Assert.assertEquals( TestConstants.notEqualsMessage, defaultItem, item );
//        /** logger.debug*/ System.out.println("defaultItem created " + defaultItem.getCreatedDate().getTime() + " Item created" + item.getCreatedDate().getTime());
//        /** logger.debug*/ System.out.println("defaultItem updated " + defaultItem.getUpdatedDate().getTime() + " Item updated" + item.getUpdatedDate().getTime());
		logger.debug( "Finishing testItemDAOGetItemNyItemIdWithErrorDisabled" );
	}

	/**
	 * Test item dAO read with error disabled.
	 */
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testItemDAOReadWithErrorDisabled( ) {

		logger.debug( "Starting testItemDAOReadWithErrorDisabled" );
		DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItems( requestParams );

		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
		// test error container
		Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.getResults( ).isEmpty( ) );

		List< Item > items = itemDAOResponse.getResults( );

		// item list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, items );
		Assert.assertFalse( TestConstants.trueMessage, items.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, itemDAOResponse.getCount( ), items.size( ) );
		Assert.assertTrue( TestConstants.falseMessage, items.size( ) >= TestConstants.ONE );
//        Assert.assertEquals(27, items.size()); /** Just a guarantee check for making sure new changes are working */
		logger.debug( "Finishing testItemDAOReadWithErrorDisabled" );
	}

	/**
	 * Test item dAO get item ny item id with error enabled.
	 */
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testItemDAOGetItemNyItemIdWithErrorEnabled( ) {

		logger.debug( "Starting testItemDAOGetItemNyItemIdWithErrorEnabled" );
		Item item;
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItemByItemId( TestConstants.ZERO, requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );

		// test error container
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.getResults( ).isEmpty( ) );

		List< Item > items = itemDAOResponse.getResults( );
		// item list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, items );
		Assert.assertFalse( TestConstants.trueMessage, items.isEmpty( ) );
		Assert.assertEquals( TestConstants.ONE, items.size( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, itemDAOResponse.getCount( ), items.size( ) );
		// test item
		item = items.get( TestConstants.ZERO );
		Assert.assertNotNull( TestConstants.nullMessage, item );

		Assert.assertNotNull( TestConstants.nullMessage, item );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemId( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemName( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemPrice( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemPriceCurrency( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getItemType( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getCreatedDate( ) );
		Assert.assertNotNull( TestConstants.nullMessage, item.getUpdatedDate( ) );

		Assert.assertTrue( TestConstants.falseMessage, defaultItem.equals( item ) );
//        /** logger.debug*/ System.out.println("defaultItem created " + defaultItem.getCreatedDate().getTime() + " Item created" + item.getCreatedDate().getTime());
//        /** logger.debug*/ System.out.println("defaultItem updated " + defaultItem.getUpdatedDate().getTime() + " Item updated" + item.getUpdatedDate().getTime());
		logger.debug( "Finishing testItemDAOGetItemNyItemIdWithErrorEnabled" );
	}

	/**
	 * Test item dAO read with error enabled.
	 */
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testItemDAOReadWithErrorEnabled( ) {

		logger.debug( "Starting testItemDAOReadWithErrorEnabled" );
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItems( requestParams );

		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse );
		// test error container
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemDAOResponse.getErrorContainer( ).getCurrentError( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, itemDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, itemDAOResponse.getResults( ).isEmpty( ) );

		List< Item > items = itemDAOResponse.getResults( );
		// item list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, items );
		Assert.assertFalse( TestConstants.trueMessage, items.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, itemDAOResponse.getCount( ), items.size( ) );
		Assert.assertTrue( TestConstants.falseMessage, items.size( ) >= TestConstants.ONE );
//        Assert.assertEquals(27, items.size()); /** Just a guarantee check for making sure new changes are working */
		logger.debug( "Finishing testItemDAOReadWithErrorEnabled" );
	}

//	@SuppressWarnings( "unchecked" )
//	@Test
//  todo remove
//	public void testGetListOfChildren( ) throws Exception {
//
//		logger.debug( "Starting test for GetListOfChildren" );
//
//		Class class1 = values.getClass( );
//		logger.debug( "Component is  **** " + class1 );
//        List results = itemDAOImpl.setIfKeyIsChildList( key, values, requestParams );
//		Assert.assertNotNull( TestConstants.nullMessage, results );
//		List<Gemstone> gemstones = (List<Gemstone>)results;
//		Assert.assertNotNull( TestConstants.notNullMessage, gemstones );
//		Assert.assertFalse( TestConstants.trueMessage, gemstones.isEmpty( ) );
//
//		logger.debug( "Completing test for GetListOfChildren" );
//	}

	/**
	 * Tear down.
	 */
	@After
	@AfterTransaction
	public void tearDown( ) {
		logger.debug( TestConstants.tearDownMethodLoggerMsg );
	}
}
