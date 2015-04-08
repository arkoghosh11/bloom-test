package com.mana.innovative.domain.consumer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/19/12 Time: 4:43 PM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "cards" )
@DiscriminatorValue( value = "CreditCard" )
public class CreditCard extends Card {

    @Column( name = "middle_name" )
    private String middleName;

    @Column( name = "card_number" )
    private String cardNumber;

    @Column( name = "exp_date" )
    private String expiryDate;

    @Column( name = "code" )
    private String CVV;

    @Column( name = "modification" )
    private Date createOrModified = new Date( );

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "customer_id" )
    private Customer customerCard;

    public String getMiddleName( ) {
        return middleName;
    }

    public void setMiddleName( final String middleName ) {
        this.middleName = middleName;
    }

    public String getCardNumber( ) {
        return cardNumber;
    }

    public void setCardNumber( final String cardNumber ) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate( ) {
        return expiryDate;
    }

    public void setExpiryDate( final String expiryDate ) {
        this.expiryDate = expiryDate;
    }

    public String getCVV( ) {
        return CVV;
    }

    public void setCVV( final String CVV ) {
        this.CVV = CVV;
    }

    public Date getCreateOrModified( ) {
        return createOrModified;
    }

    public void setCreateOrModified( final Date createOrModified ) {
        this.createOrModified = createOrModified;
    }

    public Customer getCustomerCard( ) {
        return customerCard;
    }

    public void setCustomerCard( final Customer customerCard ) {
        this.customerCard = customerCard;
    }
}
