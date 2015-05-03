package com.mana.innovative.service.common.impl;

import com.mana.innovative.dao.common.CalendarEventDAO;
import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.common.payload.CalendarEventPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventsService;
import com.mana.innovative.service.common.builder.CalendarEventResponseBuilder;
import com.mana.innovative.service.common.container.CalendarEventResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:40 PM
 * @since: jdk 1.7
 */
@Service
public class CalendarEventsServiceImpl implements CalendarEventsService {

    private static final Logger logger = LoggerFactory.getLogger( CalendarEventsServiceImpl.class );


    @Resource
    private CalendarEventDAO calendarEventDAO;

    public Response getAllEvents( RequestParams requestParams ) {

        final List< CalendarEvent > calendarEvents = new ArrayList< CalendarEvent >( );
        CalendarEvent calendarEvent = new CalendarEvent( );
        calendarEvent.setCalendarEventId( 1L );
        calendarEvent.setEventDescription( "Event Description" );
        calendarEvent.setEventEndDate( new Date( ) );
        calendarEvent.setEventStartDate( new Date( ) );
        calendarEvent.setEventName( "Event Name" );
        calendarEvent.setName( "Name" );
        calendarEvent.setOptional( "Optional" );
        calendarEvents.add( calendarEvent );
        CalendarEventResponseContainer< CalendarEventPayload > calendarEventResponseContainer =
                CalendarEventResponseBuilder.build( calendarEvents );
//                CalendarEventResponseBuilder.build( calendarEventDAO.getAllCalendarEvents() );
        Response.status( Status.BAD_REQUEST );
        calendarEventDAO.getCalendarEvents( requestParams );
        return Response.ok( calendarEventResponseContainer ).build( );
    }

    public Response deleteEvents( List< Long > calendarEventIds, RequestParams requestParams ) {

        calendarEventDAO.deleteCalendarEvents( calendarEventIds, requestParams );
        return Response.ok( ).build( );
    }
}
