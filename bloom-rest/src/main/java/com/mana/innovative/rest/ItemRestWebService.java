package com.mana.innovative.rest;/**
 * Created by alex1 on 1/30/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.impl.ItemServiceImpl;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Bloom on 1/30/2015 : 2:04 AM
 * todo This class is for ...
 */
@Component
@Path ("/{item : (?i)item}")//@Path ("/{items : (?i)items}")
public class ItemRestWebService {

    private static final Logger logger = Logger.getLogger( ItemRestWebService.class );

//    @Resource // todo login service
//    private LoginService loginService;
@Resource( name = "itemServiceImpl" )
ItemServiceImpl itemServiceImpl;

//    ItemsService() {
//    logger.setLevel(Level.DEBUG);
//    }

    @GET
    @Path ("/{item_id}")
    @Produces ({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getItems (@PathParam (value = "item_id") long itemId, @QueryParam (value = "is_error")
    @DefaultValue (value = DAOConstants.FALSE) boolean isError) {


//        if(!loginService.checkLogin(httpServletRequest)) {
//            ResponseUtility.unauthorizedAccess(null);
//        }
        Response response;
        ErrorContainer errorContainer = null;
        try {
            response = itemServiceImpl.getItemByItemId( itemId, isError );
        } catch (Exception e) {

            response = ResponseUtility.internalServerErrorMsg(null);
            logger.error("Exception occurred in ItemsService.getItems() Method", e);
        } finally {
            logger.debug(" Response for getItemsByItemId sent Successfully ");
        }
        return response;
    }

}