package com.mana.innovative.rest.filter;

import com.mana.innovative.authentication.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Login filter.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
//@javax.servlet.annotation.WebFilter (filterName = "loginFilter")
@Component
public class LoginFilter implements javax.servlet.Filter {

    /**
     * The Login service.
     */
    @Autowired
    @Qualifier
    private LoginService loginService;

    /**
     * Init void.
     *
     * @param filterConfig the filter config
     *
     * @throws ServletException the servlet exception
     */
    public void init( FilterConfig filterConfig ) throws ServletException {

//        ServletContext servletContext = filterConfig.getServletContext( );
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext( servletContext );
//
//        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory( );
//        String beanName = servletContext.getInitParameter( "lService" );
        loginService = new LoginService( );
//        autowireCapableBeanFactory.configureBean( this, beanName );
    }

    /**
     * Do filter.
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the iO exception
     */
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws ServletException, IOException {

        boolean valid = loginService.checkLogin( request );
        if ( true ) {
            chain.doFilter( request, response );
        } else {
            PrintWriter printWriter = response.getWriter( );
            printWriter.print( "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<title>Error</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<br> You are not Authenticated for the Web Services!!!\n" +
                    "</body>\n" +
                    "</html>" );
            RequestDispatcher requestDispatcher = request.getRequestDispatcher( "error.html" );
            System.out.println( "Login Failed" );
            requestDispatcher.include( request, response );
        }
    }

    /**
     * Destroy void.
     */
    public void destroy( ) {

    }

}
