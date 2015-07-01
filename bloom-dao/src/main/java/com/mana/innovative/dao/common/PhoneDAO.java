package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Phone;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 5:27 PM. This interface is PhoneDAO
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
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
     * Delete phones.
     *
     * @param phoneIds the phone ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Phone > deletePhonesByPhoneIds( List< Long > phoneIds, RequestParams requestParams );

    /**
     * Delete all phones.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Phone > deleteAllPhones( RequestParams requestParams );

}
