package com.mana.innovative.service.consumer.builder;


import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.converter.response.CustomerDomainDTOConverter;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.payload.CustomersPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.consumer.container.CustomerResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer response builder.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class CustomerResponseBuilder {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomerResponseBuilder.class );

    /**
     * Build customer response container.
     *
     * @param customerDAOResponse the customer dAO response
     * @param isError the is error
     * @return Returns a response object for customers
     */
    public static CustomerResponseContainer< CustomersPayload > build( DAOResponse< com.mana.innovative.domain
            .consumer.Customer > customerDAOResponse, boolean isError ) {

        if ( customerDAOResponse == null ) {
            customerDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter customerDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of Customer Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( customerDAOResponse.getResults( ) == null || customerDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !customerDAOResponse.isDelete( ) && !customerDAOResponse.isRequestSuccess( ) ) {
                customerDAOResponse.setCount( DAOConstants.ZERO );
            }
            customerDAOResponse.setResults( new ArrayList< com.mana.innovative.domain.consumer.Customer >( ) );
        }
        String location = "CustomerResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< com.mana.innovative.domain.consumer.Customer > customers = customerDAOResponse.getResults( );
        CustomerResponseContainer< CustomersPayload > customerResponseContainer = new CustomerResponseContainer<>( );
        CustomersPayload customersPayload = new CustomersPayload( );
        ErrorContainer errorContainer = customerDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            customersPayload.setCustomers( CustomerDomainDTOConverter.getConvertedListDTOFromDomain( customers ) );
        } catch ( IllegalArgumentValueException e ) {
            logger.error( "Failed due to invalid values", e );
            errorContainer.addError( new Error( location, e ) );
        } catch ( NullPointerException e ) {
            logger.error( "Failed due to null object", e );
            errorContainer.addError( new Error( location, e ) );
        } catch ( Exception e ) {
            logger.error( "Unexpected error while converting domain to dto object", e );
            errorContainer.addError( new Error( location, e ) );
        }
        customersPayload.setTotalCount( customerDAOResponse.getCount( ) );
        customerResponseContainer.setPayload( customersPayload );
        customerResponseContainer.setCount( customerDAOResponse.getCount( ) );
        customerResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of Customer Response" );
        return customerResponseContainer;
    }

    /**
     * Build error.
     *
     * @param location the location of the error
     * @param isError the is error
     * @param exception the exception
     * @return the customer response container
     */
    public static CustomerResponseContainer< CustomersPayload > buildError( String location, boolean isError,
                                                                            Exception exception ) {

        logger.debug( "Starting building of Error for Customer Response" );
        CustomersPayload customersPayload = new CustomersPayload( );
        customersPayload.setCustomers( new ArrayList< Customer >( ) );
        CustomerResponseContainer< CustomersPayload > customerResponseContainer = new CustomerResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            com.mana.innovative.exception.response.Error error = new Error( location, exception );
            errorContainer.addError( error );
            customerResponseContainer.setErrorContainer( errorContainer );
        }
        customerResponseContainer.setIsError( true );
        customerResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of Customer Response" );
        return customerResponseContainer;
    }
}
