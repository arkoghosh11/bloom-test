package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.client.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.service.client.ItemsService;
import com.mana.innovative.service.client.builder.ItemResponseBuilder;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;


/**
 * The type Items service impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( ItemsServiceImpl.class );

    /**
     * The Item dAO impl.
     */
    @Resource( name = "itemDAOImpl" )
    private ItemDAO itemDAOImpl;

    /**
     * Gets items.
     *
     * @param isError the is error
     * @return the items
     */
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public Response getItems( boolean isError ) {

        logger.debug( "Initiating getItems() , itemDAO injected successfully" );
        DAOResponse< Item > itemDAOResponse;
        final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItems()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        try {
            itemDAOResponse = itemDAOImpl.getItems( isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );

        }
        try {

            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );
            return Response.status( Response.Status.OK ).entity( itemResponseContainer ).build( );


        } catch ( Exception e ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );

        } finally {
            logger.debug( "Response sent Successfully from getItems()" );
        }
    }

    /**
     * Delete all items.
     *
     * @param isError the is error
     * @param deleteAllItems the delete all items
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response deleteAllItems( boolean isError, boolean deleteAllItems ) {

        logger.debug( "Initiating deleteAllItems() , itemDAO injected successfully" );
        final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllItems()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        DAOResponse< Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.deleteAllItems( deleteAllItems, isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );

        }
        try {

            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );
            return Response.status( Response.Status.OK ).entity( itemResponseContainer ).build( );

        } catch ( Exception e ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        } finally {
            logger.debug( "Response sent Successfully from getItems()" );
        }
    }
}
