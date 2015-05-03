package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.consumer.Preference;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is PreferenceDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class PreferenceDomainDTOConverter {

    private static final Logger logger = LoggerFactory.getLogger( PreferenceDomainDTOConverter.class );

    private static final long ZERO = DAOConstants.ZERO;

    /**
     * Gets converted preference dTO from preference domain.
     *
     * @param preferenceDTO    {@link Preference} the preference dTO
     * @param preferenceDomain {@link com.mana.innovative.domain.consumer.Preference} the preference domain
     *
     * @return the converted preference dTO from preference domain
     */
    public static Preference getConvertedDTOFromDomain( Preference preferenceDTO, com.mana.innovative.domain.consumer.Preference preferenceDomain ) {

        if ( preferenceDomain == null ) {
            String message = "Parameter preferenceDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( preferenceDTO == null ) {
            preferenceDTO = new Preference( );
            logger.warn( " Creating preferenceDTO, received null object" );
        }
        if ( preferenceDomain.getPreferenceId( ) >= ZERO ) {
            preferenceDTO.setPreferenceId( preferenceDomain.getPreferenceId( ) );
        }

        preferenceDTO.setPreferred( preferenceDomain.isPreferred( ) );
        preferenceDTO.setPreferenceName( preferenceDomain.getPreferenceName( ) );

//            preference.setShopPreference();
        return preferenceDTO;
    }

    /**
     * Gets converted preference dTO list.
     *
     * @param preferenceDomainList the preferences
     *
     * @return the converted preference dTO list
     */
    public static List< Preference > getConvertedListDTOFromDomain( List< com.mana
            .innovative.domain.consumer.Preference > preferenceDomainList ) {
        List< Preference > preferenceDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.Preference preference : preferenceDomainList ) {
            Preference preferenceDTO = new Preference( );
            preferenceDTO = getConvertedDTOFromDomain( preferenceDTO, preference );
            preferenceDTOList.add( preferenceDTO );
        }
        return preferenceDTOList;
    }

    /**
     * Gets converted preference domain from preference dTO.
     *
     * @param preferenceDomain the preference domain
     * @param preferenceDTO    the preference dTO
     *
     * @return the converted preference domain from preference dTO
     */
    public static com.mana.innovative.domain.consumer.Preference getConvertedDomainFromDTO( com.mana.innovative.domain.consumer.Preference preferenceDomain, final Preference preferenceDTO ) {

        if ( preferenceDTO == null ) {
            String message = "Parameter preferenceDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( preferenceDomain == null ) {
            preferenceDomain = new com.mana.innovative.domain.consumer.Preference( );
            logger.warn( "Creating preferenceDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( preferenceDTO.getPreferenceName( ) ) ) {
            preferenceDomain.setPreferenceName( preferenceDTO.getPreferenceName( ) );
        } else {
            flag = true;
            stringBuilder.append( "Preference Name" );
        }

        preferenceDomain.setIsPreferred( preferenceDTO.isPreferred( ) );

//            preference.setShopPreference();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return preferenceDomain;
    }

    /**
     * Gets converted preference domain list from preference dTO list.
     *
     * @param preferenceDTOList the preference dTO list
     *
     * @return the converted preference domain list from preference dTO list
     */
    public static List< com.mana.innovative.domain.consumer.Preference > getConvertedListDomainFromDTO( List< Preference >
                                                                                                                preferenceDTOList ) {

        List< com.mana.innovative.domain.consumer.Preference > preferenceDomainList = new ArrayList<>( );
        for ( Preference preferenceDTO : preferenceDTOList ) {
            com.mana.innovative.domain.consumer.Preference preferenceDomain = new com.mana.innovative.domain.consumer.Preference( );
            preferenceDomain = getConvertedDomainFromDTO( preferenceDomain, preferenceDTO );
            preferenceDomainList.add( preferenceDomain );
        }
        return preferenceDomainList;
    }

}
