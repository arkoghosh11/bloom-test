package com.mana.innovative.dto;/**
 * Created by Rono on 2/27/2015.
 * This is a class for .. todo 
 */

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Bloom on 2/27/2015 : 2:47 PM
 * todo This class is for ...
 */
@XmlRootElement
public class WorkingHour {

    private long workingHourId;

    private String day;
    private String startTime;
    private String endTime;

    private boolean isOffline;
    private boolean isHoliday;
    private boolean isWeekend;


    private Shop shopWorkingHour;
}
