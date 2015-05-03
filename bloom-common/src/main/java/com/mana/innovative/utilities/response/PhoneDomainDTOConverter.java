package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.common.Phone;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is PhoneDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class PhoneDomainDTOConverter {

    private static final Logger logger = LoggerFactory.getLogger( PhoneDomainDTOConverter.class );
    private static final long ZERO = DAOConstants.ZERO;

    private static final String EMPTY_STRING = DAOConstants.EMPTY_STRING;

    /**
     * Gets converted phone dTO from phone domain.
     *
     * @param phoneDTO    {@link Phone} the phone dTO
     * @param phoneDomain {@link com.mana.innovative.domain.common.Phone} the phone domain
     *
     * @return the converted phone dTO from phone domain
     */
    public static Phone getConvertedDTOFromDomain( Phone phoneDTO, com.mana.innovative.domain.common.Phone phoneDomain ) {

        if ( phoneDomain == null ) {
            String message = "Parameter phoneDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( phoneDTO == null ) {
            phoneDTO = new Phone( );
            logger.warn( " Creating phoneDTO, received null object" );
        }
        if ( phoneDomain.getPhoneId( ) >= ZERO ) {
            phoneDTO.setPhoneId( phoneDomain.getPhoneId( ) );
        }

        if ( phoneDomain.getPhoneNumber( ) != null ) {
            phoneDTO.setPhoneNumber( phoneDomain.getPhoneNumber( ) );
        }
        phoneDTO.setPhoneName( phoneDomain.getPhoneName( ) );
        phoneDTO.setPhoneModel( phoneDomain.getPhoneModel( ) );
        phoneDTO.setPhoneCarrier( phoneDomain.getPhoneCarrier( ) );
        phoneDTO.setPhoneType( phoneDomain.getPhoneType( ) );
        phoneDTO.setBoughtDate( phoneDomain.getBoughtDate( ) );


//            phone.setShopPhone();
        return phoneDTO;
    }

    /**
     * Gets converted phone dTO list.
     *
     * @param phoneDomainList the phones
     *
     * @return the converted phone dTO list
     */
    public static List< com.mana.innovative.dto.common.Phone > getConvertedListDTOFromDomain( List< com.mana
            .innovative.domain.common.Phone > phoneDomainList ) {
        List< Phone > phoneDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.common.Phone phone : phoneDomainList ) {
            Phone phoneDTO = new Phone( );
            phoneDTO = getConvertedDTOFromDomain( phoneDTO, phone );
            phoneDTOList.add( phoneDTO );
        }
        return phoneDTOList;
    }

    /**
     * Gets converted phone domain from phone dTO.
     *
     * @param phoneDomain the phone domain
     * @param phoneDTO    the phone dTO
     *
     * @return the converted phone domain from phone dTO
     */
    public static com.mana.innovative.domain.common.Phone getConvertedDomainFromDTO( com.mana.innovative.domain.common.Phone phoneDomain, final Phone phoneDTO ) {

        if ( phoneDTO == null ) {
            String message = "Parameter phoneDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( phoneDomain == null ) {
            phoneDomain = new com.mana.innovative.domain.common.Phone( );
            logger.warn( "Creating phoneDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( phoneDTO.getPhoneNumber( ) ) ) {
            phoneDomain.setPhoneNumber( phoneDTO.getPhoneNumber( ) );
        } else {
            flag = true;
            stringBuilder.append( "Phone Number" );
        }

        if ( !StringUtils.isEmpty( phoneDTO.getPhoneName( ) ) ) {
            phoneDomain.setPhoneName( phoneDTO.getPhoneName( ) );
        } else {
            phoneDomain.setPhoneName( EMPTY_STRING );
        }

        if ( !StringUtils.isEmpty( phoneDTO.getPhoneModel( ) ) ) {
            phoneDomain.setPhoneModel( phoneDTO.getPhoneModel( ) );
        } else {
            phoneDomain.setPhoneModel( EMPTY_STRING );
        }

        if ( !StringUtils.isEmpty( phoneDTO.getPhoneCarrier( ) ) ) {
            phoneDomain.setPhoneCarrier( phoneDTO.getPhoneCarrier( ) );
        } else {
            phoneDomain.setPhoneCarrier( EMPTY_STRING );
        }

        if ( !StringUtils.isEmpty( phoneDTO.getPhoneType( ) ) ) {
            phoneDomain.setPhoneType( phoneDTO.getPhoneType( ) );
        } else {
            phoneDomain.setPhoneType( EMPTY_STRING );
        }

        if ( phoneDTO.getBoughtDate( ) != null ) {
            phoneDomain.setBoughtDate( phoneDTO.getBoughtDate( ) );
        } else {
            phoneDomain.setBoughtDate( null );
        }
//            phone.setShopPhone();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return phoneDomain;
    }

    /**
     * Gets converted phone domain list from phone dTO list.
     *
     * @param phoneDTOList the phone dTO list
     *
     * @return the converted phone domain list from phone dTO list
     */
    public static List< com.mana.innovative.domain.common.Phone > getConvertedListDomainFromDTO( List< Phone >
                                                                                                         phoneDTOList ) {

        List< com.mana.innovative.domain.common.Phone > phoneDomainList = new ArrayList<>( );
        for ( Phone phoneDTO : phoneDTOList ) {
            com.mana.innovative.domain.common.Phone phoneDomain = new com.mana.innovative.domain.common.Phone( );
            phoneDomain = getConvertedDomainFromDTO( phoneDomain, phoneDTO );
            phoneDomainList.add( phoneDomain );
        }
        return phoneDomainList;
    }

}
