package com.mana.innovative.dao;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.WorkingHour;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Working hour dAO.
 */
@Repository
public interface WorkingHourDAO {

    /**
     * Gets working hour by working hour id.
     *
     * @param workingHourId the working hour id
     * @param isError the is error
     * @return the working hour by working hour id
     */
    DAOResponse< WorkingHour > getWorkingHourByWorkingHourId( long workingHourId, boolean isError );

    /**
     * Gets working hours.
     *
     * @param isError the is error
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
}
