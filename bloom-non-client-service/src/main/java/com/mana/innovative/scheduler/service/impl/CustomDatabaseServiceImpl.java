package com.mana.innovative.scheduler.service.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.client.ItemDAO;
import com.mana.innovative.dao.client.ShopDAO;
import com.mana.innovative.dao.common.CustomEventDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.domain.common.email.CustomEvent;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.scheduler.service.CustomDatabaseService;
import com.mana.innovative.utilities.date.DateCommons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseServiceImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class CustomDatabaseServiceImpl < T, E > implements CustomDatabaseService< T, E > {

    private static final Logger logger = LoggerFactory.getLogger( CustomDatabaseServiceImpl.class );

    @Resource
    private ItemDAO itemDAO;
    @Resource
    private ShopDAO shopDAO;

    @Resource
    private CustomEventDAO customEventDAO;

    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public T readData( final E e ) {

        String location = this.getClass( ).getCanonicalName( ) + "#readData()";
        logger.debug( "Starting " + location );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( true );
        DAOResponse< CustomEvent > customEventDAOResponse = null;
        try {
            if ( e instanceof Date ) {
                Date eventDate = ( Date ) e;
                Date eventStartTime = DateCommons.getStartDateTime( eventDate );
                Date eventEndTime = DateCommons.getEndDateTime( eventDate );
                customEventDAOResponse = customEventDAO.getEventsByDateRange( eventStartTime, eventEndTime,
                        requestParams );
            } else if ( e instanceof Long ) {
                Long eventId = ( Long ) e;
                customEventDAOResponse = customEventDAO.getEventById( eventId, requestParams );
            } else if ( e instanceof String ) {
                String eventName = ( String ) e;
                customEventDAOResponse = customEventDAO.getEventsByEventName( eventName, requestParams );
            } else if ( e == null ) {
                customEventDAOResponse = customEventDAO.getAllEvents( requestParams );
                logger.warn( " All events have been requested from database causing a stress in the system" );
            } else {
                logger.warn( "Invalid Object Property sent", e );
            }
        } catch ( Exception exception ) {
            logger.error( "Failed while trying to save into Database", exception );
        }
        logger.debug( "Finishing " + location );
        return ( T ) customEventDAOResponse;
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public boolean createData( final T t ) {
        String location = this.getClass( ).getCanonicalName( ) + "#()";
        logger.debug( "Starting " + location );
        try {
            if ( t instanceof Item ) {
                Item item = ( Item ) t;
                itemDAO.createItem( item, Boolean.TRUE );
            }
            if ( t instanceof Shop ) {
                Shop shop = ( Shop ) t;
                shopDAO.createShop( shop, Boolean.TRUE );
            }
        } catch ( Exception exception ) {
            logger.error( "Failed while trying to save into Database", exception );
        }
        logger.debug( "Finishing " + location );
        return true;
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public boolean updateData( final T e, boolean isScheduler ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updateData()";
        logger.debug( "Starting " + location );

        DAOResponse< CustomEvent > customEventDAOResponse = new DAOResponse<>( );
        try {
            if ( e instanceof Date ) {
                Date eventDate = ( Date ) e;

                RequestParams requestParams = new RequestParams( );
                requestParams.setIsError( Boolean.TRUE );

                if ( isScheduler ) {
                    customEventDAOResponse = customEventDAO.disableEventSchedulerForDate( eventDate, requestParams );
                } else {
                    customEventDAOResponse = customEventDAO.enableEventSchedulerForDate( eventDate, requestParams );
                }
            } else {
                logger.warn( "Invalid Object Property sent", e );
            }
        } catch ( Exception exception ) {
            logger.error( "Failed while trying to save into Database", exception );
        }

        logger.debug( "Finishing " + location );
        return customEventDAOResponse.getCount( ) == DAOConstants.ONE;
    }
}
