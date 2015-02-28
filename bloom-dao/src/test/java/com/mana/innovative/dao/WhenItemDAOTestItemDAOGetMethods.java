package com.mana.innovative.dao;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by alex1 on 1/21/2015.
 * This is a domain class
 */
@RunWith (value = SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "/dbConfig-test.xml")
@TransactionConfiguration (transactionManager = "transactionManager", defaultRollback = true)
public class WhenItemDAOTestItemDAOGetMethods {

    private static final Logger logger = LoggerFactory.getLogger(WhenItemDAOTestItemDAOGetMethods.class);

    private Item defaultItem;

    @Resource
    private ItemDAO itemDAO;

    @Before
    @BeforeTransaction
    public void setUp () {

        defaultItem = new Item();
        defaultItem.setItemId(TestConstants.ZERO);
        defaultItem.setItemName(TestConstants.DEFAULT_ITEM_NAME);
        defaultItem.setItemPrice(TestConstants.DEFAULT_PRICE);
        defaultItem.setItemPriceCurrency(TestConstants.DEFAULT_ITEM_PRICE_CURRENCY);
        defaultItem.setItemType(TestConstants.DEFAULT_ITEM_TYPE);
        defaultItem.setItemSubType(TestConstants.DEFAULT_ITEM_SUB_TYPE);
        defaultItem.setBoughtFrom(TestConstants.DEFAULT_BROUGHT_FROM);

        defaultItem.setQuantity(TestConstants.DEFAULT_QUANTITY);
        defaultItem.setQuantityType(QuantityType.UNIT.toString());

        defaultItem.setWeight(TestConstants.DEFAULT_WEIGHT);
        defaultItem.setWeightedUnit(WeightedUnit.KG.toString());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            defaultItem.setCreatedDate(dateFormat.parse("2015-01-23 17:22:12.000"));
            defaultItem.setUpdatedDate(dateFormat.parse("2015-01-28 21:05:16.000"));
            defaultItem.setBoughtDate(dateFormat.parse("2015-02-25 00:00:00.000"));

        } catch (ParseException e) {
            logger.error("Failed to initialize objects before testing", e);
        }
    }


    @Test
    public void testItemDAONotNull () {

        Assert.assertNotNull(itemDAO);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void testItemDAOGetItemNyItemId () {

        Item item;
        DAOResponse<Item> itemDAOResponse = itemDAO.getItemByItemId(TestConstants.ZERO, TestConstants.IS_ERROR);

        Assert.assertNotNull(itemDAOResponse);
        List<Item> items = itemDAOResponse.getResults();

        Assert.assertNotNull(items);
        Assert.assertFalse(items.isEmpty());
        Assert.assertEquals(TestConstants.ONE, items.size());

        item = items.get(TestConstants.ZERO);
        Assert.assertNotNull(item);

        Assert.assertNotNull(item);
        Assert.assertNotNull(item.getItemId());
        Assert.assertNotNull(item.getItemName());
        Assert.assertNotNull(item.getItemPrice());
        Assert.assertNotNull(item.getItemPriceCurrency());
        Assert.assertNotNull(item.getItemType());
        Assert.assertNotNull(item.getCreatedDate());
        Assert.assertNotNull(item.getUpdatedDate());

        Assert.assertTrue("Values not equal", defaultItem.equals(item));
//        /** logger.debug*/ System.out.println("defaultItem created " + defaultItem.getCreatedDate().getTime() + " Item created" + item.getCreatedDate().getTime());
//        /** logger.debug*/ System.out.println("defaultItem updated " + defaultItem.getUpdatedDate().getTime() + " Item updated" + item.getUpdatedDate().getTime());

    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void testItemDAORead () {


        DAOResponse<Item> itemDAOResponse = itemDAO.getItems(TestConstants.IS_ERROR);

        Assert.assertNotNull(itemDAOResponse);
        List<Item> items = itemDAOResponse.getResults();

        Assert.assertNotNull(items);
        Assert.assertFalse(items.isEmpty());
        Assert.assertTrue(items.size() >= TestConstants.ONE);
//        Assert.assertEquals(27, items.size()); /** Just a guarantee check for making sure new changes are working */
    }

    @After
    @AfterTransaction
    public void tearDown () {

//        if(!sessionFactory.isClosed()){
//            sessionFactory.close();
//        }
    }
}
