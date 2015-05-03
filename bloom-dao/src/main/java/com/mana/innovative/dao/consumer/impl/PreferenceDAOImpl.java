package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.PreferenceDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Preference;
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
 * Created by Bloom/Rono on 5/2/2015 5:36 PM. This class is PreferenceDAOImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class PreferenceDAOImpl extends BasicDAO implements PreferenceDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( PreferenceDAOImpl.class );

    /**
     * Create preference.
     *
     * @param preference    the preference
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Preference > createPreference( final Preference preference, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createPreference()";
        logger.debug( "Starting " + location );

        DAOResponse< Preference > preferenceDAOResponse = new DAOResponse<>( );
        List< Preference > preferenceList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );
            session.save( preference );
            this.closeDBTransaction( );

            preferenceDAOResponse.setRequestSuccess( true );
            preferenceList.add( preference );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for preferences table", exception );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }
        preferenceDAOResponse.setCreate( true );
        preferenceDAOResponse.setCount( preferenceList.size( ) );
        preferenceDAOResponse.setResults( preferenceList );
        preferenceDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return preferenceDAOResponse;
    }

    /**
     * Gets preferences.
     *
     * @param requestParams the request params
     *
     * @return the preferences
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Preference > getPreferences( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getPreferences()";

        logger.debug( "Starting " + location );
        DAOResponse< Preference > preferenceDAOResponse = new DAOResponse<>( );
        List< Preference > preferenceList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Preference " );
            preferenceList = query.list( );

            this.closeDBTransaction( );
            preferenceDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from preferences table for preferences ", exception );
            preferenceDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        preferenceDAOResponse.setCount( preferenceList.size( ) );
        preferenceDAOResponse.setResults( preferenceList );
        preferenceDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return preferenceDAOResponse;
    }

    /**
     * Gets preference.
     *
     * @param preferenceId  the preference id
     * @param requestParams the request params
     *
     * @return the preference
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Preference > getPreference( final long preferenceId, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getPreference()";

        logger.debug( "Starting " + location );
        DAOResponse< Preference > preferenceDAOResponse = new DAOResponse<>( );
        List< Preference > preferenceList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Preference where preferenceId=:preferenceId" );
            query.setParameter( "preferenceId", preferenceId );
            Preference preference = ( Preference ) query.uniqueResult( );

            this.closeDBTransaction( );
            preferenceList.add( preference );
            preferenceDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from preferences table for preferences ", exception );
            preferenceDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        preferenceDAOResponse.setCount( preferenceList.size( ) );
        preferenceDAOResponse.setResults( preferenceList );
        preferenceDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return preferenceDAOResponse;
    }

    /**
     * Update preference.
     *
     * @param preference    the preference
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Preference > updatePreference( final Preference preference, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updatePreference()";
        logger.debug( "Starting " + location );

        DAOResponse< Preference > preferenceDAOResponse = new DAOResponse<>( );
        List< Preference > preferenceList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        preferenceDAOResponse.setUpdate( true );

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( preference );
            this.closeDBTransaction( );
            preferenceList.add( preference );

            preferenceDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from preferences table for preferences ", exception );
            preferenceDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        preferenceDAOResponse.setCount( preferenceList.size( ) );
        preferenceDAOResponse.setResults( preferenceList );
        preferenceDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );

        return preferenceDAOResponse;
    }

    /**
     * Delete preference.
     *
     * @param preferenceId  the preference id
     * @param requestParams the request params
     *
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Preference > deletePreferenceByPreferenceId( Long preferenceId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deletePreferenceByPreferenceId()";
        logger.debug( "Starting " + location );
        DAOResponse< Preference > preferenceDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Preference where preferenceId=:preferenceId" );
            query.setParameter( "preferenceId", preferenceId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            preferenceDAOResponse.setRequestSuccess( Boolean.TRUE );
            preferenceDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from preferences table", exception );
            preferenceDAOResponse.setRequestSuccess( Boolean.FALSE );
            preferenceDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        preferenceDAOResponse.setDelete( Boolean.TRUE );
        preferenceDAOResponse.setResults( null );
        preferenceDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return preferenceDAOResponse;
    }

    /**
     * Delete all preferences.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Preference > deleteAllPreferences( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllPreferences()";
        logger.debug( "Starting " + location );
        DAOResponse< Preference > preferenceDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from Preference" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                preferenceDAOResponse.setRequestSuccess( Boolean.TRUE );
                preferenceDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from preferences table", exception );
            preferenceDAOResponse.setRequestSuccess( Boolean.FALSE );
            preferenceDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        preferenceDAOResponse.setDelete( Boolean.TRUE );
        preferenceDAOResponse.setResults( null );
        preferenceDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return preferenceDAOResponse;
    }
}
