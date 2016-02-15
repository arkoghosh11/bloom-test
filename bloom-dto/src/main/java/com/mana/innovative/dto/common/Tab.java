/**
 * @author Bloom This Class Tabs.java is for Created at August 19, 2012 5:15:37 PM
 */
package com.mana.innovative.dto.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement( name = "tabs", namespace = "http://localhost/rest/Bloom" )
public class Tab {

	/**
	 * The Tab id.
	 */
	private Integer tabId;
	/**
	 * The Tab name.
	 */
	private String tabName;
	/**
	 * The Tab content.
	 */
	private String tabContent;

	private String tabColor;

	private String tabPosition;

	/**
	 * Gets tab id.
	 *
	 * @return the tabId
	 */
	@XmlElement( name = "tab_id", nillable = false, required = true )
	public Integer getTabId( ) {
		return tabId;
	}

	/**
	 * Sets tab id.
	 *
	 * @param tabId the tabId to set
	 */
	public void setTabId( Integer tabId ) {
		this.tabId = tabId;
	}

	/**
	 * Gets tab name.
	 *
	 * @return the tabName
	 */
	@XmlElement( name = "tab_name", nillable = true, required = false )
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
	@XmlElement( name = "tab_content", nillable = true, required = false )
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

	@XmlElement( name = "tab_color", nillable = true, required = false )
	public String getTabColor( ) {
		return tabColor;
	}

	public void setTabColor( final String tabColor ) {
		this.tabColor = tabColor;
	}

	@XmlElement( name = "tab_position", nillable = true, required = false )
	public String getTabPosition( ) {
		return tabPosition;
	}

	public void setTabPosition( final String tabPosition ) {
		this.tabPosition = tabPosition;
	}

	/**
	 * Equals boolean.
	 *
	 * @param o the o
	 *
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
				'}';
	}
}
