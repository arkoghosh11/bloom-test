package com.mana.innovative.dto.consumer;


import com.mana.innovative.dto.common.Address;
import com.mana.innovative.dto.common.Phone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * The type Customer.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
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
    private Address shippingAddress;

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
    @XmlElement( name = "address" )
    public Address getShippingAddress( ) {
        return shippingAddress;
    }

    /**
     * Sets shipping address.
     *
     * @param shippingAddress the shipping address
     */
    public void setShippingAddress( final Address shippingAddress ) {
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
}
