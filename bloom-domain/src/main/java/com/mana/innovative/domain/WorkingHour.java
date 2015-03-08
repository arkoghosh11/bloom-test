package com.mana.innovative.domain;/**
 * Created by Rono on 2/27/2015.
 * This is a class for .. todo 
 */

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Bloom on 2/27/2015 : 2:47 PM todo This class is for ...
 */
@XmlRootElement
@Entity
@Table( name = "working_hours" )
public class WorkingHour {

    @Id
    @Column( name = "working_hour_id" )
    @GeneratedValue
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
    private boolean isOffline;
    @Column( name = "is_holiday" )
    private boolean isHoliday;
    @Column( name = "is_weekend" )
    private boolean isWeekend;

    @Column( name = "created_date", columnDefinition = "DATETIME" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    @Column( name = "updated_date", columnDefinition = "DATETIME" )
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

    public boolean isOffline( ) {
        return isOffline;
    }

    public void setOffline( final boolean isOffline ) {
        this.isOffline = isOffline;
    }

    public boolean isHoliday( ) {
        return isHoliday;
    }

    public void setHoliday( final boolean isHoliday ) {
        this.isHoliday = isHoliday;
    }

    public boolean isWeekend( ) {
        return isWeekend;
    }

    public void setWeekend( final boolean isWeekend ) {
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

        final WorkingHour workingHour = ( WorkingHour ) o;

        if ( workingHourId != workingHour.workingHourId ) return false;
        if ( isHoliday != workingHour.isHoliday ) return false;
        if ( isOffline != workingHour.isOffline ) return false;
        if ( isWeekend != workingHour.isWeekend ) return false;
        if ( day != null && !day.equals( workingHour.day ) ) return false;
        if ( endTime != null && !endTime.equals( workingHour.endTime ) ) return false;
        if ( startTime != null && !startTime.equals( workingHour.startTime ) ) return false;
        if ( shopWorkingHour != null && !shopWorkingHour.equals( workingHour.shopWorkingHour ) ) return false;
        if ( createdDate != null && workingHour.getCreatedDate( ) != null ? createdDate.getTime( ) != workingHour.getCreatedDate( )
                .getTime( ) : createdDate == null )
            return true;
        if ( updatedDate != null && workingHour.getUpdatedDate( ) != null ? updatedDate.getTime( ) != workingHour.getUpdatedDate( )
                .getTime( ) : updatedDate == null )
            return true;
        return true;
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
