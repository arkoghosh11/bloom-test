package com.mana.innovative.exception.response;

import com.mana.innovative.constants.ErrorConstants;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * Created by alex1 on 1/28/2015.
 * This is a domain class
 */
@RunWith(value = MockitoJUnitRunner.class)
public class WhenMockErrorTestSetErrorData {

    private static final Logger logger = Logger.getLogger(WhenMockErrorTestSetErrorData.class);

    private Error error;

    private static final String errorType = ErrorConstants.TEST_ERROR_TYPE;
    private InvocationOnMock invocationOnMock;

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


    @Test
    public void testSetError(){

        error.setErrorType(errorType);
        InvocationOnMock invocationOnMock = this.invocationOnMock;
        Assert.assertEquals(1, invocationOnMock.getArguments().length);
    }
}
