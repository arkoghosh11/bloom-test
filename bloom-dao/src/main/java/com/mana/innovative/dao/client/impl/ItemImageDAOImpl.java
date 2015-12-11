package com.mana.innovative.dao.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.client.ItemImageDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemImage;
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
 * The class ItemImageImageDAOImpl is for todo.
 * Created by BLOOM on 9/22/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository( value = "itemImageDAOImpl" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class ItemImageDAOImpl extends BasicDAO implements ItemImageDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemImageDAOImpl.class );


//    private static final String HASH = DAOConstants.HASH;

    /**
     * Delete all itemImages.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
     /* IMP DELETE Functions */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< ItemImage > deleteAllItemImages( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllItemImages()";
        logger.debug( "Starting " + location );
        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        itemImageDAOResponse.setDelete( true );
        itemImageDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        if ( requestParams.isDeleteAll( ) ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all ItemImage class rows stored in DB will not delete anything from shop class
                // Note: and it should not as a shop may or may not have any itemImages so shop class will still persist
                Query query = session.createQuery( "delete from ItemImage" );
                int count = query.executeUpdate( );
                itemImageDAOResponse.setCount( count );
                itemImageDAOResponse.setRequestSuccess( true );
                this.closeDBTransaction( );
            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                logger.error( "Error occurred while trying to clear itemImages table " + location, exception );
                if ( requestParams.isError( ) ) {
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                itemImageDAOResponse.setRequestSuccess( false );
            }
        }

        itemImageDAOResponse.setResults( null );
        itemImageDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemImageDAOResponse;
    }

    /**
     * Delete itemImage by itemImage id.
     *
     * @param itemImageId   the itemImage id
     * @param requestParams the request params
     *
     * @return Returns a boolean value to indicate a successful deletion
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< ItemImage > deleteItemImageByItemImageId( long itemImageId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemImageByItemImageId()";
        logger.debug( "Starting " + location );

        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        itemImageDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "delete from ItemImage where itemImageId=:itemImageId" );
            query.setParameter( "itemImageId", itemImageId );
            itemImageDAOResponse.setCount( query.executeUpdate( ) );
            this.closeDBTransaction( );
            itemImageDAOResponse.setRequestSuccess( true );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to delete an itemImage " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemImageDAOResponse.setRequestSuccess( false );
        }

        itemImageDAOResponse.setResults( null );
        itemImageDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemImageDAOResponse;
    }

    /**
     * Delete itemImages by itemImage ids.
     *
     * @param itemImageIds  the itemImage ids
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< ItemImage > deleteItemImagesByItemImageIds( List< Long > itemImageIds, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemImagesByItemImageIds()";
        logger.debug( "Starting " + location );
        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        itemImageDAOResponse.setDelete( true );
        itemImageDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( "DELETE from ItemImage where itemImageId in(:itemImageIds)" );
            query.setParameterList( "itemImageIds", itemImageIds );
            itemImageDAOResponse.setCount( query.executeUpdate( ) );
            itemImageDAOResponse.setRequestSuccess( true );
            this.closeDBTransaction( );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to delete itemImages " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemImageDAOResponse.setRequestSuccess( false );
        }

        itemImageDAOResponse.setResults( null );
        itemImageDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemImageDAOResponse;
    }
   /* IMP UPDATE Functions */

    /**
     * This method is to update the DB with the persistence layer to keep the ItemImage value synced
     *
     * @param itemImage     the itemImage
     * @param requestParams the request params
     *
     * @return Returns a boolean value to indicate a successful update
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< ItemImage > updateItemImage( ItemImage itemImage, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateItemImage()";
        logger.debug( "Starting " + location );
        List< ItemImage > itemImages = new ArrayList<>( );
        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        itemImageDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            if ( itemImage.getItemImageId( ) < 1 ) {
                throw new IllegalArgumentValueException( "ItemImage Id is invalid: " + itemImage.getItemImageId( ) );
            }

            this.openDBTransaction( );
            session.update( itemImage );
            this.closeDBTransaction( );

            itemImageDAOResponse.setCount( DAOConstants.ONE );
            itemImageDAOResponse.setRequestSuccess( Boolean.TRUE );
            itemImages.add( itemImage );

        } catch ( Exception exception ) {

            exception.printStackTrace( );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to update an itemImage " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemImageDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemImageDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemImageDAOResponse.setResults( itemImages );
        itemImageDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return itemImageDAOResponse;
    }

    /* IMP CREATE Functions */

    /**
     * This method is to create a ItemImage object and save it in the DB
     *
     * @param itemImage     the itemImage
     * @param requestParams the request params
     *
     * @return Returns a boolean value to indicate a successful creation
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
    public DAOResponse< ItemImage > createItemImage( ItemImage itemImage, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createItemImage()";
        logger.debug( "Starting " + location );
        List< ItemImage > itemImages = new ArrayList<>( );
        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        itemImageDAOResponse.setCreate( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            session.save( itemImage );
            this.closeDBTransaction( );

            itemImageDAOResponse.setCount( DAOConstants.ONE );
            itemImageDAOResponse.setRequestSuccess( true );
            itemImages.add( itemImage );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to create an itemImage " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            itemImageDAOResponse.setRequestSuccess( Boolean.FALSE );
            itemImageDAOResponse.setCount( DAOConstants.ZERO );
        }
        itemImageDAOResponse.setResults( itemImages );
        itemImageDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return itemImageDAOResponse;
    }


    /**
     * Gets itemImage by itemImage id.
     *
     * @param itemImageId   the itemImage id
     * @param requestParams the request params
     *
     * @return the itemImage by itemImage id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< ItemImage > getItemImageByItemImageId( long itemImageId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemImageByItemImageId()";
        logger.debug( "Starting " + location );

        List< ItemImage > itemImages = null;
        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from ItemImage where itemImageId" + " = :itemImage_id" );
            query.setLong( "itemImage_id", itemImageId );
//            transaction.commit();
            itemImages = query.list( );
            this.closeDBTransaction( );
            if ( !itemImages.isEmpty( ) && itemImages.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " ItemImage Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from itemImages table " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemImageDAOResponse.setCount( itemImages == null ? DAOConstants.ZERO : itemImages.size( ) );
        itemImageDAOResponse.setResults( itemImages );
        itemImageDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return itemImageDAOResponse;
    }

    /**
     * This method is to retrieve all the itemImages values from the DB
     *
     * @param requestParams the request params
     *
     * @return List<ItemImage>    </> Return a list of
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< ItemImage > getItemImages( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemImages()";
        logger.debug( "Starting " + location );

        List< ItemImage > itemImages = null;
        DAOResponse< ItemImage > itemImageDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            itemImages = ( List< ItemImage > ) session.createQuery( " from ItemImage" ).list( );
            this.closeDBTransaction( );
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from itemImages table " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        itemImageDAOResponse.setCount( itemImages == null ? DAOConstants.ZERO : itemImages.size( ) );
        itemImageDAOResponse.setResults( itemImages );
        itemImageDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return itemImageDAOResponse;
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