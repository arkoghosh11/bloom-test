package com.mana.innovative.dto;

/**
 * @author Bloom
 * This Class Address.java is for
 * Created at Aug 28, 2012 4:07:51 PM
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Address.
 */
@XmlRootElement( name = "address" )
public class Address {

    private Long addressId;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String district;

    private Integer zipCode;

//    private Location location;


    /**
     * Gets address id.
     *
     * @return the address id
     */
    @XmlElement( name = "addressId", nillable = false )
    public Long getAddressId( ) {
        return addressId;
    }

    /**
     * Sets address id.
     *
     * @param addressId the address id
     */
    public void setAddressId( final Long addressId ) {
        this.addressId = addressId;
    }

    /**
     * Gets address 1.
     *
     * @return the address 1
     */
    public String getAddress1( ) {
        return address1;
    }

    /**
     * Sets address 1.
     *
     * @param address1 the address 1
     */
    @XmlElement( name = "address1" )
    public void setAddress1( String address1 ) {
        this.address1 = address1;
    }

    /**
     * Gets address 2.
     *
     * @return the address 2
     */
    public String getAddress2( ) {
        return address2;
    }

    /**
     * Sets address 2.
     *
     * @param address2 the address 2
     */
    @XmlElement( name = "address2" )
    public void setAddress2( String address2 ) {
        this.address2 = address2;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity( ) {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    @XmlElement( name = "city" )
    public void setCity( String city ) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState( ) {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    @XmlElement( name = "state" )
    public void setState( String state ) {
        this.state = state;
    }

    /**
     * Gets district.
     *
     * @return the district
     */
    public String getDistrict( ) {
        return district;
    }

    /**
     * Sets district.
     *
     * @param district the district
     */
    @XmlElement( name = "district" )
    public void setDistrict( String district ) {
        this.district = district;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public Integer getZipCode( ) {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    @XmlElement( name = "zipCode" )
    public void setZipCode( Integer zipCode ) {
        this.zipCode = zipCode;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
//    @XmlElement(name = "location")
//    @XmlTransient
//    public Location getLocation() {
//        return location;
//    }
//
//    /**
//     * Sets location.
//     *
//     * @param location the location
//     */
//    public void setLocation(Location location) {
//        this.location = location;
//    }
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Address ) ) return false;

        Address address = ( Address ) o;

        if ( Integer.compare( zipCode, address.zipCode ) != 0 ) return false;
        if ( !address1.equals( address.address1 ) ) return false;
        if ( !address2.equals( address.address2 ) ) return false;
        if ( Long.compare( addressId, address.addressId ) != 0 ) return false;
        if ( !city.equals( address.city ) ) return false;
        if ( !district.equals( address.district ) ) return false;
        if ( !state.equals( address.state ) ) return false;
//        if (location != null ? !location.equals(address.location) : address.location != null) return true;

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
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", zipCode=" + zipCode +
//                ", location=" + location +
                '}';
    }
}
