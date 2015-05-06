package com.mana.innovative.service.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.common.CalendarEventDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.common.payload.CalendarEventsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventService;
import com.mana.innovative.service.common.builder.CalendarEventResponseBuilder;
import com.mana.innovative.service.common.container.CalendarEventResponseContainer;
import com.mana.innovative.utilities.date.DateCommons;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/1/13 Time: 5:49 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Service
public class CalendarEventServiceImpl implements CalendarEventService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CalendarEventServiceImpl.class );

    /**
     * The Calendar event dAO.
     */
    @Resource
    private CalendarEventDAO calendarEventDAO;

    /**
     * Gets calendar event.
     *
     * @param calendarEventId {
     *Integer
     *}
     * @param requestParams the request params
     * @return Response calendar event
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getCalendarEvent( Long calendarEventId, RequestParams requestParams ) {


        logger.debug( "Initiating #getCalendarEvent , calendarEventDAO injected successfully" );

        String location = this.getClass( ).getCanonicalName( )
                + DAOConstants.HASH + "getCalendarEvent()";
        String startDate = requestParams.getStartDate( ),
                endDate = requestParams.getEndDate( );
        CalendarEventResponseContainer< CalendarEventsPayload > calendarEventResponseContainer;
        Response response;
        if ( startDate != null && endDate != null ) {

            Date start = DateCommons.getDateFromDateString( startDate ),
                    end = DateCommons.getDateFromDateString( endDate );

            try {
                DAOResponse< com.mana.innovative.domain.common.CalendarEvent > calendarEventDAOResponse =
                        calendarEventDAO.getCalendarEventByEventId( calendarEventId, requestParams );
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
        } else
            response = ResponseUtility.notAcceptable( null );
        logger.debug( "Response for getCalendarEvent sent Successfully" );
        return response;
    }

    /**
     * Gets calendar event by date limits.
     *
     * @param requestParams the request params
     * @return the calendar event by date limits
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getCalendarEventByDateLimits( final RequestParams requestParams ) {

        logger.debug( "Initiating #getCalendarEventByDateLimits , calendarEventDAO injected successfully" );

        String location = this.getClass( ).getCanonicalName( )
                + DAOConstants.HASH + "getCalendarEventByDateLimits()";
        String startDate = requestParams.getStartDate( ),
                endDate = requestParams.getEndDate( );
        CalendarEventResponseContainer< CalendarEventsPayload > calendarEventResponseContainer;
        Response response;
        if ( startDate != null && endDate != null ) {

            Date start = DateCommons.getDateFromDateString( startDate ),
                    end = DateCommons.getDateFromDateString( endDate );

            try {
                DAOResponse< com.mana.innovative.domain.common.CalendarEvent > calendarEventDAOResponse =
                        calendarEventDAO.getCalendarEventsByDateLimits( start, end, requestParams );
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
        } else
            response = ResponseUtility.notAcceptable( null );
        logger.debug( "Response for #getCalendarEventByDateLimits sent Successfully" );
        return response;
    }

    /**
     * Create calendar event.
     *
     * @param calendarEvent the calendar event
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response createCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams ) {

        if ( calendarEvent != null ) {
            com.mana.innovative.domain.common.CalendarEvent calendarEventDomain
                    = new com.mana.innovative.domain.common.CalendarEvent( );
            calendarEventDAO.createCalendarEvent( calendarEventDomain, requestParams );
        } else {
            return Response.status( Status.BAD_REQUEST ).entity( "Entity Got is null" ).build( );
        }
        return Response.ok( calendarEvent ).build( );
    }
}
