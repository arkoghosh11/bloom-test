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
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
//@Table( name = "users" ) //@Note not req as super already has table name
@DiscriminatorValue( "Customer" )
public class Customer extends User {

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
    @Column( name = "middle_name" )
    private String middleName;

    /**
     * The Shipping address.
     */
    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "customer_address",
            inverseJoinColumns = { @JoinColumn( name = "address_id" ) },
            joinColumns = { @JoinColumn( name = "customer_id", referencedColumnName = "user_id" ) },
            uniqueConstraints = { @UniqueConstraint( name = "customer_address_id",
                    columnNames = { "address_id", "customer_id" } ) } )
    private List< Address > shippingAddress;

    /**
     * The Phones.
     */
    @OneToMany( mappedBy = "customerPhone", cascade = CascadeType.ALL, orphanRemoval = true )
    private List< Phone > phones;

    /**
     * The Cards.
     */
    @OneToMany( mappedBy = "customerCard", cascade = CascadeType.ALL, orphanRemoval = true )
    private List< CreditCard > cards;

    /**
     * The Preferences.
     */
    @ManyToMany( mappedBy = "customerPreferences", cascade = CascadeType.ALL )
    private List< Preference > preferences;


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
     * Gets shipping address.
     *
     * @return the shipping address
     */
    public List< Address > getShippingAddress( ) {
        return shippingAddress;
    }

    /**
     * Sets shipping address.
     *
     * @param shippingAddress the shipping address
     */
    public void setShippingAddress( final List< Address > shippingAddress ) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Gets phones.
     *
     * @return the phones
     */
    public List< Phone > getPhones( ) {
        return phones;
    }

    /**
     * Sets phones.
     *
     * @param phones the phones
     */
    public void setPhones( final List< Phone > phones ) {
        this.phones = phones;
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public List< CreditCard > getCards( ) {
        return cards;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards( final List< CreditCard > cards ) {
        this.cards = cards;
    }

    /**
     * Gets preferences.
     *
     * @return the preferences
     */
    public List< Preference > getPreferences( ) {
        return preferences;
    }

    /**
     * Sets preferences.
     *
     * @param preferences the preferences
     */
    public void setPreferences( final List< Preference > preferences ) {
        this.preferences = preferences;
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


    /**
     * To string.
     *
     * @return the string
     */
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
