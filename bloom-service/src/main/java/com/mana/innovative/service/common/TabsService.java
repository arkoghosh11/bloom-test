/**
 * @author Bloom This Class TabServiceImpl.java is for Created at Aug 19, 2012 5:21:25 PM
 */
package com.mana.innovative.service.common;

import com.mana.innovative.dto.request.FilterSortParams;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Tabs service.
 * <p/>
 * Created by Bloom/Rono on ${DATE} ${TIME}.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface TabsService {

    /**
     * Gets all tabs.
     *
     * @param requestParams the request params
     * @return the all tabs
     */
    Response getAllTabs( RequestParams requestParams );

    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    Response getTabsSearchedByParams( FilterSortParams searchParams, RequestParams requestParams );

    /**
     * Delete tabs.
     *
     * @param tabIds the tab ids
     * @param requestParams the request params
     * @return the response
     */
    Response deleteTabs( List< Integer > tabIds, RequestParams requestParams );
}