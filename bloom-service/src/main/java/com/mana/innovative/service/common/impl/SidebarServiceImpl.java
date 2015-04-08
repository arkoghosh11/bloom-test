package com.mana.innovative.service.common.impl;

import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.SidebarService;

import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 7:37 PM
 * @since: jdk 1.7
 */
public class SidebarServiceImpl implements SidebarService {

//    @GET
//    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
//    public Response getAllSidebars () {
//
//        return Response.ok().build();
//    }

    public Response getSidebarByType( RequestParams requestParams ) {
        return Response.ok( ).build( );
    }
}
