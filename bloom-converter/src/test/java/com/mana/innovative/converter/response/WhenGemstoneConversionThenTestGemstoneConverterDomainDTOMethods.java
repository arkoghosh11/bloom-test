package com.mana.innovative.converter.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.TestDummyDTOObjectGenerator;
import com.mana.innovative.converter.TestDummyDomainObjectGenerator;
import com.mana.innovative.dto.client.Gemstone;
import com.mana.innovative.exception.IllegalArgumentValueException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type WhenGemstoneConversionThenTestGemstoneConverterDomainDTOMethods.
 * <p/>
 * Created by Bloom/Rono on 2/13/2016 5:26 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( BlockJUnit4ClassRunner.class )
public class WhenGemstoneConversionThenTestGemstoneConverterDomainDTOMethods {
	
	private Logger logger = LoggerFactory.getLogger( WhenGemstoneConversionThenTestGemstoneConverterDomainDTOMethods.class );
	
	/**
	 * The Gemstone dTO.
	 */
	private Gemstone gemstoneDTO, /**
	 * The Gemstone dTO 2.
	 */
	gemstoneDTO2;
	/**
	 * The Gemstone domain.
	 */
	private com.mana.innovative.domain.client.Gemstone gemstoneDomain, /**
	 * The Gemstone domain 2.
	 */
	gemstoneDomain2;
	
	/**
	 * Sets up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp( ) throws Exception {
		
		logger.debug( TestConstants.setUpMethodLoggerMsg );
		
		gemstoneDomain2 = new com.mana.innovative.domain.client.Gemstone( );
		gemstoneDTO2 = new Gemstone( );
		
		// Set Values for tempValues
		gemstoneDTO = TestDummyDTOObjectGenerator.getTestGemstoneDTOObject( );
		gemstoneDomain = TestDummyDomainObjectGenerator.getTestGemstoneDomainObject( );
	}
	
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown( ) throws Exception {
		logger.debug( TestConstants.setUpMethodLoggerMsg );
		
	}
	
	/**
	 * Test get converted dTO from domain.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetConvertedDTOFromDomain( ) throws Exception {
		
		logger.debug( "Starting  GetConvertedDTOFromDomain" );
		
		gemstoneDTO2 = GemstoneDomainDTOConverter.getConvertedDTOFromDomain( gemstoneDTO2, gemstoneDomain );
		
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDTO2 );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDTO, gemstoneDTO2 );
		
		gemstoneDTO2 = GemstoneDomainDTOConverter.getConvertedDTOFromDomain( null, gemstoneDomain );
		
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDTO2 );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDTO, gemstoneDTO2 );
		
		gemstoneDTO2 = GemstoneDomainDTOConverter.getConvertedDTOFromDomain( null, gemstoneDomain );
		
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDTO2 );
		
		logger.debug( "Finishing GetConvertedDTOFromDomain" );
	}
	
	/**
	 * Test get converted list dTO from domain.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetConvertedListDTOFromDomain( ) throws Exception {
		
		logger.debug( "Starting  GetConvertedListDTOFromDomain" );
		
		List< Gemstone > gemstoneDTOList;
		List< com.mana.innovative.domain.client.Gemstone > gemstoneDomainList = new ArrayList<>( );
		gemstoneDomainList.add( gemstoneDomain );
		if ( gemstoneDomain.getGemstoneId( ) != TestConstants.TEST_ID ) {
			TestDummyDomainObjectGenerator.setTestGemstoneDomainZEROIDObject( gemstoneDomain );
		}
		gemstoneDTOList = GemstoneDomainDTOConverter.getConvertedListDTOFromDomain( gemstoneDomainList );
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDTOList );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDTOList.isEmpty( ) );
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDTOList.get( TestConstants.ZERO ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDTO, gemstoneDTOList.get( TestConstants.ZERO ) );
		
		logger.debug( "Finishing GetConvertedListDTOFromDomain" );
	}
	
	/**
	 * Test get converted domain from dTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetConvertedDomainFromDTO( ) throws Exception {
		
		logger.debug( "Starting  GetConvertedDomainFromDTO" );
		
		gemstoneDomain2 = GemstoneDomainDTOConverter.getConvertedDomainFromDTO( gemstoneDomain2, gemstoneDTO );
		TestDummyDomainObjectGenerator.setTestGemstoneDomainZEROIDObject( gemstoneDomain );
		
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDomain2 );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDomain, gemstoneDomain2 );
		
		gemstoneDomain2 = GemstoneDomainDTOConverter.getConvertedDomainFromDTO( null, gemstoneDTO );
		TestDummyDomainObjectGenerator.setTestGemstoneDomainZEROIDObject( gemstoneDomain );
		
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDomain2 );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDomain, gemstoneDomain2 );
		
		logger.debug( "Finishing GetConvertedDomainFromDTO" );
	}
	
	/**
	 * Test get converted list domain from dTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetConvertedListDomainFromDTO( ) throws Exception {
		
		logger.debug( "Starting  GetConvertedListDomainFromDTO" );
		
		List< Gemstone > gemstoneDTOList = new ArrayList<>( );
		List< com.mana.innovative.domain.client.Gemstone > gemstoneDomainList;
		gemstoneDTOList.add( gemstoneDTO );
		TestDummyDomainObjectGenerator.setTestGemstoneDomainZEROIDObject( gemstoneDomain );
		
		gemstoneDomainList = GemstoneDomainDTOConverter.getConvertedListDomainFromDTO( gemstoneDTOList );
		
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDomainList );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDomainList.isEmpty( ) );
		Assert.assertNotNull( TestConstants.notNullMessage, gemstoneDomainList.get( TestConstants.ZERO ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDomain, gemstoneDomainList.get( TestConstants.ZERO ) );
		
		logger.debug( "Finishing GetConvertedListDomainFromDTO" );
	}
	
	/**
	 * Test get converted domain from dTO for error.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetConvertedDomainFromDTOForError( ) throws Exception {
		
		logger.debug( "Starting  testGetConvertedDomainFromDTOForError" );
		Gemstone itemDis = new Gemstone( );
		IllegalArgumentValueException illegalArgumentValueException = null;
		NullPointerException nullPointerException = null;
		try {
			gemstoneDomain2 = GemstoneDomainDTOConverter.getConvertedDomainFromDTO( gemstoneDomain2, itemDis );
		} catch ( IllegalArgumentValueException exception ) {
			illegalArgumentValueException = exception;
		}
		Assert.assertNotNull( TestConstants.notNullMessage, illegalArgumentValueException );
		try {
			gemstoneDomain2 = GemstoneDomainDTOConverter.getConvertedDomainFromDTO( null, null );
		} catch ( NullPointerException exception ) {
			nullPointerException = exception;
		}
		Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
		logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
	}
	
	/**
	 * Test get converted dTO from domain for error.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetConvertedDTOFromDomainForError( ) throws Exception {
		
		logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
		NullPointerException nullPointerException = null;
		try {
			gemstoneDTO2 = GemstoneDomainDTOConverter.getConvertedDTOFromDomain( null, null );
		} catch ( NullPointerException exception ) {
			nullPointerException = exception;
		}
		Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
		logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
	}
}