package com.mana.innovative.service.common;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

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
public interface CalendarEventsService {

    /**
     * Gets calendar events.
     *
     * @param requestParams the request params
     *
     * @return the calendar events
     */
    Response getCalendarEvents( RequestParams requestParams );

    /**
     * Delete events.
     *
     * @param calendarEventIds the calendar event ids
     * @param requestParams    the request params
     *
     * @return the response
     */
    Response deleteEvents( List< Long > calendarEventIds, RequestParams requestParams );

}
