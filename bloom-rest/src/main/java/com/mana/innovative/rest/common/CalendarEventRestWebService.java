package com.mana.innovative.rest.common;

import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventService;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/1/13 Time: 5:49 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Component
@Path( "/{calendarEvent : (?i)calendarEvent}" )
public class CalendarEventRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CalendarEventRestWebService.class );

    /**
     * The Calendar event service.
     */
    @Resource
    private CalendarEventService calendarEventService;


    /**
     * Gets calendar events.
     *
     * @param startDate {String }
     * @param endDate   {String }
     * @param isError   the is error
     *
     * @return { Response }
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getCalendarEvents( @QueryParam( "START_DATE" ) String startDate,
                                       @QueryParam( "END_DATE" ) String endDate,
                                       @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        requestParams.setStartDate( startDate );
        requestParams.setEndDate( endDate );

        try {
            return calendarEventService.getCalendarEventByDateLimits( requestParams );
        } catch ( Exception exception ) {
            logger.error( "exception occurred", exception );
            return ResponseUtility.notAcceptable( "StartDate or EndDate has invalid data or format" );
        }
    }

    /**
     * Gets calendar event.
     *
     * @param calendarEventId { Integer }
     * @param isError         the is error
     *
     * @return Response calendar event
     */
    @GET
    @Path( "/{eventId}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getCalendarEvent( @PathParam( "eventId" ) Long calendarEventId,
                                      @QueryParam( "isError" ) @DefaultValue( "false" ) Boolean isError ) {

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return calendarEventService.getCalendarEvent( calendarEventId, requestParams );
    }

    /**
     * Create calendar event.
     *
     * @param calendarEvent the calendar event
     * @param isError       the is error
     *
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
    @Produces( { MediaType.APPLICATION_JSON } )
    public Response createCalendarEvent( CalendarEvent calendarEvent,
                                         @QueryParam( "isError" ) @DefaultValue( "false" ) Boolean isError ) {
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        if ( calendarEvent != null ) {
            calendarEventService.createCalendarEvent( calendarEvent, requestParams );
        } else {
            return Response.status( Status.BAD_REQUEST ).entity( "Entity Got is null" ).build( );
        }
        return Response.ok( calendarEvent ).build( );
    }
}
