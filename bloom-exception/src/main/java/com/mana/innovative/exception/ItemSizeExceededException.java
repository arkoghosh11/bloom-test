package com.mana.innovative.exception;

/**
 * The type Item size exceeded exception.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ItemSizeExceededException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 7088044909373562430L;
    /**
     * The Message.
     */
    private String message = "ItemSizeExceededException occurred ";

    /**
     * Instantiates a new Item size exceeded exception.
     */
    public ItemSizeExceededException( ) {

        super( );
    }

    /**
     * Instantiates a new Item size exceeded exception.
     *
     * @param message the message
     */
    public ItemSizeExceededException( String message ) {

        super( message );
        this.message = message + this.message;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError( ) {

        return message;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {

        return message;
    }
}