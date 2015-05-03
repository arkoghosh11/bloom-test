package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 11:51 PM. This class WhenCalendarEventThenTestCalEventDAOMethods is a test class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetCalendarEventThenTestCalEventDAOMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenGetCalendarEventThenTestCalEventDAOMethods.class );

    @Resource
    private CalendarEventDAO calendarEventDAO;
    private RequestParams requestParams;

    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        requestParams = new RequestParams( );
    }

    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCalendarEventsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCalendarEventsWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.getCalendarEvents( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );
        // calendarEvent list and its size with DAOResponse<T> class count
        List< CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvents );
        Assert.assertFalse( TestConstants.trueMessage, calendarEvents.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDAOResponse.getCount( ), calendarEvents.size( ) );

        for ( CalendarEvent calendarEvent : calendarEvents ) {
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
        }

        logger.debug( "Finishing test for GetCalendarEventsWithErrorDisabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCalendarEventWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCalendarEventWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.getCalendarEventByEventId( TestConstants.TEST_ID,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );
        // calendarEvent list and its size with DAOResponse<T> class count
        List< CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvents );
        Assert.assertFalse( TestConstants.trueMessage, calendarEvents.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDAOResponse.getCount( ), calendarEvents.size( ) );
        Assert.assertEquals( TestConstants.ONE, calendarEvents.size( ) );
        // test calendarEvent
        CalendarEvent calendarEvent = calendarEvents.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCalendarEventId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventDescription( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getName( ) );
        Assert.assertNotNull( TestConstants.falseMessage, calendarEvent.getOptional( ) );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetCalendarEventWithErrorDisabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCalendarEventsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCalendarEventsWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.getCalendarEvents( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );

        // calendarEvent list and its size with DAOResponse<T> class count
        List< CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvents );
        Assert.assertFalse( TestConstants.trueMessage, calendarEvents.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDAOResponse.getCount( ), calendarEvents.size( ) );

        for ( CalendarEvent calendarEvent : calendarEvents ) {
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
        }

        logger.debug( "Finishing test for GetCalendarEventsWithErrorEnabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCalendarEventWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCalendarEventWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.getCalendarEventByEventId( TestConstants.TEST_ID,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );

        // calendarEvent list and its size with DAOResponse<T> class count
        List< CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvents );
        Assert.assertFalse( TestConstants.trueMessage, calendarEvents.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDAOResponse.getCount( ), calendarEvents.size( ) );
        Assert.assertEquals( TestConstants.ONE, calendarEvents.size( ) );

        // test calendarEvent
        CalendarEvent calendarEvent = calendarEvents.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCalendarEventId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventDescription( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getName( ) );
        Assert.assertNotNull( TestConstants.falseMessage, calendarEvent.getOptional( ) );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetCalendarEventWithErrorEnabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCalendarEventsByDateLimitsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCalendarEventsByDateLimitsWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );

        DateFormat dateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );
        Date startDate = dateFormat.parse( TestConstants.TEST_START_DATE ),
                endDate = dateFormat.parse( TestConstants.TEST_END_DATE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.getCalendarEventsByDateLimits(
                startDate, endDate, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );

        // calendarEvent list and its size with DAOResponse<T> class count
        List< CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvents );
        Assert.assertFalse( TestConstants.trueMessage, calendarEvents.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDAOResponse.getCount( ), calendarEvents.size( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEvents.size( ) > TestConstants.ZERO );

        // test calendarEvent
        for ( CalendarEvent calendarEvent : calendarEvents ) {

            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCalendarEventId( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventDescription( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventName( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getName( ) );
            Assert.assertNotNull( TestConstants.falseMessage, calendarEvent.getOptional( ) );

            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );

            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCreatedDate( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getUpdatedDate( ) );
        }

        logger.debug( "Finishing test for GetCalendarEventsByDateLimitsWithErrorEnabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCalendarEventsByDateLimitsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCalendarEventsByDateLimitsWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );

        DateFormat dateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );
        Date startDate = dateFormat.parse( TestConstants.TEST_START_DATE ),
                endDate = dateFormat.parse( TestConstants.TEST_END_DATE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.getCalendarEventsByDateLimits(
                startDate, endDate, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );

        // test error container
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );

        // calendarEvent list and its size with DAOResponse<T> class count
        List< CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEvents );
        Assert.assertFalse( TestConstants.trueMessage, calendarEvents.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, calendarEventDAOResponse.getCount( ), calendarEvents.size( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEvents.size( ) > TestConstants.ZERO );

        // test calendarEvent
        for ( CalendarEvent calendarEvent : calendarEvents ) {

            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCalendarEventId( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventDescription( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventName( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getName( ) );
            Assert.assertNotNull( TestConstants.falseMessage, calendarEvent.getOptional( ) );

            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getEventStartDate( ) );

            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getCreatedDate( ) );
            Assert.assertNotNull( TestConstants.nullMessage, calendarEvent.getUpdatedDate( ) );
        }

        logger.debug( "Finishing test for GetCalendarEventsByDateLimitsWithErrorDisabled" );
    }

}