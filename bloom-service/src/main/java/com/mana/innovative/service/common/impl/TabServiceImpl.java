/**
 * @author Bloom This Class TabService.java is for Created at Aug 30, 2012 10:32:31 AM
 */
package com.mana.innovative.service.common.impl;

import com.mana.innovative.dao.common.TabDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.TabService;
import com.mana.innovative.service.common.builder.TabResponseBuilder;
import com.mana.innovative.service.common.container.TabResponseContainer;
import com.mana.innovative.utilities.response.TabDomainDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 * The type Tab service impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class TabServiceImpl implements TabService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( TabServiceImpl.class );

    /**
     * The Tab dAO.
     */
    @Resource
    private TabDAO tabDAO;

    /**
     * Gets tab.
     *
     * @param tabId         the tab id
     * @param requestParams the request params
     *
     * @return the tab
     */
    @Override
    public Response getTab( Integer tabId, RequestParams requestParams ) {

        DAOResponse< com.mana.innovative.domain.common.Tab > tabDAOResponse = tabDAO.getTabByTabId( tabId, requestParams );

        TabResponseContainer< TabsPayload > tabResponseContainer = TabResponseBuilder.build( tabDAOResponse,
                requestParams.isError( ) );
        return Response.ok( tabResponseContainer ).build( );
    }

    /**
     * Update tab.
     *
     * @param tabDTO        the tab
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    public Response updateTab( Tab tabDTO, RequestParams requestParams ) {

        com.mana.innovative.domain.common.Tab tabDomain = TabDomainDTOConverter.getConvertedDomainFromDTO( null, tabDTO );
        DAOResponse< com.mana.innovative.domain.common.Tab > tabDAOResponse = tabDAO.updateTab( tabDomain, requestParams );
        // Todo Need to implement this method
        return Response.ok( ).build( );
    }

    /**
     * Delete tab.
     *
     * @param tabId         the tab id
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    public Response deleteTab( Integer tabId, RequestParams requestParams ) {

        tabDAO.deleteTabByTabId( tabId, requestParams );
        return null;
    }

    /**
     * Create tab.
     *
     * @param tab           the tab
     * @param requestParams the request params
     *
     * @return the response
     */
    @Override
    public Response createTab( Tab tab, RequestParams requestParams ) {

        if ( tab == null ) {
            logger.error( "Tab is null" );
            return Response.status( Status.BAD_REQUEST ).build( );
        }

        Response response;
        try {
            tabDAO.createTab( null, requestParams );
            ResponseBuilder responseBuilder = Response.ok( tab );
            responseBuilder.status( Status.CREATED );
            response = responseBuilder.build( );
        } catch ( Exception exception ) {
            response = Response.serverError( ).build( );
            logger.error( "Failed to generate response", exception );
        }
        return response;
    }

}
