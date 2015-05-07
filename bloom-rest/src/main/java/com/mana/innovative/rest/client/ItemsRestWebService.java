package com.mana.innovative.rest.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.service.client.ItemsService;
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
 * The type Items rest web service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{items : (?i)items}" ) //@Path( "/{tabs : (?i)tabs}" )
public class ItemsRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemsRestWebService.class );
    /* Constructor injection */

    /**
     * The Items service impl.
     */
    @Resource
    ItemsService itemsServiceImpl;

    /**
     * Gets items.
     *
     * @param isError the is error
     * @return the items
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getItems( @QueryParam( value = "is_error" ) @DefaultValue( value = DAOConstants.FALSE ) boolean
                                      isError ) {

//        boolean isAuthenticated = loginService.verifyLogin(httpSession);
//        if (!isAuthenticated) return ResponseUtility.unauthorizedAccess();

        try {
            return itemsServiceImpl.getItems( isError );
        } catch ( Exception exception ) {
            logger.error( " Failed to retrieve Items" + exception );
            return ResponseUtility.internalServerErrorMsg( null );
        } finally {
            logger.debug( "Response for Item Rest Service Completed " );
        }
    }

    /**
     * Delete items.
     *
     * @param isError the is error
     * @param isDeleteAll the is delete all
     * @return the response
     */
    @DELETE
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteItems( @QueryParam( value = "is_error" ) @DefaultValue( value = DAOConstants.FALSE ) boolean
                                         isError, @QueryParam( value = "is_delete_all" ) boolean isDeleteAll ) {
        //return ResponseUtility.forbiddenRequest( null );
        try {
            return itemsServiceImpl.deleteAllItems( isError, isDeleteAll );
        } catch ( Exception exception ) {
            logger.error( " Failed to delete All Items" + exception );
            return ResponseUtility.internalServerErrorMsg( null );
        } finally {
            logger.debug( "Response for Items Rest Service Completed " );
        }
    }
}
