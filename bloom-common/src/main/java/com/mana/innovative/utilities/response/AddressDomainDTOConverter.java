package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.Address;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Address domain dTO converter.
 */
public class AddressDomainDTOConverter {


    private static final Logger logger = Logger.getLogger( AddressDomainDTOConverter.class );
    private static final int ZERO = DAOConstants.ZERO;

    /**
     * Gets converted dTO from domain.
     *
     * @param addressDomain the address domain
     *
     * @return the converted dTO from domain
     */
    public static Address getConvertedDTOFromDomain( com.mana.innovative.domain.Address addressDomain ) {

        if ( addressDomain == null ) {
            String message = " Parameter addressDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        Address addressDTO = new Address( );

        if ( addressDomain.getAddressId( ) > ZERO ) {
            addressDTO.setAddressId( addressDomain.getAddressId( ) );
        }
        if ( !StringUtils.isEmpty( addressDomain.getAddress1( ) ) ) {
            addressDTO.setAddress1( addressDomain.getAddress1( ) );
        }
        if ( !StringUtils.isEmpty( addressDomain.getAddress2( ) ) ) {
            addressDTO.setAddress2( addressDomain.getAddress2( ) );
        }
        if ( !StringUtils.isEmpty( addressDomain.getCity( ) ) ) {
            addressDTO.setCity( addressDomain.getCity( ) );
        }
        if ( !StringUtils.isEmpty( addressDomain.getDistrict( ) ) ) {
            addressDTO.setDistrict( addressDomain.getDistrict( ) );
        }
        if ( !StringUtils.isEmpty( addressDomain.getState( ) ) ) {
            addressDTO.setState( addressDomain.getState( ) );
        }
        if ( addressDomain.getZipCode( ) != null ) {
            addressDTO.setZipCode( addressDomain.getZipCode( ) );
        }

        return addressDTO;
    }


    /**
     * Gets converted list domain from dTO.
     *
     * @param addressesDTO the addresses dTO
     *
     * @return the converted list domain from dTO
     */
    public static List< com.mana.innovative.domain.Address > getConvertedListDomainFromDTO( List< Address > addressesDTO ) {

        List< com.mana.innovative.domain.Address > addressesDomain = new ArrayList<>( );
        for ( Address addressDTO : addressesDTO ) {
            com.mana.innovative.domain.Address addressDomain = getConvertedDomainFromDTO( addressDTO );
            addressesDomain.add( addressDomain );
        }
        return addressesDomain;
    }

    /**
     * Gets converted domain from dTO.
     *
     * @param addressDTO the address dTO
     *
     * @return the converted domain from dTO
     */
    public static com.mana.innovative.domain.Address getConvertedDomainFromDTO( Address addressDTO ) {

        if ( addressDTO == null ) {
            String message = " Parameter addressDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        com.mana.innovative.domain.Address addressDomain = new com.mana.innovative.domain.Address( );
        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( addressDTO.getAddress1( ) ) ) {
            addressDomain.setAddress1( addressDTO.getAddress1( ) );
        } else {
            flag = true;
            stringBuilder.append( "address1," );
        }
        if ( !StringUtils.isEmpty( addressDTO.getAddress2( ) ) ) {
            addressDomain.setAddress2( addressDTO.getAddress2( ) );
        } else {
            flag = true;
            stringBuilder.append( "address2," );
        }
        if ( !StringUtils.isEmpty( addressDTO.getCity( ) ) ) {
            addressDomain.setCity( addressDTO.getCity( ) );
        } else {
            flag = true;
            stringBuilder.append( "city," );
        }
        if ( !StringUtils.isEmpty( addressDTO.getDistrict( ) ) ) {
            addressDomain.setDistrict( addressDTO.getDistrict( ) );
        } else {
            flag = true;
            stringBuilder.append( "district," );
        }
        if ( !StringUtils.isEmpty( addressDTO.getState( ) ) ) {
            addressDomain.setState( addressDTO.getState( ) );
        } else {
            flag = true;
            stringBuilder.append( "state," );
        }
        if ( addressDTO.getZipCode( ) != null && addressDTO.getZipCode( ) > ZERO ) {
            addressDomain.setZipCode( addressDTO.getZipCode( ) );
        } else {
            flag = true;
            stringBuilder.append( "zipcode" );
        }

        if ( flag ) {
            logger.error( stringBuilder );
            throw new IllegalArgumentValueException( );
        }
        logger.debug( stringBuilder.toString( ) );
        return addressDomain;
    }

    /**
     * Gets converted list dTO from domain.
     *
     * @param addressesDomain the addresses domain
     *
     * @return the converted list dTO from domain
     */
    public static List< Address > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.Address > addressesDomain ) {

        List< Address > addressesDTO = new ArrayList<>( );
        for ( com.mana.innovative.domain.Address addressDomain : addressesDomain ) {
            Address addressDTO = getConvertedDTOFromDomain( addressDomain );
            addressesDTO.add( addressDTO );
        }
        return addressesDTO;
    }
}
