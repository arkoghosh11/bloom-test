package com.mana.innovative.service.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.client.Item;
import com.mana.innovative.dto.client.payload.ItemsPayload;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.TestDummyDTOObjectGenerator;
import com.mana.innovative.service.client.container.ItemResponseContainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by Bloom/Rono on 6/30/2015 6:19 PM. This class WhenCreateITemThenTestItemService is a test class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/service-config-test.xml", "/db-config-test.xml" } )
@TransactionConfiguration
@Transactional
public class WhenCreateItemThenTestItemServiceCreateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateItemThenTestItemServiceCreateMethods.class );

    /**
     * The Items service impl.
     */
    private ItemService itemServiceImpl;
    /**
     * The Item response container.
     */
    private ItemResponseContainer itemResponseContainer;
    /**
     * The Items payload.
     */
    private ItemsPayload itemsPayload;

    private RequestParams requestParams;

    /**
     * The Mock item.
     */
    private Item mockItem;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );

        itemServiceImpl = Mockito.mock( ItemService.class );

        itemResponseContainer = Mockito.mock( ItemResponseContainer.class );
        itemsPayload = Mockito.mock( ItemsPayload.class );
        mockItem = Mockito.mock( Item.class );
        requestParams = Mockito.mock( RequestParams.class );

        Response response = Response.status( Status.OK ).entity( itemResponseContainer ).build( );

        Mockito.when( itemServiceImpl.createItem( mockItem, requestParams ) ).thenReturn( response );
        Mockito.when( itemResponseContainer.getPayload( ) ).thenReturn( itemsPayload );
        Mockito.when( itemResponseContainer.getCount( ) ).thenReturn( 1 );
        Mockito.when( itemsPayload.getItems( ) ).thenReturn( TestDummyDTOObjectGenerator.getTestItemDTOList( ) );
        Mockito.when( itemsPayload.getTotalCount( ) ).thenReturn( TestDummyDTOObjectGenerator.getTestItemDTOList( ).size( ) );


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
     * Test create item.
     *
     * @throws Exception the exception
     */
    @SuppressWarnings( "unchecked" )
    @Test
    public void testCreateItem( ) throws Exception {

        logger.debug( "Starting test for CreateItem" );
        logger.info( "Testing ItemService GetItems method" );

        Response response = itemServiceImpl.createItem( mockItem, requestParams );

        Assert.assertNotNull( response );
        Assert.assertNotNull( response.getEntity( ) );
        logger.info( "Testing ItemResponseContainer CreateItems method" );
        ItemResponseContainer< ItemsPayload > itemResponseContainer
                = ( ItemResponseContainer< ItemsPayload > ) response.getEntity( );

        Assert.assertNotNull( itemResponseContainer.getPayload( ) );
        Assert.assertTrue( itemResponseContainer.getCount( ) > 0 );

        Mockito.verify( itemResponseContainer, Mockito.times( 1 ) ).getPayload( );
        Mockito.verify( itemResponseContainer, Mockito.times( 1 ) ).getCount( );
        logger.info( "Testing ItemsPayload GetItems method" );
        ItemsPayload itemsPayload = itemResponseContainer.getPayload( );

        Mockito.verify( itemResponseContainer, Mockito.times( 2 ) ).getPayload( );

        Assert.assertNotNull( itemsPayload.getItems( ) );
        Assert.assertTrue( itemsPayload.getItems( ).size( ) > 0 );
        Assert.assertEquals( this.itemsPayload, itemsPayload );

        logger.debug( "Finishing test for CreateItem" );
    }
}