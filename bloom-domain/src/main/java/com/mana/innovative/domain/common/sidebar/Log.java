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
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "logItem" )
public class Log extends SidebarType {

    @GeneratedValue
    @Column( name = "log_id", unique = true )
    private int logId;

    @Column( name = "detail_link" )
    private String detailLink;

    public int getLogId( ) {
        return logId;
    }

    public void setLogId( final int logId ) {
        this.logId = logId;
        this.setSidebarTypeId( logId );
    }

    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
