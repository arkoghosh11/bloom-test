package com.mana.innovative.dao;

import com.mana.innovative.constants.TestConstants;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenDeleteWorkingHThenTestWorkingHDAODeleteMethods {

    private static final Logger logger = Logger.getLogger( WhenDeleteWorkingHThenTestWorkingHDAODeleteMethods.class );

    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testDeleteWorkingHourByWorkingHrId( ) throws Exception {

        logger.debug( "Starting test for DeleteWorkingHourByWorkingHrId" );

        logger.debug( "Finishing test for DeleteWorkingHourByWorkingHrId" );
    }

    @Test
    public void testDeleteWorkingHoursByWorkingHrIds( ) throws Exception {

        logger.debug( "Starting test for DeleteWorkingHoursByWorkingHrIds" );

        logger.debug( "Finishing test for DeleteWorkingHoursByWorkingHrIds" );
    }
}