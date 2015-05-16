package com.mana.innovative.dto.common;

import com.mana.innovative.dto.adapter.DateFormatAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.Objects;

/**
 * The type Phone. Created by Bloom/Rono on Date: 10/22/12 Time: 11:14 AM
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "phone" )
public class Phone {

    /**
     * The Phone id.
     */
    private Long phoneId;

    /**
     * The Phone name.
     */
    private String phoneName;

    /**
     * The Phone number.
     */
    private String phoneNumber;

    /**
     * The Phone carrier.
     */
    private String phoneCarrier;

    /**
     * The Phone model.
     */
    private String phoneModel;

    /**
     * The Phone type.
     */
    private String phoneType;

    /**
     * The Bought date.
     */
    private Date boughDate;

    //    Note getters and setters start from here

    //    private PhoneTechnicalDetail phoneTechnicalDetail;

    /**
     * Gets phone id.
     *
     * @return the phone id
     */
    @XmlElement( name = "phoneId" )
    public Long getPhoneId( ) {
        return phoneId;
    }

    /**
     * Sets phone id.
     *
     * @param customerId the customer id
     */
    public void setPhoneId( final Long customerId ) {
        this.phoneId = customerId;
    }

    /**
     * Gets phone name.
     *
     * @return the phone name
     */
    @XmlElement( name = "phone_name" )
    public String getPhoneName( ) {
        return phoneName;
    }

    /**
     * Sets phone name.
     *
     * @param phoneName the phone name
     */
    public void setPhoneName( final String phoneName ) {
        this.phoneName = phoneName;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    @XmlElement( name = "phone_number" )
    public String getPhoneNumber( ) {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber( final String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone carrier.
     *
     * @return the phone carrier
     */
    @XmlElement( name = "phone_carrier" )
    public String getPhoneCarrier( ) {
        return phoneCarrier;
    }

    /**
     * Sets phone carrier.
     *
     * @param phoneCarrier the phone carrier
     */
    public void setPhoneCarrier( final String phoneCarrier ) {
        this.phoneCarrier = phoneCarrier;
    }

    /**
     * Gets phone model.
     *
     * @return the phone model
     */
    @XmlElement( name = "phone_model" )
    public String getPhoneModel( ) {
        return phoneModel;
    }

    /**
     * Sets phone model.
     *
     * @param phoneModel the phone model
     */
    public void setPhoneModel( final String phoneModel ) {
        this.phoneModel = phoneModel;
    }

    /**
     * Gets phone type.
     *
     * @return the phone type
     */
    @XmlElement( name = "phone_type" )
    public String getPhoneType( ) {
        return phoneType;
    }

    /**
     * Sets phone type.
     *
     * @param phoneType the phone type
     */
    public void setPhoneType( final String phoneType ) {
        this.phoneType = phoneType;
    }

    /**
     * Gets bought date.
     *
     * @return the bought date
     */
    @XmlElement( name = "bought_date" )
    @XmlJavaTypeAdapter( value = DateFormatAdapter.class )
    public Date getBoughtDate( ) {
        return boughDate;
    }

    /**
     * Sets bought date.
     *
     * @param boughtDate the bought date
     */
    public void setBoughtDate( final Date boughtDate ) {
        this.boughDate = boughtDate;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Phone ) ) return false;
        Phone phone = ( Phone ) o;
        return Objects.equals( getPhoneId( ), phone.getPhoneId( ) ) &&
                Objects.equals( getPhoneName( ), phone.getPhoneName( ) ) &&
                Objects.equals( getPhoneNumber( ), phone.getPhoneNumber( ) ) &&
                Objects.equals( getPhoneCarrier( ), phone.getPhoneCarrier( ) ) &&
                Objects.equals( getPhoneModel( ), phone.getPhoneModel( ) ) &&
                Objects.equals( getPhoneType( ), phone.getPhoneType( ) ) &&
                Objects.equals( boughDate, phone.boughDate );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Phone {" +
                " phoneId=" + phoneId +
                ", phoneName='" + phoneName +
                ", phoneNumber='" + phoneNumber +
                ", phoneCarrier='" + phoneCarrier +
                ", phoneModel='" + phoneModel +
                ", phoneType='" + phoneType +
                ", boughDate=" + boughDate +
                '}';
    }
}
