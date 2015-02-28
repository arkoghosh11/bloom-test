package com.mana.innovative.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bloom on 2/27/2015 : 2:18 PM
 * todo This class is for ...
 */
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Column
    @GeneratedValue
    private long locationId;

    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;

    //    @OneToOne(cascade = CascadeType.ALL, mappedBy =
    @Transient
    private Address address;

    @Column(name = "created_date", columnDefinition = "DATETIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date", columnDefinition = "DATETIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedDate;

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

//   Note the following fields do not need to be in the xml mappings
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (Double.compare(location.latitude, latitude) != 0) return false;
        if (locationId != location.locationId) return false;
        if (Double.compare(location.longitude, longitude) != 0) return false;
        if (!address.equals(location.address)) return false;
        if (createdDate != null && location.getCreatedDate() != null ? createdDate.getTime() != location.getCreatedDate()
                .getTime() : createdDate == null)
            return true;
        if (updatedDate != null && location.getUpdatedDate() != null ? updatedDate.getTime() != location.getUpdatedDate()
                .getTime() : updatedDate == null)
            return true;
        return true;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     *
     * @return  {@link String}a string representation of the object.
     */
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address=" + address +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
