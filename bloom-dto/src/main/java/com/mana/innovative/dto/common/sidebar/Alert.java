package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 8:45 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "alert" )
public class Alert extends SidebarType {


    /**
     * The Alert id.
     */
    private int alertId;
    /**
     * The Detail link.
     */
    private String detailLink;

    /**
     * Gets alert id.
     *
     * @return the alert id
     */
    @XmlElement( name = "alert_id", nillable = false )
    public int getAlertId( ) {
        return alertId;
    }

    /**
     * Sets alert id.
     *
     * @param alertId the alert id
     */
    public void setAlertId( final int alertId ) {
        this.alertId = alertId;
//        this.setSidebarTypeId( alertId );
    }

    /**
     * Gets detail link.
     *
     * @return the detail link
     */
    @XmlElement( name = "link", defaultValue = "", nillable = false )
    public String getDetailLink( ) {
        return detailLink;
    }

    /**
     * Sets detail link.
     *
     * @param detailLink the detail link
     */
    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
