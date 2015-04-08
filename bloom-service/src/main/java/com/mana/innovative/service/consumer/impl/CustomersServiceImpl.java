package com.mana.innovative.service.consumer.impl;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/25/12 Time: 1:13 PM
 * @since: jdk 1.7
 */

import com.mana.innovative.dao.consumer.CustomerDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.payload.CustomersPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.CustomersService;
import com.mana.innovative.service.consumer.builder.CustomerResponseBuilder;
import com.mana.innovative.service.consumer.container.CustomerResponseContainer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomersServiceImpl implements CustomersService {

    @Resource
    private CustomerDAO customerDAO;

    /**
     * This method is for giving service for url /rest/tabs with all the tabs data as a {@link java.util.List < com
     * .mana.domain.Tab ></>}
     *
     * @return {@link Response} A response object containing all of the tabs within the Database
     */
    @SuppressWarnings( "unused" ) // todo complete customer service
    @Override
    public Response getAllCustomers( RequestParams requestParams ) {

        DAOResponse< com.mana.innovative.domain.consumer.Customer > customerDAOResponse
                = customerDAO.getCustomers( requestParams );
        CustomerResponseContainer< CustomersPayload > customerResponseContainer
                = CustomerResponseBuilder.build( new ArrayList< Customer >( ) );
        return Response.ok( customerResponseContainer ).build( );
    }

    /**
     * @return {@link Response} A response from the server
     */
    @Override
    public Response deleteCustomers( List< Long > customerIds, RequestParams requestParams ) {
        // TODO Auto-generated method stub
        return null;
    }
}
