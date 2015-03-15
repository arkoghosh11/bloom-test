package com.mana.innovative.dao;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Item dao.
 */
@Repository
public interface ItemDAO {

    /**
     * Delete all items.
     *
     * @param deleteAllItems the delete all items
     * @param isError        the is error
     *
     * @return the dAO response
     */
    public DAOResponse< Item > deleteAllItems( final boolean deleteAllItems, final boolean isError );

    /**
     * Delete item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the dAO response
     */
    public DAOResponse< Item > deleteItemByItemId( long itemId, boolean isError );

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param isError the is error
     * @return the dAO response
     */
    public DAOResponse< Item > deleteItemsByItemIds( List< Long > itemIds, boolean isError );

    /* IMP UPDATE Functions */

    /**
     * Update item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    public DAOResponse< Item > updateItem( Item item, boolean isError );

    /* IMP CREATE Functions */

    /**
     * Create item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    public DAOResponse< Item > createItem( Item item, Boolean isError );

    /**
     * Gets item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the item by item id
     */
    public DAOResponse< Item > getItemByItemId( long itemId, boolean isError );

    /**
     * Gets items.
     *
     * @param isError the is error
     * @return the items
     */
    public DAOResponse< Item > getItems( boolean isError );
}
