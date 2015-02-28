package com.mana.innovative.dto;/**
 * Created by Rono on 2/27/2015.
 * This is a class for .. todo 
 */

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by Bloom on 2/27/2015 : 2:15 PM
 * todo This class is for ...
 */
@XmlRootElement(name = "shop",namespace = "http://localhost:8080/bloom-test/rest/shops")
public class Shop {

    private long shop_id;
    private long shop_own_id;
    private String shop_name;
    private String shopWebLink;

    private Address address;
    private List<WorkingHour> workingHours;
    private List<Item> items;

    public long getShop_id() {
        return shop_id;
    }

    public void setShop_id(long shop_id) {
        this.shop_id = shop_id;
    }

    public long getShop_own_id() {
        return shop_own_id;
    }

    public void setShop_own_id(long shop_own_id) {
        this.shop_own_id = shop_own_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShopWebLink() {
        return shopWebLink;
    }

    public void setShopWebLink(String shopWebLink) {
        this.shopWebLink = shopWebLink;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;

        Shop shop = (Shop) o;

        if (shop_id != shop.shop_id) return false;
        if (shop_own_id != shop.shop_own_id) return false;
        if (address != null ? !address.equals(shop.address) : shop.address != null) return false;
        if (items != null ? !items.equals(shop.items) : shop.items != null) return false;
        if (shopWebLink != null ? !shopWebLink.equals(shop.shopWebLink) : shop.shopWebLink != null) return false;
        if (shop_name != null ? !shop_name.equals(shop.shop_name) : shop.shop_name != null) return false;
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
                "shop_id=" + shop_id +
                ", shop_own_id=" + shop_own_id +
                ", shop_name='" + shop_name + '\'' +
                ", shopWebLink='" + shopWebLink + '\'' +
                ", address=" + address +
                ", workingHours=" + workingHours +
                ", items=" + items +
                '}';
    }
}