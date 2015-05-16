package com.mana.innovative.dao.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.common.PhoneDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Phone;
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
 * Created by Bloom/Rono on 5/2/2015 5:38 PM. This class is PhoneDAOImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class PhoneDAOImpl extends BasicDAO implements PhoneDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory
            .getLogger( PhoneDAOImpl.class );

    /**
     * Create phone.
     *
     * @param phone         the phone
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Phone > createPhone( final Phone phone,
                                             RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createPhone()";
        logger.debug( "Starting " + location );

        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        List< Phone > phoneList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            this.openDBTransaction( );
            session.save( phone );
            this.closeDBTransaction( );

            phoneDAOResponse.setRequestSuccess( true );
            phoneList.add( phone );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error(
                    "Exception occurred while creating data for phones table",
                    exception );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }
        phoneDAOResponse.setCreate( true );
        phoneDAOResponse.setCount( phoneList.size( ) );
        phoneDAOResponse.setResults( phoneList );
        phoneDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return phoneDAOResponse;
    }

    /**
     * Gets phones.
     *
     * @param requestParams the request params
     *
     * @return the phones
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Phone > getPhones( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getPhones()";

        logger.debug( "Starting " + location );
        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        List< Phone > phoneList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Phone " );
            phoneList = query.list( );

            this.closeDBTransaction( );
            phoneDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from phones table for phones ",
                    exception );
            phoneDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        phoneDAOResponse.setCount( phoneList.size( ) );
        phoneDAOResponse.setResults( phoneList );
        phoneDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return phoneDAOResponse;
    }

    /**
     * Gets phone.
     *
     * @param phoneId       the phone id
     * @param requestParams the request params
     *
     * @return the phone
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Phone > getPhone( final long phoneId,
                                          final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getPhone()";

        logger.debug( "Starting " + location );
        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        List< Phone > phoneList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            this.openDBTransaction( );

            Query query = session
                    .createQuery( "from Phone where phoneId=:phoneId" );
            query.setParameter( "phoneId", phoneId );
            Phone phone = ( Phone ) query.uniqueResult( );

            this.closeDBTransaction( );
            phoneList.add( phone );
            phoneDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from phones table for phones ",
                    exception );
            phoneDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        phoneDAOResponse.setCount( phoneList.size( ) );
        phoneDAOResponse.setResults( phoneList );
        phoneDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return phoneDAOResponse;
    }

    /**
     * Update phone.
     *
     * @param phone         the phone
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Phone > updatePhone( final Phone phone,
                                             final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updatePhone()";
        logger.debug( "Starting " + location );

        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        List< Phone > phoneList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( phone );
            this.closeDBTransaction( );
            phoneList.add( phone );

            phoneDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from phones table for phones ",
                    exception );
            phoneDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        phoneDAOResponse.setUpdate( true );
        phoneDAOResponse.setCount( phoneList.size( ) );
        phoneDAOResponse.setResults( phoneList );
        phoneDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return phoneDAOResponse;
    }

    /**
     * Delete phone.
     *
     * @param phoneId       the phone id
     * @param requestParams the request params
     *
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Phone > deletePhoneByPhoneId( Long phoneId,
                                                      RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deletePhoneByPhoneId()";
        logger.debug( "Starting " + location );
        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session
                    .createQuery( "delete  from Phone where phoneId=:phoneId" );
            query.setParameter( "phoneId", phoneId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            phoneDAOResponse.setRequestSuccess( Boolean.TRUE );
            phoneDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from phones table",
                    exception );
            phoneDAOResponse.setRequestSuccess( Boolean.FALSE );
            phoneDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        phoneDAOResponse.setDelete( Boolean.TRUE );
        phoneDAOResponse.setResults( null );
        phoneDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return phoneDAOResponse;
    }

    /**
     * Delete phones.
     *
     * @param phoneIds      the phone ids
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Phone > deletePhonesByPhoneIds( final List< Long > phoneIds,
                                                        final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#deletePhonesByPhoneIds()";
        logger.debug( "Starting " + location );
        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Phone where phoneId in(:phoneIds)" );
            query.setParameterList( "phoneIds", phoneIds );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            phoneDAOResponse.setRequestSuccess( Boolean.TRUE );
            phoneDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from phones table",
                    exception );
            phoneDAOResponse.setRequestSuccess( Boolean.FALSE );
            phoneDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        phoneDAOResponse.setDelete( Boolean.TRUE );
        phoneDAOResponse.setResults( null );
        phoneDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return phoneDAOResponse;
    }

    /**
     * Delete all phones.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Phone > deleteAllPhones( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( )
                + "#deleteAllPhones()";
        logger.debug( "Starting " + location );
        DAOResponse< Phone > phoneDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from Phone" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                phoneDAOResponse.setRequestSuccess( Boolean.TRUE );
                phoneDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from phones table",
                    exception );
            phoneDAOResponse.setRequestSuccess( Boolean.FALSE );
            phoneDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        phoneDAOResponse.setDelete( Boolean.TRUE );
        phoneDAOResponse.setResults( null );
        phoneDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return phoneDAOResponse;
    }
}
