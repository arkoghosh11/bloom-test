package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.client.GemstoneDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.dto.client.payload.GemstonesPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.client.GemstonesService;
import com.mana.innovative.service.client.builder.GemstoneResponseBuilder;
import com.mana.innovative.service.client.container.GemstoneResponseContainer;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * The type GemstonesServiceImpl.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:13 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class GemstonesServiceImpl implements GemstonesService {
	
	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( GemstonesServiceImpl.class );
	
	/**
	 * The Gemstone dAO impl.
	 */
	@Resource( name = "gemstoneDAOImpl" )
	private GemstoneDAO gemstoneDAOImpl;
	
	/**
	 * Gets gemstones.
	 *
	 * @param requestParams the request params
	 *
	 * @return the gemstones
	 */
	@Override
	@Cacheable( value = ServiceConstants.ITEMS_CACHE, key = ServiceConstants.KEY_NAME )
	@Transactional( propagation = Propagation.REQUIRED, readOnly = true )
	public Response getAllGemstones( RequestParams requestParams ) {
		
		logger.debug( "Initiating getGemstones() , gemstoneDAO injected successfully" );
		DAOResponse< Gemstone > gemstoneDAOResponse;
		final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstones()";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;

		if ( !requestParams.validatePaging( ) ) {
			return ResponseUtility.badRequest( "Invalid page params provided either none or valid params are " +
					"required" );
		}

		try {
			gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			
		}
		try {
			
			gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );
			return Response.status( Response.Status.OK ).entity( gemstoneResponseContainer ).build( );
			
		} catch ( Exception exception ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			
		} finally {
			logger.debug( "Response sent Successfully from getGemstones()" );
		}
	}
	
	/**
	 * Delete all gemstones.
	 *
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public Response deleteAllGemstones( RequestParams requestParams ) {
		
		logger.debug( "Initiating deleteAllGemstones() , gemstoneDAO injected successfully" );
		final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllGemstones()";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;
		DAOResponse< Gemstone > gemstoneDAOResponse;
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.deleteAllGemstones( requestParams );
		} catch ( Exception e ) {
			if ( e instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, e );
			} else
				logger.error( "Exception occurred in" + location, e );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), e );
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			
		}
		try {
			
			gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );
			return Response.status( Response.Status.OK ).entity( gemstoneResponseContainer ).build( );
			
		} catch ( Exception e ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), e );
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
		} finally {
			logger.debug( "Response sent Successfully from getGemstones()" );
		}
	}
}