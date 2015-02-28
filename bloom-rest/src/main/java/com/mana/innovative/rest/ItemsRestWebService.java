package com.mana.innovative.rest;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.service.ItemsService;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 */
@Component
@Path ("/{items : (?i)items}") //@Path( "/{tabs : (?i)tabs}" )
public class ItemsRestWebService {

    @Resource
    ItemsService itemsService;
    /* Constructor injection */

    private static final Logger logger = Logger.getLogger(ItemsRestWebService.class);


    @GET
    @Produces ({ MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML })
    public Response getItems (@QueryParam (value = "is_error") @DefaultValue (value = DAOConstants.FALSE) boolean
                                          isError) {

//        boolean isAuthenticated = loginService.verifyLogin(httpSession);
//        if (!isAuthenticated) return ResponseUtility.unauthorizedAccess();

        try {
            return itemsService.getItems(isError);
        }
        catch (Exception e) {
            logger.error(" Failed to retrieve Items" + e);
            return ResponseUtility.internalServerErrorMsg(null);
        }
        finally {
            logger.debug("Response for Item Rest Service Completed ");
        }
    }
}
