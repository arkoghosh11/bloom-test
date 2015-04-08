package com.mana.innovative.service.common.builder;


import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.common.payload.CalendarEventPayload;
import com.mana.innovative.service.common.container.CalendarEventResponseContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:23 PM
 * @since: jdk 1.7
 */
public class CalendarEventResponseBuilder {

    /**
     * @param calendarEvents {@link List<CalendarEvent>} Is an arrayList passed in as super class {List} for generating
     *                       calendar event response
     *
     * @return {@link CalendarEventResponseContainer} Returns a response object for calendar Events
     */
    public static CalendarEventResponseContainer< CalendarEventPayload > build( List< CalendarEvent > calendarEvents ) {

        CalendarEventResponseContainer< CalendarEventPayload > calendarEventResponseContainer = new
                CalendarEventResponseContainer< CalendarEventPayload >( );

        calendarEventResponseContainer.setCount( calendarEvents.size( ) );

        CalendarEventPayload calendarEventPayload = new CalendarEventPayload( );
        calendarEventPayload.setCalendarEvents( calendarEvents );
        calendarEventResponseContainer.setPayload( calendarEventPayload );
        return calendarEventResponseContainer;
    }

    /**
     * @param calendarEvent {@link CalendarEvent} Is an arrayList passed in as super class {List} for generating
     *                      calendar event response
     *
     * @return {@link CalendarEventResponseContainer} Returns a response object for calendar Event
     */
    public static CalendarEventResponseContainer< CalendarEventPayload > build( CalendarEvent calendarEvent ) {

        CalendarEventResponseContainer< CalendarEventPayload > calendarEventResponseContainer = new
                CalendarEventResponseContainer< CalendarEventPayload >( );

        List< CalendarEvent > calendarEvents = new ArrayList< CalendarEvent >( );
        calendarEvents.add( calendarEvent );
        calendarEventResponseContainer.setCount( calendarEvents.size( ) );

        CalendarEventPayload calendarEventPayload = new CalendarEventPayload( );
        calendarEventPayload.setCalendarEvents( calendarEvents );
        calendarEventResponseContainer.setPayload( calendarEventPayload );

        return calendarEventResponseContainer;
    }
}
