package com.mana.innovative.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Bloom/Rono on 6/7/2015 10:09 PM. This class is RequestProcessingInterceptor
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
public class RequestProcessingInterceptor extends HandlerInterceptorAdapter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( RequestProcessingInterceptor.class );

    /**
     * Pre handle.
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     *
     * @return the boolean
     *
     * @throws Exception the exception
     */
    @Override
    public boolean preHandle( HttpServletRequest request,
                              HttpServletResponse response, Object handler ) throws Exception {
        long startTime = System.currentTimeMillis( );
        logger.info( "********* Request URL::" + request.getRequestURL( ).toString( )
                + ":: Start Time=" + System.currentTimeMillis( ) );
        request.setAttribute( "startTime", startTime );
        //if returned false, we need to make sure 'response' is sent
        return true;
    }

    /**
     * Post handle.
     *
     * @param request the request
     * @param response the response
     * @param handler the handler
     * @param modelAndView the model and view
     * @throws Exception the exception
     */
    @Override
    public void postHandle( HttpServletRequest request,
                            HttpServletResponse response, Object handler,
                            ModelAndView modelAndView ) throws Exception {
        logger.debug( "Request URL::" + request.getRequestURL( ).toString( )
                + " Sent to Handler :: Current Time=" + System.currentTimeMillis( ) );
        //we can add attributes in the modelAndView and use that in the view page
    }

    /**
     * After completion.
     *
     * @param request the request
     * @param response the response
     * @param handler the handler
     * @param ex the ex
     * @throws Exception the exception
     */
    @Override
    public void afterCompletion( HttpServletRequest request,
                                 HttpServletResponse response, Object handler, Exception ex )
            throws Exception {
        long startTime = ( Long ) request.getAttribute( "startTime" );
        logger.info( "Request URL::" + request.getRequestURL( ).toString( )
                + ":: End Time=" + System.currentTimeMillis( ) );
        logger.info( "Request URL::" + request.getRequestURL( ).toString( )
                + ":: Time Taken=" + ( System.currentTimeMillis( ) - startTime ) );
    }
}
