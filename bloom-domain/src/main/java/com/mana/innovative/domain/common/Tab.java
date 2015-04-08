/**
 * @author Bloom This Class Tabs.java is for Created at August 19, 2012 5:15:37 PM
 */
package com.mana.innovative.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bloom
 */
@Entity
@Table( name = "tabs" )
public class Tab {

    @Id
    @Column( name = "tab_id" )
    private int tabId;
    @Column( name = "tab_name" )
    private String tabName;
    @Column( name = "tab_content" )
    private String tabContent;

    /**
     * @return the tabId
     */
    public int getTabId( ) {
        return tabId;
    }

    /**
     * @param tabId the tabId to set
     */
    public void setTabId( int tabId ) {
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
    public void setTabContent( String tabContent ) {
        this.tabContent = tabContent;
    }

    public Object clone( ) throws CloneNotSupportedException {
        return super.clone( );
    }

    public String toString( ) {
        return tabId + " " + tabName + " " + tabContent;
    }

}
