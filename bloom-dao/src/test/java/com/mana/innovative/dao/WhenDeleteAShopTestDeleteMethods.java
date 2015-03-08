package com.mana.innovative.dao;

import com.mana.innovative.constants.TestConstants;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Bloom/Rono on 3/3/2015.
 * This class is for .. ToDo
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WhenDeleteAShopTestDeleteMethods {

    private static final Logger logger = Logger.getLogger( WhenDeleteAShopTestDeleteMethods.class );

    @Resource
    private ShopDAO shopDAO;

    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testShopDAODeleteWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for ShopDAODeleteWithErrorEnabled" );

        logger.debug( "Finishing test for ShopDAODeleteWithErrorEnabled" );
    }
}
