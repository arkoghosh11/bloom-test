/**
 * @author Bloom This Class UserService.java is for Created at Aug 30, 2012 10:32:59 AM
 */
package com.mana.innovative.service.consumer;


import com.mana.innovative.dto.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * The interface User service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface UserService {

    /**
     * Gets user.
     *
     * @param userId the user id
     * @param requestParams the request params
     * @return the user
     */
    Response getUserByUserId( Long userId, RequestParams requestParams );

    /**
     * Create user.
     *
     * @param user the user
     * @param requestParams the request params
     * @return the response
     */
    Response createUser( User user, RequestParams requestParams );

    /**
     * Update user.
     *
     * @param user the user
     * @param requestParams the request params
     * @return the response
     */
    Response updateUser( User user, RequestParams requestParams );

    /**
     * Delete user.
     *
     * @param userId the user id
     * @param requestParams the request params
     * @return the response
     */
    Response deleteUserByUserId( Long userId, RequestParams requestParams );
}
