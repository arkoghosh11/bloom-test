package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Customer;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

/**
 * Created by Bloom/Rono on 4/10/2015.
 * <p/>
 * This interface is CustomerDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface CustomerDAO {

    DAOResponse< Customer > createCustomer( Customer customer, RequestParams requestParams );

    DAOResponse< Customer > getCustomers( RequestParams requestParams );

    DAOResponse< Customer > deleteCustomer( Long customerId, RequestParams requestParams );
}
