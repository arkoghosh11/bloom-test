package com.mana.innovative.service.client;

import com.mana.innovative.dto.client.payload.ItemsPayload;
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
 * Created by alex1 on 1/23/2015. This is a domain class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/service-config-test.xml", "/db-config-test.xml" } )
@TransactionConfiguration
@Transactional
public class WhenGetItemsThenTestItemsService {

    /**
     * The constant logger.
     */
    public static final Logger logger = LoggerFactory.getLogger( WhenGetItemsThenTestItemsService.class );

    /**
     * The Items service impl.
     */

    private ItemsService itemsServiceImpl;
    /**
     * The Item response container.
     */
    private ItemResponseContainer itemResponseContainer;
    /**
     * The Items payload.
     */
    private ItemsPayload itemsPayload;

    /**
     * Sets up.
     */
    @Before
    public void setUp( ) {

        logger.info( "Initiating ItemService Test" );
        itemsServiceImpl = Mockito.mock( ItemsService.class );

        itemResponseContainer = Mockito.mock( ItemResponseContainer.class );
        itemsPayload = Mockito.mock( ItemsPayload.class );

        Response response = Response.status( Status.OK ).entity( itemResponseContainer ).build( );

        Mockito.when( itemsServiceImpl.getItems( false ) ).thenReturn( response );
        Mockito.when( itemResponseContainer.getPayload( ) ).thenReturn( itemsPayload );
        Mockito.when( itemResponseContainer.getCount( ) ).thenReturn( 1 );
        Mockito.when( itemsPayload.getItems( ) ).thenReturn( TestDummyDTOObjectGenerator.getTestItemDTOList( ) );
        Mockito.when( itemsPayload.getTotalCount( ) ).thenReturn( TestDummyDTOObjectGenerator.getTestItemDTOList( ).size( ) );
    }

    /**
     * Test get items.
     */
    @SuppressWarnings( "unchecked" )
    @Test
    public void testGetItems( ) {

        logger.info( "Testing ItemService GetItems method" );

        Response response = itemsServiceImpl.getItems( false );

        Assert.assertNotNull( response );
        Assert.assertNotNull( response.getEntity( ) );
        logger.info( "Testing ItemResponseContainer GetItems method" );
        itemResponseContainer = ( ItemResponseContainer ) response.getEntity( );
        Assert.assertNotNull( itemResponseContainer.getPayload( ) );
        Assert.assertTrue( itemResponseContainer.getCount( ) > 0 );

        logger.info( "Testing ItemsPayload GetItems method" );
        itemsPayload = ( ItemsPayload ) itemResponseContainer.getPayload( );
        Assert.assertNotNull( itemsPayload.getItems( ) );
        Assert.assertTrue( itemsPayload.getItems( ).size( ) > 0 );
    }


    /**
     * Close void.
     */
    @After
    public void close( ) {

        logger.info( "Shutting  Testing for " + logger.getClass( ).getCanonicalName( ) );
    }
}
