package com.mana.innovative.dto;/**
 * Created by Rono on 2/27/2015.
 * This is a class for .. todo 
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The type Shop.
 */
@XmlRootElement(name = "shop",namespace = "http://localhost:8080/bloom-test/rest/shops")
public class Shop {

    @XmlElement(name = "shop_id", nillable = false)
    private long shopId;
    @XmlElement(name = "shop_own_id", nillable = false)
    private long shopOwnId;
    @XmlElement(name = "shop_name", nillable = false)
    private String shopName;
    @XmlElement(name = "shop_web_link")
    private String shopWebLink;

    @XmlElement(name = "address", nillable = false)
    private Address address;
    @XmlElementWrapper(name = "working_hours")
    @XmlElement(name = "working_hour", nillable = true)
    private List<WorkingHour> workingHours;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item", nillable = true)
    private List<Item> items;

    /**
     * Gets shop id.
     *
     * @return the shop id
     */
    public long getShopId() {
        return shopId;
    }

    /**
     * Sets shop id.
     *
     * @param shopId the shop id
     */
    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    /**
     * Gets shop own id.
     *
     * @return the shop own id
     */
    public long getShopOwnId() {
        return shopOwnId;
    }

    /**
     * Sets shop own id.
     *
     * @param shopOwnId the shop own id
     */
    public void setShopOwnId(long shopOwnId) {
        this.shopOwnId = shopOwnId;
    }

    /**
     * Gets shop name.
     *
     * @return the shop name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Sets shop name.
     *
     * @param shopName the shop name
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Gets shop web link.
     *
     * @return the shop web link
     */
    public String getShopWebLink() {
        return shopWebLink;
    }

    /**
     * Sets shop web link.
     *
     * @param shopWebLink the shop web link
     */
    public void setShopWebLink(String shopWebLink) {
        this.shopWebLink = shopWebLink;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets working hours.
     *
     * @return the working hours
     */
    public List<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    /**
     * Sets working hours.
     *
     * @param workingHours the working hours
     */
    public void setWorkingHours(List<WorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;

        Shop shop = (Shop) o;

        if (shopId != shop.shopId) return false;
        if (shopOwnId != shop.shopOwnId) return false;
        if (address != null ? !address.equals(shop.address) : shop.address != null) return false;
        if (items != null ? !items.equals(shop.items) : shop.items != null) return false;
        if (shopWebLink != null ? !shopWebLink.equals(shop.shopWebLink) : shop.shopWebLink != null) return false;
        if (shopName != null ? !shopName.equals(shop.shopName) : shop.shopName != null) return false;
        if (workingHours != null ? !workingHours.equals(shop.workingHours) : shop.workingHours != null) return false;

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