/**
 * @author Bloom This Class TabServiceImpl.java is for Created at Aug 19, 2012 5:21:25 PM
 */
package com.mana.innovative.service.common.impl;

import com.mana.innovative.dao.common.TabDAO;
import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.TabsService;
import com.mana.innovative.service.common.builder.TabResponseBuilder;
import com.mana.innovative.service.common.container.TabResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Tabs service impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class TabsServiceImpl implements TabsService {

    /**
     * The constant log.
     */
    private static final Logger log = LoggerFactory.getLogger( TabsServiceImpl.class );

    /**
     * The Tab dAO.
     */
    @Resource
    private TabDAO tabDAO;

    /**
     * Gets all tabs.
     *
     * @return the all tabs
     */
    public Response getAllTabs( RequestParams requestParams ) {

        log.debug( "**** Tab DAO is " + tabDAO );
        tabDAO.getTabs( requestParams );
        TabResponseContainer< TabsPayload > tabResponseContainer
                = TabResponseBuilder.build( new ArrayList< Tab >( ) );
        Response.status( Status.BAD_REQUEST );
        return Response.ok( tabResponseContainer ).build( );
    }

    /**
     * Delete tabs.
     *
     * @param tabIds        the tab ids
     * @param requestParams the request params
     *
     * @return the response
     */
    public Response deleteTabs( List< Integer > tabIds, RequestParams requestParams ) {

        tabDAO.deleteTabs( tabIds, requestParams );
        return null;
    }
}
