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
 * @since: jdk 1.7
 */
@XmlRootElement( name = "sidebar_image_payload" )
public class SidebarImagePayload {

    private List< SidebarImageType > sidebarTypes;

    private int count;

    @XmlElementWrapper( name = "sidebar_types" )
    @XmlElement( name = "sidebar_types" )
    public List< SidebarImageType > getSidebarTypes( ) {
        return sidebarTypes;
    }

    public void setSidebarTypes( final List< SidebarImageType > sidebarTypes ) {
        this.sidebarTypes = sidebarTypes;
    }

    @XmlElement
    public int getCount( ) {
        return count;
    }

    public void setCount( final int count ) {
        this.count = count;
    }
}
