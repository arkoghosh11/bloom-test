package com.mana.innovative.converter.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.consumer.Privilege;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/14/2015 7:25 PM. This class is PrivilegeDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class PrivilegeDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( PrivilegeDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;

    /**
     * Gets converted privilege dTO from privilege domain.
     *
     * @param privilegeDTO the privilege dTO
     * @param privilegeDomain the privilege domain
     * @return the converted privilege dTO from privilege domain
     */
    public static Privilege getConvertedDTOFromDomain( Privilege privilegeDTO,
                                                       com.mana.innovative.domain.consumer.Privilege privilegeDomain ) {

        if ( privilegeDomain == null ) {
            String message = "Parameter privilegeDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( privilegeDTO == null ) {
            privilegeDTO = new Privilege( );
            logger.warn( " Creating privilegeDTO, received null object" );
        }
        if ( privilegeDomain.getPrivilegeId( ) >= ZERO ) {
            privilegeDTO.setPrivilegeId( privilegeDomain.getPrivilegeId( ) );
        }
        privilegeDTO.setPrivilegeName( privilegeDomain.getPrivilegeName( ) );
        privilegeDTO.setPrivilegeId( privilegeDomain.getPrivilegeId( ) );
        privilegeDTO.setAccessible( privilegeDomain.isAccessible( ) );
        // privilege.setShopPrivilege();
        return privilegeDTO;
    }

    /**
     * Gets converted privilege dTO list.
     *
     * @param privilegeDomainList the privilege domain list
     * @return the converted privilege dTO list
     */
    public static List< Privilege > getConvertedListDTOFromDomain(
            List< com.mana.innovative.domain.consumer.Privilege > privilegeDomainList ) {

        List< Privilege > privilegeDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.Privilege privilege : privilegeDomainList ) {

            Privilege privilegeDTO = new Privilege( );
            privilegeDTO = getConvertedDTOFromDomain( privilegeDTO, privilege );
            privilegeDTOList.add( privilegeDTO );
        }
        return privilegeDTOList;
    }

    /**
     * Gets converted privilege domain from privilege dTO.
     *
     * @param privilegeDomain the privilege domain
     * @param privilegeDTO the privilege dTO
     * @return the converted privilege domain from privilege dTO
     */
    public static com.mana.innovative.domain.consumer.Privilege getConvertedDomainFromDTO(
            com.mana.innovative.domain.consumer.Privilege privilegeDomain, Privilege privilegeDTO ) {

        if ( privilegeDTO == null ) {
            String message = "Parameter privilegeDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( privilegeDomain == null ) {
            privilegeDomain = new com.mana.innovative.domain.consumer.Privilege( );
            logger.warn( "Creating privilegeDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( privilegeDTO.getPrivilegeId( ) != null && privilegeDTO.getPrivilegeId( ) > 0 ) {
            privilegeDomain.setPrivilegeId( privilegeDTO.getPrivilegeId( ) );
        } else {
            flag = true;
            stringBuilder.append( " PrivilegeId," );
        }

        if ( !StringUtils.isEmpty( privilegeDTO.getPrivilegeName( ) ) ) {
            privilegeDomain.setPrivilegeName( privilegeDTO.getPrivilegeName( ) );
        } else {
            flag = true;
            stringBuilder.append( " PrivilegeName" );
        }

        privilegeDomain.setAccessible( privilegeDTO.isAccessible( ) );

        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return privilegeDomain;
    }

    /**
     * Gets converted privilege domain list from privilege dTO list.
     *
     * @param privilegeDTOList the privilege dTO list
     * @return the converted privilege domain list from privilege dTO list
     */
    public static List< com.mana.innovative.domain.consumer.Privilege > getConvertedListDomainFromDTO(
            List< Privilege > privilegeDTOList ) {

        List< com.mana.innovative.domain.consumer.Privilege > privilegeDomainList = new ArrayList<>( );
        for ( Privilege privilegeDTO : privilegeDTOList ) {
            com.mana.innovative.domain.consumer.Privilege privilegeDomain = new com.mana.innovative.domain.consumer.Privilege( );
            privilegeDomain = getConvertedDomainFromDTO( privilegeDomain, privilegeDTO );
            privilegeDomainList.add( privilegeDomain );
        }
        return privilegeDomainList;
    }

}
