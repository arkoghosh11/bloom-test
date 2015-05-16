package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Phone;
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
 * Created by Bloom/Rono on 5/15/2015 6:45 PM. This class WhenCreatePhoneThenTestPhoneDAOMethods is a test class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreatePhoneThenTestPhoneDAOMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenCreatePhoneThenTestPhoneDAOMethods.class );

    /**
     * The Dummy phone.
     */
    private Phone dummyPhone;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Phone dAO.
     */
    @Resource
    private PhoneDAO phoneDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyPhone = TestDummyDomainObjectGenerator.getTestPhoneDomainObject( );

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
     * Test create phone with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreatePhoneWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreatePhoneWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Phone > phoneDAOResponse = phoneDAO.createPhone( dummyPhone, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );

        Phone phone = phoneDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, phone );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyPhone, phone );

        logger.debug( "Finishing test for CreatePhoneWithErrorDisabled" );
    }

    /**
     * Test create phone with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreatePhoneWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreatePhoneWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Phone > phoneDAOResponse = phoneDAO.createPhone( dummyPhone, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );

        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.getResults( ).isEmpty( ) );

        Phone phone = phoneDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, phone );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyPhone, phone );

        logger.debug( "Finishing test for CreatePhoneWithErrorEnabled" );
    }
}