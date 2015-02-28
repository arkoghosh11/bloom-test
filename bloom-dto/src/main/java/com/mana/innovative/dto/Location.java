package com.mana.innovative.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * The type Location.
 */
@XmlRootElement(name = "location")
public class Location {

    private long locationId;

    private double latitude;
    private double longitude;


    /**
     * Gets location id.
     *
     * @return the location id
     */
    @XmlElement(name = "location_id")
    public long getLocationId() {
        return locationId;
    }

    /**
     * Sets location id.
     *
     * @param locationId the location id
     */
    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    @XmlElement(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    @XmlElement(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (Double.compare(location.latitude, latitude) != 0) return false;
        if (locationId != location.locationId) return false;
        if (Double.compare(location.longitude, longitude) != 0) return false;

        return true;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     *
     * @return {@link String}a string representation of the object.
     */
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
