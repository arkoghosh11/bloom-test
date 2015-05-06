package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Item dAO.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface ItemDAO {

    /**
     * Delete all items.
     *
     * @param deleteAllItems the delete all items
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Item > deleteAllItems( final boolean deleteAllItems, final boolean isError );

    /**
     * Delete item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Item > deleteItemByItemId( long itemId, boolean isError );

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Item > deleteItemsByItemIds( List< Long > itemIds, boolean isError );

    /* IMP UPDATE Functions */

    /**
     * Update item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Item > updateItem( Item item, boolean isError );

    /* IMP CREATE Functions */

    /**
     * Create item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Item > createItem( Item item, Boolean isError );

    /**
     * Gets item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the item by item id
     */
    DAOResponse< Item > getItemByItemId( long itemId, boolean isError );

    /**
     * Gets items.
     *
     * @param isError the is error
     * @return the items
     */
    DAOResponse< Item > getItems( boolean isError );
}
