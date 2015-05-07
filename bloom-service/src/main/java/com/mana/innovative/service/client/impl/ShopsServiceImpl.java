package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.client.ShopDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.dto.client.payload.ShopsPayload;
import com.mana.innovative.service.client.ShopsService;
import com.mana.innovative.service.client.builder.ShopResponseBuilder;
import com.mana.innovative.service.client.container.ShopResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * The type Shops service impl.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class ShopsServiceImpl implements ShopsService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ShopsServiceImpl.class );

    /**
     * The Shop dAO impl.
     */
    @Resource
    private ShopDAO shopDAOImpl;

    /**
     * Gets shops.
     *
     * @param isError the is error
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
     * @param isError the is error
     * @param deleteAllShops the delete all shops
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
