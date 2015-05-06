package com.mana.innovative.domain.common.sidebar;


import com.mana.innovative.domain.common.SidebarType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:03 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "note_item" )
public class Note extends SidebarType {

    /**
     * The Note id.
     */
    @GeneratedValue
    @Column( name = "note_id", unique = true )
    private int noteId;

    /**
     * The Detail link.
     */
    @Column( name = "detail_link" )
    private String detailLink;

    /**
     * Gets note id.
     *
     * @return the note id
     */
    public int getNoteId( ) {
        return noteId;
    }

    /**
     * Sets note id.
     *
     * @param noteId the note id
     */
    public void setNoteId( final int noteId ) {
        this.noteId = noteId;
        this.setSidebarTypeId( noteId );
    }

    /**
     * Gets detail link.
     *
     * @return the detail link
     */
    public String getDetailLink( ) {
        return detailLink;
    }

    /**
     * Sets detail link.
     *
     * @param detailLink the detail link
     */
    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
