package com.mana.innovative.dao.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.client.WorkingHourDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.WorkingHour;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Working hour dAO impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class WorkingHourDAOImpl extends BasicDAO implements WorkingHourDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WorkingHourDAOImpl.class );

    /**
     * Gets workingHour by workingHour id.
     *
     * @param workingHourId the workingHour id
     * @param isError the is error
     * @return the workingHour by workingHour id
     */
    @SuppressWarnings( "unchecked" )
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
            this.closeDBTransaction( );

            if ( !workingHours.isEmpty( ) && workingHours.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " WorkingHour Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from workingHours table " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
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
     * @param isError the is error
     * @return List<WorkingHour>   </> Return a list of
     */
    @SuppressWarnings( "unchecked" )
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
            this.closeDBTransaction( );

        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from workingHours table for " + location,
                    exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
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
     * @param isError the is error
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
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
            this.closeDBTransaction( );
            workingHourDAOResponse.setRequestSuccess( true );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to delete workingHour", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }

        workingHourDAOResponse.setResults( null );
        workingHourDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }

    /**
     * Delete working hours by working hr ids.
     *
     * @param workingHourIds the working hour ids
     * @param isError the is error
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
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
            this.closeDBTransaction( );
            workingHourDAOResponse.setRequestSuccess( true );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to delete workingHours with given ids " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            workingHourDAOResponse.setRequestSuccess( false );
        }

        workingHourDAOResponse.setResults( null );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }

    /**
     * Delete all working hours.
     *
     * @param deleteAllWorkingHrs the delete all working hrs
     * @param isError the is error
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< WorkingHour > deleteAllWorkingHours( final boolean deleteAllWorkingHrs, final boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllWorkingHours()";
        logger.debug( "Starting " + location );
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse.setDelete( true );
        workingHourDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        if ( deleteAllWorkingHrs ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all WorkingHour class rows stored in DB will not delete anything from workingHour class
                // Note: and it should not as a workingHour may or may not have any workingHours so workingHour class will still persist
                Query query = session.createQuery( "delete from WorkingHour" );
                workingHourDAOResponse.setCount( query.executeUpdate( ) );
                this.closeDBTransaction( );
                workingHourDAOResponse.setRequestSuccess( true );

            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                logger.error( "Error occurred while trying to clear workingHours table " + location, exception );
                if ( isError ) {
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                workingHourDAOResponse.setRequestSuccess( false );
            }
        }
        workingHourDAOResponse.setResults( null );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }

    /**
     * Create dAO response.
     *
     * @param workingHour the working hour
     * @param isError the is error
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< WorkingHour > createWorkingHour( final WorkingHour workingHour, final boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createWorkingHour()";
        logger.debug( "Starting " + location );
        List< WorkingHour > workingHours = new ArrayList<>( );
        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse.setCreate( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            session.save( workingHour );
            this.closeDBTransaction( );

            workingHourDAOResponse.setCount( DAOConstants.ONE );
            workingHourDAOResponse.setRequestSuccess( true );
            workingHours.add( workingHour );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to create an workingHour " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            workingHourDAOResponse.setRequestSuccess( Boolean.FALSE );
            workingHourDAOResponse.setCount( DAOConstants.ZERO );
        }
        workingHourDAOResponse.setResults( workingHours );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }

    /**
     * Update workingHour.
     *
     * @param workingHour the workingHour
     * @param isError the is error
     * @return the dAO response
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< WorkingHour > updateWorkingHour( final WorkingHour workingHour, boolean isError ) {

        if ( workingHour == null ) {
            throw new NullPointerException( "Update object WorkingHour is null" );
        }
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateWorkingHour()";
        logger.debug( "Starting " + location );

        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );
        List< WorkingHour > workingHours = null;

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from WorkingHour where workingHourId=:workingHourId" );
            query.setParameter( "workingHourId", workingHour.getWorkingHourId( ) );
            workingHours = query.list( );
            if ( workingHours.size( ) != DAOConstants.ONE ) {
                throw new IllegalArgumentValueException( "WorkingHour List Size is different to expected Size" );
            }
            WorkingHour dbWorkingHour = workingHours.get( DAOConstants.ZERO );
            this.updateShopWorkingHour( workingHour, dbWorkingHour );
            this.closeDBTransaction( );
            this.openDBTransaction( );
            session.update( workingHour );
            this.closeDBTransaction( );
            workingHourDAOResponse.setCount( DAOConstants.ONE );
            workingHours = new ArrayList<>( );
            workingHours.add( workingHour );
            workingHourDAOResponse.setCount( DAOConstants.ONE );
            workingHourDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to update workingHour", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }

        workingHourDAOResponse.setResults( workingHours );
        workingHourDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return workingHourDAOResponse;
    }
}
