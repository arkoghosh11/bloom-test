package com.mana.innovative.domain.consumer;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 3:41 PM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "cards" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "Card" )
public class Card {

    @Id
    @Column( name = "card_id" )
    private String cardId;

    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "card_type" )
    private String cardType;

    @Column( name = "issue_date" )
    private String issueDate;

    @Column( name = "has_picture" )
    private boolean cardHasCustomerPic;

    public String getCardId( ) {
        return cardId;
    }

    public void setCardId( final String cardId ) {
        this.cardId = cardId;
    }


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

    public String getCardType( ) {
        return cardType;
    }

    public void setCardType( final String cardType ) {
        this.cardType = cardType;
    }

    public String getIssueDate( ) {
        return issueDate;
    }

    public void setIssueDate( final String issueDate ) {
        this.issueDate = issueDate;
    }

    public boolean isCardHasCustomerPic( ) {
        return cardHasCustomerPic;
    }

    public void setCardHasCustomerPic( final boolean cardHasCustomerPic ) {
        this.cardHasCustomerPic = cardHasCustomerPic;
    }
}
