package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.CardDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Card;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 5:37 PM. This class is CardDAOImpl
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository( value = "cardDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class CardDAOImpl extends BasicDAO implements CardDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CardDAOImpl.class );

    /**
     * Create card.
     *
     * @param card the card
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Card > createCard( final Card card, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCard()";
        logger.debug( "Starting " + location );

        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        List< Card > cardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );
            session.save( card );
            this.closeDBTransaction( );

            cardDAOResponse.setRequestSuccess( true );
            cardList.add( card );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for cards table", exception );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }
        cardDAOResponse.setCreate( true );
        cardDAOResponse.setCount( cardList.size( ) );
        cardDAOResponse.setResults( cardList );
        cardDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );

        return cardDAOResponse;
    }

    /**
     * Gets cards.
     *
     * @param requestParams the request params
     * @return the cards
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Card > getCards( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCards()";

        logger.debug( "Starting " + location );
        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        List< Card > cardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Card " );
            cardList = query.list( );

            this.closeDBTransaction( );
            cardDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from cards table for cards ", exception );
            cardDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        cardDAOResponse.setCount( cardList.size( ) );
        cardDAOResponse.setResults( cardList );
        cardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return cardDAOResponse;
    }

    /**
     * Gets card.
     *
     * @param cardId the card id
     * @param requestParams the request params
     * @return the card
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Card > getCardByCardId( final long cardId, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getCardByCardId()";

        logger.debug( "Starting " + location );
        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        List< Card > cardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Card where cardId=:cardId" );
            query.setParameter( "cardId", cardId );
            Card card = ( Card ) query.uniqueResult( );

            this.closeDBTransaction( );
            cardList.add( card );
            cardDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from cards table for cards ", exception );
            cardDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        cardDAOResponse.setErrorContainer( errorContainer );
        cardDAOResponse.setCount( cardList.size( ) );
        cardDAOResponse.setResults( cardList );

        logger.debug( "Finishing " + location );
        return cardDAOResponse;
    }

    /**
     * Update card.
     *
     * @param card the card
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Card > updateCard( final Card card, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updateCard()";
        logger.debug( "Starting " + location );

        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        List< Card > cardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        cardDAOResponse.setUpdate( true );

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( card );
            this.closeDBTransaction( );
            cardList.add( card );

            cardDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from cards table for cards ", exception );
            cardDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }
        cardDAOResponse.setErrorContainer( errorContainer );
        cardDAOResponse.setCount( cardList.size( ) );
        cardDAOResponse.setResults( cardList );
        logger.debug( "Finishing " + location );
        return cardDAOResponse;
    }

    /**
     * Delete card.
     *
     * @param cardId the card id
     * @param requestParams the request params
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Card > deleteCardByCardId( long cardId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteCardByCardId()";
        logger.debug( "Starting " + location );
        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Card where cardId=:cardId" );
            query.setParameter( "cardId", cardId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            cardDAOResponse.setRequestSuccess( Boolean.TRUE );
            cardDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from cards table", exception );
            cardDAOResponse.setRequestSuccess( Boolean.FALSE );
            cardDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        cardDAOResponse.setErrorContainer( errorContainer );
        cardDAOResponse.setDelete( Boolean.TRUE );
        cardDAOResponse.setResults( null );

        logger.debug( "Finishing " + location );
        return cardDAOResponse;
    }

    /**
     * Delete cards by cards id.
     *
     * @param cardIds the card ids
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Card > deleteCardsByCardIds( final List< Long > cardIds, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteCardsByCardId()";
        logger.debug( "Starting " + location );
        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Card where cardId in(:cardIds)" );
            query.setParameterList( "cardIds", cardIds );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            cardDAOResponse.setRequestSuccess( Boolean.TRUE );
            cardDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from cards table", exception );
            cardDAOResponse.setRequestSuccess( Boolean.FALSE );
            cardDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        cardDAOResponse.setErrorContainer( errorContainer );
        cardDAOResponse.setDelete( Boolean.TRUE );
        cardDAOResponse.setResults( null );

        logger.debug( "Finishing " + location );
        return cardDAOResponse;
    }

    /**
     * Delete all cards.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Card > deleteAllCards( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllCards()";
        logger.debug( "Starting " + location );
        DAOResponse< Card > cardDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from Card" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                cardDAOResponse.setRequestSuccess( Boolean.TRUE );
                cardDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from cards table", exception );
            cardDAOResponse.setRequestSuccess( Boolean.FALSE );
            cardDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        cardDAOResponse.setErrorContainer( errorContainer );
        cardDAOResponse.setDelete( Boolean.TRUE );
        cardDAOResponse.setResults( null );

        logger.debug( "Finishing " + location );
        return cardDAOResponse;
    }
}
