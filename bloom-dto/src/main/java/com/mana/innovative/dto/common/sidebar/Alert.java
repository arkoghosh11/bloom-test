package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 8:45 PM
 * @since: jdk 1.7
 */
@XmlRootElement( name = "alert" )
public class Alert extends SidebarType {


    private int alertId;
    private String detailLink;

    @XmlElement( name = "alert_id", nillable = false )
    public int getAlertId( ) {
        return alertId;
    }

    public void setAlertId( final int alertId ) {
        this.alertId = alertId;
//        this.setSidebarTypeId( alertId );
    }

    @XmlElement( name = "link", defaultValue = "", nillable = false )
    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
