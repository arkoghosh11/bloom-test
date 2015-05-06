package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Phone;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

/**
 * Created by Bloom/Rono on 5/2/2015 5:27 PM. This interface is PhoneDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface PhoneDAO {

    /**
     * Create phone.
     *
     * @param phone the phone
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Phone > createPhone( Phone phone, RequestParams requestParams );

    /**
     * Gets phones.
     *
     * @param requestParams the request params
     * @return the phones
     */
    DAOResponse< Phone > getPhones( RequestParams requestParams );

    /**
     * Gets phone.
     *
     * @param phoneId the phone id
     * @param requestParams the request params
     * @return the phone
     */
    DAOResponse< Phone > getPhone( long phoneId, RequestParams requestParams );

    /**
     * Update phone.
     *
     * @param phone the phone
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Phone > updatePhone( Phone phone, RequestParams requestParams );

    /**
     * Delete phone by phone id.
     *
     * @param phoneId the phone id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Phone > deletePhoneByPhoneId( Long phoneId, RequestParams requestParams );

    /**
     * Delete all phones.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Phone > deleteAllPhones( RequestParams requestParams );

}
