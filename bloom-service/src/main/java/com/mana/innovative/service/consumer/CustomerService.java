package com.mana.innovative.service.consumer;

import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * Created by Bloom/Rono on 4/13/2015. This class is CustomerService
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface CustomerService {

    Response getCustomerByUserId( Long customerId, RequestParams requestParams );

    Response createCustomer( Customer customer, RequestParams requestParams );

    Response updateCustomer( Customer customer, RequestParams requestParams );

    Response deleteCustomerByUserId( Long customerId, RequestParams requestParams );

}
