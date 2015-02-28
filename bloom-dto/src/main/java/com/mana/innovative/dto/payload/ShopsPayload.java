package com.mana.innovative.dto.payload;/**
 * Created by Rono on 2/27/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.dto.Item;
import com.mana.innovative.dto.Shop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by Bloom on 2/27/2015 : 5:58 PM
 * todo This class is for ...
 */
public class ShopsPayload {

    private List<Shop> shops;
    private int totalCount;


    /**
     * This method returns a list of items
     * @return {@link java.util.List<Item></>}  Return a list of items
     */
    @XmlElementWrapper(name = "shops")
    @XmlElement(name = "shop")
    public List<Shop> getShops() {
        return shops;
    }
    /**
     * This method sets a list of shops to its class property
     * @param shops {@link java.util.List<Shop></Shop>} A list of shops
     */
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public int getTotalCount () {
        return totalCount;
    }

    @XmlElement(name = "shop_count")
    public void setTotalCount (final int totalCount) {
        this.totalCount = totalCount;
    }
}
