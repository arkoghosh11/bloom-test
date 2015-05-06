package com.mana.innovative.service.container;

import com.mana.innovative.exception.response.ErrorContainer;

import javax.xml.bind.annotation.XmlElement;

/**
 * The type Response container.
 *
 * @param <T>  the type parameter
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ResponseContainer < T > {

    /**
     * The Count.
     */
    private int count;

    /**
     * The Payload.
     */
    private T payload;
    /**
     * The Error container.
     */
    private ErrorContainer errorContainer;
    /**
     * The Is error.
     */
    private boolean isError;

    /**
     * Gets count.
     *
     * @return the count
     */
    @XmlElement( name = "count", namespace = "http://localhost:8080/Bloom/rest/" )
    public int getCount( ) {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount( int count ) {
        this.count = count;
    }

    /**
     * Gets payload.
     *
     * @return the payload
     */
    @XmlElement( name = "payload", namespace = "http://localhost:8080/Bloom/rest/" )
    public T getPayload( ) {
        return payload;
    }

    /**
     * Sets payload.
     *
     * @param payload the payload
     */
    public void setPayload( T payload ) {
        this.payload = payload;
    }

    /**
     * Gets error container.
     *
     * @return the error container
     */
    @XmlElement( name = "error_container", namespace = "http://localhost:8080/Bloom/rest/" )
    public ErrorContainer getErrorContainer( ) {
        return errorContainer;
    }

    /**
     * Sets error container.
     *
     * @param errorContainer the error container
     */
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
