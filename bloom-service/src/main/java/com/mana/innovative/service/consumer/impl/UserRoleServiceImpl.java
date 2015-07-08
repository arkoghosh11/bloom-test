package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.dao.consumer.UserRoleDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.payload.UserRolesPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UserRoleService;
import com.mana.innovative.service.consumer.builder.UserRoleResponseBuilder;
import com.mana.innovative.service.consumer.container.UserRoleResponseContainer;
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
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger( UserRoleServiceImpl.class );

    @Resource
    private UserRoleDAO userRoleDAO;

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

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void getUserRoleByUserId( final String userId, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getUserRoleByUserId()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );

    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void addUserRoleByUserId( final String userId, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#addUserRoleByUserId()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );

    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void updateUserRoleByUserId( final String userId, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updateUserRoleByUserId()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );

    }

}
