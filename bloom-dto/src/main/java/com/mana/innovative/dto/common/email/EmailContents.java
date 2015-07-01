package com.mana.innovative.dto.common.email;

import java.util.Objects;

/**
 * The type Email contents.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class EmailContents {

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

    /**
     * The Use hTML content.
     */
    private boolean useHTMLContent;
    /**
     * The Use hTML in line picture.
     */
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

    /**
     * Is use hTML content.
     *
     * @return the boolean
     */
    public boolean isUseHTMLContent( ) {
        return useHTMLContent;
    }

    /**
     * Sets use hTML content.
     *
     * @param useHTMLContent the use hTML content
     */
    public void setUseHTMLContent( final boolean useHTMLContent ) {
        this.useHTMLContent = useHTMLContent;
    }

    /**
     * Is use hTML in line picture.
     *
     * @return the boolean
     */
    public boolean isUseHTMLInLinePicture( ) {
        return useHTMLInLinePicture;
    }

    /**
     * Sets use hTML in line picture.
     *
     * @param useHTMLInLinePicture the use hTML in line picture
     */
    public void setUseHTMLInLinePicture( final boolean useHTMLInLinePicture ) {
        this.useHTMLInLinePicture = useHTMLInLinePicture;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof EmailContents ) ) return false;
        EmailContents that = ( EmailContents ) o;
        return Objects.equals( hasAttachment, that.hasAttachment ) &&
                Objects.equals( isSimple( ), that.isSimple( ) ) &&
                Objects.equals( isUseHTMLContent( ), that.isUseHTMLContent( ) ) &&
                Objects.equals( isUseHTMLInLinePicture( ), that.isUseHTMLInLinePicture( ) ) &&
                Objects.equals( getSender( ), that.getSender( ) ) &&
                Objects.equals( getReceiver( ), that.getReceiver( ) ) &&
                Objects.equals( getCcReceiver( ), that.getCcReceiver( ) ) &&
                Objects.equals( getBccReceiver( ), that.getBccReceiver( ) ) &&
                Objects.equals( getSubject( ), that.getSubject( ) ) &&
                Objects.equals( getBody( ), that.getBody( ) ) &&
                Objects.equals( getAttachmentLocation( ), that.getAttachmentLocation( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "EmailContents {" +
                "                            sender='" + sender +
                ",                            receiver='" + receiver +
                ",                            ccReceiver='" + ccReceiver +
                ",                            bccReceiver='" + bccReceiver +
                ",                            subject='" + subject +
                ",                            body='" + body +
                ",                            attachmentLocation='" + attachmentLocation +
                ",                            hasAttachment=" + hasAttachment +
                ",                            isSimple=" + isSimple +
                ",                            useHTMLContent=" + useHTMLContent +
                ",                            useHTMLInLinePicture=" + useHTMLInLinePicture +
                '}';
    }
}
