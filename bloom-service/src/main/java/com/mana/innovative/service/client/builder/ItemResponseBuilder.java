package com.mana.innovative.service.client.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.client.Item;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import com.mana.innovative.utilities.response.ItemDomainDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Item response builder.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ItemResponseBuilder {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemResponseBuilder.class );

    /**
     * Build item response container.
     *
     * @param itemDAOResponse the item dAO response
     * @param isError the is error
     * @return the item response container
     */
    public static ItemResponseContainer< ItemsPayload > build( DAOResponse< com.mana.innovative.domain.client.Item > itemDAOResponse,
                                                               boolean isError ) {

        if ( itemDAOResponse == null ) {
            itemDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter itemDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of Item Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( itemDAOResponse.getResults( ) == null || itemDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !itemDAOResponse.isDelete( ) && !itemDAOResponse.isRequestSuccess( ) ) {
                itemDAOResponse.setCount( DAOConstants.ZERO );
            }
            itemDAOResponse.setResults( new ArrayList< com.mana.innovative.domain.client.Item >( ) );
        }
        String location = "ItemResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< com.mana.innovative.domain.client.Item > items = itemDAOResponse.getResults( );
        ItemResponseContainer< ItemsPayload > itemResponseContainer = new ItemResponseContainer<>( );
        ItemsPayload itemsPayload = new ItemsPayload( );
        ErrorContainer errorContainer = itemDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            itemsPayload.setItems( ItemDomainDTOConverter.getConvertedListDTOFromDomain( items ) );
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
        itemsPayload.setTotalCount( itemDAOResponse.getCount( ) );
        itemResponseContainer.setPayload( itemsPayload );
        itemResponseContainer.setCount( itemDAOResponse.getCount( ) );
        itemResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of Item Response" );
        return itemResponseContainer;
    }


    /**
     * Build error.
     *
     * @param location the location of the error
     * @param isError the is error
     * @param exception the exception
     * @return the item response container
     */
    public static ItemResponseContainer< ItemsPayload > buildError( String location, boolean isError,
                                                                    Exception exception ) {

        logger.debug( "Starting building of Error for Item Response" );
        ItemsPayload itemsPayload = new ItemsPayload( );
        itemsPayload.setItems( new ArrayList< Item >( ) );
        ItemResponseContainer< ItemsPayload > itemResponseContainer = new ItemResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            Error error = new Error( location, exception );
            errorContainer.addError( error );
            itemResponseContainer.setErrorContainer( errorContainer );
        }
        itemResponseContainer.setIsError( true );
        itemResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of Item Response" );
        return itemResponseContainer;
    }

}
