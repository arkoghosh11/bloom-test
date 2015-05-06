package com.mana.innovative.dto.common.payload;

import com.mana.innovative.dto.common.CalendarEvent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:24 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
public class CalendarEventsPayload {

    /**
     * The Calendar events.
     */
    private List< CalendarEvent > calendarEvents;
    /**
     * The Total count.
     */
    private int totalCount;

    /**
     * Gets calendar events.
     *
     * @return the calendar events
     */
    @XmlElementWrapper( name = "calendar_events" )
    @XmlElement( name = "calendar_event" )
    public List< CalendarEvent > getCalendarEvents( ) {
        return calendarEvents;
    }

    /**
     * Sets calendar events.
     *
     * @param calendarEvents the calendar events
     */
    public void setCalendarEvents( final List< CalendarEvent > calendarEvents ) {
        this.calendarEvents = calendarEvents;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount( ) {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount( final int totalCount ) {
        this.totalCount = totalCount;
    }
}
