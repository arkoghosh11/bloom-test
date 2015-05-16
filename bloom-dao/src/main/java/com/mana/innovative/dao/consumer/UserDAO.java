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

/**
 * The interface User dAO. Created by Bloom/Rono on ${DATE} ${TIME}.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface UserDAO {


    /**
     * This method is to create a user
     *
     * @param requestParams the request params
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
    DAOResponse< User > getUserByUserId( long userId, RequestParams requestParams );

    /**
     * This method is to create a user
     *
     * @param user          the user
     * @param requestParams the request params
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
     * @param userId        the user id
     * @param requestParams the request params
     * @param tableName     the table name
     *
     * @return the dAO response
     */
    DAOResponse< User > deleteUserByUserId( long userId, RequestParams requestParams, String tableName );

    /**
     * Delete users by user ids.
     *
     * @param userIds the user ids
     * @param requestParams the request params
     * @param tableName the table name
     * @return the dAO response
     */
    DAOResponse< User > deleteUsersByUserIds( List< Long > userIds, RequestParams requestParams, String tableName );

    /**
     * Delete users.
     *
     * @param requestParams the request params
     * @param tableName     the table name
     *
     * @return the dAO response
     */
    DAOResponse< User > deleteAllUsers( RequestParams requestParams, String tableName );
}
