package com.mana.innovative.domain.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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
    private String group;

    @Temporal( value = TemporalType.DATE )
    @Column( name = "date" )
    private Date date;

    @Id
    @Column( name = "sidebar_type_id" )
    @GeneratedValue
    private int sidebarTypeId;
    @Column( name = "group_priority" )
    private int groupPriority;
    @Column( name = "type_priority" )
    private int typePriority;
    @Column( name = "group_count" )
    private int groupCount;
    @Column( name = "success" )
    private boolean success;

    public SidebarType( ) {
        logger.info( "Class Initialize" + logger.getClass( ).getCanonicalName( ) );
    }

    public String getItemType( ) {
        return itemType;
    }

    public void setItemType( final String itemType ) {
        this.itemType = itemType;
    }

    public Date getDate( ) {
        return date;
    }

    public void setDate( final Date date ) {
        this.date = date;
    }

    public String getGroup( ) {
        return group;
    }

    public void setGroup( final String group ) {
        this.group = group;
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

    public int getSidebarTypeId( ) {
        return sidebarTypeId;
    }

    public void setSidebarTypeId( final int sidebarTypeId ) {
        this.sidebarTypeId = sidebarTypeId;
    }
}