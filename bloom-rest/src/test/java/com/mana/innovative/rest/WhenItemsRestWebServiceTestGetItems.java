package com.mana.innovative.rest;

import com.mana.innovative.domain.Item;
import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.service.container.ItemResponseContainer;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


/**
 * Created by Rono on 2/26/2015.
 * This is a class for .. todo
 */
public class WhenItemsRestWebServiceTestGetItems extends RestTest {

    private static final Logger logger = Logger.getLogger(WhenItemsRestWebServiceTestGetItems.class);

    @Override
    protected AppDescriptor configure() {

        logger.debug("Starting Jersey WebContainer");
        return super.configure();
    }


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

    @Test
    public void testGetItemsClientResponseWithJSON() {


        ClientResponse response = resource().path("items").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        logger.debug(" URI used is " + response.toString());
        assertEquals("Failed to get a proper response ", 200, response.getStatus());


//        logger.info("JSON output is\n"+ response.getEntity(String.class));
//        ObjectMapper objectMapper = new ObjectMapper();
//
        ItemResponseContainer<ItemsPayload> itemsPayloadItemResponseContainer = null;
//        try {
//            itemsPayloadItemResponseContainer = objectMapper.readValue(
//                    response.getEntity(String.class),ItemResponseContainer.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail("Failed to parse json string");
//        }
//        ItemResponseContainer<ItemsPayload> itemsPayloadItemResponseContainer = response.getEntity(ItemResponseContainer.class);

        // Create a JaxBContext
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(ItemResponseContainer.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // Create the Unmarshaller Object using the JaxB Context
        Unmarshaller unmarshaller = null;
        try {
            if (jc != null) {
                unmarshaller = jc.createUnmarshaller();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // Set the Unmarshaller media type to JSON or XML
//        try {
//            unmarshaller.setProperty("media type","application/json");
//        } catch (PropertyException e) {
//            e.printStackTrace();
//        }

        // Set it to true if you need to include the JSON root element in the
        // JSON input
//        unmarshaller
//                .setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);

        // Create the StreamSource by creating StringReader using the JSON input
        StreamSource json = new StreamSource(new StringReader(response.getEntity(String.class)));

        // Getting the employee pojo again from the json
        try {
            itemsPayloadItemResponseContainer = unmarshaller.unmarshal(json, ItemResponseContainer.class)
                    .getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
//        verify(dataAccessService, atMost(1)).getProductByWebId("123", false, false);
        Assert.assertNotNull(response);
        Assert.assertNotNull(itemsPayloadItemResponseContainer);
        assertTrue("Count is 0 or less", itemsPayloadItemResponseContainer.getCount() > 0);
        Assert.assertNotNull(itemsPayloadItemResponseContainer.getPayload());
        assertTrue("Count is 0 or less", itemsPayloadItemResponseContainer.getPayload().getItems().size() > 0);
    }

    @After
    public void tearDown() {

        logger.debug("Testing done");
    }

}
