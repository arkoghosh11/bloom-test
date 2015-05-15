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
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "user_role" )
public class UserRole {

    private Integer userRoleId;
    private String userRoleName;
    private boolean isActive;

    private List< Privilege > privileges;

    @XmlElement( name = "user_role_id", nillable = false, defaultValue = "1" )
    public Integer getUserRoleId( ) {
        return userRoleId;
    }

    public void setUserRoleId( final Integer userRoleId ) {
        this.userRoleId = userRoleId;
    }

    @XmlElement( name = "user_role_name", nillable = false, defaultValue = "anonymous" )
    public String getUserRoleName( ) {
        return userRoleName;
    }

    public void setUserRoleName( final String userRoleName ) {
        this.userRoleName = userRoleName;
    }

    @XmlElement( name = "is_active" )
    public boolean isActive( ) {
        return isActive;
    }

    public void setIsActive( final boolean isActive ) {
        this.isActive = isActive;
    }

    @XmlElementWrapper( name = "privileges" )
    @XmlElement( name = "privilege" )
    public List< Privilege > getPrivileges( ) {
        return privileges;
    }

    public void setPrivileges( final List< Privilege > privileges ) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof UserRole ) ) return false;
        UserRole userRole = ( UserRole ) o;
        return Objects.equals( isActive( ), userRole.isActive( ) ) &&
                Objects.equals( getUserRoleName( ), userRole.getUserRoleName( ) ) &&
                Objects.equals( getPrivileges( ), userRole.getPrivileges( ) );
    }


    @Override
    public String toString( ) {
        return "UserRole {" +
                " userRoleName= " + userRoleName +
                ", isActive=" + isActive +
                ", privileges=" + privileges +
                '}';
    }
}
