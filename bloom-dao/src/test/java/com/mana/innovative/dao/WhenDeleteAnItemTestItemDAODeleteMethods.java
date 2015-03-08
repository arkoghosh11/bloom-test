package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import junit.framework.Assert;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by alex1 on 1/28/2015.
 * This class is for testing given {@link ItemDAO#deleteItem(Item)} &
 *   {@link ItemDAO#deleteItemByItemId(int)}
 * <p/>
 * Please uncomment the following lines to enable Spring Integration Test
 * the 2nd line requires location on Context Config Files for beans and properties extra, the 1st one is to enble Spring for the Class
 *
 * @ RunWith(value = SpringJUnit4ClassRunner.class | MockitoWithJunitRunner.Class)
 * @ ContextConfiguration(location {"loc1"."loc2"})
 * @ TransactionConfiguration   <--- Only If required
 * @ Transactional              <--- Only If required
 */
@RunWith (value = SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {"/dbConfig-test.xml"})
@TransactionConfiguration
@Transactional
public class WhenDeleteAnItemTestItemDAODeleteMethods {

    private static final Logger logger = Logger.getLogger(WhenDeleteAnItemTestItemDAODeleteMethods.class);

    @Resource
    private ItemDAO itemDAO;

    @Resource
    private SessionFactory sessionFactory;

    private Item dummyItem;


    /**
     * This method is to initialize Objects and configuration files
     * before testing test method
     */
    @Before
    public void setUp () throws Exception {


        try {
            DAOResponse<Item> itemDAOResponse = itemDAO.getItemByItemId(TestConstants.ZERO, TestConstants.IS_ERROR);

            List<Item> items = itemDAOResponse.getResults();
            Assert.assertFalse( TestConstants.trueMessage, items.isEmpty( ) );

            dummyItem = items.get(TestConstants.ZERO);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Exception occurred in test initializer while try to fetch the deleting record from DB", e);
        }
    }

    @Test
    public void testItemDAONotNull () {

        Assert.assertNotNull(itemDAO);
    }

    /**
     * This method is to test deletion of an item
     */
    @Test
    @Rollback (value = true)
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void testItemDAODelete () throws Exception {

        boolean deleteItem = itemDAO.deleteItem(dummyItem);
        Assert.assertTrue(deleteItem);
    }


    /**
     * This method is to release objects and shut down OR close any connections after Test is completed
     * before testing test method
     */
    @After
    @AfterTransaction
    public void tearDown () throws Exception {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery(" from Item where itemId=:item_id");
        query.setLong("item_id", DAOConstants.ZERO);
        List<Item> items = query.list();
        Assert.assertFalse(" List is Empty, Hib deleted the default row Row ", items.isEmpty());

//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource);
//        jdbcTemplate.execute(" Select * from items",  );

    }
}



