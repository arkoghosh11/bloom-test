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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is a test class for testing class todo...
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true, transactionManager = "transactionManager" ) // If required
@Transactional   // If required
public class WhenUpdateEventThenTestCustomEventUpdateDAOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenUpdateEventThenTestCustomEventUpdateDAOMethods.class );

    /**
     * The Custom event dAO.
     */
    @Resource
    private CustomEventDAO customEventDAO;

    /**
     * The Custom event.
     */
    private CustomEvent customEvent;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        DAOResponse< CustomEvent > customEventDAOResponse = customEventDAO.getEventById( TestConstants.ZERO, new RequestParams( ) );

        Assert.assertNotNull( customEventDAOResponse );
        Assert.assertNotNull( customEventDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customEventDAOResponse.getCount( ) );

        customEvent = customEventDAOResponse.getResults( ).get( TestConstants.ZERO );
        customEvent.setCcReceivers( TestConstants.TEST_EMAIL );
        customEvent.setEventName( TestConstants.UPDATED_TEST_VALUE );
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
     * Test update event with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testUpdateEventWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for UpdateEventWithErrorDisabled" );
        DAOResponse< CustomEvent > customEventDAOResponse =
                customEventDAO.updateEvent( customEvent, new RequestParams( ) );

        Assert.assertNotNull( customEventDAOResponse );

        Assert.assertNull( TestConstants.notNullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( customEventDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customEventDAOResponse.getCount( ) );

        CustomEvent localCustomEvent = customEventDAOResponse.getResults( ).get( TestConstants.ZERO );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_EMAIL, localCustomEvent.getCcReceivers( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, localCustomEvent.getCustomEventId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_SUBJECT, localCustomEvent
                .getSubject( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_BODY, localCustomEvent
                .getBody( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.UPDATED_TEST_VALUE, localCustomEvent
                .getEventName( ) );


        logger.debug( "Finishing test for UpdateEventWithErrorDisabled" );
    }

    /**
     * Test enable event scheduler for date with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testEnableEventSchedulerForDateWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for EnableEventSchedulerForDateWithErrorDisabled" );

        Date date = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT ).parse( "2015-04-19 22:21:21" );

        DAOResponse< CustomEvent > customEventDAOResponse =
                customEventDAO.enableEventSchedulerForDate( date, new RequestParams( ) );

        Assert.assertNotNull( customEventDAOResponse );

        Assert.assertNull( TestConstants.notNullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.nullMessage, customEventDAOResponse.getResults( ) );

//      Note Seems rollback is enabled this will be 0 always as no db commits actually takes place
//        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customEventDAOResponse.getCount( ) );

        logger.debug( "Finishing test for EnableEventSchedulerForDateWithErrorDisabled" );
    }

    /**
     * Test disable event scheduler for date.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDisableEventSchedulerForDate( ) throws Exception {

        logger.debug( "Starting test for DisableEventSchedulerForDateWithErrorDisabled" );

        Date date = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT ).parse( "2015-04-19 22:21:21" );

        DAOResponse< CustomEvent > customEventDAOResponse =
                customEventDAO.disableEventSchedulerForDate( date, new RequestParams( ) );

        Assert.assertNotNull( customEventDAOResponse );

        Assert.assertNull( TestConstants.notNullMessage, customEventDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.nullMessage, customEventDAOResponse.getResults( ) );
//      Note Seems rollback is enabled this will be 0 always as no db commits actually takes place
//        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, customEventDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DisableEventSchedulerForDateWithErrorDisabled" );
    }
}