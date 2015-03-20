package com.mana.innovative.dao.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.ShopDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Shop;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Shop dAO impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ShopDAOImpl extends BasicDAO implements ShopDAO {

    private static final Logger log = Logger.getLogger( ShopDAOImpl.class );

    static {
        log.setLevel( Level.DEBUG );
    }

    /**
     * Gets shop by shop id.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the shop by shop id
     */
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Shop > getShopByShopId( long shopId, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getShopByShopId()";
        log.debug( "Starting " + location );

        List< Shop > shops = null;
        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Shop where shopId" + " = :shop_id" );
            query.setLong( "shop_id", shopId );
//            transaction.commit();
            shops = query.list( );
            this.closeDBTransaction( );
            if ( !shops.isEmpty( ) && shops.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " Shop Size exceeded maximum value of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to fetch data from shops table " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        shopDAOResponse.setCount( shops == null ? DAOConstants.ZERO : shops.size( ) );
        shopDAOResponse.setResults( shops );
        shopDAOResponse.setErrorContainer( errorContainer );
        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

    /**
     * This method is to retrieve all the shops values from the DB
     *
     * @param isError the is error
     *
     * @return the shops
     */
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Shop > getShops( boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getShops()";
        log.debug( "Starting " + location );
        List< Shop > shops = null;
        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            this.openDBTransaction( );
            shops = session.createQuery( " from Shop" ).list( );
            this.closeDBTransaction( );
//            transaction.commit();
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Error occurred while trying to fetch data from shops table " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        shopDAOResponse.setCount( shops == null ? DAOConstants.ZERO : shops.size( ) );
        shopDAOResponse.setResults( shops );
        shopDAOResponse.setErrorContainer( errorContainer );
        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

    /**
     * Create shop.
     *
     * @param shop    the shop
     * @param isError the is error
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Shop > createShop( final Shop shop, boolean isError ) {

        if ( shop == null ) {
            throw new NullPointerException( "Creation object Shop is null" );
        }
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createShop()";
        log.debug( "Starting " + location );
        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        shopDAOResponse.setCreate( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );
        List< Shop > shops = new ArrayList<>( );
        try {
            this.openDBTransaction( );
            // Note need to use saveOrUpdate here since Shop, or other stuff of shop object might have been reused
            session.save( shop );
            shopDAOResponse.setCount( DAOConstants.ONE );
            shops.add( shop );
            this.closeDBTransaction( );
            shopDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            shopDAOResponse.setRequestSuccess( Boolean.FALSE );
            log.error( "Failed to create shop", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        shopDAOResponse.setResults( shops );
        shopDAOResponse.setErrorContainer( errorContainer );

        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

    /**
     * Update shop.
     *
     * @param shop    the shop
     * @param isError the is error
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Shop > updateShop( final Shop shop, boolean isError ) {

        if ( shop == null ) {
            throw new NullPointerException( "Update object Shop is null" );
        }
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateShop()";
        log.debug( "Starting " + location );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        shopDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );
        List< Shop > shops = null;

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Shop where shopId=:shopId" );
            query.setParameter( "shopId", shop.getShopId( ) );
            shops = query.list( );
            if ( shops.size( ) != DAOConstants.ONE ) {
                throw new IllegalArgumentValueException( "Shop List Size is different to expected Size" );
            }
            Shop dbShop = shops.get( DAOConstants.ZERO );
            this.updateShopValues( shop, dbShop );
            this.updateShopAddress( shop.getAddress( ), dbShop.getAddress( ) );
            this.updateShopWorkingHours( shop.getWorkingHours( ), dbShop.getWorkingHours( ) );
            this.updateShopItems( shop.getItems( ), dbShop.getItems( ) );
            this.closeDBTransaction( );
            this.openDBTransaction( );
            session.update( shop );
            this.closeDBTransaction( );
            shopDAOResponse.setCount( DAOConstants.ONE );
            shops = new ArrayList<>( );
            shops.add( shop );
            shopDAOResponse.setCount( DAOConstants.ONE );
            shopDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to update shop", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }

        shopDAOResponse.setResults( shops );
        shopDAOResponse.setErrorContainer( errorContainer );
        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Shop > deleteShopByShopId( final long shopId, final boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteShopByShopId()";
        log.debug( "Starting " + location );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        shopDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from Shop where shopId=:shopId" );
            query.setParameter( "shopId", shopId );
            shopDAOResponse.setCount( query.executeUpdate( ) );
            this.closeDBTransaction( );
            shopDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to delete shop", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        shopDAOResponse.setResults( null );
        shopDAOResponse.setErrorContainer( errorContainer );
        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Shop > deleteShopsByShopIds( final List< Long > shopIds, final boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteShopsByShopIds()";
        log.debug( "Starting " + location );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        shopDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from Shop where shopId in (:shopIds)" );
            query.setParameterList( "shopIds", shopIds );
            shopDAOResponse.setCount( query.executeUpdate( ) );

            this.closeDBTransaction( );
            shopDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to delete shops with given ids " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            shopDAOResponse.setRequestSuccess( false );
        }
        shopDAOResponse.setResults( null );
        shopDAOResponse.setErrorContainer( errorContainer );
        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Shop > deleteAllShops( final boolean deleteAllShops, final boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteShopsByShopIds()";
        log.debug( "Starting " + location );

        DAOResponse< Shop > shopDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );
        shopDAOResponse.setDelete( true );
        shopDAOResponse.setCount( DAOConstants.ZERO );

        if ( deleteAllShops ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all Shop class rows stored in DB will also delete joined classes items and
                // Note: working hours
                Query query = session.createQuery( "delete from Shop" );
                shopDAOResponse.setCount( query.executeUpdate( ) );
                this.closeDBTransaction( );
                shopDAOResponse.setRequestSuccess( true );
//            transaction.commit();
            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                log.error( "Error occurred while trying to delete all shops " + location, exception );
                if ( isError ) {
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                shopDAOResponse.setRequestSuccess( false );
            }
        }
        shopDAOResponse.setResults( null );
        shopDAOResponse.setErrorContainer( errorContainer );
        log.debug( "Finishing " + location );
        return shopDAOResponse;
    }

}
