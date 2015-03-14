package com.mana.innovative.service;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.service.impl.ItemsServiceImpl;
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
 *
 * please uncomment the following lines to enable Spring Integration Test
 * the 2nd line requires location on Context Config Files for beans and properties extra, the 1st one is to enable Spring for the Class
 *
 * @ RunWith(value = SpringJUnit4ClassRunner.class | MockitoWithJunitRunner.Class)
 * @ ContextConfiguration(location {"loc1"."loc2"})
 */
@RunWith(value = MockitoJUnitRunner.class)
public class WhenItemsServiceTestSpringTransactional {

    @Mock
    private ItemsServiceImpl itemsServiceImpl;

    /**
     * This method is to initialize Objects and configuration files
     * before testing test method
     */
    @Before
    public void setUp () throws Exception {

        DAOResponse<Item> itemDAOResponse = Mockito.mock(DAOResponse.class);
        Mockito.when( itemsServiceImpl.getItems( Mockito.anyBoolean( ) ) ).thenReturn( Response.ok( ).entity
                (itemDAOResponse).build());
    }


    @Test
    public void testSpringTransactional(){

    }
    /**
     * This method is to release objects and shut down OR close any connections after Test is completed
     * before testing test method
     */
    @After
    public void tearDown () throws Exception {

    }
}