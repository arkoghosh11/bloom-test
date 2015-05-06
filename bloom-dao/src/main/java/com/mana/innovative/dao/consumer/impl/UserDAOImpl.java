package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.UserDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/10/2015. This class is UserDAOImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository( value = "userDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class UserDAOImpl extends BasicDAO implements UserDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( UserDAOImpl.class );

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory( ) {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory( final SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method is to create a user
     *
     * @param requestParams the request params
     * @return boolean Return a boolean value to indicate if user creation passed or failed
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > getUsers( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getUsers()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        List< User > userList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from User " );
            userList = query.list( );

            this.closeDBTransaction( );
            userDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from users table", exception );
            userDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userDAOResponse.setCount( userList.size( ) );
        userDAOResponse.setResults( userList );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;

    }

    /**
     * Gets user by user id.
     *
     * @param userId the user id
     * @param requestParams the request params
     * @return the user by user id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > getUserByUserId( final Long userId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getUsers()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        List< User > userList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from User where userId=:userId" );
            query.setParameter( "userId", userId );
            userList = query.list( );

            this.closeDBTransaction( );
            userDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from users table", exception );
            userDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userDAOResponse.setCount( userList.size( ) );
        userDAOResponse.setResults( userList );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;

    }


    /**
     * This method is to create a user
     *
     * @param user the user
     * @param requestParams the request params
     * @return boolean Return a boolean value to indicate if user creation passed or failed
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > createUser( User user, RequestParams requestParams ) {


        String location = this.getClass( ).getCanonicalName( ) + "#createUser()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        List< User > userList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            session.save( user );

            this.closeDBTransaction( );
            userDAOResponse.setRequestSuccess( Boolean.TRUE );
            userList.add( user );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while creating data for users table", exception );
            userDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userDAOResponse.setCreate( Boolean.TRUE );
        userDAOResponse.setCount( userList.size( ) );
        userDAOResponse.setResults( userList );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;
    }

    /**
     * Update user.
     *
     * @param user the user
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > updateUser( final User user, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updateUser()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        List< User > userList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            // todo update code here later
            session.update( user );

            this.closeDBTransaction( );
            userDAOResponse.setRequestSuccess( Boolean.TRUE );
            userList.add( user );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while updating data for users table", exception );
            userDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userDAOResponse.setUpdate( Boolean.TRUE );
        userDAOResponse.setCount( userList.size( ) );
        userDAOResponse.setResults( userList );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;
    }

    /**
     * Delete user by user id.
     *
     * @param userId the user id
     * @param requestParams the request params
     * @param entityName Optional Param only to be set by subclass to the query works without issues
     * @return the dAO response
     */
    @Override
    public DAOResponse< User > deleteUserByUserId( Long userId, RequestParams requestParams, String entityName ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteUserByUserId()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        entityName = entityName != null ? entityName : "User";
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from " + entityName + " where userId=:userId" );
            query.setParameter( "userId", userId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            userDAOResponse.setRequestSuccess( Boolean.TRUE );
            userDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from users table", exception );
            userDAOResponse.setRequestSuccess( Boolean.FALSE );
            userDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userDAOResponse.setDelete( Boolean.TRUE );
        userDAOResponse.setResults( null );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;
    }

    /**
     * Delete users.
     *
     * @param userIds the user ids
     * @param requestParams the request params
     * @param tableName the table name
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > deleteUsers( final List< Long > userIds, final RequestParams requestParams, String
            tableName ) {
        String location = this.getClass( ).getCanonicalName( ) + "#deleteUsers()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        tableName = tableName != null ? tableName : "User";

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from " + tableName + " where userId in (:userIds)" );
            query.setParameterList( "userIds", userIds );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            userDAOResponse.setRequestSuccess( Boolean.TRUE );
            userDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from users table", exception );
            userDAOResponse.setRequestSuccess( Boolean.FALSE );
            userDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        userDAOResponse.setDelete( Boolean.TRUE );
        userDAOResponse.setResults( null );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;
    }

}
