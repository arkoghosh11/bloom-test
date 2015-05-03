package com.mana.innovative.domain.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 8:05 PM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "sidebar_items" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "sidebarItem" )
public class SidebarType {

    private static final Logger logger = LoggerFactory.getLogger( SidebarType.class );

    @Column( name = "item_type" )
    private String itemType;
    @Column( name = "subject" )
    private String subject;
    @Column( name = "group_type" )
    private String groupType;

    @Temporal( value = TemporalType.DATE )
    @Column( name = "due_date" )
    private Date dueDate;

    @Id
    @Column( name = "sidebar_type_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long sidebarTypeId;

    @Column( name = "group_priority" )
    private int groupPriority;
    @Column( name = "type_priority" )
    private int typePriority;
    @Column( name = "group_count" )
    private int groupCount;
    @Column( name = "success" )
    private boolean success;

    @Column( name = "created_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    public SidebarType( ) {
        logger.info( "Class Initialize" + logger.getClass( ).getCanonicalName( ) );
    }

    public String getItemType( ) {
        return itemType;
    }

    public void setItemType( final String itemType ) {
        this.itemType = itemType;
    }

    public Date getDueDate( ) {
        return dueDate;
    }

    public void setDueDate( final Date date ) {
        this.dueDate = date;
    }

    public String getGroupType( ) {
        return groupType;
    }

    public void setGroupType( final String group ) {
        this.groupType = group;
    }

    public int getGroupPriority( ) {
        return groupPriority;
    }

    public void setGroupPriority( final int groupPriority ) {
        this.groupPriority = groupPriority;
    }

    public int getTypePriority( ) {
        return typePriority;
    }

    public void setTypePriority( final int typePriority ) {
        this.typePriority = typePriority;
    }

    public int getGroupCount( ) {
        return groupCount;
    }

    public void setGroupCount( final int groupCount ) {
        this.groupCount = groupCount;
    }

    public String getSubject( ) {
        return subject;
    }

    public void setSubject( final String subject ) {
        this.subject = subject;
    }

    public boolean isSuccess( ) {
        return success;
    }

    public void setSuccess( final boolean success ) {
        this.success = success;
    }

    public long getSidebarTypeId( ) {
        return sidebarTypeId;
    }

    public void setSidebarTypeId( final long sidebarTypeId ) {
        this.sidebarTypeId = sidebarTypeId;
    }

    public Date getCreatedDate( ) {
        return createdDate;
    }

    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof SidebarType ) ) return false;
        SidebarType that = ( SidebarType ) o;
        return Objects.equals( getSidebarTypeId( ), that.getSidebarTypeId( ) ) &&
                Objects.equals( getGroupPriority( ), that.getGroupPriority( ) ) &&
                Objects.equals( getTypePriority( ), that.getTypePriority( ) ) &&
                Objects.equals( getGroupCount( ), that.getGroupCount( ) ) &&
                Objects.equals( isSuccess( ), that.isSuccess( ) ) &&
                Objects.equals( getItemType( ), that.getItemType( ) ) &&
                Objects.equals( getSubject( ), that.getSubject( ) ) &&
                Objects.equals( getGroupType( ), that.getGroupType( ) ) &&
                Objects.equals( getDueDate( ), that.getDueDate( ) );
    }


    @Override
    public String toString( ) {
        return "SidebarType {" +
                " itemType= " + itemType +
                ", subject= " + subject +
                ", groupType= " + groupType +
                ", dueDate=" + dueDate +
                ", sidebarTypeId=" + sidebarTypeId +
                ", groupPriority=" + groupPriority +
                ", typePriority=" + typePriority +
                ", groupCount=" + groupCount +
                ", success=" + success + '}';
    }
}