package com.mana.innovative.dao;

import com.mana.innovative.domain.Item;
import com.mana.innovative.exception.IllegalItemSearchListSizeException;
import com.mana.innovative.exception.response.*;
import com.mana.innovative.logic.ItemSearchOption;
import com.mana.innovative.logic.QueryUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Basic dAO.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT)
public class BasicDAO {


    /**
     * The constant log.
     */
    protected static final Logger log = Logger.getLogger(BasicDAO.class);

    /**
     * The Query util.
     */
    protected QueryUtil queryUtil;
    /**
     * The Session.
     */
    protected Session session;
    /**
     * The Transaction.
     */
    protected Transaction transaction;

    /**
     * The Session factory.
     */
    @Resource
    protected SessionFactory sessionFactory;

    /**
     * Open dB transaction.
     */
    protected void openDBTransaction() {

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
     * Close dB transaction.
     */
    protected void closeDBTransaction() {

        if (session != null) {
            session.flush();
        }
        log.info("Flushed Hibernate DB Transaction");
    }

    /**
     * Handle exceptions.
     *
     * @param exception the exception
     */
    protected void handleExceptions(HibernateException exception) {
//        if (transaction != null) {
//            transaction.rollback();
//        }
        log.info("Hibernate Exception occurred with \nmessage: " + exception.getMessage(), exception);
    }

    /**
     * Fill error container.
     *
     * @param location  the location
     * @param exception the exception
     * @return the error container
     */
    protected ErrorContainer fillErrorContainer(String location, Exception exception) {

        log.info("**** Recording Error Container Object");
        ErrorContainer errorContainer = new ErrorContainer();
        errorContainer.addError(new com.mana.innovative.exception.response.Error(location, exception));
        return errorContainer;
    }


        /* IMP Get Functions 1st one is  special Search by Param Function */

    /**
     * Gets item by search params.
     *
     * @param itemSearchOption the item search option
     * @param maxResults       the max results
     * @param startLimit       the start limit
     * @return the item by search params
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

    /**
     * Add condition params.
     *
     * @param detachedCriteria      the detached criteria
     * @param searchConditionParams the search condition params
     * @param searchConditions      the search conditions
     * @param keys                  the keys
     * @return the detached criteria
     */
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

    //@SuppressWarnings(value = "unchecked")

    /**
     * This method is for adding order param like ASC or DESC to the given result set
     *
     * @param detachedCriteria {@link DetachedCriteria}
     * @param searchOrders     {@link List<Map<String></>></>}
     * @param keys             {@link List<String></>}
     * @return {@link DetachedCriteria} return the detached criteria with the added params and keys for searching
     */
    private DetachedCriteria addOrderParams(DetachedCriteria detachedCriteria, List<Map<String, String>>
            searchOrders, List<String> keys) {

        for (int i = 0; i < searchOrders.size(); i++) {
            String ordering = searchOrders.get(i).get(keys.get(i));
            if (ordering != null) {
                detachedCriteria.addOrder(queryUtil.getCreatedOrder(keys.get(i), ordering));
            }
        }
        return detachedCriteria;
    }

    /**
     * Add match type params.
     *
     * @param detachedCriteria the detached criteria
     * @param searchParams     the search params
     * @param searchMatchTypes the search match types
     * @param searchConditions the search conditions
     * @param keys             the keys
     * @return the detached criteria
     */
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
     * @param searchConditions {@link java.util.List<java.util.Map>} A list of type Map of type {@link java.util
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

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
