package com.mana.innovative.service.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.ShopDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Shop;
import com.mana.innovative.dto.payload.ShopsPayload;
import com.mana.innovative.service.ShopsService;
import com.mana.innovative.service.builder.ShopResponseBuilder;
import com.mana.innovative.service.container.ShopResponseContainer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * The type Shops service impl.
 */
@Service
public class ShopsServiceImpl implements ShopsService {

    private static final Logger logger = Logger.getLogger( ShopsServiceImpl.class );

    @Resource
    private ShopDAO shopDAOImpl;

    /**
     * Gets shops.
     *
     * @return the shops
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public Response getShops( boolean isError ) {

        DAOResponse< Shop > shopDAOResponse;
        String location = this.getClass( ).getCanonicalName( ) + ServiceConstants.HASH + "getShops()";
        ShopResponseContainer< ShopsPayload > shopResponseContainer;
        try {
            shopDAOResponse = shopDAOImpl.getShops( isError );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        }
        try {
            shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );
            return Response.status( Response.Status.OK ).entity( shopResponseContainer ).build( );

        } catch ( Exception exception ) {
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        } finally {
            logger.debug( "Response sent Successfully" );
        }
    }

    /**
     * Delete all shops.
     *
     * @param isError        the is error
     * @param deleteAllShops the delete all shops
     *
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response deleteAllShops( boolean isError, boolean deleteAllShops ) {

        logger.debug( "Initiating deleteAllShops() , shopDAO injected successfully" );
        final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllShops()";
        ShopResponseContainer< ShopsPayload > shopResponseContainer;
        DAOResponse< Shop > shopDAOResponse;
        try {
            shopDAOResponse = shopDAOImpl.deleteAllShops( deleteAllShops, isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );

        }
        try {

            shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );
            return Response.status( Response.Status.OK ).entity( shopResponseContainer ).build( );

        } catch ( Exception e ) {
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        } finally {
            logger.debug( "Response sent Successfully from getShops()" );
        }
    }
}
