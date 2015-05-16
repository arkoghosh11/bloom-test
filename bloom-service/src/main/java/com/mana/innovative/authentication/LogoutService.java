package com.mana.innovative.authentication;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * The type Logout service. todo complete log out service
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class LogoutService {

    /**
     * Do log out.
     *
     * @param httpSession the http session
     */
    public void doLogOut( HttpSession httpSession ) {

        if ( httpSession != null ) {
            httpSession.invalidate( );
        }

    }
}
