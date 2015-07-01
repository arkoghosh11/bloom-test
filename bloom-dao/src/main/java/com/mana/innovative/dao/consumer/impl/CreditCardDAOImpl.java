package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.CreditCardDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.CreditCard;
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
 * Created by Bloom/Rono on 5/2/2015 5:37 PM. This class is CreditCardDAOImpl
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository( value = "creditCardDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class CreditCardDAOImpl extends BasicDAO implements CreditCardDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CreditCardDAOImpl.class );

    /**
     * Create creditCard.
     *
     * @param creditCard the creditCard
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CreditCard > createCreditCard( final CreditCard creditCard, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCreditCard()";
        logger.debug( "Starting " + location );

        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        List< CreditCard > creditCardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );
            session.save( creditCard );
            this.closeDBTransaction( );

            creditCardDAOResponse.setRequestSuccess( true );
            creditCardList.add( creditCard );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for creditCards table", exception );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        creditCardDAOResponse.setCreate( true );
        creditCardDAOResponse.setCount( creditCardList.size( ) );
        creditCardDAOResponse.setResults( creditCardList );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return creditCardDAOResponse;
    }

    /**
     * Gets creditCards.
     *
     * @param requestParams the request params
     * @return the creditCards
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< CreditCard > getCreditCards( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCreditCards()";

        logger.debug( "Starting " + location );
        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        List< CreditCard > creditCardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from CreditCard " );
            creditCardList = query.list( );

            this.closeDBTransaction( );
            creditCardDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from creditCards table for creditCards ", exception );
            creditCardDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        creditCardDAOResponse.setCount( creditCardList.size( ) );
        creditCardDAOResponse.setResults( creditCardList );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return creditCardDAOResponse;
    }

    /**
     * Gets credit card.
     *
     * @param cardId the card id
     * @param requestParams the request params
     * @return the credit card
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< CreditCard > getCreditCardByCardId( final long cardId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCreditCardByCardId()";

        logger.debug( "Starting " + location );
        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        List< CreditCard > creditCardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session
                    .createQuery( "from CreditCard where cardId=:cardId" );
            query.setParameter( "cardId", cardId );
            CreditCard creditCard = ( CreditCard ) query.uniqueResult( );

            this.closeDBTransaction( );
            creditCardList.add( creditCard );
            creditCardDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from creditCards table for creditCards ", exception );
            creditCardDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        creditCardDAOResponse.setCount( creditCardList.size( ) );
        creditCardDAOResponse.setResults( creditCardList );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return creditCardDAOResponse;
    }

    /**
     * Update credit card.
     *
     * @param creditCard the credit card
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< CreditCard > updateCreditCard( final CreditCard creditCard, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updateCreditCard()";
        logger.debug( "Starting " + location );

        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        List< CreditCard > creditCardList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        creditCardDAOResponse.setUpdate( true );

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( creditCard );
            this.closeDBTransaction( );
            creditCardList.add( creditCard );

            creditCardDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from creditCards table for creditCards ",
                    exception );
            creditCardDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        creditCardDAOResponse.setCount( creditCardList.size( ) );
        creditCardDAOResponse.setResults( creditCardList );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return creditCardDAOResponse;
    }

    /**
     * Delete creditCard.
     *
     * @param cardId the creditCard id
     * @param requestParams the request params
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CreditCard > deleteCreditCardByCardId( long cardId,
                                                               RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteCreditCardByCreditCardId()";
        logger.debug( "Starting " + location );
        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from CreditCard where cardId=:cardId and customerCard.userId=:userId" );
            query.setParameter( "cardId", cardId );
            query.setParameter( "userId", requestParams.getId( ) );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            creditCardDAOResponse.setRequestSuccess( Boolean.TRUE );
            creditCardDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from creditCards table",
                    exception );
            creditCardDAOResponse.setRequestSuccess( Boolean.FALSE );
            creditCardDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        creditCardDAOResponse.setDelete( Boolean.TRUE );
        creditCardDAOResponse.setResults( null );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return creditCardDAOResponse;
    }

    /**
     * Delete credit cards by card ids.
     *
     * @param cardIds the card ids
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< CreditCard > deleteCreditCardsByCardIds( final List< Long > cardIds, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteCreditCardsByCardIds()";
        logger.debug( "Starting " + location );
        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete from CreditCard where cardId in(:cardIds) " +
                    "and customerCard.userId=:userId" );
            query.setParameterList( "cardIds", cardIds );
            query.setParameter( "userId", requestParams.getId( ) );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            creditCardDAOResponse.setRequestSuccess( Boolean.TRUE );
            creditCardDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from creditCards table",
                    exception );
            creditCardDAOResponse.setRequestSuccess( Boolean.FALSE );
            creditCardDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        creditCardDAOResponse.setDelete( Boolean.TRUE );
        creditCardDAOResponse.setResults( null );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return creditCardDAOResponse;
    }

    /**
     * Delete all creditCards.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< CreditCard > deleteAllCreditCards( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllCreditCards()";

        logger.debug( "Starting " + location );
        DAOResponse< CreditCard > creditCardDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from CreditCard" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                creditCardDAOResponse.setRequestSuccess( Boolean.TRUE );
                creditCardDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from creditCards table", exception );
            creditCardDAOResponse.setRequestSuccess( Boolean.FALSE );
            creditCardDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        creditCardDAOResponse.setDelete( Boolean.TRUE );
        creditCardDAOResponse.setResults( null );
        creditCardDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return creditCardDAOResponse;
    }
}
