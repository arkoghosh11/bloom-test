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
 * @since: jdk 1.7
 */
@XmlRootElement( name = "calendar", namespace = "http://localhost:8080/rest/calendarEvents" )
public class CalendarEvent {

    @XmlTransient
    private static boolean dateResponse = false;
    private Long calendarEventId;
    private Date eventStartDate;
    private Date eventEndDate;
    private String name;
    private String eventName;
    private String eventDescription;
    private String optional;

    public static boolean isDateResponse( ) {
        return dateResponse;
    }

    public static void setDateResponse( final boolean dateResponse ) {
        CalendarEvent.dateResponse = dateResponse;
    }

    @XmlElement( name = "EVENT_ID", nillable = false )
    public Long getCalendarEventId( ) {
        return calendarEventId;
    }

    public void setCalendarEventId( final Long calendarEventId ) {
        this.calendarEventId = calendarEventId;
    }

    @XmlJavaTypeAdapter( DateFormatAdapter.class )
    @XmlElement( name = "START_DATE", type = String.class, nillable = false )
    public Date getEventStartDate( ) {
        return eventStartDate;
    }

    public void setEventStartDate( final Date eventStartDate ) {
        this.eventStartDate = eventStartDate;
    }

    @XmlJavaTypeAdapter( DateFormatAdapter.class )
    @XmlElement( name = "END_DATE", type = String.class, nillable = false )
    public Date getEventEndDate( ) {
        return eventEndDate;
    }

    public void setEventEndDate( final Date eventEndDate ) {
        this.eventEndDate = eventEndDate;
    }

    @XmlElement( name = "CREATED_BY", nillable = true, defaultValue = "Unknown" )
    public String getName( ) {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    @XmlElement( name = "EVENT_NAME", nillable = true, defaultValue = "No Name" )
    public String getEventName( ) {
        return eventName;
    }

    public void setEventName( final String eventName ) {
        this.eventName = eventName;
    }

    @XmlElement( name = "EVENT_DESCRIPTION", nillable = true, defaultValue = "No Description" )
    public String getEventDescription( ) {
        return eventDescription;
    }

    public void setEventDescription( final String eventDescription ) {
        this.eventDescription = eventDescription;
    }

    @XmlElement( name = "OPTIONAL", nillable = true, defaultValue = "No Optional" )
    public String getOptional( ) {
        return optional;
    }

    public void setOptional( final String optional ) {
        this.optional = optional;
    }

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

    @Override
    public String toString( ) {
        return "calendarEventId: " + calendarEventId + " eventName: " + eventName + " eventStartDate: " +
                eventStartDate +
                " eventEndDate: " + eventEndDate + " created by: " + name + " optional: " + optional;
    }
}
