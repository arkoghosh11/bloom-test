package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:04 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
// Note this class is not supported for now
@XmlRootElement( name = "Log" )
public class Log extends SidebarType {

    /**
     * The Log id.
     */
    private int logId;
    /**
     * The Detail link.
     */
    private String detailLink;

    /**
     * Gets log id.
     *
     * @return the log id
     */
    @XmlElement( name = "LOG_ID", nillable = false )
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
//        this.setSidebarTypeId( logId );
    }

    /**
     * Gets detail link.
     *
     * @return the detail link
     */
    @XmlElement( name = "LINK", defaultValue = "", nillable = false )
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
