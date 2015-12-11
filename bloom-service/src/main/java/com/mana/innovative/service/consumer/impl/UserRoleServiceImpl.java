package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.consumer.UserRoleDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.UserRole;
import com.mana.innovative.dto.consumer.payload.UserRolesPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.consumer.UserRoleService;
import com.mana.innovative.service.consumer.builder.UserRoleResponseBuilder;
import com.mana.innovative.service.consumer.container.UserRoleResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Bloom/Rono on 7/8/2015 2:17 AM.
 * This class is UserRoleServiceImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UserRoleServiceImpl.class );

    /**
     * The User role dAO.
     */
    @Resource
    private UserRoleDAO userRoleDAO;

    /**
     * Gets user role by user name.
     *
     * @param userName      the user name
     * @param requestParams the request params
     *
     * @return the user role by user name
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public UserRoleResponseContainer< UserRolesPayload > getUserRoleByUserName( final String userName, RequestParams
            requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getUserRoleByUserName()";
        logger.debug( "Starting " + location );
        UserRoleResponseContainer< UserRolesPayload > userRoleResponseContainer;
        DAOResponse< com.mana.innovative.domain.consumer.UserRole > userRoleDAOResponse = userRoleDAO
                .getUserRoleByUserRoleName( userName, requestParams );

        userRoleResponseContainer = UserRoleResponseBuilder.build( userRoleDAOResponse, requestParams.isError( ) );

        logger.debug( "Finishing " + location );
        return userRoleResponseContainer;
    }

    /**
     * Gets user roles.
     *
     * @param requestParams the request params
     *
     * @return the user roles
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public UserRoleResponseContainer< UserRolesPayload > getUserRoles( final RequestParams requestParams ) {


        DAOResponse< com.mana.innovative.domain.consumer.UserRole > userRoleDAOResponse;
        UserRoleResponseContainer< UserRolesPayload > userRoleResponseContainer;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getUserRoles()";
        logger.debug( "Starting " + location );

        try {
            userRoleDAOResponse = userRoleDAO.getUserRoles( requestParams );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            userRoleResponseContainer = UserRoleResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return userRoleResponseContainer;
        }
        try {
            userRoleResponseContainer = UserRoleResponseBuilder.build( userRoleDAOResponse, requestParams.isError( ) );
            return userRoleResponseContainer;

        } catch ( Exception exception ) {
            logger.error( "Exception occurred while building UserRoleResponseContainer", exception );
            userRoleResponseContainer = UserRoleResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return userRoleResponseContainer;
        } finally {
            logger.debug( " Response for getUserRoles sent Successfully " );
        }

    }


    /**
     * Gets user role by user id.
     * This method is there for keeping with all the other service classes but no user/client/consumer should ever
     * need to use this method.
     * <p/>
     * Even admins should not need to use this one, use {@link this#getUserRoleByUserName(String, RequestParams)}
     *
     * @param userId        the user id
     * @param requestParams the request params
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public UserRoleResponseContainer< UserRolesPayload > getUserRoleByUserId( final Long userId, RequestParams requestParams ) {

        logger.debug( "Initiating getUserRoleByUserId for incoming user, userRoleDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#getUserRoleByUserId()";
        logger.debug( "Starting " + location );
        UserRoleResponseContainer< UserRolesPayload > userRoleResponseContainer;

        if ( userId < 1 ) {
            userRoleResponseContainer = UserRoleResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( " UserId is required for find a Users UserRole" ) );
            return userRoleResponseContainer;
        }
        DAOResponse< com.mana.innovative.domain.consumer.UserRole > userDAOResponse;
        try {
            userDAOResponse = userRoleDAO.getUserRoleByUserId( userId, requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            userRoleResponseContainer = UserRoleResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return userRoleResponseContainer;
        }
        userRoleResponseContainer = UserRoleResponseBuilder.build( userDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for getUserRoleByUserId generated successfully from Service Level" );
        return userRoleResponseContainer;
    }

    /**
     * Add user role by user id.
     *
     * @param userRole      the user role
     * @param requestParams the request params
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void addUserRoleByUserId( UserRole userRole, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#addUserRoleByUserId()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );

    }

    /**
     * Update user role by user id.
     *
     * @param userRole      the user role
     * @param requestParams the request params
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void updateUserRoleByUserId( UserRole userRole, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updateUserRoleByUserId()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );

    }

}
