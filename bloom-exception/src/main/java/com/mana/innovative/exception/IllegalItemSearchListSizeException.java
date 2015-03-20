package com.mana.innovative.exception;

import com.mana.innovative.constants.DAOConstants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright Date: 10/2/12 Time: 3:42 PM
 * @since: jdk 1.7
 */
public class IllegalItemSearchListSizeException extends RuntimeException {

    private String message = "Size of Search String keywords cannot be Greater than "
            + DAOConstants.THREE + " OR Less " + "Than " + DAOConstants.ONE;

    public IllegalItemSearchListSizeException( ) {
        super( );
    }

    public IllegalItemSearchListSizeException( String message ) {

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
