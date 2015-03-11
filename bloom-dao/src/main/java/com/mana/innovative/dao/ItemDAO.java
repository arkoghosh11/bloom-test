package com.mana.innovative.dao;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The interface Item dao.
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public interface ItemDAO {

    /**
     * Delete all items.
     *
     * @param deleteAllItems the delete all items
     * @param isError        the is error
     *
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > deleteAllItems( final boolean deleteAllItems, final boolean isError );

    /**
     * Delete item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteItemByItemId( long itemId, boolean isError );

    /**
     * Delete item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteItem( Item item, boolean isError );

    /* IMP UPDATE Functions */

    /**
     * Update item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > updateItem( Item item, boolean isError );

    /* IMP CREATE Functions */

    /**
     * Create item.
     *
     * @param item the item
     * @param isError the is error
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
    public DAOResponse< Item > createItem( Item item, Boolean isError );

    /**
     * Gets item by item id.
     *
     * @param itemId the item id
     * @param isError the is error
     * @return the item by item id
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > getItemByItemId( long itemId, boolean isError );

    /**
     * Gets items.
     *
     * @param isError the is error
     * @return the items
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Item > getItems( boolean isError );
}
