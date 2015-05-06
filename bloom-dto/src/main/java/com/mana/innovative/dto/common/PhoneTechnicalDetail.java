package com.mana.innovative.dto.common;

import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/22/12 Time: 4:52 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
public class PhoneTechnicalDetail {

    /**
     * The Phone detail id.
     */
    private Long phoneDetailId;
    /**
     * The Phone company.
     */
    private String phoneCompany;
    /**
     * The IMEI number.
     */
    private String IMEINumber;
    /**
     * The Model.
     */
    private String model;
    /**
     * The Model number.
     */
    private String modelNumber;
    /**
     * The Price.
     */
    private String price;
    /**
     * The Currency.
     */
    private Currency currency;


//        currency = Currency.getInstance( Locale.getDefault() );

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public Currency getCurrency( ) {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency( final Currency currency ) {

        if ( currency == null ) {
            this.currency = Currency.getInstance( Locale.getDefault( ) );
        } else
            this.currency = currency;
    }

    /**
     * Gets phone detail id.
     *
     * @return the phone detail id
     */
    public Long getPhoneDetailId( ) {
        return phoneDetailId;
    }

    /**
     * Sets phone detail id.
     *
     * @param phoneDetailId the phone detail id
     */
    public void setPhoneDetailId( final Long phoneDetailId ) {
        this.phoneDetailId = phoneDetailId;
    }

    /**
     * Gets phone company.
     *
     * @return the phone company
     */
    public String getPhoneCompany( ) {
        return phoneCompany;
    }

    /**
     * Sets phone company.
     *
     * @param phoneCompany the phone company
     */
    public void setPhoneCompany( final String phoneCompany ) {
        this.phoneCompany = phoneCompany;
    }

    /**
     * Gets iMEI number.
     *
     * @return the iMEI number
     */
    public String getIMEINumber( ) {
        return IMEINumber;
    }

    /**
     * Sets iMEI number.
     *
     * @param IMEINumber the iMEI number
     */
    public void setIMEINumber( final String IMEINumber ) {
        this.IMEINumber = IMEINumber;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel( ) {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel( final String model ) {
        this.model = model;
    }

    /**
     * Gets model number.
     *
     * @return the model number
     */
    public String getModelNumber( ) {
        return modelNumber;
    }

    /**
     * Sets model number.
     *
     * @param modelNumber the model number
     */
    public void setModelNumber( final String modelNumber ) {
        this.modelNumber = modelNumber;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public String getPrice( ) {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice( final String price ) {
        this.price = price;
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

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "PhoneTechnicalDetail {" +
                " phoneDetailId=" + phoneDetailId +
                ", phoneCompany='" + phoneCompany +
                ", IMEINumber='" + IMEINumber +
                ", model='" + model +
                ", modelNumber='" + modelNumber +
                ", price='" + price +
                ", currency=" + currency +
                '}';
    }
}
