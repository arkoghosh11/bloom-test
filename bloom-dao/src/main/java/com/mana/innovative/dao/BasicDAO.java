package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.domain.client.WorkingHour;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.logic.ItemSearchOption;
import com.mana.innovative.logic.QueryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Basic dAO.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public class BasicDAO {


    /**
     * The constant logger.
     */
    protected static final Logger logger = LoggerFactory.getLogger( BasicDAO.class );

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
    protected void openDBTransaction( ) {

        logger.debug( " Trying to open Hibernate DB Transaction " );
        try {
            if ( sessionFactory == null ) {
                NullPointerException exception = new NullPointerException( "Session " + "Factory is Null" );
                logger.error( "Session Factory inject is Null", exception );
                throw exception;
            }
            session = sessionFactory.getCurrentSession( );
//            Note Hib transaction vs spring Transaction
//            transaction = session.beginTransaction();
        } catch ( Exception e ) {
            logger.error( "Current Session error from Session Factory, either Transaction Manager Config issue " +
                    "or no DB Connection ", e );
        }
        logger.debug( "Hibernate DB Transaction Opened" );
    }

    /**
     * Close dB transaction.
     */
    protected void closeDBTransaction( ) {

        logger.debug( "Trying to Flush Hibernate Transaction" );
        if ( session != null ) {
            session.flush( );
        }
        logger.debug( "Flushed Hibernate DB Transaction" );
    }

    /**
     * Handle exceptions.
     *
     * @param exception the exception
     */
    protected void handleExceptions( HibernateException exception ) {
//        if (transaction != null) {
//            transaction.rollback();
//        }
        logger.error( "Hibernate Exception occurred with \nmessage: " + exception.getMessage( ), exception );
    }

    /**
     * Fill error container.
     *
     * @param location  the location
     * @param exception the exception
     *
     * @return the error container
     */
    protected ErrorContainer fillErrorContainer( String location, Exception exception ) {

        logger.debug( "**** Recording Error Container Object" );
        ErrorContainer errorContainer = new ErrorContainer( );
        errorContainer.addError( new com.mana.innovative.exception.response.Error( location, exception ) );
        return errorContainer;
    }


        /* IMP Get Functions 1st one is  special Search by Param Function */

    /**
     * Gets item by search params.
     *
     * @param itemSearchOption the item search option
     * @param maxResults       the max results
     * @param startLimit       the start limit
     *
     * @return the item by search params
     */
    @Transactional( readOnly = true, propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public List getItemBySearchParams( ItemSearchOption itemSearchOption, int maxResults, int startLimit ) {

        List itemList = null;
        try {
            this.openDBTransaction( );

            DetachedCriteria detachedCriteria = this.getDetachedCriteriaBySearchParams( itemSearchOption );

            itemList = detachedCriteria.getExecutableCriteria( session ).list( );

            this.closeDBTransaction( );
//            transaction.commit();
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Exception occurred while trying to search " + itemSearchOption.toString( ) );
        }
        return ( ( ( itemList != null ) && ( itemList.size( ) > com.mana.innovative.constants.DAOConstants.ZERO ) ) ) ?
                itemList : null;
    }

         /* IMP Functions private used for the search function */

    /**
     * This method is to create a detached criteria
     *
     * @param itemSearchOption {@link com.mana.innovative.logic.ItemSearchOption}
     *
     * @return {@link org.hibernate.criterion.DetachedCriteria} A detached criteria object
     */
    private DetachedCriteria getDetachedCriteriaBySearchParams( ItemSearchOption itemSearchOption ) {

        List< Map< String, Object > > searchConditionParams = itemSearchOption.getSearchConditionParams( );
        List< Map< String, String > > searchOrderWithParams = itemSearchOption.getSearchOrderWithParams( );
        List< Map< String, Object > > searchMatchTypeParams = itemSearchOption.getSearchMatchTypeParams( );

        List< Map< String, String > > searchConditions = itemSearchOption.getSearchConditions( );
        List< Map< String, String > > searchMatchType = itemSearchOption.getSearchMatchType( );
        List< String > keys;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass( Item.class );

        if ( searchMatchType.isEmpty( ) ) {
            keys = this.getKeysForSearch( searchConditions );
            detachedCriteria = this.addConditionParams( detachedCriteria, searchConditionParams, searchConditions, keys );
        }
        if ( !searchOrderWithParams.isEmpty( ) ) {
            keys = this.getKeysForSearch( searchOrderWithParams );
            detachedCriteria = this.addOrderParams( detachedCriteria, searchOrderWithParams, keys );
        }
        if ( !searchMatchType.isEmpty( ) ) {
            keys = this.getKeysForSearch( searchMatchType );
            detachedCriteria = this.addMatchTypeParams( detachedCriteria, searchMatchTypeParams, searchMatchType,
                    searchConditions, keys );
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
     *
     * @return the detached criteria
     */
    public DetachedCriteria addConditionParams( DetachedCriteria detachedCriteria, List< Map< String, Object > >
            searchConditionParams, List< Map< String, String > > searchConditions, List< String > keys ) {

        for ( int i = DAOConstants.ZERO; i < searchConditions.size( ) && searchConditionParams.size( ) ==
                searchConditions.size( );
              i++ ) {

            // get condition value from Map with key
            String condition = searchConditions.get( i ).get( keys.get( i ) );
            // get value of class property from map to query the DB
            Object value = searchConditionParams.get( i ).get( keys.get( i ) );
            // add SQL restrictions
            detachedCriteria.add( queryUtil.getAddedRestriction( keys.get( i ), value, condition ) );
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
     *
     * @return {@link DetachedCriteria} return the detached criteria with the added params and keys for searching
     */
    private DetachedCriteria addOrderParams( DetachedCriteria detachedCriteria, List< Map< String, String > >
            searchOrders, List< String > keys ) {

        for ( int i = DAOConstants.ZERO; i < searchOrders.size( ); i++ ) {
            String ordering = searchOrders.get( i ).get( keys.get( i ) );
            if ( ordering != null ) {
                detachedCriteria.addOrder( queryUtil.getCreatedOrder( keys.get( i ), ordering ) );
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
     *
     * @return the detached criteria
     */
    public DetachedCriteria addMatchTypeParams( DetachedCriteria detachedCriteria, List< Map< String, Object > >
            searchParams, List< Map< String, String > > searchMatchTypes, List< Map< String, String > > searchConditions,
                                                List< String > keys ) {

        System.out.println( searchParams + "\n" + searchParams.size( ) + " params " + searchParams.isEmpty( ) + " " +
                "matchType " + "" + searchMatchTypes.isEmpty( ) + " conditions " +
                searchConditions.isEmpty( ) + " keys" +
                " " + keys.isEmpty( ) );
        if ( searchParams.isEmpty( ) || searchConditions.isEmpty( ) || searchMatchTypes.isEmpty( ) ||
                keys.isEmpty( ) ) {
            throw new NullPointerException( "One of the Lists in parameters is Empty" );
        }
        for ( int i = DAOConstants.ZERO; i < searchMatchTypes.size( ); i++ ) {

            // get condition value from Map with key
            String operator = searchConditions.get( i ).get( keys.get( i ) );
            // get value of class property from map to query the DB
            Object value = searchParams.get( i ).get( keys.get( i ) );

            String matchType = searchMatchTypes.get( i ).get( keys.get( i ) );

            detachedCriteria.add( queryUtil.getAddedRestriction( keys.get( i ), value, operator, matchType ) );
        }
        return detachedCriteria;
    }

    /**
     * This method is for getting the keys for searching
     *
     * @param searchConditions {@link java.util.List<java.util.Map>} A list of type Map of type {@link java.util
     *                         .Map<String></>}
     *
     * @return {@link java.util.List<String>} A list of type String
     */
    private List< String > getKeysForSearch( final List< Map< String, String > > searchConditions ) {

        List< String > keys = new ArrayList< String >( );
        for ( Map< String, String > searchCondition : searchConditions ) {
            if ( searchCondition.size( ) != com.mana.innovative.constants.DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( "Map Size must be ONE (1)" );
            }
            for ( Map.Entry< String, String > entry : searchCondition.entrySet( ) ) {
                keys.add( entry.getKey( ) );
            }
        }
        return keys;
    }


    /**
     * Update shop values.
     *
     * @param shop   the shop
     * @param dbShop the db shop
     */
    protected void updateShopValues( final Shop shop, final Shop dbShop ) {

        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopValues()" );
        dbShop.setShopName( !StringUtils.isEmpty( shop.getShopName( ) ) ? shop.getShopName( ) : dbShop.getShopName( ) );
        dbShop.setShopOwnId( shop.getShopOwnId( ) != null && shop.getShopOwnId( ) > DAOConstants.ZERO ? shop.getShopOwnId(
        ) : dbShop.getShopOwnId( ) );
        dbShop.setShopWebLink( !StringUtils.isEmpty( shop.getShopWebLink( ) ) ? shop.getShopWebLink( ) :
                dbShop.getShopWebLink( ) );
        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopValues()" );

    }

    /**
     * Update shop items.
     *
     * @param items   the items
     * @param dbItems the db items
     */
    protected void updateShopItems( final List< Item > items, final List< Item > dbItems ) {

        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopItems()" );
        for ( int i = DAOConstants.ZERO; i < dbItems.size( ) && dbItems.size( ) == items.size( ); i++ ) {
            Item dbItem = dbItems.get( i ), item = items.get( i );
            this.updateShopItem( item, dbItem );
        }
        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopItems()" );
    }

    /**
     * Update shop item.
     *
     * @param item   the item
     * @param dbItem the db item
     */
    protected void updateShopItem( final Item item, final Item dbItem ) {

        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopItem()" );

        if ( dbItem.getItemId( ) != item.getItemId( ) ) {
            throw new IllegalArgumentValueException( "Item Id cannot be updated or overridden, dbItem " + dbItem
                    .getItemId( ) + ", itemId " + item.getItemId( ) );
        }
        dbItem.setItemPrice( item.getItemPrice( ) != null && item.getItemPrice( ) > DAOConstants.ZERO ? item
                .getItemPrice( ) : dbItem.getItemPrice( ) );
        dbItem.setItemPriceCurrency( item.getItemPriceCurrency( ) != null ? item.getItemPriceCurrency( ) : dbItem
                .getItemPriceCurrency( ) );

        dbItem.setItemName( item.getItemName( ) != null ? item.getItemName( ) : dbItem.getItemName( ) );
        dbItem.setItemType( item.getItemType( ) != null ? item.getItemType( ) : dbItem.getItemType( ) );
        dbItem.setItemSubType( dbItem.getItemSubType( ) );

        dbItem.setQuantityType( item.getQuantityType( ) != null ? item.getQuantityType( ) : dbItem.getQuantityType( ) );
        dbItem.setQuantity( item.getQuantity( ) != null && item.getQuantity( ) > DAOConstants.ZERO ? item.getQuantity(
        ) : dbItem.getQuantity( ) );

        dbItem.setWeight( item.getWeight( ) != null && item.getWeight( ) > DAOConstants.ZERO ? item.getWeight( ) : dbItem
                .getWeight( ) );
        dbItem.setWeightedUnit( item.getWeightedUnit( ) != null ? item.getWeightedUnit( ) : dbItem.getWeightedUnit( ) );

        dbItem.setBoughtFrom( item.getBoughtFrom( ) != null ? item.getBoughtFrom( ) : dbItem.getBoughtFrom( ) );
        dbItem.setBoughtDate( item.getBoughtDate( ) != null ? item.getBoughtDate( ) : dbItem.getBoughtDate( ) );

        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopItem()" );
    }


    /**
     * Update shop working hours.
     *
     * @param workingHours   the working hours
     * @param dbWorkingHours the db working hours
     */
    protected void updateShopWorkingHours( final List< WorkingHour > workingHours, final List< WorkingHour > dbWorkingHours ) {

        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopWorkingHours()" );

        for ( int i = DAOConstants.ZERO; i < dbWorkingHours.size( ) && workingHours.size( ) == workingHours.size( ); i++ ) {
            WorkingHour dbWorkingHour = dbWorkingHours.get( i ), workingHour = workingHours.get( i );
            this.updateShopWorkingHour( workingHour, dbWorkingHour );
        }
        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopWorkingHours()" );
    }

    /**
     * Update shop working hour.
     *
     * @param workingHour   the working hour
     * @param dbWorkingHour the db working hour
     */
    protected void updateShopWorkingHour( final WorkingHour workingHour, final WorkingHour dbWorkingHour ) {

        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopWorkingHour()" );
        if ( dbWorkingHour.getWorkingHourId( ) != workingHour.getWorkingHourId( ) ) {
            throw new IllegalArgumentValueException( "WorkingHour Id cannot be updated or overridden, dbWorkingHour "
                    + dbWorkingHour.getWorkingHourId( ) + ", workingHourId " + workingHour.getWorkingHourId( ) );
        }

        dbWorkingHour.setDay( !StringUtils.isEmpty( workingHour.getDay( ) ) ? workingHour.getDay( ) : dbWorkingHour.getDay( ) );
        dbWorkingHour.setStartTime( workingHour.getStartTime( ) != null ? workingHour.getStartTime( ) : dbWorkingHour.getStartTime( ) );
        dbWorkingHour.setEndTime( workingHour.getEndTime( ) != null ? workingHour.getEndTime( ) : dbWorkingHour.getEndTime( ) );
        dbWorkingHour.setOffline( workingHour.isOffline( ) != null ? workingHour.isOffline( ) : dbWorkingHour.isOffline( ) );
        dbWorkingHour.setWeekend( workingHour.isWeekend( ) != null ? workingHour.isWeekend( ) : dbWorkingHour.isWeekend( ) );
        dbWorkingHour.setHoliday( workingHour.isHoliday( ) != null ? workingHour.isHoliday( ) : dbWorkingHour.isHoliday( ) );
        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopWorkingHour()" );
    }

    /**
     * Update shop address.
     *
     * @param address   the address
     * @param dbAddress the db address
     */
    protected void updateShopAddress( final Address address, final Address dbAddress ) {

        logger.debug( "Starting " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopAddress()" );
        if ( dbAddress.getAddressId( ) != address.getAddressId( ) ) {
            throw new IllegalArgumentValueException( "Address Id cannot be updated or overridden, dbAddress "
                    + dbAddress.getAddressId( ) + ", addressId " + address.getAddressId( ) );
        }

        dbAddress.setAddress1( !StringUtils.isEmpty( address.getAddress1( ) ) ? address.getAddress1( ) : dbAddress.getAddress1( ) );
        dbAddress.setAddress2( !StringUtils.isEmpty( address.getAddress2( ) ) ? address.getAddress2( ) : dbAddress
                .getAddress2( ) );
        dbAddress.setDistrict( !StringUtils.isEmpty( address.getDistrict( ) ) ? address.getDistrict( ) : dbAddress.getDistrict(
        ) );
        dbAddress.setCity( !StringUtils.isEmpty( address.getCity( ) ) ? address.getCity( ) : dbAddress.getCity( ) );
        dbAddress.setState( !StringUtils.isEmpty( address.getState( ) ) ? address.getState( ) : dbAddress.getState( ) );
        dbAddress.setZipCode( address.getZipCode( ) != null && address.getZipCode( ) > DAOConstants.ZERO ? address
                .getZipCode( ) : dbAddress.getZipCode( ) );
        logger.debug( "Finishing " + this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShopAddress()" );
    }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory( ) {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

}

/* // IMP
DELIMITER $$

USE `bloom`$$

DROP TRIGGER -- !50032
 IF EXISTS  `trigger_date_insert_shops`$$

        CREATE
     --   !50017 DEFINER = 'root'@'localhost'
        TRIGGER `trigger_date_insert_shops` BEFORE INSERT ON `shops`
        FOR EACH ROW BEGIN
        SET NEW.created_date = NOW();
        SET NEW.updated_date = NOW();
        END;
        $$

        DELIMITER ;
*/
/*
DELIMITER $$

USE `bloom`$$

DROP TRIGGER -- !50032 IF EXISTS
 `trigger_update_date_modify_shops`$$

        CREATE
    -- !50017 DEFINER = 'root'@'localhost'
        TRIGGER `trigger_update_date_modify_shops` BEFORE UPDATE ON `shops`
        FOR EACH ROW BEGIN
        SET NEW.updated_date = NOW();
        END;
        $$

        DELIMITER ;
 */
