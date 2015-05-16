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
 * Created by Bloom/Rono on 5/2/2015 6:16 PM. This class WhenGetPreferenceThenTestPreferenceDAOGetMethods is a test
 * class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetPreferenceThenTestPreferenceDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetPreferenceThenTestPreferenceDAOGetMethods.class );

    /**
     * The Preference dAO.
     */
    @Resource
    private PreferenceDAO preferenceDAO;
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
     * Test get preferences with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPreferencesWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetPreferencesWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.getPreferences( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.getResults( ).isEmpty( ) );
        // preference list and its size with DAOResponse<T> class count
        List< Preference > preferences = preferenceDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, preferences );
        Assert.assertFalse( TestConstants.trueMessage, preferences.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDAOResponse.getCount( ), preferences.size( ) );

        for ( Preference preference : preferences ) {
            Assert.assertNotNull( TestConstants.nullMessage, preference );
        }

        logger.debug( "Finishing test for GetPreferencesWithErrorDisabled" );
    }

    /**
     * Test get preference with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPreferenceWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetPreferenceWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.getPreferenceByPreferenceId( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.getResults( ).isEmpty( ) );
        // preference list and its size with DAOResponse<T> class count
        List< Preference > preferences = preferenceDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, preferences );
        Assert.assertFalse( TestConstants.trueMessage, preferences.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDAOResponse.getCount( ), preferences.size( ) );
        Assert.assertEquals( TestConstants.ONE, preferences.size( ) );
        // test preference
        Preference preference = preferences.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, preference );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getPreferenceId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getPreferenceId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getPreferenceName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, preference.getCustomerPreferences( ) );
        Assert.assertFalse( TestConstants.trueMessage, preference.getCustomerPreferences( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, preference.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetPreferenceWithErrorDisabled" );
    }

    /**
     * Test get preferences with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPreferencesWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetPreferencesWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.getPreferences( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.getResults( ).isEmpty( ) );

        // preference list and its size with DAOResponse<T> class count
        List< Preference > preferences = preferenceDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, preferences );
        Assert.assertFalse( TestConstants.trueMessage, preferences.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDAOResponse.getCount( ), preferences.size( ) );

        for ( Preference preference : preferences ) {
            Assert.assertNotNull( TestConstants.nullMessage, preference );
        }

        logger.debug( "Finishing test for GetPreferencesWithErrorEnabled" );
    }

    /**
     * Test get preference with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPreferenceWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetPreferenceWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.getPreferenceByPreferenceId( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.getResults( ).isEmpty( ) );

        // preference list and its size with DAOResponse<T> class count
        List< Preference > preferences = preferenceDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, preferences );
        Assert.assertFalse( TestConstants.trueMessage, preferences.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, preferenceDAOResponse.getCount( ), preferences.size( ) );
        Assert.assertEquals( TestConstants.ONE, preferences.size( ) );

        // test preference
        Preference preference = preferences.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, preference );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getPreferenceId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getPreferenceId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getPreferenceName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, preference.getCustomerPreferences( ) );
        Assert.assertFalse( TestConstants.trueMessage, preference.getCustomerPreferences( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, preference.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, preference.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetPreferenceWithErrorEnabled" );
    }
}