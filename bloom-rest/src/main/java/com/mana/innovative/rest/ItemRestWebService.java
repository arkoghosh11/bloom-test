package com.mana.innovative.rest;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.Item;
import com.mana.innovative.service.ItemService;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * The type Item rest web service.
 */
@Component
@Path( "/{item : (?i)item}" )//@Path ("/{items : (?i)items}")
public class ItemRestWebService {

    private static final Logger logger = Logger.getLogger( ItemRestWebService.class );

    //    @Resource // todo login service
//    private LoginService loginService;
    @Resource( name = "itemServiceImpl" )
    ItemService itemServiceImpl;

//    ItemsService() {
//    logger.setLevel(Level.DEBUG);
//    }

    @GET
    @Path( "/{item_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getItems( @PathParam( value = "item_id" ) long itemId, @QueryParam( value = "is_error" )
    @DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {


//        if(!loginService.checkLogin(httpServletRequest)) {
//            ResponseUtility.unauthorizedAccess(null);
//        }
        Response response;
        try {
            response = itemServiceImpl.getItemByItemId( itemId, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ItemsService.getItems() Method", exception );
        } finally {
            logger.debug( " Response for getItemsByItemId sent Successfully " );
        }
        return response;
    }

    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createAnItem( @NotNull Item itemDTO, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

    @PUT
    @Path( "/{item_id}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateAnItem( @PathParam( "/{item_id}" ) Long itemId, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

    @DELETE
    @Path( "/{item_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteAnItem( @PathParam( "/{item_id}" ) Long itemId, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

    @DELETE
    @Path( "/ids" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteItemsByItemIds( List< Long > itemIds, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

}