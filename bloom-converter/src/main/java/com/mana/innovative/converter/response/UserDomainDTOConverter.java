package com.mana.innovative.converter.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.consumer.User;
import com.mana.innovative.dto.consumer.UserRole;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is UserDomainDTOConverter
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class UserDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;

    /**
     * Gets converted user dTO from user domain.
     *
     * @param userDTO the user dTO
     * @param userDomain the user domain
     * @return the converted user dTO from user domain
     */
    public static User getConvertedDTOFromDomain( User userDTO, com.mana.innovative.domain.consumer.User userDomain ) {

        if ( userDomain == null ) {
            String message = "Parameter userDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        if ( userDTO == null ) {
            userDTO = new User( );
            logger.warn( " Creating userDTO, received null object" );
        }

        if ( userDomain.getUserId( ) >= ZERO ) {
            userDTO.setUserId( userDomain.getUserId( ) );
        }
        if ( !StringUtils.isEmpty( userDomain.getUserName( ) ) ) {
            userDTO.setUserName( userDomain.getUserName( ) );
        }

        if ( !StringUtils.isEmpty( userDomain.getPassword( ) ) ) {
            userDTO.setPassword( userDomain.getPassword( ) );
        }
        if ( !StringUtils.isEmpty( userDomain.getEmail( ) ) ) {
            userDTO.setEmail( userDomain.getEmail( ) );
        }

        if ( userDomain.getUserRole( ) != null ) {
            userDTO.setUserRole( UserRoleDomainDTOConverter.getConvertedDTOFromDomain( new UserRole( ), userDomain
                    .getUserRole( ) ) );
        }
        return userDTO;
    }

    /**
     * Gets converted user dTO list.
     *
     * @param users the users
     * @return the converted user dTO list
     */
    public static List< User > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.consumer.User > users ) {

        List< User > userDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.User user : users ) {

            User userDTO = new User( );
            userDTO = getConvertedDTOFromDomain( userDTO, user );
            userDTOList.add( userDTO );
        }
        return userDTOList;
    }

    /**
     * Gets converted user domain from user dTO.
     *
     * @param userDomain the user domain
     * @param userDTO the user dTO
     * @return the converted user domain from user dTO
     */
    public static com.mana.innovative.domain.consumer.User getConvertedDomainFromDTO( com.mana.innovative.domain.consumer.User userDomain, User userDTO ) {

        if ( userDTO == null ) {
            String message = "Parameter userDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( userDomain == null ) {
            userDomain = new com.mana.innovative.domain.consumer.User( );
            logger.warn( "Creating userDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( userDTO.getUserName( ) ) ) {
            userDomain.setUserName( userDTO.getUserName( ) );
        } else {
            flag = true;
            stringBuilder.append( " UserName," );
        }
        if ( !StringUtils.isEmpty( userDTO.getPassword( ) ) ) {
            userDomain.setPassword( userDTO.getPassword( ) );
        } else {
            flag = true;
            stringBuilder.append( " User Password," );
        }
        if ( !StringUtils.isEmpty( userDTO.getEmail( ) ) ) {
            userDomain.setEmail( userDTO.getEmail( ) );
        } else {
            flag = true;
            stringBuilder.append( " User Email," );
        }

        if ( userDTO.getUserRole( ) != null ) {
            userDomain.setUserRole( UserRoleDomainDTOConverter.getConvertedDomainFromDTO( new com.mana.innovative
                    .domain.consumer.UserRole( ), userDTO.getUserRole( ) ) );
        } else {
            flag = true;
            stringBuilder.append( " User Role" );
        }
//            user.setShopUser();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return userDomain;
    }

    /**
     * Gets converted user domain list from user dTO list.
     *
     * @param userDTOList the user dTO list
     * @return the converted user domain list from user dTO list
     */
    public static List< com.mana.innovative.domain.consumer.User > getConvertedListDomainFromDTO( List< User > userDTOList ) {

        List< com.mana.innovative.domain.consumer.User > userDomainList = new ArrayList<>( );
        for ( User userDTO : userDTOList ) {
            com.mana.innovative.domain.consumer.User userDomain = new com.mana.innovative.domain.consumer.User( );
            userDomain = getConvertedDomainFromDTO( userDomain, userDTO );
            userDomainList.add( userDomain );
        }
        return userDomainList;
    }
}
