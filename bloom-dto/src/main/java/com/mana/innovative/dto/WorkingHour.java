package com.mana.innovative.dto;

/**
 * Created by Rono on 2/27/2015.
 * This is a class for .. todo 
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Working hour.
 */
@XmlRootElement( name = "working_hour" )
public class WorkingHour {

    @XmlElement( name = "working_hour_id" )
    private long workingHourId;

    @XmlElement( name = "day", defaultValue = "Monday" )
    private String day;
    @XmlElement( name = "start_time" )
    private String startTime;
    @XmlElement( name = "end_time" )
    private String endTime;

    @XmlElement( name = "is_closed", defaultValue = "false" )
    private boolean isOffline;
    @XmlElement( name = "is_holiday", defaultValue = "false" )
    private boolean isHoliday;
    @XmlElement( name = "is_weekend", defaultValue = "false" )
    private boolean isWeekend;

    /**
     * Gets working hour id.
     *
     * @return the working hour id
     */
    public long getWorkingHourId( ) {
        return workingHourId;
    }

    /**
     * Sets working hour id.
     *
     * @param workingHourId the working hour id
     */
    public void setWorkingHourId( long workingHourId ) {
        this.workingHourId = workingHourId;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
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
    public boolean isOffline( ) {
        return isOffline;
    }

    /**
     * Sets offline.
     *
     * @param isOffline the is offline
     */
    public void setOffline( boolean isOffline ) {
        this.isOffline = isOffline;
    }

    /**
     * Is holiday.
     *
     * @return the boolean
     */
    public boolean isHoliday( ) {
        return isHoliday;
    }

    /**
     * Sets holiday.
     *
     * @param isHoliday the is holiday
     */
    public void setHoliday( boolean isHoliday ) {
        this.isHoliday = isHoliday;
    }

    /**
     * Is weekend.
     *
     * @return the boolean
     */
    public boolean isWeekend( ) {
        return isWeekend;
    }

    /**
     * Sets weekend.
     *
     * @param isWeekend the is weekend
     */
    public void setWeekend( boolean isWeekend ) {
        this.isWeekend = isWeekend;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;

        WorkingHour that = ( WorkingHour ) o;

        if ( isHoliday != that.isHoliday ) return false;
        if ( isOffline != that.isOffline ) return false;
        if ( isWeekend != that.isWeekend ) return false;
        if ( workingHourId != that.workingHourId ) return false;
        if ( !day.equals( that.day ) ) return false;
        if ( !endTime.equals( that.endTime ) ) return false;
        if ( !startTime.equals( that.startTime ) ) return false;

        return true;
    }

    @Override
    public int hashCode( ) {
        return 0;
    }

    /**
     * Returns a string representation of the object. In general, the {@code toString} method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read.
     *
     * @return {@link String}a string representation of the object.
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
