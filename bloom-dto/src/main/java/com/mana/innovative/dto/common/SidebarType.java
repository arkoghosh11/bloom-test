package com.mana.innovative.dto.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 8:05 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "sidebarType" )
public class SidebarType {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( SidebarType.class );

    /**
     * The Item type.
     */
    private String itemType;
    /**
     * The Subject.
     */
    private String subject;
    /**
     * The Group type.
     */
    private String groupType;
    /**
     * The Due date.
     */
    private Date dueDate;
    /**
     * The Sidebar type id.
     */
    private Long sidebarTypeId;
    /**
     * The Group priority.
     */
    private int groupPriority;
    /**
     * The Type priority.
     */
    private int typePriority;
    /**
     * The Group count.
     */
    private int groupCount;
    /**
     * The Success.
     */
    private boolean success;

    /**
     * Instantiates a new Sidebar type.
     */
    public SidebarType( ) {
        logger.info( "Class Initialize" + logger.getClass( ).getCanonicalName( ) );
    }

    /**
     * Gets item type.
     *
     * @return the item type
     */
    @XmlElement( name = "ITEM_TYPE" )
    public String getItemType( ) {
        return itemType;
    }

    /**
     * Sets item type.
     *
     * @param itemType the item type
     */
    public void setItemType( final String itemType ) {
        this.itemType = itemType;
    }

    /**
     * Gets due date.
     *
     * @return the due date
     */
    @XmlElement( name = "DUE_DATE" )
    public Date getDueDate( ) {
        return dueDate;
    }

    /**
     * Sets due date.
     *
     * @param dueDate the due date
     */
    public void setDueDate( final Date dueDate ) {
        this.dueDate = dueDate;
    }

    /**
     * Gets group type.
     *
     * @return the group type
     */
    @XmlElement( name = "GROUP" )
    public String getGroupType( ) {
        return groupType;
    }

    /**
     * Sets group type.
     *
     * @param groupType the group type
     */
    public void setGroupType( final String groupType ) {
        this.groupType = groupType;
    }

    /**
     * Gets group priority.
     *
     * @return the group priority
     */
    @XmlElement( name = "GROUP_PRIORITY" )
    public int getGroupPriority( ) {
        return groupPriority;
    }

    /**
     * Sets group priority.
     *
     * @param groupPriority the group priority
     */
    public void setGroupPriority( final int groupPriority ) {
        this.groupPriority = groupPriority;
    }

    /**
     * Gets type priority.
     *
     * @return the type priority
     */
    @XmlElement( name = "TYPE_PRIORITY" )
    public int getTypePriority( ) {
        return typePriority;
    }

    /**
     * Sets type priority.
     *
     * @param typePriority the type priority
     */
    public void setTypePriority( final int typePriority ) {
        this.typePriority = typePriority;
    }

    /**
     * Gets group count.
     *
     * @return the group count
     */
    @XmlElement( name = "GROUP_COUNT" )
    public int getGroupCount( ) {
        return groupCount;
    }

    /**
     * Sets group count.
     *
     * @param groupCount the group count
     */
    public void setGroupCount( final int groupCount ) {
        this.groupCount = groupCount;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    @XmlElement( name = "SUBJECT" )
    public String getSubject( ) {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject( final String subject ) {
        this.subject = subject;
    }

    /**
     * Is success.
     *
     * @return the boolean
     */
    @XmlElement( name = "SUCCESS", defaultValue = "true", nillable = false )
    public boolean isSuccess( ) {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess( final boolean success ) {
        this.success = success;
    }

    /**
     * Gets sidebar type id.
     *
     * @return the sidebar type id
     */
    public Long getSidebarTypeId( ) {
        return sidebarTypeId;
    }

    /**
     * Sets sidebar type id.
     *
     * @param sidebarTypeId the sidebar type id
     */
    @XmlElement( name = "SIDEBAR_TYPE_ID", nillable = false )
    public void setSidebarTypeId( final Long sidebarTypeId ) {
        this.sidebarTypeId = sidebarTypeId;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {

        if ( this == o ) return true;
        if ( !( o instanceof SidebarType ) ) return false;
        SidebarType that = ( SidebarType ) o;
        return Objects.equals( getGroupPriority( ), that.getGroupPriority( ) ) &&
                Objects.equals( getTypePriority( ), that.getTypePriority( ) ) &&
                Objects.equals( getGroupCount( ), that.getGroupCount( ) ) &&
                Objects.equals( isSuccess( ), that.isSuccess( ) ) &&
                Objects.equals( getItemType( ), that.getItemType( ) ) &&
                Objects.equals( getSubject( ), that.getSubject( ) ) &&
                Objects.equals( getGroupType( ), that.getGroupType( ) ) &&
                Objects.equals( getDueDate( ), that.getDueDate( ) ) &&
                Objects.equals( getSidebarTypeId( ), that.getSidebarTypeId( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "SidebarType {" +
                " itemType='" + itemType +
                ", subject='" + subject +
                ", groupType='" + groupType +
                ", dueDate=" + dueDate +
                ", sidebarTypeId=" + sidebarTypeId +
                ", groupPriority=" + groupPriority +
                ", typePriority=" + typePriority +
                ", groupCount=" + groupCount +
                ", success=" + success +
                '}';
    }
}