package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:03 PM
 * @since: jdk 1.7
 */
@XmlRootElement( name = "guideline" )
public class Guideline extends SidebarType {

    private int guidelineId;
    private String detailLink;

    @XmlElement( name = "guideline_id", nillable = false )
    public int getGuidelineId( ) {
        return guidelineId;
    }

    public void setGuidelineId( final int guidelineId ) {
        this.guidelineId = guidelineId;
        this.setSidebarTypeId( guidelineId );
    }

    @XmlElement( name = "link", defaultValue = "", nillable = false )
    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
