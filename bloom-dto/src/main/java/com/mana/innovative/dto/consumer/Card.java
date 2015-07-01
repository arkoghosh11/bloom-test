package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The type Card.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "card", namespace = "http://localhost/rest/Bloom/card" )
public class Card {

    /**
     * The Card id.
     */
    private Long cardId;

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */
    private String lastName;

    /**
     * The Card number.
     */
    private String cardNumber;

    /**
     * The Issue date.
     */
    private String issueDate;

    /**
     * The Picture location.
     */
    private String pictureLocation;

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
    @XmlElement( name = "card_id" )
    public Long getCardId( ) {
        return cardId;
    }

    /**
     * Sets card id.
     *
     * @param cardId the card id
     */
    public void setCardId( final Long cardId ) {
        this.cardId = cardId;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    @XmlElement( name = "first_name" )
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
    @XmlElement( name = "last_name" )
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
     * Gets card number.
     *
     * @return the card number
     */
    @XmlElement( name = "card_number" )
    public String getCardNumber( ) {
        return cardNumber;
    }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber( final String cardNumber ) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets issue date.
     *
     * @return the issue date
     */
    @XmlElement( name = "issue_date" )
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
     * Gets picture location.
     *
     * @return the picture location
     */
    @XmlElement( name = "picture_location" )
    public String getPictureLocation( ) {
        return pictureLocation;
    }

    /**
     * Sets picture location.
     *
     * @param pictureLocation the picture location
     */
    public void setPictureLocation( String pictureLocation ) {
        this.pictureLocation = pictureLocation;
    }

    /**
     * Is card has customer pic.
     *
     * @return the boolean
     */
    @XmlElement( name = "has_picture" )
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

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Card ) ) return false;
        Card card = ( Card ) o;
        return Objects.equals( isCardHasCustomerPic( ), card.isCardHasCustomerPic( ) ) &&
                Objects.equals( getCardId( ), card.getCardId( ) ) &&
                Objects.equals( getFirstName( ), card.getFirstName( ) ) &&
                Objects.equals( getLastName( ), card.getLastName( ) ) &&
                Objects.equals( getCardNumber( ), card.getCardNumber( ) ) &&
                Objects.equals( getIssueDate( ), card.getIssueDate( ) ) &&
                Objects.equals( getPictureLocation( ), card.getPictureLocation( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Card {" +
                " cardId=" + cardId +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", cardNumber='" + cardNumber +
                ", issueDate='" + issueDate +
                ", pictureLocation='" + pictureLocation +
                ", cardHasCustomerPic=" + cardHasCustomerPic +
                '}';
    }
}
