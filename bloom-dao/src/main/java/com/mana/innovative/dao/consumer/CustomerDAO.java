package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Customer;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bloom/Rono on 4/10/2015.
 * <p/>
 * This interface is CustomerDAO
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface CustomerDAO {

    /**
     * Create customer.
     *
     * @param customer the customer
     * @param requestParams the request params
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
     * @param userId the customer id
     * @param requestParams the request params
     * @return the customer
     */
    DAOResponse< Customer > getCustomerByUserId( long userId, RequestParams requestParams );

    /**
     * Update customer.
     *
     * @param customer the customer
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Customer > updateCustomer( Customer customer, RequestParams requestParams );

    /**
     * Delete customer by user id.
     *
     * @param userId the customer id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Customer > deleteCustomerByUserId( long userId, RequestParams requestParams );

    /**
     * Delete customers by user ids.
     *
     * @param userIds the customer ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Customer > deleteCustomersByUserIds( List< Long > userIds, RequestParams requestParams );

    /**
     * Delete all customers.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Customer > deleteAllCustomers( RequestParams requestParams );
}
