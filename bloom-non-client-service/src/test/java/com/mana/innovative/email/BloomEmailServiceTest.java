package com.mana.innovative.email;/**
 * Created by Rono on 2/10/2015. This is a class for .. todo
 */

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.common.email.EmailContents;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * The type Bloom email service test.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/email-config-test.xml" } )
public class BloomEmailServiceTest {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( BloomEmailServiceTest.class );

    /**
     * The Receiver.
     */
    String receiver, /**
     * The Cc.
     */
    cc, /**
     * The Bcc.
     */
    bcc, /**
     * The Subject.
     */
    subject, /**
     * The Body.
     */
    body, /**
     * The Attachment location.
     */
    attachmentLocation;

    /**
     * The Bloom email service.
     */
    @Resource
    private BloomEmailService bloomEmailService;

    /**
     * The Email ids.
     */
    @Value( value = "${email_receiver}" )
    private String emailIds;
    /**
     * The Email contents.
     */
    private EmailContents emailContents;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        emailContents = new EmailContents( );
        emailContents.setReceiver( "meankur2@gmail.com" );
        emailContents.setCcReceiver( "" );
        emailContents.setBccReceiver( "arkoghosh@hotmail.com" );
        emailContents.setSubject( "Open Email Please" );
        emailContents.setBody( "Hello Receiver," );
        emailContents.setIsSimple( false );
        emailContents.setHasAttachment( true );
        emailContents.setAttachmentLocation( "C:/Properties/resources/pictures/RJ_froggie.jpg" );
    }

    /**
     * Test simple mail service.
     */
    @Test
    public void testSimpleMailService( ) {
        Assert.assertNotNull( emailIds );

        //Send a simple composed mail overwrite values
        emailContents.setIsSimple( true );
        emailContents.setHasAttachment( false );
        Assert.assertTrue( TestConstants.falseMessage, bloomEmailService.sendMail( emailContents ) );
    }

    /**
     * Test mime mail service.
     */
    @Test
    public void testMimeMailService( ) {

        Assert.assertNotNull( emailIds );
        Assert.assertTrue( TestConstants.falseMessage, bloomEmailService.sendMail( emailContents ) );
    }

    /**
     * Test mime mail service with inline html.
     */
    @Test
    public void testMimeMailServiceWithInlineHtml( ) {

    }
}
