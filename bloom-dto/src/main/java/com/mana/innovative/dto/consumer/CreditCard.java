package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Date;
import java.util.Objects;

/**
 * The type Credit card.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "credit_card" )
@XmlSeeAlso( { Card.class } )
public class CreditCard extends Card {

    /**
     * The Middle name.
     */
    private String middleName;

    /**
     * The Expiry date.
     */
    private String expiryDate;

    /**
     * The Card type.
     */
    private String cardType;

    /**
     * The CVV.
     */
    private String CVV;

    /**
     * The Create or modified.
     */
    private Date createOrModified = new Date( );

    /**
     * The Customer card.
     */
//    private Customer customerCard;

    // Note getters and setters start from here

    /**
     * Gets middle name.
     *
     * @return the middle name
     */
    @XmlElement( name = "middle_name" )
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
     * Gets expiry date.
     *
     * @return the expiry date
     */
    @XmlElement( name = "expiry_date" )
    public String getExpiryDate( ) {
        return expiryDate;
    }

    /**
     * Sets expiry date.
     *
     * @param expiryDate the expiry date
     */
    public void setExpiryDate( final String expiryDate ) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets card type.
     *
     * @return the card type
     */
    @XmlElement( name = "card_type" )
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
     * Gets cVV.
     *
     * @return the cVV
     */
    @XmlElement( name = "cvv" )
    public String getCVV( ) {
        return CVV;
    }

    /**
     * Sets cVV.
     *
     * @param CVV the cVV
     */
    public void setCVV( final String CVV ) {
        this.CVV = CVV;
    }

    /**
     * Gets create or modified.
     *
     * @return the create or modified
     */
    @XmlElement( name = "modification" )
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
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof CreditCard ) ) return false;
        CreditCard that = ( CreditCard ) o;
        return Objects.equals( getMiddleName( ), that.getMiddleName( ) ) &&
                Objects.equals( getExpiryDate( ), that.getExpiryDate( ) ) &&
                Objects.equals( getCardType( ), that.getCardType( ) ) &&
                Objects.equals( getCVV( ), that.getCVV( ) ) &&
                Objects.equals( getCreateOrModified( ), that.getCreateOrModified( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return super.toString( ) + "CreditCard {" +
                " cardType='" + cardType +
                ",   CVV='" + CVV +
                ",   createOrModified=" + createOrModified +
                ",   expiryDate='" + expiryDate +
                ",   middleName='" + middleName +
                '}';
    }
}
