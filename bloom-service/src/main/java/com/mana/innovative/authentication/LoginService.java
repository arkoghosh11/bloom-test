package com.mana.innovative.authentication;/**
 * Created by alex1 on 1/29/2015. This is a class for .. todo
 */

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.consumer.UserRole;
import com.mana.innovative.dto.consumer.payload.UsersPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UserRoleService;
import com.mana.innovative.service.consumer.UserService;
import com.mana.innovative.service.consumer.container.UserResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    /**
     * Do login.
     *
     * @param httpRequest the http request
     * @param httpSession the http session
     *
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
     *
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
     *
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
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public UserDetails loadUserByUsername( final String username ) throws UsernameNotFoundException {

        String location = this.getClass( ).getCanonicalName( ) + "#loadUserByUsername()";
        logger.debug( "Starting " + location );
        Response response = userService.findUserByUserName( username, new RequestParams( ) );
        if ( response == null || response.getEntity( ) == null )
            throw new NullPointerException( "Severe Null Exception" );
        UserResponseContainer< UsersPayload > userResponseContainer = ( UserResponseContainer< UsersPayload > ) response.getEntity( );
        UsersPayload usersPayload = userResponseContainer.getPayload( );

        if ( usersPayload == null ) throw new NullPointerException( "Severe Null Exception" );
        List< com.mana.innovative.dto.consumer.User > userDTOList = usersPayload.getUsers( );

        if ( userDTOList == null ) throw new NullPointerException( "Severe Null Exception" );
        com.mana.innovative.dto.consumer.User userDTO = userDTOList.get( DAOConstants.ZERO );

        if ( userDTOList.size( ) > DAOConstants.ONE ) {
            logger.warn( "Found more than one user, size is " + userDTOList.size( ) );
        }
        if ( userDTOList.size( ) < DAOConstants.ONE ) {
            logger.info( "Found no user, size is " + userDTOList.size( ) );
            userDTOList.add( new com.mana.innovative.dto.consumer.User( ) );
        }
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( true );

        List< GrantedAuthority > authorities =
                buildUserAuthority( userRoleService.getUserRoles( requestParams ).getPayload( ).getUserRoles( ) );
        logger.debug( "Finishing " + location );
        return buildUserForAuthentication( userDTO, authorities );
    }

    /**
     * Build user for authentication.
     *
     * @param userDTO     the user dTO
     * @param authorities the authorities
     *
     * @return user
     */
    // Converts com.mana.innovative.dto.consumer.User userDTO user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication( com.mana.innovative.dto.consumer.User userDTO,
                                             List< GrantedAuthority > authorities ) {
        UserRole userRoleDTO = userDTO.getUserRole( );
        // Note userRoleDTO.isExpired() is used fro both accountNonExpired & credentialsNonExpired
        return new User( userDTO.getUserName( ), userDTO.getPassword( ),
                userRoleDTO.isActive( ), userRoleDTO.isExpired( ), userRoleDTO.isExpired( ), userRoleDTO.isLocked( ), authorities );
    }

    /**
     * Build user authority.
     *
     * @param userRoles the user roles
     *
     * @return the list
     */
    private List< GrantedAuthority > buildUserAuthority( List< UserRole > userRoles ) {

        Set< GrantedAuthority > grantedAuthoritySets = new HashSet<>( );

        // Build user's authorities
        for ( UserRole userRole : userRoles ) {
            grantedAuthoritySets.add( new SimpleGrantedAuthority( userRole.getUserRoleName( ) ) );
        }
        return new ArrayList<>( grantedAuthoritySets );
    }
}
