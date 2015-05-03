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

    /**
     * Create customer.
     *
     * @param customer      the customer
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< Customer > createCustomer( Customer customer, RequestParams requestParams );

    /**
     * Gets customers.
     *
     * @param requestParams the request params
     * @return the customers
     */
    DAOResponse< Customer > getCustomers( RequestParams requestParams );

    /**
     * Gets customer.
     *
     * @param customerId    the customer id
     * @param requestParams the request params
     *
     * @return the customer
     */
    DAOResponse< Customer > getCustomerByUserId( long customerId, RequestParams requestParams );

    /**
     * Update customer.
     *
     * @param customer      the customer
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< Customer > updateCustomer( Customer customer, RequestParams requestParams );

    /**
     * Delete customer by user id.
     *
     * @param customerId    the customer id
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< Customer > deleteCustomerByUserId( Long customerId, RequestParams requestParams );
}
