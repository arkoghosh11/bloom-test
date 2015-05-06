package com.mana.innovative.service.common.impl;

import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.SidebarService;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 3/23/13 Time: 7:37 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Service
public class SidebarServiceImpl implements SidebarService {

//    @GET
//    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
//    public Response getAllSidebars () {
//
//        return Response.ok().build();
//    }

    /**
     * Gets sidebar by type.
     *
     * @param requestParams the request params
     *
     * @return the sidebar by type
     */
    public Response getSidebarByType( RequestParams requestParams ) {
        return Response.ok( ).build( );
    }
}
