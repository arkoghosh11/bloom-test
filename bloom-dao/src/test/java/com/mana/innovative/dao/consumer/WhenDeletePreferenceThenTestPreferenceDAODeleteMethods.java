package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Preference;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by Bloom/Rono on 5/15/2015 11:29 PM. This class WhenDeletePreferenceThenTestPreferenceDAODeleteMethods is a
 * test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeletePreferenceThenTestPreferenceDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeletePreferenceThenTestPreferenceDAODeleteMethods.class );

    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Preference dAO.
     */
    @Resource
    private PreferenceDAO preferenceDAO;

    /**
     * The Test id.
     */
    private long testId;

    /**
     * The Test ids.
     */
    private List< Long > testIds;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.TEST_ID;
        testIds = new ArrayList<>( );
        testIds.add( ( long ) TestConstants.ZERO );
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
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        try {
            DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.getPreferenceByPreferenceId( testId, requestParams );
            Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
            Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, preferenceDAOResponse.getCount( ) );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while trying to test if row was deleted during test", exception );
            fail( "Failed to test if row was deleted during test" );
        }
    }

    /**
     * Test delete preference with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeletePreferenceWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeletePreferenceWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.deletePreferenceByPreferenceId( testId, requestParams );

        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );

        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, preferenceDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeletePreferenceWithErrorDisabled" );
    }

    /**
     * Test delete preference with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeletePreferenceWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeletePreferenceWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.deletePreferenceByPreferenceId( testId, requestParams );

        Assert.assertNotNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, preferenceDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeletePreferenceWithErrorEnabled" );
    }

    /**
     * Test delete preferences by preference ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeletePreferencesByPreferenceIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeletePreferencesByPreferenceIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.deletePreferencesByPreferenceIds( testIds, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), preferenceDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeletePreferencesByPreferenceIdsErrorEnabled" );
    }

    /**
     * Test delete preferences by preference ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeletePreferencesByPreferenceIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeletePreferencesByPreferenceIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.deletePreferencesByPreferenceIds( testIds, requestParams );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), preferenceDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeletePreferencesByPreferenceIdsErrorDisabled" );
    }


    /**
     * Test delete all preference with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPreferenceWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPreferenceWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.deleteAllPreferences( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, preferenceDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPreferenceWithErrorEnabled" );
    }

    /**
     * Test delete all preference with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPreferenceWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPreferenceWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO
                .deleteAllPreferences( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, preferenceDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPreferenceWithErrorDisabled" );
    }

    /**
     * Test delete all preference with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPreferenceWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPreferenceWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO
                .deleteAllPreferences( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPreferenceWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all preference with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPreferenceWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPreferenceWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.deleteAllPreferences( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPreferenceWithDeleteAllTrueWithErrorDisabled" );
    }
}