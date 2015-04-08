package com.mana.innovative.dao.consumer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/23/12 Time: 10:13 AM
 * @since: jdk 1.7
 */

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {


    /**
     * This method is to create a user
     *
     * @return boolean Return a boolean value to indicate if user creation passed or failed
     */
    DAOResponse< User > getUsers( RequestParams requestParams );

    /**
     * Gets user by user id.
     *
     * @param userId        the user id
     * @param requestParams the request params
     *
     * @return the user by user id
     */
    DAOResponse< User > getUserByUserId( Long userId, RequestParams requestParams );

    /**
     * This method is to create a user
     *
     * @param user {@link User}
     *
     * @return boolean Return a boolean value to indicate if user creation passed or failed
     */
    DAOResponse< User > createUser( User user, RequestParams requestParams );

    /**
     * Update user.
     *
     * @param user          the user
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< User > updateUser( User user, RequestParams requestParams );


    /**
     * Delete user.
     *
     * @param userId the user id
     *
     * @return the dAO response
     */
    DAOResponse< User > deleteUserByUserId( Long userId, RequestParams requestParams, String tableName );

    /**
     * Delete users.
     *
     * @param userIds       the user ids
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< User > deleteUsers( List< Long > userIds, RequestParams requestParams, String tableName );
}
