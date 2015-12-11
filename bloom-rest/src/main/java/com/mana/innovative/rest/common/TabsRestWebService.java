/**
 * @author Bloom This Class TabServiceImpl.java is for Created at Aug 19, 2012 5:21:25 PM
 */
package com.mana.innovative.rest.common;

import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.TabsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Tabs rest web service.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{tabs : (?i)tabs}" )
public class TabsRestWebService {

    /**
     * The constant log.
     */
    private static final Logger logger = LoggerFactory.getLogger( TabsRestWebService.class );

    /**
     * The Servlet request.
     */
    @Context
    private HttpServletRequest servletRequest;

    /**
     * The Tabs service.
     */
    @Resource
    private TabsService tabsService;

    // private HttpSession session;

    /**
     * This method is for giving service for url /rest/tabs with all the tabs data as a {@link List<Tab></>}
     *
     * @param isError the is error
     * @return A response object containing all of the tabs within the Database
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getAllTabs( @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting request for #getAllTabs()" );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        Response response = tabsService.getAllTabs( requestParams );
        logger.debug( "Finishing response for #getAllTabs()" );
        return response;
    }

    /**
     * Delete tabs.
     *
     * @param tabIds the tab ids
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteTabs( List< Integer > tabIds, @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting request for #deleteTabs()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        Response response = tabsService.deleteTabs( tabIds, requestParams );

        logger.debug( "Finishing response for #deleteTabs()" );

        return response;
    }
}
