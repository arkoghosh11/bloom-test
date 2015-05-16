package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Tab;
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
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/3/2015 12:06 AM. This class WhenGetTabThenTestTabDAOMethods is a test class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@Transactional   // If required
public class WhenGetTabThenTestTabDAOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetTabThenTestTabDAOMethods.class );

    /**
     * The Tab dAO.
     */
    @Resource
    private TabDAO tabDAO;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        requestParams = new RequestParams( );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    /**
     * Test get tabs with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetTabsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetTabsWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Tab > tabDAOResponse = tabDAO.getTabs( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.getResults( ).isEmpty( ) );
        // tab list and its size with DAOResponse<T> class count
        List< Tab > tabs = tabDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, tabs );
        Assert.assertFalse( TestConstants.trueMessage, tabs.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDAOResponse.getCount( ), tabs.size( ) );

        for ( Tab tab : tabs ) {
            Assert.assertNotNull( TestConstants.nullMessage, tab );
        }

        logger.debug( "Finishing test for GetTabsWithErrorDisabled" );
    }

    /**
     * Test get tab with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetTabWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetTabWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Tab > tabDAOResponse = tabDAO.getTabByTabId( ( int ) TestConstants.TEST_ID,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.getResults( ).isEmpty( ) );
        // tab list and its size with DAOResponse<T> class count
        List< Tab > tabs = tabDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, tabs );
        Assert.assertFalse( TestConstants.trueMessage, tabs.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDAOResponse.getCount( ), tabs.size( ) );
        Assert.assertEquals( TestConstants.ONE, tabs.size( ) );
        // test tab
        Tab tab = tabs.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, tab );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getTabId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getTabContent( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getTabName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, tab.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetTabWithErrorDisabled" );
    }

    /**
     * Test get tabs with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetTabsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetTabsWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Tab > tabDAOResponse = tabDAO.getTabs( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.getResults( ).isEmpty( ) );

        // tab list and its size with DAOResponse<T> class count
        List< Tab > tabs = tabDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, tabs );
        Assert.assertFalse( TestConstants.trueMessage, tabs.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDAOResponse.getCount( ), tabs.size( ) );

        for ( Tab tab : tabs ) {
            Assert.assertNotNull( TestConstants.nullMessage, tab );
        }

        logger.debug( "Finishing test for GetTabsWithErrorEnabled" );
    }

    /**
     * Test get tab with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetTabWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetTabWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Tab > tabDAOResponse = tabDAO.getTabByTabId( ( int ) TestConstants.TEST_ID,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.getResults( ).isEmpty( ) );

        // tab list and its size with DAOResponse<T> class count
        List< Tab > tabs = tabDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, tabs );
        Assert.assertFalse( TestConstants.trueMessage, tabs.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, tabDAOResponse.getCount( ), tabs.size( ) );
        Assert.assertEquals( TestConstants.ONE, tabs.size( ) );

        // test tab
        Tab tab = tabs.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, tab );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getTabId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getTabContent( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getTabName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, tab.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tab.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetTabWithErrorEnabled" );
    }


    /**
     * Test get tab by search params.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetTabBySearchParams( ) throws Exception {

        logger.debug( "Starting test for GetTabBySearchParams" );

        logger.debug( "Finishing test for GetTabBySearchParams" );
    }


}