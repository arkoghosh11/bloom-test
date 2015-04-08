package com.mana.innovative.rest.common;

import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.common.payload.CalendarEventPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventsService;
import com.mana.innovative.service.common.builder.CalendarEventResponseBuilder;
import com.mana.innovative.service.common.container.CalendarEventResponseContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Calendar events rest web service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{calendarEvents : (?i)calendarEvents}" ) //@Note remove the case sensitiveness
public class CalendarEventsRestWebService {

    /**
     * The Calendar events service.
     */
    @Resource
    private CalendarEventsService calendarEventsService;

    /**
     * Gets all events.
     *
     * @param isError the is error
     *
     * @return the all events
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getAllEvents( @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        final List< CalendarEvent > calendarEvents = new ArrayList<>( );
        CalendarEvent calendarEvent = new CalendarEvent( );
        calendarEvent.setCalendarEventId( 1 );
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
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        calendarEventsService.getAllEvents( requestParams );
        Response.status( Status.BAD_REQUEST );
        return Response.ok( calendarEventResponseContainer ).build( );
    }


    /**
     * Delete events.
     *
     * @param calendarEventIds the calendar event ids
     * @param isError          the is error
     *
     * @return the response
     */
    @DELETE
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteEvents( List< Long > calendarEventIds,
                                  @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        calendarEventsService.deleteEvents( calendarEventIds, requestParams );

        return Response.ok( ).build( );
    }
}
