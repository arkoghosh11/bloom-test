package com.mana.innovative.rest.client;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.client.GemstonesService;
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
 * The type GemStonesRestWebService.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 9:53 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Path( value = "/{gemstones : (?i)gemstones}" )
@Component
public class GemStonesRestWebService {
	
	private static final Logger logger = LoggerFactory.getLogger( GemStonesRestWebService.class );

	@Resource
	private GemstonesService gemstonesService;

	@GET
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getAllGemstones( @QueryParam( "is_error" ) @DefaultValue( value = ServiceConstants.FALSE ) Boolean
											 isError,
									 @QueryParam( value = "start_limit" ) Long startLimit,
									 @QueryParam( value = "end_limit" ) Long endLimit,
									 @QueryParam( value = "page_size" ) Integer pageSize ) {
		//        boolean isAuthenticated = loginService.verifyLogin(httpSession);
//        if (!isAuthenticated) return ResponseUtility.unauthorizedAccess();
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );

			requestParams.setStartLimit( startLimit );
			requestParams.setEndLimit( endLimit );
			requestParams.setPageSize( pageSize );

			logger.info( "Gemstones Service " + gemstonesService );
			return gemstonesService.getAllGemstones( requestParams );
		} catch ( Exception exception ) {
			logger.error( " Failed to retrieve Gemstones" + exception );
			return ResponseUtility.internalServerErrorMsg( null );
		} finally {
			logger.debug( "Response for Gemstones Rest Service Completed " );
		}
	}

	/**
	 * Delete items.
	 *
	 * @param isError the is error
	 * @param isDeleteAll the is delete all
	 *
	 * @return the response
	 */
	@DELETE
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response deleteAllItems( @QueryParam( value = "is_error" ) @DefaultValue( value = DAOConstants.FALSE )
									boolean
											isError, @QueryParam( value = "is_delete_all" ) boolean isDeleteAll ) {
		//return ResponseUtility.forbiddenRequest( null );
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );
			return gemstonesService.deleteAllGemstones( requestParams );
		} catch ( Exception exception ) {
			logger.error( " Failed to delete All Gemstones" + exception );
			return ResponseUtility.internalServerErrorMsg( null );
		} finally {
			logger.debug( "Response for Gemstones Rest Service Completed " );
		}
	}
}