package com.mana.innovative.dao;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Address dAO.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface AddressDAO {

    /**
     * Gets address by address id.
     *
     * @param addressId the address id
     * @param isError the is error
     * @return the address by address id
     */
    DAOResponse< Address > getAddressByAddressId( long addressId, boolean isError );

    /**
     * Gets address.
     *
     * @param isError the is error
     * @return the address
     */
    DAOResponse< Address > getAddress( boolean isError );

    /**
     * Delete address by address id.
     *
     * @param addressId the address id
     * @param isError   the is error
     *
     * @return the dAO response
     */
    DAOResponse< Address > deleteAddressByAddressId( long addressId, boolean
            isError );

    /**
     * Delete addresses by address ids.
     *
     * @param addressIds the address ids
     * @param isError    the is error
     *
     * @return the dAO response
     */
    DAOResponse< Address > deleteAddressesByAddressIds( List< Long > addressIds, boolean isError );

    /**
     * Delete all address.
     *
     * @param deleteAllItems the delete all items
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Address > deleteAllAddress( boolean deleteAllItems, boolean isError );

    /**
     * Create address.
     *
     * @param address the address
     * @param isError the is error
     * @return the dAO response
     */
    DAOResponse< Address > createAddress( Address address, boolean isError );


    /**
     * Update address.
     *
     * @param address the address
     * @param isError the is error
     *
     * @return the dAO response
     */
    DAOResponse< Address > updateAddress( Address address, boolean isError );
}
