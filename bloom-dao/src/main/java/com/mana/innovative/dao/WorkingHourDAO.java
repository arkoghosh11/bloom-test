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
 * Created by Bloom/Rono on 3/3/2015. This class is for .. ToDo
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class WorkingHourDAO extends BasicDAO {

    private static final Logger log = Logger.getLogger( WorkingHourDAO.class );

    static {
        log.setLevel( Level.INFO );
    }

    /**
     * Gets workingHour by workingHour id.
     *
     * @param workingHourId the workingHour id
     * @param isError       the is error
     *
     * @return the workingHour by workingHour id
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< WorkingHour > getWorkingHourByWorkingHourId( long workingHourId, boolean isError ) {

        List< WorkingHour > workingHours = null;
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;

        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }

        log.info( "**Inside itemDAO.getWorkingHours()***" );
        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from WorkingHour where workingHourId" + " = :workingHour_id" );
            query.setLong( "workingHour_id", workingHourId );
//            transaction.commit();
            workingHours = query.list( );
            if ( !workingHours.isEmpty( ) && workingHours.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " WorkingHour Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            exception.printStackTrace( );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to fetch data from items table for workingHourDAO" + ".getItems()",
                    exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#getWorkingHourByWorkingHourId()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        workingHourDAOResponse.setCount( workingHours == null ? 0 : workingHours.size( ) );
        workingHourDAOResponse.setResults( workingHours );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        return workingHourDAOResponse;
    }

    /**
     * This method is to retrieve all the workingHours values from the DB
     *
     * @return List<WorkingHour></> Return a list of {@link WorkingHour}
     */

    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< WorkingHour > getWorkingHours( boolean isError ) {

        List< WorkingHour > workingHours = null;
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;

        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }

        log.info( "**Inside workingHourDAO.getWorkingHours()***" );
        try {
            this.openDBTransaction( );
            workingHours = ( List< WorkingHour > ) session.createQuery( " from WorkingHour" ).list( );
//            transaction.commit();
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            log.error( "Error occurred while trying to fetch data from workingHours table for workingHourDAO" + ".getWorkingHours()",
                    exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#getWorkingHours()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        workingHourDAOResponse.setCount( workingHours == null ? 0 : workingHours.size( ) );
        workingHourDAOResponse.setResults( workingHours );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        return workingHourDAOResponse;
    }
}
