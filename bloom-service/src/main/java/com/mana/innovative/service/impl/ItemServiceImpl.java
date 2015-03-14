package com.mana.innovative.service.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.ItemDAO;
import com.mana.innovative.dao.impl.ItemDAOImpl;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.Item;
import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.ItemService;
import com.mana.innovative.service.builder.ItemResponseBuilder;
import com.mana.innovative.service.container.ItemResponseContainer;
import com.mana.innovative.utilities.response.ItemDomainDTOConverter;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * The type Item service impl.
 */
@Service
public class ItemServiceImpl implements ItemService {


    private static final Logger logger = Logger.getLogger( ItemServiceImpl.class );
    /**
     * The Item dAO.
     */
    @Resource( name = "itemDAOImpl" )
    ItemDAO itemDAOImpl;

    /**
     * Gets item.
     *
     * @param itemId  the item id
     * @param isError the is error
     *
     * @return the item
     */
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.DEFAULT )
    public Response getItemByItemId( long itemId, boolean isError ) {

        logger.debug( "Initiating getItemByItemId for item_id = " + itemId + " , itemDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.Item > itemDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemByItemId()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        try {
            itemDAOResponse = itemDAOImpl.getItemByItemId( itemId, isError );

        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );

            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;
        }
        try {
            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );
            response = Response.status( Response.Status.OK ).entity( itemResponseContainer ).build( );
            return response;

        } catch ( Exception e ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getItemsByItemId sent Successfully " );
        }
    }

    /**
     * Create item.
     *
     * @param itemDTO the item dTO
     * @param isError the is error
     *
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response createItem( Item itemDTO, boolean isError ) {

        logger.debug( "Initiating createItem for incoming item, itemDAO injected successfully" );
        DAOResponse< com.mana.innovative.domain.Item > itemDAOResponse;
        Response response;
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createItem()";
        com.mana.innovative.domain.Item itemDomain = new com.mana.innovative.domain.Item( );
        try {
            itemDomain = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain, itemDTO );
        } catch ( IllegalArgumentValueException | NullPointerException e ) {
            logger.error( "Exception occurred while trying to convert object", e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        try {
            itemDAOResponse = itemDAOImpl.createItem( itemDomain, isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;
        }
        try {
            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );
            response = Response.status( Response.Status.OK ).entity( itemResponseContainer ).build( );
            return response;

        } catch ( Exception e ) {

            logger.error( "Exception occurred " + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;

        } finally {
            logger.debug( " Response for createItem generated successfully from Service Level" );
        }
    }

    /**
     * This method is to Update an item. It requires the new item with the updated properties and itemId original
     *
     * @param itemDTO the item
     * @param isError the is error
     *
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public Response updateItem( Item itemDTO, boolean isError ) {

        logger.debug( "Initiating updateItem for incoming item, itemDAO injected successfully" );
        com.mana.innovative.domain.Item itemDomain = new com.mana.innovative.domain.Item( );
        String location = this.getClass( ).getCanonicalName( ) + "#updateItem";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        if ( itemDTO.getItemId( ) < 1 ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError,
                    new IllegalArgumentValueException( ", ItemId is Required for " +
                            "Updating an Item" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        itemDomain.setItemId( itemDTO.getItemId( ) );
        try {
            itemDomain = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain, itemDTO );
        } catch ( IllegalArgumentValueException | NullPointerException e ) {
            logger.error( "Exception occurred while trying to convert object", e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        } catch ( Exception e ) {
            logger.error( "Exception occurred in " + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.updateItem( itemDomain, isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );

            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );

        logger.debug( " Response for updateItem generated successfully from Service Level" );
        return Response.ok( ).entity( itemResponseContainer ).build( );
    }

    /**
     * This method is to Delete an item with its instance required from client.
     *
     * @param itemDTO the item
     * @param isError the is error
     *
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public Response deleteItem( Item itemDTO, boolean isError ) {

        logger.debug( "Initiating deleteItem for incoming item, itemDAO injected successfully" );

        com.mana.innovative.domain.Item itemDomain = new com.mana.innovative.domain.Item( );

        String location = this.getClass( ).getCanonicalName( ) + "#deleteITem";
        ItemResponseContainer< ItemsPayload > itemResponseContainer = null;
        if ( itemDTO.getItemId( ) < 1 ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError
                    , new IllegalArgumentValueException(
                    ", ItemId is required for deleting an Item" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        itemDomain.setItemId( itemDTO.getItemId( ) );
        try {
            itemDomain = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain, itemDTO );
        } catch ( IllegalArgumentValueException | NullPointerException e ) {
            logger.error( "Exception occurred while trying to convert object", e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        } catch ( Exception e ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.deleteItem( itemDomain, isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );

        logger.debug( " Response for deleteItem generated successfully from Service Level" );
        return Response.ok( ).entity( itemResponseContainer ).build( );
    }

    /**
     * This method is to Delete an item via itemId only.
     *
     * @param itemId  the item id
     * @param isError the is error
     *
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public Response deleteItemByItemId( long itemId, boolean isError ) {

        logger.debug( "Initiating deleteItemByItemId for incoming item, itemDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#deleteITem";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;

        if ( itemId < 1 ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, isError,
                    new IllegalArgumentValueException( ", ItemId is required for deleting an Item" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.deleteItemByItemId( itemId, isError );
        } catch ( Exception e ) {
            if ( e instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, e );
            } else
                logger.error( "Exception occurred in" + location, e );

            itemResponseContainer = ItemResponseBuilder.buildError( location, isError, e );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, isError );

        logger.debug( " Response for deleteItemByItemId generated successfully from Service Level" );
        return Response.ok( ).entity( itemResponseContainer ).build( );
    }
}