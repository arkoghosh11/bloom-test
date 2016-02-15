package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.common.SearchOption;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Item dAO.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface ItemDAO {

    /**
     * Delete all items.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Item > deleteAllItems( RequestParams requestParams );

    /**
     * Delete item by item id.
     *
     * @param itemId the item id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Item > deleteItemByItemId( long itemId, RequestParams requestParams );

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Item > deleteItemsByItemIds( List< Long > itemIds, RequestParams requestParams );

    /* IMP UPDATE Functions */

    /**
     * Update item.
     *
     * @param item the item
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Item > updateItem( Item item, RequestParams requestParams );

    /* IMP CREATE Functions */

    /**
     * Create item.
     *
     * @param item the item
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Item > createItem( Item item, RequestParams requestParams );

    /**
     * Gets item by item id.
     *
     * @param itemId the item id
     * @param requestParams the request params
     * @return the item by item id
     */
    DAOResponse< Item > getItemByItemId( long itemId, RequestParams requestParams );

    /**
     * Gets item by item name.
     *
     * @param itemName the item name
     * @param requestParams the request params
     *
     * @return the item by item name
     */
    DAOResponse< Item > getItemByItemName( String itemName, RequestParams requestParams );

    /**
     * Gets items.
     *
     * @param requestParams the request params
     * @return the items
     */
    DAOResponse< Item > getItems( RequestParams requestParams );

    /**
     * Gets item by search params.
     *
     * @param itemSearchOption the item search option
     * @param requestParams the request params
     *
     * @return the item by search params
     */
    DAOResponse< Item > getItemsBySearchParams( SearchOption itemSearchOption, RequestParams requestParams );
}
