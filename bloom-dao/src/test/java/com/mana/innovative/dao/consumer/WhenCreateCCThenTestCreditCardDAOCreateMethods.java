package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.CreditCard;
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
 * Created by Bloom/Rono on 5/4/2015 8:20 PM. This class WhenCreateCCThenTestCreditCardDAOMethods is a test class
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateCCThenTestCreditCardDAOCreateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateCCThenTestCreditCardDAOCreateMethods.class );

    /**
     * The Dummy credit card.
     */
    private CreditCard dummyCreditCard;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Credit card dAO.
     */
    @Resource
    private CreditCardDAO creditCardDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyCreditCard = TestDummyDomainObjectGenerator.getTestCreditCardDomainObject( );

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
     * Test create credit card with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCreditCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateCreditCardWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.createCreditCard( dummyCreditCard, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.getResults( ).isEmpty( ) );


        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );

        CreditCard creditCard = creditCardDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCreditCard, creditCard );

        logger.debug( "Finishing test for CreateCreditCardWithErrorDisabled" );
    }

    /**
     * Test create credit card with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCreditCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateCreditCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.createCreditCard( dummyCreditCard, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.getResults( ).isEmpty( ) );


        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );

        CreditCard creditCard = creditCardDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCreditCard, creditCard );

        logger.debug( "Finishing test for CreateCreditCardWithErrorEnabled" );
    }
}