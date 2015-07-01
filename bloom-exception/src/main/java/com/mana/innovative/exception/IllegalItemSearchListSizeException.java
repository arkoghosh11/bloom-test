package com.mana.innovative.exception;

import com.mana.innovative.constants.DAOConstants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright Date : 10/2/12 Time: 3:42 PM
 * @since: jdk 1.7
 */
public class IllegalItemSearchListSizeException extends RuntimeException {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -1988171265095486516L;
    /**
     * The Message.
     */
    private String message = "Size of Search String keywords cannot be Greater than "
            + DAOConstants.THREE + " OR Less " + "Than "
            + DAOConstants.ONE;

    /**
     * Instantiates a new Illegal item search list size exception.
     */
    public IllegalItemSearchListSizeException( ) {

        super( );
    }

    /**
     * Instantiates a new Illegal item search list size exception.
     *
     * @param message the message
     */
    public IllegalItemSearchListSizeException( String message ) {

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
