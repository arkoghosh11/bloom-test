package com.mana.innovative.rest.consumer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/25/12 Time: 1:13 PM
 * @since: jdk 1.7
 */

import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.CustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Customers rest web service.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{customers : (i?)customers}" )
public class CustomersRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomersRestWebService.class );

    /**
     * The Customers service.
     */
    @Resource
    private CustomersService customersService;

    /**
     * This method is for giving service for url /rest/tabs with all the tabs data as a {@link List < com
     * .mana.domain.Tab ></>}*
     *
     * @param isError the is error
     * @return A response object containing all of the tabs within the Database
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getAllCustomers( @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #getAllCustomers()" );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return customersService.getAllCustomers( requestParams );
    }

    /**
     * Delete customers.
     *
     * @param customerIds the customer ids
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteCustomers( List< Long > customerIds,
                                     @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #deleteCustomers()" );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        // TODO Auto-generated method stub
        return customersService.deleteCustomers( customerIds, requestParams );
    }
}
