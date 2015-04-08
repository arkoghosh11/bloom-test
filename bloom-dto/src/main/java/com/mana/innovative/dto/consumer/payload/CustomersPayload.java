package com.mana.innovative.dto.consumer.payload;


import com.mana.innovative.dto.consumer.Customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/25/12 Time: 1:18 PM
 * @since: jdk 1.7
 */
public class CustomersPayload {

    private List< Customer > customers;

    /**
     * This method returns a list of customers
     *
     * @return {@link List<Customer></>} Return a list of customers
     */
    @XmlElement( name = "customer" )
    @XmlElementWrapper( name = "customers" )
    public List< Customer > getCustomers( ) {
        return customers;
    }

    /**
     * This method sets a list of customers to its class property
     *
     * @param customers {@link List<Customer></>} A list of customers
     */
    public void setCustomers( final List< Customer > customers ) {
        this.customers = customers;
    }
}
