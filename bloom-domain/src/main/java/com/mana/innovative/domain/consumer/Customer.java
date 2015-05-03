package com.mana.innovative.domain.consumer;


import com.mana.innovative.domain.common.Address;
import com.mana.innovative.domain.common.Phone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "customer_address",
            inverseJoinColumns = { @JoinColumn( name = "address_id" ) },
            joinColumns = { @JoinColumn( name = "customer_id", referencedColumnName = "user_id" ) },
            uniqueConstraints = { @UniqueConstraint( name = "customer_address_id",
                    columnNames = { "address_id", "customer_id" } ) } )
    private List< Address > shippingAddress;

    @OneToMany( mappedBy = "customerPhone", cascade = CascadeType.ALL, orphanRemoval = true )
    private List< Phone > phones;

    @OneToMany( mappedBy = "customerCard", cascade = CascadeType.ALL, orphanRemoval = true )
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

    public List< Address > getShippingAddress( ) {
        return shippingAddress;
    }

    public void setShippingAddress( final List< Address > shippingAddress ) {
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

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Customer ) ) return false;
        if ( !super.equals( o ) ) return false;
        Customer customer = ( Customer ) o;
        return Objects.equals( getFirstName( ), customer.getFirstName( ) ) &&
                Objects.equals( getLastName( ), customer.getLastName( ) ) &&
                Objects.equals( getMiddleName( ), customer.getMiddleName( ) ) &&
                Objects.equals( getShippingAddress( ), customer.getShippingAddress( ) ) &&
                Objects.equals( getPhones( ), customer.getPhones( ) ) &&
                Objects.equals( getCards( ), customer.getCards( ) ) &&
                Objects.equals( getPreferences( ), customer.getPreferences( ) );
    }


    @Override
    public String toString( ) {
        return super.toString( ) + "Customer {" +
                " firstName= " + firstName +
                ", lastName= " + lastName +
                ", middleName= " + middleName +
                ", shippingAddress=" + shippingAddress +
                ", phones=" + phones +
                ", cards=" + cards +
                ", preferences=" + preferences +
                '}';
    }
}
