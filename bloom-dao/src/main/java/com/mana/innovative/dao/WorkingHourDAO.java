package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.WorkingHour;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
