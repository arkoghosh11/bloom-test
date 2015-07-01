package com.mana.innovative.dto.client;

import com.mana.innovative.dto.common.Address;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * The type Shop.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "shop", namespace = "http://localhost:8080/bloom-test/rest/shops" )
public class Shop {


    /**
     * The Shop id.
     */
    private Long shopId;
    /**
     * The Shop own id.
     */
    private Long shopOwnId;
    /**
     * The Shop name.
     */
    private String shopName;
    /**
     * The Shop web link.
     */
    private String shopWebLink;

    /**
     * The Address.
     */
    private Address address;
    /**
     * The Working hours.
     */
    private List< WorkingHour > workingHours;
    /**
     * The Items.
     */
    private List< Item > items;

    /**
     * Gets shop id.
     *
     * @return the shop id
     */
    @XmlElement( name = "shop_id", nillable = false )
    public Long getShopId( ) {
        return shopId;
    }

    /**
     * Sets shop id.
     *
     * @param shopId the shop id
     */
    public void setShopId( Long shopId ) {
        this.shopId = shopId;
    }

    /**
     * Gets shop own id.
     *
     * @return the shop own id
     */
    @XmlElement( name = "shop_own_id", nillable = false )
    public Long getShopOwnId( ) {
        return shopOwnId;
    }

    /**
     * Sets shop own id.
     *
     * @param shopOwnId the shop own id
     */
    public void setShopOwnId( Long shopOwnId ) {
        this.shopOwnId = shopOwnId;
    }

    /**
     * Gets shop name.
     *
     * @return the shop name
     */
    @XmlElement( name = "shop_name", nillable = false )
    public String getShopName( ) {
        return shopName;
    }

    /**
     * Sets shop name.
     *
     * @param shopName the shop name
     */
    public void setShopName( String shopName ) {
        this.shopName = shopName;
    }

    /**
     * Gets shop web link.
     *
     * @return the shop web link
     */
    @XmlElement( name = "shop_web_link" )
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
    @XmlElement( name = "address", nillable = false )
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
    @XmlElementWrapper( name = "working_hours" )
    @XmlElement( name = "working_hour", nillable = true )
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
    @XmlElementWrapper( name = "items" )
    @XmlElement( name = "item", nillable = true )
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
     * Equals boolean.
     *
     * @param o the o
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
                "shopId=" + shopId +
                ", shopOwnId=" + shopOwnId +
                ", shopName='" + shopName + '\'' +
                ", shopWebLink='" + shopWebLink + '\'' +
                ", address=" + address +
                ", workingHours=" + workingHours +
                ", items=" + items +
                '}';
    }
}