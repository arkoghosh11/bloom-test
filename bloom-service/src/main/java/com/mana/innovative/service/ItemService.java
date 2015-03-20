package com.mana.innovative.service;

import com.mana.innovative.dto.Item;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Item service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface ItemService {

    /**
     * Gets item by item id.
     *
     * @param itemID  the item iD
     * @param isError the is error
     *
     * @return the item by item id
     */
    public Response getItemByItemId( long itemID, boolean isError );

    /**
     * Create item.
     *
     * @param itemDTO the item dTO
     * @param isError the is error
     *
     * @return the response
     */
    public Response createItem( Item itemDTO, boolean isError );

    /**
     * Update item.
     *
     * @param itemDTO the item dTO
     * @param isError the is error
     *
     * @return the response
     */
    public Response updateItem( Item itemDTO, boolean isError );

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param isError the is error
     *
     * @return the response
     */
    public Response deleteItemsByItemIds( List< Long > itemIds, boolean isError );

    /**
     * Delete item by item id.
     *
     * @param itemId  the item id
     * @param isError the is error
     *
     * @return the response
     */
    public Response deleteItemByItemId( long itemId, boolean isError );

}
