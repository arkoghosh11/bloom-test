package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.consumer.UserRole;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/14/2015 7:24 PM. This class is UserRoleDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class UserRoleDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserRoleDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;

    /**
     * Gets converted userRole dTO from userRole domain.
     *
     * @param userRoleDTO the userRole dTO
     * @param userRoleDomain the userRole domain
     * @return the converted userRole dTO from userRole domain
     */
    public static UserRole getConvertedDTOFromDomain( UserRole userRoleDTO,
                                                      com.mana.innovative.domain.consumer.UserRole userRoleDomain ) {

        if ( userRoleDomain == null ) {
            String message = "Parameter userRoleDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( userRoleDTO == null ) {
            userRoleDTO = new UserRole( );
            logger.warn( " Creating userRoleDTO, received null object" );
        }
        if ( userRoleDomain.getUserRoleId( ) >= ZERO ) {
            userRoleDTO.setUserRoleId( userRoleDomain.getUserRoleId( ) );
        }
        if ( !StringUtils.isEmpty( userRoleDomain.getUserRoleName( ) ) ) {
            userRoleDTO.setUserRoleName( userRoleDomain.getUserRoleName( ) );
        }
        if ( userRoleDomain.isActive( ) ) {
            userRoleDTO.setIsActive( userRoleDomain.isActive( ) );
        }
        if ( userRoleDomain.getPrivileges( ) != null ) {
            userRoleDTO.setPrivileges( PrivilegeDomainDTOConverter.getConvertedListDTOFromDomain( userRoleDomain
                    .getPrivileges( ) ) );
        }

        // userRole.setShopUserRole();
        return userRoleDTO;
    }

    /**
     * Gets converted userRole dTO list.
     *
     * @param userRoleDomainList the userRole domain list
     * @return the converted userRole dTO list
     */
    public static List< UserRole > getConvertedListDTOFromDomain(
            List< com.mana.innovative.domain.consumer.UserRole > userRoleDomainList ) {

        List< UserRole > userRoleDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.UserRole userRole : userRoleDomainList ) {

            UserRole userRoleDTO = new UserRole( );
            userRoleDTO = getConvertedDTOFromDomain( userRoleDTO, userRole );
            userRoleDTOList.add( userRoleDTO );
        }
        return userRoleDTOList;
    }

    /**
     * Gets converted userRole domain from userRole dTO.
     *
     * @param userRoleDomain the userRole domain
     * @param userRoleDTO the userRole dTO
     * @return the converted userRole domain from userRole dTO
     */
    public static com.mana.innovative.domain.consumer.UserRole getConvertedDomainFromDTO(
            com.mana.innovative.domain.consumer.UserRole userRoleDomain, UserRole userRoleDTO ) {

        if ( userRoleDTO == null ) {
            String message = "Parameter userRoleDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( userRoleDomain == null ) {
            userRoleDomain = new com.mana.innovative.domain.consumer.UserRole( );
            logger.warn( "Creating userRoleDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( userRoleDTO.getUserRoleName( ) ) ) {
            userRoleDomain.setUserRoleName( userRoleDTO.getUserRoleName( ) );
        } else {
            flag = true;
            stringBuilder.append( " UserRoleName," );
        }

        // userRole.setShopUserRole();
        userRoleDomain.setIsActive( userRoleDTO.isActive( ) );
        if ( !userRoleDTO.isActive( ) ) {
            logger.warn( "Deprecated user role used for login " );
        }

        // Note might need to check role and privilege validation here
        if ( userRoleDTO.getUserRoleId( ) != null && userRoleDTO.getUserRoleId( ) > ZERO ) {
            userRoleDomain.setUserRoleId( userRoleDTO.getUserRoleId( ) );
        } else {
            userRoleDomain.setUserRoleId( DAOConstants.DEFAULT_USER_ROLE );
            userRoleDomain.setIsActive( true );
            userRoleDomain.setUserRoleName( DAOConstants.DEFAULT_USER_ROLE_NAME );
            flag = false;
        }

        if ( userRoleDTO.getPrivileges( ) != null && !userRoleDTO.getPrivileges( ).isEmpty( ) ) {
            userRoleDomain.setPrivileges( PrivilegeDomainDTOConverter.getConvertedListDomainFromDTO( userRoleDTO
                    .getPrivileges( ) ) );
        } else {
            flag = true;
            stringBuilder.append( " Privileges " );
        }
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return userRoleDomain;
    }

    /**
     * Gets converted userRole domain list from userRole dTO list.
     *
     * @param userRoleDTOList the userRole dTO list
     * @return the converted userRole domain list from userRole dTO list
     */
    public static List< com.mana.innovative.domain.consumer.UserRole > getConvertedListDomainFromDTO(
            List< UserRole > userRoleDTOList ) {

        List< com.mana.innovative.domain.consumer.UserRole > userRoleDomainList = new ArrayList<>( );
        for ( UserRole userRoleDTO : userRoleDTOList ) {
            com.mana.innovative.domain.consumer.UserRole userRoleDomain = new com.mana.innovative.domain.consumer.UserRole( );
            userRoleDomain = getConvertedDomainFromDTO( userRoleDomain, userRoleDTO );
            userRoleDomainList.add( userRoleDomain );
        }
        return userRoleDomainList;
    }
}
