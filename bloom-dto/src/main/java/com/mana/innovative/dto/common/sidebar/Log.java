package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:04 PM
 * @since: jdk 1.7
 */
// Note this class is not supported for now
@XmlRootElement( name = "Log" )
public class Log extends SidebarType {

    private int logId;
    private String detailLink;

    @XmlElement( name = "LOG_ID", nillable = false )
    public int getLogId( ) {
        return logId;
    }

    public void setLogId( final int logId ) {
        this.logId = logId;
//        this.setSidebarTypeId( logId );
    }

    @XmlElement( name = "LINK", defaultValue = "", nillable = false )
    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
