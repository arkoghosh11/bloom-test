package com.mana.innovative.dto.common;

import com.mana.innovative.dto.adapter.DateFormatAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/1/13 Time: 5:38 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "calendar", namespace = "http://localhost:8080/rest/calendarEvents" )
public class CalendarEvent {

    /**
     * The constant dateResponse.
     */
    @XmlTransient
    private static boolean dateResponse = false;
    /**
     * The Calendar event id.
     */
    private Long calendarEventId;
    /**
     * The Event start date.
     */
    private Date eventStartDate;
    /**
     * The Event end date.
     */
    private Date eventEndDate;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Event name.
     */
    private String eventName;
    /**
     * The Event description.
     */
    private String eventDescription;
    /**
     * The Optional.
     */
    private String optional;

    /**
     * Is date response.
     *
     * @return the boolean
     */
    public static boolean isDateResponse( ) {
        return dateResponse;
    }

    /**
     * Sets date response.
     *
     * @param dateResponse the date response
     */
    public static void setDateResponse( final boolean dateResponse ) {
        CalendarEvent.dateResponse = dateResponse;
    }

    /**
     * Gets calendar event id.
     *
     * @return the calendar event id
     */
    @XmlElement( name = "EVENT_ID", nillable = false )
    public Long getCalendarEventId( ) {
        return calendarEventId;
    }

    /**
     * Sets calendar event id.
     *
     * @param calendarEventId the calendar event id
     */
    public void setCalendarEventId( final Long calendarEventId ) {
        this.calendarEventId = calendarEventId;
    }

    /**
     * Gets event start date.
     *
     * @return the event start date
     */
    @XmlJavaTypeAdapter( DateFormatAdapter.class )
    @XmlElement( name = "START_DATE", type = String.class, nillable = false )
    public Date getEventStartDate( ) {
        return eventStartDate;
    }

    /**
     * Sets event start date.
     *
     * @param eventStartDate the event start date
     */
    public void setEventStartDate( final Date eventStartDate ) {
        this.eventStartDate = eventStartDate;
    }

    /**
     * Gets event end date.
     *
     * @return the event end date
     */
    @XmlJavaTypeAdapter( DateFormatAdapter.class )
    @XmlElement( name = "END_DATE", type = String.class, nillable = false )
    public Date getEventEndDate( ) {
        return eventEndDate;
    }

    /**
     * Sets event end date.
     *
     * @param eventEndDate the event end date
     */
    public void setEventEndDate( final Date eventEndDate ) {
        this.eventEndDate = eventEndDate;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @XmlElement( name = "CREATED_BY", nillable = true, defaultValue = "Unknown" )
    public String getName( ) {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName( final String name ) {
        this.name = name;
    }

    /**
     * Gets event name.
     *
     * @return the event name
     */
    @XmlElement( name = "EVENT_NAME", nillable = true, defaultValue = "No Name" )
    public String getEventName( ) {
        return eventName;
    }

    /**
     * Sets event name.
     *
     * @param eventName the event name
     */
    public void setEventName( final String eventName ) {
        this.eventName = eventName;
    }

    /**
     * Gets event description.
     *
     * @return the event description
     */
    @XmlElement( name = "EVENT_DESCRIPTION", nillable = true, defaultValue = "No Description" )
    public String getEventDescription( ) {
        return eventDescription;
    }

    /**
     * Sets event description.
     *
     * @param eventDescription the event description
     */
    public void setEventDescription( final String eventDescription ) {
        this.eventDescription = eventDescription;
    }

    /**
     * Gets optional.
     *
     * @return the optional
     */
    @XmlElement( name = "OPTIONAL", nillable = true, defaultValue = "No Optional" )
    public String getOptional( ) {
        return optional;
    }

    /**
     * Sets optional.
     *
     * @param optional the optional
     */
    public void setOptional( final String optional ) {
        this.optional = optional;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof CalendarEvent ) ) return false;
        CalendarEvent that = ( CalendarEvent ) o;
        return Objects.equals( getCalendarEventId( ), that.getCalendarEventId( ) ) &&
                Objects.equals( getEventStartDate( ), that.getEventStartDate( ) ) &&
                Objects.equals( getEventEndDate( ), that.getEventEndDate( ) ) &&
                Objects.equals( getName( ), that.getName( ) ) &&
                Objects.equals( getEventName( ), that.getEventName( ) ) &&
                Objects.equals( getEventDescription( ), that.getEventDescription( ) ) &&
                Objects.equals( getOptional( ), that.getOptional( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "calendarEventId: " + calendarEventId + " eventName: " + eventName + " eventStartDate: " +
                eventStartDate +
                " eventEndDate: " + eventEndDate + " created by: " + name + " optional: " + optional;
    }
}
