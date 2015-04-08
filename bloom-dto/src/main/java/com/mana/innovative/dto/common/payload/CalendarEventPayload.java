package com.mana.innovative.dto.common.payload;

import com.mana.innovative.dto.common.CalendarEvent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:24 PM
 * @since: jdk 1.7
 */
public class CalendarEventPayload {

    private List< CalendarEvent > calendarEvents;

    @XmlElementWrapper( name = "calendar_events" )
    @XmlElement( name = "calendar_event" )
    public List< CalendarEvent > getCalendarEvents( ) {
        return calendarEvents;
    }

    public void setCalendarEvents( final List< CalendarEvent > calendarEvents ) {
        this.calendarEvents = calendarEvents;
    }
}
