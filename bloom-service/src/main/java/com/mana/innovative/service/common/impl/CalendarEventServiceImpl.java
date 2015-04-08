package com.mana.innovative.service.common.impl;

import com.mana.innovative.dao.common.CalendarEventDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.common.payload.CalendarEventPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.CalendarEventService;
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

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/1/13 Time: 5:49 PM
 * @since: jdk 1.7
 */
@Service
public class CalendarEventServiceImpl implements CalendarEventService {

    private static final Logger logger = LoggerFactory.getLogger( CalendarEventServiceImpl.class );

    @Resource
    private CalendarEventDAO calendarEventDAO;

    /**
     * Gets calendar events.
     *
     * @param requestParams the request params
     *
     * @return the calendar events
     */
    @Override
    public Response getCalendarEvents( RequestParams requestParams ) {

        String startDate = requestParams.getStartDate( );
        String endDate = requestParams.getEndDate( );
        if ( startDate != null && endDate != null ) {
            Date start = new Date( ), end = new Date( );
            // Note need to implement todo DAOConstants.DEFAULT_DATE_LIMIT_FORMAT
            DAOResponse< com.mana.innovative.domain.common.CalendarEvent > calendarEventDAOResponse =
                    calendarEventDAO.getCalendarEventsByDateLimits( start, end, requestParams );
            CalendarEventResponseContainer< CalendarEventPayload > calendarEventResponseContainer =
                    CalendarEventResponseBuilder.build( new ArrayList< CalendarEvent >( ) );
            return Response.ok( calendarEventResponseContainer ).build( );
        } else
            return Response.status( Status.NOT_ACCEPTABLE ).build( );
    }

    /**
     * @param calendarEventId {Integer}
     *
     * @return Response
     */
    @Override
    public Response getCalendarEvent( Long calendarEventId, RequestParams requestParams ) {

        DAOResponse< com.mana.innovative.domain.common.CalendarEvent > calendarEventDAOResponse =
                calendarEventDAO.getCalendarEventByEventId( calendarEventId, requestParams );
        CalendarEventResponseContainer< CalendarEventPayload > calendarEventResponseContainer =
                CalendarEventResponseBuilder.build( new ArrayList< CalendarEvent >( ) );
        return Response.ok( calendarEventResponseContainer ).build( );
    }

    @Override
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
