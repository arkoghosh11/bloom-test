package com.mana.innovative.dao;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Address dAO.
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
    public DAOResponse< Address > getAddressByAddressId( long addressId, boolean isError );

    /**
     * Gets address.
     *
     * @param isError the is error
     * @return the address
     */
    public DAOResponse< Address > getAddress( boolean isError );

    /**
     * Delete address by address id.
     *
     * @param addressId the address id
     * @param isError   the is error
     *
     * @return the dAO response
     */
    public DAOResponse< Address > deleteAddressByAddressId( long addressId, boolean
            isError );

    /**
     * Delete addresses by address ids.
     *
     * @param addressIds the address ids
     * @param isError    the is error
     *
     * @return the dAO response
     */
    public DAOResponse< Address > deleteAddressesByAddressIds( List< Long > addressIds, boolean isError );

    /**
     * Delete all address.
     *
     * @param deleteAllItems the delete all items
     * @param isError the is error
     * @return the dAO response
     */
    public DAOResponse< Address > deleteAllAddress( boolean deleteAllItems, boolean isError );

    /**
     * Create address.
     *
     * @param address the address
     * @param isError the is error
     * @return the dAO response
     */
    public DAOResponse< Address > createAddress( Address address, boolean isError);

}
