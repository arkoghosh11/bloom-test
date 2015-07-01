package com.mana.innovative.dto.consumer;


import com.mana.innovative.dto.common.Address;
import com.mana.innovative.dto.common.Phone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;
import java.util.Objects;

/**
 * The type Customer.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "customer" )
@XmlSeeAlso( { User.class } )
public class Customer extends User {

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

    /**
     * The Shipping address.
     */
    private List< Address > shippingAddress;

    /**
     * The Phones.
     */
    private List< Phone > phones;

    /**
     * The Cards.
     */
    private List< CreditCard > cards;

    /**
     * The Preferences.
     */
    private List< Preference > preferences;

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
     * Gets shipping address.
     *
     * @return the shipping address
     */
    @XmlElement( name = "addresses" )
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
    @XmlElement( name = "phone" )
    @XmlElementWrapper( name = "phones" )
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
    @XmlElement( name = "card" )
    @XmlElementWrapper( name = "cards" )
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
    @XmlElement( name = "preference" )
    @XmlElementWrapper( name = "preferences" )
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
                " firstName='" + firstName +
                ",   lastName='" + lastName +
                ",   middleName='" + middleName +
                ",   shippingAddress=" + shippingAddress +
                ",   phones=" + phones +
                ",   cards=" + cards +
                ",   preferences=" + preferences +
                '}';
    }
}
