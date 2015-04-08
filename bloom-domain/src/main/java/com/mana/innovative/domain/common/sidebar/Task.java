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
@DiscriminatorValue( value = "taskItem" )
public class Task extends SidebarType {

    @GeneratedValue
    @Column( name = "task_id" )
    private int taskId;

    @Column( name = "detail_link" )
    private String detailLink;

    public int getTaskId( ) {
        return taskId;
    }

    public void setTaskId( final int taskId ) {
        this.taskId = taskId;
        this.setSidebarTypeId( taskId );
    }

    public String getDetailLink( ) {
        return detailLink;
    }

    public void setDetailLink( final String detailLink ) {
        this.detailLink = detailLink;
    }
}