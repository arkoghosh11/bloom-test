package com.mana.innovative.service;

import com.mana.innovative.dto.Shop;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Shop service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface ShopService {

    /**
     * Gets shopDTO.
     *
     * @param shopId  the shopDTO id
     * @param isError the is error
     *
     * @return the shopDTO
     */
    public Response getShopByShopId( long shopId, boolean isError );

    /**
     * Create shopDTO.
     *
     * @param shopDTO the shopDTO
     * @param isError the is error
     *
     * @return the response
     */
    public Response createShop( Shop shopDTO, boolean isError );

    /**
     * Update shopDTO.
     *
     * @param shopDTO the shopDTO
     * @param isError the is error
     *
     * @return the response
     */
    public Response updateShop( Shop shopDTO, boolean isError );

    /**
     * Delete shop by shop id.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the response
     */
    public Response deleteShopByShopId( Long shopId, boolean isError );

    /**
     * Delete shops by shop ids.
     *
     * @param shopIds the shop ids
     * @param isError the is error
     *
     * @return the response
     */
    public Response deleteShopsByShopIds( List< Long > shopIds, boolean isError );
}
