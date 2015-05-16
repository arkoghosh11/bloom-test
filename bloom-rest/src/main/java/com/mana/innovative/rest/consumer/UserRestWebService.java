/**
 * @author Bloom This Class UserService.java is for Created at Aug 30, 2012 10:32:59 AM
 */
package com.mana.innovative.rest.consumer;

import com.mana.innovative.dto.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The type User service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{user: (?i)user}" )
public class UserRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserRestWebService.class );

    /**
     * The User service.
     */
    @Resource
    private UserService userService;

    /**
     * Gets single user details.
     *
     * @param userId  the user id
     * @param isError the is error
     *
     * @return the single user details
     */
    @GET
    @Path( "/{userId}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getSingleUserDetails( @PathParam( "userId" ) Long userId,
                                          @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #getSingleUserDetails()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return userService.getUserByUserId( userId, requestParams );
    }

    /**
     * Create new user.
     *
     * @param user    the user
     * @param isError the is error
     *
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createNewUser( User user,
                                   @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #createNewUser()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return userService.createUser( user, requestParams );
    }

    /**
     * Update specific user details.
     *
     * @param user    the user
     * @param userId  the user id
     * @param isError the is error
     *
     * @return the response
     */
    @PUT
    @Path( "/{userId}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateSpecificUserDetails( User user,
                                               @PathParam( "userId" ) Long userId,
                                               @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #updateSpecificUserDetails()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return userService.updateUser( user, requestParams );
    }

    /**
     * Delete specific user.
     *
     * @param userId  the user id
     * @param isError the is error
     *
     * @return the response
     */
    @DELETE
    @Path( "/{userId}" )
    public Response deleteSpecificUser( @PathParam( "userId" ) Long userId,
                                        @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #deleteSpecificUser()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return userService.deleteUserByUserId( userId, requestParams );
    }
}
