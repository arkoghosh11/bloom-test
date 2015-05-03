package com.mana.innovative.domain.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 4:52 PM
 * @since: jdk 1.7
 */
// Todo Hibernate/JPA config for PhoneTechnicalDetail
public class PhoneTechnicalDetail {

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    @Column( name = "phone_detail_id", nullable = false )
    private String phoneDetailId;
    @Column( name = "phone_company" )
    private String phoneCompany;
    @Column( name = "imei_number", unique = true, nullable = false )
    private String IMEINumber;
    @Column( name = "model" )
    private String model;
    @Column( name = "model_number" )
    private String modelNumber;
    @Column( name = "price" )
    private String price;
    @Column( name = "currency" )
    private Currency currency;


//        currency = Currency.getInstance( Locale.getDefault() );

    public Currency getCurrency( ) {
        return currency;
    }

    public void setCurrency( final Currency currency ) {

        if ( currency == null ) {
            this.currency = Currency.getInstance( Locale.getDefault( ) );
        } else
            this.currency = currency;
    }

    public String getPhoneDetailId( ) {
        return phoneDetailId;
    }

    public void setPhoneDetailId( final String phoneDetailId ) {
        this.phoneDetailId = phoneDetailId;
    }

    public String getPhoneCompany( ) {
        return phoneCompany;
    }

    public void setPhoneCompany( final String phoneCompany ) {
        this.phoneCompany = phoneCompany;
    }

    public String getIMEINumber( ) {
        return IMEINumber;
    }

    public void setIMEINumber( final String IMEINumber ) {
        this.IMEINumber = IMEINumber;
    }

    public String getModel( ) {
        return model;
    }

    public void setModel( final String model ) {
        this.model = model;
    }

    public String getModelNumber( ) {
        return modelNumber;
    }

    public void setModelNumber( final String modelNumber ) {
        this.modelNumber = modelNumber;
    }

    public String getPrice( ) {
        return price;
    }

    public void setPrice( final String price ) {
        this.price = price;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof PhoneTechnicalDetail ) ) return false;
        PhoneTechnicalDetail that = ( PhoneTechnicalDetail ) o;
        return Objects.equals( getPhoneDetailId( ), that.getPhoneDetailId( ) ) &&
                Objects.equals( getPhoneCompany( ), that.getPhoneCompany( ) ) &&
                Objects.equals( getIMEINumber( ), that.getIMEINumber( ) ) &&
                Objects.equals( getModel( ), that.getModel( ) ) &&
                Objects.equals( getModelNumber( ), that.getModelNumber( ) ) &&
                Objects.equals( getPrice( ), that.getPrice( ) ) &&
                Objects.equals( getCurrency( ), that.getCurrency( ) );
    }

    @Override
    public String toString( ) {
        return "PhoneTechnicalDetail {" +
                " phoneDetailId= " + phoneDetailId +
                ", phoneCompany= " + phoneCompany +
                ", IMEINumber= " + IMEINumber +
                ", model= " + model +
                ", modelNumber= " + modelNumber +
                ", price= " + price +
                ", currency=" + currency +
                '}';
    }
}
