package com.mana.innovative.dto.common;

import com.mana.innovative.dto.consumer.Customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 11:14 AM
 * @since: jdk 1.7
 */
@XmlRootElement( name = "phone" )
public class Phone {

    private String phoneId;

    private String phoneNumber;

    private String phoneCarrier;

    private String phoneModel;

    private String phoneType;

    private Customer customerPhone;

    //    Note getters and setters start from here

    //    private PhoneTechnicalDetail phoneTechnicalDetail;

    @XmlElement( name = "phoneId" )
    public String getPhoneId( ) {
        return phoneId;
    }

    public void setPhoneId( final String customerId ) {
        this.phoneId = customerId;
    }

    @XmlElement( name = "phoneNumber" )
    public String getPhoneNumber( ) {
        return phoneNumber;
    }

    public void setPhoneNumber( final String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement( name = "phoneCarrier" )
    public String getPhoneCarrier( ) {
        return phoneCarrier;
    }

    public void setPhoneCarrier( final String phoneCarrier ) {
        this.phoneCarrier = phoneCarrier;
    }

    @XmlElement( name = "phoneModel" )
    public String getPhoneModel( ) {
        return phoneModel;
    }

    public void setPhoneModel( final String phoneModel ) {
        this.phoneModel = phoneModel;
    }

    @XmlElement( name = "phoneType" )
    public String getPhoneType( ) {
        return phoneType;
    }

    public void setPhoneType( final String phoneType ) {
        this.phoneType = phoneType;
    }
}
