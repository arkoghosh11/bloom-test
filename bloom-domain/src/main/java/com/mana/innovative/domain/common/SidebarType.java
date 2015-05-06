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
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
@Table( name = "sidebar_items" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "sidebarItem" )
public class SidebarType {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( SidebarType.class );

    /**
     * The Item type.
     */
    @Column( name = "item_type" )
    private String itemType;
    /**
     * The Subject.
     */
    @Column( name = "subject" )
    private String subject;
    /**
     * The Group type.
     */
    @Column( name = "group_type" )
    private String groupType;

    /**
     * The Due date.
     */
    @Temporal( value = TemporalType.DATE )
    @Column( name = "due_date" )
    private Date dueDate;

    /**
     * The Sidebar type id.
     */
    @Id
    @Column( name = "sidebar_type_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long sidebarTypeId;

    /**
     * The Group priority.
     */
    @Column( name = "group_priority" )
    private int groupPriority;
    /**
     * The Type priority.
     */
    @Column( name = "type_priority" )
    private int typePriority;
    /**
     * The Group count.
     */
    @Column( name = "group_count" )
    private int groupCount;
    /**
     * The Success.
     */
    @Column( name = "success" )
    private boolean success;

    /**
     * The Created date.
     */
    @Column( name = "created_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

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
    public Date getDueDate( ) {
        return dueDate;
    }

    /**
     * Sets due date.
     *
     * @param date the date
     */
    public void setDueDate( final Date date ) {
        this.dueDate = date;
    }

    /**
     * Gets group type.
     *
     * @return the group type
     */
    public String getGroupType( ) {
        return groupType;
    }

    /**
     * Sets group type.
     *
     * @param group the group
     */
    public void setGroupType( final String group ) {
        this.groupType = group;
    }

    /**
     * Gets group priority.
     *
     * @return the group priority
     */
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
    public long getSidebarTypeId( ) {
        return sidebarTypeId;
    }

    /**
     * Sets sidebar type id.
     *
     * @param sidebarTypeId the sidebar type id
     */
    public void setSidebarTypeId( final long sidebarTypeId ) {
        this.sidebarTypeId = sidebarTypeId;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate( ) {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
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


    /**
     * To string.
     *
     * @return the string
     */
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