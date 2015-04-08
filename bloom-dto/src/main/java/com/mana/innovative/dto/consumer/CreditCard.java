package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Date;

/**
 * The type Credit card.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "CreditCard" )
@XmlSeeAlso( { Card.class } )
public class CreditCard extends Card {

    /**
     * The Middle name.
     */
    private String middleName;

    /**
     * The Card number.
     */
    private String cardNumber;

    /**
     * The Expiry date.
     */
    private String expiryDate;

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
    private Customer customerCard;

    // Note getters and setters start from here

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
     * Gets card number.
     *
     * @return the card number
     */
    @XmlElement( name = "cardNumber" )
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
     * Gets expiry date.
     *
     * @return the expiry date
     */
    @XmlElement( name = "expiryDate" )
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
     * Gets cVV.
     *
     * @return the cVV
     */
    @XmlElement( name = "CVV" )
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

}
