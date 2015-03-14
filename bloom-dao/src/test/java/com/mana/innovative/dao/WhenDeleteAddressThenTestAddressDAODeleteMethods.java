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

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenDeleteAddressThenTestAddressDAODeleteMethods {

    private static final Logger logger = Logger.getLogger( WhenDeleteAddressThenTestAddressDAODeleteMethods.class );

    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testDeleteAddressByAddressId( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressByAddressId" );

        logger.debug( "Finishing test for DeleteAddressByAddressId" );
    }

    @Test
    public void testDeleteAddressesByAddressIds( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressesByAddressIds" );

        logger.debug( "Finishing test for DeleteAddressesByAddressIds" );
    }
}