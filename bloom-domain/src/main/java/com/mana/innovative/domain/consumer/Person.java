package com.mana.innovative.domain.consumer;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 12/10/12 Time: 1:36 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
//@Table( name = "users" ) //@Note not req as super already has table name
@DiscriminatorValue( "Person" )
public class Person extends User {

    /**
     * The Title.
     */
    @Column( name = "title", nullable = true )
    private String title;

    /**
     * The First name.
     */
    @Column( name = "first_name" )
    private String firstName;

    /**
     * The Last name.
     */
    @Column( name = "last_name" )
    private String lastName;

    /**
     * The Middle name.
     */
    @Column( name = "middle_name", nullable = true )
    private String middleName;

    /**
     * Gets first name.
     *
     * @return the first name
     */
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
        return super.toString( ) + "Person {" +
                "title= " + title +
                ",firstName= " + firstName +
                ",lastName= " + lastName +
                ",middleName= " + middleName +
                '}';
    }
}
