package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created by Bloom/Rono on 5/13/2015 11:36 AM. This class is UserRole
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "user_role" )
public class UserRole {

    /**
     * The User role id.
     */
    private Integer userRoleId;
    /**
     * The User role name.
     */
    private String userRoleName;
    /**
     * The Is active.
     */
    private boolean isActive;

    private boolean isExpired;
    private boolean isLocked;

    /**
     * The Privileges.
     */
    private List< Privilege > privileges;

    /**
     * Gets user role id.
     *
     * @return the user role id
     */
    @XmlElement( name = "user_role_id", nillable = false, defaultValue = "1" )
    public Integer getUserRoleId( ) {
        return userRoleId;
    }

    /**
     * Sets user role id.
     *
     * @param userRoleId the user role id
     */
    public void setUserRoleId( final Integer userRoleId ) {
        this.userRoleId = userRoleId;
    }

    /**
     * Gets user role name.
     *
     * @return the user role name
     */
    @XmlElement( name = "user_role_name", nillable = false, defaultValue = "anonymous" )
    public String getUserRoleName( ) {
        return userRoleName;
    }

    /**
     * Sets user role name.
     *
     * @param userRoleName the user role name
     */
    public void setUserRoleName( final String userRoleName ) {
        this.userRoleName = userRoleName;
    }

    /**
     * Is active.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_active" )
    public boolean isActive( ) {
        return isActive;
    }

    /**
     * Sets is active.
     *
     * @param isActive the is active
     */
    public void setIsActive( final boolean isActive ) {
        this.isActive = isActive;
    }

    /**
     * Is expired.
     *
     * @return the boolean
     */
    public boolean isExpired( ) {
        return isExpired;
    }

    /**
     * Sets is expired.
     *
     * @param isExpired the is expired
     */
    public void setIsExpired( final boolean isExpired ) {
        this.isExpired = isExpired;
    }

    /**
     * Is locked.
     *
     * @return the boolean
     */
    public boolean isLocked( ) {
        return isLocked;
    }

    /**
     * Sets is locked.
     *
     * @param isLocked the is locked
     */
    public void setIsLocked( final boolean isLocked ) {
        this.isLocked = isLocked;
    }

    /**
     * Gets privileges.
     *
     * @return the privileges
     */
    @XmlElementWrapper( name = "privileges" )
    @XmlElement( name = "privilege" )
    public List< Privilege > getPrivileges( ) {
        return privileges;
    }

    /**
     * Sets privileges.
     *
     * @param privileges the privileges
     */
    public void setPrivileges( final List< Privilege > privileges ) {
        this.privileges = privileges;
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
        if ( !( o instanceof UserRole ) ) return false;
        UserRole userRole = ( UserRole ) o;
        return Objects.equals( isActive( ), userRole.isActive( ) ) &&
                Objects.equals( getUserRoleName( ), userRole.getUserRoleName( ) ) &&
                Objects.equals( getPrivileges( ), userRole.getPrivileges( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "UserRole {" +
                " userRoleName= " + userRoleName +
                ", isActive=" + isActive +
                ", privileges=" + privileges +
                '}';
    }
}
