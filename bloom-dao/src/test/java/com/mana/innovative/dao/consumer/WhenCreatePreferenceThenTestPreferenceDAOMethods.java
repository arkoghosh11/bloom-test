package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
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

/**
 * Created by Bloom/Rono on 5/4/2015 8:21 PM. This class WhenCreatePreferenceThenTestPreferenceDAOMethods is a test
 * class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreatePreferenceThenTestPreferenceDAOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreatePreferenceThenTestPreferenceDAOMethods.class );

    /**
     * The Dummy preference.
     */
    private Preference dummyPreference;
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
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyPreference = TestDummyDomainObjectGenerator.getTestPreferenceDomainObject( );

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
     * Test create preference with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreatePreferenceWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreatePreferenceWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.createPreference( dummyPreference, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );

        Preference preference = preferenceDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, preference );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyPreference, preference );

        logger.debug( "Finishing test for CreatePreferenceWithErrorDisabled" );
    }

    /**
     * Test create preference with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreatePreferenceWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreatePreferenceWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Preference > preferenceDAOResponse = preferenceDAO.createPreference( dummyPreference, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, preferenceDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, preferenceDAOResponse.isRequestSuccess( ) );

        Assert.assertFalse( TestConstants.trueMessage, preferenceDAOResponse.getResults( ).isEmpty( ) );

        Preference preference = preferenceDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, preference );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyPreference, preference );

        logger.debug( "Finishing test for CreatePreferenceWithErrorEnabled" );
    }
}