package com.mana.innovative.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

/**
 * Created by Bloom on 2/27/2015 : 2:18 PM
 * todo This class is for ...
 */
@XmlRootElement(name = "location")
public class Location {

    private long locationId;

    private double latitude;
    private double longitude;


    @XmlElement(name = "location_id")
    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    @XmlElement(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @XmlElement(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

//   Note the following fields do not need to be in the xml mappings
}
