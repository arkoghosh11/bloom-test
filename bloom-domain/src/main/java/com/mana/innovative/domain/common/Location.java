package com.mana.innovative.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Objects;

/**
 * The type Location.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "locations" )
public class Location {

    /**
     * The Location id.
     */
    @Id
    @Column
    @GeneratedValue
    private long locationId;

    /**
     * The Latitude.
     */
    @Column( name = "latitude" )
    private double latitude;
    /**
     * The Longitude.
     */
    @Column( name = "longitude" )
    private double longitude;

    /**
     * The Address.
     */
//    @OneToOne(cascade = CascadeType.ALL, mappedBy =
    @Transient
    private Address address;

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
     * Gets location id.
     *
     * @return the location id
     */
    public long getLocationId( ) {
        return locationId;
    }

    /**
     * Sets location id.
     *
     * @param locationId the location id
     */
    public void setLocationId( long locationId ) {
        this.locationId = locationId;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude( ) {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude( double latitude ) {
        this.latitude = latitude;
    }


    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude( ) {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude( double longitude ) {
        this.longitude = longitude;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
//   Note the following fields do not need to be in the xml mappings
    public Address getAddress( ) {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress( Address address ) {
        this.address = address;
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
    public void setCreatedDate( Date createdDate ) {
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
    public void setUpdatedDate( Date updatedDate ) {
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
        if ( !( o instanceof Location ) ) return false;
        Location location = ( Location ) o;
        return Objects.equals( getLocationId( ), location.getLocationId( ) ) &&
                Objects.equals( getLatitude( ), location.getLatitude( ) ) &&
                Objects.equals( getLongitude( ), location.getLongitude( ) ) &&
                Objects.equals( getAddress( ), location.getAddress( ) );
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
        return "Location{" +
                "locationId=" + locationId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address=" + address +
                '}';
    }
}
