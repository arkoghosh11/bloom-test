package com.mana.innovative.rest.client;

import com.mana.innovative.authentication.LoginService;
import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.client.Shop;
import com.mana.innovative.service.client.ShopService;
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
 * The type Shop rest web service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{shop : (?i)shop}" )
public class ShopRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ShopRestWebService.class );


    /**
     * The Login service.
     */
    @Resource // todo login service
    private LoginService loginService;
    /**
     * The Shop service impl.
     */
    @Resource
    private ShopService shopServiceImpl;

    /**
     * The Http servlet request.
     */
    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Gets shops.
     *
     * @param shopId the shop id
     * @param isError the is error
     * @return the shops
     */
    @GET
    @Path( "/{shop_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getShop( @PathParam( value = "shop_id" ) long shopId, @QueryParam( value = "is_error" )
    @DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {

        // IMP verify access for this method
        if ( !loginService.checkLogin( httpServletRequest ) ) {
            return ResponseUtility.unauthorizedAccess( null );
        }
        Response response;
        try {
            response = shopServiceImpl.getShopByShopId( shopId, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ShopsService.getShopByShopId() Method", exception );
        } finally {
            logger.debug( " Response for getShop sent Successfully " );
        }
        return response;
    }

    /**
     * Create an shop.
     *
     * @param shopDTO the shop dTO
     * @param isError the is error
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createAShop( @NotNull Shop shopDTO, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {

        Response response;

        try {
            response = shopServiceImpl.createShop( shopDTO, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ShopService.createShop() Method", exception );
        } finally {
            logger.debug( " Response for createAShop sent Successfully " );
        }
        return response;
    }

    /**
     * Update an shop.
     *
     * @param shopId the shop id
     * @param shopDTO the shop dTO
     * @param isError the is error
     * @return the response
     */
    @PUT
    @Path( "/{shop_id}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateAShop( @PathParam( "shop_id" ) Long shopId, Shop shopDTO, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {

        Response response;
        // check the shop ID for validity
        if ( shopId < 1 ) {
            return ResponseUtility.badRequest( "shop id is less than 0" );
        }
        try {
            response = shopServiceImpl.updateShop( shopDTO, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ShopService.updateShop() Method", exception );
        } finally {
            logger.debug( " Response for updateAShop sent Successfully " );
        }
        return response;
    }

    /**
     * Delete an shop.
     *
     * @param shopId the shop id
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Path( "/{shop_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteAShop( @PathParam( "shop_id" ) Long shopId, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {

        Response response;
        // check the shop ID for validity
        if ( shopId < 1 ) {
            return ResponseUtility.badRequest( "shop id is less than 0" );
        }
        try {
            response = shopServiceImpl.deleteShopByShopId( shopId, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ShopService.deleteShopByShopId() Method", exception );
        } finally {
            logger.debug( " Response for deleteShop sent Successfully " );
        }
        return response;
    }

    /**
     * Delete shops by shop ids.
     *
     * @param shopIds the shop ids
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Path( "/ids" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteMultipleShops( List< Long > shopIds, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {

        Response response;

        try {
            response = shopServiceImpl.deleteShopsByShopIds( shopIds, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ShopService.deleteShopsByShopIds() Method", exception );
        } finally {
            logger.debug( " Response for deleteMultipleShops sent Successfully " );
        }
        return response;
    }

}
