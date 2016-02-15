package com.mana.innovative.rest.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * The class CORSFilter is for todo.
 * Created by BLOOM on 12/21/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Provider
public class CORSFilter implements ContainerResponseFilter, Filter {

	private static final Logger logger = LoggerFactory.getLogger( CORSFilter.class );

	@Override
	public ContainerResponse filter( ContainerRequest request,
									 ContainerResponse response ) {

		response.getHttpHeaders( ).add( "Access-Control-Allow-Origin", "*" );
		response.getHttpHeaders( ).add( "Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization" );
		response.getHttpHeaders( ).add( "Access-Control-Allow-Credentials", "true" );
		response.getHttpHeaders( ).add( "Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD" );

		return response;
	}

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException {

	}

	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
		logger.debug( "Starting doFilter for adding response headers" );
		( ( HttpServletResponse ) servletResponse ).addHeader( "Access-Control-Allow-Origin", "*" );
		( ( HttpServletResponse ) servletResponse ).addHeader( "Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization" );
		( ( HttpServletResponse ) servletResponse ).addHeader( "Access-Control-Allow-Credentials", "true" );
		( ( HttpServletResponse ) servletResponse ).addHeader( "Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD" );
		filterChain.doFilter( servletRequest, servletResponse );
		logger.debug( "Finishing doFilter for adding response headers" );
	}

	@Override
	public void destroy( ) {

	}
}
