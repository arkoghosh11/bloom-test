package com.mana.innovative.exception.response;

import com.mana.innovative.constants.ErrorConstants;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alex1 on 1/28/2015.
 * This is a domain class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith(value = MockitoJUnitRunner.class)
public class WhenMockErrorTestSetErrorData {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenMockErrorTestSetErrorData.class );
    /**
     * The constant errorType.
     */
    private static final String errorType = ErrorConstants.TEST_ERROR_TYPE;
    /**
     * The Error.
     */
    private Error error;
    /**
     * The Invocation on mock.
     */
    private InvocationOnMock invocationOnMock;

    /**
     * Set up.
     */
    @Before
    public void setUp(){

        error = Mockito.mock(Error.class);
        final WhenMockErrorTestSetErrorData me = this;
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                me.invocationOnMock = invocationOnMock;
                return null;
            }
        }).when(error).setErrorType(errorType);

    }


    /**
     * Test set error.
     */
    @Test
    public void testSetError(){

        error.setErrorType(errorType);
        InvocationOnMock invocationOnMock = this.invocationOnMock;
        Assert.assertEquals(1, invocationOnMock.getArguments().length);
    }
}
