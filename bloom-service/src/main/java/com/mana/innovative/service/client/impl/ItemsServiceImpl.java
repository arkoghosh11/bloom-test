package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.client.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.client.ItemsService;
import com.mana.innovative.service.client.builder.ItemResponseBuilder;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;


/**
 * The type Items service impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemsServiceImpl.class );

    /**
     * The Item dAO impl.
     */
    @Resource( name = "itemDAOImpl" )
    private ItemDAO itemDAOImpl;

    /**
     * Gets items.
     *
     * @param requestParams the request params
     * @return the items
     */
    @Override
    @Cacheable( value = ServiceConstants.ITEMS_CACHE, key = ServiceConstants.KEY_NAME )
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public Response getItems( RequestParams requestParams ) {

        logger.debug( "Initiating getItems() , itemDAO injected successfully" );
        DAOResponse< Item > itemDAOResponse;
        final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItems()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        try {
            itemDAOResponse = itemDAOImpl.getItems( requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );

        }
        try {

            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );
            return Response.status( Response.Status.OK ).entity( itemResponseContainer ).build( );


        } catch ( Exception exception ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );

        } finally {
            logger.debug( "Response sent Successfully from getItems()" );
        }
    }

    /**
     * Delete all items.
     *
     * @param requestParams the request params
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response deleteAllItems( RequestParams requestParams ) {

        logger.debug( "Initiating deleteAllItems() , itemDAO injected successfully" );
        final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllItems()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        DAOResponse< Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.deleteAllItems( requestParams );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );

        }
        try {

            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );
            return Response.status( Response.Status.OK ).entity( itemResponseContainer ).build( );

        } catch ( Exception e ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        } finally {
            logger.debug( "Response sent Successfully from getItems()" );
        }
    }
}
