package com.mana.innovative.domain.common;

import com.mana.innovative.domain.consumer.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 11:14 AM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "phones" )
public class Phone {

    @Id
    @Column( name = "phone_id", nullable = false )
    private String phoneId;

    @Column( name = "phone_number", nullable = false )
    private String phoneNumber;

    @Column( name = "phone_carrier", nullable = true )
    private String phoneCarrier;

    @Column( name = "phone_model", nullable = true )
    private String phoneModel;

    @Column( name = "phone_type", nullable = true )
    private String phoneType;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "customer_id" )
    private Customer customerPhone;

    //    private PhoneTechnicalDetail phoneTechnicalDetail;
    public String getPhoneId( ) {
        return phoneId;
    }

    public void setPhoneId( final String customerId ) {
        this.phoneId = customerId;
    }

    public String getPhoneNumber( ) {
        return phoneNumber;
    }

    public void setPhoneNumber( final String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCarrier( ) {
        return phoneCarrier;
    }

    public void setPhoneCarrier( final String phoneCarrier ) {
        this.phoneCarrier = phoneCarrier;
    }

    public String getPhoneModel( ) {
        return phoneModel;
    }

    public void setPhoneModel( final String phoneModel ) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneType( ) {
        return phoneType;
    }

    public void setPhoneType( final String phoneType ) {
        this.phoneType = phoneType;
    }

    public Customer getCustomerPhone( ) {
        return customerPhone;
    }

    public void setCustomerPhone( final Customer customerPhone ) {
        this.customerPhone = customerPhone;
    }
}
