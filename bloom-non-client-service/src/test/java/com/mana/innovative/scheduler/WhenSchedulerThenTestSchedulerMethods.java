package com.mana.innovative.scheduler;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.scheduler.impl.CustomSchedulerImpl;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;

/**
 * The type When scheduler then test scheduler methods.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml", "/email-config-test.xml", "/scheduler-config-test.xml" } )
public class WhenSchedulerThenTestSchedulerMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( WhenSchedulerThenTestSchedulerMethods.class );

    /**
     * The Custom scheduler.
     */

    private CustomSchedulerImpl customSchedulerImpl;

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

        customSchedulerImpl = Mockito.mock( CustomSchedulerImpl.class );
        if ( xmlFileToRead != null && contextPath != null ) {
            file = new File( xmlFileToRead );
        } else {
            fail( "Failed to setup for test" );
        }
        Mockito.when( customSchedulerImpl.readFromXMLNSave( file ) ).thenReturn( Boolean.TRUE );
        Mockito.when( customSchedulerImpl.readEventsFromDB( ) ).thenReturn( Mockito.spy( new ArrayList< CustomEvent >( ) ) );
        Mockito.when( customSchedulerImpl.writeFromXMLNSave( ) ).thenReturn( Boolean.TRUE );
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

        Assert.assertTrue( customSchedulerImpl.readFromXMLNSave( file ) );
        Mockito.verify( customSchedulerImpl ).readFromXMLNSave( file );
        Mockito.verify( customSchedulerImpl, times( TestConstants.ONE ) ).readFromXMLNSave( file );
        logger.debug( "Finishing test for ReadFromXMLNSave" );
    }

    @Test
    public void testSendEmailForEvent( ) throws Exception {

        logger.debug( "Starting test for SendEmailForEvent" );

        Assert.assertTrue( TestConstants.falseMessage, customSchedulerImpl.readEventsFromDB( ) != null );
        Assert.assertEquals( TestConstants.notEqualsMessage, new ArrayList< CustomEvent >( ), customSchedulerImpl
                .readEventsFromDB( ) );
//        Assert.assertNotNull( customEvents  );
        logger.debug( "Finishing test for SendEmailForEvent" );
    }

    @Test
    public void testOutDummyItemData( ) throws Exception {

        logger.debug( "Starting test for OutDummyItemData" );
        Assert.assertNotNull( TestConstants.nullMessage, customSchedulerImpl );
        Assert.assertTrue( TestConstants.falseMessage, customSchedulerImpl.writeFromXMLNSave( ) );
        logger.debug( "Finishing test for OutDummyItemData" );
    }
}