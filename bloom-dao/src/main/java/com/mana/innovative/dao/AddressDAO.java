package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Address;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
