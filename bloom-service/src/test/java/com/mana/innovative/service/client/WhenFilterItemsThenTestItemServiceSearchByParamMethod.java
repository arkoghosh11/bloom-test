package com.mana.innovative.service.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.dto.request.Filter;
import com.mana.innovative.dto.request.FilterSortParams;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.dto.request.Sort;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * The type WhenFilterItemsThenTestItemServiceSearchByParamMethod.
 * <p/>
 * Created by Bloom/Rono on 2/11/2016 6:18 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/service-config-test.xml", "/db-config-test.xml" } )
public class WhenFilterItemsThenTestItemServiceSearchByParamMethod {

	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( WhenFilterItemsThenTestItemServiceSearchByParamMethod.class );


	@Resource
	private ItemsService itemsService;

	private RequestParams requestParams;
	private FilterSortParams filterSortParams;
	private String gemstoneCriteria;

	@Before
	public void setUp( ) throws Exception {
		logger.debug( TestConstants.setUpMethodLoggerMsg );

		filterSortParams = new FilterSortParams( );

		List< String > params = new ArrayList<>( );

		Filter filter = new Filter( );
//		params.add( "item_type=test_type" );
		filter.setParams( params );
		Sort sort = new Sort( );


		gemstoneCriteria = "gemstone_name=default,emerald";

		filterSortParams.setFilter( filter );
		filterSortParams.setSort( sort );

		requestParams = new RequestParams( );
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );

	}

	@After
	public void tearDown( ) throws Exception {
		logger.debug( TestConstants.tearDownMethodLoggerMsg );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamsWithItemTypeFilter( ) throws Exception {

		logger.debug( "Starting test for #testGetItemsSearchedByParamsWithItemTypeFilter()" );

		List< String > params;
		params = new ArrayList<>( );
		params.add( "item_type=test_type" );
		assertNotNull( TestConstants.nullMessage, itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams ) );
		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams );
		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );

		assertTrue( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );

		logger.debug( "Finishing test for #testGetItemsSearchedByParamsWithItemTypeFilter()" );

	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamWithOnlyGemstone( ) throws Exception {

		logger.debug( "Starting test for GetItemsSearchedByParamWithOnlyGemstone" );

		List< String > params;

		params = new ArrayList<>( );

		params.add( gemstoneCriteria );

		filterSortParams.getFilter( ).setParams( params );

		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams, requestParams );
		assertNotNull( TestConstants.nullMessage, itemResponse );
		assertNotNull( TestConstants.nullMessage, itemResponse.getEntity( ) );

		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ) );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ).getItems( ) );

		assertFalse( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );
		assertEquals( TestConstants.notEqualsMessage, itemResponseContainer.getPayload( ).getItems( ).size( ),
				itemResponseContainer.getPayload( ).getTotalCount( ) );


		logger.debug( "Completing test for GetItemsSearchedByParamWithOnlyGemstone" );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamWithItemTypeNGemstone( ) throws Exception {

		logger.debug( "Starting test for GetItemsSearchedByParamWithItemTypeNGemstone" );

		List< String > params = filterSortParams.getFilter( ).getParams( );

		params.add( "item_type=test_type" );
		params.add( gemstoneCriteria );

		filterSortParams.getFilter( ).setParams( params );

		logger.debug( filterSortParams.toString( ) );
		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams );
		assertNotNull( TestConstants.nullMessage, itemResponse );
		assertNotNull( TestConstants.nullMessage, itemResponse.getEntity( ) );

		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ) );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ).getItems( ) );

		assertTrue( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );
		assertEquals( TestConstants.notEqualsMessage, itemResponseContainer.getPayload( ).getItems( ).size( ),
				itemResponseContainer.getPayload( ).getTotalCount( ) );

		logger.debug( "Completing test for GetItemsSearchedByParamWithItemTypeNGemstone" );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamWithItemTypeNItemId( ) throws Exception {

		logger.debug( "Starting test for GetItemsSearchedByParamWithItemTypeNItemId" );

		List< String > params;
		params = new ArrayList<>( );

		params.add( "item_type=test_type" );
		params.add( "item_id=0,1" );

		filterSortParams.getFilter( ).setParams( params );

		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams );
		assertNotNull( TestConstants.nullMessage, itemResponse );
		assertNotNull( TestConstants.nullMessage, itemResponse.getEntity( ) );

		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ) );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ).getItems( ) );

		assertTrue( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );
		assertEquals( TestConstants.notEqualsMessage, itemResponseContainer.getPayload( ).getItems( ).size( ),
				itemResponseContainer.getPayload( ).getTotalCount( ) );


		logger.debug( "Completing test for GetItemsSearchedBYParamWithItemTypeNItemId" );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamWithMultipleProperties( ) throws Exception {

		logger.debug( "Starting test for GetItemsSearchedByParamWithItemTypeNItemId" );

		List< String > params;

		params = new ArrayList<>( );

		params.add( "item_id=3,4,5" );
		params.add( "item_image_id=3,4,5" );
		params.add( "item_discount_id=3,4,5" );
		params.add( "gemstone_id=3,4,5" );
		params.add( "gemstone_name=test1,test2,test3" );
		params.add( "item_description=test1,test2,test3" );
		params.add( "item_type=test1" );
		filterSortParams.getFilter( ).setParams( params );

		params = new ArrayList<>( );
		params.add( "item_type=asc" );
		params.add( "item_price=desc" );
		filterSortParams.getSort( ).setParams( params );


		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams );
		assertNotNull( TestConstants.nullMessage, itemResponse );
		assertNotNull( TestConstants.nullMessage, itemResponse.getEntity( ) );

		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ) );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ).getItems( ) );

		assertTrue( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );
		assertEquals( TestConstants.notEqualsMessage, itemResponseContainer.getPayload( ).getItems( ).size( ),
				itemResponseContainer.getPayload( ).getTotalCount( ) );


		logger.debug( "Completing test for GetItemsSearchedBYParamWithItemTypeNItemId" );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamWithMultiplePropertiesWithVariableValues( ) throws Exception {

		logger.debug( "Starting test for GetItemsSearchedByParamWithMultiplePropertiesWithVariableValues" );

		List< String > params;

		params = new ArrayList<>( );

		params.add( "item_id=3,4,5" );
		params.add( "item_image_id=3,4,5" );
		params.add( "item_discount_id=3" );
		params.add( "gemstone_id=3,4,5" );
		params.add( "gemstone_name=test3" );
		params.add( "item_description=test3,test2" );
		params.add( "item_type=test1" );
		params.add( "item_price=5.0" );
		filterSortParams.getFilter( ).setParams( params );

		params = new ArrayList<>( );
		params.add( "item_type=asc" );
		params.add( "item_price=desc" );
		filterSortParams.getSort( ).setParams( params );


		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams );
		assertNotNull( TestConstants.nullMessage, itemResponse );
		assertNotNull( TestConstants.nullMessage, itemResponse.getEntity( ) );

		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ) );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ).getItems( ) );

		assertTrue( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );
		assertEquals( TestConstants.notEqualsMessage, itemResponseContainer.getPayload( ).getItems( ).size( ),
				itemResponseContainer.getPayload( ).getTotalCount( ) );


		logger.debug( "Completing test for GetItemsSearchedByParamWithMultiplePropertiesWithVariableValues" );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetItemsSearchedByParamWithSpecificVales( ) throws Exception {

		logger.debug( "Starting test for GetItemsSearchedByParamWithSpecificVales" );

		List< String > params;

		params = new ArrayList<>( );

		params.add( "gemstone_name=" );
		params.add( "item_type=belts" );
		filterSortParams.getFilter( ).setParams( params );

		params = new ArrayList<>( );
//		params.add( "item_type=asc" );
		params.add( "item_price=" );
		filterSortParams.getSort( ).setParams( params );


		Response itemResponse = itemsService.getItemsSearchedByParams( filterSortParams,
				requestParams );
		assertNotNull( TestConstants.nullMessage, itemResponse );
		assertNotNull( TestConstants.nullMessage, itemResponse.getEntity( ) );

		ItemResponseContainer< ItemsPayload > itemResponseContainer = ( ItemResponseContainer< ItemsPayload > ) itemResponse.getEntity( );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ) );
		assertNotNull( TestConstants.nullMessage, itemResponseContainer.getPayload( ).getItems( ) );

		assertFalse( TestConstants.trueMessage, itemResponseContainer.getPayload( ).getItems( ).isEmpty( ) );
		assertEquals( TestConstants.notEqualsMessage, itemResponseContainer.getPayload( ).getItems( ).size( ),
				itemResponseContainer.getPayload( ).getTotalCount( ) );

		assertEquals( TestConstants.notEqualsMessage, 2, itemResponseContainer.getPayload( ).getTotalCount( ) );

		logger.debug( "Completing test for GetItemsSearchedByParamWithSpecificVales" );
	}
}