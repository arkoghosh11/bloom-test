/**
 * @author Bloom This Class TabServiceImpl.java is for Created at Aug 19, 2012 5:21:25 PM
 */
package com.mana.innovative.service.common.impl;

import com.mana.innovative.dao.common.TabDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.TabsService;
import com.mana.innovative.service.common.builder.TabResponseBuilder;
import com.mana.innovative.service.common.container.TabResponseContainer;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
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
     * @param requestParams the request params
     * @return the all tabs
     */
    public Response getAllTabs( RequestParams requestParams ) {

        log.debug( "**** Tab DAO is " + tabDAO );
        Response response;
        TabResponseContainer< TabsPayload > tabResponseContainer;

        try {
            DAOResponse< com.mana.innovative.domain.common.Tab > tabDAOResponse =
                    tabDAO.getTabs( requestParams );
            tabResponseContainer = TabResponseBuilder.build( tabDAOResponse, requestParams.isError( ) );
            response = Response.ok( tabResponseContainer ).build( );
        } catch ( Exception exception ) {
            response = ResponseUtility.internalServerErrorMsg( null );
        }
        return response;
    }

    /**
     * Delete tabs.
     *
     * @param tabIds the tab ids
     * @param requestParams the request params
     * @return the response
     */
    public Response deleteTabs( List< Integer > tabIds, RequestParams requestParams ) {

        tabDAO.deleteTabs( tabIds, requestParams );
        return null;
    }
}
