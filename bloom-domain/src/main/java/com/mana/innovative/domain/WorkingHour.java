package com.mana.innovative.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * The type Working hour.
 */
@XmlRootElement
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

        WorkingHour workingHour = ( WorkingHour ) o;

        if ( workingHourId != workingHour.getWorkingHourId( ) ) return false;
        if ( day != null ? !day.equals( workingHour.day ) : workingHour.day != null ) return false;
        if ( startTime != null ? !startTime.equals( workingHour.getStartTime( ) ) : workingHour.getStartTime( ) != null
                ) return false;
        if ( endTime != null ? !endTime.equals( workingHour.getEndTime( ) ) : workingHour.getEndTime( ) != null )
            return false;
        if ( isHoliday != null ? !isHoliday.equals( workingHour.isHoliday( ) ) : workingHour.isHoliday( ) != null )
            return false;
        if ( isOffline != null ? !isOffline.equals( workingHour.isOffline( ) ) : workingHour.isOffline( ) != null )
            return false;
        if ( isWeekend != null ? !isWeekend.equals( workingHour.isWeekend( ) ) : workingHour.isWeekend( ) != null )
            return false;
        if ( createdDate != null ? !createdDate.equals( workingHour.getCreatedDate( ) ) :
                workingHour.getCreatedDate( ) != null ) return true;
        if ( updatedDate != null ? !updatedDate.equals( workingHour.getUpdatedDate( ) ) :
                workingHour.getUpdatedDate( ) != null ) return true;

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
