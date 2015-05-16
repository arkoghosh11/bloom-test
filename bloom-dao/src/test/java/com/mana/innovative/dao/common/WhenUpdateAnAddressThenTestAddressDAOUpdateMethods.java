package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This class is a test class for testing class todo...
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
//@Transactional   // If required
public class WhenUpdateAnAddressThenTestAddressDAOUpdateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenUpdateAnAddressThenTestAddressDAOUpdateMethods.class );

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test update address for shop error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateAddressForShopErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for UpdateAddressForShopErrorEnabled" );

        logger.debug( "Finishing test for UpdateAddressForShopErrorEnabled" );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }
}