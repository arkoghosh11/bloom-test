package com.mana.innovative.exception;

/**
 * Created by alex1 on 1/28/2015.
 * This is a domain class
 */
public class ItemSizeExceededException extends Exception {

    private String message = "ItemSizeExceededException occurred ";

    public ItemSizeExceededException () {
        super();
    }

    public ItemSizeExceededException ( String message ) {

        super( message );
        this.message = message + this.message;
    }

    public String getError() {
        return message;
    }

    public String toString () {
        return message;
    }
}