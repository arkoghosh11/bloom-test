package com.mana.innovative.service.consumer.impl;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/25/12 Time: 1:13 PM
 * @since: jdk 1.7
 */

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.consumer.CustomerDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.payload.CustomersPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.service.consumer.CustomersService;
import com.mana.innovative.service.consumer.builder.CustomerResponseBuilder;
import com.mana.innovative.service.consumer.container.CustomerResponseContainer;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Customers service impl.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public class CustomersServiceImpl implements CustomersService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomersServiceImpl.class );

    /**
     * The Customer dAO.
     */
    @Resource
    private CustomerDAO customerDAO;

    /**
     * This method is for giving service for url /rest/tabs with all the tabs data as a {@link List < com
     * .mana.domain.Tab ></>}*
     *
     * @param requestParams the request params
     * @return A response object containing all of the tabs within the Database
     */
    @Override
    @Cacheable( value = ServiceConstants.CUSTOMERS_CACHE, key = ServiceConstants.KEY_NAME )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public Response getAllCustomers( RequestParams requestParams ) {

        logger.debug( "Initiating getCustomers, customerDAO injected successfully" );

        DAOResponse< com.mana.innovative.domain.consumer.Customer > customerDAOResponse;
        Response response;
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getCustomers()";
        CustomerResponseContainer< CustomersPayload > customerResponseContainer;
        try {
            customerDAOResponse = customerDAO.getCustomers( requestParams );

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
            logger.error( "Exception occurred while building response", exception );
            customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ), exception );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( customerResponseContainer ).build( );
            return response;
        } finally {
            logger.debug( " Response for getCustomersByUserId sent Successfully " );
        }
    }

    /**
     * Delete customers.
     *
     * @param customerIds the customer ids
     * @param requestParams the request params
     * @return A response from the server
     */
    //todo decide whether to delete all the if one fails or not or write a dao delete for a collection of customers
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public Response deleteCustomers( List< Long > customerIds, RequestParams requestParams ) {
        logger.debug( "Initiating deleteCustomers for incoming customerIDs, customerDAO injected successfully" );
        String location = this.getClass( ).getCanonicalName( ) + "#deleteCustomers()";
        CustomerResponseContainer< CustomersPayload > customerResponseContainer = null;

        for ( Long customerId : customerIds ) {
            if ( customerId < 1 ) {
                customerResponseContainer = CustomerResponseBuilder.buildError( location, requestParams.isError( ),
                        new IllegalArgumentValueException( ", CustomerId is required for deleting a Customer" ) );
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
        }
        logger.debug( " Response for deleteCustomers generated successfully from Service Level" );
        return Response.ok( ).entity( customerResponseContainer ).build( );
    }
}
