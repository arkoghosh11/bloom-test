package com.mana.innovative.domain.consumer;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 12/10/12 Time: 1:36 PM
 * @since: jdk 1.7
 */
@Entity
//@Table( name = "users" ) //@Note not req as super already has table name
@DiscriminatorValue( "Person" )
public class Person extends User {

    @Column( name = "title", nullable = true )
    private String title;

    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "middle_name", nullable = true )
    private String middleName;

    public String getFirstName( ) {
        return firstName;
    }

    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    }

    public String getMiddleName( ) {
        return middleName;
    }

    public void setMiddleName( final String middleName ) {
        this.middleName = middleName;
    }

    public String getTitle( ) {
        return title;
    }

    public void setTitle( final String title ) {
        this.title = title;
    }
}
