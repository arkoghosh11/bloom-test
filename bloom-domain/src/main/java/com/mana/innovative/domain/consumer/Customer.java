package com.mana.innovative.domain.consumer;


import com.mana.innovative.domain.common.Address;
import com.mana.innovative.domain.common.Phone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/19/12 Time: 4:42 PM
 * @since: jdk 1.7
 */
@Entity
//@Table( name = "users" ) //@Note not req as super already has table name
@DiscriminatorValue( "Customer" )
public class Customer extends User {

    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "middle_name" )
    private String middleName;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "address_id", referencedColumnName = "address_id" )
    private Address shippingAddress;

    @OneToMany( mappedBy = "customerPhone", cascade = CascadeType.ALL )
    private List< Phone > phones;

    @OneToMany( mappedBy = "customerCard", cascade = CascadeType.ALL )
    private List< CreditCard > cards;

    @ManyToMany( mappedBy = "customerPreferences", cascade = CascadeType.ALL )
    private List< Preference > preferences;


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

    public Address getShippingAddress( ) {
        return shippingAddress;
    }

    public void setShippingAddress( final Address shippingAddress ) {
        this.shippingAddress = shippingAddress;
    }

    public List< Phone > getPhones( ) {
        return phones;
    }

    public void setPhones( final List< Phone > phones ) {
        this.phones = phones;
    }

    public List< CreditCard > getCards( ) {
        return cards;
    }

    public void setCards( final List< CreditCard > cards ) {
        this.cards = cards;
    }

    public List< Preference > getPreferences( ) {
        return preferences;
    }

    public void setPreferences( final List< Preference > preferences ) {
        this.preferences = preferences;
    }
}
