package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetCustomEventThenTestCustomEventDAOGet {

    private static final Logger logger = LoggerFactory.getLogger( WhenGetCustomEventThenTestCustomEventDAOGet.class );

    @Resource
    private CustomEventDAO customEventDAO;

    private Date eventDate;
    private Long eventId;
    private String eventName;
    private RequestParams requestParams;

    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );
        eventDate = simpleDateFormat.parse( "2015-04-14 17:22:12" );
        eventId = TestConstants.TEST_ID;
        eventName = "Test";

        requestParams = new RequestParams( );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
    }

    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testGetEventById( ) throws Exception {

        logger.debug( "Starting test for GetEventById" );

        DAOResponse< CustomEvent > customEventDAOResponse = customEventDAO.getEventById( eventId, requestParams );

        Assert.assertNotNull( customEventDAOResponse );
        // test customEventDAOResponse

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getResults( ) );

        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getCount( ) >= 0 );

        logger.debug( "Finishing test for GetEventById" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetEventsByDateWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetEventsByDateWithErrorEnabled" );
        DAOResponse< CustomEvent > customEventDAOResponse = customEventDAO.getEventsByDate( eventDate, requestParams );

        Assert.assertNotNull( customEventDAOResponse );
        // test customEventDAOResponse

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getResults( ) );

        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getCount( ) >= 0 );

        logger.debug( "Finishing test for GetEventsByDateWithErrorEnabled" );
    }

    @Test
    public void testGetEventsByEventName( ) throws Exception {

        logger.debug( "Starting test for GetEventsByEventName" );

        DAOResponse< CustomEvent > customEventDAOResponse = customEventDAO.getEventsByEventName( eventName,
                requestParams );

        Assert.assertNotNull( customEventDAOResponse );
        // test customEventDAOResponse

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getResults( ) );

        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getCount( ) >= 0 );

        logger.debug( "Finishing test for GetEventsByEventName" );
    }

    @Test
    public void testGetAllEvents( ) throws Exception {

        logger.debug( "Starting test for GetAllEvents" );

        DAOResponse< CustomEvent > customEventDAOResponse = customEventDAO.getAllEvents( requestParams );

        Assert.assertNotNull( customEventDAOResponse );
        // test customEventDAOResponse

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, customEventDAOResponse.getResults( ) );

        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, customEventDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, customEventDAOResponse.getCount( ) >= 0 );

        logger.debug( "Finishing test for GetAllEvents" );
    }
}