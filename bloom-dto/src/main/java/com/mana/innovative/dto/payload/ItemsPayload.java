package com.mana.innovative.dto.payload;

import com.mana.innovative.dto.Item;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * The type Items payload.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlSeeAlso( { Item.class } )
@XmlRootElement( name = "items_payload" )
public class ItemsPayload {

    private List<Item> items;
    private int totalCount;


    /**
     * This method returns a list of items
     * @return {@link java.util.List<Item></>}  Return a list of items
     */
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<Item> getItems() {
        return items;
    }
    /**
     * This method sets a list of items to its class property
     * @param items {@link java.util.List<Item></Item>} A list of items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotalCount () {
        return totalCount;
    }

    @XmlElement(name = "item_count")
    public void setTotalCount (final int totalCount) {
        this.totalCount = totalCount;
    }
}
