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
@DiscriminatorValue( value = "taskItem" )
public class Task extends SidebarType {

    /**
     * The Task id.
     */
    @GeneratedValue
    @Column( name = "task_id" )
    private int taskId;

    /**
     * The Detail link.
     */
    @Column( name = "detail_link" )
    private String detailLink;

    /**
     * Gets task id.
     *
     * @return the task id
     */
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
        this.setSidebarTypeId( taskId );
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