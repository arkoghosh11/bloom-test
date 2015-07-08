package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Shop dAO.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface ShopDAO {

    /**
     * Gets shop by shop id.
     *
     * @param shopId the shop id
     * @param requestParams the request params
     * @return the shop by shop id
     */
    DAOResponse< Shop > getShopByShopId( long shopId, RequestParams requestParams );

    /**
     * Gets shops.
     *
     * @param requestParams the request params
     * @return the shops
     */
    DAOResponse< Shop > getShops( RequestParams requestParams );

    /**
     * Create shop.
     *
     * @param shop the shop
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Shop > createShop( Shop shop, RequestParams requestParams );

    /**
     * Update shop.
     *
     * @param shop the shop
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Shop > updateShop( Shop shop, RequestParams requestParams );

    /**
     * Delete shop by shop id.
     *
     * @param shopId the shop id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Shop > deleteShopByShopId( long shopId, RequestParams requestParams );

    /**
     * Delete shops by shop ids.
     *
     * @param shopIds the shop ids
     * @param requestParams the request params
     * @return the dAO response
     */
//@Transactional( propagation = Propagation.REQUIRES_NEW )
    DAOResponse< Shop > deleteShopsByShopIds( List< Long > shopIds, RequestParams requestParams );

    /**
     * Delete all shops.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Shop > deleteAllShops( RequestParams requestParams );
}
