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
    DAOResponse< Address > getAddressByAddressId( long addressId, boolean isError );

    /**
     * Gets address.
     *
     * @param isError the is error
     * @return the address
     */
    DAOResponse< Address > getAddress( boolean isError );

    DAOResponse< Address > deleteAddressByAddressId( long addressId, boolean
            isError );

    DAOResponse< Address > deleteAddressesByAddressIds( List< Long > addressIds, boolean isError );
}
