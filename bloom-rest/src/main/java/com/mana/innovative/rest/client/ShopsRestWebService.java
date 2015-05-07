package com.mana.innovative.rest.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.service.client.ShopsService;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The type Shops rest web service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{shops : (?i)shops}" )
public class ShopsRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ShopsRestWebService.class );

    /**
     * The Shops service impl.
     */
    @Resource
    private ShopsService shopsServiceImpl;

    /**
     * Gets shops.
     *
     * @param isError the is error
     * @return the shops
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getShops( @QueryParam( value = "is_error" ) @DefaultValue( value = DAOConstants.FALSE ) boolean
                                      isError ) {

//        boolean isAuthenticated = loginService.verifyLogin(httpSession);
//        if (!isAuthenticated) return ResponseUtility.unauthorizedAccess();

        try {
            return shopsServiceImpl.getShops( isError );
        } catch ( Exception exception ) {
            logger.error( " Failed to retrieve Shops" + exception );
            return ResponseUtility.internalServerErrorMsg( null );
        } finally {
            logger.debug( "Response for Shop Rest Service Completed " );
        }
    }

    /**
     * Delete shops.
     *
     * @param isError the is error
     * @param isDeleteAll the is delete all
     * @return the response
     */
    @DELETE
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteShops( @QueryParam( value = "is_error" ) @DefaultValue( value = DAOConstants.FALSE ) boolean
                                         isError, @QueryParam( value = "is_delete_all" ) boolean isDeleteAll ) {
        //return ResponseUtility.forbiddenRequest( null );
        try {
            return shopsServiceImpl.deleteAllShops( isError, isDeleteAll );
        } catch ( Exception exception ) {
            logger.error( " Failed to delete All Shops" + exception );
            return ResponseUtility.internalServerErrorMsg( null );
        } finally {
            logger.debug( "Response for Shops Rest Service Completed " );
        }
    }

}
