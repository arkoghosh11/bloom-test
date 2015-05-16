package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Shop;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * The type When get shop then test shop dAO get methods.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetShopThenTestShopDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetShopThenTestShopDAOGetMethods.class );
    /**
     * The Shop id.
     */
    long shopId;
    /**
     * The Shop dAO.
     */
    @Resource
    private ShopDAO shopDAOImpl;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        shopId = ( long ) TestConstants.ONE;
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {

        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    /**
     * Test get shop by shop id.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetShopByShopIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetShopByShopIdWithErrorEnabled" );
        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShopByShopId( shopId, TestConstants.IS_ERROR_TRUE );

        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        // test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, shopDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, shopDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );
        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        List< Shop > shops = shopDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, shops.size( ) );
        // Test Shop
        Shop shop = shops.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, shop );
        Assert.assertEquals( TestConstants.notEqualsMessage, shopId, shop.getShopId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_OWN_ID, shop.getShopOwnId( ).longValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_NAME, shop.getShopName( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_WEB_LINK, shop.getShopWebLink( ) );
        // Test Address linked with Shop
        Assert.assertNotNull( TestConstants.nullMessage, shop.getAddress( ) );
        // Test shop items
        Assert.assertNotNull( TestConstants.nullMessage, shop.getItems( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getItems( ).isEmpty( ) );
        // Test Shop working Hours
        Assert.assertNotNull( TestConstants.nullMessage, shop.getWorkingHours( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getWorkingHours( ).isEmpty( ) );

        logger.debug( "Finishing test for GetShopByShopIdWithErrorEnabled" );
    }

    /**
     * Test get shops.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetShopsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetShopsWithErrorEnabled" );
        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShops( TestConstants.IS_ERROR_TRUE );

        // test DAO Response errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );

        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( shopDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, shopDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, shopDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );
        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetShopsWithErrorEnabled" );
    }

    /**
     * Test get shop by shop id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetShopByShopIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetShopByShopIdWithErrorDisabled" );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShopByShopId( shopId, TestConstants.IS_ERROR );
        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, shopDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, shopDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );
        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        //
        List< Shop > shops = shopDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, shops.size( ) );
        // Test Shop
        Shop shop = shops.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, shop );
        Assert.assertEquals( TestConstants.notEqualsMessage, shopId, shop.getShopId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_OWN_ID, shop.getShopOwnId( ).longValue( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_NAME, shop.getShopName( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TEST_WEB_LINK, shop.getShopWebLink( ) );
        // Test Address linked with Shop
        Assert.assertNotNull( TestConstants.nullMessage, shop.getAddress( ) );
        // Test shop items
        Assert.assertNotNull( TestConstants.nullMessage, shop.getItems( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getItems( ).isEmpty( ) );
        // Test Shop working Hours
        Assert.assertNotNull( TestConstants.nullMessage, shop.getWorkingHours( ) );
        Assert.assertFalse( TestConstants.trueMessage, shop.getWorkingHours( ).isEmpty( ) );

        logger.debug( "Finishing test for GetShopByShopIdWithErrorDisabled" );
    }

    /**
     * Test get shops with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetShopsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetShopsWithErrorDisabled" );
        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShops( TestConstants.IS_ERROR );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, shopDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, shopDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, shopDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );
        Assert.assertFalse( shopDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        logger.debug( "Finishing test for GetShopsWithErrorDisabled" );
    }
}