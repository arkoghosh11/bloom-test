package com.mana.innovative.dao.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemImage;
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
 * The class WhenDeleteItemImgThenTestItemImgDAODeleteMethods is for todo.
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
public class WhenDeleteItemImgThenTestItemImgDAODeleteMethods {
	
	private Logger logger = LoggerFactory.getLogger( WhenDeleteItemImgThenTestItemImgDAODeleteMethods.class );
	
	
	/**
	 * The Working hour dAO impl.
	 */
	@Resource
	private ItemImageDAO itemImageDAOImpl;
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
		Query query = session.createQuery( " from ItemImage where itemImageId=:itemImage_id" );
		query.setLong( "itemImage_id", DAOConstants.ZERO );
		List< ItemImage > itemImages = query.list( );
		Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", itemImages.isEmpty( ) );
		logger.debug( TestConstants.setUpMethodLoggerMsg );
		
	}
	
	/**
	 * Test item image dAO not null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testItemImageDAONotNull( ) throws Exception {
		
		logger.debug( "Starting test for ItemImageDAONotNull" );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOImpl );
		logger.debug( "Finishing test for ItemImageDAONotNull" );
	}
	
	/**
	 * Test delete item image by item image id error enabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteItemImageByItemImageIdErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteItemImageByItemImageIdErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteItemImageByItemImageId( testId, requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
		// check ErrorContainer
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemImageDAOResponse.getCount( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteItemImageByItemImageIdErrorEnabled" );
	}
	
	/**
	 * Test delete item image by item image id error disabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteItemImageByItemImageIdErrorDisabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteItemImageByItemImageIdErrorDisabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR );
		
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteItemImageByItemImageId( testId, requestParams );
		// check ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
		
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, itemImageDAOResponse.getCount( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteItemImageByItemImageIdErrorDisabled" );
	}
	
	/**
	 * Test delete item images by item image ids error enabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteItemImagesByItemImageIdsErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteItemImagesByItemImageIdsErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteItemImagesByItemImageIds( testIds, requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
		// check ErrorContainer
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), itemImageDAOResponse.getCount( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteItemImagesByItemImageIdsErrorEnabled" );
	}
	
	/**
	 * Test delete item images by item image ids error disabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteItemImagesByItemImageIdsErrorDisabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteItemImagesByItemImageIdsErrorDisabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR );
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteItemImagesByItemImageIds( testIds,
				requestParams );
		// check ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
		
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), itemImageDAOResponse.getCount( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteItemImagesByItemImageIdsErrorDisabled" );
	}
	
	/**
	 * Test delete all item images with error enabled.
	 *
	 * @throws Exception the exception
	 */
	// IMP Delete all ItemImage Test Cases
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteAllItemImagesWithErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteAllItemImagesWithErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL );
		
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteAllItemImages( requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
		// check ErrorContainer
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteAllItemImagesWithErrorEnabled" );
	}
	
	/**
	 * Test delete all item images with error disabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteAllItemImagesWithErrorDisabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteAllItemImagesWithErrorDisabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR );
		requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL );
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteAllItemImages( requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
		// check ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertFalse( TestConstants.trueMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteAllItemImagesWithErrorDisabled" );
	}
	
	/**
	 * Test delete all item images with delete all true with error enabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteAllItemImagesWithDeleteAllTrueWithErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteAllItemImagesWithDeleteAllTrueWithErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL_TRUE );
		
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteAllItemImages( requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
		// check ErrorContainer
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.getCount( ) > TestConstants.ONE );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteAllItemImagesWithDeleteAllTrueWithErrorEnabled" );
	}
	
	/**
	 * Test delete all item images with delete all true error disabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Rollback( value = true )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public void testDeleteAllItemImagesWithDeleteAllTrueErrorDisabled( ) throws Exception {
		
		logger.debug( "Starting test for DeleteAllItemImagesWithDeleteAllTrueWithErrorDisabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR );
		requestParams.setIsDeleteAll( TestConstants.IS_DELETE_ALL_TRUE );
		DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.deleteAllItemImages( requestParams );
		Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
		// check ErrorContainer
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
		// check DAOResponse
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isDelete( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.isRequestSuccess( ) );
		Assert.assertTrue( TestConstants.falseMessage, itemImageDAOResponse.getCount( ) > TestConstants.ONE );
		Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getResults( ) );
		
		logger.debug( "Finishing test for DeleteAllItemImagesWithDeleteAllTrueWithErrorDisabled" );
	}
	
}
