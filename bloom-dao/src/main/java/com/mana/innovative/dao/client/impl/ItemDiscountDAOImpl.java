package com.mana.innovative.dao.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.client.ItemDiscountDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.dto.request.RequestParams;
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
 * The class ItemDiscountDAOImpl is for todo.
 * Created by BLOOM on 9/22/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository( value = "itemDiscountDAOImpl" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class ItemDiscountDAOImpl extends BasicDAO implements ItemDiscountDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemDiscountDAOImpl.class );


//    private static final String HASH = DAOConstants.HASH;

    /**
     * Delete all itemDiscounts.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
/* IMP DELETE Functions */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< ItemDiscount > deleteAllItemDiscounts( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllItemDiscounts()";
        logger.debug( "Starting " + location );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        itemDiscountDAOResponse.setDelete( true );
        itemDiscountDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        if ( requestParams.isDeleteAll( ) ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all ItemDiscount class rows stored in DB will not delete anything from shop class
                // Note: and it should not as a shop may or may not have any itemDiscounts so shop class will still persist
                Query query = session.createQuery( "delete from ItemDiscount" );
                int count = query.executeUpdate( );
                itemDiscountDAOResponse.setCount( count );
                itemDiscountDAOResponse.setRequestSuccess( true );
                this.closeDBTransaction( );
            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                logger.error( "Error occurred while trying to clear itemDiscounts table " + location, exception );
                if ( requestParams.isError( ) ) {
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                itemDiscountDAOResponse.setRequestSuccess( false );
            }
        }

        itemDiscountDAOResponse.setResults( null );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDiscountDAOResponse;
    }

    /**
     * Delete itemDiscount by itemDiscount id.
     *
     * @param itemDiscountId the itemDiscount id
     * @param requestParams  the request params
     *
     * @return Returns a boolean value to indicate a successful deletion
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< ItemDiscount > deleteItemDiscountByItemDiscountId( long itemDiscountId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemDiscountByItemDiscountId()";
        logger.debug( "Starting " + location );

        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        itemDiscountDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "delete from ItemDiscount where itemDiscountId=:itemDiscountId" );
            query.setParameter( "itemDiscountId", itemDiscountId );
            itemDiscountDAOResponse.setCount( query.executeUpdate( ) );
            this.closeDBTransaction( );
            itemDiscountDAOResponse.setRequestSuccess( true );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to delete an itemDiscount " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDiscountDAOResponse.setRequestSuccess( false );
        }

        itemDiscountDAOResponse.setResults( null );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDiscountDAOResponse;
    }

    /**
     * Delete itemDiscounts by itemDiscount ids.
     *
     * @param itemDiscountIds the itemDiscount ids
     * @param requestParams   the request params
     *
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< ItemDiscount > deleteItemDiscountsByItemDiscountIds( List< Long > itemDiscountIds, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemDiscountsByItemDiscountIds()";
        logger.debug( "Starting " + location );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        itemDiscountDAOResponse.setDelete( true );
        itemDiscountDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "DELETE from ItemDiscount where itemDiscountId in(:itemDiscountIds)" );
            query.setParameterList( "itemDiscountIds", itemDiscountIds );
            itemDiscountDAOResponse.setCount( query.executeUpdate( ) );
            itemDiscountDAOResponse.setRequestSuccess( true );
            this.closeDBTransaction( );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to delete itemDiscounts " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDiscountDAOResponse.setRequestSuccess( false );
        }

        itemDiscountDAOResponse.setResults( null );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDiscountDAOResponse;
    }
   /* IMP UPDATE Functions */

    /**
     * This method is to update the DB with the persistence layer to keep the ItemDiscount value synced
     *
     * @param itemDiscount  the itemDiscount
     * @param requestParams the request params
     *
     * @return Returns a boolean value to indicate a successful update
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< ItemDiscount > updateItemDiscount( ItemDiscount itemDiscount, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateItemDiscount()";
        logger.debug( "Starting " + location );
        List< ItemDiscount > itemDiscounts = new ArrayList<>( );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        itemDiscountDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            if ( itemDiscount.getItemDiscountId( ) < 1 ) {
                throw new IllegalArgumentValueException( "ItemDiscount Id is invalid: " + itemDiscount.getItemDiscountId( ) );
            }

            this.openDBTransaction( );
            session.update( itemDiscount );
            this.closeDBTransaction( );

            itemDiscountDAOResponse.setCount( DAOConstants.ONE );
            itemDiscountDAOResponse.setRequestSuccess( Boolean.TRUE );
            itemDiscounts.add( itemDiscount );

        } catch ( Exception exception ) {

            exception.printStackTrace( );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to update an itemDiscount " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDiscountDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemDiscountDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemDiscountDAOResponse.setResults( itemDiscounts );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return itemDiscountDAOResponse;
    }

    /* IMP CREATE Functions */

    /**
     * This method is to create a ItemDiscount object and save it in the DB
     *
     * @param itemDiscount  the itemDiscount
     * @param requestParams the request params
     *
     * @return Returns a boolean value to indicate a successful creation
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
    public DAOResponse< ItemDiscount > createItemDiscount( ItemDiscount itemDiscount, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createItemDiscount()";
        logger.debug( "Starting " + location );
        List< ItemDiscount > itemDiscounts = new ArrayList<>( );
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        itemDiscountDAOResponse.setCreate( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            session.save( itemDiscount );
            this.closeDBTransaction( );

            itemDiscountDAOResponse.setCount( DAOConstants.ONE );
            itemDiscountDAOResponse.setRequestSuccess( true );
            itemDiscounts.add( itemDiscount );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to create an itemDiscount " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemDiscountDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemDiscountDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemDiscountDAOResponse.setResults( itemDiscounts );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemDiscountDAOResponse;
    }


    /**
     * Gets itemDiscount by itemDiscount id.
     *
     * @param itemDiscountId the itemDiscount id
     * @param requestParams  the request params
     *
     * @return the itemDiscount by itemDiscount id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< ItemDiscount > getItemDiscountByItemDiscountId( long itemDiscountId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemDiscountByItemDiscountId()";
        logger.debug( "Starting " + location );

        List< ItemDiscount > itemDiscounts = null;
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from ItemDiscount where itemDiscountId" + " = :itemDiscount_id" );
            query.setLong( "itemDiscount_id", itemDiscountId );
//            transaction.commit();
            itemDiscounts = query.list( );
            this.closeDBTransaction( );
            if ( !itemDiscounts.isEmpty( ) && itemDiscounts.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " ItemDiscount Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from itemDiscounts table " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemDiscountDAOResponse.setCount( itemDiscounts == null ? DAOConstants.ZERO : itemDiscounts.size( ) );
        itemDiscountDAOResponse.setResults( itemDiscounts );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return itemDiscountDAOResponse;
    }

    /**
     * This method is to retrieve all the itemDiscounts values from the DB
     *
     * @param requestParams the request params
     *
     * @return List<ItemDiscount>     </> Return a list of
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< ItemDiscount > getItemDiscounts( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemDiscounts()";
        logger.debug( "Starting " + location );

        List< ItemDiscount > itemDiscounts = null;
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            itemDiscounts = ( List< ItemDiscount > ) session.createQuery( " from ItemDiscount" ).list( );
            this.closeDBTransaction( );
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from itemDiscounts table " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemDiscountDAOResponse.setCount( itemDiscounts == null ? DAOConstants.ZERO : itemDiscounts.size( ) );
        itemDiscountDAOResponse.setResults( itemDiscounts );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return itemDiscountDAOResponse;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< ItemDiscount > getItemDiscountsByParams( final String key, final List values, RequestParams requestParams
    ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemDiscountByItemDiscountId()";
        logger.debug( "Starting " + location );

        List< ItemDiscount > itemDiscountList = null;
        DAOResponse< ItemDiscount > itemDiscountDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );

            Query query = session.
                    createQuery( " from ItemDiscount where " + key.substring(
                            key.indexOf( ServiceConstants.DOT ) + 1 ) + " in " + "(:value)" );
            query.setParameterList( "value", values );
            itemDiscountList = query.list( );
            itemDiscountDAOResponse.setRequestSuccess( Boolean.TRUE );

            this.closeDBTransaction( );
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from itemDiscounts table " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemDiscountDAOResponse.setCount( itemDiscountList == null ? DAOConstants.ZERO : itemDiscountList.size( ) );
        itemDiscountDAOResponse.setResults( itemDiscountList );
        itemDiscountDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return itemDiscountDAOResponse;
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
