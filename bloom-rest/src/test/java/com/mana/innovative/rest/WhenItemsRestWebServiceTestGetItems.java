package com.mana.innovative.rest;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.AppDescriptor;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by Rono on 2/26/2015.
 * This is a class for .. todo
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class WhenItemsRestWebServiceTestGetItems extends RestTest {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenItemsRestWebServiceTestGetItems.class );

    /**
     * Configure app descriptor.
     *
     * @return the app descriptor
     */
    @Override
    protected AppDescriptor configure() {

        logger.info( TestConstants.setUpMethodLoggerMsg );
        logger.info( "Starting Jersey WebContainer" );
        return super.configure();
    }


    /**
     * Test get items client response with xML.
     */
    @SuppressWarnings( "unchecked" )
    @Test
    public void testGetItemsClientResponseWithXML() {


        ClientResponse response = resource().path("items").accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        logger.debug(" URI used is " + response.toString());
        assertEquals("Failed to get a proper response ", 200, response.getStatus());

        ItemResponseContainer<ItemsPayload> itemsPayloadItemResponseContainer = response.getEntity(ItemResponseContainer.class);

//        verify(dataAccessService, atMost(1)).getProductByWebId("123", false, false);
        Assert.assertNotNull(response);
        Assert.assertNotNull(itemsPayloadItemResponseContainer);
        assertTrue("Count is 0 or less", itemsPayloadItemResponseContainer.getCount() > 0);
        Assert.assertNotNull(itemsPayloadItemResponseContainer.getPayload());
        assertTrue("Count is 0 or less", itemsPayloadItemResponseContainer.getPayload().getItems().size() > 0);
    }

    /**
     * Test get items client response with jSON.
     */
    @Test
    public void testGetItemsClientResponseWithJSON() {

        ClientResponse response = resource().path("items").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String jsonString = response.toString( );
        logger.info( " URI used is " + jsonString );
        assertEquals("Failed to get a proper response ", 200, response.getStatus());
        assertNotNull( TestConstants.nullMessage, jsonString );

    }

}
