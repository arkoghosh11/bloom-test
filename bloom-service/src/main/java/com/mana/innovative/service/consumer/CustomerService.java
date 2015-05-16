package com.mana.innovative.service.consumer;

import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * Created by Bloom/Rono on 4/13/2015. This class is CustomerService
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface CustomerService {

    /**
     * Gets customer by user id.
     *
     * @param customerId    the customer id
     * @param requestParams the request params
     *
     * @return the customer by user id
     */
    Response getCustomerByUserId( Long customerId, RequestParams requestParams );

    /**
     * Create customer.
     *
     * @param customer      the customer
     * @param requestParams the request params
     *
     * @return the response
     */
    Response createCustomer( Customer customer, RequestParams requestParams );

    /**
     * Update customer.
     *
     * @param customer      the customer
     * @param requestParams the request params
     *
     * @return the response
     */
    Response updateCustomer( Customer customer, RequestParams requestParams );

    /**
     * Delete customer by user id.
     *
     * @param customerId    the customer id
     * @param requestParams the request params
     *
     * @return the response
     */
    Response deleteCustomerByUserId( Long customerId, RequestParams requestParams );

}
