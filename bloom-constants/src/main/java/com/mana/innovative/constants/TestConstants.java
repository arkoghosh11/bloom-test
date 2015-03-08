package com.mana.innovative.constants;

/**
 * Created by alex1 on 1/28/2015.
 * This is a domain class
 */
public class TestConstants {

    public static final int MINUS_ONE = -1;
    public static final int ONE = 1;
    public static final int ZERO = 0;
    public static final int THREE = 3;
    public static final int TWO = 2;
    public static final int TEST_ZIPCODE = 99999;


    public static final double DEFAULT_PRICE = ZERO;
    public static final double DEFAULT_QUANTITY = ZERO;
    public static final double DEFAULT_WEIGHT = ZERO;
    public static final String DEFAULT_ITEM_NAME = "default_name";
    public static final String DEFAULT_ITEM_PRICE_CURRENCY = "Dollar";
    public static final String DEFAULT_ITEM_TYPE = "default_type";
    public static final String DEFAULT_ITEM_SUB_TYPE = DEFAULT_ITEM_TYPE;
    public static final String DEFAULT_BROUGHT_FROM = "default";

    public static final double TEST_PRICE = TWO;
    public static final double TEST_QUANTITY = ONE;
    public static final double TEST_WEIGHT = ONE;
    public static final String TEST = "test";
    public static final String TEST_VALUE = "test@testemail.com";
    public static final String TEST_PRICE_CURRENCY = "Rupee";
    public static final String TEST_ITEM_TYPE = TEST;
    public static final String TEST_BROUGHT_FROM = TEST;

    public static final String UPDATED_TEST_VALUE = "updated_test@testemail.com";
    public static final double UPDATED_ITEM_PRICE = 3;

    public static final boolean IS_ERROR = false;
    public static final boolean IS_ERROR_TRUE = true;
    public static final String FAIL_MESSAGE = "Failed due to expected and actual results being different";
    public static final long TEST_OWN_ID = 1111L;
    public static final String TEST_NAME = TEST;
    public static final String TEST_WEB_LINK = TEST;
    public static final String TEST_DISTRICT = "test_district";
    public static final String TEST_STATE = "test_state";
    public static final String TEST_CITY = "test_city";
    public static final String TEST_START_TIME = "00:00";
    public static final String TEST_END_TIME = "11:59";
    public static final String TEST_DAY = TEST;
    public static final long TEST_ID = ONE;
    public static final String TEST_TIME_FORMAT = "HH:mm";
    public static final String TEST_BOUGHT_DATE = "2015-01-23 17:22:12.000";
    public static final boolean TEST_TRUE = true;
    public static final boolean TEST_FALSE = false;
    public static final boolean TEST_IS_HOLIDAY = TEST_TRUE;
    public static final boolean TEST_IS_WEEKEND = TEST_TRUE;
    public static final boolean TEST_IS_CLOSED = TEST_TRUE;
    public static final boolean IS_DELETE_ALL = TEST_FALSE;
    public static final boolean IS_DELETE_ALL_TRUE = TEST_TRUE;
    // todo is to import these values from a properties file
    public static String setUpMethodLoggerMsg = "Initializing tests...";
    public static String tearDownMethodLoggerMsg = ".... Preparing to shutdown test";

    public static String notNullMessage = "Actual specimen is not null";
    public static String nullMessage = "Actual specimen is null";
    public static String trueMessage = "Actual specimen is true";
    public static String falseMessage = "Actual specimen is false";
    public static String sameMessage = "Actual and expected specimen are same";
    public static String notSameMessage = "Actual and expected specimen are not same";
    public static String notEqualsMessage = "Both specimens are not equal";
    public static String equalErrorMessage = "Both specimens are equal";
}
