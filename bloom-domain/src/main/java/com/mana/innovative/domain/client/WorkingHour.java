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
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "working_hours" )
public class WorkingHour {

    @Id
    @Column( name = "working_hour_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long workingHourId;

    @Column( name = "day_of_week" )
    private String day;
    @Column( name = "open_time_of_day", columnDefinition = "TIME" )
    @Temporal( TemporalType.TIME )
    private Date startTime;
    @Column( name = "close_time_of_day", columnDefinition = "TIME" )
    @Temporal( TemporalType.TIME )
    private Date endTime;
    @Column( name = "is_closed" )
    private Boolean isOffline;
    @Column( name = "is_holiday" )
    private Boolean isHoliday;
    @Column( name = "is_weekend" )
    private Boolean isWeekend;

    @Column( name = "created_date", columnDefinition = "TIMESTAMP" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    @Column( name = "updated_date", columnDefinition = "TIMESTAMP" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "shop_id" )
    private Shop shopWorkingHour;

    public long getWorkingHourId( ) {
        return workingHourId;
    }

    public void setWorkingHourId( final long workingHourId ) {
        this.workingHourId = workingHourId;
    }

    public String getDay( ) {
        return day;
    }

    public void setDay( final String day ) {
        this.day = day;
    }

    public Date getStartTime( ) {
        return startTime;
    }

    public void setStartTime( final Date startTime ) {
        this.startTime = startTime;
    }

    public Date getEndTime( ) {
        return endTime;
    }

    public void setEndTime( final Date endTime ) {
        this.endTime = endTime;
    }

    public Boolean isOffline( ) {
        return isOffline;
    }

    public void setOffline( final Boolean isOffline ) {
        this.isOffline = isOffline;
    }

    public Boolean isHoliday( ) {
        return isHoliday;
    }

    public void setHoliday( final Boolean isHoliday ) {
        this.isHoliday = isHoliday;
    }

    public Boolean isWeekend( ) {
        return isWeekend;
    }

    public void setWeekend( final Boolean isWeekend ) {
        this.isWeekend = isWeekend;
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

    public Shop getShopWorkingHour( ) {
        return shopWorkingHour;
    }

    public void setShopWorkingHour( final Shop shopWorkingHour ) {
        this.shopWorkingHour = shopWorkingHour;
    }

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
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", shopWorkingHour=" + shopWorkingHour +
                '}';
    }
}
