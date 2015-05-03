package com.mana.innovative.rest.consumer;

import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Bloom/Rono on 5/3/2015 3:29 AM. This class is CustomerResWebService
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{customer: (?i)customer}" )
public class CustomerResWebService {

    private static final Logger logger = LoggerFactory.getLogger( CustomerResWebService.class );

    @Resource
    private CustomerService customerService;

    /**
     * Gets single customer details.
     *
     * @param customerId the customer id
     *
     * @return the single customer details
     */
    @GET
    @Path( "/{customerId}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getSingleCustomerDetails( @PathParam( "customerId" ) Long customerId,
                                              @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #getSingleCustomerDetails()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return customerService.getCustomerByUserId( customerId, requestParams );
    }

    /**
     * Create new customer.
     *
     * @param customer the customer
     * @param isError  the is error
     *
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createNewCustomer( Customer customer,
                                       @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #createNewCustomer()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return customerService.createCustomer( customer, requestParams );
    }

    /**
     * Update specific customer details.
     *
     * @param customerId the customer id
     *
     * @return the response
     */
    @PUT
    @Path( "/{customerId}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateSpecificCustomerDetails( Customer customer,
                                                   @PathParam( "customerId" ) Long customerId,
                                                   @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #updateSpecificCustomerDetails()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return customerService.updateCustomer( customer, requestParams );
    }

    /**
     * Delete specific customer.
     *
     * @param customerId the customer id
     *
     * @return the response
     */
    @DELETE
    @Path( "/{customerId}" )
    public Response deleteSpecificCustomer( @PathParam( "customerId" ) Long customerId,
                                            @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #deleteSpecificCustomer()" );

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return customerService.deleteCustomerByUserId( customerId, requestParams );
    }
}
