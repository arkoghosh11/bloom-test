package com.mana.innovative.dao.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.WorkingHourDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.WorkingHour;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Working hour dAO impl.
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class WorkingHourDAOImpl extends BasicDAO implements WorkingHourDAO {

    private static final Logger logger = Logger.getLogger( WorkingHourDAOImpl.class );

    /**
     * Gets workingHour by workingHour id.
     *
     * @param workingHourId the workingHour id
     * @param isError       the is error
     *
     * @return the workingHour by workingHour id
     */
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< WorkingHour > getWorkingHourByWorkingHourId( long workingHourId, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getWorkingHourByWorkingHourId()";
        logger.debug( "Starting " + location );

        List< WorkingHour > workingHours = null;
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

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
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from items table " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        workingHourDAOResponse.setCount( workingHours == null ? DAOConstants.ZERO : workingHours.size( ) );
        workingHourDAOResponse.setResults( workingHours );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }

    /**
     * This method is to retrieve all the workingHours values from the DB
     *
     * @return List<WorkingHour></> Return a list of {@link WorkingHour}
     */
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< WorkingHour > getWorkingHours( boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getWorkingHours()";
        logger.debug( "Starting " + location );
        List< WorkingHour > workingHours = null;
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            workingHours = ( List< WorkingHour > ) session.createQuery( " from WorkingHour" ).list( );
//            transaction.commit();
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from workingHours table for " + location,
                    exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        workingHourDAOResponse.setCount( workingHours == null ? DAOConstants.ZERO : workingHours.size( ) );
        workingHourDAOResponse.setResults( workingHours );
        workingHourDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }

    /**
     * Delete working hour by working hr ids.
     *
     * @param workingHourId the working hour id
     * @param isError       the is error
     *
     * @return the dAO response
     */
    @Override
    public DAOResponse< WorkingHour > deleteWorkingHourByWorkingHrId( final long workingHourId, final boolean
            isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteWorkingHourByWorkingHrId()";
        logger.debug( "Starting " + location );

        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from WorkingHour where workingHourId=:workingHourId" );
            query.setParameter( "workingHourId", workingHourId );
            workingHourDAOResponse.setCount( query.executeUpdate( ) );
            workingHourDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to delete workingHour", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        workingHourDAOResponse.setResults( null );
        workingHourDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return null;
    }

    /**
     * Delete working hours by working hr ids.
     *
     * @param workingHourIds the working hour ids
     * @param isError        the is error
     *
     * @return the dAO response
     */
    @Override
    public DAOResponse< WorkingHour > deleteWorkingHoursByWorkingHrIds( List< Long > workingHourIds, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteWorkingHoursByWorkingHrIds()";
        logger.debug( "Starting " + location );
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from WorkingHour where workingHourId in (:workingHourIds)" );
            query.setParameterList( "workingHourIds", workingHourIds );
            workingHourDAOResponse.setCount( query.executeUpdate( ) );
            workingHourDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to delete workingHours with given ids " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            workingHourDAOResponse.setRequestSuccess( false );
        } finally {
            this.closeDBTransaction( );
        }
        workingHourDAOResponse.setResults( null );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return null;
    }

}
