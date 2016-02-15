package com.mana.innovative.converter.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.client.Gemstone;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GemstoneDomainDTOConverter.
 * <p/>
 * Created by Bloom/Rono on 2/13/2016 5:00 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class GemstoneDomainDTOConverter {

	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( GemstoneDomainDTOConverter.class );
	/**
	 * The constant ZERO.
	 */
	private static final int ZERO = DAOConstants.ZERO;

	/**
	 * Gets converted gemstone dTO from gemstone domain.
	 *
	 * @param gemstoneDTO the gemstone dTO
	 * @param gemstoneDomain the gemstone domain
	 *
	 * @return the converted gemstone dTO from gemstone domain
	 */
	public static Gemstone getConvertedDTOFromDomain( Gemstone gemstoneDTO, com.mana.innovative.domain.client.Gemstone gemstoneDomain ) {

		if ( gemstoneDomain == null ) {
			String message = "Parameter gemstoneDomain is required for conversion";
			logger.error( message );
			throw new NullPointerException( message );
		}
		if ( gemstoneDTO == null ) {
			gemstoneDTO = new Gemstone( );
			logger.warn( " Creating gemstoneDTO, received null object" );
		}

		if ( gemstoneDomain.getGemstoneId( ) >= ZERO ) {
			gemstoneDTO.setGemstoneId( gemstoneDomain.getGemstoneId( ) );
		}
		if ( !StringUtils.isEmpty( gemstoneDomain.getGemstoneName( ) ) ) {
			gemstoneDTO.setGemstoneName( gemstoneDomain.getGemstoneName( ) );
		}
		if ( gemstoneDomain.getGemstoneDescription( ) != null ) {
			gemstoneDTO.setGemstoneDescription( gemstoneDomain.getGemstoneDescription( ) );
		}

		return gemstoneDTO;
	}

	/**
	 * Gets converted gemstone dTO list.
	 *
	 * @param gemstones the gemstones
	 *
	 * @return the converted gemstone dTO list
	 */
	public static List< Gemstone > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.client.Gemstone > gemstones ) {

		List< Gemstone > gemstoneDTOList = new ArrayList<>( );
		for ( com.mana.innovative.domain.client.Gemstone gemstone : gemstones ) {

			Gemstone gemstoneDTO = new Gemstone( );
			gemstoneDTO = getConvertedDTOFromDomain( gemstoneDTO, gemstone );
			gemstoneDTOList.add( gemstoneDTO );
		}
		return gemstoneDTOList;
	}

	/**
	 * Gets converted gemstone domain from gemstone dTO.
	 *
	 * @param gemstoneDomain the gemstone domain
	 * @param gemstoneDTO the gemstone dTO
	 *
	 * @return the converted gemstone domain from gemstone dTO
	 */
	public static com.mana.innovative.domain.client.Gemstone
	getConvertedDomainFromDTO( com.mana.innovative.domain.client.Gemstone gemstoneDomain, Gemstone gemstoneDTO ) {


		if ( gemstoneDTO == null ) {
			String message = "Parameter gemstoneDTO is required for conversion";
			logger.error( message );
			throw new NullPointerException( message );
		}

		if ( gemstoneDomain == null ) {
			gemstoneDomain = new com.mana.innovative.domain.client.Gemstone( );
			logger.warn( "Creating gemstoneDomain, received null object" );
		}

		boolean flag = false;
		StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

		if ( !StringUtils.isEmpty( gemstoneDTO.getGemstoneName( ) ) ) {
			gemstoneDomain.setGemstoneName( gemstoneDTO.getGemstoneName( ) );
		} else {
			flag = true;
			stringBuilder.append( " GemstoneName," );
		}
		if ( !StringUtils.isEmpty( gemstoneDTO.getGemstoneDescription( ) ) ) {
			gemstoneDomain.setGemstoneDescription( gemstoneDTO.getGemstoneDescription( ) );
		} else {
			gemstoneDomain.setGemstoneDescription( ServiceConstants.DEFAULT_GEMSTONE_DESCRIPTION );
		}

		if ( flag ) {
			logger.error( stringBuilder.toString( ) );
			throw new IllegalArgumentValueException( stringBuilder.toString( ) );
		}
		logger.info( stringBuilder.toString( ) );
		return gemstoneDomain;
	}

	/**
	 * Gets converted gemstone domain list from gemstone dTO list.
	 *
	 * @param gemstoneDTOList the gemstone dTO list
	 *
	 * @return the converted gemstone domain list from gemstone dTO list
	 */
	public static List< com.mana.innovative.domain.client.Gemstone > getConvertedListDomainFromDTO( List< Gemstone > gemstoneDTOList ) {

		List< com.mana.innovative.domain.client.Gemstone > gemstoneDomainList = new ArrayList<>( );
		for ( Gemstone gemstoneDTO : gemstoneDTOList ) {
			com.mana.innovative.domain.client.Gemstone gemstoneDomain = new com.mana.innovative.domain.client.Gemstone( );
			gemstoneDomain = getConvertedDomainFromDTO( gemstoneDomain, gemstoneDTO );
			gemstoneDomainList.add( gemstoneDomain );
		}
		return gemstoneDomainList;
	}
}
