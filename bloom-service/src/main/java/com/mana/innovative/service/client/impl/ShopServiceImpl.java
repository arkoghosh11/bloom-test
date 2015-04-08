package com.mana.innovative.service.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.client.ShopDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.client.Shop;
import com.mana.innovative.dto.client.payload.ShopsPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.client.ShopService;
import com.mana.innovative.service.client.builder.ShopResponseBuilder;
import com.mana.innovative.service.client.container.ShopResponseContainer;
import com.mana.innovative.utilities.response.ShopDomainDTOConverter;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Shop service impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = Logger.getLogger( ShopServiceImpl.class );
    private final int ONE = 1;

    @Resource
    private ShopDAO shopDAOImpl;

    /**
     * Gets shopDTO.
     *
     * @param shopId  the shopDTO id
     * @param isError the is error
     *
     * @return the shopDTO
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public Response getShopByShopId( final long shopId, final boolean isError ) {

        logger.debug( "Initiating getShopByShopId for shop_id = " + shopId + " , shopDAO injected successfully" );


        DAOResponse< com.mana.innovative.domain.client.Shop > shopDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getShopByShopId()";
        ShopResponseContainer< ShopsPayload > shopResponseContainer;
        if ( shopId < 0 ) {
            IllegalArgumentValueException exception = new IllegalArgumentValueException( "Value is less than 0" );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( shopResponseContainer ).build( );
        }

        try {
            shopDAOResponse = shopDAOImpl.getShopByShopId( shopId, isError );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
            return response;
        }
        try {
            shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );
            response = Response.status( Response.Status.OK ).entity( shopResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getShopsByShopId sent Successfully " );
        }
    }

    /**
     * Create shop.
     *
     * @param shopDTO the shop dTO
     * @param isError the is error
     *
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response createShop( Shop shopDTO, boolean isError ) {

        logger.debug( "Initiating createShop for incoming shop, shopDAO injected successfully" );
        DAOResponse< com.mana.innovative.domain.client.Shop > shopDAOResponse;
        Response response;
        ShopResponseContainer< ShopsPayload > shopResponseContainer;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createShop()";
        com.mana.innovative.domain.client.Shop shopDomain = new com.mana.innovative.domain.client.Shop( );
        try {
            shopDomain = ShopDomainDTOConverter.getConvertedDomainFromDTO( shopDomain, shopDTO );
        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( shopResponseContainer ).build( );
        }
        try {
            shopDAOResponse = shopDAOImpl.createShop( shopDomain, isError );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
            return response;
        }
        try {
            shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );
            response = Response.status( Response.Status.OK ).entity( shopResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {

            logger.error( "Exception occurred " + location, exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
            return response;

        } finally {
            logger.debug( " Response for createShop generated successfully from Service Level" );
        }
    }

    /**
     * This method is to Update an shop. It requires the new shop with the updated properties and shopId original
     *
     * @param shopDTO the shop
     * @param isError the is error
     *
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response updateShop( Shop shopDTO, boolean isError ) {

        logger.debug( "Initiating updateShop for incoming shop, shopDAO injected successfully" );
        com.mana.innovative.domain.client.Shop shopDomain = new com.mana.innovative.domain.client.Shop( );
        String location = this.getClass( ).getCanonicalName( ) + "#updateShop";
        ShopResponseContainer< ShopsPayload > shopResponseContainer;
        if ( shopDTO.getShopId( ) < ONE ) {
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError,
                    new IllegalArgumentValueException( ", ShopId is Required for Updating an Shop" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( shopResponseContainer ).build( );
        }
        shopDomain.setShopId( shopDTO.getShopId( ) );
        try {
            shopDomain = ShopDomainDTOConverter.getConvertedDomainFromDTO( shopDomain, shopDTO );
        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( shopResponseContainer ).build( );
        } catch ( Exception exception ) {
            logger.error( "Exception occurred in " + location, exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.client.Shop > shopDAOResponse;
        try {
            shopDAOResponse = shopDAOImpl.updateShop( shopDomain, isError );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        }
        shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );

        logger.debug( " Response for updateShop generated successfully from Service Level" );
        return Response.ok( ).entity( shopResponseContainer ).build( );
    }

    /**
     * This method is to Delete an shop via shopId only.
     *
     * @param shopId  the shop id
     * @param isError the is error
     *
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public Response deleteShopByShopId( Long shopId, boolean isError ) {

        logger.debug( "Initiating deleteShopByShopId for incoming shop, shopDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#deleteITem";
        ShopResponseContainer< ShopsPayload > shopResponseContainer;

        if ( shopId < ONE ) {
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError,
                    new IllegalArgumentValueException( ", ShopId is required for deleting an Shop" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( shopResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.client.Shop > shopDAOResponse;
        try {
            shopDAOResponse = shopDAOImpl.deleteShopByShopId( shopId, isError );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        }
        shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );

        logger.debug( " Response for deleteShopByShopId generated successfully from Service Level" );
        return Response.ok( ).entity( shopResponseContainer ).build( );
    }

    /**
     * Delete shops by shop ids.
     *
     * @param shopIds the shop ids
     * @param isError the is error
     *
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public Response deleteShopsByShopIds( List< Long > shopIds, boolean isError ) {

        logger.debug( "Initiating deleteShopsByShopIds for incoming shopIds, shopDAO injected successfully" );

        String location = this.getClass( ).getCanonicalName( ) + "#deleteShopsByShopIds";
        ShopResponseContainer< ShopsPayload > shopResponseContainer;
        if ( shopIds.isEmpty( ) ) {
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError
                    , new IllegalArgumentValueException( ", ShopIds are required for deleting Shops" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( shopResponseContainer ).build( );
        }

        DAOResponse< com.mana.innovative.domain.client.Shop > shopDAOResponse;
        try {
            shopDAOResponse = shopDAOImpl.deleteShopsByShopIds( shopIds, isError );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            shopResponseContainer = ShopResponseBuilder.buildError( location, isError, exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( shopResponseContainer ).build( );
        }
        shopResponseContainer = ShopResponseBuilder.build( shopDAOResponse, isError );

        logger.debug( " Response for deleteShopsByShopIds generated successfully from Service Level" );
        return Response.ok( ).entity( shopResponseContainer ).build( );
    }
}
