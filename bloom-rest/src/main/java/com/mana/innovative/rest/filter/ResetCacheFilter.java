package com.mana.innovative.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Bloom/Rono on 5/6/2015 9:55 PM. This class is ResetCacheFilter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
public class ResetCacheFilter implements Filter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ResetCacheFilter.class );

    /**
     * Called by the web container to indicate to a filter that it is being placed into service.
     * <p/>
     * <p>The servlet container calls the init method exactly once after instantiating the filter. The init method must
     * complete successfully before the filter is asked to do any filtering work.
     * <p/>
     * <p>The web container cannot place the filter into service if the init method either <ol> <li>Throws a
     * ServletException <li>Does not return within a time period defined by the web container </ol>
     *
     * @param filterConfig the filter config
     *
     * @throws ServletException the servlet exception
     */
    @Override
    public void init( final FilterConfig filterConfig ) throws ServletException {
        String location = this.getClass( ).getCanonicalName( ) + "#init()";
//        logger.debug( "Starting " + location );
//        logger.debug( "Finishing " + location );

        logger.debug( "***** Preparing for all caches to be completely flushed ****** " );
        this.flushAllCaches( );
        logger.debug( "***** Finished clearing all caches ****** " );

    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container each time a request/response pair is
     * passed through the chain due to a client request for a resource at the end of the chain. The FilterChain passed
     * in to this method allows the Filter to pass on the request and response to the next entity in the chain.
     * <p/>
     * <p>A typical implementation of this method would follow the following pattern: <ol> <li>Examine the request
     * <li>Optionally wrap the request object with a custom implementation to filter content or headers for input
     * filtering <li>Optionally wrap the response object with a custom implementation to filter content or headers for
     * output filtering <li> <ul> <li><strong>Either</strong> invoke the next entity in the chain using the FilterChain
     * object (<code>chain.doFilter()</code>), <li><strong>or</strong> not pass on the request/response pair to the next
     * entity in the filter chain to block the request processing </ul> <li>Directly set headers on the response after
     * invocation of the next entity in the filter chain. </ol>
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain
     *
     * @throws IOException      the iO exception
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain ) throws IOException, ServletException {
        String location = this.getClass( ).getCanonicalName( ) + "#doFilter()";
//        logger.debug( "Starting " + location );
        chain.doFilter( request, response );
//        logger.debug( "Finishing " + location );

    }

    /**
     * Called by the web container to indicate to a filter that it is being taken out of service.
     * <p/>
     * <p>This method is only called once all threads within the filter's doFilter method have exited or after a timeout
     * period has passed. After the web container calls this method, it will not call the doFilter method again on this
     * instance of the filter.
     * <p/>
     * <p>This method gives the filter an opportunity to clean up any resources that are being held (for example,
     * memory, file handles, threads) and make sure that any persistent state is synchronized with the filter's current
     * state in memory.
     */
    @Override
    public void destroy( ) {
        String location = this.getClass( ).getCanonicalName( ) + "#()";
        logger.debug( "Starting " + location );
        logger.debug( "Finishing " + location );

    }

    /**
     * Flush all caches.
     */
    @Caching( evict = {
//            @CacheEvict(value="itemsCache", allEntries=true),
            @CacheEvict( value = "shopsCache", allEntries = true )
    } )
    private void flushAllCaches( ) {
        logger.warn( "***** All caches have been completely flushed ****** " );
    }
}
