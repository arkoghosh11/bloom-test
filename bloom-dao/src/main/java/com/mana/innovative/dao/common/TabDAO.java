package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Tab;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.logic.ItemSearchOption;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Tab dAO.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface TabDAO {


    /**
     * Gets tabs.
     *
     * @param requestParams the request params
     * @return the tabs
     */
    DAOResponse< Tab > getTabs( RequestParams requestParams );

    /**
     * Delete tab.
     *
     * @param tabId the tab id
     * @param requestParams the request params
     * @return the boolean
     */
    DAOResponse< Tab > deleteTabByTabId( int tabId, RequestParams requestParams );

    /**
     * Update tab.
     *
     * @param tab the tab
     * @param requestParams the request params
     * @return the boolean
     */
    DAOResponse< Tab > updateTab( Tab tab, RequestParams requestParams );

    /**
     * Create tab.
     *
     * @param tab the tab
     * @param requestParams the request params
     * @return the boolean
     */
    DAOResponse< Tab > createTab( Tab tab, RequestParams requestParams );

    /**
     * Gets tab by search params.
     *
     * @param searchOption the tab search option
     * @param requestParams the request params
     * @return the tab by search params
     */
    DAOResponse< Tab > getTabBySearchParams( ItemSearchOption searchOption, RequestParams requestParams );

    /**
     * Gets tab by tab id.
     *
     * @param tabId the tab id
     * @param requestParams the request params
     * @return the tab by tab id
     */
    DAOResponse< Tab > getTabByTabId( int tabId, RequestParams requestParams );

    /**
     * Delete tabs.
     *
     * @param tabIds the tab ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Tab > deleteTabs( List< Integer > tabIds, RequestParams requestParams );

    /**
     * Delete all tabs.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Tab > deleteAllTabs( RequestParams requestParams );
}
