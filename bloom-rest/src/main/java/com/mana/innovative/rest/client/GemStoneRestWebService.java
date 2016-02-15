package com.mana.innovative.rest.client;

import com.mana.innovative.authentication.LoginService;
import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.client.Gemstone;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.client.GemstoneService;
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
 * The type GemStoneRestWebService.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 9:53 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Path( "/{gemstone : (?i)gemstone}" )
@Component
public class GemStoneRestWebService {
	
	private static final Logger logger = LoggerFactory.getLogger( GemStoneRestWebService.class );
	
	
	/**
	 * The Gemstone service impl.
	 */
	@Resource
	GemstoneService gemstoneServiceImpl;
	/**
	 * The Login service.
	 */
//    @Resource // todo login service
	private LoginService loginService = new LoginService( );
	/**
	 * The Http servlet request.
	 */
	@Context
	private HttpServletRequest httpServletRequest;
	
	/**
	 * Gets gemstones.
	 *
	 * @param gemstoneIdName the gemstone id
	 * @param isError the is error
	 *
	 * @return the gemstones
	 */
	@GET
	@Path( "/{gemstone_id}" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getGemstoneByIdOrName( @PathParam( value = "gemstone_id" ) String gemstoneIdName, @QueryParam( value = "is_error" )
	@DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {
		
		System.out.println( "****** Gemstone Service.********" );
		// IMP verify access for this method, right now enabling this line will block all calls to this
		// web service as login service is not implemented yet
//        if ( !loginService.checkLogin( httpServletRequest ) ) {
//            return ResponseUtility.unauthorizedAccess( null );
//        }
		
		Response response = null;
		
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );
			try {
				long gemstoneId = Integer.parseInt( gemstoneIdName );
				response = gemstoneServiceImpl.getGemstoneByGemstoneId( gemstoneId, requestParams );
			} catch ( Exception exception ) {
				response = gemstoneServiceImpl.getGemstoneByGemstoneName( gemstoneIdName, requestParams );
			}
		} catch ( Exception exception ) {
			
			response = ResponseUtility.internalServerErrorMsg( null );
			logger.error( "Exception occurred in GemstonesService.getGemstones() Method", exception );
		} finally {
			logger.debug( " Response for getGemstonesByGemstoneId sent Successfully " );
		}
		return response;
	}
	
	/**
	 * Create an gemstone.
	 *
	 * @param gemstoneDTO the gemstone dTO
	 * @param isError the is error
	 *
	 * @return the response
	 */
	@POST
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response createAnGemstone( @NotNull Gemstone gemstoneDTO, @QueryParam( value = "is_error" )
	@DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
		
		Response response;
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );
			response = gemstoneServiceImpl.createGemstone( gemstoneDTO, requestParams );
		} catch ( Exception exception ) {
			
			response = ResponseUtility.internalServerErrorMsg( null );
			logger.error( "Exception occurred in GemstonesService.createGemstone() Method", exception );
		} finally {
			logger.debug( " Response for getGemstonesByGemstoneId sent Successfully " );
		}
		return response;
	}
	
	/**
	 * Update an gemstone.
	 *
	 * @param gemstoneId the gemstone id
	 * @param gemstoneDTO the gemstone dTO
	 * @param isError the is error
	 *
	 * @return the response
	 */
	@PUT
	@Path( "/{gemstone_id}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateAnGemstone( @PathParam( "gemstone_id" ) Long gemstoneId, Gemstone gemstoneDTO, @QueryParam( value = "is_error" )
	@DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {
		
		Response response;
		// check the gemstone ID for validity
		if ( gemstoneId < 1 ) {
			return ResponseUtility.badRequest( "gemstone id is less than 0" );
		}
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );
			response = gemstoneServiceImpl.updateGemstone( gemstoneDTO, requestParams );
		} catch ( Exception exception ) {
			
			response = ResponseUtility.internalServerErrorMsg( null );
			logger.error( "Exception occurred in GemstonesService.updateAnGemstone() Method", exception );
		} finally {
			logger.debug( " Response for updateAnGemstone sent Successfully " );
		}
		return response;
	}
	
	/**
	 * Delete an gemstone.
	 *
	 * @param gemstoneId the gemstone id
	 * @param isError the is error
	 *
	 * @return the response
	 */
	@DELETE
	@Path( "/{gemstone_id}" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response deleteAnGemstone( @PathParam( "gemstone_id" ) Long gemstoneId, @QueryParam( value = "is_error" )
	@DefaultValue( value = ServiceConstants.FALSE ) boolean isError ) {
		
		Response response;
		// check the gemstone ID for validity
		if ( gemstoneId < 1 ) {
			return ResponseUtility.badRequest( "gemstone id is less than 0" );
		}
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );
			response = gemstoneServiceImpl.deleteGemstoneByGemstoneId( gemstoneId, requestParams );
		} catch ( Exception exception ) {
			
			response = ResponseUtility.internalServerErrorMsg( null );
			logger.error( "Exception occurred in GemstonesService.getGemstones() Method", exception );
		} finally {
			logger.debug( " Response for getGemstonesByGemstoneId sent Successfully " );
		}
		return response;
	}
	
	/**
	 * Delete gemstones by gemstone ids.
	 *
	 * @param gemstoneIds the gemstone ids
	 * @param isError the is error
	 *
	 * @return the response
	 */
	@DELETE
	@Path( "/ids" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response deleteGemstonesByGemstoneIds( List< Long > gemstoneIds, @QueryParam( value = "is_error" )
	@DefaultValue( value = DAOConstants.FALSE ) boolean isError ) {
		
		Response response;
		try {
			RequestParams requestParams = new RequestParams( );
			requestParams.setIsError( isError );
			response = gemstoneServiceImpl.deleteGemstonesByGemstoneIds( gemstoneIds, requestParams );
		} catch ( Exception exception ) {
			
			response = ResponseUtility.internalServerErrorMsg( null );
			logger.error( "Exception occurred in GemstonesService.getGemstones() Method", exception );
		} finally {
			logger.debug( " Response for getGemstonesByGemstoneId sent Successfully " );
		}
		return response;
	}
	
}