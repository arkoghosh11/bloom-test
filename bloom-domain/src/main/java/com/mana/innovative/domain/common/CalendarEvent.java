package com.mana.innovative.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/1/13 Time: 5:38 PM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "Calendar_Events" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public class CalendarEvent {

    @Id
    @Column( name = "calendar_event_id", nullable = false )
    private int calendarEventId;

    @Column( name = "event_start_date", columnDefinition = "DATETIME" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date eventStartDate;

    @Column( name = "event_end_date", columnDefinition = "DATETIME" )
    @Temporal( TemporalType.TIMESTAMP )
    @NotNull
    private Date eventEndDate;

    @Column( name = "created_by" )
    @NotNull
    private String name;

    @Column( name = "event_name" )
    @NotNull
    private String eventName;

    @Column( name = "event_description" )
    private String eventDescription;

    @Column( name = "optional" )
    private String optional;

    public int getCalendarEventId( ) {
        return calendarEventId;
    }

    public void setCalendarEventId( final int calendarEventId ) {
        this.calendarEventId = calendarEventId;
    }

    public Date getEventStartDate( ) {
        return eventStartDate;
    }

    public void setEventStartDate( final Date eventStartDate ) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate( ) {
        return eventEndDate;
    }

    public void setEventEndDate( final Date eventEndDate ) {
        this.eventEndDate = eventEndDate;
    }

    public String getName( ) {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public String getEventName( ) {
        return eventName;
    }

    public void setEventName( final String eventName ) {
        this.eventName = eventName;
    }

    public String getEventDescription( ) {
        return eventDescription;
    }

    public void setEventDescription( final String eventDescription ) {
        this.eventDescription = eventDescription;
    }

    public String getOptional( ) {
        return optional;
    }

    public void setOptional( final String optional ) {
        this.optional = optional;
    }

    @Override
    public String toString( ) {
        return "calendarEventId: " + calendarEventId + " eventName: " + eventName + " eventStartDate: " +
                eventStartDate +
                " eventEndDate: " + eventEndDate + " created by: " + name + " optional: " + optional;
    }
}
