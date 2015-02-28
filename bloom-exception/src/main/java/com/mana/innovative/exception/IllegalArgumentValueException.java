package com.mana.innovative.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom
 *         Date: 10/3/12
 *         Time: 11:14 AM
 * @since: jdk 1.7
 */
public class IllegalArgumentValueException extends RuntimeException {

    private String message = "   \nArgument value is Invalid or Null ";

    public IllegalArgumentValueException () {
        super();
    }

    public IllegalArgumentValueException ( String message ) {

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
