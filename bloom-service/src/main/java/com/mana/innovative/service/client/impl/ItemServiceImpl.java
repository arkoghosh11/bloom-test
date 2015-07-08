package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.client.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.client.Item;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.client.ItemService;
import com.mana.innovative.service.client.builder.ItemResponseBuilder;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import com.mana.innovative.utilities.response.ItemDomainDTOConverter;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

/**
 * The type Item service impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public class ItemServiceImpl implements ItemService {


    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemServiceImpl.class );
    /**
     * The ONE.
     */
    private final int ONE = 1;
    /**
     * The Item dAO.
     */
    @Resource( name = "itemDAOImpl" )
    private ItemDAO itemDAOImpl;

    /**
     * Gets item.
     *
     * @param itemId the item id
     * @param requestParams the request params
     * @return the item
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.DEFAULT )
    public Response getItemByItemId( long itemId, RequestParams requestParams ) {

        logger.debug( "Initiating getItemByItemId for item_id = " + itemId + " , itemDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.client.Item > itemDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemByItemId()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        if ( itemId < 0 ) {
            IllegalArgumentValueException exception = new IllegalArgumentValueException( "Value is less than 0" );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        try {
            itemDAOResponse = itemDAOImpl.getItemByItemId( itemId, requestParams );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;
        }
        try {
            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );
            response = Response.status( Status.OK ).entity( itemResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getItemsByItemId sent Successfully " );
        }
    }

    /**
     * Create item.
     *
     * @param itemDTO the item dTO
     * @param requestParams the request params
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response createItem( Item itemDTO, RequestParams requestParams ) {

        logger.debug( "Initiating createItem for incoming item, itemDAO injected successfully" );
        DAOResponse< com.mana.innovative.domain.client.Item > itemDAOResponse;
        Response response;
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createItem()";
        com.mana.innovative.domain.client.Item itemDomain = new com.mana.innovative.domain.client.Item( );
        try {
            itemDomain = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain, itemDTO );
        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        try {
            itemDAOResponse = itemDAOImpl.createItem( itemDomain, requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;
        }
        try {
            itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );
            response = Response.status( Status.CREATED ).entity( itemResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {

            logger.error( "Exception occurred " + location, exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
            return response;

        } finally {
            logger.debug( "Response for createItem generated successfully from Service Level" );
        }
    }

    /**
     * This method is to Update an item. It requires the new item with the updated properties and itemId original
     *
     * @param itemDTO the item
     * @param requestParams the request params
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public Response updateItem( Item itemDTO, RequestParams requestParams ) {

        logger.debug( "Initiating updateItem for incoming item, itemDAO injected successfully" );
        com.mana.innovative.domain.client.Item itemDomain = new com.mana.innovative.domain.client.Item( );
        String location = this.getClass( ).getCanonicalName( ) + "#updateItem";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        if ( itemDTO.getItemId( ) < ONE ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( ", ItemId is Required for " +
                            "Updating an Item" ) );
            return Response.status( Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        itemDomain.setItemId( itemDTO.getItemId( ) );
        try {
            itemDomain = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain, itemDTO );
        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        } catch ( Exception exception ) {
            logger.error( "Exception occurred in " + location, exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.client.Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.updateItem( itemDomain, requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for updateItem generated successfully from Service Level" );
        return Response.ok( ).entity( itemResponseContainer ).build( );
    }

    /**
     * Delete items by item ids.
     *
     * @param itemIds the item ids
     * @param requestParams the request params
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public Response deleteItemsByItemIds( List< Long > itemIds, RequestParams requestParams ) {

        logger.debug( "Initiating deleteItemsByItemIds for incoming itemIds, itemDAO injected successfully" );

        String location = this.getClass( ).getCanonicalName( ) + "#deleteItemsByItemIds";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;
        if ( itemIds.isEmpty( ) ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( )
                    , new IllegalArgumentValueException( ", ItemIds are required for deleting Items" ) );
            return Response.status( Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }

        DAOResponse< com.mana.innovative.domain.client.Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.deleteItemsByItemIds( itemIds, requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for deleteItemsByItemIds generated successfully from Service Level" );
        return Response.ok( ).entity( itemResponseContainer ).build( );
    }

    /**
     * This method is to Delete an item via itemId only.
     *
     * @param itemId the item id
     * @param requestParams the request params
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public Response deleteItemByItemId( long itemId, RequestParams requestParams ) {

        logger.debug( "Initiating deleteItemByItemId for incoming item, itemDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#deleteItemByItemId()";
        ItemResponseContainer< ItemsPayload > itemResponseContainer;

        if ( itemId < ONE ) {
            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( ", ItemId is required for deleting an Item" ) );
            return Response.status( Status.BAD_REQUEST ).entity( itemResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.client.Item > itemDAOResponse;
        try {
            itemDAOResponse = itemDAOImpl.deleteItemByItemId( itemId, requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            itemResponseContainer = ItemResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( itemResponseContainer ).build( );
        }
        itemResponseContainer = ItemResponseBuilder.build( itemDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for deleteItemByItemId generated successfully from Service Level" );
        return Response.ok( ).entity( itemResponseContainer ).build( );
    }
}