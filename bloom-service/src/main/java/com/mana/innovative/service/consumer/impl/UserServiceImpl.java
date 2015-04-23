/**
 * @author Bloom This Class UserService.java is for Created at Aug 30, 2012 10:32:59 AM
 */
package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.dao.consumer.UserDAO;
import com.mana.innovative.dto.consumer.User;
import com.mana.innovative.dto.consumer.payload.UserPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UserService;
import com.mana.innovative.service.consumer.builder.UserResponseBuilder;
import com.mana.innovative.service.consumer.container.UserResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

/**
 * The type User service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger( UserServiceImpl.class );

    @Context
    private UriInfo uriInfo;
    @Resource
    private UserDAO userDAO;

    /**
     * Gets single user details.
     *
     * @param userId the user id
     *
     * @return the single user details
     */
    @Override
    public Response getUser( Long userId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getUser()";
        logger.debug( "Starting " + location );
        UserResponseContainer< UserPayload > userResponseContainer
                = UserResponseBuilder.build( new ArrayList< User >( ) );
        userDAO.getUsers( requestParams );

        logger.debug( "Finishing " + location );

        return Response.ok( userResponseContainer ).build( );
    }

    /**
     * Create user.
     *
     * @param user          the user
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    public Response createUser( User user, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createUser()";
        logger.debug( "Starting " + location );

        userDAO.createUser( null, requestParams );
        // TODO Auto-generated method stub
        logger.debug( "Starting " + location );

        return null;
    }

    /**
     * Update user.
     *
     * @param user          the user
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    public Response updateUser( User user, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updateUser()";
        logger.debug( "Starting " + location );

        // need to convert
        userDAO.updateUser( null, requestParams );
        // TODO Auto-generated method stub
        logger.debug( "Finishing " + location );

        return null;
    }

    /**
     * Delete specific user.
     *
     * @param userId the user id
     *
     * @return the response
     */
    @Override
    public Response deleteUser( Long userId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteUser()";
        logger.debug( "Starting " + location );

        userDAO.deleteUserByUserId( userId, requestParams, null );
        //todo

        logger.debug( "Finishing " + location );

        return null;
    }
}
