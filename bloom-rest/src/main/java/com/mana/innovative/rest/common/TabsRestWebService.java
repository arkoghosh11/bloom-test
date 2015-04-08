/**
 * @author Bloom This Class TabServiceImpl.java is for Created at Aug 19, 2012 5:21:25 PM
 */
package com.mana.innovative.rest.common;

import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.TabsService;
import org.apache.log4j.Logger;
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
 * @author Bloom
 */
@Component
@Path( "/{tabs : (?i)tabs}" )
public class TabsRestWebService {

    private static final Logger log = Logger.getLogger( TabsRestWebService.class );

    @Context
    private HttpServletRequest servletRequest;

    @Resource
    private TabsService tabsService;

//    private HttpSession session;


    /**
     * This method is for giving service for url /rest/tabs with all the tabs data as a {@link java.util.List<Tab></>}
     *
     * @return {@link Response} A response object containing all of the tabs within the Database
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getAllTabs( @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {


        log.debug( "**** Tab Service is " + tabsService );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return tabsService.getAllTabs( requestParams );
    }

    @DELETE
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteTabs( List< Integer > tabIds,
                                @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return tabsService.deleteTabs( tabIds, requestParams );
    }
}
