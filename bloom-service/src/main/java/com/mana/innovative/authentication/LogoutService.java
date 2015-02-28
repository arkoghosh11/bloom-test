package com.mana.innovative.authentication;/**
 * Created by alex1 on 1/29/2015.
 * This is a class for .. todo 
 */

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by Bloom on 1/29/2015 : 11:10 PM
 * todo This class is for ...
 */
@Service
public class LogoutService {

    public void doLogOut (HttpSession httpSession) {

        if(httpSession != null){
            httpSession.invalidate();
        }

    }
}
