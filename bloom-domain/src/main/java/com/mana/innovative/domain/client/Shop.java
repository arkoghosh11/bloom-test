package com.mana.innovative.domain.client;

import com.mana.innovative.domain.common.Address;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Shop.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "shops" )
public class Shop {

    /**
     * The Shop id.
     */
    @Id
    @Column( name = "shop_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long shopId;
    /**
     * The Shop own id.
     */
    @Column( name = "shop_main_id", unique = true, nullable = false )
    private Long shopOwnId;
    /**
     * The Shop name.
     */
    @Column( name = "shop_name" )
    private String shopName;
    /**
     * The Shop web link.
     */
    @Column( name = "shop_web_link" )
    private String shopWebLink;

    /**
     * The Address.
     */
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "address_id", nullable = false )
    private Address address;
    /**
     * The Working hours.
     */
    @OneToMany( orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "shopWorkingHour" )
    private List< WorkingHour > workingHours;
    /**
     * The Items.
     */
    @OneToMany( orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "shopItem" )
    private List< Item > items;

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
     * Gets shop id.
     *
     * @return the shop id
     */
    public long getShopId( ) {
        return shopId;
    }

    /**
     * Sets shop id.
     *
     * @param shop_id the shop _ id
     */
    public void setShopId( long shop_id ) {
        this.shopId = shop_id;
    }

    /**
     * Gets shop own id.
     *
     * @return the shop own id
     */
    public Long getShopOwnId( ) {
        return shopOwnId;
    }

    /**
     * Sets shop own id.
     *
     * @param shopOwnId the shopOwnId
     */
    public void setShopOwnId( Long shopOwnId ) {
        this.shopOwnId = shopOwnId;
    }

    /**
     * Gets shop name.
     *
     * @return the shop name
     */
    public String getShopName( ) {
        return shopName;
    }

    /**
     * Sets shop name.
     *
     * @param shop_name the shop _ name
     */
    public void setShopName( String shop_name ) {
        this.shopName = shop_name;
    }

    /**
     * Gets shop web link.
     *
     * @return the shop web link
     */
    public String getShopWebLink( ) {
        return shopWebLink;
    }

    /**
     * Sets shop web link.
     *
     * @param shopWebLink the shop web link
     */
    public void setShopWebLink( String shopWebLink ) {
        this.shopWebLink = shopWebLink;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
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
     * Gets working hours.
     *
     * @return the working hours
     */
    public List< WorkingHour > getWorkingHours( ) {
        return workingHours;
    }

    /**
     * Sets working hours.
     *
     * @param workingHours the working hours
     */
    public void setWorkingHours( List< WorkingHour > workingHours ) {
        this.workingHours = workingHours;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List< Item > getItems( ) {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems( List< Item > items ) {
        this.items = items;
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
        if ( !( o instanceof Shop ) ) return false;
        Shop shop = ( Shop ) o;
        return Objects.equals( getShopId( ), shop.getShopId( ) ) &&
                Objects.equals( getShopOwnId( ), shop.getShopOwnId( ) ) &&
                Objects.equals( getShopName( ), shop.getShopName( ) ) &&
                Objects.equals( getShopWebLink( ), shop.getShopWebLink( ) ) &&
                Objects.equals( getAddress( ), shop.getAddress( ) ) &&
                Objects.equals( getWorkingHours( ), shop.getWorkingHours( ) ) &&
                Objects.equals( getItems( ), shop.getItems( ) );
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
        return "Shop{" +
                "shop_id=" + shopId +
                ", shop_own_id=" + shopOwnId +
                ", shop_name='" + shopName + '\'' +
                ", shopWebLink='" + shopWebLink + '\'' +
                ", address=" + address +
                ", workingHours=" + workingHours +
                ", items=" + items +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
