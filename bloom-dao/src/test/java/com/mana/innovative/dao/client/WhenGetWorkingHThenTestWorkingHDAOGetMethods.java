package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.converter.response.WorkingHourDomainDTOConverter;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.WorkingHour;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * This class is a test class for testing class todo...
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetWorkingHThenTestWorkingHDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetWorkingHThenTestWorkingHDAOGetMethods.class );

    /**
     * The Working hour dAO.
     */
    @Resource
    private WorkingHourDAO workingHourDAO;

    /**
     * The Working hours id.
     */
    private long workingHoursId;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        workingHoursId = ( long ) TestConstants.ONE;
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test get working hour by working hour id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetWorkingHourByWorkingHourIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetWorkingHourByWorkingHourIdWithErrorDisabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAO.getWorkingHourByWorkingHourId( workingHoursId, TestConstants.IS_ERROR );
        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, workingHourDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, workingHourDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ) );
        Assert.assertFalse( workingHourDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        //
        List< WorkingHour > workingHours = workingHourDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, workingHours.size( ) );
        // Test WorkingHour
        WorkingHour workingHour = workingHours.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, workingHour );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHoursId, workingHour.getWorkingHourId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_DAY, workingHour.getDay( ) );
        // test start time
        Assert.assertNotNull( TestConstants.nullMessage, workingHour.getStartTime( ) );
        String startTimeString = WorkingHourDomainDTOConverter.toStringTimeFromDate( workingHour.getStartTime( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_START_TIME, startTimeString );
        // test end time
        Assert.assertNotNull( TestConstants.nullMessage, workingHour.getEndTime( ) );
        String endTimeString = WorkingHourDomainDTOConverter.toStringTimeFromDate( workingHour.getEndTime( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_END_TIME, endTimeString );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_IS_HOLIDAY, workingHour.isHoliday( ).booleanValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_IS_CLOSED, workingHour.isOffline( ).booleanValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_IS_WEEKEND, workingHour.isWeekend( ).booleanValue( ) );

        Assert.assertNotNull( TestConstants.notEqualsMessage, workingHour.getShopWorkingHour( ) );

        logger.debug( "Finishing test for GetWorkingHourByWorkingHourIdWithErrorDisabled" );
    }

    /**
     * Test get working hour by working hour id with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetWorkingHourByWorkingHourIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetWorkingHourByWorkingHourIdWithErrorEnabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAO.getWorkingHourByWorkingHourId(
                workingHoursId, TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, workingHourDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, workingHourDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ) );
        Assert.assertFalse( workingHourDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        //
        List< WorkingHour > workingHours = workingHourDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, workingHours.size( ) );
        // Test WorkingHour
        WorkingHour workingHour = workingHours.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, workingHour );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHoursId, workingHour.getWorkingHourId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_DAY, workingHour.getDay( ) );
        // test start time
        Assert.assertNotNull( TestConstants.nullMessage, workingHour.getStartTime( ) );
        String startTimeString = WorkingHourDomainDTOConverter.toStringTimeFromDate( workingHour.getStartTime( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_START_TIME, startTimeString );
        // test end time
        Assert.assertNotNull( TestConstants.nullMessage, workingHour.getEndTime( ) );
        String endTimeString = WorkingHourDomainDTOConverter.toStringTimeFromDate( workingHour.getEndTime( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_END_TIME, endTimeString );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_IS_HOLIDAY, workingHour.isHoliday( ).booleanValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_IS_CLOSED, workingHour.isOffline( ).booleanValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_IS_WEEKEND, workingHour.isWeekend( ).booleanValue( ) );

        Assert.assertNotNull( TestConstants.notEqualsMessage, workingHour.getShopWorkingHour( ) );

        logger.debug( "Finishing test for GetWorkingHourByWorkingHourIdWithErrorEnabled" );
    }

    /**
     * Test get working hours with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetWorkingHoursWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetWorkingHoursWithErrorDisabled" );
        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAO.getWorkingHours( TestConstants.IS_ERROR );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, workingHourDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, workingHourDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ) );
        Assert.assertFalse( workingHourDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        logger.debug( "Finishing test for GetWorkingHoursWithErrorDisabled" );
    }

    /**
     * Test get working hours with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetWorkingHoursWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetWorkingHoursWithErrorEnabled" );
        DAOResponse< WorkingHour > workingHourDAOResponse = workingHourDAO.getWorkingHours( TestConstants.IS_ERROR_TRUE );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, workingHourDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, workingHourDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ) );
        // test list result and content Major object
        Assert.assertFalse( workingHourDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        logger.debug( "Finishing test for GetWorkingHoursWithErrorEnabled" );
    }
}