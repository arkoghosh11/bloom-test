package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
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
 * Created by Bloom/Rono on 4/30/2015 7:58 PM. This class WhenCalEventConversionThenTestCalEventConverterDomainDTOMethods
 * is a test class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenCalEventConversionThenTestCalEventConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCalEventConversionThenTestCalEventConverterDomainDTOMethods.class );

    /**
     * The Calendar event dTO.
     */
    private CalendarEvent calendarEventDTO, /**
     * The Calendar event dTO 2.
     */
    calendarEventDTO2;
    /**
     * The Calendar event domain.
     */
    private com.mana.innovative.domain.common.CalendarEvent calendarEventDomain, /**
     * The Calendar event domain 2.
     */
    calendarEventDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        calendarEventDomain2 = new com.mana.innovative.domain.common.CalendarEvent( );
        calendarEventDTO2 = new CalendarEvent( );

        // Set Values for tempValues
        calendarEventDTO = TestDummyDTOObjectGenerator.getTestCalendarEventDTOObject( );
        calendarEventDomain = TestDummyDomainObjectGenerator.getTestCalendarEventDomainObject( );

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

        calendarEventDTO2 = CalendarEventDomainDTOConverter.getConvertedDTOFromDomain( null, calendarEventDomain );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDTO, calendarEventDTO2 );

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

        List< CalendarEvent > calendarEventDTOList;
        List< com.mana.innovative.domain.common.CalendarEvent > calendarEventDomainList = new ArrayList<>( );
        calendarEventDomainList.add( calendarEventDomain );
        if ( calendarEventDomain.getCalendarEventId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestCalendarEventDomainZEROIDObject( calendarEventDomain );
        }
        calendarEventDTOList = CalendarEventDomainDTOConverter.getConvertedListDTOFromDomain( calendarEventDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDTOList );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDTO, calendarEventDTOList.get( TestConstants.ZERO ) );

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

        calendarEventDomain2 = CalendarEventDomainDTOConverter.getConvertedDomainFromDTO( null, calendarEventDTO );
        TestDummyDomainObjectGenerator.setTestCalendarEventDomainZEROIDObject( calendarEventDomain );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDomain, calendarEventDomain2 );

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

        List< CalendarEvent > calendarEventDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.common.CalendarEvent > calendarEventDomainList;
        calendarEventDTOList.add( calendarEventDTO );
        TestDummyDomainObjectGenerator.setTestCalendarEventDomainZEROIDObject( calendarEventDomain );

        calendarEventDomainList = CalendarEventDomainDTOConverter.getConvertedListDomainFromDTO( calendarEventDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDomainList );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDomain, calendarEventDomainList.get( TestConstants.ZERO ) );

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
        CalendarEvent calendarEvent = new CalendarEvent( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            calendarEventDomain2 = CalendarEventDomainDTOConverter.getConvertedDomainFromDTO( null, calendarEvent );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            calendarEventDomain2 = CalendarEventDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
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
            calendarEventDTO2 = CalendarEventDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}