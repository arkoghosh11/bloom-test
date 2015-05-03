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
     * Gets calendar events.
     *
     * @param requestParams the request params
     *
     * @return the calendar events
     */
    Response getCalendarEvents( RequestParams requestParams );

    /**
     * @param calendarEventId {Integer}
     *
     * @return Response
     */
    Response getCalendarEvent( Long calendarEventId, RequestParams requestParams );

    Response createCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams );
}
