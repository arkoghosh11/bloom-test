package com.mana.innovative.service.container;

import com.mana.innovative.exception.response.ErrorContainer;

import javax.xml.bind.annotation.XmlElement;

/**
 * The type Response container.
 *
 * @param <T> the type parameter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ResponseContainer < T > {

    private int count;

    private T payload;
    private ErrorContainer errorContainer;
    private boolean isError;

    @XmlElement( name = "count", namespace = "http://localhost:8080/Bloom/rest/" )
    public int getCount( ) {
        return count;
    }

    public void setCount( int count ) {
        this.count = count;
    }

    @XmlElement( name = "payload", namespace = "http://localhost:8080/Bloom/rest/" )
    public T getPayload( ) {
        return payload;
    }

    public void setPayload( T payload ) {
        this.payload = payload;
    }

    @XmlElement( name = "error_container", namespace = "http://localhost:8080/Bloom/rest/" )
    public ErrorContainer getErrorContainer( ) {
        return errorContainer;
    }

    public void setErrorContainer( ErrorContainer errorContainer ) {
        this.errorContainer = errorContainer;
    }

    /**
     * Is error.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_error", defaultValue = "false", nillable = false )
    public boolean isError( ) {
        return isError;
    }

    /**
     * Sets is error.
     *
     * @param isError the is error
     */
    public void setIsError( final boolean isError ) {
        this.isError = isError;
    }
}
