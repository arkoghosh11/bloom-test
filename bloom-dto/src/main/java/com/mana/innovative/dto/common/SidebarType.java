package com.mana.innovative.dto.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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
    private String group;
    private Date date;
    private int sidebarTypeId;
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

    @XmlElement( name = "DATE" )
    public Date getDate( ) {
        return date;
    }

    public void setDate( final Date date ) {
        this.date = date;
    }

    @XmlElement( name = "GROUP" )
    public String getGroup( ) {
        return group;
    }

    public void setGroup( final String group ) {
        this.group = group;
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

    public int getSidebarTypeId( ) {
        return sidebarTypeId;
    }

    @XmlElement( name = "SIDEBAR_TYPE_ID", nillable = false )
    public void setSidebarTypeId( final int sidebarTypeId ) {
        this.sidebarTypeId = sidebarTypeId;
    }
}