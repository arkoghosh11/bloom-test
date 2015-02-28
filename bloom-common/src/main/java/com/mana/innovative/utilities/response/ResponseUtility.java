package com.mana.innovative.utilities.response;/**
 * Created by alex1 on 1/29/2015.
 * This is a class for .. todo 
 */

import javax.ws.rs.core.Response;

/**
 * Created by Bloom on 1/29/2015 : 9:30 PM
 * todo This class is for ...
 */
public class ResponseUtility {

    /**
     * This method is for returns a Response object with a proper message for an Internal Server Error
     *
     * @param optionalMsg {@link String} An optional Message to override default Message for HTTP Response Header
     *
     * @return {@link Response} Response Object for Jersey Response
     */
    public static Response internalServerErrorMsg (String optionalMsg) {

        optionalMsg = optionalMsg != null ? optionalMsg : "Failed to Process Request";
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(optionalMsg).build();
    }

    /**
     * This method is for returns a Response object with a proper message for an Unauthorized Access for a WebService
     *
     * @param optionalMsg {@link String} An optional Message to override default Message for HTTP Response Header
     *
     * @return {@link Response} Response Object for Jersey Response
     */
    public static Response unauthorizedAccess (String optionalMsg) {

        optionalMsg = optionalMsg != null ? optionalMsg : "Not Authorized for This Service";
        return Response.status(Response.Status.UNAUTHORIZED).entity(optionalMsg).build();

    }

    /**
     * This method is for returns a Response object with a proper message for no data service found for a WebService
     *
     * @param optionalMsg {@link String} An optional Message to override default Message for HTTP Response Header
     *
     * @return {@link Response} Response Object for Jersey Response
     */
    public static Response noResourceFound (String optionalMsg) {

        optionalMsg = optionalMsg != null ? optionalMsg : " Resource Not Found";
        return Response.status(Response.Status.NOT_FOUND).entity(optionalMsg).build();

    }

    /**
     * This method is for returns a Response object with a proper message for an Unauthorized Access for a WebService
     *
     * @param optionalMsg {@link String} An optional Message to override default Message for HTTP Response Header
     *
     * @return {@link Response} Response Object for Jersey Response
     */
    public static Response badRequest (String optionalMsg) {

        optionalMsg = optionalMsg != null ? optionalMsg : "Client HTTP Headers or Parameters for WebService are " +
                "Invalid";
        return Response.status(Response.Status.BAD_REQUEST).entity(optionalMsg).build();

    }


}
