package com.mana.innovative.service.client;

import com.mana.innovative.dto.client.Item;
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
     * @param isError the is error
     * @return the item by item id
     */
    Response getItemByItemId( long itemID, boolean isError );

    /**
     * Create item.
     *
     * @param itemDTO the item dTO
     * @param isError the is error
     * @return the response
     */
    Response createItem( Item itemDTO, boolean isError );

    /**
     * Update item.
     *
     * @param itemDTO the item dTO
     * @param isError the is error
     * @return the response
     */
    Response updateItem( Item itemDTO, boolean isError );

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param isError the is error
     * @return the response
     */
    Response deleteItemsByItemIds( List< Long > itemIds, boolean isError );

    /**
     * Delete item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the response
     */
    Response deleteItemByItemId( long itemId, boolean isError );

}
