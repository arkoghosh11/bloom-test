package com.mana.innovative.service.client;

import com.mana.innovative.dto.client.Item;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Item service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface ItemService {

    /**
     * Gets item by item id.
     *
     * @param itemID the item iD
     * @param requestParams the request params
     * @return the item by item id
     */
    Response getItemByItemId( long itemID, RequestParams requestParams );

    /**
     * Create item.
     *
     * @param itemDTO the item dTO
     * @param requestParams the request params
     * @return the response
     */
    Response createItem( Item itemDTO, RequestParams requestParams );

    /**
     * Update item.
     *
     * @param itemDTO the item dTO
     * @param requestParams the request params
     * @return the response
     */
    Response updateItem( Item itemDTO, RequestParams requestParams );

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param requestParams the request params
     * @return the response
     */
    Response deleteItemsByItemIds( List< Long > itemIds, RequestParams requestParams );

    /**
     * Delete item by item id.
     *
     * @param itemId the item id
     * @param requestParams the request params
     * @return the response
     */
    Response deleteItemByItemId( long itemId, RequestParams requestParams );

}
