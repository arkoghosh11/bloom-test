/**
 * @author Bloom This Class UserService.java is for Created at Aug 28, 2012 4:31:34 PM
 */
package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.dao.consumer.UserDAO;
import com.mana.innovative.dto.consumer.User;
import com.mana.innovative.dto.consumer.payload.UserPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UsersService;
import com.mana.innovative.service.consumer.builder.UserResponseBuilder;
import com.mana.innovative.service.consumer.container.UserResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

        String location = this.getClass( ).getCanonicalName( ) + "#getAllUsers()";
        logger.debug( "Starting " + location );

        //todo
        userDAO.getUsers( requestParams );
        UserResponseContainer< UserPayload > tabResponseContainer
                = UserResponseBuilder.build( new ArrayList< User >( ) );

        logger.debug( "Finishing " + location );

        return Response.ok( tabResponseContainer ).build( );
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
