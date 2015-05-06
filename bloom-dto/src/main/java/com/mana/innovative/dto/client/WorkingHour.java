package com.mana.innovative.dto.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The type Working hour.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "working_hour" )
public class WorkingHour {


    /**
     * The Working hour id.
     */
    private Long workingHourId;

    /**
     * The Day.
     */
    private String day;
    /**
     * The Start time.
     */
    private String startTime;
    /**
     * The End time.
     */
    private String endTime;

    /**
     * The Is offline.
     */
    private Boolean isOffline;
    /**
     * The Is holiday.
     */
    private Boolean isHoliday;
    /**
     * The Is weekend.
     */
    private Boolean isWeekend;

    /**
     * Gets working hour id.
     *
     * @return the working hour id
     */
    @XmlElement( name = "working_hour_id" )
    public Long getWorkingHourId( ) {
        return workingHourId;
    }

    /**
     * Sets working hour id.
     *
     * @param workingHourId the working hour id
     */
    public void setWorkingHourId( Long workingHourId ) {
        this.workingHourId = workingHourId;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    @XmlElement( name = "day", defaultValue = "Monday" )
    public String getDay( ) {
        return day;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay( String day ) {
        this.day = day;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    @XmlElement( name = "start_time" )
    public String getStartTime( ) {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime( String startTime ) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    @XmlElement( name = "end_time" )
    public String getEndTime( ) {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime( String endTime ) {
        this.endTime = endTime;
    }

    /**
     * Is offline.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_closed", defaultValue = "false" )
    public Boolean isOffline( ) {
        return isOffline;
    }

    /**
     * Sets offline.
     *
     * @param isOffline the is offline
     */
    public void setOffline( Boolean isOffline ) {
        this.isOffline = isOffline;
    }

    /**
     * Is holiday.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_holiday", defaultValue = "false" )
    public Boolean isHoliday( ) {
        return isHoliday;
    }

    /**
     * Sets holiday.
     *
     * @param isHoliday the is holiday
     */
    public void setHoliday( Boolean isHoliday ) {
        this.isHoliday = isHoliday;
    }

    /**
     * Is weekend.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_weekend", defaultValue = "false" )
    public Boolean isWeekend( ) {
        return isWeekend;
    }

    /**
     * Sets weekend.
     *
     * @param isWeekend the is weekend
     */
    public void setWeekend( Boolean isWeekend ) {
        this.isWeekend = isWeekend;
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
        if ( !( o instanceof WorkingHour ) ) return false;
        WorkingHour that = ( WorkingHour ) o;
        return Objects.equals( getWorkingHourId( ), that.getWorkingHourId( ) ) &&
                Objects.equals( getDay( ), that.getDay( ) ) &&
                Objects.equals( getStartTime( ), that.getStartTime( ) ) &&
                Objects.equals( getEndTime( ), that.getEndTime( ) ) &&
                Objects.equals( isOffline, that.isOffline ) &&
                Objects.equals( isHoliday, that.isHoliday ) &&
                Objects.equals( isWeekend, that.isWeekend );
    }

    /**
     * Returns a string representation of the object. In general, the {@code toString} method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString( ) {
        return "WorkingHour{" +
                "workingHourId=" + workingHourId +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isOffline=" + isOffline +
                ", isHoliday=" + isHoliday +
                ", isWeekend=" + isWeekend +
                '}';
    }
}
