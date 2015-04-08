package com.mana.innovative.domain.common.sidebar;


import com.mana.innovative.domain.common.SidebarType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 8:45 PM
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "alertItem" )
public class Alert extends SidebarType {


    @GeneratedValue
    @Column( name = "alert_id", unique = true, nullable = false )
    private int alertId;

    @Column( name = "detail_link", nullable = true )
    private String detailLink;

    public int getAlertId( ) {
        return alertId;
    }

    public void setAlertId( final int alertId ) {
        this.alertId = alertId;
        this.setSidebarTypeId( alertId );
    }

    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
