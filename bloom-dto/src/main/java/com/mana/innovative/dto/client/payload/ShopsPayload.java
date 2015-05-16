package com.mana.innovative.dto.client.payload;

import com.mana.innovative.dto.client.Shop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * The type Shops payload.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ShopsPayload {

    /**
     * The Shops.
     */
    private List< Shop > shops;
    /**
     * The Total count.
     */
    private int totalCount;


    /**
     * This method returns a list of items
     *
     * @return Return a list of items
     */
    @XmlElementWrapper( name = "shops" )
    @XmlElement( name = "shop" )
    public List< Shop > getShops( ) {
        return shops;
    }

    /**
     * This method sets a list of shops to its class property
     *
     * @param shops A list of shops
     */
    public void setShops( List< Shop > shops ) {
        this.shops = shops;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount( ) {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    @XmlElement( name = "shop_count" )
    public void setTotalCount( final int totalCount ) {
        this.totalCount = totalCount;
    }
}
