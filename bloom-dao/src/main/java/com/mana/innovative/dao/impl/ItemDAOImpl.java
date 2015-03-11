package com.mana.innovative.dao.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

    private static final Logger log = Logger.getLogger( ItemDAOImpl.class );

    static {
        log.setLevel( Level.DEBUG );
    }


    /* IMP DELETE Functions */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > deleteAllItems( final boolean deleteAllItems, final boolean isError ) {
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;
        itemDAOResponse.setDelete( true );
        itemDAOResponse.setCount( DAOConstants.ZERO );
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        if ( deleteAllItems ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all Item class rows stored in DB will not delete anything from shop class
                // Note: and it should not as a shop may or may not have any items so shop class will still persist
                Query query = session.createQuery( "delete from Item" );
                int count = query.executeUpdate( );
                itemDAOResponse.setCount( count );
                itemDAOResponse.setRequestSuccess( true );
//            transaction.commit();
            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                log.error( "Error occurred while trying to delete an item with itemDAO.deleteItem()", exception );
                if ( isError ) {
                    String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItem()";
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                itemDAOResponse.setRequestSuccess( false );
            } finally {
                this.closeDBTransaction( );
            }
        }
        itemDAOResponse.setResults( null );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }

    /**
     * @param itemId {@link Integer}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful deletion
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteItemByItemId( long itemId, boolean isError ) {

        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;
        itemDAOResponse.setDelete( true );
        itemDAOResponse.setCount( DAOConstants.ZERO );
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "delete from Item where itemId=:itemId" );
            query.setParameter( "itemId", itemId );
            int count = query.executeUpdate( );
            itemDAOResponse.setCount( count );
            itemDAOResponse.setRequestSuccess( true );
//            transaction.commit();
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to delete an item with itemDAO.deleteItem()", exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItem()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( false );
        } finally {
            this.closeDBTransaction( );
        }
        itemDAOResponse.setResults( null );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }

    /**
     * This method is to delete an item by Item itself
     *
     * @param item {@link Item}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful deletion
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Item > deleteItem( Item item, boolean isError ) {

        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;
        itemDAOResponse.setDelete( true );
        itemDAOResponse.setCount( DAOConstants.ZERO );
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            this.openDBTransaction( );
            List items = session.createCriteria( Item.class )
                    .add( Restrictions.eq( "itemId", item.getItemId( ) ) )
                    .list( );
            session.delete( items.get( DAOConstants.ZERO ) );
            itemDAOResponse.setRequestSuccess( true );
            itemDAOResponse.setCount( items.size( ) );
//            transaction.commit();
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to delete an item with itemDAO.deleteItem()", exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItem()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( false );
        } finally {
            this.closeDBTransaction( );
        }
        itemDAOResponse.setResults( null );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }
   /* IMP UPDATE Functions */

    /**
     * This method is to update the DB with the persistence layer to keep the Item value synced
     *
     * @param item {@link Item}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful update
     */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > updateItem( Item item, boolean isError ) {

        List< Item > items = new ArrayList<>( );
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info( "**Inside itemDAO.updateItem()***" );
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
            dbItem.setItemPrice( item.getItemPrice( ) != DAOConstants.ZERO && item.getItemPrice( ) > DAOConstants.ZERO ? item.getItemPrice( ) : dbItem
                    .getItemPrice( ) );
            dbItem.setItemPriceCurrency( item.getItemPriceCurrency( ) != null ? item.getItemPriceCurrency( ) : dbItem
                    .getItemPriceCurrency( ) );
            dbItem.setItemName( item.getItemName( ) != null ? item.getItemName( ) : dbItem.getItemName( ) );
            dbItem.setItemType( item.getItemType( ) != null ? item.getItemType( ) : dbItem.getItemType( ) );
            dbItem.setItemSubType( dbItem.getItemSubType( ) );

            dbItem.setQuantityType( item.getQuantityType( ) != null ? item.getQuantityType( ) : dbItem.getQuantityType( ) );
            dbItem.setQuantity( item.getQuantity( ) > DAOConstants.ZERO ? item.getQuantity( ) : dbItem.getQuantity( ) );

            dbItem.setWeightedUnit( item.getWeightedUnit( ) != null ? item.getWeightedUnit( ) : dbItem.getWeightedUnit( ) );
            dbItem.setWeight( item.getWeight( ) > DAOConstants.ZERO ? item.getWeight( ) : dbItem.getWeight( ) );

            dbItem.setBoughtFrom( item.getBoughtFrom( ) != null ? item.getBoughtFrom( ) : dbItem.getBoughtFrom( ) );
            dbItem.setBoughtDate( item.getBoughtDate( ) != null ? item.getBoughtDate( ) : dbItem.getBoughtDate( ) );

            this.openDBTransaction( );
            session.update( dbItem );
            this.closeDBTransaction( );

            itemDAOResponse.setCount( DAOConstants.ONE );
            itemDAOResponse.setRequestSuccess( Boolean.TRUE );
            items.add( item );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to create an item with itemDAO.createItem()", exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#" + "createItem()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }

    /* IMP CREATE Functions */

    /**
     * This method is to create a Item object and save it in the DB
     *
     * @param item {@link Item}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful creation
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
    public DAOResponse< Item > createItem( Item item, Boolean isError ) {

        List< Item > items = new ArrayList<>( );
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        itemDAOResponse.setCreate( true );
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info( "**Starting " + this.getClass( ).getCanonicalName( ) + "#createItem()***" );
        try {
            this.openDBTransaction( );
            session.save( item );
            this.closeDBTransaction( );

            itemDAOResponse.setCount( DAOConstants.ONE );
            itemDAOResponse.setRequestSuccess( Boolean.TRUE );
            items.add( item );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to create an item with itemDAO.createItem()", exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#" + "createItem()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }


    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Item > getItemByItemId( long itemId, boolean isError ) {

        List< Item > items = null;
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info( "**Inside itemDAO.getItems()***" );
        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Item where itemId" + " = :item_id" );
            query.setLong( "item_id", itemId );
//            transaction.commit();
            items = query.list( );
            if ( !items.isEmpty( ) && items.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " Item Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to fetch data from items table for itemDAO" + ".getItems()",
                    exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#getItemByItemId()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }

    /**
     * This method is to retrieve all the items values from the DB
     *
     * @return List<Item></> Return a list of {@link com.mana.innovative.domain.Item}
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Item > getItems( boolean isError ) {

        List< Item > items = null;
        DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info( "**Inside itemDAO.getItems()***" );
        try {
            this.openDBTransaction( );
            items = ( List< Item > ) session.createQuery( " from Item" ).list( );
//            transaction.commit();
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            log.error( "Error occurred while trying to fetch data from items table for itemDAO" + ".getItems()",
                    exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#getItems()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
        itemDAOResponse.setResults( items );
        itemDAOResponse.setErrorContainer( errorContainer );
        return itemDAOResponse;
    }

    public SessionFactory getSessionFactory( ) {

        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {

        this.sessionFactory = sessionFactory;
    }
}
