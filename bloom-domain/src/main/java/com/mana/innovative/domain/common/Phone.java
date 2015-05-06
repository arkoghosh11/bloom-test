package com.mana.innovative.domain.common;

import com.mana.innovative.domain.consumer.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 11:14 AM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
@Table( name = "phones" )
public class Phone {

    /**
     * The Phone id.
     */
    @Id
    @Column( name = "phone_id", nullable = false )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long phoneId;

    /**
     * The Phone name.
     */
    @Column( name = "phone_name", nullable = true )
    private String phoneName;

    /**
     * The Phone number.
     */
    @Column( name = "phone_number", nullable = false )
    private String phoneNumber;

    /**
     * The Phone carrier.
     */
    @Column( name = "phone_carrier", nullable = true )
    private String phoneCarrier;

    /**
     * The Phone model.
     */
    @Column( name = "phone_model", nullable = true )
    private String phoneModel;

    /**
     * The Phone type.
     */
    @Column( name = "phone_type", nullable = true )
    private String phoneType;

    /**
     * The Bought date.
     */
    @Column( name = "bought_date", nullable = true )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date boughtDate;

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
     * The Customer phone.
     */
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "customer_id" )
    private Customer customerPhone;
    //    private PhoneTechnicalDetail phoneTechnicalDetail;

    /**
     * Gets phone id.
     *
     * @return the phone id
     */
    public long getPhoneId( ) {
        return phoneId;
    }

    /**
     * Sets phone id.
     *
     * @param phoneId the customer id
     */
    public void setPhoneId( final long phoneId ) {
        this.phoneId = phoneId;
    }

    /**
     * Gets phone name.
     *
     * @return the phone name
     */
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
    public Date getBoughtDate( ) {
        return boughtDate;
    }

    /**
     * Sets bought date.
     *
     * @param boughtDate the bought date
     */
    public void setBoughtDate( final Date boughtDate ) {
        this.boughtDate = boughtDate;
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

    /**
     * Gets customer phone.
     *
     * @return the customer phone
     */
    public Customer getCustomerPhone( ) {
        return customerPhone;
    }

    /**
     * Sets customer phone.
     *
     * @param customerPhone the customer phone
     */
    public void setCustomerPhone( final Customer customerPhone ) {
        this.customerPhone = customerPhone;
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
                Objects.equals( getBoughtDate( ), phone.getBoughtDate( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", phoneName='" + phoneName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneCarrier='" + phoneCarrier + '\'' +
                ", phoneModel='" + phoneModel + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", boughtDate=" + boughtDate +
                ", customerPhone=" + customerPhone +
                '}';
    }
}
