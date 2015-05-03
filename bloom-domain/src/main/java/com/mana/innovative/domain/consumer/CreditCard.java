package com.mana.innovative.domain.consumer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/19/12 Time: 4:43 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
@DiscriminatorValue( value = "CreditCard" )
public class CreditCard extends Card {

    /**
     * The Middle name.
     */
    @Column( name = "middle_name" )
    private String middleName;

    /**
     * The Card type.
     */
    @Column( name = "card_type" )
    private String cardType;

    /**
     * The CVV.
     */
    @Column( name = "cvv_code" )
    private int CVV;

    @Column( name = "expiry_date" )
    private String expiryDate;

    /**
     * The Create or modified.
     */
    @Column( name = "modification" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createOrModified;

    /**
     * The Customer card.
     */
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "customer_id", nullable = false )
    private Customer customerCard;

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
     * Gets cVV.
     *
     * @return the cVV
     */
    public int getCVV( ) {
        return CVV;
    }

    /**
     * Sets cVV.
     *
     * @param CVV the cVV
     */
    public void setCVV( final int CVV ) {
        this.CVV = CVV;
    }

    /**
     * Gets card type.
     *
     * @return the card type
     */
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

    public String getExpiryDate( ) {
        return expiryDate;
    }

    public void setExpiryDate( final String expiryDate ) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets create or modified.
     *
     * @return the create or modified
     */
    public Date getCreateOrModified( ) {
        return createOrModified;
    }

    /**
     * Sets create or modified.
     *
     * @param createOrModified the create or modified
     */
    public void setCreateOrModified( final Date createOrModified ) {
        this.createOrModified = createOrModified;
    }

    /**
     * Gets customer card.
     *
     * @return the customer card
     */
    public Customer getCustomerCard( ) {
        return customerCard;
    }

    /**
     * Sets customer card.
     *
     * @param customerCard the customer card
     */
    public void setCustomerCard( final Customer customerCard ) {
        this.customerCard = customerCard;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof CreditCard ) ) return false;
        if ( !super.equals( o ) ) return false;
        CreditCard that = ( CreditCard ) o;
        return Objects.equals( getCVV( ), that.getCVV( ) ) &&
                Objects.equals( getMiddleName( ), that.getMiddleName( ) ) &&
                Objects.equals( getCardNumber( ), that.getCardNumber( ) ) &&
                Objects.equals( getCardType( ), that.getCardType( ) ) &&
                Objects.equals( getExpiryDate( ), that.getExpiryDate( ) ) &&
                Objects.equals( getCreateOrModified( ), that.getCreateOrModified( ) );
    }

    @Override
    public String toString( ) {
        return super.toString( ) + " CreditCard{" +
                "middleName=" + middleName +
                ", cardType=" + cardType +
                ", CVV=" + CVV +
                ", expiryDate=" + expiryDate +
                ", createOrModified=" + createOrModified +
                '}';
    }
}
