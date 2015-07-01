package com.mana.innovative.rest.client;

import com.mana.innovative.authentication.LoginService;
import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.client.Item;
import com.mana.innovative.service.client.ItemService;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * The type Item rest web service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma, vsssadik@gmail.com
 * @Copyright
 */
@Component
@Path( "/{item : (?i)item}" )//@Path ("/{items : (?i)items}")
public class ItemRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemRestWebService.class );

    /**
     * The Item service impl.
     */
    @Resource
    ItemService itemServiceImpl;
    /**
     * The Login service.
     */
    @Resource // todo login service
    private LoginService loginService;
    /**
     * The Http servlet request.
     */
    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Gets items.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the items
     */
    @GET
    @Path( "/{item_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getItems( @PathParam( value = "item_id" ) long itemId, @QueryParam( value = "is_error" )
    @DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {

        System.out.println( "****** Item Service.********" );
        // IMP verify access for this method, right now enabling this line will block all calls to this
        // web service as login service is not implemented yet
//        if ( !loginService.checkLogin( httpServletRequest ) ) {
//            return ResponseUtility.unauthorizedAccess( null );
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

    /**
     * Create an item.
     *
     * @param itemDTO the item dTO
     * @param isError the is error
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createAnItem( @NotNull Item itemDTO, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {

        Response response;
        try {
            response = itemServiceImpl.createItem( itemDTO, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ItemsService.createItem() Method", exception );
        } finally {
            logger.debug( " Response for getItemsByItemId sent Successfully " );
        }
        return response;
    }

    /**
     * Update an item.
     *
     * @param itemId the item id
     * @param itemDTO the item dTO
     * @param isError the is error
     * @return the response
     */
    @PUT
    @Path( "/{item_id}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateAnItem( @PathParam( "item_id" ) Long itemId, Item itemDTO, @QueryParam( value = "is_error" )
    @DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {

        Response response;
        // check the item ID for validity
        if ( itemId < 1 ) {
            return ResponseUtility.badRequest( "item id is less than 0" );
        }
        try {
            response = itemServiceImpl.updateItem( itemDTO, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ItemsService.updateAnItem() Method", exception );
        } finally {
            logger.debug( " Response for updateAnItem sent Successfully " );
        }
        return response;
    }

    /**
     * Delete an item.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Path( "/{item_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteAnItem( @PathParam( "item_id" ) Long itemId, @QueryParam( value = "is_error" )
    @DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {

        Response response;
        // check the item ID for validity
        if ( itemId < 1 ) {
            return ResponseUtility.badRequest( "item id is less than 0" );
        }
        try {
            response = itemServiceImpl.deleteItemByItemId( itemId, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ItemsService.getItems() Method", exception );
        } finally {
            logger.debug( " Response for getItemsByItemId sent Successfully " );
        }
        return response;
    }

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Path( "/ids" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteItemsByItemIds( List< Long > itemIds, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {

        Response response;
        try {
            response = itemServiceImpl.deleteItemsByItemIds( itemIds, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ItemsService.getItems() Method", exception );
        } finally {
            logger.debug( " Response for getItemsByItemId sent Successfully " );
        }
        return response;
    }

}