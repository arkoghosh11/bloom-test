package com.mana.innovative.service.client;

import com.mana.innovative.dto.client.payload.GemstonesPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.TestDummyDTOObjectGenerator;
import com.mana.innovative.service.client.container.GemstoneResponseContainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * The type WhenGetGemstonesThenTestGemstoneServiceGetMethods.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:31 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/service-config-test.xml", "/db-config-test.xml" } )
@TransactionConfiguration
@Transactional
public class WhenGetGemstonesThenTestGemstoneServiceGetMethods {
	
	private static final Logger logger = LoggerFactory.getLogger( WhenGetGemstonesThenTestGemstoneServiceGetMethods.class );
	
	/**
	 * The Gemstones service impl.
	 */
	
	private GemstonesService gemstonesServiceImpl;
	/**
	 * The Gemstone response container.
	 */
	private GemstoneResponseContainer gemstoneResponseContainer;
	/**
	 * The Gemstones payload.
	 */
	private GemstonesPayload gemstonesPayload;
	
	private RequestParams requestParams;
	
	/**
	 * Sets up.
	 */
	@Before
	public void setUp( ) {
		
		logger.info( "Initiating GemstoneService Test" );
		gemstonesServiceImpl = Mockito.mock( GemstonesService.class );
		
		gemstoneResponseContainer = Mockito.mock( GemstoneResponseContainer.class );
		gemstonesPayload = Mockito.mock( GemstonesPayload.class );
		requestParams = Mockito.mock( RequestParams.class );
		
		Response response = Response.status( Status.OK ).entity( gemstoneResponseContainer ).build( );
		
		Mockito.when( gemstonesServiceImpl.getAllGemstones( requestParams ) ).thenReturn( response );
		Mockito.when( gemstoneResponseContainer.getPayload( ) ).thenReturn( gemstonesPayload );
		Mockito.when( gemstoneResponseContainer.getCount( ) ).thenReturn( 1 );
		Mockito.when( gemstonesPayload.getGemstones( ) ).thenReturn( TestDummyDTOObjectGenerator.getNCreateGemstoneDTOList(
		) );
		Mockito.when( gemstonesPayload.getTotalCount( ) ).thenReturn( TestDummyDTOObjectGenerator.getNCreateGemstoneDTOList( ).size( ) );
	}
	
	/**
	 * Test get gemstones.
	 */
	@SuppressWarnings( "unchecked" )
	@Test
	public void testGetGemstones( ) {
		
		logger.info( "Testing GemstoneService GetGemstones method" );
		
		Response response = gemstonesServiceImpl.getAllGemstones( requestParams );
		
		Assert.assertNotNull( response );
		Assert.assertNotNull( response.getEntity( ) );
		logger.info( "Testing GemstoneResponseContainer GetGemstones method" );
		gemstoneResponseContainer = ( GemstoneResponseContainer ) response.getEntity( );
		Assert.assertNotNull( gemstoneResponseContainer.getPayload( ) );
		Assert.assertTrue( gemstoneResponseContainer.getCount( ) > 0 );
		
		logger.info( "Testing GemstonesPayload GetGemstones method" );
		gemstonesPayload = ( GemstonesPayload ) gemstoneResponseContainer.getPayload( );
		Assert.assertNotNull( gemstonesPayload.getGemstones( ) );
		Assert.assertTrue( gemstonesPayload.getGemstones( ).size( ) > 0 );
	}
	
	
	/**
	 * Close void.
	 */
	@After
	public void close( ) {
		
		logger.info( "Shutting  Testing for " + logger.getClass( ).getCanonicalName( ) );
	}
}