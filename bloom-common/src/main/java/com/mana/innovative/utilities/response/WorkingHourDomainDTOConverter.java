package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.WorkingHour;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Bloom/Rono on 3/5/2015. This class is for .. ToDo
 */
public class WorkingHourDomainDTOConverter {

    private static final Logger logger = Logger.getLogger( WorkingHourDomainDTOConverter.class );
    private static final int ZERO = DAOConstants.ZERO;
    private static final int ONE = DAOConstants.ONE;
    private static final String STRING_ZERO = DAOConstants.STRING_ZERO;
    private static final int FOUR = 4;


    /**
     * Gets converted dTO from domain.
     *
     * @param workingHourDomain the workingHour domain
     *
     * @return the converted dTO from domain
     */
    public static WorkingHour getConvertedDTOFromDomain( com.mana.innovative.domain.WorkingHour workingHourDomain ) {

        if ( workingHourDomain == null ) {
            String message = "workingHourDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        WorkingHour workingHourDTO = new WorkingHour( );

        if ( workingHourDomain.getWorkingHourId( ) > ZERO ) {
            workingHourDTO.setWorkingHourId( workingHourDomain.getWorkingHourId( ) );
        }
        if ( !StringUtils.isEmpty( workingHourDomain.getDay( ) ) ) {
            workingHourDTO.setDay( workingHourDomain.getDay( ) );
        }
        if ( workingHourDomain.getStartTime( ) != null ) {

            workingHourDTO.setStartTime( toStringTimeFromDate( workingHourDomain.getStartTime( ) ) );
        }
        if ( workingHourDomain.getEndTime( ) != null ) {
            workingHourDTO.setEndTime( toStringTimeFromDate( workingHourDomain.getEndTime( ) ) );
        }

        workingHourDTO.setHoliday( workingHourDomain.isHoliday( ) );
        workingHourDTO.setOffline( workingHourDomain.isOffline( ) );
        workingHourDTO.setWeekend( workingHourDomain.isWeekend( ) );

        return workingHourDTO;
    }


    /**
     * Gets converted list domain from dTO.
     *
     * @param workingHoursDTO the workingHours dTO
     *
     * @return the converted list domain from dTO
     */
    public static List< com.mana.innovative.domain.WorkingHour > getConvertedListDomainFromDTO( List< WorkingHour > workingHoursDTO ) {

        List< com.mana.innovative.domain.WorkingHour > workingHoursDomain = new ArrayList<>( );
        for ( WorkingHour workingHourDTO : workingHoursDTO ) {
            com.mana.innovative.domain.WorkingHour workingHourDomain = getConvertedDomainFromDTO( workingHourDTO );
            workingHoursDomain.add( workingHourDomain );
        }
        return workingHoursDomain;
    }

    /**
     * Gets converted domain from dTO.
     *
     * @param workingHourDTO the workingHour dTO
     *
     * @return the converted domain from dTO
     */
    public static com.mana.innovative.domain.WorkingHour getConvertedDomainFromDTO( WorkingHour workingHourDTO ) {

        if ( workingHourDTO == null ) {
            String message = "WorkingHourDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        com.mana.innovative.domain.WorkingHour workingHourDomain = new com.mana.innovative.domain.WorkingHour( );
        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm" );

        if ( !StringUtils.isEmpty( workingHourDTO.getDay( ) ) ) {
            workingHourDomain.setDay( workingHourDTO.getDay( ) );
        } else {
            flag = true;
            stringBuilder.append( " day," );
        }
        try {
            if ( !StringUtils.isEmpty( workingHourDTO.getStartTime( ) ) ) {
                workingHourDomain.setStartTime( simpleDateFormat.parse( workingHourDTO.getStartTime( ) ) );
            } else {
                flag = true;
                stringBuilder.append( " startTime," );
            }
            if ( !StringUtils.isEmpty( workingHourDTO.getEndTime( ) ) ) {
                workingHourDomain.setEndTime( simpleDateFormat.parse( workingHourDTO.getEndTime( ) ) );
            } else {
                flag = true;
                stringBuilder.append( " endTime," );
            }
        } catch ( ParseException exception ) {
            flag = true;
            stringBuilder.append( " startTime/endTime Parse Exception," );
            logger.error( "Failed to Parse String to Date", exception );
        }
        workingHourDomain.setHoliday( workingHourDTO.isHoliday( ) );
        workingHourDomain.setOffline( workingHourDTO.isOffline( ) );
        workingHourDomain.setWeekend( workingHourDTO.isWeekend( ) );

        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( );
        }
        logger.info( stringBuilder.toString( ) );
        return workingHourDomain;
    }

    /**
     * Gets converted list dTO from domain.
     *
     * @param workingHoursDomain the workingHours domain
     *
     * @return the converted list dTO from domain
     */
    public static List< WorkingHour > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.WorkingHour > workingHoursDomain ) {

        List< WorkingHour > workingHoursDTO = new ArrayList<>( );
        for ( com.mana.innovative.domain.WorkingHour workingHourDomain : workingHoursDomain ) {
            WorkingHour workingHourDTO = getConvertedDTOFromDomain( workingHourDomain );
            workingHoursDTO.add( workingHourDTO );
        }
        return workingHoursDTO;
    }

    /**
     * To string time from date.
     *
     * @param date the date
     *
     * @return the string
     */
    public static String toStringTimeFromDate( Date date ) {

        GregorianCalendar calendar = new GregorianCalendar( );
        calendar.setTime( date );
        String time = Integer.toString( calendar.get( GregorianCalendar.HOUR ) );
        if ( time.length( ) == ONE ) {
            time = time.concat( STRING_ZERO );
        }
        time += DAOConstants.TIME_SEPARATOR_FORMAT.concat( Integer.toString( calendar.get( GregorianCalendar.MINUTE ) ) );

        if ( time.length( ) == FOUR ) {
            time = time.substring( ZERO, time.length( ) - ONE )
                    .concat( STRING_ZERO )
                    .concat( time.substring( time.length( ) - ONE, time.length( ) ) );
        }
        return time;
    }

}
