package com.mana.innovative.constants;

/**
 * The type DAO constants.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
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
    /**
     * The constant TIME_SEPARATOR_FORMAT.
     */
    public static final String TIME_SEPARATOR_FORMAT = ":";

    /**
     * The constant DEFAULT_DATE_FORMAT.
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * The constant DEFAULT_DATE_LIMIT_FORMAT.
     */
    public static final String DEFAULT_DATE_LIMIT_FORMAT = "yy-MM-dd";
    /**
     * The constant DEFAULT_DATE_RETURN_FORMAT.
     */
    public static final String DEFAULT_DATE_RETURN_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * The constant EMPTY_STRING.
     */
    public static final String EMPTY_STRING = "";
    /**
     * The constant DEFAULT_PRIVILEGE.
     */
    public static final int DEFAULT_PRIVILEGE = 3;
    /**
     * The constant DEFAULT_USER_ROLE.
     */
    public static final int DEFAULT_USER_ROLE = 1;
    /**
     * The constant DEFAULT_USER_ROLE_NAME.
     */
    public static final String DEFAULT_USER_ROLE_NAME = "anonymous";
    /**
     * The constant DEFAULT_GEM_DESCRIPTION.
     */
    public static final String DEFAULT_GEM_DESCRIPTION = "Not Available";
    public static final String GEMSTONE_CLASS_IN_STRING = "gemstone";
    public static final String ITEM_IMAGE_CLASS_IN_STRING = "itemimage";
    public static final String ITEM_DISCOUNT_CLASS_IN_STRING = "itemdiscount";
    public static final String STRING = "string";
    public static final String LIST_STRING = "List";
    public static final String LONG = "long";
    public static final String DOUBLE = "double";


    /**
     * To string from number.
     *
     * @param number the number
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
     * @return the integer
     */
    public static Integer toNumberFromString( final String number ) {

        return Integer.parseInt( number );
    }
}
