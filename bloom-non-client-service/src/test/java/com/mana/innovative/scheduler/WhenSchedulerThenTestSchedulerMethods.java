package com.mana.innovative.scheduler;

import com.mana.innovative.constants.TestConstants;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;

import static org.junit.Assert.fail;

/**
 * The type When scheduler then test scheduler methods.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/scheduler-config-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
//@Transactional   // If required
public class WhenSchedulerThenTestSchedulerMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( WhenSchedulerThenTestSchedulerMethods.class );

    /**
     * The Custom scheduler.
     */
    @Resource
    private CustomScheduler customScheduler;
    /**
     * The File.
     */
    private File file;

    /**
     * The Xml file to read.
     */
    @Value( "${xml_file_to_read}" )
    private String xmlFileToRead;
    /**
     * The Context path.
     */
    @Value( "${xml_java_context_path}" )
    private String contextPath;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        if ( xmlFileToRead != null && contextPath != null ) {
            file = new File( xmlFileToRead );
        } else {
            fail( "Failed to setup for test" );
        }
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
     * Test read from cSVN save.
     *
     * @throws Exception the exception
     */
    @Test
    public void testReadFromCSVNSave( ) throws Exception {

        logger.debug( "Starting test for ReadFromCSVNSave" );

        logger.debug( "Finishing test for ReadFromCSVNSave" );
    }

    /**
     * Test read from xMLN save.
     *
     * @throws Exception the exception
     */
    @Test
    public void testReadFromXMLNSave( ) throws Exception {

        logger.debug( "Starting test for ReadFromXMLNSave" );
        Assert.assertTrue( customScheduler.readFromXMLNSave( file ) );
        logger.debug( "Finishing test for ReadFromXMLNSave" );
    }
}