package com.mana.innovative.dao.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.common.CalendarEventDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.CalendarEvent;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.ListEmptyException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Calendar event dAO impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class CalendarEventDAOImpl extends BasicDAO implements CalendarEventDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CalendarEventDAOImpl.class );

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * Gets calendar events.
     *
     * @return the calendar events
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > getCalendarEvents( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCalendarEvents()";

        logger.debug( "Starting " + location );
        List< CalendarEvent > calendarEvents = new ArrayList<>( );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from CalendarEvent " );
            calendarEvents = query.list( );
            this.closeDBTransaction( );
            calendarEventDAOResponse.setResults( calendarEvents );
            calendarEventDAOResponse.setCount( calendarEvents.size( ) );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( calendarEvents );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Gets calendar events by date limits.
     *
     * @param startDate the start date
     * @param endDate   the end date
     *
     * @return the calendar events by date limits
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > getCalendarEventsByDateLimits( Date startDate, Date endDate,
                                                                       RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCalendarEventsByDateLimits()";

        logger.debug( "Starting " + location );
        List< CalendarEvent > calendarEvents = new ArrayList<>( );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );
            Criteria criteria = session.createCriteria( CalendarEvent.class )
                    .add( Restrictions.ge( "eventStartDate", startDate ) )
                    .add( Restrictions.le( "eventEndDate", endDate ) );
            calendarEvents = criteria.list( );
            this.closeDBTransaction( );
            calendarEventDAOResponse.setResults( calendarEvents );
            calendarEventDAOResponse.setCount( calendarEvents.size( ) );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( calendarEvents );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Gets calendar event by event id.
     *
     * @param eventId       the event id
     * @param requestParams the request params
     *
     * @return the calendar event by event id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > getCalendarEventByEventId( long eventId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCalendarEventByEventId()";

        logger.debug( "Starting " + location );
        List< CalendarEvent > calendarEvents = new ArrayList<>( );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( " from CalendarEvent  where calendarEventId=:eventId" );
            query.setParameter( "eventId", eventId );
            calendarEvents = query.list( );

            this.closeDBTransaction( );

            calendarEventDAOResponse.setResults( calendarEvents );
            calendarEventDAOResponse.setCount( calendarEvents.size( ) );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed while getting data from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( calendarEvents );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Create calendar event.
     *
     * @param calendarEvent the calendar event
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > createCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCalendarEvent()";

        logger.debug( "Starting " + location );
        List< CalendarEvent > calendarEvents = new ArrayList<>( );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            session.save( calendarEvent );

            this.closeDBTransaction( );

            calendarEvents.add( calendarEvent );
            calendarEventDAOResponse.setResults( calendarEvents );
            calendarEventDAOResponse.setCount( calendarEvents.size( ) );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( calendarEvents );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Update calendar event.
     *
     * @param calendarEvent the calendar event
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > updateCalendarEvent( CalendarEvent calendarEvent, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updateCalendarEvent()";

        logger.debug( "Starting " + location );
        List< CalendarEvent > calendarEvents = new ArrayList<>( );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );
            // todo might need to change logic here similar to {@link ShopDAO.updateShop()}
            session.update( calendarEvent );

            this.closeDBTransaction( );

            calendarEventDAOResponse.setResults( calendarEvents );
            calendarEventDAOResponse.setCount( calendarEvents.size( ) );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while updating or getting data for update from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( calendarEvents );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Delete calendar event by event id.
     *
     * @param calendarEventId the calendar event id
     * @param requestParams   the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > deleteCalendarEventByEventId( long calendarEventId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCalendarEventByEventId()";

        logger.debug( "Starting " + location );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( " delete from CalendarEvent  where calendarEventId=:eventId" );
            query.setParameter( "eventId", calendarEventId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            calendarEventDAOResponse.setResults( null );
            calendarEventDAOResponse.setCount( count );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( null );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Delete calendar events.
     *
     * @param calendarEventIds the calendar event ids
     * @param requestParams    the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > deleteCalendarEvents( final List< Long > calendarEventIds, RequestParams requestParams ) {

        if ( calendarEventIds.isEmpty( ) ) {
            throw new ListEmptyException( "calendarIds list for deletion is empty" );
        }
        String location = this.getClass( ).getCanonicalName( ) + "#deleteCalendarEvents()";

        logger.debug( "Starting " + location );
        DAOResponse< CalendarEvent > calendarEventDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( " delete from CalendarEvent  where calendarEventId in(:eventIds)" );
            query.setParameterList( "eventIds", calendarEventIds );
            int count = query.executeUpdate( );
            this.closeDBTransaction( );

            calendarEventDAOResponse.setResults( null );
            calendarEventDAOResponse.setCount( count );
            calendarEventDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from calendar_events table", exception );
            calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
            calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            calendarEventDAOResponse.setResults( null );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                calendarEventDAOResponse.setErrorContainer( errorContainer );
            }
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Delete all calendar events.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CalendarEvent > deleteAllCalendarEvents( RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllCalendarEvents()";

        logger.debug( "Starting " + location );
        DAOResponse< CalendarEvent > calendarEventDAOResponse =
                new DAOResponse<>( );

        calendarEventDAOResponse.setRequestSuccess( Boolean.FALSE );
        calendarEventDAOResponse.setCount( DAOConstants.ZERO );
        calendarEventDAOResponse.setResults( null );

        logger.warn( "Accessing" + location + "\nThis method is not allowed for execution under any circumstance\n" +
                " to purge all data please contact a system or db administrator" );
        if ( requestParams.isError( ) ) {

            ErrorContainer errorContainer = fillErrorContainer( location, new Exception( "Unauthorized" ) );
            calendarEventDAOResponse.setErrorContainer( errorContainer );
        }
        logger.debug( "Finishing " + location );
        return calendarEventDAOResponse;
    }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory( ) {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory( SessionFactory sessionFactory ) {

        this.sessionFactory = sessionFactory;
    }
}

