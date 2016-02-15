package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.domain.client.Item;
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
import java.util.List;

/**
 * The type WhenCreateGemStoneThenTestGemstoneCreateMethods.
 * <p/>
 * Created by Bloom/Rono on 2/13/2016 5:53 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration(/* transactionManager = "transactionManager", */defaultRollback = true )
@Transactional
public class WhenCreateGemStoneThenTestGemstoneCreateMethods {
	
	private static final Logger logger = LoggerFactory.getLogger( WhenCreateGemStoneThenTestGemstoneCreateMethods.class );
	
	/**
	 * The Gemstone dAO impl.
	 */
	@Resource
	private GemstoneDAO gemstoneDAOImpl;
	
	@Resource
	private ItemDAO itemDAOImpl;
	
	/**
	 * The Session factory.
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	private RequestParams requestParams;

	/**
	 * The Dummy item.
	 */
	private Gemstone dummyGemstone;
	
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
		
		dummyGemstone = TestDummyDomainObjectGenerator.getTestGemstoneDomainObject( );
	}
	
	/**
	 * Test item dAO not null.
	 */
	@Test
	public void testGemstoneDAONotNull( ) {
		
		Assert.assertNotNull( gemstoneDAOImpl );
	}
	
	/**
	 * Test item dAO create without exception.
	 * Note GemstoneDAOCreateWithExistingItemIDIIWithoutException ID = item discount, II = item image
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRED )
	public void testGemstoneDAOCreateWithExistingItemIDWithoutException( ) {
		
		logger.debug( "Starting test for GemstoneDAOCreateWithoutException" );
		
		DAOResponse< Item > itemDAOResponse = itemDAOImpl.getItemByItemId( TestConstants.ZERO, requestParams );
		
		Assert.assertNotNull( itemDAOResponse );
		Assert.assertNotNull( itemDAOResponse.getResults( ) );
		Assert.assertNull( itemDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( itemDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		Item item = itemDAOResponse.getResults( ).get( TestConstants.ZERO );
//        Assert.assertTrue( TestConstants.falseMessage, checkItem( item ));
		DAOResponse< Gemstone > gemstoneDAOResponse;
		
		if ( item.getGemstoneList( ) == null ) {
			item.setGemstoneList( new ArrayList< Gemstone >( ) );
		}
		
		gemstoneDAOResponse
				= gemstoneDAOImpl.getGemstoneByGemstoneId( dummyGemstone.getGemstoneId( ), requestParams );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		
		item.getGemstoneList( ).clear( );
		item.getGemstoneList( ).add( dummyGemstone );

		List< Item > itemList = new ArrayList<>( );
		itemList.add( item );

		dummyGemstone.setItemList( itemList );
		
		gemstoneDAOResponse = gemstoneDAOImpl.createGemstone( dummyGemstone, requestParams );
		
		// Test gemstoneDAOResponse
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.isCreate( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.isRequestSuccess( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isUpdate( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isDelete( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		
		// Test ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		
		Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, gemstoneDAOResponse.getCount( ) );
		logger.debug( "Finishing test for GemstoneDAOCreateWithoutException" );
		
	}
	
	@Test
	public void testGemstoneDAOCreateWithExistingItemOnlyNErrorContainer( ) throws Exception {
		
		logger.debug( "Starting test for GemstoneDAOCreateWithExistingItemOnlyNErrorContainer" );
		
		DAOResponse< Gemstone > gemstoneDAOResponse;
		gemstoneDAOResponse = gemstoneDAOImpl.getGemstoneByGemstoneId( dummyGemstone.getGemstoneId( ), requestParams );
		
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		
		gemstoneDAOResponse = gemstoneDAOImpl.createGemstone( dummyGemstone, requestParams );
		
		// Test gemstoneDAOResponse
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.isCreate( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.isRequestSuccess( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isUpdate( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isDelete( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		
		// Test ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		
		Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, gemstoneDAOResponse.getCount( ) );
		logger.debug( "Finishing test for GemstoneDAOCreateWithExistingItemOnlyNErrorContainer" );
	}
	
	/**
	 * Test item dAO create throws exception.
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRED )
	public void testGemstoneDAOCreateThrowsException( ) {
		
		logger.debug( "Starting test for GemstoneDAOCreateThrowsException" );
		
		DAOResponse< Gemstone > gemstoneDAOResponse;
		dummyGemstone = new Gemstone( );
		
		gemstoneDAOResponse = gemstoneDAOImpl.createGemstone( dummyGemstone, requestParams );
		
		// Test WorkingHourDAOResponse
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.isCreate( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isRequestSuccess( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isUpdate( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isDelete( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		// Test ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		
		logger.debug( "Finishing test for GemstoneDAOCreateThrowsException" );
		
	}
	
	/**
	 * Test item dAO create throws exception n error container.
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRED )
	public void testGemstoneDAOCreateThrowsExceptionNErrorContainer( ) {
		
		logger.debug( "Starting test for GemstoneDAOCreateThrowsExceptionNErrorContainer" );
		DAOResponse< Gemstone > gemstoneDAOResponse;
		dummyGemstone = new Gemstone( );
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		gemstoneDAOResponse = gemstoneDAOImpl.createGemstone( dummyGemstone, requestParams );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// Test Error Container
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		
		// Test WorkingHourDAOResponse
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.isCreate( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isRequestSuccess( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isUpdate( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.isDelete( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		Assert.assertEquals( "Value of Count is not Zero", TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		
		logger.debug( "Finishing test for GemstoneDAOCreateThrowsExceptionNErrorContainer" );
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
		Query query = session.createQuery( " from Gemstone where gemstoneId=:item_discount_id" );
		query.setLong( "item_discount_id", TestConstants.MINUS_ONE );
		
		List< Gemstone > items = query.list( );
		Assert.assertTrue( " List is not Empty, Hib created a dummy Row ", items.isEmpty( ) );
		logger.debug( TestConstants.tearDownMethodLoggerMsg );
	}
	
}