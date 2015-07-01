package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Objects;

/**
 * The type Person.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "person" )
@XmlSeeAlso( { User.class } )
public class Person extends User {

    /**
     * The Title.
     */
    private String title;

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */
    private String lastName;

    /**
     * The Middle name.
     */
    private String middleName;

    //    Note getters and setters start from here

    /**
     * Gets first name.
     *
     * @return the first name
     */
    @XmlElement( name = "firstName" )
    public String getFirstName( ) {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    @XmlElement( name = "lastName" )
    public String getLastName( ) {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Gets middle name.
     *
     * @return the middle name
     */
    @XmlElement( name = "middleName" )
    public String getMiddleName( ) {
        return middleName;
    }

    /**
     * Sets middle name.
     *
     * @param middleName the middle name
     */
    public void setMiddleName( final String middleName ) {
        this.middleName = middleName;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    @XmlElement( name = "title" )
    public String getTitle( ) {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle( final String title ) {
        this.title = title;
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
        if ( !( o instanceof Person ) ) return false;
        if ( !super.equals( o ) ) return false;
        Person person = ( Person ) o;
        return Objects.equals( getTitle( ), person.getTitle( ) ) &&
                Objects.equals( getFirstName( ), person.getFirstName( ) ) &&
                Objects.equals( getLastName( ), person.getLastName( ) ) &&
                Objects.equals( getMiddleName( ), person.getMiddleName( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Person{" +
                "title='" + title +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", middleName='" + middleName +
                "} " + super.toString( );
    }

}
