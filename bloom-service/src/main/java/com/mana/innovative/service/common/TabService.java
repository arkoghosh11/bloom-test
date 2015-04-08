/**
 * @author Bloom This Class TabService.java is for Created at Aug 30, 2012 10:32:31 AM
 */
package com.mana.innovative.service.common;

import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * @author Bloom
 */
@Service
public interface TabService {

    Response getTab( Integer tabId, RequestParams requestParams );

    /**
     * Update tab.
     *
     * @param tab           the tab
     * @param requestParams the request params
     *
     * @return the response
     */
    Response updateTab( Tab tab, RequestParams requestParams );


    /**
     * Delete tab.
     *
     * @param tabId         the tab id
     * @param requestParams the request params
     *
     * @return the response
     */

    Response deleteTab( Integer tabId, RequestParams requestParams );


    /**
     * Create tab.
     *
     * @param tab           the tab
     * @param requestParams the request params
     *
     * @return the response
     */

    Response createTab( Tab tab, RequestParams requestParams );
}
