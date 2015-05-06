package com.mana.innovative.service;

import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.service.client.ItemsService;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-config-test.xml","/db-config-test.xml"})
@TransactionConfiguration
@Transactional
public class WhenItemServiceTestGetItems {

    /**
     * The constant logger.
     */
    public static final Logger logger = Logger.getLogger(WhenItemServiceTestGetItems.class);

    /**
     * The Items service impl.
     */
    @Resource
    private ItemsService itemsServiceImpl;

    /**
     * Sets up.
     */
    @Before
    public void setUp () {

        logger.info("Initiating ItemService Test");
    }


    /**
     * Test get items.
     */
    @Test
    public void testGetItems () {

        logger.info("Testing ItemService GetItems method");

        Response response = itemsServiceImpl.getItems( false );

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getEntity());
        logger.info("Testing ItemResponseContainer GetItems method");
        ItemResponseContainer itemResponseContainer = (ItemResponseContainer) response.getEntity();
        Assert.assertNotNull(itemResponseContainer.getPayload());
        Assert.assertTrue(itemResponseContainer.getCount() > 0);

        logger.info("Testing ItemsPayload GetItems method");
        ItemsPayload itemsPayload = (ItemsPayload) itemResponseContainer.getPayload();
        Assert.assertNotNull(itemsPayload.getItems());
        Assert.assertTrue(itemsPayload.getItems().size() > 1);
    }

    /**
     * Close void.
     */
    @After
    public void close() {

        logger.info("Shutting  Testing for " + logger.getClass().getCanonicalName());
    }
}
