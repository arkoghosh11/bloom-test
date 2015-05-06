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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is a test class for testing class todo...
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenDateCommonsThenTestItsMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDateCommonsThenTestItsMethods.class );

    /**
     * The Test date.
     */
    private Date testDate;
    /**
     * The Simple date format.
     */
    private SimpleDateFormat simpleDateFormat;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );
        testDate = simpleDateFormat.parse( "2015-04-14 17:22:12" );

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
     * Test get start date time.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetStartDateTime( ) throws Exception {

        logger.debug( "Starting test for GetStartDateTime" );


        Assert.assertEquals( TestConstants.notEqualsMessage,
                simpleDateFormat.parse( "2015-04-14 17:00:00" ),
                DateCommons.getStartDateTime( testDate ) );

        logger.debug( "Finishing test for GetStartDateTime" );
    }

    /**
     * Test get end date time.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetEndDateTime( ) throws Exception {

        logger.debug( "Starting test for GetEndDateTime" );
        Assert.assertEquals( TestConstants.notEqualsMessage,
                simpleDateFormat.parse( "2015-04-14 17:59:59" ),
                DateCommons.getEndDateTime( testDate ) );

        logger.debug( "Finishing test for GetEndDateTime" );
    }

    /**
     * Test update date to prev hour.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateDateToPrevHour( ) throws Exception {

        logger.debug( "Starting test for UpdateDateToPrevHour" );

        testDate = simpleDateFormat.parse( "2015-04-14 17:59:59" );
        Assert.assertEquals( TestConstants.notEqualsMessage,
                simpleDateFormat.parse( "2015-04-14 16:59:59" ),
                DateCommons.updateDateToPrevHour( testDate ) );

        testDate = simpleDateFormat.parse( "2015-04-01 00:59:59" );
        Assert.assertEquals( TestConstants.notEqualsMessage,
                simpleDateFormat.parse( "2015-03-31 23:59:59" ),
                DateCommons.updateDateToPrevHour( testDate ) );

        testDate = simpleDateFormat.parse( "2015-04-14 00:59:59" );
        Assert.assertEquals( TestConstants.notEqualsMessage,
                simpleDateFormat.parse( "2015-04-13 23:59:59" ),
                DateCommons.updateDateToPrevHour( testDate ) );

        testDate = simpleDateFormat.parse( "2015-01-01 00:59:59" );
        Assert.assertEquals( TestConstants.notEqualsMessage,
                simpleDateFormat.parse( "2014-12-31 23:59:59" ),
                DateCommons.updateDateToPrevHour( testDate ) );

        logger.debug( "Finishing test for UpdateDateToPrevHour" );
    }
}