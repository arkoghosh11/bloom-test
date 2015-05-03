package com.mana.innovative.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/1/13 Time: 5:38 PM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "Calendar_Events" )
public class CalendarEvent {

    @Id
    @Column( name = "calendar_event_id", nullable = false )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long calendarEventId;

    @Column( name = "event_start_date", columnDefinition = "TIMESTAMP" )
    @Temporal( TemporalType.TIMESTAMP )
    @NotNull
    private Date eventStartDate;

    @Column( name = "event_end_date", columnDefinition = "TIMESTAMP" )
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

    @Column( name = "created_date", columnDefinition = "TIMESTAMP" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    @Column( name = "updated_date", columnDefinition = "TIMESTAMP" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    public long getCalendarEventId( ) {
        return calendarEventId;
    }

    public void setCalendarEventId( final long calendarEventId ) {
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

    public Date getCreatedDate( ) {
        return createdDate;
    }

    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
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
        return "CalendarEvent {" +
                " calendarEventId=" + calendarEventId +
                ", eventStartDate=" + eventStartDate +
                ", eventEndDate=" + eventEndDate +
                ", name= " + name +
                ", eventName= " + eventName +
                ", eventDescription= " + eventDescription +
                ", optional= " + optional +
                ", createdDate= " + createdDate +
                ", updatedDate= " + updatedDate +
                '}';
    }
}
