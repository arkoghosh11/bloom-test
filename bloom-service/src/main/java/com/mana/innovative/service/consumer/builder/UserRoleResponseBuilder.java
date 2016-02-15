package com.mana.innovative.service.consumer.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.converter.response.UserRoleDomainDTOConverter;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.UserRole;
import com.mana.innovative.dto.consumer.payload.UserRolesPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.consumer.container.UserRoleResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 7/8/2015 2:43 AM.
 * This class is UserRoleRoleResponseBuilder
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class UserRoleResponseBuilder {

    private static final Logger logger = LoggerFactory.getLogger( UserRoleResponseBuilder.class );

    /**
     * Build userRole response container.
     *
     * @param userRoleDAOResponse the userRole dAO response
     * @param isError             the is error
     *
     * @return Returns a response object for userRoles
     */
    public static UserRoleResponseContainer< UserRolesPayload > build( DAOResponse< UserRole > userRoleDAOResponse, boolean isError ) {

        if ( userRoleDAOResponse == null ) {
            userRoleDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter userRoleDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of UserRole Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( userRoleDAOResponse.getResults( ) == null || userRoleDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !userRoleDAOResponse.isDelete( ) && !userRoleDAOResponse.isRequestSuccess( ) ) {
                userRoleDAOResponse.setCount( DAOConstants.ZERO );
            }
            userRoleDAOResponse.setResults( new ArrayList< UserRole >( ) );
        }
        String location = "UserRoleResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );
        UserRoleResponseContainer< UserRolesPayload > userRoleResponseContainer = new UserRoleResponseContainer<>( );
        UserRolesPayload userRolesPayload = new UserRolesPayload( );
        ErrorContainer errorContainer = userRoleDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            userRolesPayload.setUserRoles( UserRoleDomainDTOConverter.getConvertedListDTOFromDomain( userRoles ) );
        } catch ( IllegalArgumentValueException e ) {
            logger.error( "Failed due to invalid values", e );
            errorContainer.addError( new com.mana.innovative.exception.response.Error( location, e ) );
        } catch ( NullPointerException e ) {
            logger.error( "Failed due to null object", e );
            errorContainer.addError( new com.mana.innovative.exception.response.Error( location, e ) );
        } catch ( Exception e ) {
            logger.error( "Unexpected error while converting domain to dto object", e );
            errorContainer.addError( new Error( location, e ) );
        }
        userRolesPayload.setTotalCount( userRoleDAOResponse.getCount( ) );
        userRoleResponseContainer.setPayload( userRolesPayload );
        userRoleResponseContainer.setCount( userRoleDAOResponse.getCount( ) );
        userRoleResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of UserRole Response" );
        return userRoleResponseContainer;
    }

    /**
     * Build error.
     *
     * @param location  the location of the error
     * @param isError   the is error
     * @param exception the exception
     *
     * @return the userRole response container
     */
    public static UserRoleResponseContainer< UserRolesPayload > buildError( String location, boolean isError,
                                                                            Exception exception ) {

        logger.debug( "Starting building of Error for UserRole Response" );
        UserRolesPayload userRolesPayload = new UserRolesPayload( );
        userRolesPayload.setUserRoles( new ArrayList< com.mana.innovative.dto.consumer.UserRole >( ) );
        UserRoleResponseContainer< UserRolesPayload > userRoleResponseContainer = new UserRoleResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            com.mana.innovative.exception.response.Error error = new Error( location, exception );
            errorContainer.addError( error );
            userRoleResponseContainer.setErrorContainer( errorContainer );
        }
        userRoleResponseContainer.setIsError( true );
        userRoleResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of UserRole Response" );
        return userRoleResponseContainer;
    }
}
