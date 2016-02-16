package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemImage;
import com.mana.innovative.dto.request.RequestParams;
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
import java.util.ArrayList;
import java.util.List;

/**
 * The class WhenGetItemImgThenTestItemImgDAOGetMethods is for todo.
 * Created by BLOOM on 11/25/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration(/* transactionManager = "transactionManager", */defaultRollback = true )
@Transactional
public class WhenGetItemImgThenTestItemImgDAOGetMethods {

    /**
     * The ItemImage id.
     */
    long itemImageId;
    private Logger logger = LoggerFactory.getLogger( WhenGetItemImgThenTestItemImgDAOGetMethods.class );
    /**
     * The ItemImage dAO.
     */
    @Resource
    private ItemImageDAO itemImageDAOImpl;

    private RequestParams requestParams;

    private String key;

    private List< Long > values;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        itemImageId = TestConstants.ONE;

        requestParams = new RequestParams( );
        requestParams.setIsError( TestConstants.IS_ERROR );

        key = TestConstants.KEY_ITEM_IMAGE_ID;

        values = new ArrayList<>( );
        values.add( ( long ) TestConstants.ZERO );
        values.add( ( long ) TestConstants.ONE );
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
     * Test get itemImage by itemImage id.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemImageByItemImageIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetItemImageByItemImageIdWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.getItemImageByItemImageId( itemImageId, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
        // test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemImageDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertFalse( itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        List< ItemImage > itemImages = itemImageDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, itemImages.size( ) );
        // Test ItemImage
        ItemImage itemImage = itemImages.get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, itemImage );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageId, itemImage.getItemImageId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_HEIGHT, itemImage.getImageHeight( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_WIDTH, itemImage.getImageWidth(
        ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_PRIORITY, itemImage.getImagePriority( ) );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_LOCATION, itemImage.getImageLocation( ) );

        Assert.assertNotNull( TestConstants.nullMessage, itemImage.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImage.getUpdatedDate( ) );


        // Test itemImage items
        Assert.assertNotNull( TestConstants.nullMessage, itemImage.getItem( ) );

        logger.debug( "Finishing test for GetItemImageByItemImageIdWithErrorEnabled" );
    }

    /**
     * Test get itemImages.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemImagesWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetItemImagesWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.getItemImages( requestParams );

        // test DAO Response errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );

        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( itemImageDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemImageDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertFalse( itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetItemImagesWithErrorEnabled" );
    }

    /**
     * Test get itemImage by itemImage id with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemImageByItemImageIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetItemImageByItemImageIdWithErrorDisabled" );

        DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.getItemImageByItemImageId( itemImageId, requestParams );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );

        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemImageDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertFalse( itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        List< ItemImage > itemImages = itemImageDAOResponse.getResults( );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, itemImages.size( ) );
        // Test ItemImage
        ItemImage itemImage = itemImages.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, itemImage );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemImageId, itemImage.getItemImageId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_HEIGHT, itemImage.getImageHeight( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_WIDTH, itemImage.getImageWidth(
        ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_PRIORITY, itemImage.getImagePriority( ) );

        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_IMAGE_LOCATION, itemImage.getImageLocation( ) );

        Assert.assertNotNull( TestConstants.nullMessage, itemImage.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImage.getUpdatedDate( ) );


        // Test itemImage items
        Assert.assertNotNull( TestConstants.nullMessage, itemImage.getItem( ) );

        logger.debug( "Finishing test for GetItemImageByItemImageIdWithErrorDisabled" );
    }

    /**
     * Test get itemImages with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( readOnly = true )
    public void testGetItemImagesWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetItemImagesWithErrorDisabled" );

        DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.getItemImages( requestParams );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemImageDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertFalse( itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );

        logger.debug( "Finishing test for GetItemImagesWithErrorDisabled" );
    }

    @Test
    @Transactional( readOnly = true )
    public void testGetItemImagesByParams( ) throws Exception {

        logger.debug( "Starting test for GetItemImagesByParams" );

        DAOResponse< ItemImage > itemImageDAOResponse = itemImageDAOImpl.getItemImagesByParams( key, values, requestParams );

        // test errorContainer
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse );
        Assert.assertNull( TestConstants.notNullMessage, itemImageDAOResponse.getErrorContainer( ) );
        // test DAOResponse
        Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, itemImageDAOResponse.getCount( ) );
        Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, itemImageDAOResponse.getOffset( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ) );
        Assert.assertFalse( itemImageDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, itemImageDAOResponse.getResults( ).get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, values.size( ), itemImageDAOResponse.getResults( ).size( ) );

        logger.info( "******* Data 0 is\n " + itemImageDAOResponse.getResults( ).get( 0 ).toString( ) );
        logger.info( "******* Data 1 is\n " + itemImageDAOResponse.getResults( ).get( 1 ).toString( ) );
        logger.debug( "Completing test for GetItemImagesByParams" );
    }
}
