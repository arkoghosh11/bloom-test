package com.mana.innovative.utilities.date;

import com.mana.innovative.constants.TestConstants;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type WhenStringCommonsThenTestItsMethods.
 * <p/>
 * Created by Bloom/Rono on 2/12/2016 9:00 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( BlockJUnit4ClassRunner.class )
public class WhenStringCommonsThenTestItsMethods {

	private static final Logger logger = LoggerFactory.getLogger( WhenStringCommonsThenTestItsMethods.class );

	@Before
	public void setUp( ) throws Exception {

		logger.debug( TestConstants.setUpMethodLoggerMsg );
	}

	@After
	public void tearDown( ) throws Exception {

		logger.debug( TestConstants.tearDownMethodLoggerMsg );
	}

	@Test
	public void testAdjustKeyWithProperKey( ) throws Exception {

		Assert.assertEquals( TestConstants.notEqualsMessage, "itemType", StringCommons.adjustKey( "item_type" ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, "itemType_", StringCommons.adjustKey( "item_type_" ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, "itemType12", StringCommons.adjustKey( "item_type_12" ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, "itemType", StringCommons.adjustKey( "item_type" ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, "itemTypeT1T2T3T", StringCommons.adjustKey(
				"item_Type_t1_t2_t3_t"
		) );
		Assert.assertEquals( TestConstants.notEqualsMessage, "itemTypeT1T2T3T2", StringCommons.adjustKey(
				"item_Type_t1_t2_t3_t_2"
		) );
	}

	@Test
	public void testAdjustKeyWithAdjustedKey( ) throws Exception {

		Assert.assertEquals( TestConstants.notEqualsMessage, "itemType", StringCommons.adjustKey( "itemType" ) );
	}

	@Test
	public void testAlterKeyAccToHibMappingWithKey( ) throws Exception {

		logger.debug( "Starting test for AlterKeyAccToHibMappingWithKey" );

		Assert.assertEquals( TestConstants.notEqualsMessage, "gemstone.gemstone_name", StringCommons
				.alterKeyAccToHibMapping( "gemstone_name" ) );
		logger.debug( "Completing test for AlterKeyAccToHibMappingWithKey" );
	}

	@Test
	public void testAlterKeyAccToHibMappingWithInvalidKey( ) throws Exception {

		logger.debug( "Starting test for AlterKeyAccToHibMappingWithInvalidKey" );

		Assert.assertEquals( TestConstants.notEqualsMessage, "gemstone.gemstoneName", StringCommons
				.alterKeyAccToHibMapping( "gemstoneName" ) );
		logger.debug( "Completing test for AlterKeyAccToHibMappingWithInvalidKey" );
	}
}