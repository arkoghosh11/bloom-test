package com.mana.innovative.dto.common.payload;


import com.mana.innovative.dto.common.sidebar.SidebarImageType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/29/13 Time: 9:22 AM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "sidebar_image_payload" )
public class SidebarImagePayload {

    /**
     * The Sidebar types.
     */
    private List< SidebarImageType > sidebarTypes;

    /**
     * The Count.
     */
    private int count;

    /**
     * Gets sidebar types.
     *
     * @return the sidebar types
     */
    @XmlElementWrapper( name = "sidebar_types" )
    @XmlElement( name = "sidebar_types" )
    public List< SidebarImageType > getSidebarTypes( ) {
        return sidebarTypes;
    }

    /**
     * Sets sidebar types.
     *
     * @param sidebarTypes the sidebar types
     */
    public void setSidebarTypes( final List< SidebarImageType > sidebarTypes ) {
        this.sidebarTypes = sidebarTypes;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    @XmlElement
    public int getCount( ) {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount( final int count ) {
        this.count = count;
    }
}
