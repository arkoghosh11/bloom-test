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
 * @author Bloom Date: 3/23/13 Time: 9:03 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "guidelineItem" )
public class Guideline extends SidebarType {

    /**
     * The Guideline id.
     */
    @GeneratedValue
    @Column( name = "guideline_id" )
    private int guidelineId;

    /**
     * The Detail link.
     */
    @Column( name = "detail_link" )
    private String detailLink;

    /**
     * Gets guideline id.
     *
     * @return the guideline id
     */
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
        this.setSidebarTypeId( guidelineId );
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
