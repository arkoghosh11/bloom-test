/**
 * @author Bloom This Class UserService.java is for Created at Aug 28, 2012 4:31:34 PM
 */
package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Users service impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class UsersServiceImpl implements UsersService {

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
     *
     * @return the all users
     */
    @Override
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
     * @param userIds       the user ids
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    public Response deleteUsers( List< Long > userIds, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteUsers()";
        logger.debug( "Starting " + location );

        userDAO.deleteUsers( userIds, requestParams, null );

        logger.debug( "Finishing " + location );

        return Response.status( Response.Status.SERVICE_UNAVAILABLE ).build( );
    }

}
