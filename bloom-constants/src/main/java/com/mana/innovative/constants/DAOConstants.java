package com.mana.innovative.constants;

/**
 * The type DAO constants.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public final class DAOConstants {

    /**
     * The constant ONE.
     */
    public static final int ONE = 1;
    /**
     * The constant ZERO.
     */
    public static final int ZERO = 0;
    /**
     * The constant THREE.
     */
    public static final int THREE = 3;
    /**
     * The constant TWQ.
     */
    public static final int TWQ = 2;

    /**
     * The constant ASC.
     */
    public static final String ASC = "asc";
    /**
     * The constant DESC.
     */
    public static final String DESC = "desc";


    /**
     * The constant DEFAULT_PAGE_LIMIT.
     */
    public static final int DEFAULT_PAGE_LIMIT = 25;
    /**
     * The constant STRING_ZERO.
     */
    public static final String STRING_ZERO = "0";
    /**
     * The constant DEFAULT_STRING_PAGE_LIMIT.
     */
    public static final String DEFAULT_STRING_PAGE_LIMIT = "25";
    /**
     * The constant FALSE.
     */
    public static final String FALSE = "false";
    /**
     * The constant HASH.
     */
    public static final String HASH = "#";
    /**
     * The constant IS_ERROR.
     */
    public static final boolean IS_ERROR = false;
    /**
     * The constant TIME_FORMAT.
     */
    public static final java.lang.String TIME_FORMAT = "HH:mm";
    public static final String TIME_SEPARATOR_FORMAT = ":";

    public static final String DEFAULT_DATE_LIMIT_FORMAT = "yy-MM-dd";
    public static final String DEFAULT_DATE_RETURN_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";


    /**
     * To string from number.
     *
     * @param number the number
     *
     * @return the string
     */
    public static String toStringFromNumber( final int number ) {

        Integer integer = number;
        return integer.toString( );
    }

    /**
     * To number from string.
     *
     * @param number the number
     *
     * @return the integer
     */
    public static Integer toNumberFromString( final String number ) {

        return Integer.parseInt( number );
    }
}
