/**
 * @author Bloom This Class TabPayload.java is for Created at Aug 19, 2012 6:38:43 PM
 */
package com.mana.innovative.dto.common.payload;

import com.mana.innovative.dto.common.Tab;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;


/**
 * @author Bloom
 */
public class TabsPayload {

    private List< Tab > tabs;

    /**
     * This method returns a list of tabs
     *
     * @return {@link List<Tab></Tab>} Return a list of tabs
     */
    @XmlElementWrapper( name = "tabs" )
    @XmlElement( name = "tab" )
    public List< Tab > getTabs( ) {

        return tabs;
    }

    /**
     * This method sets a list of tabs to its class property
     *
     * @param tabs {@link List<Tab></Tab>} A list of tabs
     */
    public void setTabs( List< Tab > tabs ) {

        this.tabs = tabs;
    }
}
