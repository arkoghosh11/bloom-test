/**
 * @author Bloom This Class User.java is for Created at Aug 28, 2012 4:05:17 PM
 */
package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type User.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "user", namespace = "http://localhost/rest/Bloom/user" )
public class User {

    /**
     * The User id.
     */
    private String userId;

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

    //    Note getters and setters start from here

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId( ) {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    @XmlElement( name = "userId" )
    public void setUserId( String userId ) {
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
     * To string.
     *
     * @return the string
     */
    public String toString( ) {
        return this.userId + " " + this.userName + " " + this.password + " " + this.email + "";
    }
}
