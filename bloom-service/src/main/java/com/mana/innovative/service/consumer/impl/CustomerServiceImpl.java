package com.mana.innovative.service.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.consumer.CustomerDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.payload.CustomersPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.consumer.CustomerService;
import com.mana.innovative.service.consumer.builder.CustomerResponseBuilder;
import com.mana.innovative.service.consumer.container.CustomerResponseContainer;
import com.mana.innovative.utilities.response.CustomerDomainDTOConverter;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * Created by Bloom/Rono on 4/13/2015. This class is CustomerServiceImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomerServiceImpl.class );

    /**
     * The Customer dAO.
     */
    @Resource
    private CustomerDAO customerDAO;

    /**
     * Gets single customer details.
     *
     * @param customerId the customer id
     * @param requestParams the request params
     * @return the single customer details
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getCustomerByUserId( Long customerId, RequestParams requestParams ) {

        logger.debug( "Initiating getCustomerByCustomerId for customer_id = " + customerId + ", customerDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.consumer.Customer > customerDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getCustomerByUserId()";
        CustomerResponseContainer< CustomersPayload > customerResponseContainer;
        if ( customerId < 0 ) {
            IllegalArgumentValueException exception = new IllegalArgumentValueException( "Value is less than 0" );
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( customerResponseContainer ).build( );
        }
        try {
            customerDAOResponse = customerDAO.getCustomerByUserId( customerId, requestParams );

        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
            return response;
        }
        try {
            customerResponseContainer = CustomerResponseBuilder.build( customerDAOResponse, requestParams.isError( ) );
            response = Response.status( Response.Status.OK ).entity( customerResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getCustomersByUserId sent Successfully " );
        }
    }

    /**
     * Create customer.
     *
     * @param customer the customer
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response createCustomer( Customer customer, RequestParams requestParams ) {

        logger.debug( "Initiating createCustomer for incoming customer, customerDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.consumer.Customer > customerDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getCustomerByUserId()";
        CustomerResponseContainer< CustomersPayload > customerResponseContainer;
        try {
            com.mana.innovative.domain.consumer.Customer customerDomain = new com.mana.innovative.domain.consumer
                    .Customer( );
            customerDomain = CustomerDomainDTOConverter.getConvertedDomainFromDTO( customerDomain, customer );
            customerDAOResponse = customerDAO.createCustomer( customerDomain, requestParams );

        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( customerResponseContainer ).build( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
            return response;
        }
        try {
            customerResponseContainer = CustomerResponseBuilder.build( customerDAOResponse, requestParams.isError( ) );
            response = Response.status( Response.Status.OK ).entity( customerResponseContainer ).build( );
            return response;

        } catch ( Exception exception ) {
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( "Response for createCustomer generated successfully from Service Level" );
        }
    }

    /**
     * Update customer.
     *
     * @param customerDTO the customer
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response updateCustomer( Customer customerDTO, RequestParams requestParams ) {


        logger.debug( "Initiating updateCustomer for incoming customer, customerDAO injected successfully" );
        com.mana.innovative.domain.consumer.Customer customerDomain = new com.mana.innovative.domain.consumer.Customer( );
        String location = this.getClass( ).getCanonicalName( ) + "#updateCustomer()";
        CustomerResponseContainer< CustomersPayload > customerResponseContainer;
        if ( customerDTO.getUserId( ) < 1 ) {
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( ", CustomerId is Required for " +
                            "Updating a Customer" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( customerResponseContainer ).build( );
        }
        customerDomain.setUserId( customerDTO.getUserId( ) );
        DAOResponse< com.mana.innovative.domain.consumer.Customer > customerDAOResponse;
        try {
            customerDomain = CustomerDomainDTOConverter.getConvertedDomainFromDTO( customerDomain, customerDTO );
            customerDAOResponse = customerDAO.updateCustomer( customerDomain, requestParams );
        } catch ( IllegalArgumentValueException | NullPointerException exception ) {
            logger.error( "Exception occurred while trying to convert object", exception );
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.BAD_REQUEST ).entity( customerResponseContainer ).build( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
        }

        customerResponseContainer = CustomerResponseBuilder.build( customerDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for updateCustomer generated successfully from Service Level" );
        return Response.ok( ).entity( customerResponseContainer ).build( );
    }

    /**
     * Delete specific customer.
     *
     * @param customerId the customer id
     * @param requestParams the request params
     * @return the response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response deleteCustomerByUserId( Long customerId, RequestParams requestParams ) {

        logger.debug( "Initiating deleteCustomerByCustomerId for incoming customer, customerDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#deleteCustomerByUserId()";
        CustomerResponseContainer< CustomersPayload > customerResponseContainer;

        if ( customerId < 1 ) {
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ),
                    new IllegalArgumentValueException( ", CustomerId is required for deleting an Customer" ) );
            return Response.status( Response.Status.BAD_REQUEST ).entity( customerResponseContainer ).build( );
        }
        DAOResponse< com.mana.innovative.domain.consumer.Customer > customerDAOResponse;
        try {
            customerDAOResponse = customerDAO.deleteCustomerByUserId( customerId, requestParams );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                logger.error( "Hibernate Exception occurred while trying to send data to DB " + location, exception );
            } else
                logger.error( "Exception occurred in" + location, exception );

            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
        }
        customerResponseContainer = CustomerResponseBuilder.build( customerDAOResponse, requestParams.isError( ) );

        logger.debug( " Response for deleteCustomerByUserId generated successfully from Service Level" );
        return Response.ok( ).entity( customerResponseContainer ).build( );
    }
}
