package com.mana.innovative.exception.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Error container.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "error_container" )
public class ErrorContainer {

    private Error currentError;
    private List< Error > errors;

    /**
     * Instantiates a new Error container.
     */
    public ErrorContainer( ) {

        this.instantiateErrors( );
    }

    /**
     * Instantiates a new Error container.
     *
     * @param currentError the current error
     */
    public ErrorContainer( final Error currentError ) {

        this.instantiateErrors( );
        this.setCurrentError( currentError );
    }

    private void instantiateErrors( ) {
        errors = new ArrayList<>( );
    }

    /**
     * Gets current error.
     *
     * @return the current error
     */
    public Error getCurrentError( ) {
        return currentError;
    }

    /**
     * Sets current error.
     *
     * @param currentError the current error
     */
    @XmlTransient
    public void setCurrentError( final Error currentError ) {
        this.currentError = currentError;
        this.addError( currentError );
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List< Error > getErrors( ) {
        return errors;
    }

    /**
     * Sets errors.
     *
     * @param errors the errors
     */
    @XmlElementWrapper( name = "errors" )
    @XmlElement
    public void setErrors( final List< Error > errors ) {
        this.errors = errors;
    }

    /**
     * This method is for adding a new currentError to the errors list object. To set the current currentError object
     * use setCurrentError
     *
     * @param error the error
     */
    public void addError( final Error error ) {
        this.errors.add( error );
    }
}
