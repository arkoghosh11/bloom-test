package com.mana.innovative.service.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.client.impl.ItemsServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

/**
 * This class is for testing given
 * <p/>
 * please uncomment the following lines to enable Spring Integration Test the 2nd line requires location on Context
 * Config Files for beans and properties extra, the 1st one is to enable Spring for the Class
 *
 * @author Rono, Ankur Bhardwaj
 * @ RunWith(value = SpringJUnit4ClassRunner.class | MockitoWithJunitRunner.Class) @ ContextConfiguration(location
 *{
 *"loc1"."loc2"
 *}
 *)
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = MockitoJUnitRunner.class )
public class WhenItemsServiceTestSpringTransactional {

    /**
     * The Items service impl.
     */
    @Mock
    private ItemsServiceImpl itemsServiceImpl;

    /**
     * This method is to initialize Objects and configuration files before testing test method
     *
     * @throws Exception the exception
     */
    @SuppressWarnings( "unchecked" )
    @Before
    public void setUp( ) throws Exception {

        DAOResponse< Item > itemDAOResponse = Mockito.mock( DAOResponse.class );
        Mockito.when( itemsServiceImpl.getItems( Mockito.any( RequestParams.class ) ) ).thenReturn(
                Response.ok( ).entity( itemDAOResponse ).build( ) );
    }

    /**
     * Test spring transactional.
     */
    @Test
    public void testSpringTransactional( ) {

    }

    /**
     * This method is to release objects and shut down OR close any connections after Test is completed before testing
     * test method
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {

    }
}