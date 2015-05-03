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
 * @since: jdk 1.7
 */
@XmlRootElement( name = "sidebarType" )
public class SidebarType {

    private static final Logger logger = LoggerFactory.getLogger( SidebarType.class );

    private String itemType;
    private String subject;
    private String groupType;
    private Date dueDate;
    private Long sidebarTypeId;
    private int groupPriority;
    private int typePriority;
    private int groupCount;
    private boolean success;

    public SidebarType( ) {
        logger.info( "Class Initialize" + logger.getClass( ).getCanonicalName( ) );
    }

    @XmlElement( name = "ITEM_TYPE" )
    public String getItemType( ) {
        return itemType;
    }

    public void setItemType( final String itemType ) {
        this.itemType = itemType;
    }

    @XmlElement( name = "DUE_DATE" )
    public Date getDueDate( ) {
        return dueDate;
    }

    public void setDueDate( final Date dueDate ) {
        this.dueDate = dueDate;
    }

    @XmlElement( name = "GROUP" )
    public String getGroupType( ) {
        return groupType;
    }

    public void setGroupType( final String groupType ) {
        this.groupType = groupType;
    }

    @XmlElement( name = "GROUP_PRIORITY" )
    public int getGroupPriority( ) {
        return groupPriority;
    }

    public void setGroupPriority( final int groupPriority ) {
        this.groupPriority = groupPriority;
    }

    @XmlElement( name = "TYPE_PRIORITY" )
    public int getTypePriority( ) {
        return typePriority;
    }

    public void setTypePriority( final int typePriority ) {
        this.typePriority = typePriority;
    }

    @XmlElement( name = "GROUP_COUNT" )
    public int getGroupCount( ) {
        return groupCount;
    }

    public void setGroupCount( final int groupCount ) {
        this.groupCount = groupCount;
    }

    @XmlElement( name = "SUBJECT" )
    public String getSubject( ) {
        return subject;
    }

    public void setSubject( final String subject ) {
        this.subject = subject;
    }

    @XmlElement( name = "SUCCESS", defaultValue = "true", nillable = false )
    public boolean isSuccess( ) {
        return success;
    }

    public void setSuccess( final boolean success ) {
        this.success = success;
    }

    public Long getSidebarTypeId( ) {
        return sidebarTypeId;
    }

    @XmlElement( name = "SIDEBAR_TYPE_ID", nillable = false )
    public void setSidebarTypeId( final Long sidebarTypeId ) {
        this.sidebarTypeId = sidebarTypeId;
    }

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