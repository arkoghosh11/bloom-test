package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex1 on 1/20/2015. This is a domain class
 */
@Repository(value = "itemDAO")
/**
 *  Back to our example, this time you are concerned about the database security, so you define
 *  your DAO classes this
 *  way:
 *
 *
 * @Code {User DAO
 * @Transactional(Propagation=MANDATORY)
 * class UserDAO{
 * // some CRUD methods
 * } }
 *
 * Meaning that whenever a DAO object, and hence a potential access to db, is created,
 * we need to reassure that the call was made from inside one of our services,
 * implying that a live transaction should exist; otherwise an exception occurs.
 * Therefor the propagation is of type MANDATORY.
 */
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT)
public class ItemDAO extends BasicDAO {

    private static final Logger log = Logger.getLogger(ItemDAO.class);

    static {
        log.setLevel(Level.INFO);
    }


    /* IMP DELETE Functions */

    /**
     * @param itemId {@link Integer}
     * @return {@link Boolean} Returns a boolean value to indicate a successful deletion
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteItemByItemId(int itemId) {

        try {
            this.openDBTransaction();
            List items = session.createCriteria(Item.class).add(Restrictions.eq("itemId", itemId)).list();
            session.delete(items.get(com.mana.innovative.constants.DAOConstants.ZERO));
//            transaction.commit();
        } catch (HibernateException exception) {
            this.handleExceptions(exception);
            return false;
        } finally {
            this.closeDBTransaction();
        }
        return true;
    }

    /**
     * @param item {@link Item}
     * @return {@link Boolean} Returns a boolean value to indicate a successful deletion
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteItem(Item item) {

        try {
            this.openDBTransaction();
            session.delete(item);
//            transaction.commit();
        } catch (HibernateException exception) {
            this.handleExceptions(exception);
            return false;
        } finally {
            this.closeDBTransaction();
        }
        return true;
    }
   /* IMP UPDATE Functions */

    /**
     * This method is to update the DB with the persistence layer to keep the Item value synced
     *
     * @param item {@link Item}
     * @return {@link Boolean} Returns a boolean value to indicate a successful update
     */
    @Transactional(propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED)
    public DAOResponse<Item> updateItem(Item item, boolean isError) {

        List<Item> items = new ArrayList<>();
        DAOResponse<Item> itemDAOResponse = new DAOResponse<>();
        itemDAOResponse.setUpdate(true);
        ErrorContainer errorContainer = null;
        log.info("**Inside itemDAO.updateItem()***");
        try {
            if (item.getItemId() < 1) {
                throw new IllegalArgumentValueException("Item Id is invalid: " + item.getItemId());
            }
            this.openDBTransaction();
//            Item dbItem = (Item) session.get(Item.class, item.getItemId());
            Query query = session.createQuery(" from Item where itemId=:itemId");
            query.setParameter( "itemId", item.getItemId( ) );
            items = query.list();
            if ( items.size( ) != DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( "Item List Size is different to expected Size" );
            }
            Item dbItem = items.get(DAOConstants.ZERO);

            this.closeDBTransaction();
            dbItem.setItemPrice( item.getItemPrice( ) != DAOConstants.ZERO && item.getItemPrice( ) > DAOConstants.ZERO ? item.getItemPrice( ) : dbItem
                    .getItemPrice());
            dbItem.setItemPriceCurrency(item.getItemPriceCurrency() != null ? item.getItemPriceCurrency() : dbItem
                    .getItemPriceCurrency());
            dbItem.setItemName(item.getItemName() != null ? item.getItemName() : dbItem.getItemName());
            dbItem.setItemType(item.getItemType() != null ? item.getItemType() : dbItem.getItemType());
            dbItem.setItemSubType( dbItem.getItemSubType( ) );

            dbItem.setQuantityType( item.getQuantityType( ) != null ? item.getQuantityType( ) : dbItem.getQuantityType( ) );
            dbItem.setQuantity( item.getQuantity( ) > DAOConstants.ZERO ? item.getQuantity( ) : dbItem.getQuantity( ) );

            dbItem.setWeightedUnit( item.getWeightedUnit( ) != null ? item.getWeightedUnit( ) : dbItem.getWeightedUnit( ) );
            dbItem.setWeight( item.getWeight( ) > DAOConstants.ZERO ? item.getWeight( ) : dbItem.getWeight( ) );

            dbItem.setBoughtFrom( item.getBoughtFrom( ) != null ? item.getBoughtFrom( ) : dbItem.getBoughtFrom( ) );
            dbItem.setBoughtDate( item.getBoughtDate( ) != null ? item.getBoughtDate( ) : dbItem.getBoughtDate( ) );



            this.openDBTransaction();
            session.update(dbItem);
            this.closeDBTransaction();

            itemDAOResponse.setCount(DAOConstants.ONE);
            itemDAOResponse.setRequestSuccess(Boolean.TRUE);
            items.add(item);
        } catch (Exception exception) {
            exception.printStackTrace();
            if (exception instanceof HibernateException) {
                this.handleExceptions((HibernateException) exception);
            }
            log.error("Error occurred while trying to create an item with itemDAO.createItem()", exception);
            if (isError) {
                String location = this.getClass().getCanonicalName() + "#" + "createItem()";
                errorContainer = this.fillErrorContainer(location, exception);
            }
            itemDAOResponse.setRequestSuccess(Boolean.FALSE);
            itemDAOResponse.setCount(DAOConstants.ZERO);
        }
        itemDAOResponse.setResults(items);
        itemDAOResponse.setErrorContainer(errorContainer);
        return itemDAOResponse;
    }

    /* IMP CREATE Functions */

    /**
     * This method is to create a Item object and save it in the DB
     *
     * @param item {@link Item}
     * @return {@link Boolean} Returns a boolean value to indicate a successful creation
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public DAOResponse<Item> createItem(Item item, Boolean isError) {

        List<Item> items = new ArrayList<>();
        DAOResponse<Item> itemDAOResponse = new DAOResponse<>();
        itemDAOResponse.setCreate(true);
        ErrorContainer errorContainer = null;
        log.info("**Starting " + this.getClass().getCanonicalName() + "#createItem()***");
        try {
            this.openDBTransaction();
            session.save(item);
            this.closeDBTransaction();

            itemDAOResponse.setCount(DAOConstants.ONE);
            itemDAOResponse.setRequestSuccess(Boolean.TRUE);
            items.add(item);

        } catch (Exception exception) {
            if (exception instanceof HibernateException) {
                this.handleExceptions((HibernateException) exception);
            }
            log.error("Error occurred while trying to create an item with itemDAO.createItem()", exception);
            if (isError) {
                String location = this.getClass().getCanonicalName() + "#" + "createItem()";
                errorContainer = this.fillErrorContainer(location, exception);
            }
            itemDAOResponse.setRequestSuccess(Boolean.FALSE);
            itemDAOResponse.setCount(DAOConstants.ZERO);
        }
        itemDAOResponse.setResults(items);
        itemDAOResponse.setErrorContainer(errorContainer);
        return itemDAOResponse;
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
    public DAOResponse<Item> getItemByItemId(long itemId, boolean isError) {

        List<Item> items = null;
        DAOResponse<Item> itemDAOResponse = new DAOResponse<>();
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info("**Inside itemDAO.getItems()***");
        try {
            this.openDBTransaction();
            Query query = session.createQuery(" from Item where itemId" + " = :item_id");
            query.setLong("item_id", itemId);
//            transaction.commit();
            items = query.list();
            if (!items.isEmpty() && items.size() > DAOConstants.ONE) {
                throw new IllegalSearchListSizeException( " Item Size exceeded maximum value " +
                        "of " + DAOConstants.ONE);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            if (exception instanceof HibernateException) {
                this.handleExceptions((HibernateException) exception);
            }
            log.error("Error occurred while trying to fetch data from items table for itemDAO" + ".getItems()",
                    exception);
            if (isError) {
                String location = this.getClass().getCanonicalName() + "#getItemByItemId()";
                errorContainer = this.fillErrorContainer(location, exception);
            }
        } finally {
            this.closeDBTransaction();
        }
        itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
        itemDAOResponse.setResults(items);
        itemDAOResponse.setErrorContainer(errorContainer);
        return itemDAOResponse;
    }

    /**
     * This method is to retrieve all the items values from the DB
     *
     * @return List<Item></> Return a list of {@link com.mana.innovative.domain.Item}
     */

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public DAOResponse<Item> getItems(boolean isError) {

        List<Item> items = null;
        DAOResponse<Item> itemDAOResponse = new DAOResponse<>();
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info("**Inside itemDAO.getItems()***");
        try {
            this.openDBTransaction();
            items = (List<Item>) session.createQuery(" from Item").list();
//            transaction.commit();
        } catch (HibernateException exception) {
            this.handleExceptions(exception);
            log.error("Error occurred while trying to fetch data from items table for itemDAO" + ".getItems()",
                    exception);
            if (isError) {
                String location = this.getClass().getCanonicalName() + "#getItems()";
                errorContainer = this.fillErrorContainer(location, exception);
            }
        } finally {
            this.closeDBTransaction();
        }
        itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
        itemDAOResponse.setResults(items);
        itemDAOResponse.setErrorContainer(errorContainer);
        return itemDAOResponse;
    }

    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

}
