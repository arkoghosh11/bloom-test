package com.mana.innovative.domain.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Bloom/Rono on 5/13/2015 11:20 AM. This class is UserRole
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "user_roles" )
public class UserRole {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserRole.class );

    /**
     * The User role id.
     */
    @Id
    @Column( name = "user_role_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private int userRoleId;

    /**
     * The Is active.
     */
    @Column( name = "is_active" )
    private boolean isActive;

    /**
     * The User role name.
     */
    @Column( name = "user_role_name", columnDefinition = "varchar", length = 20 )
    private String userRoleName;

    /**
     * The Created date.
     */
    @Column( name = "created_date", columnDefinition = "timestamp" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;

    /**
     * The Updated date.
     */
    @Column( name = "updated_date", columnDefinition = "timestamp" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    /**
     * The Users.
     */
    @OneToMany( mappedBy = "userRole", cascade = { CascadeType.MERGE, CascadeType.PERSIST } )
    private List< User > users;

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "user_role_privilege", joinColumns = @JoinColumn( name = "user_role_id", referencedColumnName =
            "user_role_id" ), inverseJoinColumns = @JoinColumn( name = "privilege_id", referencedColumnName =
            "privilege_id" ), uniqueConstraints = @UniqueConstraint( name = "user_role_privilege_id", columnNames =
            { "user_role_id",
                    "privilege_id" } ) )
    private List< Privilege > privileges;

    /**
     * Gets user role id.
     *
     * @return the user role id
     */
    public int getUserRoleId( ) {
        return userRoleId;
    }

    /**
     * Sets user role id.
     *
     * @param userRoleId the user role id
     */
    public void setUserRoleId( final int userRoleId ) {
        this.userRoleId = userRoleId;
    }

    /**
     * Is active.
     *
     * @return the boolean
     */
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
     * Gets user role name.
     *
     * @return the user role name
     */
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
     * Gets users.
     *
     * @return the users
     */
    public List< User > getUsers( ) {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers( final List< User > users ) {
        this.users = users;
    }

    public List< Privilege > getPrivileges( ) {
        return privileges;
    }

    public void setPrivileges( final List< Privilege > privilegeList ) {
        this.privileges = privilegeList;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof UserRole ) ) return false;
        UserRole userRole = ( UserRole ) o;
        return Objects.equals( getUserRoleId( ), userRole.getUserRoleId( ) ) &&
                Objects.equals( isActive( ), userRole.isActive( ) ) &&
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
                " userRoleId=" + userRoleId +
                ", isActive=" + isActive +
                ", userRoleName= " + userRoleName +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", privilegeList=" + privileges +
                '}';
    }
}
