package com.mana.innovative.domain.consumer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Bloom/Rono on 5/14/2015 6:45 PM. This class is Privileges
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "privileges" )
public class Privilege {


    /**
     * The Privilege id.
     */
    @Id
    @Column( name = "privilege_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private int privilegeId;
    /**
     * The Privilege name.
     */
    @Column( name = "privilege_name", length = 100 )
    private String privilegeName;
    /**
     * The Accessible.
     */
    @Column( name = "is_accessible" )
    private boolean accessible;

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
     * The User roles.
     */
    @ManyToMany( mappedBy = "privileges", cascade = { CascadeType.MERGE, CascadeType.REFRESH } )
    private List< UserRole > userRoles;

    /**
     * Gets privilege id.
     *
     * @return the privilege id
     */
    public int getPrivilegeId( ) {
        return privilegeId;
    }

    /**
     * Sets privilege id.
     *
     * @param privilegeId the privilege id
     */
    public void setPrivilegeId( final int privilegeId ) {
        this.privilegeId = privilegeId;
    }

    /**
     * Gets privilege name.
     *
     * @return the privilege name
     */
    public String getPrivilegeName( ) {
        return privilegeName;
    }

    /**
     * Sets privilege name.
     *
     * @param privilegeName the privilege name
     */
    public void setPrivilegeName( final String privilegeName ) {
        this.privilegeName = privilegeName;
    }

    /**
     * Is accessible.
     *
     * @return the boolean
     */
    public boolean isAccessible( ) {
        return accessible;
    }

    /**
     * Sets accessible.
     *
     * @param accessible the accessible
     */
    public void setAccessible( final boolean accessible ) {
        this.accessible = accessible;
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
     * Gets update date.
     *
     * @return the update date
     */
    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    /**
     * Sets update date.
     *
     * @param updatedDate the update date
     */
    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets user roles.
     *
     * @return the user roles
     */
    public List< UserRole > getUserRoles( ) {
        return userRoles;
    }

    /**
     * Sets user roles.
     *
     * @param userRoles the user roles
     */
    public void setUserRoles( final List< UserRole > userRoles ) {
        this.userRoles = userRoles;
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
        if ( o == null || getClass( ) != o.getClass( ) ) return false;
        Privilege that = ( Privilege ) o;
        return Objects.equals( privilegeId, that.privilegeId ) &&
                Objects.equals( accessible, that.accessible ) &&
                Objects.equals( privilegeName, that.privilegeName );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Privilege {" +
                "  privilegeId=" + privilegeId +
                ",  privilegeName= " + privilegeName +
                ",  accessible=" + accessible +
                ",  createdDate=" + createdDate +
                ",  updatedDate=" + updatedDate +
                '}';
    }
}
