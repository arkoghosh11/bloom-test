package com.mana.innovative.exception;

/**
 * The type Item size exceeded exception.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ItemSizeExceededException extends Exception {

    private String message = "ItemSizeExceededException occurred ";

    public ItemSizeExceededException( ) {
        super( );
    }

    public ItemSizeExceededException( String message ) {

        super( message );
        this.message = message + this.message;
    }

    public String getError( ) {
        return message;
    }

    public String toString( ) {
        return message;
    }
}