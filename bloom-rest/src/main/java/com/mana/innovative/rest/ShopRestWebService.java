package com.mana.innovative.rest;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.Shop;
import com.mana.innovative.service.ShopService;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    private static final Logger logger = Logger.getLogger( ShopRestWebService.class );

    /**
     * The Shop service impl.
     */
//    @Resource // todo login service
//    private LoginService loginService;
    @Resource( name = "shopServiceImpl" )
    private ShopService shopServiceImpl;

//    ShopsService() {
//    logger.setLevel(Level.DEBUG);
//    }

    /**
     * Gets shops.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the shops
     */
    @GET
    @Path( "/{shop_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getShops( @PathParam( value = "shop_id" ) long shopId, @QueryParam( value = "is_error" )
    @DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {


//        if(!loginService.checkLogin(httpServletRequest)) {
//            ResponseUtility.unauthorizedAccess(null);
//        }
        Response response;
        try {
            response = shopServiceImpl.getShopByShopId( shopId, isError );
        } catch ( Exception exception ) {

            response = ResponseUtility.internalServerErrorMsg( null );
            logger.error( "Exception occurred in ShopsService.getShops() Method", exception );
        } finally {
            logger.debug( " Response for getShopsByShopId sent Successfully " );
        }
        return response;
    }

    /**
     * Create an shop.
     *
     * @param shopDTO the shop dTO
     * @param isError the is error
     *
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createAnShop( @NotNull Shop shopDTO, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

    /**
     * Update an shop.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the response
     */
    @PUT
    @Path( "/{shop_id}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateAnShop( @PathParam( "shop_id" ) Long shopId, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

    /**
     * Delete an shop.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the response
     */
    @DELETE
    @Path( "/{shop_id}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteAnShop( @PathParam( "shop_id" ) Long shopId, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

    /**
     * Delete shops by shop ids.
     *
     * @param shopIds the shop ids
     * @param isError the is error
     *
     * @return the response
     */
    @DELETE
    @Path( "/ids" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteShopsByShopIds( List< Long > shopIds, @QueryParam( value = "is_error" )
    @DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
        return null;
    }

}
