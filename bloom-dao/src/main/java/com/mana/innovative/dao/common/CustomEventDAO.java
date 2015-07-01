package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Bloom/Rono on 4/14/2015. This class is CustomEventDAO
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface CustomEventDAO {

    /**
     * Gets event by id.
     *
     * @param customEventId the custom event id
     * @param requestParams the request params
     * @return the event by id
     */
    DAOResponse< CustomEvent > getEventById( long customEventId, RequestParams requestParams );

    /**
     * Gets events by date.
     *
     * @param eventDate the event date
     * @param requestParams the request params
     * @return the events by date
     */
    DAOResponse< CustomEvent > getEventsByDate( Date eventDate, RequestParams requestParams );

    /**
     * Gets events by event name.
     *
     * @param eventName the event name
     * @param requestParams the request params
     * @return the events by event name
     */
    DAOResponse< CustomEvent > getEventsByEventName( String eventName, RequestParams requestParams );

    /**
     * Gets all events.
     *
     * @param requestParams the request params
     * @return the all events
     */
    DAOResponse< CustomEvent > getAllEvents( RequestParams requestParams );

    /**
     * Update event.
     *
     * @param customEvent the custom event
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CustomEvent > updateEvent( CustomEvent customEvent, RequestParams requestParams );

    /**
     * Enable event scheduler for date.
     *
     * @param date the date
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CustomEvent > enableEventSchedulerForDate( Date date, RequestParams requestParams );

    /**
     * Disable event scheduler for date.
     *
     * @param date the date
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CustomEvent > disableEventSchedulerForDate( Date date, RequestParams requestParams );

    /**
     * Gets events by date range.
     *
     * @param eventStartTime the event start time
     * @param eventEndTime the event end time
     * @param requestParams the request params
     * @return the events by date range
     */
    DAOResponse< CustomEvent > getEventsByDateRange( Date eventStartTime, Date eventEndTime, RequestParams requestParams );
}
