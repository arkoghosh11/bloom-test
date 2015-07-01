/**
 * @author Bloom This Class UserService.java is for Created at Aug 30, 2012 10:32:59 AM
 */
package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.consumer.UserDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.payload.UsersPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.consumer.UserService;
import com.mana.innovative.service.consumer.builder.UserResponseBuilder;
import com.mana.innovative.service.consumer.container.UserResponseContainer;
import com.mana.innovative.utilities.response.UserDomainDTOConverter;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * The type User service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserServiceImpl.class );

    /**
     * The User dAO.
     */
    @Resource
    private UserDAO userDAO;

    /**
     * Gets single user details.
     *
     * @param userId the user id
     * @param requestParams the request params
     * @return the single user details
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getUserByUserId( Long userId, RequestParams requestParams ) {

        logger.debug( "Initiating getUserByUserId for user_id = " + userId + ", userDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.consumer.User > userDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getUserByUserId()";
        UserResponseContainer< UsersPayload > userResponseContainer;
        if ( userId < 0 ) {
            IllegalArgumentValueException exception = new IllegalArgumentValueException( "Value is less than 0" );
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( userResponseContainer ).build( );
        }
        try {
            userDAOResponse = userDAO.getUserByUserId( userId, requestParams );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
            return response;
        }
        try {
            userResponseContainer = UserResponseBuilder.build( userDAOResponse, requestParams.isError( ) );
            response = Response.status( Response.Status.OK ).entity( userResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getUsersByUserId sent Successfully " );
        }
    }

    /**
     * Create user.
     *
     * @param user the user
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response createUser( com.mana.innovative.dto.consumer.User user, RequestParams requestParams ) {

        logger.debug( "Initiating createUser for incoming user, userDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.consumer.User > userDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getUserByUserId()";
        UserResponseContainer< UsersPayload > userResponseContainer;
        try {
            com.mana.innovative.domain.consumer.User userDomain = new com.mana.innovative.domain.consumer
                    .User( );
            userDomain = UserDomainDTOConverter.getConvertedDomainFromDTO( userDomain, user );
            userDAOResponse = userDAO.createUser( userDomain, requestParams );

        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( userResponseContainer ).build( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
            return response;
        }
        try {
            userResponseContainer = UserResponseBuilder.build( userDAOResponse, requestParams.isError( ) );
            response = Response.status( Response.Status.CREATED ).entity( userResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( "Response for createUser generated successfully from Service Level" );
        }
    }

    /**
     * Update user.
     *
     * @param userDTO the user
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response updateUser( com.mana.innovative.dto.consumer.User userDTO, RequestParams requestParams ) {


        logger.debug( "Initiating updateUser for incoming user, userDAO injected successfully" );
        com.mana.innovative.domain.consumer.User userDomain = new com.mana.innovative.domain.consumer.User( );
        String location = this.getClass( ).getCanonicalName( ) + "#updateUser()";
        UserResponseContainer< UsersPayload > userResponseContainer;
        if ( userDTO.getUserId( ) == null || userDTO.getUserId( ) < 1 ) {
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( ", UserId is Required for " +
                            "Updating a User" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( userResponseContainer ).build( );
        }
        userDomain.setUserId( userDTO.getUserId( ) );
        DAOResponse< com.mana.innovative.domain.consumer.User > userDAOResponse;
        try {
            userDomain = UserDomainDTOConverter.getConvertedDomainFromDTO( userDomain, userDTO );
            userDAOResponse = userDAO.updateUser( userDomain, requestParams );
        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( userResponseContainer ).build( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
        }

        userResponseContainer = UserResponseBuilder.build( userDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for updateUser generated successfully from Service Level" );
        return Response.ok( ).entity( userResponseContainer ).build( );
    }

    /**
     * Delete specific user.
     *
     * @param userId the user id
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response deleteUserByUserId( Long userId, RequestParams requestParams ) {

        logger.debug( "Initiating deleteUserByUserId for incoming user, userDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#deleteUserByUserId()";
        UserResponseContainer< UsersPayload > userResponseContainer;

        if ( userId < 1 ) {
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( ", UserId is required for deleting an User" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( userResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.consumer.User > userDAOResponse;
        try {
            userDAOResponse = userDAO.deleteUserByUserId( userId, requestParams, null );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
        }
        userResponseContainer = UserResponseBuilder.build( userDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for deleteUserByUserId generated successfully from Service Level" );
        return Response.ok( ).entity( userResponseContainer ).build( );
    }
}
