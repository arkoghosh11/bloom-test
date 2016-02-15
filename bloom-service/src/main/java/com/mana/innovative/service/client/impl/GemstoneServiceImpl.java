package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.converter.response.GemstoneDomainDTOConverter;
import com.mana.innovative.dao.client.GemstoneDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.client.payload.GemstonesPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.client.GemstoneService;
import com.mana.innovative.service.client.builder.GemstoneResponseBuilder;
import com.mana.innovative.service.client.container.GemstoneResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

/**
 * The type GemstoneServiceImpl.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:13 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class GemstoneServiceImpl implements GemstoneService {
	
	private static final Logger logger = LoggerFactory.getLogger( GemstoneServiceImpl.class );

	/**
	 * The ONE.
	 */
	private final int ONE = 1;
	/**
	 * The Gemstone dAO.
	 */
	@Resource( name = "gemstoneDAOImpl" )
	private GemstoneDAO gemstoneDAOImpl;

	/**
	 * Gets gemstone.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the gemstone
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.DEFAULT )
	public Response getGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams ) {

		logger.debug( "Initiating getGemstoneByGemstoneId for gemstone_id = " + gemstoneId + " , gemstoneDAO injected successfully" );

		DAOResponse< com.mana.innovative.domain.client.Gemstone > gemstoneDAOResponse;
		Response response;
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstoneByGemstoneId()";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;
		if ( gemstoneId < 0 ) {
			IllegalArgumentValueException exception = new IllegalArgumentValueException( "Value is less than 0" );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		}
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.getGemstoneByGemstoneId( gemstoneId, requestParams );

		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );

			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			return response;
		}
		try {
			gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );
			response = Response.status( Status.OK ).entity( gemstoneResponseContainer ).build( );
			return response;

		} catch ( Exception exception ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			return response;
		} finally {
			logger.debug( " Response for getGemstonesByGemstoneId sent Successfully " );
		}
	}

	@Override
	@Transactional( propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.DEFAULT )
	public Response getGemstoneByGemstoneName( final String gemstoneName, final RequestParams requestParams ) {

		logger.debug( "Initiating getGemstoneByGemstoneName for gemstone_name = " + gemstoneName + " , gemstoneDAO injected successfully" );

		DAOResponse< com.mana.innovative.domain.client.Gemstone > gemstoneDAOResponse;
		Response response;
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstoneByGemstoneId()";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;

		if ( StringUtils.isEmpty( gemstoneName ) ) {

			IllegalArgumentValueException exception = new IllegalArgumentValueException( "Value is Empty or Null" );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		}
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.getGemstoneByGemstoneName( gemstoneName, requestParams );

		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );

			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			return response;
		}
		try {
			gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );
			response = Response.status( Status.OK ).entity( gemstoneResponseContainer ).build( );
			return response;

		} catch ( Exception exception ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			return response;
		} finally {
			logger.debug( " Response for getGemstoneByGemstoneName sent Successfully " );
		}
	}

	/**
	 * Create gemstone.
	 *
	 * @param gemstoneDTO the gemstone dTO
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public Response createGemstone( com.mana.innovative.dto.client.Gemstone gemstoneDTO, RequestParams requestParams ) {

		logger.debug( "Initiating createGemstone for incoming gemstone, gemstoneDAO injected successfully" );
		DAOResponse< com.mana.innovative.domain.client.Gemstone > gemstoneDAOResponse;
		Response response;
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createGemstone()";
		com.mana.innovative.domain.client.Gemstone gemstoneDomain = new com.mana.innovative.domain.client.Gemstone( );
		try {
			gemstoneDomain = GemstoneDomainDTOConverter.getConvertedDomainFromDTO( gemstoneDomain, gemstoneDTO );
		} catch ( IllegalArgumentValueException | NullPointerException exception ) {
			logger.error( "Exception occurred while trying to convert object", exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		}
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.createGemstone( gemstoneDomain, requestParams );
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			return response;
		}
		try {
			gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );
			response = Response.status( Status.CREATED ).entity( gemstoneResponseContainer ).build( );
			return response;

		} catch ( Exception exception ) {

			logger.error( "Exception occurred " + location, exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
			return response;

		} finally {
			logger.debug( "Response for createGemstone generated successfully from Service Level" );
		}
	}

	/**
	 * This method is to Update an gemstone. It requires the new gemstone with the updated properties and gemstoneId
	 * original
	 *
	 * @param gemstoneDTO the gemstone
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
	public Response updateGemstone( com.mana.innovative.dto.client.Gemstone gemstoneDTO, RequestParams requestParams ) {

		logger.debug( "Initiating updateGemstone for incoming gemstone, gemstoneDAO injected successfully" );
		com.mana.innovative.domain.client.Gemstone gemstoneDomain = new com.mana.innovative.domain.client.Gemstone( );
		String location = this.getClass( ).getCanonicalName( ) + "#updateGemstone";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;
		if ( gemstoneDTO.getGemstoneId( ) < ONE ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ),
					new IllegalArgumentValueException( ", GemstoneId is Required for " +
							"Updating an Gemstone" ) );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		}
		gemstoneDomain.setGemstoneId( gemstoneDTO.getGemstoneId( ) );
		try {
			gemstoneDomain = GemstoneDomainDTOConverter.getConvertedDomainFromDTO( gemstoneDomain, gemstoneDTO );
		} catch ( IllegalArgumentValueException | NullPointerException exception ) {
			logger.error( "Exception occurred while trying to convert object", exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		} catch ( Exception exception ) {
			logger.error( "Exception occurred in " + location, exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
		}
		DAOResponse< com.mana.innovative.domain.client.Gemstone > gemstoneDAOResponse;
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.updateGemstone( gemstoneDomain, requestParams );
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );

			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
		}
		gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );

		logger.debug( " Response for updateGemstone generated successfully from Service Level" );
		return Response.ok( ).entity( gemstoneResponseContainer ).build( );
	}

	/**
	 * This method is to Delete an gemstone via gemstoneId only.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRES_NEW )
	public Response deleteGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams ) {

		logger.debug( "Initiating deleteGemstoneByGemstoneId for incoming gemstone, gemstoneDAO injected successfully" );
		String location = this.getClass( ).getCanonicalName( ) + "#deleteGemstoneByGemstoneId()";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;

		if ( gemstoneId < ONE ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ),
					new IllegalArgumentValueException( ", GemstoneId is required for deleting an Gemstone" ) );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		}
		DAOResponse< com.mana.innovative.domain.client.Gemstone > gemstoneDAOResponse;
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.deleteGemstoneByGemstoneId( gemstoneId, requestParams );
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );

			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
		}
		gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );

		logger.debug( " Response for deleteGemstoneByGemstoneId generated successfully from Service Level" );
		return Response.ok( ).entity( gemstoneResponseContainer ).build( );
	}

	/**
	 * Delete gemstones by gemstone ids.
	 *
	 * @param gemstoneIds the gemstone ids
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRES_NEW )
	public Response deleteGemstonesByGemstoneIds( List< Long > gemstoneIds, RequestParams requestParams ) {

		logger.debug( "Initiating deleteGemstonesByGemstoneIds for incoming gemstoneIds, gemstoneDAO injected successfully" );

		String location = this.getClass( ).getCanonicalName( ) + "#deleteGemstonesByGemstoneIds";
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer;
		if ( gemstoneIds.isEmpty( ) ) {
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( )
					, new IllegalArgumentValueException( ", GemstoneIds are required for deleting Gemstones" ) );
			return Response.status( Status.BAD_REQUEST ).entity( gemstoneResponseContainer ).build( );
		}

		DAOResponse< com.mana.innovative.domain.client.Gemstone > gemstoneDAOResponse;
		try {
			gemstoneDAOResponse = gemstoneDAOImpl.deleteGemstonesByGemstoneIds( gemstoneIds, requestParams );
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
			} else
				logger.error( "Exception occurred in" + location, exception );
			gemstoneResponseContainer = GemstoneResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( gemstoneResponseContainer ).build( );
		}
		gemstoneResponseContainer = GemstoneResponseBuilder.build( gemstoneDAOResponse, requestParams.isError( ) );

		logger.debug( " Response for deleteGemstonesByGemstoneIds generated successfully from Service Level" );
		return Response.ok( ).entity( gemstoneResponseContainer ).build( );
	}
}