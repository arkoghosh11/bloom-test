package com.mana.innovative.rest;/**
 * Created by Rono on 2/26/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly2.web.GrizzlyWebTestContainerFactory;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bloom on 2/26/2015 : 11:25 PM
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class WhenItemRestWebServiceTestGetItem extends RestTest {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenItemRestWebServiceTestGetItem.class );


    /**
     * Configure app descriptor.
     *
     * @return the app descriptor
     */
    @Override
    protected AppDescriptor configure( ) {
        logger.info( TestConstants.setUpMethodLoggerMsg );
        logger.debug( "Starting Jersey WebContainer" );
        return super.configure( );
    }

    /**
     * Gets test container factory.
     *
     * @return the test container factory
     */
    @Override
    public TestContainerFactory getTestContainerFactory( ) {
        return new GrizzlyWebTestContainerFactory( );
    }

    /**
     * Test get items client response.
     */
    @SuppressWarnings( "unchecked" )
    @Test
    public void testGetItemsClientResponseWithXML( ) {

        ClientResponse response = resource( ).path( "item/0" ).accept( MediaType.APPLICATION_XML ).get( ClientResponse.class );

        logger.debug( "Using URI " + response.toString( ) );
        assertEquals( "Failed to get a proper response ", 200, response.getStatus( ) );
        ItemResponseContainer< ItemsPayload > itemsPayloadItemResponseContainer = response.getEntity( ItemResponseContainer.class );

//        verify(dataAccessService, atMost(1)).getProductByWebId("123", false, false);
        Assert.assertNotNull( response );
        Assert.assertNotNull( itemsPayloadItemResponseContainer );
        assertTrue( "Count is 0 or less", itemsPayloadItemResponseContainer.getCount( ) > 0 );
        Assert.assertNotNull( itemsPayloadItemResponseContainer.getPayload( ) );
        assertTrue( "Count is 0 or less", itemsPayloadItemResponseContainer.getPayload( ).getItems( ).size( ) > 0 );
    }

    /**
     * Test get items client response with jSON.
     */
    @Test
    public void testGetItemsClientResponseWithJSON( ) {

        ClientResponse response = resource( ).path( "item/0" ).accept( MediaType.APPLICATION_JSON ).get( ClientResponse
                .class );
        String jsonString = response.toString( );
        logger.info( " URI used is " + jsonString );
        assertEquals( "Failed to get a proper response ", 200, response.getStatus( ) );
        assertNotNull( TestConstants.nullMessage, jsonString );

    }

}
