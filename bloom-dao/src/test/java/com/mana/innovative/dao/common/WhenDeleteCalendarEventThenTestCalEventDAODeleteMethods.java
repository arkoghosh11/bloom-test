package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/15/2015 7:32 PM. This class WhenDeleteCalendarEventThenTestCalEventDAODeleteMethods is a
 * test class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteCalendarEventThenTestCalEventDAODeleteMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteCalendarEventThenTestCalEventDAODeleteMethods.class );

    /**
     * The CalendarEvent dAO impl.
     */
    @Resource
    private CalendarEventDAO calendarEventDAOImpl;
    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    private RequestParams requestParams;

    /**
     * The Test id.
     */
    private long testId;
    /**
     * The Test ids.
     */
    private List< Long > testIds;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.TEST_ID;
        testIds = new ArrayList<>( );
        testIds.add( ( long ) TestConstants.ZERO );
        testIds.add( testId );
        requestParams = new RequestParams( );
        logger.debug( "Test SetUp completed" );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    @SuppressWarnings( "unchecked" )
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from CalendarEvent where calendarEventId=:calendarEvent_id" );
        query.setLong( "calendarEvent_id", testId );
        List< CalendarEvent > calendarEvents = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", calendarEvents.isEmpty( ) );
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test calendarEvent dAO not null.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalendarEventDAONotNull( ) throws Exception {

        logger.debug( "Starting test for CalendarEventDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOImpl );
        logger.debug( "Finishing test for CalendarEventDAONotNull" );
    }

    /**
     * Test delete calendarEvent by calendarEvent id error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCalendarEventByCalendarEventIdErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCalendarEventByCalendarEventIdErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteCalendarEventByEventId( testId, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, calendarEventDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCalendarEventByCalendarEventIdErrorEnabled" );
    }

    /**
     * Test delete calendarEvent by calendarEvent id error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCalendarEventByCalendarEventIdErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCalendarEventByCalendarEventIdErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteCalendarEventByEventId( testId, requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, calendarEventDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCalendarEventByCalendarEventIdErrorDisabled" );
    }

    /**
     * Test delete calendarEvents by calendarEvent ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCalendarEventsByCalendarEventIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCalendarEventsByCalendarEventIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteCalendarEvents( testIds, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), calendarEventDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCalendarEventsByCalendarEventIdsErrorEnabled" );
    }

    /**
     * Test delete calendarEvents by calendarEvent ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCalendarEventsByCalendarEventIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCalendarEventsByCalendarEventIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteCalendarEvents( testIds, requestParams );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), calendarEventDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCalendarEventsByCalendarEventIdsErrorDisabled" );
    }

    /**
     * Test delete all calendarEvent with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCalendarEventWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCalendarEventWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl.deleteAllCalendarEvents( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, calendarEventDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCalendarEventWithErrorEnabled" );
    }

    /**
     * Test delete all calendarEvent with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCalendarEventWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCalendarEventWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteAllCalendarEvents( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, calendarEventDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCalendarEventWithErrorDisabled" );
    }

    /**
     * Test delete all calendarEvent with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCalendarEventWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCalendarEventWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteAllCalendarEvents( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( calendarEventDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCalendarEventWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all calendarEvent with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCalendarEventWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCalendarEventWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< CalendarEvent > calendarEventDAOResponse = calendarEventDAOImpl
                .deleteAllCalendarEvents( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, calendarEventDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, calendarEventDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, calendarEventDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, calendarEventDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCalendarEventWithDeleteAllTrueWithErrorDisabled" );
    }
}