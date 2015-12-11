package com.mana.innovative.domain.common;

import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.domain.consumer.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Address.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "address" )
public class Address {

    /**
     * The Address id.
     */
    @Id
    @Column( name = "address_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long addressId;

    /**
     * The Address 1.
     */
    @Column( name = "address1" )
    private String address1;

    /**
     * The Address 2.
     */
    @Column( name = "address2" )
    private String address2;

    /**
     * The City.
     */
    @Column( name = "city" )
    private String city;

    /**
     * The State.
     */
    @Column( name = "state" )
    private String state;

    /**
     * The District.
     */
    @Column( name = "district" )
    private String district;

    /**
     * The Zip code.
     */
    @Column( name = "zipcode" )
    private Integer zipCode;

//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "location_id", nullable = true, unique = true)
//    @Transient
//    private Location location;

    /**
     * The Shop address.
     */
    @OneToOne( orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy =
            "address" )
    private Shop shopAddress;

    /**
     * The Customer address.
     */
    @ManyToMany( cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST }, mappedBy =
            "shippingAddress" )
    private List< Customer > customerAddress;

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
     * Gets address id.
     *
     * @return the address id
     */
    public long getAddressId( ) {
        return addressId;
    }

    /**
     * Sets address id.
     *
     * @param addressId the address id
     */
    public void setAddressId( final long addressId ) {
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
    public void setZipCode( Integer zipCode ) {
        this.zipCode = zipCode;
    }

//    /**
//     * Gets location.
//     *
//     * @return the location
//     */
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


    /**
     * Gets shop address.
     *
     * @return the shop address
     */
    public Shop getShopAddress( ) {
        return shopAddress;
    }

    /**
     * Sets shop address.
     *
     * @param shopAddress the shop address
     */
    public void setShopAddress( final Shop shopAddress ) {
        this.shopAddress = shopAddress;
    }

    /**
     * Gets customer address.
     *
     * @return the customer address
     */
    public List< Customer > getCustomerAddress( ) {
        return customerAddress;
    }

    /**
     * Sets customer address.
     *
     * @param customerAddress the customer address
     */
    public void setCustomerAddress( final List< Customer > customerAddress ) {
        this.customerAddress = customerAddress;
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
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Address ) ) return false;
        Address address = ( Address ) o;
        return Objects.equals( getAddressId( ), address.getAddressId( ) ) &&
                Objects.equals( getAddress1( ), address.getAddress1( ) ) &&
                Objects.equals( getAddress2( ), address.getAddress2( ) ) &&
                Objects.equals( getCity( ), address.getCity( ) ) &&
                Objects.equals( getState( ), address.getState( ) ) &&
                Objects.equals( getDistrict( ), address.getDistrict( ) ) &&
                Objects.equals( getZipCode( ), address.getZipCode( ) );
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
        return "Address {" +
                " addressId=" + addressId +
                ",address1= " + address1 +
                ",address2= " + address2 +
                ",city= " + city +
                ",state= " + state +
                ",district= " + district +
                ",zipCode=" + zipCode +
//                ", shopAddress=" + shopAddress +
//                ", customerAddress=" + customerAddress +
                ",createdDate=" + createdDate +
                ",updatedDate=" + updatedDate +
                '}';
    }
}
