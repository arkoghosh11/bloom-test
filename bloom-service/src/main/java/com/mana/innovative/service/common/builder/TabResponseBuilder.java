/**
 * @author Bloom This Class TabResponseBuilder.java is for Created at Aug 19, 2012 6:24:31 PM
 */
package com.mana.innovative.service.common.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.common.container.TabResponseContainer;
import com.mana.innovative.utilities.response.TabDomainDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Tab response builder.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class TabResponseBuilder {

    /**
     * The constant logger.
     */
    public static final Logger logger = LoggerFactory.getLogger( TabResponseBuilder.class );


    /**
     * Build tab response container.
     *
     * @param tabDAOResponse the tab dAO response
     * @param isError the is error
     * @return Returns a response object for tabs
     */
    public static TabResponseContainer< TabsPayload > build( DAOResponse< com.mana.innovative.domain.common.Tab > tabDAOResponse, boolean isError ) {

        if ( tabDAOResponse == null ) {
            tabDAOResponse = new DAOResponse<>( );
            logger.warn( "Parameter tabDAOResponse was passed as null" );
        }
        logger.debug( "Starting building of Tab Response" );
        /**
         * If these lines are executed an error has occurred is indicated normally unless request is a delete request
         * in that case count will still 1 depending on how many are deleted
         */
        if ( tabDAOResponse.getResults( ) == null || tabDAOResponse.getResults( ).isEmpty( ) ) {
            // for delete success this will be true
            if ( !tabDAOResponse.isDelete( ) && !tabDAOResponse.isRequestSuccess( ) ) {
                tabDAOResponse.setCount( DAOConstants.ZERO );
            }
            tabDAOResponse.setResults( new ArrayList< com.mana.innovative.domain.common.Tab >( ) );
        }
        String location = "TabResponseContainer<T>" + ServiceConstants.HASH + "build()";
        List< com.mana.innovative.domain.common.Tab > tabs = tabDAOResponse.getResults( );
        TabResponseContainer< TabsPayload > tabResponseContainer = new TabResponseContainer<>( );
        TabsPayload tabsPayload = new TabsPayload( );
        ErrorContainer errorContainer = tabDAOResponse.getErrorContainer( );
        if ( errorContainer == null ) {
            errorContainer = new ErrorContainer( );
        }
        try {
            tabsPayload.setTabs( TabDomainDTOConverter.getConvertedListDTOFromDomain( tabs ) );
        } catch ( IllegalArgumentValueException e ) {
            logger.error( "Failed due to invalid values", e );
            errorContainer.addError( new com.mana.innovative.exception.response.Error( location, e ) );
        } catch ( NullPointerException e ) {
            logger.error( "Failed due to null object", e );
            errorContainer.addError( new Error( location, e ) );
        } catch ( Exception e ) {
            logger.error( "Unexpected error while converting domain to dto object", e );
            errorContainer.addError( new Error( location, e ) );
        }
        tabsPayload.setTotalCount( tabDAOResponse.getCount( ) );
        tabResponseContainer.setPayload( tabsPayload );
        tabResponseContainer.setCount( tabDAOResponse.getCount( ) );
        tabResponseContainer.setErrorContainer( isError ? errorContainer : null );

        logger.debug( "Completing building of Tab Response" );
        return tabResponseContainer;
    }

    /**
     * Build error.
     *
     * @param location the location of the error
     * @param isError the is error
     * @param exception the exception
     * @return the tab response container
     */
    public static TabResponseContainer< TabsPayload > buildError( String location, boolean isError,
                                                                  Exception exception ) {

        logger.debug( "Starting building of Error for Tab Response" );
        TabsPayload tabsPayload = new TabsPayload( );
        tabsPayload.setTabs( new ArrayList< com.mana.innovative.dto.common.Tab >( ) );
        TabResponseContainer< TabsPayload > tabResponseContainer = new TabResponseContainer<>( );
        ErrorContainer errorContainer;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
            com.mana.innovative.exception.response.Error error = new Error( location, exception );
            errorContainer.addError( error );
            tabResponseContainer.setErrorContainer( errorContainer );
        }
        tabResponseContainer.setIsError( true );
        tabResponseContainer.setCount( DAOConstants.ZERO );
        logger.debug( "Ending building of Tab Response" );
        return tabResponseContainer;
    }
}
