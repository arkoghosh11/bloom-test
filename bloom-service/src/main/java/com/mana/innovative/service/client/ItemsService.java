package com.mana.innovative.service.client;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * The interface Items service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface ItemsService {

    /**
     * Gets items.
     *
     * @param requestParams the request params
     * @return the items
     */
    Response getItems( RequestParams requestParams );

    /**
     * Delete all items.
     *
     * @param requestParams the request params
     * @return the response
     */
    Response deleteAllItems( RequestParams requestParams );
}
