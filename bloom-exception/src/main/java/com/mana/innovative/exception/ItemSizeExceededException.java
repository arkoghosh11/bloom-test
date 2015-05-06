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
    public String toString( ) {
        return message;
    }
}