package com.mana.innovative.exception.response;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alex1 on 1/28/2015.
 * This is a domain class
 */
public class WhenErrorOccursTestError {

    private static final Logger logger =  Logger.getLogger(WhenErrorOccursTestError.class);

    private Error error;
    private Exception classCastException;

    @Before
    public void setUp() {

        error = new Error();
        classCastException = new ClassCastException();
        error.setErrorMessage(classCastException.getMessage());
        error.setErrorType(classCastException.getClass().getCanonicalName());
        error.setErrorData(classCastException);
        error.setErrorLocation(classCastException.getLocalizedMessage());
    }


    @Test
    public void testGetError() {

        Assert.assertNotNull(classCastException);
        // Test LocalMsg , Msg, Error Object, ErrorType(Error Class Type)
        Assert.assertEquals(classCastException.getMessage(), error.getErrorMessage());
        Assert.assertEquals(classCastException.getLocalizedMessage(),error.getErrorLocation());
        Assert.assertEquals(classCastException,error.getErrorData());
        Assert.assertEquals(classCastException.getClass().getCanonicalName(),error.getErrorType());

    }
}
