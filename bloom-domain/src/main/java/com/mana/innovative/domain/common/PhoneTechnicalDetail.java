package com.mana.innovative.domain.common;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 4:52 PM
 * @since: jdk 1.7
 */
// Todo Hibernate/JPA config for PhoneTechnicalDetail
public class PhoneTechnicalDetail {

    private String phoneDetailId;
    private String phoneCompany;
    private String IMEINumber;
    private String model;
    private String modelNumber;
    private String price;
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
}
