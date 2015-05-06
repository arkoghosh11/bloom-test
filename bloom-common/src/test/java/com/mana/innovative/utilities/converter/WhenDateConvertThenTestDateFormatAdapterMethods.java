package com.mana.innovative.utilities.converter;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.adapter.DateFormatAdapter;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type When date convert then test date format adapter methods.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenDateConvertThenTestDateFormatAdapterMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( WhenDateConvertThenTestDateFormatAdapterMethods.class );

    /**
     * The Date format adapter.
     */
    private DateFormatAdapter dateFormatAdapter;
    /**
     * The String date.
     */
    private String stringDate;
    /**
     * The Date.
     */
    private Date date;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dateFormatAdapter = new DateFormatAdapter( );
        stringDate = "2015-01-25 15:10:05";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        date = simpleDateFormat.parse( stringDate );
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
     * Test unmarshal.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUnmarshal( ) throws Exception {

        logger.debug( "Starting test for Unmarshal" );
        Date date = dateFormatAdapter.unmarshal( stringDate );
        Assert.assertEquals( TestConstants.notEqualsMessage, this.date, date );
        logger.debug( "Finishing test for Unmarshal" );
    }

    /**
     * Test marshal.
     *
     * @throws Exception the exception
     */
    @Test
    public void testMarshal( ) throws Exception {

        logger.debug( "Starting test for Marshal" );
        String stringDate = dateFormatAdapter.marshal( date );
        Assert.assertEquals( TestConstants.notEqualsMessage, this.stringDate, stringDate );
        logger.debug( "Finishing test for Marshal" );
    }
}