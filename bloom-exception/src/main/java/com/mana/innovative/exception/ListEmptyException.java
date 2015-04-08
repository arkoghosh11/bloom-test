package com.mana.innovative.exception;

/**
 * Created by Bloom/Rono on 4/14/2015. This class is ListEmptyException
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ListEmptyException extends RuntimeException {

//    private static final Logger logger = Logger.getLogger( ListEmptyException.class );

    private String message = "   \nEmpty List or invoked an incorrect time ";

    public ListEmptyException( ) {
        super( );
    }

    public ListEmptyException( String message ) {

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
