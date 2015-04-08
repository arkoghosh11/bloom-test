package com.mana.innovative.dto.common.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Email contents.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class EmailContents {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( EmailContents.class );


    /**
     * The Sender.
     */
    private String sender;
    /**
     * The Receiver.
     */
    private String receiver;

    /**
     * The Cc receiver.
     */
    private String ccReceiver;
    /**
     * The Bcc receiver.
     */
    private String bccReceiver;
    /**
     * The Subject.
     */
    private String subject;
    /**
     * The Body.
     */
    private String body;
    /**
     * The Attachment location.
     */
    private String attachmentLocation;

    /**
     * The Has attachment.
     */
    private boolean hasAttachment;
    /**
     * The Is simple.
     */
    private boolean isSimple;

    private boolean useHTMLContent;
    private boolean useHTMLInLinePicture;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender( ) {
        return sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender( final String sender ) {
        this.sender = sender;
    }

    /**
     * Gets receiver.
     *
     * @return the receiver
     */
    public String getReceiver( ) {
        return receiver;
    }

    /**
     * Sets receiver.
     *
     * @param receiver the receiver
     */
    public void setReceiver( final String receiver ) {
        this.receiver = receiver;
    }

    /**
     * Gets cc receiver.
     *
     * @return the cc receiver
     */
    public String getCcReceiver( ) {
        return ccReceiver;
    }

    /**
     * Sets cc receiver.
     *
     * @param ccReceiver the cc receiver
     */
    public void setCcReceiver( final String ccReceiver ) {
        this.ccReceiver = ccReceiver;
    }

    /**
     * Gets bcc receiver.
     *
     * @return the bcc receiver
     */
    public String getBccReceiver( ) {
        return bccReceiver;
    }

    /**
     * Sets bcc receiver.
     *
     * @param bccReceiver the bcc receiver
     */
    public void setBccReceiver( final String bccReceiver ) {
        this.bccReceiver = bccReceiver;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject( ) {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject( final String subject ) {
        this.subject = subject;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody( ) {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody( final String body ) {
        this.body = body;
    }

    /**
     * Gets attachment location.
     *
     * @return the attachment location
     */
    public String getAttachmentLocation( ) {
        return attachmentLocation;
    }

    /**
     * Sets attachment location.
     *
     * @param attachmentLocation the attachment location
     */
    public void setAttachmentLocation( final String attachmentLocation ) {
        this.attachmentLocation = attachmentLocation;
    }

    /**
     * Has attachment.
     *
     * @return the boolean
     */
    public boolean hasAttachment( ) {
        return hasAttachment;
    }

    /**
     * Sets has attachment.
     *
     * @param hasAttachment the has attachment
     */
    public void setHasAttachment( final boolean hasAttachment ) {
        this.hasAttachment = hasAttachment;
    }

    /**
     * Is simple.
     *
     * @return the boolean
     */
    public boolean isSimple( ) {
        return isSimple;
    }

    /**
     * Sets is simple.
     *
     * @param isSimple the is simple
     */
    public void setIsSimple( final boolean isSimple ) {
        this.isSimple = isSimple;
    }

    public boolean isUseHTMLContent( ) {
        return useHTMLContent;
    }

    public void setUseHTMLContent( final boolean useHTMLContent ) {
        this.useHTMLContent = useHTMLContent;
    }

    public boolean isUseHTMLInLinePicture( ) {
        return useHTMLInLinePicture;
    }

    public void setUseHTMLInLinePicture( final boolean useHTMLInLinePicture ) {
        this.useHTMLInLinePicture = useHTMLInLinePicture;
    }
}
