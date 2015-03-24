package com.mana.innovative.dao;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Shop dAO.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface ShopDAO {

    /**
     * Gets shop by shop id.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the shop by shop id
     */
    DAOResponse< Shop > getShopByShopId( long shopId, boolean isError );

    /**
     * Gets shops.
     *
     * @param isError the is error
     *
     * @return the shops
     */
    DAOResponse< Shop > getShops( boolean isError );

    /**
     * Create shop.
     *
     * @param shop    the shop
     * @param isError the is error
     *
     * @return the dAO response
     */
    DAOResponse< Shop > createShop( Shop shop, boolean isError );

    /**
     * Update shop.
     *
     * @param shop    the shop
     * @param isError the is error
     *
     * @return the dAO response
     */
    DAOResponse< Shop > updateShop( Shop shop, boolean isError );

    /**
     * Delete shop by shop id.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the dAO response
     */
    DAOResponse< Shop > deleteShopByShopId( long shopId, boolean isError );

    //@Transactional( propagation = Propagation.REQUIRES_NEW )
    DAOResponse< Shop > deleteShopsByShopIds( List< Long > shopIds, boolean isError );

    DAOResponse< Shop > deleteAllShops( boolean deleteAllShops, boolean isError );
}
