package com.mana.innovative.domain.client;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * The type Working hour.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "working_hours" )
public class WorkingHour {

    /**
     * The Working hour id.
     */
    @Id
    @Column( name = "working_hour_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long workingHourId;

    /**
     * The Day.
     */
    @Column( name = "day_of_week" )
    private String day;
    /**
     * The Start time.
     */
    @Column( name = "open_time_of_day", columnDefinition = "TIME" )
    @Temporal( TemporalType.TIME )
    private Date startTime;
    /**
     * The End time.
     */
    @Column( name = "close_time_of_day", columnDefinition = "TIME" )
    @Temporal( TemporalType.TIME )
    private Date endTime;
    /**
     * The Is offline.
     */
    @Column( name = "is_closed" )
    private Boolean isOffline;
    /**
     * The Is holiday.
     */
    @Column( name = "is_holiday" )
    private Boolean isHoliday;
    /**
     * The Is weekend.
     */
    @Column( name = "is_weekend" )
    private Boolean isWeekend;

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
     * The Shop working hour.
     */
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "shop_id" )
    private Shop shopWorkingHour;

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
    public void setWorkingHourId( final long workingHourId ) {
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
    public void setDay( final String day ) {
        this.day = day;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStartTime( ) {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime( final Date startTime ) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public Date getEndTime( ) {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime( final Date endTime ) {
        this.endTime = endTime;
    }

    /**
     * Is offline.
     *
     * @return the boolean
     */
    public Boolean isOffline( ) {
        return isOffline;
    }

    /**
     * Sets offline.
     *
     * @param isOffline the is offline
     */
    public void setOffline( final Boolean isOffline ) {
        this.isOffline = isOffline;
    }

    /**
     * Is holiday.
     *
     * @return the boolean
     */
    public Boolean isHoliday( ) {
        return isHoliday;
    }

    /**
     * Sets holiday.
     *
     * @param isHoliday the is holiday
     */
    public void setHoliday( final Boolean isHoliday ) {
        this.isHoliday = isHoliday;
    }

    /**
     * Is weekend.
     *
     * @return the boolean
     */
    public Boolean isWeekend( ) {
        return isWeekend;
    }

    /**
     * Sets weekend.
     *
     * @param isWeekend the is weekend
     */
    public void setWeekend( final Boolean isWeekend ) {
        this.isWeekend = isWeekend;
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
     * Gets shop working hour.
     *
     * @return the shop working hour
     */
    public Shop getShopWorkingHour( ) {
        return shopWorkingHour;
    }

    /**
     * Sets shop working hour.
     *
     * @param shopWorkingHour the shop working hour
     */
    public void setShopWorkingHour( final Shop shopWorkingHour ) {
        this.shopWorkingHour = shopWorkingHour;
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
        if ( !( o instanceof WorkingHour ) ) return false;
        WorkingHour that = ( WorkingHour ) o;
        return Objects.equals( getWorkingHourId( ), that.getWorkingHourId( ) ) &&
                Objects.equals( getDay( ), that.getDay( ) ) &&
                Objects.equals( getStartTime( ), that.getStartTime( ) ) &&
                Objects.equals( getEndTime( ), that.getEndTime( ) ) &&
                Objects.equals( isOffline, that.isOffline ) &&
                Objects.equals( isHoliday, that.isHoliday );
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
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", shopWorkingHour=" + shopWorkingHour +
                '}';
    }
}
