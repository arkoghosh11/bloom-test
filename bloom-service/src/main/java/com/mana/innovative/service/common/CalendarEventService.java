package com.mana.innovative.service.common;

import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * The interface Calendar event service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface CalendarEventService {

    /**
     * Gets calendar event.
     *
     * @param calendarEventId {
     *Integer
     *}
     * @param requestParams the request params
     * @return Response calendar event
     */
    Response getCalendarEvent( Long calendarEventId, RequestParams requestParams );

    /**
     * Gets calendar event by date limits.
     *
     * @param requestParams the request params
     * @return the calendar event by date limits
     */
    Response getCalendarEventByDateLimits( RequestParams requestParams );

    /**
     * Create calendar event.
     *
     * @param calendarEvent the calendar event
     * @param requestParams the request params
     *
     * @return the response
     */
    Response createCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams );
}
