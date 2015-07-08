package com.mana.innovative.service.consumer;

import com.mana.innovative.dto.consumer.payload.UserRolesPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.container.UserRoleResponseContainer;
import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 7/8/2015 2:09 AM.
 * This class is UserRoleService
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface UserRoleService {

    UserRoleResponseContainer< UserRolesPayload > getUserRoleByUserName( String userName, RequestParams requestParams );

    void getUserRoleByUserId( String userId, RequestParams requestParams );

    void addUserRoleByUserId( String userId, RequestParams requestParams );

    void updateUserRoleByUserId( String userId, RequestParams requestParams );

}
