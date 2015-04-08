package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Card.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "card", namespace = "http://localhost/rest/Bloom/card" )
public class Card {

    /**
     * The Card id.
     */
    private String cardId;

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */
    private String lastName;

    /**
     * The Card type.
     */
    private String cardType;

    /**
     * The Issue date.
     */
    private String issueDate;

    /**
     * The Card has customer pic.
     */
    private boolean cardHasCustomerPic;

    //    Note getters and setters start from here

    /**
     * Gets card id.
     *
     * @return the card id
     */
    @XmlElement( name = "cardId" )
    public String getCardId( ) {
        return cardId;
    }

    /**
     * Sets card id.
     *
     * @param cardId the card id
     */
    public void setCardId( final String cardId ) {
        this.cardId = cardId;
    }

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
     * Gets card type.
     *
     * @return the card type
     */
    @XmlElement( name = "cardType" )
    public String getCardType( ) {
        return cardType;
    }

    /**
     * Sets card type.
     *
     * @param cardType the card type
     */
    public void setCardType( final String cardType ) {
        this.cardType = cardType;
    }

    /**
     * Gets issue date.
     *
     * @return the issue date
     */
    @XmlElement( name = "issueDate" )
    public String getIssueDate( ) {
        return issueDate;
    }

    /**
     * Sets issue date.
     *
     * @param issueDate the issue date
     */
    public void setIssueDate( final String issueDate ) {
        this.issueDate = issueDate;
    }

    /**
     * Is card has customer pic.
     *
     * @return the boolean
     */
    @XmlElement( name = "hasPicture" )
    public boolean isCardHasCustomerPic( ) {
        return cardHasCustomerPic;
    }

    /**
     * Sets card has customer pic.
     *
     * @param cardHasCustomerPic the card has customer pic
     */
    public void setCardHasCustomerPic( final boolean cardHasCustomerPic ) {
        this.cardHasCustomerPic = cardHasCustomerPic;
    }
}
