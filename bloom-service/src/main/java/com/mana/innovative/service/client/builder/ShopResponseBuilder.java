package com.mana.innovative.service.client.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.dto.client.payload.ShopsPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.client.container.ShopResponseContainer;
import com.mana.innovative.utilities.response.ShopDomainDTOConverter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Shop response builder.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class ShopResponseBuilder {


    private static final Logger logger = Logger.getLogger( ShopResponseBuilder.class );

    /**
     * Build shop response container.
     *
     * @param shopDAOResponse the shop dAO response
     * @param isError         the is error
     *
     * @return the shop response container
     */
    public static ShopResponseContainer< ShopsPayload > build( DAOResponse< Shop > shopDAOResponse, boolean isError ) {

        if ( shopDAOResponse == null ) {
            shopDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter shopDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of Shop Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( shopDAOResponse.getResults( ) == null || shopDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !shopDAOResponse.isDelete( ) && !shopDAOResponse.isRequestSuccess( ) ) {
                shopDAOResponse.setCount( DAOConstants.ZERO );
            }
            shopDAOResponse.setResults( new ArrayList< Shop >( ) );
        }
        String location = "ShopResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< Shop > shops = shopDAOResponse.getResults( );
        ShopResponseContainer< ShopsPayload > shopResponseContainer = new ShopResponseContainer<>( );
        ShopsPayload shopsPayload = new ShopsPayload( );
        ErrorContainer errorContainer = shopDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            shopsPayload.setShops( ShopDomainDTOConverter.getConvertedListDTOFromDomain( shops ) );
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
        shopsPayload.setTotalCount( shopDAOResponse.getCount( ) );
        shopResponseContainer.setPayload( shopsPayload );
        shopResponseContainer.setCount( shopDAOResponse.getCount( ) );
        shopResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of Shop Response" );
        return shopResponseContainer;
    }


    /**
     * Build error.
     *
     * @param location  the location
     * @param isError   the is error
     * @param exception the exception
     *
     * @return the shop response container
     */
    public static ShopResponseContainer< ShopsPayload > buildError( String location, final boolean isError, Exception
            exception ) {


        logger.debug( "Starting building of Error for Shop Response" );

        ShopsPayload shopsPayload = new ShopsPayload( );
        shopsPayload.setShops( new ArrayList< com.mana.innovative.dto.client.Shop >( ) );

        ShopResponseContainer< ShopsPayload > shopResponseContainer = new ShopResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            Error error = new Error( location, exception );
            errorContainer.addError( error );
            shopResponseContainer.setErrorContainer( errorContainer );
        }
        shopResponseContainer.setIsError( true );
        shopResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of Shop Response" );
        return shopResponseContainer;
    }
}
