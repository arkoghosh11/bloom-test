package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.consumer.UserDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository( value = "userDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class UserDAOImpl extends BasicDAO implements UserDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserDAOImpl.class );

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;


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
    public DAOResponse< User > getUserByUserId( final long userId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getUsers()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        List< User > userList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from User where userId=:userId" );
            query.setParameter( "userId", userId );
            User user = ( User ) query.uniqueResult( );

            this.closeDBTransaction( );
            userList.add( user );
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
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > deleteUserByUserId( long userId, RequestParams requestParams, String entityName ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteUserByUserId()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        entityName = entityName != null ? entityName : "User";
        userDAOResponse.setDelete( Boolean.TRUE );

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
     * Delete users by user ids.
     *
     * @param userIds the user ids
     * @param requestParams the request params
     * @param entityName the entity name
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > deleteUsersByUserIds( final List< Long > userIds, final RequestParams requestParams, String entityName ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteUsersByUserIds()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        entityName = entityName != null ? entityName : "User";
        userDAOResponse.setDelete( Boolean.TRUE );

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from " + entityName + " where userId in(:userIds)" );
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

    /**
     * Delete users.
     *
     * @param requestParams the request params
     * @param tableName the table name
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > deleteAllUsers( final RequestParams requestParams, String
            tableName ) {
        String location = this.getClass( ).getCanonicalName( ) + "#deleteAllUsers()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        tableName = tableName != null ? tableName : "User";
        userDAOResponse.setCount( DAOConstants.ZERO );

        if ( requestParams.isDeleteAll( ) ) {
            try {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from " + tableName );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                userDAOResponse.setRequestSuccess( Boolean.TRUE );
                userDAOResponse.setCount( count );

            } catch ( HibernateException exception ) {

                this.handleExceptions( exception );
                logger.error( "Failed while deleting data from users table", exception );
                userDAOResponse.setRequestSuccess( Boolean.FALSE );

                if ( requestParams.isError( ) ) {

                    errorContainer = fillErrorContainer( location, exception );
                }
            }
        }

        userDAOResponse.setDelete( Boolean.TRUE );
        userDAOResponse.setResults( null );
        userDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return userDAOResponse;
    }


    /**
     * Find user by user name.
     *
     * @param userName      the user name
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< User > findUserByUserName( final String userName, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getUsers()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = new DAOResponse<>( );
        List< User > userList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from User where userName=:userName" );
            query.setParameter( "userName", userName );
            User user = ( User ) query.uniqueResult( );

            this.closeDBTransaction( );
            userList.add( user );
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

}
