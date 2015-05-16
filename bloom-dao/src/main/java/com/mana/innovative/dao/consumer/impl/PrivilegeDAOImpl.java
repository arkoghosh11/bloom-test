package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.PrivilegeDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Privilege;
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
 * Created by Bloom/Rono on 5/15/2015 12:38 AM. This class is PrivilegeDAOImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository( value = "privilegeDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class PrivilegeDAOImpl extends BasicDAO implements PrivilegeDAO {

    private static final Logger logger = LoggerFactory.getLogger( PrivilegeDAOImpl.class );

    /**
     * Gets privileges.
     *
     * @param requestParams the request params
     *
     * @return the privileges
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Privilege > getPrivileges( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getPrivileges()";

        logger.debug( "Starting " + location );
        DAOResponse< Privilege > privilegeDAOResponse = new DAOResponse<>( );
        List< Privilege > privilegeList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Privilege " );
            privilegeList = query.list( );

            this.closeDBTransaction( );
            privilegeDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from privileges table for privileges ", exception );
            privilegeDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        privilegeDAOResponse.setCount( privilegeList.size( ) );
        privilegeDAOResponse.setResults( privilegeList );
        privilegeDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return privilegeDAOResponse;
    }

    /**
     * Gets privilege.
     *
     * @param privilegeId   the privilege id
     * @param requestParams the request params
     *
     * @return the privilege
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Privilege > getPrivilegeByPrivilegeId( final int privilegeId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getPrivilegeByPrivilegeId()";

        logger.debug( "Starting " + location );
        DAOResponse< Privilege > privilegeDAOResponse = new DAOResponse<>( );
        List< Privilege > privilegeList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Privilege where privilegeId=:privilegeId" );
            query.setParameter( "privilegeId", privilegeId );
            Privilege privilege = ( Privilege ) query.uniqueResult( );

            this.closeDBTransaction( );
            privilegeList.add( privilege );
            privilegeDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from privileges table for privileges ", exception );
            privilegeDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        privilegeDAOResponse.setCount( privilegeList.size( ) );
        privilegeDAOResponse.setResults( privilegeList );
        privilegeDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return privilegeDAOResponse;
    }

    /**
     * Create privilege.
     *
     * @param privilege     the privilege
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Privilege > createPrivilege( final Privilege privilege, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createPrivilege()";
        logger.debug( "Starting " + location );

        DAOResponse< Privilege > privilegeDAOResponse = new DAOResponse<>( );
        List< Privilege > privilegeList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            session.save( privilege );

            this.closeDBTransaction( );

            privilegeDAOResponse.setRequestSuccess( true );
            privilegeList.add( privilege );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for privileges table", exception );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        privilegeDAOResponse.setCreate( true );
        privilegeDAOResponse.setCount( privilegeList.size( ) );
        privilegeDAOResponse.setResults( privilegeList );
        privilegeDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return privilegeDAOResponse;
    }

    /**
     * Update privilege.
     *
     * @param privilege     the privilege
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Privilege > updatePrivilege( final Privilege privilege, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updatePrivilege()";
        logger.debug( "Starting " + location );

        DAOResponse< Privilege > privilegeDAOResponse = new DAOResponse<>( );
        List< Privilege > privilegeList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        privilegeDAOResponse.setUpdate( true );

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( privilege );
            this.closeDBTransaction( );
            privilegeList.add( privilege );

            privilegeDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from privileges table for privileges ", exception );
            privilegeDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        privilegeDAOResponse.setCount( privilegeList.size( ) );
        privilegeDAOResponse.setResults( privilegeList );
        privilegeDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );

        return privilegeDAOResponse;
    }

    /**
     * Delete privilege.
     *
     * @param privilegeId   the privilege id
     * @param requestParams the request params
     *
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Privilege > deletePrivilegeByPrivilegeId( int privilegeId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deletePrivilegeByPrivilegeId()";
        logger.debug( "Starting " + location );
        DAOResponse< Privilege > privilegeDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Privilege where privilegeId=:privilegeId" );
            query.setParameter( "privilegeId", privilegeId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            privilegeDAOResponse.setRequestSuccess( Boolean.TRUE );
            privilegeDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from privileges table", exception );
            privilegeDAOResponse.setRequestSuccess( Boolean.FALSE );
            privilegeDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        privilegeDAOResponse.setDelete( Boolean.TRUE );
        privilegeDAOResponse.setResults( null );
        privilegeDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return privilegeDAOResponse;
    }

    /**
     * Delete all privileges.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Privilege > deleteAllPrivileges( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllPrivileges()";
        logger.debug( "Starting " + location );
        DAOResponse< Privilege > privilegeDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from Privilege" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                privilegeDAOResponse.setRequestSuccess( Boolean.TRUE );
                privilegeDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from privileges table", exception );
            privilegeDAOResponse.setRequestSuccess( Boolean.FALSE );
            privilegeDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        privilegeDAOResponse.setDelete( Boolean.TRUE );
        privilegeDAOResponse.setResults( null );
        privilegeDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return privilegeDAOResponse;
    }
}
