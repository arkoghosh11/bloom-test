package com.mana.innovative.rest.common;

import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventService;
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
 * @since: jdk 1.7
 */
@Component
@Path( "/{calendarEvent : (?i)calendarEvent}" )
public class CalendarEventRestWebService {


    @Resource
    private CalendarEventService calendarEventService;


    /**
     * @param startDate{String}
     * @param endDate{String}
     *
     * @return {Response}
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

        if ( startDate != null && endDate != null ) {
            return calendarEventService.getCalendarEvents( requestParams );
        } else
            return Response.status( Status.NOT_ACCEPTABLE ).build( );
    }

    /**
     * @param calendarEventId {Integer}
     *
     * @return Response
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
