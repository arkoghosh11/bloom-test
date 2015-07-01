package com.mana.innovative.dto.client.payload;

import com.mana.innovative.dto.client.Item;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * The type Items payload.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlSeeAlso( { Item.class } )
@XmlRootElement( name = "items_payload" )
public class ItemsPayload {

    /**
     * The Items.
     */
    private List< Item > items;
    /**
     * The Total count.
     */
    private int totalCount;


    /**
     * This method returns a list of items
     *
     * @return Return a list of items
     */
    @XmlElementWrapper( name = "items" )
    @XmlElement( name = "item" )
    public List< Item > getItems( ) {
        return items;
    }

    /**
     * This method sets a list of items to its class property
     *
     * @param items A list of items
     */
    public void setItems( List< Item > items ) {
        this.items = items;
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
    @XmlElement( name = "item_count" )
    public void setTotalCount( final int totalCount ) {
        this.totalCount = totalCount;
    }
}
