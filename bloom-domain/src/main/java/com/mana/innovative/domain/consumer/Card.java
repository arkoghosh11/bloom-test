package com.mana.innovative.domain.consumer;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 3:41 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
@Table( name = "cards" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "Card" )
public class Card {

    /**
     * The Card id.
     */
    @Id
    @Column( name = "card_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long cardId;

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
     * The Card number.
     */
    @Column( name = "card_number", unique = true )
    private String cardNumber;

    /**
     * The Issue date.
     */
    @Column( name = "issue_date" )
    private String issueDate;

    /**
     * The Card has customer pic.
     */
    @Column( name = "has_picture" )
    private boolean cardHasCustomerPic;


    @Column( name = "picture_location", nullable = true )
    private String pictureLocation;

    /**
     * The Created date.
     */
    @Column( name = "created_date", updatable = false )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;

    /**
     * The Updated date.
     */
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;


    /**
     * Gets card id.
     *
     * @return the card id
     */
    public long getCardId( ) {
        return cardId;
    }

    /**
     * Sets card id.
     *
     * @param cardId the card id
     */
    public void setCardId( final long cardId ) {
        this.cardId = cardId;
    }


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
     * Gets card number.
     *
     * @return the card number
     */
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

    public String getPictureLocation( ) {
        return pictureLocation;
    }

    public void setPictureLocation( final String pictureLocation ) {
        this.pictureLocation = pictureLocation;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate( ) {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals( final Object o ) {

        if ( this == o ) return true;
        if ( !( o instanceof Card ) ) return false;
        Card card = ( Card ) o;
        return Objects.equals( getCardId( ), card.getCardId( ) ) &&
                Objects.equals( isCardHasCustomerPic( ), card.isCardHasCustomerPic( ) ) &&
                Objects.equals( getFirstName( ), card.getFirstName( ) ) &&
                Objects.equals( getLastName( ), card.getLastName( ) ) &&
                Objects.equals( getIssueDate( ), card.getIssueDate( ) ) &&
                Objects.equals( getPictureLocation( ), card.getPictureLocation( ) );
    }

    @Override
    public String toString( ) {
        return "Card{" +
                "cardId=" + cardId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", cardHasCustomerPic=" + cardHasCustomerPic +
                ", pictureLocation='" + pictureLocation + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
