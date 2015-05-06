package com.mana.innovative.service.common.builder;


import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.payload.CalendarEventsPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.common.container.CalendarEventResponseContainer;
import com.mana.innovative.utilities.response.CalendarEventDomainDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 1/2/13 Time: 4:23 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
public class CalendarEventResponseBuilder {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CalendarEventResponseBuilder.class );

    /**
     * Build calendarEvent response container.
     *
     * @param calendarEventDAOResponse the calendarEvent dAO response
     * @param isError the is error
     * @return Returns a response object for calendarEvents
     */
    public static CalendarEventResponseContainer< CalendarEventsPayload > build( DAOResponse< com.mana.innovative.domain.common.CalendarEvent >
                                                                                         calendarEventDAOResponse, boolean isError ) {

        if ( calendarEventDAOResponse == null ) {
            calendarEventDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter calendarEventDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of CalendarEvent Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( calendarEventDAOResponse.getResults( ) == null || calendarEventDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !calendarEventDAOResponse.isDelete( ) && !calendarEventDAOResponse.isRequestSuccess( ) ) {
                calendarEventDAOResponse.setCount( DAOConstants.ZERO );
            }
            calendarEventDAOResponse
                    .setResults( new ArrayList< com.mana.innovative.domain.common.CalendarEvent >( ) );
        }
        String location = "CalendarEventResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< com.mana.innovative.domain.common.CalendarEvent > calendarEvents = calendarEventDAOResponse.getResults( );
        CalendarEventResponseContainer< CalendarEventsPayload > calendarEventResponseContainer = new CalendarEventResponseContainer<>( );
        CalendarEventsPayload calendarEventsPayload = new CalendarEventsPayload( );
        ErrorContainer errorContainer = calendarEventDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            calendarEventsPayload.setCalendarEvents( CalendarEventDomainDTOConverter.getConvertedListDTOFromDomain( calendarEvents ) );
        } catch ( IllegalArgumentValueException e ) {
            logger.error( "Failed due to invalid values", e );
            errorContainer.addError( new com.mana.innovative.exception.response.Error( location, e ) );
        } catch ( NullPointerException e ) {
            logger.error( "Failed due to null object", e );
            errorContainer.addError( new Error( location, e ) );
        } catch ( Exception e ) {
            logger.error( "Unexpected error while converting domain to dto object", e );
            errorContainer.addError( new Error( location, e ) );
        }
        calendarEventsPayload.setTotalCount( calendarEventDAOResponse.getCount( ) );
        calendarEventResponseContainer.setPayload( calendarEventsPayload );
        calendarEventResponseContainer.setCount( calendarEventDAOResponse.getCount( ) );
        calendarEventResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of CalendarEvent Response" );
        return calendarEventResponseContainer;
    }

    /**
     * Build error.
     *
     * @param location the location of the error
     * @param isError the is error
     * @param exception the exception
     * @return the calendarEvent response container
     */
    public static CalendarEventResponseContainer< CalendarEventsPayload > buildError( String location, boolean isError,
                                                                                      Exception exception ) {

        logger.debug( "Starting building of Error for CalendarEvent Response" );
        CalendarEventsPayload calendarEventsPayload = new CalendarEventsPayload( );
        calendarEventsPayload.setCalendarEvents( new ArrayList< com.mana.innovative.dto.common.CalendarEvent >( ) );
        CalendarEventResponseContainer< CalendarEventsPayload > calendarEventResponseContainer = new CalendarEventResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            com.mana.innovative.exception.response.Error error = new Error( location, exception );
            errorContainer.addError( error );
            calendarEventResponseContainer.setErrorContainer( errorContainer );
        }
        calendarEventResponseContainer.setIsError( true );
        calendarEventResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of CalendarEvent Response" );
        return calendarEventResponseContainer;
    }
}
