package com.mana.innovative.service.consumer.builder;


import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.payload.CustomersPayload;
import com.mana.innovative.service.consumer.container.CustomerResponseContainer;

import java.util.List;

/**
 * The type Customer response builder.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class CustomerResponseBuilder {

    /**
     * @param customers {@link List<Customer>} Is an arrayList passed in as super class {List} for generating customer
     *                  response
     *
     * @return {@link CustomerResponseContainer} Returns a response object for customers
     */
    public static CustomerResponseContainer< CustomersPayload > build( List< Customer > customers ) {

        CustomerResponseContainer< CustomersPayload > customerResponseContainer = new CustomerResponseContainer< CustomersPayload >( );
        customerResponseContainer.setCount( customers.size( ) );

        CustomersPayload customersPayload = new CustomersPayload( );
        customersPayload.setCustomers( customers );
        customerResponseContainer.setPayload( customersPayload );

        return customerResponseContainer;
    }
}
