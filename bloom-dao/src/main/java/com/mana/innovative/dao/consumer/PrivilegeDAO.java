package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Privilege;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

/**
 * Created by Bloom/Rono on 5/15/2015 12:37 AM. This class is PrivilegeDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface PrivilegeDAO {

    /**
     * This method is to create a privilege
     *
     * @param requestParams the request params
     *
     * @return boolean Return a boolean value to indicate if privilege creation passed or failed
     */
    DAOResponse< Privilege > getPrivileges( RequestParams requestParams );

    /**
     * Gets privilege by privilege id.
     *
     * @param privilegeId   the privilege id
     * @param requestParams the request params
     *
     * @return the privilege by privilege id
     */
    DAOResponse< Privilege > getPrivilegeByPrivilegeId( int privilegeId, RequestParams requestParams );

    /**
     * This method is to create a privilege
     *
     * @param privilege     the privilege
     * @param requestParams the request params
     *
     * @return boolean Return a boolean value to indicate if privilege creation passed or failed
     */
    DAOResponse< Privilege > createPrivilege( Privilege privilege, RequestParams requestParams );

    /**
     * Update privilege.
     *
     * @param privilege     the privilege
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< Privilege > updatePrivilege( Privilege privilege, RequestParams requestParams );


    /**
     * Delete privilege.
     *
     * @param privilegeId   the privilege id
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< Privilege > deletePrivilegeByPrivilegeId( int privilegeId, RequestParams requestParams );

    /**
     * Delete privileges.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< Privilege > deleteAllPrivileges( RequestParams requestParams );
}
