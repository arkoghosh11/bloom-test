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
@XmlRootElement( name = "task" )
public class Task extends SidebarType {

    private int taskId;
    private String detailLink;

    @XmlElement( name = "task_id", nillable = false )
    public int getTaskId( ) {
        return taskId;
    }

    public void setTaskId( final int taskId ) {
        this.taskId = taskId;
//        this.setSidebarTypeId( taskId );
    }

    @XmlElement( name = "link", defaultValue = "", nillable = true )
    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}