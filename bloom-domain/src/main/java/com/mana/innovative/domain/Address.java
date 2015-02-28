package com.mana.innovative.domain;

/**
 * @author Bloom
 * This Class Address.java is for
 * Created at Aug 28, 2012 4:07:51 PM
 */
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The type Address.
 */
@Entity
@Table( name = "address" )
public class Address {

    @Id
    @Column( name = "address_id", nullable = false )
    private long addressId;

    @Column( name = "address1" )
    private String address1;

    @Column( name = "address2" )
    private String address2;

    @Column( name = "city" )
    private String city;

    @Column( name = "state" )
    private String state;

    @Column( name = "district" )
    private String district;

    @Column( name = "zipcode" )
    private int zipCode;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "location_id", nullable = true, unique = true)
    private Location location;

    @OneToOne( cascade = { CascadeType.ALL}, mappedBy = "address")
    private Shop shopAddress;

    @Column(name = "created_date", columnDefinition = "DATETIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date", columnDefinition = "DATETIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedDate;


    /**
     * Gets address id.
     *
     * @return the address id
     */
    public long getAddressId () {
        return addressId;
    }

    /**
     * Sets address id.
     *
     * @param addressId the address id
     */
    public void setAddressId ( final long addressId ) {
        this.addressId = addressId;
    }

    /**
     * Gets address 1.
     *
     * @return the address 1
     */
    public String getAddress1 () {
        return address1;
    }

    /**
     * Sets address 1.
     *
     * @param address1 the address 1
     */
    public void setAddress1 ( String address1 ) {
        this.address1 = address1;
    }

    /**
     * Gets address 2.
     *
     * @return the address 2
     */
    public String getAddress2 () {
        return address2;
    }

    /**
     * Sets address 2.
     *
     * @param address2 the address 2
     */
    public void setAddress2 ( String address2 ) {
        this.address2 = address2;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity () {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity ( String city ) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState () {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState ( String state ) {
        this.state = state;
    }

    /**
     * Gets district.
     *
     * @return the district
     */
    public String getDistrict () {
        return district;
    }

    /**
     * Sets district.
     *
     * @param district the district
     */
    public void setDistrict ( String district ) {
        this.district = district;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public int getZipCode () {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode ( int zipCode ) {
        this.zipCode = zipCode;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Gets shop address.
     *
     * @return the shop address
     */
    public Shop getShopAddress() {
        return shopAddress;
    }

    /**
     * Sets shop address.
     *
     * @param shopAddress the shop address
     */
    public void setShopAddress(final Shop shopAddress) {
        this.shopAddress = shopAddress;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (addressId != address.addressId) return false;
        if (zipCode != address.zipCode) return false;
        if (!address1.equals(address.address1)) return false;
        if (!address2.equals(address.address2)) return false;
        if (!city.equals(address.city)) return false;
        if (createdDate != null ? !createdDate.equals(address.createdDate) : address.createdDate != null) return false;
        if (!district.equals(address.district)) return false;
        if (!location.equals(address.location)) return false;
        if (!shopAddress.equals( address.shopAddress)) return false;
        if (!state.equals(address.state)) return false;

        if (createdDate != null && address.getCreatedDate() != null ? createdDate.getTime() != address.getCreatedDate()
                .getTime() : createdDate == null)
            return true;
        if (updatedDate != null && address.getUpdatedDate() != null ? updatedDate.getTime() != address.getUpdatedDate()
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
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", zipCode=" + zipCode +
                ", location=" + location +
                ", shopAddress=" + shopAddress +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
