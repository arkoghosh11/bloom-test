/**
 * @author Bloom This Class UserResponseBuilder.java is for Created at Aug 28, 2012 4:19:30 PM
 */
package com.mana.innovative.service.consumer.builder;


import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.converter.response.UserDomainDTOConverter;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.payload.UsersPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.consumer.container.UserResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User response builder.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class UserResponseBuilder {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserResponseBuilder.class );

    /**
     * Build user response container.
     *
     * @param userDAOResponse the user dAO response
     * @param isError the is error
     * @return Returns a response object for users
     */
    public static UserResponseContainer< UsersPayload > build( DAOResponse< com.mana.innovative.domain.consumer.User > userDAOResponse, boolean isError ) {

        if ( userDAOResponse == null ) {
            userDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter userDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of User Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( userDAOResponse.getResults( ) == null || userDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !userDAOResponse.isDelete( ) && !userDAOResponse.isRequestSuccess( ) ) {
                userDAOResponse.setCount( DAOConstants.ZERO );
            }
            userDAOResponse.setResults( new ArrayList< com.mana.innovative.domain.consumer.User >( ) );
        }
        String location = "UserResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< com.mana.innovative.domain.consumer.User > users = userDAOResponse.getResults( );
        UserResponseContainer< UsersPayload > userResponseContainer = new UserResponseContainer<>( );
        UsersPayload usersPayload = new UsersPayload( );
        ErrorContainer errorContainer = userDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            usersPayload.setUsers( UserDomainDTOConverter.getConvertedListDTOFromDomain( users ) );
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
        usersPayload.setTotalCount( userDAOResponse.getCount( ) );
        userResponseContainer.setPayload( usersPayload );
        userResponseContainer.setCount( userDAOResponse.getCount( ) );
        userResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of User Response" );
        return userResponseContainer;
    }

    /**
     * Build error.
     *
     * @param location the location of the error
     * @param isError the is error
     * @param exception the exception
     * @return the user response container
     */
    public static UserResponseContainer< UsersPayload > buildError( String location, boolean isError,
                                                                    Exception exception ) {

        logger.debug( "Starting building of Error for User Response" );
        UsersPayload usersPayload = new UsersPayload( );
        usersPayload.setUsers( new ArrayList< com.mana.innovative.dto.consumer.User >( ) );
        UserResponseContainer< UsersPayload > userResponseContainer = new UserResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            com.mana.innovative.exception.response.Error error = new Error( location, exception );
            errorContainer.addError( error );
            userResponseContainer.setErrorContainer( errorContainer );
        }
        userResponseContainer.setIsError( true );
        userResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of User Response" );
        return userResponseContainer;
    }

}
