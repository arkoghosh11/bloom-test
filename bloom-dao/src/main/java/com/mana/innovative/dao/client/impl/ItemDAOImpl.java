package com.mana.innovative.dao.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.client.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Item dAO impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository( value = "itemDAOImpl" )
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
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class ItemDAOImpl extends BasicDAO implements ItemDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemDAOImpl.class );


//    private static final String HASH = DAOConstants.HASH;

    /**
     * Delete all items.
     *
     * @param deleteAllItems the delete all items
     * @param isError        the is error
     *
     * @return the dAO response
     */
/* IMP DELETE Functions */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteAllItems( final boolean deleteAllItems, final boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllItems()";
        logger.debug( "Starting " + location );
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setDelete( true );
        itemDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        if ( deleteAllItems ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all Item class rows stored in DB will not delete anything from shop class
                // Note: and it should not as a shop may or may not have any items so shop class will still persist
                Query query = session.createQuery( "delete from Item" );
                int count = query.executeUpdate( );
                itemDAOResponse.setCount( count );
                itemDAOResponse.setRequestSuccess( true );
                this.closeDBTransaction( );
            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                logger.error( "Error occurred while trying to clear items table " + location, exception );
                if ( isError ) {
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                itemDAOResponse.setRequestSuccess( false );
            }
        }

        itemDAOResponse.setResults( null );
        itemDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDAOResponse;
    }

    /**
     * Delete item by item id.
     *
     * @param itemId  the item id
     * @param isError the is error
     *
     * @return Returns a boolean value to indicate a successful deletion
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteItemByItemId( long itemId, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemByItemId()";
        logger.debug( "Starting " + location );

        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "delete from Item where itemId=:itemId" );
            query.setParameter( "itemId", itemId );
            itemDAOResponse.setCount( query.executeUpdate( ) );
            this.closeDBTransaction( );
            itemDAOResponse.setRequestSuccess( true );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to delete an item " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( false );
        }

        itemDAOResponse.setResults( null );
        itemDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDAOResponse;
    }

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param isError the is error
     *
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteItemsByItemIds( List< Long > itemIds, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemsByItemIds()";
        logger.debug( "Starting " + location );
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setDelete( true );
        itemDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "DELETE from Item where itemId in(:itemIds)" );
            query.setParameterList( "itemIds", itemIds );
            itemDAOResponse.setCount( query.executeUpdate( ) );
            itemDAOResponse.setRequestSuccess( true );
            this.closeDBTransaction( );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to delete items " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( false );
        }

        itemDAOResponse.setResults( null );
        itemDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDAOResponse;
    }
   /* IMP UPDATE Functions */

    /**
     * This method is to update the DB with the persistence layer to keep the Item value synced
     *
     * @param item    the item
     * @param isError the is error
     *
     * @return Returns a boolean value to indicate a successful update
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > updateItem( Item item, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateItem()";
        logger.debug( "Starting " + location );
        List< Item > items = new ArrayList<>( );
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            if ( item.getItemId( ) < 1 ) {
                throw new IllegalArgumentValueException( "Item Id is invalid: " + item.getItemId( ) );
            }
            this.openDBTransaction( );
//            Item dbItem = (Item) session.get(Item.class, item.getItemId());
            Query query = session.createQuery( " from Item where itemId=:itemId" );
            query.setParameter( "itemId", item.getItemId( ) );
            items = query.list( );
            if ( items.size( ) != DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( "Item List Size is different to expected Size" );
            }
            Item dbItem = items.get( DAOConstants.ZERO );

            this.closeDBTransaction( );
            this.updateShopItem( item, dbItem );
            this.openDBTransaction( );
            session.update( dbItem );
            this.closeDBTransaction( );

            itemDAOResponse.setCount( DAOConstants.ONE );
            itemDAOResponse.setRequestSuccess( Boolean.TRUE );
            items.add( item );
        } catch ( Exception exception ) {
            exception.printStackTrace( );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to update an item " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return itemDAOResponse;
    }

    /* IMP CREATE Functions */

    /**
     * This method is to create a Item object and save it in the DB
     *
     * @param item    the item
     * @param isError the is error
     *
     * @return Returns a boolean value to indicate a successful creation
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
    public DAOResponse< Item > createItem( Item item, Boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createItem()";
        logger.debug( "Starting " + location );
        List< Item > items = new ArrayList<>( );
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setCreate( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            session.save( item );
            this.closeDBTransaction( );

            itemDAOResponse.setCount( DAOConstants.ONE );
            itemDAOResponse.setRequestSuccess( true );
            items.add( item );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to create an item " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDAOResponse;
    }


    /**
     * Gets item by item id.
     *
     * @param itemId  the item id
     * @param isError the is error
     *
     * @return the item by item id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > getItemByItemId( long itemId, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemByItemId()";
        logger.debug( "Starting " + location );

        List< Item > items = null;
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Item where itemId" + " = :item_id" );
            query.setLong( "item_id", itemId );
//            transaction.commit();
            items = query.list( );
            this.closeDBTransaction( );
            if ( !items.isEmpty( ) && items.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " Item Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from items table " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return itemDAOResponse;
    }

    /**
     * This method is to retrieve all the items values from the DB
     *
     * @param isError the is error
     *
     * @return List<Item> </> Return a list of
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Item > getItems( boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItems()";
        logger.debug( "Starting " + location );

        List< Item > items = null;
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            items = ( List< Item > ) session.createQuery( " from Item" ).list( );
            this.closeDBTransaction( );
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from items table " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return itemDAOResponse;
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
