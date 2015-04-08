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
 * @since: jdk 1.7
 */
@DiscriminatorColumn( name = "discriminator", discriminatorType = DiscriminatorType.STRING )
@DiscriminatorValue( value = "note_item" )
public class Note extends SidebarType {

    @GeneratedValue
    @Column( name = "note_id", unique = true )
    private int noteId;

    @Column( name = "detail_link" )
    private String detailLink;

    public int getNoteId( ) {
        return noteId;
    }

    public void setNoteId( final int noteId ) {
        this.noteId = noteId;
        this.setSidebarTypeId( noteId );
    }

    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}
