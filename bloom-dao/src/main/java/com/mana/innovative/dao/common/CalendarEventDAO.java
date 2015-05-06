package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * The interface Calendar event dAO.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface CalendarEventDAO {


    /**
     * Gets calendar events.
     *
     * @param requestParams the request params
     * @return the calendar events
     */
    DAOResponse< CalendarEvent > getCalendarEvents( RequestParams requestParams );

    /**
     * Gets calendar events by date limits.
     *
     * @param startDate the start date
     * @param endDate the end date
     * @param requestParams the request params
     * @return the calendar events by date limits
     */
    DAOResponse< CalendarEvent > getCalendarEventsByDateLimits( Date startDate, Date endDate, RequestParams requestParams );

    /**
     * Gets calendar event by event id.
     *
     * @param eventId the event id
     * @param requestParams the request params
     * @return the calendar event by event id
     */
    DAOResponse< CalendarEvent > getCalendarEventByEventId( long eventId, RequestParams requestParams );

    /**
     * Create calendar event.
     *
     * @param calendarEvent the calendar event
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CalendarEvent > createCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams );

    /**
     * Update calendar event.
     *
     * @param calendarEvent the calendar event
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CalendarEvent > updateCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams );


    /**
     * Delete calendar event by event id.
     *
     * @param calendarEventId the calendar event id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CalendarEvent > deleteCalendarEventByEventId( long calendarEventId, RequestParams requestParams );

    /**
     * Delete calendar events.
     *
     * @param calendarEventIds the calendar event ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CalendarEvent > deleteCalendarEvents( final List< Long > calendarEventIds, RequestParams requestParams );

    /**
     * Delete all calendar events.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CalendarEvent > deleteAllCalendarEvents( RequestParams requestParams );
}
