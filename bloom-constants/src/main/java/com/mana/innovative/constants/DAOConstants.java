package com.mana.innovative.constants;

/**
 * Created by alex1 on 1/20/2015.
 * This is a domain class
 */
public final class DAOConstants {

    public static final int ONE = 1;
    public static final int ZERO = 0;
    public static final int THREE = 3;
    public static final int TWQ = 2;

    public static final String ASC = "asc";
    public static final String DESC = "desc";


    public static final int DEFAULT_PAGE_LIMIT = 25;
    public static final String STRING_ZERO = "0";
    public static final String DEFAULT_STRING_PAGE_LIMIT = "25";
    public static final String FALSE = "false";
    public static final String HASH = "#";
    public static final boolean IS_ERROR = false;


    public static String toStringFromNumber(final int number) {

        Integer integer = number;
        return integer.toString();
    }

    public static Integer toNumberFromString (final String number) {

        return Integer.parseInt(number);
    }
}
