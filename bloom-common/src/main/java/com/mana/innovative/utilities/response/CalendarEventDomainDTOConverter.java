package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is CalendarEventDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class CalendarEventDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CalendarEventDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;
    /**
     * The constant EMPTY_STRING.
     */
    private static final String EMPTY_STRING = DAOConstants.EMPTY_STRING;

    /**
     * Gets converted calendarEvent dTO from calendarEvent domain.
     *
     * @param calendarEventDTO    the calendarEvent dTO
     * @param calendarEventDomain the calendarEvent domain
     *
     * @return the converted calendarEvent dTO from calendarEvent domain
     */
    public static CalendarEvent getConvertedDTOFromDomain( CalendarEvent calendarEventDTO, com.mana.innovative.domain.common.CalendarEvent calendarEventDomain ) {

        if ( calendarEventDomain == null ) {
            String message = "Parameter calendarEventDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( calendarEventDTO == null ) {
            calendarEventDTO = new CalendarEvent( );
            logger.warn( " Creating calendarEventDTO, received null object" );
        }
        if ( calendarEventDomain.getCalendarEventId( ) >= ZERO ) {
            calendarEventDTO.setCalendarEventId( calendarEventDomain.getCalendarEventId( ) );
        }
        calendarEventDTO.setEventName( calendarEventDomain.getEventName( ) );
        calendarEventDTO.setEventDescription( calendarEventDomain.getEventDescription( ) );
        calendarEventDTO.setEventStartDate( calendarEventDomain.getEventStartDate( ) );
        calendarEventDTO.setEventEndDate( calendarEventDomain.getEventEndDate( ) );
        calendarEventDTO.setName( calendarEventDomain.getName( ) );
        calendarEventDTO.setOptional( calendarEventDomain.getOptional( ) );

//            calendarEvent.setShopCalendarEvent();
        return calendarEventDTO;
    }

    /**
     * Gets converted calendarEvent dTO list.
     *
     * @param calendarEventDomainList the calendarEvent domain list
     *
     * @return the converted calendarEvent dTO list
     */
    public static List< CalendarEvent > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.common.CalendarEvent > calendarEventDomainList ) {

        List< CalendarEvent > calendarEventDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.common.CalendarEvent calendarEvent : calendarEventDomainList ) {

            CalendarEvent calendarEventDTO = new CalendarEvent( );
            calendarEventDTO = getConvertedDTOFromDomain( calendarEventDTO, calendarEvent );
            calendarEventDTOList.add( calendarEventDTO );
        }
        return calendarEventDTOList;
    }

    /**
     * Gets converted calendarEvent domain from calendarEvent dTO.
     *
     * @param calendarEventDomain the calendarEvent domain
     * @param calendarEventDTO    the calendarEvent dTO
     *
     * @return the converted calendarEvent domain from calendarEvent dTO
     */
    public static com.mana.innovative.domain.common.CalendarEvent getConvertedDomainFromDTO( com.mana.innovative.domain.common.CalendarEvent calendarEventDomain, CalendarEvent calendarEventDTO ) {

        if ( calendarEventDTO == null ) {
            String message = "Parameter calendarEventDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( calendarEventDomain == null ) {
            calendarEventDomain = new com.mana.innovative.domain.common.CalendarEvent( );
            logger.warn( "Creating calendarEventDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( calendarEventDTO.getEventName( ) ) ) {
            calendarEventDomain.setEventName( calendarEventDTO.getEventName( ) );
        } else {
            flag = true;
            stringBuilder.append( "EventName, " );
        }

        if ( !StringUtils.isEmpty( calendarEventDTO.getEventDescription( ) ) ) {
            calendarEventDomain.setEventDescription( calendarEventDTO.getEventDescription( ) );
        } else calendarEventDomain.setEventDescription( EMPTY_STRING );

        if ( !StringUtils.isEmpty( calendarEventDTO.getEventStartDate( ) ) ) {
            calendarEventDomain.setEventStartDate( calendarEventDTO.getEventStartDate( ) );
        } else {
            flag = true;
            stringBuilder.append( "EventStartDate, " );
        }

        if ( !StringUtils.isEmpty( calendarEventDTO.getEventEndDate( ) ) ) {
            calendarEventDomain.setEventEndDate( calendarEventDTO.getEventEndDate( ) );
        } else {
            flag = true;
            stringBuilder.append( "EventEndDate." );
        }

        if ( !StringUtils.isEmpty( calendarEventDTO.getName( ) ) ) {
            calendarEventDomain.setName( calendarEventDTO.getName( ) );
        } else calendarEventDomain.setName( EMPTY_STRING );

        if ( !StringUtils.isEmpty( calendarEventDTO.getOptional( ) ) ) {
            calendarEventDomain.setOptional( calendarEventDTO.getOptional( ) );
        } else calendarEventDomain.setOptional( EMPTY_STRING );

        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return calendarEventDomain;
    }

    /**
     * Gets converted calendarEvent domain list from calendarEvent dTO list.
     *
     * @param calendarEventDTOList the calendarEvent dTO list
     *
     * @return the converted calendarEvent domain list from calendarEvent dTO list
     */
    public static List< com.mana.innovative.domain.common.CalendarEvent > getConvertedListDomainFromDTO( List< CalendarEvent > calendarEventDTOList ) {

        List< com.mana.innovative.domain.common.CalendarEvent > calendarEventDomainList = new ArrayList<>( );
        for ( CalendarEvent calendarEventDTO : calendarEventDTOList ) {
            com.mana.innovative.domain.common.CalendarEvent calendarEventDomain = new com.mana.innovative.domain.common.CalendarEvent( );
            calendarEventDomain = getConvertedDomainFromDTO( calendarEventDomain, calendarEventDTO );
            calendarEventDomainList.add( calendarEventDomain );
        }
        return calendarEventDomainList;
    }


}
