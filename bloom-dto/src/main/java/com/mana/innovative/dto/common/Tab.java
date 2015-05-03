/**
 * @author Bloom This Class Tabs.java is for Created at August 19, 2012 5:15:37 PM
 */
package com.mana.innovative.dto.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * @author Bloom
 */
@XmlRootElement( name = "tabs", namespace = "http://localhost/rest/Bloom" )
public class Tab {

    private Integer tabId;
    private String tabName;
    private String tabContent;

    /**
     * @return the tabId
     */
    public Integer getTabId( ) {
        return tabId;
    }

    /**
     * @param tabId the tabId to set
     */
    @XmlElement( name = "tabId", nillable = false, required = true )
    public void setTabId( Integer tabId ) {
        this.tabId = tabId;
    }

    /**
     * @return the tabName
     */
    public String getTabName( ) {
        return tabName;
    }

    /**
     * @param tabName the tabName to set
     */
    @XmlElement( name = "tabName", nillable = true, required = false )
    public void setTabName( String tabName ) {
        this.tabName = tabName;
    }

    /**
     * @return the tabContent
     */
    public String getTabContent( ) {
        return tabContent;
    }

    /**
     * @param tabContent the tabContent to set
     */
    @XmlElement( name = "tabContent", nillable = true, required = false )
    public void setTabContent( String tabContent ) {
        this.tabContent = tabContent;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Tab ) ) return false;
        Tab tab = ( Tab ) o;
        return Objects.equals( getTabId( ), tab.getTabId( ) ) &&
                Objects.equals( getTabName( ), tab.getTabName( ) ) &&
                Objects.equals( getTabContent( ), tab.getTabContent( ) );
    }

    @Override
    public String toString( ) {
        return "Tab {" +
                " tabId=" + tabId +
                ", tabName='" + tabName +
                ", tabContent='" + tabContent +
                '}';
    }
}
