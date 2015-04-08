/**
 * @author Bloom This Class User.java is for Created at Aug 28, 2012 4:05:17 PM
 */
package com.mana.innovative.domain.consumer;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Bloom
 */
@Entity
@Table( name = "users" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( "User" )
public class User {

    @Id
    @Column( name = "user_id", nullable = false )
    private String userId;

    @Column( name = "user_name", nullable = false, unique = true )
    private String userName;

    @Column( name = "password", nullable = false )
    private String password;

    @Column( name = "email", nullable = false )
    @Email
    private String email;

    /**
     * @return the userId
     */
    public String getUserId( ) {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId( String userId ) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName( ) {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName( String userName ) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword( ) {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail( ) {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    public String toString( ) {
        return this.userId + " " + this.userName + " " + this.password + " " + this.email + "";
    }
}
