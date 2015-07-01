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
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
@Table( name = "Calendar_Events" )
public class CalendarEvent {

    /**
     * The Calendar event id.
     */
    @Id
    @Column( name = "calendar_event_id", nullable = false )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long calendarEventId;

    /**
     * The Event start date.
     */
    @Column( name = "event_start_date", columnDefinition = "TIMESTAMP" )
    @Temporal( TemporalType.TIMESTAMP )
    @NotNull
    private Date eventStartDate;

    /**
     * The Event end date.
     */
    @Column( name = "event_end_date", columnDefinition = "TIMESTAMP" )
    @Temporal( TemporalType.TIMESTAMP )
    @NotNull
    private Date eventEndDate;

    /**
     * The Name.
     */
    @Column( name = "created_by" )
    @NotNull
    private String name;

    /**
     * The Event name.
     */
    @Column( name = "event_name" )
    @NotNull
    private String eventName;

    /**
     * The Event description.
     */
    @Column( name = "event_description" )
    private String eventDescription;

    /**
     * The Optional.
     */
    @Column( name = "optional" )
    private String optional;

    /**
     * The Created date.
     */
    @Column( name = "created_date", columnDefinition = "TIMESTAMP" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date", columnDefinition = "TIMESTAMP" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    /**
     * Gets calendar event id.
     *
     * @return the calendar event id
     */
    public long getCalendarEventId( ) {
        return calendarEventId;
    }

    /**
     * Sets calendar event id.
     *
     * @param calendarEventId the calendar event id
     */
    public void setCalendarEventId( final long calendarEventId ) {
        this.calendarEventId = calendarEventId;
    }

    /**
     * Gets event start date.
     *
     * @return the event start date
     */
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
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate( ) {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
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
