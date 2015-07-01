/**
 * @author Bloom This Class UserService.java is for Created at Aug 28, 2012 4:31:34 PM
 */
package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.consumer.UserDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.payload.UsersPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UsersService;
import com.mana.innovative.service.consumer.builder.UserResponseBuilder;
import com.mana.innovative.service.consumer.container.UserResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * The type Users service impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public class UsersServiceImpl implements UsersService {

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
     * Gets all users.
     *
     * @param requestParams the request params
     * @return the all users
     */
    @Override
    @Cacheable( value = ServiceConstants.USERS_CACHE, key = ServiceConstants.KEY_NAME )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getAllUsers( RequestParams requestParams ) {

        logger.debug( "Initiating getUsers, userDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.consumer.User > userDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getUsers()";
        UserResponseContainer< UsersPayload > userResponseContainer;
        try {
            userDAOResponse = userDAO.getUsers( requestParams );

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
            logger.error( "Exception occurred while building response", exception );
            userResponseContainer = UserResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( userResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getUsersByUserId sent Successfully " );
        }
    }

    /**
     * Delete users.
     *
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public Response deleteAllUsers( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllUsers()";
        logger.debug( "Starting " + location );

        userDAO.deleteAllUsers( requestParams, null );

        logger.debug( "Finishing " + location );

        return Response.status( Response.Status.SERVICE_UNAVAILABLE ).build( );
    }

}
