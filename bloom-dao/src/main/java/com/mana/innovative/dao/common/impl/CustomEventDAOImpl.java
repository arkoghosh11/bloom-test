package com.mana.innovative.dao.common.impl;

import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.common.CustomEventDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.dto.request.RequestParams;
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
import java.util.Date;
import java.util.List;

/**
 * The type Custom event dAO impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class CustomEventDAOImpl extends BasicDAO implements CustomEventDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomEventDAOImpl.class );

    /**
     * Gets event by id.
     *
     * @param customEventId the custom event id
     * @param requestParams the request params
     *
     * @return the event by id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CustomEvent > getEventById( final long customEventId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getEventById()";

        logger.debug( "Starting " + location );
        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams != null && requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( "from CustomEvent where customEventId=:eventId" );
            query.setParameter( "eventId", customEventId );
            customEventList = query.list( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setCount( customEventList.size( ) );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customEventDAOResponse;
    }

    /**
     * Gets events by date.
     *
     * @param eventDate     the event date
     * @param requestParams the request params
     *
     * @return the events by date
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CustomEvent > getEventsByDate( final Date eventDate, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getEventsByDate()";

        logger.debug( "Starting " + location );
        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( "from CustomEvent where eventDate=:eventDate" );
            query.setParameter( "eventDate", eventDate );
            customEventList = query.list( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setCount( customEventList.size( ) );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location, customEventDAOResponse.getResults( ).toArray( ) );

        return customEventDAOResponse;
    }

    /**
     * Gets events by event name.
     *
     * @param eventName     the event name
     * @param requestParams the request params
     *
     * @return the events by event name
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CustomEvent > getEventsByEventName( final String eventName, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getEventsByEventName()";

        logger.debug( "Starting " + location );

        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( "from CustomEvent where eventName=:eventName" );
            query.setParameter( "eventName", eventName );
            customEventList = query.list( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setCount( customEventList.size( ) );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customEventDAOResponse;
    }

    /**
     * Gets all events.
     *
     * @param requestParams the request params
     *
     * @return the all events
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CustomEvent > getAllEvents( final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getAllEvents()";
        logger.debug( "Starting " + location );
        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( "from CustomEvent" );
            customEventList = query.list( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setCount( customEventList.size( ) );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customEventDAOResponse;
    }

    /**
     * Update event.
     *
     * @param customEvent   the custom event
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< CustomEvent > updateEvent( final CustomEvent customEvent, final RequestParams requestParams
    ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getAllEvents()";
        logger.debug( "Starting " + location );
        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            session.update( customEvent );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );
            customEventList.add( customEvent );

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setCount( customEventList.size( ) );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customEventDAOResponse;
    }

    /**
     * Enable event scheduler for date.
     *
     * @param date          the date
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< CustomEvent > enableEventSchedulerForDate( Date date, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#enableEventSchedulerForDate()";
        logger.debug( "Starting " + location );
        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( "update CustomEvent set isScheduler=:newValue where eventDate=:customDate" );
            query.setBoolean( "newValue", Boolean.TRUE );
            query.setDate( "customDate", date );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );
            customEventDAOResponse.setCount( count );
            customEventList = null;

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customEventDAOResponse;
    }

    /**
     * Disable event scheduler for date.
     *
     * @param date          the date
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< CustomEvent > disableEventSchedulerForDate( Date date, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#disableEventSchedulerForDate()";
        logger.debug( "Starting " + location );
        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( "update CustomEvent set isScheduler=:newValue where eventDate=:customDate" );
            query.setBoolean( "newValue", Boolean.FALSE );
            query.setDate( "customDate", date );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );
            customEventDAOResponse.setCount( count );
            customEventList = null;

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customEventDAOResponse;
    }

    /**
     * Gets events by date range.
     *
     * @param eventStartTime the event start time
     * @param eventEndTime   the event end time
     * @param requestParams  the request params
     *
     * @return the events by date range
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< CustomEvent > getEventsByDateRange( final Date eventStartTime, final Date eventEndTime, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getEventsByDateRange()";
        logger.debug( "Starting " + location );

        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        List< CustomEvent > customEventList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {

            this.openDBTransaction( );

            Query query = session.createQuery( " from CustomEvent where eventDate>=:eventStartTime and eventDate <= " +
                    ":eventEndTime" );
            query.setParameter( "eventStartTime", eventStartTime );
            query.setParameter( "eventEndTime", eventEndTime );
            customEventList = query.list( );

            this.closeDBTransaction( );
            customEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( Exception exception ) {
            customEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from custom_events table", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customEventDAOResponse.setResults( customEventList );
        customEventDAOResponse.setCount( customEventList.size( ) );
        customEventDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return customEventDAOResponse;
    }
}
