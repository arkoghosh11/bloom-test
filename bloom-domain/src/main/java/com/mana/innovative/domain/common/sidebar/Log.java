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
 * @author Bloom Date: 3/23/13 Time: 9:04 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "logItem" )
public class Log extends SidebarType {

    /**
     * The Log id.
     */
    @GeneratedValue
    @Column( name = "log_id", unique = true )
    private int logId;

    /**
     * The Detail link.
     */
    @Column( name = "detail_link" )
    private String detailLink;

    /**
     * Gets log id.
     *
     * @return the log id
     */
    public int getLogId( ) {
        return logId;
    }

    /**
     * Sets log id.
     *
     * @param logId the log id
     */
    public void setLogId( final int logId ) {
        this.logId = logId;
        this.setSidebarTypeId( logId );
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
