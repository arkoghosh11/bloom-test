package com.mana.innovative.service.client;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;


/**
 * The type GemstonesService.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 9:59 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface GemstonesService {

	/**
	 * Gets all gemstones.
	 *
	 * @param requestParams the request params
	 *
	 * @return the all gemstones
	 */
	Response getAllGemstones( RequestParams requestParams );

	/**
	 * Delete all gemstones response.
	 *
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	Response deleteAllGemstones( RequestParams requestParams );
}
