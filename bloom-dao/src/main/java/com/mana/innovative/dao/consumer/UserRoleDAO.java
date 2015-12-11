package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.UserRole;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

/**
 * Created by Bloom/Rono on 5/15/2015 12:30 AM. This class is UserRoleDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface UserRoleDAO {

    /**
     * This method is to create a userRole
     *
     * @param requestParams the request params
     *
     * @return boolean Return a boolean value to indicate if userRole creation passed or failed
     */
    DAOResponse< UserRole > getUserRoles( RequestParams requestParams );

    /**
     * Gets userRole by userRole id.
     *
     * @param userRoleId    the userRole id
     * @param requestParams the request params
     *
     * @return the userRole by userRole id
     */
    DAOResponse< UserRole > getUserRoleByUserRoleId( int userRoleId, RequestParams requestParams );

    /**
     * Gets user role by user role name.
     *
     * @param userName      the user name
     * @param requestParams the request params
     *
     * @return the user role by user role name
     */
    DAOResponse< UserRole > getUserRoleByUserRoleName( String userName, RequestParams requestParams );

    /**
     * This method is to create a userRole
     *
     * @param userRole      the userRole
     * @param requestParams the request params
     *
     * @return boolean Return a boolean value to indicate if userRole creation passed or failed
     */
    DAOResponse< UserRole > createUserRole( UserRole userRole, RequestParams requestParams );

    /**
     * Update userRole.
     *
     * @param userRole      the userRole
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< UserRole > updateUserRole( UserRole userRole, RequestParams requestParams );


    /**
     * Delete userRole.
     *
     * @param userRoleId    the userRole id
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< UserRole > deleteUserRoleByUserRoleId( int userRoleId, RequestParams requestParams );

    /**
     * Delete userRoles.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< UserRole > deleteAllUserRoles( RequestParams requestParams );

    /**
     * Gets user role by user id.
     *
     * @param userId        the user id
     * @param requestParams the request params
     *
     * @return the user role by user id
     */
    DAOResponse< UserRole > getUserRoleByUserId( Long userId, RequestParams requestParams );
}
