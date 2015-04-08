/**
 * @author Bloom This Class UserResponseBuilder.java is for Created at Aug 28, 2012 4:19:30 PM
 */
package com.mana.innovative.service.consumer.builder;


import com.mana.innovative.dto.consumer.User;
import com.mana.innovative.dto.consumer.payload.UserPayload;
import com.mana.innovative.service.consumer.container.UserResponseContainer;

import java.util.List;

/**
 * The type User response builder.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class UserResponseBuilder {

    /**
     * Build user response container.
     *
     * @param users the users
     *
     * @return the user response container
     */
    public static UserResponseContainer< UserPayload > build( List< User > users ) {

        UserResponseContainer< UserPayload > userResponseContainer = new UserResponseContainer< UserPayload >( );
        userResponseContainer.setCount( users.size( ) );
        UserPayload userPayload = new UserPayload( );
        userPayload.setUsers( users );
        userResponseContainer.setPayload( userPayload );
        return userResponseContainer;
    }

}
