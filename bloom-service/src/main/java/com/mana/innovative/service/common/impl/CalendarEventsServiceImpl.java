package com.mana.innovative.service.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.common.CalendarEventDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.payload.CalendarEventsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventsService;
import com.mana.innovative.service.common.builder.CalendarEventResponseBuilder;
import com.mana.innovative.service.common.container.CalendarEventResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:40 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Service
public class CalendarEventsServiceImpl implements CalendarEventsService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CalendarEventsServiceImpl.class );


    /**
     * The Calendar event dAO.
     */
    @Resource
    private CalendarEventDAO calendarEventDAO;

//    public Response getAllEvents( RequestParams requestParams ) {
//
//        final List< CalendarEvent > calendarEvents = new ArrayList< CalendarEvent >( );
//        CalendarEvent calendarEvent = new CalendarEvent( );
//        calendarEvent.setCalendarEventId( 1L );
//        calendarEvent.setEventDescription( "Event Description" );
//        calendarEvent.setEventEndDate( new Date( ) );
//        calendarEvent.setEventStartDate( new Date( ) );
//        calendarEvent.setEventName( "Event Name" );
//        calendarEvent.setName( "Name" );
//        calendarEvent.setOptional( "Optional" );
//        calendarEvents.add( calendarEvent );
//    }

    /**
     * Gets calendar events.
     *
     * @param requestParams the request params
     * @return the calendar events
     */
    @Override
    @Cacheable( value = ServiceConstants.CALENDAR_EVENTS_CACHE, key = ServiceConstants.KEY_NAME )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getCalendarEvents( RequestParams requestParams ) {

        logger.debug( "Initiating #getCalendarEvents , calendarEventDAO injected successfully" );

        String location = this.getClass( ).getCanonicalName( )
                + DAOConstants.HASH + "getCalendarEvents()";
        CalendarEventResponseContainer< CalendarEventsPayload > calendarEventResponseContainer;
        Response response;

        try {
            DAOResponse< com.mana.innovative.domain.common.CalendarEvent > calendarEventDAOResponse =
                    calendarEventDAO.getCalendarEvents( requestParams );
            calendarEventResponseContainer =
                    CalendarEventResponseBuilder.build( calendarEventDAOResponse, requestParams.isError( ) );
            response = Response.ok( calendarEventResponseContainer ).build( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            calendarEventResponseContainer = CalendarEventResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( calendarEventResponseContainer ).build( );
        }
        logger.debug( "Response for getCalendarEvents sent Successfully" );
        return response;
    }


    /**
     * Delete events.
     *
     * @param calendarEventIds the calendar event ids
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response deleteEvents( List< Long > calendarEventIds, RequestParams requestParams ) {

        calendarEventDAO.deleteCalendarEvents( calendarEventIds, requestParams );
        return Response.ok( ).build( );
    }
}
