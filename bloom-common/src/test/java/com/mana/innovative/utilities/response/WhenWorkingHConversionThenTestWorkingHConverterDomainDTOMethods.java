package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.client.WorkingHour;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is a test class for testing class todo...
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenWorkingHConversionThenTestWorkingHConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenWorkingHConversionThenTestWorkingHConverterDomainDTOMethods.class );

    /**
     * The Working hour dTO.
     */
    private WorkingHour workingHourDTO, /**
     * The Working hour dTO 2.
     */
    workingHourDTO2;
    /**
     * The Working hour domain.
     */
    private com.mana.innovative.domain.client.WorkingHour workingHourDomain, /**
     * The Working hour domain 2.
     */
    workingHourDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        workingHourDomain2 = new com.mana.innovative.domain.client.WorkingHour( );
        workingHourDTO2 = new WorkingHour( );

        // Set Values for tempValues
        workingHourDTO = TestDummyDTOObjectGenerator.getTestWorkingHourDTOObject( );
        workingHourDomain = TestDummyDomainObjectGenerator.getTestWorkingHourDomainObject( );

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
     * Test get converted dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  GetConvertedDTOFromDomain" );

        workingHourDTO2 = WorkingHourDomainDTOConverter.getConvertedDTOFromDomain( workingHourDomain );

        Assert.assertNotNull( TestConstants.nullMessage, workingHourDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDTO, workingHourDTO2 );

        logger.debug( "Finishing GetConvertedDTOFromDomain" );
    }

    /**
     * Test get converted list dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDTOFromDomain" );

        List< WorkingHour > workingHourDTOList;
        List< com.mana.innovative.domain.client.WorkingHour > workingHourDomainList = new ArrayList<>( );
        workingHourDomainList.add( workingHourDomain );
        if ( workingHourDomain.getWorkingHourId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestWorkingHourDomainZEROIDObject( workingHourDomain );
        }
        workingHourDTOList = WorkingHourDomainDTOConverter.getConvertedListDTOFromDomain( workingHourDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDTOList );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDTO, workingHourDTOList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDTOFromDomain" );
    }

    /**
     * Test get converted domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  GetConvertedDomainFromDTO" );

        workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( workingHourDTO );
        TestDummyDomainObjectGenerator.setTestWorkingHourDomainZEROIDObject( workingHourDomain );

        Assert.assertNotNull( TestConstants.nullMessage, workingHourDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDomain, workingHourDomain2 );

        logger.debug( "Finishing GetConvertedDomainFromDTO" );
    }

    /**
     * Test get converted list domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDomainFromDTO" );

        List< WorkingHour > workingHourDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.client.WorkingHour > workingHourDomainList;
        workingHourDTOList.add( workingHourDTO );
        TestDummyDomainObjectGenerator.setTestWorkingHourDomainZEROIDObject( workingHourDomain );

        workingHourDomainList = WorkingHourDomainDTOConverter.getConvertedListDomainFromDTO( workingHourDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, workingHourDomainList );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDomain, workingHourDomainList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDomainFromDTO" );
    }

    /**
     * Test to string time from date.
     *
     * @throws Exception the exception
     */
    @Test
    public void testToStringTimeFromDate( ) throws Exception {

        logger.debug( "Starting  ToStringTimeFromDate" );
        String testTimeString = "11:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_TIME_FORMAT );
        Date testDate = simpleDateFormat.parse( testTimeString );
        Assert.assertEquals( TestConstants.notEqualsMessage, testTimeString, WorkingHourDomainDTOConverter.toStringTimeFromDate(
                testDate ) );

        logger.debug( "Finishing ToStringTimeFromDate" );
    }

    /**
     * Test get converted domain from dTO for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDomainFromDTOForError" );
        WorkingHour workingHour = new WorkingHour( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( workingHour );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        illegalArgumentValueException = null;

        try {
            workingHourDTO.setEndTime( "3e:r4" );
            workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( workingHourDTO );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDomainFromDTOForError" );
    }

    /**
     * Test get converted dTO from domain for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        NullPointerException nullPointerException = null;
        try {
            workingHourDTO2 = WorkingHourDomainDTOConverter.getConvertedDTOFromDomain( null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}