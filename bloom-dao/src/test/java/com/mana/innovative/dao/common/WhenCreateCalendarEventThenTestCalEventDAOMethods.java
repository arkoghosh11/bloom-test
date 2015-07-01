package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Bloom/Rono on 5/15/2015 6:45 PM. This class WhenCreateCalEventThenTestCalEventDAOMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateCalendarEventThenTestCalEventDAOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateCalendarEventThenTestCalEventDAOMethods.class );

    /**
     * The Dummy calendarEvent.
     */
    private CalendarEvent dummyCalendarEvent;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The CalendarEvent dAO.
     */
    @Resource
    private CalendarEventDAO calendarEventDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyCalendarEvent = TestDummyDomainObjectGenerator.getTestCalendarEventDomainObject( );

        requestParams = new RequestParams( );

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );

    }

    /**
     * Test create calendarEvent with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCalendarEventWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateCalendarEventWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.createCalendarEvent( dummyCalendarEvent, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );

        CalendarEvent calendarEvent = calendarEventDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCalendarEvent, calendarEvent );

        logger.debug( "Finishing test for CreateCalendarEventWithErrorDisabled" );
    }

    /**
     * Test create calendarEvent with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCalendarEventWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateCalendarEventWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAO.createCalendarEvent( dummyCalendarEvent, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );

        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.getResults( ).isEmpty( ) );

        CalendarEvent calendarEvent = calendarEventDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEvent );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCalendarEvent, calendarEvent );

        logger.debug( "Finishing test for CreateCalendarEventWithErrorEnabled" );
    }
}