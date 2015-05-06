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
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "alertItem" )
public class Alert extends SidebarType {


    /**
     * The Alert id.
     */
    @GeneratedValue
    @Column( name = "alert_id", unique = true, nullable = false )
    private int alertId;

    /**
     * The Detail link.
     */
    @Column( name = "detail_link", nullable = true )
    private String detailLink;

    /**
     * Gets alert id.
     *
     * @return the alert id
     */
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
        this.setSidebarTypeId( alertId );
    }

    /**
     * Gets detail link.
     *
     * @return the detail link
     */
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
