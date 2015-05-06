package com.mana.innovative.authentication;/**
 * Created by alex1 on 1/29/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.constants.ServiceConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Properties;

/**
 * The type Login service.
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
@Qualifier
public class LoginService {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( LoginService.class );

    /**
     * The Login parameter name for session.
     */
    @Value( value = "${bloom-service.loginVariable}" )
    private String loginParameterNameForSession;

    /**
     * Do login.
     *
     * @param httpRequest the http request
     * @param httpSession the http session
     * @return the boolean
     */
    public boolean doLogin( HttpServletRequest httpRequest, HttpSession httpSession ) {

        if ( loginParameterNameForSession == null ) {
            loginParameterNameForSession = "isLoginValid";
        }
        logger.debug( "Starting Login Service " );
        Boolean isAuthenticated = ( Boolean ) httpSession.getAttribute( loginParameterNameForSession );
        if ( isAuthenticated != null && isAuthenticated ) {
            return true;
        }
        String envLocation = httpRequest.getServletContext( ).getAttribute( "location" ).toString( );
        envLocation = envLocation.startsWith( "file:/" ) ? envLocation.replaceFirst( "^file:/", "" ) : envLocation;
        Properties properties = new Properties( );
        try {
            properties.load( new FileInputStream( envLocation ) );
        } catch ( IOException e ) {
            logger.error( "Failed to read or load Properties from system located file at " + envLocation, e );
            return false;
        }
        // todo need to make these keys load from properties file
        // IMP by loading from properties file we can make it simple
        // IMP and more secured as the file can be removed from location
        String user = httpRequest.getParameter( "user_name" );
        String password = httpRequest.getParameter( "password" );
        String appKeyValue = httpRequest.getParameter( "app_key" );

        int loginFlag = -1;
        for ( Map.Entry< Object, Object > map : properties.entrySet( ) ) {
            if ( map.getKey( ).toString( ).contains( "user" ) ) {
                if ( map.getValue( ).toString( ).equalsIgnoreCase( user ) ) {
                    loginFlag++;
                }
            }

            if ( map.getKey( ).toString( ).contains( "pass" ) ) {
                if ( map.getValue( ).toString( ).equalsIgnoreCase( password ) ) {
                    loginFlag++;
                }
            }

            if ( map.getKey( ).toString( ).equals( "app_key" ) ) {
                if ( map.getValue( ).toString( ).equalsIgnoreCase( appKeyValue ) ) {
                    loginFlag++;
                }
            }
        }

        if ( loginFlag < 1 ) {
            logger.debug( "Login Service Completed" );
            return false;
        }

//        Cookie cookies[] =httpRequest.getCookies();
//        for(Cookie cookie: cookies) {
//            String value = cookie.getValue();
//            String name = cookie.getName();
//            int maxAge = cookie.getMaxAge();
//        }
//        todo work with cookies for saving login information

        httpSession.setAttribute( "user_name", user );
        httpRequest.setAttribute( loginParameterNameForSession, Boolean.TRUE );
        httpSession.setMaxInactiveInterval( ServiceConstants.HALF_HOUR );
        logger.debug( "Login Service Completed" );
        return true;
    }

    /**
     * Generate unique user token.
     *
     * @param userName the user name
     * @return the string
     */
    protected String generateUniqueUserToken( String userName ) {


        SecureRandom secureRandom = new SecureRandom( );
        return new BigInteger( 130, secureRandom ).toString( 32 );
    }

    /**
     * Check login.
     *
     * @param httpRequest the http request
     * @return the boolean
     */
    public boolean checkLogin( ServletRequest httpRequest ) {

        HttpSession httpSession;
        Boolean isAuthenticated = null;
        if ( httpRequest instanceof HttpServletRequest ) {
            httpSession = ( ( HttpServletRequest ) httpRequest ).getSession( );
            isAuthenticated = ( Boolean ) httpSession.getAttribute( loginParameterNameForSession );
        }
        return isAuthenticated != null && isAuthenticated;
    }
}
