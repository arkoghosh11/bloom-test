package com.mana.innovative.service.consumer;

import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The interface Customers service. Created by IntelliJ IDEA.
 * <p/>
 * Date: 10/25/12 Time: 1:13 PM
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Service
public interface CustomersService {


    /**
     * Gets all customers.
     *
     * @param requestParams the is error
     * @return the all customers
     */
    Response getAllCustomers( RequestParams requestParams );

    /**
     * Delete customers.
     *
     * @param customerIds the customer ids
     * @param requestParams the request params
     * @return the response
     */
    Response deleteCustomers( List< Long > customerIds, RequestParams requestParams );
}
