package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
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

/**
 * Created by Bloom/Rono on 5/15/2015 6:44 PM. This class WhenCreateTabThenTestTabDAOMethods is a test class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional()   // If required
public class WhenCreateTabThenTestTabDAOMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenCreateTabThenTestTabDAOMethods.class );

    /**
     * The Dummy tab.
     */
    private Tab dummyTab;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Tab dAO.
     */
    @Resource
    private TabDAO tabDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyTab = TestDummyDomainObjectGenerator.getTestTabDomainObject( );

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
     * Test create tab with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateTabWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateTabWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Tab > tabDAOResponse = tabDAO.createTab( dummyTab, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );

        Tab tab = tabDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, tab );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyTab, tab );

        logger.debug( "Finishing test for CreateTabWithErrorDisabled" );
    }

    /**
     * Test create tab with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateTabWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateTabWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Tab > tabDAOResponse = tabDAO.createTab( dummyTab, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, tabDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, tabDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, tabDAOResponse.isRequestSuccess( ) );

        Assert.assertFalse( TestConstants.trueMessage, tabDAOResponse.getResults( ).isEmpty( ) );

        Tab tab = tabDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, tab );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyTab, tab );

        logger.debug( "Finishing test for CreateTabWithErrorEnabled" );
    }
}