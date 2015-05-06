package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:03 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@XmlRootElement( name = "note" )
public class Note extends SidebarType {

    /**
     * The Note id.
     */
    private int noteId;
    /**
     * The Detail link.
     */
    private String detailLink;

    /**
     * Gets note id.
     *
     * @return the note id
     */
    @XmlElement( name = "note_id", nillable = false )
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
//        this.setSidebarTypeId( noteId );
    }

    /**
     * Gets detail link.
     *
     * @return the detail link
     */
    @XmlElement( name = "link", defaultValue = "", nillable = true )
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
