package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalItemSearchListSizeException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.logic.ItemSearchOption;
import com.mana.innovative.logic.QueryUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class ItemDAO {

    private static final Logger log = Logger.getLogger(ItemDAO.class);

    static {
        log.setLevel(Level.INFO);
    }

    private QueryUtil queryUtil;
    private Session session;
    private Transaction transaction;

    @Resource
    private SessionFactory sessionFactory;

    /**
     * This method to open the DB transaction to start the transaction
     */
    private void openDBTransaction() {

        log.info(" Trying to open Hibernate DB Transaction ");
        try {
            if (sessionFactory == null) {
                log.error("Session Factory inject is Null", new NullPointerException("Session " + "Factory is Null"));
                return;
            }
            session = sessionFactory.getCurrentSession();
//            Note Hib transaction vs spring Transaction
//            transaction = session.beginTransaction();
        } catch (NullPointerException e) {
            log.error(" Not current Session for Session Factory either Transaction Manager Config" +
                    " bug or no DB " + "Connection ", e);
        }
        log.info("Hibernate DB Transaction Opened");
    }

    /**
     * This method is to close the db connection
     */
    private void closeDBTransaction() {

        if (session != null) {
            session.flush();
        }
        log.info("Flushed Hibernate DB Transaction");
    }

    /**
     * This method is to handle any hibernate Exceptions and log it
     *
     * @param exception {@link org.hibernate.HibernateException} A runtime exception object
     */
    private void handleExceptions(HibernateException exception) {
//        if (transaction != null) {
//            transaction.rollback();
//        }
        log.info("Hibernate Exception", exception);
    }

    /**
     * This method is to fill the ErrorContainer with itemDao errors and the correct method locations
     *
     * @param location  {@link String}
     * @param exception {@link Exception}
     * @return {@link ErrorContainer} Returns a ErrorContainer Object with a list of Objects
     */
    private ErrorContainer fillErrorContainer(String location, Exception exception) {

        log.info("**** Recording Error Container Object");
        ErrorContainer errorContainer = new ErrorContainer();
        errorContainer.addError(new Error(location, exception));
        return errorContainer;
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
            query.setParameter("itemId",item.getItemId());
            items = query.list();
            if(items.size() != DAOConstants.ONE) {
                throw new IllegalItemSearchListSizeException("Item List Size is different to expected Size");
            }
            Item dbItem = items.get(DAOConstants.ZERO);

            this.closeDBTransaction();
            dbItem.setItemPrice(item.getItemPrice() != 0 && item.getItemPrice() > 0 ? item.getItemPrice() : dbItem
                    .getItemPrice());
            dbItem.setItemPriceCurrency(item.getItemPriceCurrency() != null ? item.getItemPriceCurrency() : dbItem
                    .getItemPriceCurrency());
            dbItem.setItemName(item.getItemName() != null ? item.getItemName() : dbItem.getItemName());
            dbItem.setItemType(item.getItemType() != null ? item.getItemType() : dbItem.getItemType());

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

    /* IMP Get Functions 1st one is  special Search by Param Function */

    /**
     * Propagation is Required_New as this will a new and long transaction and we don't want other transactions to be
     * withheld because of this one
     *
     * @param itemSearchOption {@link ItemSearchOption}
     * @param maxResults       {@link Integer}
     * @param startLimit       {@link Integer}
     * @return {@link List<Item></>}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List getItemBySearchParams(ItemSearchOption itemSearchOption, int maxResults, int startLimit) {

        List itemList = null;
        try {
            this.openDBTransaction();

            DetachedCriteria detachedCriteria = this.getDetachedCriteriaBySearchParams(itemSearchOption);

            itemList = detachedCriteria.getExecutableCriteria(session).list();
//            transaction.commit();
        } catch (HibernateException exception) {
            this.handleExceptions(exception);
        } finally {
            this.closeDBTransaction();
        }
        return (((itemList != null) && (itemList.size() > com.mana.innovative.constants.DAOConstants.ZERO))) ?
                itemList : null;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
    public DAOResponse<Item> getItemByItemId(long itemId, boolean isError) {

        List<Item> items = null;
        DAOResponse<Item> itemDAOResponse = new DAOResponse<>();
        ErrorContainer errorContainer = null;
        log.info("**Inside itemDAO.getItems()***");
        try {
            this.openDBTransaction();
            Query query = session.createQuery(" from Item where itemId" + " = :item_id");
            query.setLong("item_id", itemId);
//            transaction.commit();
            items = query.list();
            if (!items.isEmpty() && items.size() > DAOConstants.ONE) {
                throw new IllegalItemSearchListSizeException(" Item Size exceeded maximum value " +
                        "of " + DAOConstants.ONE);
            }
        } catch (Exception exception) {
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
        itemDAOResponse.setCount(items == null ? 0 : items.size());
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
        log.info("**Inside itemDAO.getItems()***");
        try {
            this.openDBTransaction();
            items = (List<Item>) session.createQuery(" from com.mana.innovative.domain.Item").list();
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
        itemDAOResponse.setCount(items == null ? 0 : items.size());
        itemDAOResponse.setResults(items);
        itemDAOResponse.setErrorContainer(errorContainer);
        return itemDAOResponse;
    }

     /* IMP Functions private used for the search function */

    /**
     * This method is to create a detached criteria
     *
     * @param itemSearchOption {@link com.mana.innovative.logic.ItemSearchOption}
     * @return {@link org.hibernate.criterion.DetachedCriteria} A detached criteria object
     */
    private DetachedCriteria getDetachedCriteriaBySearchParams(ItemSearchOption itemSearchOption) {

        List<Map<String, Object>> searchConditionParams = itemSearchOption.getSearchConditionParams();
        List<Map<String, String>> searchOrderWithParams = itemSearchOption.getSearchOrderWithParams();
        List<Map<String, Object>> searchMatchTypeParams = itemSearchOption.getSearchMatchTypeParams();

        List<Map<String, String>> searchConditions = itemSearchOption.getSearchConditions();
        List<Map<String, String>> searchMatchType = itemSearchOption.getSearchMatchType();
        List<String> keys;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Item.class);

        if (searchMatchType.isEmpty()) {
            keys = this.getKeysForSearch(searchConditions);
            detachedCriteria = this.addConditionParams(detachedCriteria, searchConditionParams, searchConditions, keys);
        }
        if (!searchOrderWithParams.isEmpty()) {
            keys = this.getKeysForSearch(searchOrderWithParams);
            detachedCriteria = this.addOrderParams(detachedCriteria, searchOrderWithParams, keys);
        }
        if (!searchMatchType.isEmpty()) {
            keys = this.getKeysForSearch(searchMatchType);
            detachedCriteria = this.addMatchTypeParams(detachedCriteria, searchMatchTypeParams, searchMatchType,
                    searchConditions, keys);
        }
        return detachedCriteria;
    }

    public DetachedCriteria addConditionParams(DetachedCriteria detachedCriteria, List<Map<String, Object>>
            searchConditionParams, List<Map<String, String>> searchConditions, List<String> keys) {

        for (int i = 0; i < searchConditions.size() && searchConditionParams.size() == searchConditions.size(); i++) {

            // get condition value from Map with key
            String condition = searchConditions.get(i).get(keys.get(i));
            // get value of class property from map to query the DB
            Object value = searchConditionParams.get(i).get(keys.get(i));
            // add SQL restrictions
            detachedCriteria.add(queryUtil.getAddedRestriction(keys.get(i), value, condition));
        }
        return detachedCriteria;
    }

    @SuppressWarnings(value = "unchecked")
    /**
     *
     * @param detachedCriteria
     * @param searchOrders
     * @param keys
     * @return
     */ private DetachedCriteria addOrderParams(DetachedCriteria detachedCriteria, List<Map<String, String>>
            searchOrders, List<String> keys) {

        for (int i = 0; i < searchOrders.size(); i++) {
            String ordering = searchOrders.get(i).get(keys.get(i));
            if (ordering != null) {
                detachedCriteria.addOrder(queryUtil.getCreatedOrder(keys.get(i), ordering));
            }
        }
        return detachedCriteria;
    }

    public DetachedCriteria addMatchTypeParams(DetachedCriteria detachedCriteria, List<Map<String, Object>>
            searchParams, List<Map<String, String>> searchMatchTypes, List<Map<String, String>> searchConditions,
                                               List<String> keys) {

        System.out.println(searchParams + "\n" + searchParams.size() + " params " + searchParams.isEmpty() + " " +
                "matchType " + "" + searchMatchTypes.isEmpty() + " conditions " +
                searchConditions.isEmpty() + " keys" +
                " " + keys.isEmpty());
        if (searchParams.isEmpty() || searchConditions.isEmpty() || searchMatchTypes.isEmpty() ||
                keys.isEmpty()) {
            throw new NullPointerException("One of the Lists in parameters is Empty");
        }
        for (int i = 0; i < searchMatchTypes.size(); i++) {

            // get condition value from Map with key
            String operator = searchConditions.get(i).get(keys.get(i));
            // get value of class property from map to query the DB
            Object value = searchParams.get(i).get(keys.get(i));

            String matchType = searchMatchTypes.get(i).get(keys.get(i));

            detachedCriteria.add(queryUtil.getAddedRestriction(keys.get(i), value, operator, matchType));
        }
        return detachedCriteria;
    }

    /**
     * This method is for getting the keys for searching
     *
     * @param searchConditions {@link java.util.List< java.util.Map>} A list of type Map of type {@link java.util
     *                         .Map<String></>}
     * @return {@link java.util.List<String>} A list of type String
     */
    private List<String> getKeysForSearch(final List<Map<String, String>> searchConditions) {

        List<String> keys = new ArrayList<String>();
        for (Map<String, String> searchCondition : searchConditions) {
            if (searchCondition.size() != com.mana.innovative.constants.DAOConstants.ONE) {
                throw new IllegalItemSearchListSizeException("Map Size must be ONE (1)");
            }
            for (Map.Entry<String, String> entry : searchCondition.entrySet()) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

}
