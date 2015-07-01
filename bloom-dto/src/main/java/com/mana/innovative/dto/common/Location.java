package com.mana.innovative.dto.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The type Location.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "location" )
public class Location {

    /**
     * The Location id.
     */
    private long locationId;

    /**
     * The Latitude.
     */
    private double latitude;
    /**
     * The Longitude.
     */
    private double longitude;


    /**
     * Gets location id.
     *
     * @return the location id
     */
    @XmlElement( name = "location_id" )
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
    @XmlElement( name = "latitude" )
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
    @XmlElement( name = "longitude" )
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
                Objects.equals( getLongitude( ), location.getLongitude( ) );
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
                '}';
    }
}
