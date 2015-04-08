package com.mana.innovative.scheduler;

import com.mana.innovative.constants.TestConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.fail;

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml", "/email-config-test.xml", "/scheduler-config-test.xml" } )
// "" <- <add location file>
//@TransactionConfiguration // If required
//@Transactional   // If required
public class WhenCustomSchedulerThenTestMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenCustomSchedulerThenTestMethods.class );

    @Resource
    private CustomScheduler customScheduler;

    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testLoadItemsFile( ) throws Exception {

        logger.debug( "Starting test for LoadItemsFile" );

        try {
            customScheduler.loadItemsFile( );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while testing LoadItemsFile", exception );
            fail( "Exception occurred" );
        }

        logger.debug( "Finishing test for LoadItemsFile" );
    }

    @Test
    public void testGetEventsNEmail( ) throws Exception {

        logger.debug( "Starting test for GetEventsNEmail" );
        try {
            customScheduler.getEventsNEmail( );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while testing GetEventsNEmail", exception );
            fail( "Exception occurred" );
        }
        logger.debug( "Finishing test for GetEventsNEmail" );
    }
}