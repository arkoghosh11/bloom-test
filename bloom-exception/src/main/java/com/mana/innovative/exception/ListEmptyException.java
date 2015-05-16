package com.mana.innovative.exception;

/**
 * Created by Bloom/Rono on 4/14/2015. This class is ListEmptyException
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ListEmptyException extends RuntimeException {

    // private static final Logger logger = Logger.getLogger( ListEmptyException.class );

    /**
     *
     */
    private static final long serialVersionUID = -4617909591581505624L;
    /**
     * The Message.
     */
    private String message = "   \nEmpty List or invoked an incorrect time ";

    /**
     * Instantiates a new List empty exception.
     */
    public ListEmptyException( ) {

        super( );
    }

    /**
     * Instantiates a new List empty exception.
     *
     * @param message the message
     */
    public ListEmptyException( String message ) {

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
