package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.dto.request.RequestParams;

import java.util.List;

/**
 * The type GemstoneDAO.
 * <p/>
 * Created by Bloom/Rono on 2/13/2016 5:56 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public interface GemstoneDAO {
	
	/**
	 * Delete all gemstones.
	 *
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	DAOResponse< Gemstone > deleteAllGemstones( RequestParams requestParams );
	
	/**
	 * Delete gemstone by gemstone id.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	DAOResponse< Gemstone > deleteGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams );
	
	/**
	 * Delete gemstones by gemstone ids.
	 *
	 * @param gemstoneIds the gemstone ids
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	DAOResponse< Gemstone > deleteGemstonesByGemstoneIds( List< Long > gemstoneIds, RequestParams requestParams );

    /* IMP UPDATE Functions */
	
	/**
	 * Update gemstone.
	 *
	 * @param gemstone the gemstone
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	DAOResponse< Gemstone > updateGemstone( Gemstone gemstone, RequestParams requestParams );

    /* IMP CREATE Functions */
	
	/**
	 * Create gemstone.
	 *
	 * @param gemstone the gemstone
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	DAOResponse< Gemstone > createGemstone( Gemstone gemstone, RequestParams requestParams );
	
	/**
	 * Gets gemstone by gemstone id.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the gemstone by gemstone id
	 */
	DAOResponse< Gemstone > getGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams );


	DAOResponse< Gemstone > getGemstoneByGemstoneName( String gemstoneName, RequestParams requestParams );


	/**
	 * Gets gemstones.
	 *
	 * @param requestParams the request params
	 *
	 * @return the gemstones
	 */
	DAOResponse< Gemstone > getGemstones( RequestParams requestParams );

	DAOResponse< Gemstone > getGemstonesByParams( String key, List values, RequestParams requestParams );
}