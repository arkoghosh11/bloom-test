package com.mana.innovative.service.client;

import com.mana.innovative.dto.client.Gemstone;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type GemstoneService.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:06 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface GemstoneService {
	/**
	 * Gets gemstone by gemstone id.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the gemstone by gemstone id
	 */
	Response getGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams );

	/**
	 * Gets gemstone by gemstone name.
	 *
	 * @param gemstoneIdName the gemstone id name
	 * @param requestParams the request params
	 *
	 * @return the gemstone by gemstone name
	 */
	Response getGemstoneByGemstoneName( String gemstoneIdName, RequestParams requestParams );

	/**
	 * Create gemstone response.
	 *
	 * @param gemstoneDTO the gemstone dto
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	Response createGemstone( Gemstone gemstoneDTO, RequestParams requestParams );

	/**
	 * Update gemstone response.
	 *
	 * @param gemstoneDTO the gemstone dto
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	Response updateGemstone( Gemstone gemstoneDTO, RequestParams requestParams );

	/**
	 * Delete gemstone by gemstone id response.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	Response deleteGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams );

	/**
	 * Delete gemstones by gemstone ids response.
	 *
	 * @param gemstoneIds the gemstone ids
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	Response deleteGemstonesByGemstoneIds( List< Long > gemstoneIds, RequestParams requestParams );

}
