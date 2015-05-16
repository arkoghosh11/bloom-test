package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Tab;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bloom/Rono on 5/15/2015 7:36 PM. This class WhenDeleteTabThenTestTabDAODeleteMethods is a test class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration // If required
@Transactional   // If required
public class WhenDeleteTabThenTestTabDAODeleteMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteTabThenTestTabDAODeleteMethods.class );


    /**
     * The Tab dAO impl.
     */
    @Resource
    private TabDAO tabDAOImpl;
    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    private RequestParams requestParams;

    /**
     * The Test id.
     */
    private int testId;
    /**
     * The Test ids.
     */
    private List< Integer > testIds;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.ONE;
        testIds = new ArrayList<>( );
        testIds.add( TestConstants.ZERO );
        testIds.add( testId );
        requestParams = new RequestParams( );
        logger.debug( "Test SetUp completed" );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    @SuppressWarnings( "unchecked" )
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Tab where tabId=:tab_id" );
        query.setLong( "tab_id", testId );
        List< Tab > tabs = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", tabs.isEmpty( ) );
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test tab dAO not null.
     *
     * @throws Exception the exception
     */
    @Test
    public void testTabDAONotNull( ) throws Exception {

        logger.debug( "Starting test for TabDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOImpl );
        logger.debug( "Finishing test for TabDAONotNull" );
    }

    /**
     * Test delete tab by tab id error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteTabByTabIdErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteTabByTabIdErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteTabByTabId( testId, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, tabDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteTabByTabIdErrorEnabled" );
    }

    /**
     * Test delete tab by tab id error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteTabByTabIdErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteTabByTabIdErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Tab > tabDAOResponse = tabDAOImpl
                .deleteTabByTabId( testId, requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, tabDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteTabByTabIdErrorDisabled" );
    }

    /**
     * Test delete tabs by tab ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteTabsByTabIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteTabsByTabIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteTabs( testIds, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), tabDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteTabsByTabIdsErrorEnabled" );
    }

    /**
     * Test delete tabs by tab ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteTabsByTabIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteTabsByTabIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteTabs( testIds, requestParams );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), tabDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteTabsByTabIdsErrorDisabled" );
    }

    /**
     * Test delete all tab with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllTabWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllTabWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteAllTabs( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, tabDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllTabWithErrorEnabled" );
    }

    /**
     * Test delete all tab with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllTabWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllTabWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteAllTabs( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, tabDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllTabWithErrorDisabled" );
    }

    /**
     * Test delete all tab with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllTabWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllTabWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteAllTabs( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllTabWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all tab with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllTabWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllTabWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Tab > tabDAOResponse = tabDAOImpl.deleteAllTabs( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllTabWithDeleteAllTrueWithErrorDisabled" );
    }
}