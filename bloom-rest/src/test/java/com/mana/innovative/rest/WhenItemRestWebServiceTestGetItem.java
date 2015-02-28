package com.mana.innovative.rest;/**
 * Created by Rono on 2/26/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.service.container.ItemResponseContainer;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.AppDescriptor;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bloom on 2/26/2015 : 11:25 PM
 * todo This class is for ...
 */
public class WhenItemRestWebServiceTestGetItem extends RestTest {

    private static final Logger logger = Logger.getLogger(WhenItemRestWebServiceTestGetItem.class);


    @Override
    protected AppDescriptor configure() {

        logger.debug("Starting Jersey WebContainer");
        return super.configure();
    }

    @Test
    public void testGetItemsClientResponse() {

        ClientResponse response = resource().path("item/0").accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        logger.debug("Using URI " + response.toString());
        assertEquals("Failed to get a proper response ", 200, response.getStatus());
        ItemResponseContainer<ItemsPayload> itemsPayloadItemResponseContainer = response.getEntity(ItemResponseContainer.class);

//        verify(dataAccessService, atMost(1)).getProductByWebId("123", false, false);
        Assert.assertNotNull(response);
        Assert.assertNotNull(itemsPayloadItemResponseContainer);
        assertTrue("Count is 0 or less", itemsPayloadItemResponseContainer.getCount() > 0);
        Assert.assertNotNull(itemsPayloadItemResponseContainer.getPayload());
        assertTrue("Count is 0 or less", itemsPayloadItemResponseContainer.getPayload().getItems().size() > 0);
    }
}
