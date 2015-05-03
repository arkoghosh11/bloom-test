package com.mana.innovative.dto.common.sidebar;

import com.mana.innovative.dto.common.SidebarType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 9:03 PM
 * @since: jdk 1.7
 */
@XmlRootElement( name = "note" )
public class Note extends SidebarType {

    private int noteId;
    private String detailLink;

    @XmlElement( name = "note_id", nillable = false )
    public int getNoteId( ) {
        return noteId;
    }

    public void setNoteId( final int noteId ) {
        this.noteId = noteId;
//        this.setSidebarTypeId( noteId );
    }

    @XmlElement( name = "link", defaultValue = "", nillable = true )
    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
