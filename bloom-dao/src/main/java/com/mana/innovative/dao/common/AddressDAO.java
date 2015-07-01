package com.mana.innovative.dao.common;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Address dAO.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface AddressDAO {

    /**
     * Gets address by address id.
     *
     * @param addressId the address id
     * @param requestParams the request params
     * @return the address by address id
     */
    DAOResponse< Address > getAddressByAddressId( long addressId, RequestParams requestParams );

    /**
     * Gets address.
     *
     * @param requestParams the request params
     * @return the address
     */
    DAOResponse< Address > getAddress( RequestParams requestParams );

    /**
     * Delete address by address id.
     *
     * @param addressId the address id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Address > deleteAddressByAddressId( long addressId, RequestParams requestParams );

    /**
     * Delete addresses by address ids.
     *
     * @param addressIds the address ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Address > deleteAddressesByAddressIds( List< Long > addressIds, RequestParams requestParams );

    /**
     * Delete all address.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Address > deleteAllAddress( RequestParams requestParams );

    /**
     * Create address.
     *
     * @param address the address
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Address > createAddress( Address address, RequestParams requestParams );


    /**
     * Update address.
     *
     * @param address the address
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Address > updateAddress( Address address, RequestParams requestParams );
}
