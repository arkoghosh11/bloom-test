package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.WorkingHour;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Working hour dAO.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface WorkingHourDAO {

    /**
     * Gets working hour by working hour id.
     *
     * @param workingHourId the working hour id
     * @param isError       the is error
     *
     * @return the working hour by working hour id
     */
    DAOResponse< WorkingHour > getWorkingHourByWorkingHourId( long workingHourId, boolean isError );

    /**
     * Gets working hours.
     *
     * @param isError the is error
     *
     * @return the working hours
     */
    DAOResponse< WorkingHour > getWorkingHours( boolean isError );

    /**
     * Delete working hour by working hr ids.
     *
     * @param workingHourId the working hour id
     * @param isError       the is error
     *
     * @return the dAO response
     */
    DAOResponse< WorkingHour > deleteWorkingHourByWorkingHrId( long workingHourId, boolean
            isError );

    /**
     * Delete working hours by working hr ids.
     *
     * @param workingHourIds the working hour ids
     * @param isError        the is error
     *
     * @return the dAO response
     */
    DAOResponse< WorkingHour > deleteWorkingHoursByWorkingHrIds( List< Long > workingHourIds, boolean isError );

    /**
     * Delete all working hours.
     *
     * @param deleteAllWorkingHrs the delete all working hrs
     * @param isError             the is error
     *
     * @return the dAO response
     */
    DAOResponse< WorkingHour > deleteAllWorkingHours( boolean deleteAllWorkingHrs, boolean isError );

    /**
     * Create dAO response.
     *
     * @param workingHour the working hour
     * @param isError     the is error
     *
     * @return the dAO response
     */
    DAOResponse< WorkingHour > createWorkingHour( WorkingHour workingHour, boolean isError );


    /**
     * Update working hour.
     *
     * @param workingHour the working hour
     * @param isError     the is error
     *
     * @return the dAO response
     */
    DAOResponse< WorkingHour > updateWorkingHour( WorkingHour workingHour, boolean isError );
}
