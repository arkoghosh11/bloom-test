/**
 * @author Bloom This Class User.java is for Created at Aug 28, 2012 4:05:17 PM
 */
package com.mana.innovative.domain.consumer;

import org.hibernate.validator.constraints.Email;

import javax.persistence.CascadeType;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * The type User.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "users" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( "User" )
public class User {

    /**
     * The User id.
     */
    @Id
    @Column( name = "user_id", nullable = false )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long userId;

    /**
     * The User name.
     */
    @Column( name = "user_name", nullable = false, unique = true )
    private String userName;

    /**
     * The Password.
     */
    @Column( name = "password", nullable = false )
    private String password;

    /**
     * The Email.
     */
    @Column( name = "email", nullable = false )
    @Email
    private String email;

    /**
     * The Created date.
     */
    @Column( name = "created_date", updatable = false )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH } )
    @JoinColumn( name = "user_role_id", referencedColumnName = "user_role_id" )
    private UserRole userRole;

    /**
     * Gets user id.
     *
     * @return the userId
     */
    public long getUserId( ) {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the userId to set
     */
    public void setUserId( long userId ) {
        this.userId = userId;
    }

    /**
     * Gets user name.
     *
     * @return the userName
     */
    public String getUserName( ) {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the userName to set
     */
    public void setUserName( String userName ) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword( ) {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password to set
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail( ) {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email to set
     */
    public void setEmail( String email ) {
        this.email = email;
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
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole( ) {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole( final UserRole userRole ) {
        this.userRole = userRole;
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
        if ( !( o instanceof User ) ) return false;
        User user = ( User ) o;
        return Objects.equals( getUserId( ), user.getUserId( ) ) &&
                Objects.equals( getUserName( ), user.getUserName( ) ) &&
                Objects.equals( getPassword( ), user.getPassword( ) ) &&
                Objects.equals( getEmail( ), user.getEmail( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "User {" +
                " userId=" + userId +
                ", userName= " + userName +
                ", password= " + password +
                ", email= " + email +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
