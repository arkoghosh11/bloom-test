/**
 * @author Bloom This Class User.java is for Created at Aug 28, 2012 4:05:17 PM
 */
package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The type User.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "user", namespace = "http://localhost/rest/Bloom/user" )
public class User {

    /**
     * The User id.
     */
    private Long userId;

    /**
     * The User name.
     */
    private String userName;

    /**
     * The Password.
     */
    private String password;

    /**
     * The Email.
     */
    private String email;

    /**
     * The User role.
     */
    private UserRole userRole;

    //    Note getters and setters start from here

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId( ) {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    @XmlElement( name = "userId" )
    public void setUserId( Long userId ) {
        this.userId = userId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName( ) {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    @XmlElement( name = "userName" )
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
     * @param password the password
     */
    @XmlElement( name = "password" )
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
     * @param email the email
     */
    @XmlElement( name = "email" )
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    @XmlElement( name = "user_role" )
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
                Objects.equals( getEmail( ), user.getEmail( ) ) &&
                Objects.equals( getUserRole( ), user.getUserRole( ) );
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
                ", userRole=" + userRole +
                '}';
    }
}
