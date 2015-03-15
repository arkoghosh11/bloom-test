package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.WorkingHour;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenDeleteWorkingHThenTestWorkingHDAODeleteMethods {

    private static final Logger logger = Logger.getLogger( WhenDeleteWorkingHThenTestWorkingHDAODeleteMethods.class );

    @Resource
    private WorkingHourDAO workingHourDAOImpl;
    @Resource
    private SessionFactory sessionFactory;

    private long testId;
    private List< Long > testIds;

    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.TEST_ID;
        testIds = new ArrayList<>( );
        testIds.add( ( long ) TestConstants.ZERO );
        testIds.add( testId );
    }

    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from WorkingHour where workingHourId=:workingHr_id" );
        query.setLong( "workingHr_id", DAOConstants.ZERO );
        List< WorkingHour > workingHrs = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", workingHrs.isEmpty( ) );
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testWorkingHourDAONotNull( ) throws Exception {

        logger.debug( "Starting test for WorkingHourDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOImpl );
        logger.debug( "Finishing test for WorkingHourDAONotNull" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteWorkingHourByWorkingHrIdErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteWorkingHourByWorkingHrIdErrorEnabled" );
        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteWorkingHourByWorkingHrId( testId,
                TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( workingHourDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, workingHourDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteWorkingHourByWorkingHrIdErrorEnabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteWorkingHourByWorkingHrIdErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteWorkingHourByWorkingHrIdErrorDisabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteWorkingHourByWorkingHrId( testId, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, workingHourDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteWorkingHourByWorkingHrIdErrorDisabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteWorkingHoursByWorkingHrIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteWorkingHoursByWorkingHrIdsErrorEnabled" );
        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteWorkingHoursByWorkingHrIds(
                testIds, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( workingHourDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), workingHourDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteWorkingHoursByWorkingHrIdsErrorEnabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteWorkingHoursByWorkingHrIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteWorkingHoursByWorkingHrIdsErrorDisabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteWorkingHoursByWorkingHrIds(
                testIds, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), workingHourDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteWorkingHoursByWorkingHrIdsErrorDisabled" );
    }

    //  IMP Delete all WorkingHour Test Cases
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllWorkingHoursWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllWorkingHoursWithErrorEnabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteAllWorkingHours( TestConstants
                .IS_DELETE_ALL, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( workingHourDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, workingHourDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllWorkingHoursWithErrorEnabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllWorkingHoursWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllWorkingHoursWithErrorDisabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl
                .deleteAllWorkingHours( TestConstants.IS_DELETE_ALL, TestConstants.IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, workingHourDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllWorkingHoursWithErrorDisabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllWorkingHoursWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllWorkingHoursWithDeleteAllTrueWithErrorEnabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteAllWorkingHours( TestConstants
                .IS_DELETE_ALL_TRUE, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( workingHourDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllWorkingHoursWithDeleteAllTrueWithErrorEnabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllWorkingHoursWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllWorkingHoursWithDeleteAllTrueWithErrorDisabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAOImpl.deleteAllWorkingHours( TestConstants
                .IS_DELETE_ALL_TRUE, TestConstants.IS_ERROR );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllWorkingHoursWithDeleteAllTrueWithErrorDisabled" );
    }
}