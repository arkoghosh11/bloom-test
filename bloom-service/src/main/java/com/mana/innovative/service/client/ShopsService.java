package com.mana.innovative.service.client;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;

/**
 * The interface Shops service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface ShopsService {

    /**
     * Gets shops.
     *
     * @param requestParams the request params
     * @return the shops
     */
    Response getShops( RequestParams requestParams );

    /**
     * Delete all shops.
     *
     * @param requestParams the request params
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    Response deleteAllShops( RequestParams requestParams );
}
