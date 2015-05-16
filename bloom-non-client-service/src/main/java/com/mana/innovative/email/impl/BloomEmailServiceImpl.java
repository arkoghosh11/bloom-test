package com.mana.innovative.email.impl;

import com.mana.innovative.dto.common.email.EmailContents;
import com.mana.innovative.email.BloomEmailService;
import com.mana.innovative.exception.EmptyPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring3.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;

/**
 * The type Bloom email service impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class BloomEmailServiceImpl implements BloomEmailService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( BloomEmailServiceImpl.class );

    /**
     * The Java mail sender.
     */
    @Resource( name = "javaMailSender" )
    private JavaMailSender javaMailSender;

    /**
     * The Spring template engine.
     */
    @Resource( name = "springTemplateEngine" )
    private SpringTemplateEngine springTemplateEngine;

    /**
     * The Use hTML content.
     */
    private boolean useHTMLContent;
    /**
     * The Use hTML in line picture.
     */
    private boolean useHTMLInLinePicture;

    /**
     * The Template file.
     */
    @Value( value = "${bloom-non-client-service.thymeLeaf_template_file_location}" )
    private String templateFile;

    /**
     * The Image resource name.
     */
    private String imageResourceName;

//
//    @Resource
//    private SimpleMailMessage preConfiguredMessage;

    /**
     * Send mail.
     *
     * @param emailContents the email contents
     *
     * @return the boolean
     */
    @Override
    public boolean sendMail( EmailContents emailContents ) {

        String location = this.getClass( ).getCanonicalName( ) + "#sendMail()";
        logger.debug( "Starting " + location );
        logger.debug( "********* emailContents " + emailContents.toString( ) );
        if ( emailContents.isSimple( ) ) {

            logger.info( "Using attachment Simple Message for Simple Email Construction" );
            this.sendMail( emailContents.getReceiver( ),
                    emailContents.getCcReceiver( ),
                    emailContents.getBccReceiver( ),
                    emailContents.getSubject( ),
                    emailContents.getBody( ) );
        } else if ( emailContents.hasAttachment( ) ) {
            logger.info( "Using attachment Mime Message for Complex Email Construction" );
            this.sendMail( emailContents.getReceiver( ),
                    emailContents.getCcReceiver( ),
                    emailContents.getBccReceiver( ),
                    emailContents.getSubject( ),
                    emailContents.getBody( ),
                    emailContents.getAttachmentLocation( ) );
        }
        this.useHTMLContent = emailContents.isUseHTMLContent( );
        this.useHTMLInLinePicture = emailContents.isUseHTMLInLinePicture( );

        logger.debug( "Finishing " + location );
        return true;
    }


    /**
     * Send mail.
     *
     * @param to      the to
     * @param cc      the cc
     * @param bcc     the bcc
     * @param subject the subject
     * @param body    the body
     *
     * @return the boolean
     */
    private boolean sendMail( String to, String cc, String bcc, String subject, String body ) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage( );

        try {
            if ( !StringUtils.isEmpty( to.trim( ) ) ) {
                simpleMailMessage.setTo( to.split( "," ) );
            } else throw new EmptyPropertyException( "to" );
            if ( !StringUtils.isEmpty( cc.trim( ) ) ) {
                simpleMailMessage.setCc( cc.split( "," ) );
            } else logger.debug( "No cc receivers found for emailing to" );

            if ( !StringUtils.isEmpty( bcc.trim( ) ) ) {
                simpleMailMessage.setBcc( bcc.split( "," ) );
            } else logger.debug( "No bcc receivers found for emailing to" );
            if ( !StringUtils.isEmpty( subject ) ) {
                simpleMailMessage.setSubject( subject );
            } else throw new EmptyPropertyException( "subject" );
            if ( !StringUtils.isEmpty( body ) ) {
                simpleMailMessage.setText( body );
            } else throw new EmptyPropertyException( "body" );

        } catch ( EmptyPropertyException exception ) {
            logger.error( "Exception occurred while creating simple message" );
            return false;
        }
        try {
//            if ( simpleMailMessage.getFrom( ) != null ) {
            javaMailSender.send( simpleMailMessage );
//            }
        } catch ( Exception exception ) {
            logger.error( "Exception occurred ", exception );
            return false;
        }
        return true;
    }

    /**
     * Send mail.
     *
     * @param to                 the to
     * @param cc                 the cc
     * @param bcc                the bcc
     * @param subject            the subject
     * @param body               the body
     * @param attachmentLocation the attachment location
     *
     * @return the boolean
     */
    private boolean sendMail( String to, String cc, String bcc, String subject, String body, String attachmentLocation
    ) {

        String location = this.getClass( ).getCanonicalName( ) + "#sendMail(havingAttachment)";
        logger.debug( "Starting " + location );
        MimeMessage mimeMessage = javaMailSender.createMimeMessage( );

        logger.debug( ( ( JavaMailSenderImpl ) javaMailSender ).getUsername( ) );

        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper( mimeMessage, true );
            if ( !StringUtils.isEmpty( to ) ) {
                mimeMessageHelper.setTo( to.split( "," ) );
            } else throw new EmptyPropertyException( "to" );

            if ( !StringUtils.isEmpty( cc.trim( ) ) ) {
                mimeMessageHelper.setCc( cc.split( "," ) );
            } else logger.debug( "No cc receivers found for emailing to" );

            if ( !StringUtils.isEmpty( bcc ) ) {
                mimeMessageHelper.setBcc( bcc.split( "," ) );
            } else logger.debug( "No bcc receivers found for emailing to" );
            if ( !StringUtils.isEmpty( subject ) ) {
                mimeMessageHelper.setSubject( subject );
            } else throw new EmptyPropertyException( "subject" );
            if ( !StringUtils.isEmpty( body ) ) {
                mimeMessageHelper.setText( body, true );
            } else throw new EmptyPropertyException( "body" );

            if ( StringUtils.isEmpty( attachmentLocation ) ) {
                throw new EmptyPropertyException( "attachmentLocation" );
            }

            String attachmentLocations[] = attachmentLocation.split( "," );
            for ( String attachLocation : attachmentLocations ) {
                FileSystemResource fileSystemResource = new FileSystemResource( attachLocation );
                if ( fileSystemResource.exists( ) && fileSystemResource.isReadable( ) ) {
                    mimeMessageHelper.addAttachment( fileSystemResource.getFilename( ), fileSystemResource );
                }
            }


            if ( useHTMLContent ) {
                Context ctx = new Context( );
                ctx.setVariable( "subscriptionDate", new Date( ) );
                ctx.setVariable( "hobbies", Arrays.asList( "Cinema", "Sports", "Music" ) );
                ctx.setVariable( "imageResourceName", imageResourceName ); // so that we can reference it from HTML
                springTemplateEngine.process( templateFile, ctx );
            }

        } catch ( MessagingException exception ) {
            logger.error( "Exception occurred while creating mimeMessageHelper" );
            return false;
        } catch ( EmptyPropertyException exception ) {
            logger.error( " The following properties were empty ", exception );
            return false;
        }

        try {

//            if ( mimeMessage.getFrom( ) != null ) {
            logger.debug( "Email Sending" );
            javaMailSender.send( mimeMessage );
            logger.debug( "Email Sent" );
//            }

        } catch ( Exception exception ) {
            logger.error( "Exception occurred ", exception );
            return false;
        }
        logger.debug( "Finishing " + location );
        return true;
    }

    /**
     * Check mail properties.
     *
     * @param mailProperties the mail properties
     *
     * @return the boolean
     */
    private boolean checkMailProperties( String mailProperties[] ) {

        for ( String mailProperty : mailProperties ) {
            try {
                if ( StringUtils.isEmpty( mailProperty ) ) {
                    throw new EmptyPropertyException( );
                }
            } catch ( EmptyPropertyException exception ) {
                logger.error( "Exception occurred since a property for email was empty", exception );
                return false;
            }
        }
        return true;
    }

//    /**
//     * This method will send a pre-configured message
//     */
//    public void sendPreConfiguredMail( String message ) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage( preConfiguredMessage );
//        mailMessage.setText( message );
////        javaMailSender.send(mailMessage);
//    }

}
