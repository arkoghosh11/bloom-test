/**
 * @author Bloom This Class Tabs.java is for Created at August 19, 2012 5:15:37 PM
 */
package com.mana.innovative.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * The type Tab.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "tabs" )
public class Tab {

    /**
     * The Tab id.
     */
    @Id
    @Column( name = "tab_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private int tabId;

    /**
     * The Tab name.
     */
    @Column( name = "tab_name" )
    private String tabName;
    /**
     * The Tab content.
     */
    @Column( name = "tab_content" )
    private String tabContent;

    @Column( name = "tab_color" )
    private String tabColor;

    @Column( name = "tab_position" )
    private String tabPosition;

    /**
     * The Created date.
     */
    @Column( name = "created_date", updatable = false )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    /**
     * Gets tab id.
     *
     * @return the tabId
     */
    public int getTabId( ) {
        return tabId;
    }

    /**
     * Sets tab id.
     *
     * @param tabId the tabId to set
     */
    public void setTabId( int tabId ) {
        this.tabId = tabId;
    }

    /**
     * Gets tab name.
     *
     * @return the tabName
     */
    public String getTabName( ) {
        return tabName;
    }

    /**
     * Sets tab name.
     *
     * @param tabName the tabName to set
     */
    public void setTabName( String tabName ) {
        this.tabName = tabName;
    }

    /**
     * Gets tab content.
     *
     * @return the tabContent
     */
    public String getTabContent( ) {
        return tabContent;
    }

    /**
     * Sets tab content.
     *
     * @param tabContent the tabContent to set
     */
    public void setTabContent( String tabContent ) {
        this.tabContent = tabContent;
    }

    public String getTabColor( ) {
        return tabColor;
    }

    public void setTabColor( final String tabColor ) {
        this.tabColor = tabColor;
    }

    public String getTabPosition( ) {
        return tabPosition;
    }

    public void setTabPosition( final String tabPosition ) {
        this.tabPosition = tabPosition;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate( ) {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Tab ) ) return false;
        Tab tab = ( Tab ) o;
        return Objects.equals( getTabId( ), tab.getTabId( ) ) &&
                Objects.equals( getTabName( ), tab.getTabName( ) ) &&
                Objects.equals( getTabContent( ), tab.getTabContent( ) ) &&
                Objects.equals( getTabColor( ), tab.getTabColor( ) ) &&
                Objects.equals( getTabPosition( ), tab.getTabPosition( ) );
    }

    /**
     * Clone object.
     *
     * @return the object
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public Object clone( ) throws CloneNotSupportedException {
        return super.clone( );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Tab{" +
                "tabId=" + tabId +
                ", tabName='" + tabName + '\'' +
                ", tabContent='" + tabContent + '\'' +
                ", tabColor='" + tabColor + '\'' +
                ", tabPosition='" + tabPosition + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
