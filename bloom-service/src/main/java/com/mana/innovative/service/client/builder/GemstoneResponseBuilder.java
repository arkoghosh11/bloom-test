package com.mana.innovative.service.client.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.converter.response.GemstoneDomainDTOConverter;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.dto.client.payload.GemstonesPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.client.container.GemstoneResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GemstoneResponseBuilder.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:20 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class GemstoneResponseBuilder {
	
	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( GemstoneResponseBuilder.class );
	
	/**
	 * Build gemstone response container.
	 *
	 * @param gemstoneDAOResponse the gemstone dAO response
	 * @param isError the is error
	 *
	 * @return the gemstone response container
	 */
	public static GemstoneResponseContainer< GemstonesPayload > build( DAOResponse< Gemstone > gemstoneDAOResponse,
																	   boolean isError ) {
		
		if ( gemstoneDAOResponse == null ) {
			gemstoneDAOResponse = new DAOResponse<>( );
			logger.warn( "Parameter gemstoneDAOResponse was passed as null" );
		}
		logger.debug( "Starting building of Gemstone Response" );
		/**
		 * If these lines are executed an error has occurred is indicated normally unless request is a delete request
		 * in that case count will still 1 depending on how many are deleted
		 */
		if ( gemstoneDAOResponse.getResults( ) == null || gemstoneDAOResponse.getResults( ).isEmpty( ) ) {
			// for delete success this will be true
			if ( !gemstoneDAOResponse.isDelete( ) && !gemstoneDAOResponse.isRequestSuccess( ) ) {
				gemstoneDAOResponse.setCount( DAOConstants.ZERO );
			}
			gemstoneDAOResponse.setResults( new ArrayList< Gemstone >( ) );
		}
		String location = "GemstoneResponseContainer<T>" + ServiceConstants.HASH + "build()";
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer = new GemstoneResponseContainer<>( );
		GemstonesPayload gemstonesPayload = new GemstonesPayload( );
		ErrorContainer errorContainer = gemstoneDAOResponse.getErrorContainer( );
		if ( errorContainer == null ) {
			errorContainer = new ErrorContainer( );
		}
		try {
			gemstonesPayload.setGemstones( GemstoneDomainDTOConverter.getConvertedListDTOFromDomain( gemstones ) );
		} catch ( IllegalArgumentValueException e ) {
			logger.error( "Failed due to invalid values", e );
			errorContainer.addError( new com.mana.innovative.exception.response.Error( location, e ) );
		} catch ( NullPointerException e ) {
			logger.error( "Failed due to null object", e );
			errorContainer.addError( new Error( location, e ) );
		} catch ( Exception e ) {
			logger.error( "Unexpected error while converting domain to dto object", e );
			errorContainer.addError( new Error( location, e ) );
		}
		gemstonesPayload.setTotalCount( gemstoneDAOResponse.getCount( ) );
		gemstoneResponseContainer.setPayload( gemstonesPayload );
		gemstoneResponseContainer.setCount( gemstoneDAOResponse.getCount( ) );
		gemstoneResponseContainer.setErrorContainer( isError ? errorContainer : null );
		
		logger.debug( "Completing building of Gemstone Response" );
		return gemstoneResponseContainer;
	}
	
	
	/**
	 * Build error.
	 *
	 * @param location the location of the error
	 * @param isError the is error
	 * @param exception the exception
	 *
	 * @return the gemstone response container
	 */
	public static GemstoneResponseContainer< GemstonesPayload > buildError( String location, boolean isError,
																			Exception exception ) {
		
		logger.debug( "Starting building of Error for Gemstone Response" );
		GemstonesPayload gemstonesPayload = new GemstonesPayload( );
		gemstonesPayload.setGemstones( new ArrayList< com.mana.innovative.dto.client.Gemstone >( ) );
		GemstoneResponseContainer< GemstonesPayload > gemstoneResponseContainer = new GemstoneResponseContainer<>( );
		ErrorContainer errorContainer;
		if ( isError ) {
			errorContainer = new ErrorContainer( );
			Error error = new Error( location, exception );
			errorContainer.addError( error );
			gemstoneResponseContainer.setErrorContainer( errorContainer );
		}
		gemstoneResponseContainer.setIsError( true );
		gemstoneResponseContainer.setCount( DAOConstants.ZERO );
		logger.debug( "Ending building of Gemstone Response" );
		return gemstoneResponseContainer;
	}
	
}