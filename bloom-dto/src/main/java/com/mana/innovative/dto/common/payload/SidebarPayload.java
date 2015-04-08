package com.mana.innovative.dto.common.payload;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 4/9/13 Time: 4:25 PM
 * @since: jdk 1.7
 */
@XmlRootElement( name = "sidebar_payload", namespace = "http:localhost:8080/rest/sidebar?" )
public class SidebarPayload {

    @XmlElement( name = "sidebar_type_list" )
    private List< SidebarType > sidebarTypeList;


    public List< SidebarType > getSidebarTypeList( ) {
        return sidebarTypeList;
    }

    public void setSidebarTypeList( final List< SidebarType > sidebarTypeList ) {
        this.sidebarTypeList = sidebarTypeList;
    }
}
