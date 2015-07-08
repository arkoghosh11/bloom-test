package com.mana.innovative.service.client;

import com.mana.innovative.dto.client.Shop;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Shop service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface ShopService {

    /**
     * Gets shopDTO.
     *
     * @param shopId the shopDTO id
     * @param requestParams the request params
     * @return the shopDTO
     */
    Response getShopByShopId( long shopId, RequestParams requestParams );

    /**
     * Create shopDTO.
     *
     * @param shopDTO the shopDTO
     * @param requestParams the request params
     * @return the response
     */
    Response createShop( Shop shopDTO, RequestParams requestParams );

    /**
     * Update shopDTO.
     *
     * @param shopDTO the shopDTO
     * @param requestParams the request params
     * @return the response
     */
    Response updateShop( Shop shopDTO, RequestParams requestParams );

    /**
     * Delete shop by shop id.
     *
     * @param shopId the shop id
     * @param requestParams the request params
     * @return the response
     */
    Response deleteShopByShopId( Long shopId, RequestParams requestParams );

    /**
     * Delete shops by shop ids.
     *
     * @param shopIds the shop ids
     * @param requestParams the request params
     * @return the response
     */
    Response deleteShopsByShopIds( List< Long > shopIds, RequestParams requestParams );
}
