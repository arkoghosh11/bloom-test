package com.mana.innovative.service.common;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * The interface Sidebar service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface SidebarService {

    /**
     * Gets sidebar by type.
     *
     * @param requestParams the request params
     * @return the sidebar by type
     */
    Response getSidebarByType( RequestParams requestParams );
}
