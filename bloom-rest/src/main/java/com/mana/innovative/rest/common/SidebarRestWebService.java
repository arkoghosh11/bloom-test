package com.mana.innovative.rest.common;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 7:37 PM
 * @since: jdk 1.7
 */
@Component
@Path( "/{sidebar: (i?)sidebar}" )
public class SidebarRestWebService {

//    @GET
//    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
//    public Response getAllSidebars () {
//
//        return Response.ok().build();
//    }

    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getSidebarByType( @QueryParam( "type" ) String type, @QueryParam( "id" ) String id,
                                      @Context UriInfo uriInfo ) {
        System.out.println( "***********  Type is " + type + "  id is " + id + "URi is " + uriInfo.getQueryParameters
                ( true ).toString( ) );
//        Sidebar
        return Response.ok( ).build( );
    }
}
