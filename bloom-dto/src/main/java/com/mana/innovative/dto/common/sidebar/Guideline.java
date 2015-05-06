package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:03 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "guideline" )
public class Guideline extends SidebarType {

    /**
     * The Guideline id.
     */
    private int guidelineId;
    /**
     * The Detail link.
     */
    private String detailLink;

    /**
     * Gets guideline id.
     *
     * @return the guideline id
     */
    @XmlElement( name = "guideline_id", nillable = false )
    public int getGuidelineId( ) {
        return guidelineId;
    }

    /**
     * Sets guideline id.
     *
     * @param guidelineId the guideline id
     */
    public void setGuidelineId( final int guidelineId ) {
        this.guidelineId = guidelineId;
//        this.setSidebarTypeId( guidelineId );
    }

    /**
     * Gets detail link.
     *
     * @return the detail link
     */
    @XmlElement( name = "link", defaultValue = "", nillable = false )
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
