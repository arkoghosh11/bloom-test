package com.mana.innovative.exception.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The type Error.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "error" )
public class Error {

    /**
     * The Error type.
     */
    private String errorType;
    /**
     * The Error location.
     */
    private String errorLocation;
    /**
     * The Error message.
     */
    private String errorMessage;
    /**
     * The Error data.
     */
    private Object errorData;

    /**
     * Instantiates a new Error.
     */
    public Error( ) {
    }

    /**
     * Instantiates a new Error.
     *
     * @param errorLocation the error location
     */
    public Error( String errorLocation ) {

        this.errorLocation = errorLocation;
    }

    /**
     * Instantiates a new Error.
     *
     * @param errorLocation the error location
     * @param throwable     the throwable
     */
    public Error( String errorLocation, Throwable throwable ) {

        this.setErrorLocation( errorLocation );
        this.setErrorData( throwable );
        this.setErrorType( throwable.getClass( ).getCanonicalName( ) );
        this.setErrorMessage( throwable.getMessage( ) );
    }

    /**
     * Gets error type.
     *
     * @return the error type
     */
    @XmlElement( name = "error_type" )
    public String getErrorType( ) {
        return errorType;
    }

    /**
     * Sets error type.
     *
     * @param errorType the error type
     */
    public void setErrorType( final String errorType ) {
        this.errorType = errorType;
    }

    /**
     * Gets error location.
     *
     * @return the error location
     */
    @XmlElement( name = "error_location" )
    public String getErrorLocation( ) {
        return errorLocation;
    }

    /**
     * Sets error location.
     *
     * @param errorLocation the error location
     */
    public void setErrorLocation( final String errorLocation ) {
        this.errorLocation = errorLocation;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    @XmlElement( name = "error_message" )
    public String getErrorMessage( ) {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage( final String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets error data.
     *
     * @return the error data
     */
    @XmlTransient
    public Object getErrorData( ) {
        return errorData;
    }

    /**
     * Sets error data.
     *
     * @param errorData the error data
     */
    public void setErrorData( final Object errorData ) {
        this.errorData = errorData;
    }
}
