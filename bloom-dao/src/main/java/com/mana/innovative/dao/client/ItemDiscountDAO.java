package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface ItemDiscountDiscountDAO is for todo.
 * Created by BLOOM on 9/22/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface ItemDiscountDAO {

    /**
     * Delete all itemDiscounts.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemDiscount > deleteAllItemDiscounts( RequestParams requestParams );

    /**
     * Delete itemDiscount by itemDiscount id.
     *
     * @param itemDiscountId the itemDiscount id
     * @param requestParams  the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemDiscount > deleteItemDiscountByItemDiscountId( long itemDiscountId, RequestParams requestParams );

    /**
     * Delete itemDiscounts by itemDiscount ids.
     *
     * @param itemDiscountIds the itemDiscount ids
     * @param requestParams   the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemDiscount > deleteItemDiscountsByItemDiscountIds( List< Long > itemDiscountIds, RequestParams requestParams );

    /* IMP UPDATE Functions */

    /**
     * Update itemDiscount.
     *
     * @param itemDiscount  the itemDiscount
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemDiscount > updateItemDiscount( ItemDiscount itemDiscount, RequestParams requestParams );

    /* IMP CREATE Functions */

    /**
     * Create itemDiscount.
     *
     * @param itemDiscount  the itemDiscount
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemDiscount > createItemDiscount( ItemDiscount itemDiscount, RequestParams requestParams );

    /**
     * Gets itemDiscount by itemDiscount id.
     *
     * @param itemDiscountId the itemDiscount id
     * @param requestParams  the request params
     *
     * @return the itemDiscount by itemDiscount id
     */
    DAOResponse< ItemDiscount > getItemDiscountByItemDiscountId( long itemDiscountId, RequestParams requestParams );

    /**
     * Gets itemDiscounts.
     *
     * @param requestParams the request params
     *
     * @return the itemDiscounts
     */
    DAOResponse< ItemDiscount > getItemDiscounts( RequestParams requestParams );

    /**
     * Gets item discounts by params.
     *
     * @param key the key
     * @param values the values
     * @param requestParams the request params
     *
     * @return the item discounts by params
     */
    DAOResponse< ItemDiscount > getItemDiscountsByParams( String key, List values, final RequestParams requestParams );
}
