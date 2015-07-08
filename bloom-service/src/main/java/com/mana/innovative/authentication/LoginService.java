package com.mana.innovative.authentication;/**
 * Created by alex1 on 1/29/2015. This is a class for .. todo
 */

import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.service.consumer.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
@Qualifier
public class LoginService implements UserDetailsService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( LoginService.class );

    /**
     * The Login parameter name for session.
     */
    @Value( value = "${bloom-service.loginVariable}" )
    private String loginParameterNameForSession;

    @Resource
    private UserRoleService userRoleService;

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

    /**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * sensitive, or case insensitive depending on how the implementation instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername( final String username ) throws UsernameNotFoundException {
        String location = this.getClass( ).getCanonicalName( ) + "#()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );
        return null;
    }
}
