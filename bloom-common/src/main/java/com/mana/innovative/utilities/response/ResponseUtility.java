package com.mana.innovative.utilities.response;/**
 * Created by alex1 on 1/29/2015. This is a class for .. todo
 */

import javax.ws.rs.core.Response;

/**
 * The type Response utility.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ResponseUtility {

    /**
     * This method is for returns a Response object with a proper message for an Internal Server Error
     *
     * @param optionalMsg  An optional Message to override default Message for HTTP Response Header
     * @return Response Object for Jersey Response
     */
    public static Response internalServerErrorMsg( String optionalMsg ) {

        optionalMsg = optionalMsg != null ? optionalMsg : "Failed During Process Of Request";
        return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( optionalMsg ).build( );
    }

    /**
     * This method is for returns a Response object with a proper message for an Unauthorized Access for a WebService
     *
     * @param optionalMsg  An optional Message to override default Message for HTTP Response Header
     * @return Response Object for Jersey Response
     */
    public static Response unauthorizedAccess( String optionalMsg ) {

        optionalMsg = optionalMsg != null ? optionalMsg : "Not Authorized for This Service";
        return Response.status( Response.Status.UNAUTHORIZED ).entity( optionalMsg ).build( );

    }

    /**
     * This method is for returns a Response object with a proper message for no data service found for a WebService
     *
     * @param optionalMsg  An optional Message to override default Message for HTTP Response Header
     * @return Response Object for Jersey Response
     */
    public static Response noResourceFound( String optionalMsg ) {

        optionalMsg = optionalMsg != null ? optionalMsg : " Resource Not Found";
        return Response.status( Response.Status.NOT_FOUND ).entity( optionalMsg ).build( );

    }

    /**
     * This method is for returns a Response object with a proper message for a Bad Request for a WebService
     *
     * @param optionalMsg  An optional Message to override default Message for HTTP Response Header
     * @return Response Object for Jersey Response
     */
    public static Response badRequest( String optionalMsg ) {
        optionalMsg = optionalMsg != null ? optionalMsg : "Client HTTP Headers or Parameters for WebService are " +
                "Invalid";
        return Response.status( Response.Status.BAD_REQUEST ).entity( optionalMsg ).build( );

    }

    /**
     * This method is for returns a Response object with a proper message for a Not Acceptable for a WebService
     *
     * @param optionalMsg  An optional Message to override default Message for HTTP Response Header
     * @return Response Object for Jersey Response
     */
    public static Response notAcceptable( String optionalMsg ) {

        optionalMsg = optionalMsg != null ? optionalMsg : "Client HTTP Headers or Parameters for WebService are " +
                "Invalid";
        return Response.status( Response.Status.NOT_ACCEPTABLE ).entity( optionalMsg ).build( );

    }

    /**
     * This method is for returns a Response object with a proper message for an Forbidden Access for a WebService
     *
     * @param optionalMsg  An optional Message to override default Message for HTTP Response Header
     * @return Response Object for Jersey Response
     */
    public static Response forbiddenRequest( String optionalMsg ) {
        optionalMsg = optionalMsg != null ? optionalMsg : "You do not have access to this method " +
                "Insufficient Access";
        return Response.status( Response.Status.FORBIDDEN ).entity( optionalMsg ).build( );

    }

    /**
     * This method is for returns a Response object with a proper message for a Service Unavailable for a WebService
     *
     * @param optionalMsg An optional Message to override default Message for HTTP Response Header
     *
     * @return Response Object for Jersey Response
     */
    public static Response notAvailable( String optionalMsg ) {
        optionalMsg = optionalMsg != null ? optionalMsg : "This service is not yet available ";
        return Response.status( Response.Status.SERVICE_UNAVAILABLE ).entity( optionalMsg ).build( );
    }
}
