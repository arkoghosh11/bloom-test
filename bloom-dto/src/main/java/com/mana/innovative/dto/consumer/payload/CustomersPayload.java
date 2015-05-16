package com.mana.innovative.dto.consumer.payload;


import com.mana.innovative.dto.consumer.Customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/25/12 Time: 1:18 PM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
public class CustomersPayload {

    /**
     * The Customers.
     */
    private List< Customer > customers;
    /**
     * The Total count.
     */
    private int totalCount;

    /**
     * This method returns a list of customers
     *
     * @return Return a list of customers
     */
    @XmlElement( name = "customer" )
    @XmlElementWrapper( name = "customers" )
    public List< Customer > getCustomers( ) {
        return customers;
    }

    /**
     * This method sets a list of customers to its class property
     *
     * @param customers A list of customers
     */
    public void setCustomers( final List< Customer > customers ) {
        this.customers = customers;
    }


    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount( ) {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    @XmlElement( name = "customer_count" )
    public void setTotalCount( final int totalCount ) {
        this.totalCount = totalCount;
    }
}
