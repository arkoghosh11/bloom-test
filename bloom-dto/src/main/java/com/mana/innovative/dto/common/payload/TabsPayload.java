/**
 * @author Bloom This Class TabPayload.java is for Created at Aug 19, 2012 6:38:43 PM
 */
package com.mana.innovative.dto.common.payload;

import com.mana.innovative.dto.common.Tab;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * The type Tabs payload.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class TabsPayload {

    /**
     * The Tabs.
     */
    private List< Tab > tabs;
    /**
     * The Total count.
     */
    private int totalCount;

    /**
     * This method returns a list of tabs
     *
     * @return Return a list of tabs
     */
    @XmlElementWrapper( name = "tabs" )
    @XmlElement( name = "tab" )
    public List< Tab > getTabs( ) {

        return tabs;
    }

    /**
     * This method sets a list of tabs to its class property
     *
     * @param tabs A list of tabs
     */
    public void setTabs( List< Tab > tabs ) {

        this.tabs = tabs;
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
    public void setTotalCount( final int totalCount ) {
        this.totalCount = totalCount;
    }
}
