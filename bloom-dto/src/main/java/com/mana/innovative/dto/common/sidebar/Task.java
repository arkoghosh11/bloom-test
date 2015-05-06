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
@XmlRootElement( name = "task" )
public class Task extends SidebarType {

    /**
     * The Task id.
     */
    private int taskId;
    /**
     * The Detail link.
     */
    private String detailLink;

    /**
     * Gets task id.
     *
     * @return the task id
     */
    @XmlElement( name = "task_id", nillable = false )
    public int getTaskId( ) {
        return taskId;
    }

    /**
     * Sets task id.
     *
     * @param taskId the task id
     */
    public void setTaskId( final int taskId ) {
        this.taskId = taskId;
//        this.setSidebarTypeId( taskId );
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