package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.UserRoleDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.domain.consumer.UserRole;
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
 * Created by Bloom/Rono on 5/15/2015 12:32 AM. This class is UserRoleDAOImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository( value = "userRoleDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class UserRoleDAOImpl extends BasicDAO implements UserRoleDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory
            .getLogger( UserRoleDAOImpl.class );

    /**
     * Gets userRoles.
     *
     * @param requestParams the request params
     *
     * @return the userRoles
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< UserRole > getUserRoles( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( )
                + "#getUserRoles()";

        logger.debug( "Starting " + location );
        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        List< UserRole > userRoleList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from UserRole " );
            userRoleList = query.list( );

            this.closeDBTransaction( );
            userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from userRoles table for userRoles ",
                    exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setCount( userRoleList.size( ) );
        userRoleDAOResponse.setResults( userRoleList );
        userRoleDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return userRoleDAOResponse;
    }

    /**
     * Gets userRole.
     *
     * @param userRoleId    the userRole id
     * @param requestParams the request params
     *
     * @return the userRole
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< UserRole > getUserRoleByUserRoleId( final int userRoleId,
                                                            final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( )
                + "#getUserRoleByUserRoleId()";

        logger.debug( "Starting " + location );
        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        List< UserRole > userRoleList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from UserRole where userRoleId=:userRoleId" );
            query.setParameter( "userRoleId", userRoleId );
            UserRole userRole = ( UserRole ) query.uniqueResult( );

            this.closeDBTransaction( );
            userRoleList.add( userRole );
            userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from userRoles table for userRoles ",
                    exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setCount( userRoleList.size( ) );
        userRoleDAOResponse.setResults( userRoleList );
        userRoleDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return userRoleDAOResponse;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< UserRole > getUserRoleByUserRoleName( final String userRoleName, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( )
                + "#getUserRoleByUserRoleName()";

        logger.debug( "Starting " + location );
        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        List< UserRole > userRoleList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;
        try {
            this.openDBTransaction( );

            Query query = session
                    .createQuery( "from UserRole where userRoleName=:userRoleName" );
            query.setParameter( "userRoleName", userRoleName );
//            UserRole userRole = ( UserRole ) query.uniqueResult( );
            userRoleList = query.list( );

            this.closeDBTransaction( );
//            userRoleList.add( userRole );
            userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from userRoles table for userRoles ",
                    exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setCount( userRoleList.size( ) );
        userRoleDAOResponse.setResults( userRoleList );
        userRoleDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return userRoleDAOResponse;
    }

    /**
     * Create userRole.
     *
     * @param userRole      the userRole
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< UserRole > createUserRole( final UserRole userRole,
                                                   RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( )
                + "#createUserRole()";
        logger.debug( "Starting " + location );

        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        List< UserRole > userRoleList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            this.openDBTransaction( );
            session.save( userRole );
            this.closeDBTransaction( );

            userRoleDAOResponse.setRequestSuccess( true );
            userRoleList.add( userRole );

        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error(
                    "Exception occurred while creating data for userRoles table",
                    exception );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }
        userRoleDAOResponse.setCreate( true );
        userRoleDAOResponse.setCount( userRoleList.size( ) );
        userRoleDAOResponse.setResults( userRoleList );
        userRoleDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return userRoleDAOResponse;
    }

    /**
     * Update userRole.
     *
     * @param userRole      the userRole
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< UserRole > updateUserRole( final UserRole userRole,
                                                   final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( )
                + "#updateUserRole()";
        logger.debug( "Starting " + location );

        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        List< UserRole > userRoleList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;
        userRoleDAOResponse.setUpdate( true );

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( userRole );
            this.closeDBTransaction( );
            userRoleList.add( userRole );

            userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error(
                    "Failed while getting data from userRoles table for userRoles ",
                    exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setCount( userRoleList.size( ) );
        userRoleDAOResponse.setResults( userRoleList );
        userRoleDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );

        return userRoleDAOResponse;
    }

    /**
     * Delete userRole.
     *
     * @param userRoleId    the userRole id
     * @param requestParams the request params
     *
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< UserRole > deleteUserRoleByUserRoleId( int userRoleId,
                                                               RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( )
                + "#deleteUserRoleByUserRoleId()";
        logger.debug( "Starting " + location );
        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;
        try {
            this.openDBTransaction( );

            Query query = session
                    .createQuery( "delete  from UserRole where userRoleId=:userRoleId" );
            query.setParameter( "userRoleId", userRoleId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );
            userRoleDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from userRoles table",
                    exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );
            userRoleDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setDelete( Boolean.TRUE );
        userRoleDAOResponse.setResults( null );
        userRoleDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userRoleDAOResponse;
    }

    /**
     * Delete all userRoles.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< UserRole > deleteAllUserRoles(
            final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( )
                + "#deleteAllUserRoles()";
        logger.debug( "Starting " + location );
        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from UserRole" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );
                userRoleDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from userRoles table",
                    exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );
            userRoleDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setDelete( Boolean.TRUE );
        userRoleDAOResponse.setResults( null );
        userRoleDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userRoleDAOResponse;
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
    public DAOResponse< UserRole > getUserRoleByUserId( final Long userId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getUserRoleByUserId()";
        logger.debug( "Starting " + location );
        DAOResponse< UserRole > userRoleDAOResponse = new DAOResponse<>( );
        List< UserRole > userRoleList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from User where userId=:userId" );
            query.setParameter( "userId", userId );
            User userDomain = ( User ) query.uniqueResult( );
            UserRole userRole = userDomain != null ? userDomain.getUserRole( ) : null;

            this.closeDBTransaction( );
            if ( userRole == null && userDomain != null ) {
                logger.warn( "No user role was found for user with id " + userId );
            }
            userRoleList.add( userRole );
            userRoleDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from userRoles table for userRoles ", exception );
            userRoleDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userRoleDAOResponse.setCount( userRoleList.size( ) );
        userRoleDAOResponse.setResults( userRoleList );
        userRoleDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return userRoleDAOResponse;
    }
}
