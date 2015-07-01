package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by Bloom/Rono on 5/15/2015 11:25 PM. This class WhenDeleteCCardThenTestCCardDAOMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteCCardThenTestCCardDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteCCardThenTestCCardDAODeleteMethods.class );

    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The CreditCard dAO.
     */
    @Resource
    private CreditCardDAO creditCardDAO;

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
        testIds.add( ( long ) TestConstants.TWO );
        testIds.add( testId );
        requestParams = new RequestParams( );
        requestParams.setId( TestConstants.TEST_ID );
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
            DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.getCreditCardByCardId( testId, requestParams );
            Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
            Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, creditCardDAOResponse.getCount( ) );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while trying to test if row was deleted during test", exception );
            fail( "Failed to test if row was deleted during test" );
        }
    }

    /**
     * Test delete creditCard with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteCreditCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCreditCardWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.deleteCreditCardByCardId( testId, requestParams );

        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );

        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, creditCardDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteCreditCardWithErrorDisabled" );
    }

    /**
     * Test delete creditCard with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteCreditCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCreditCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.deleteCreditCardByCardId( testId, requestParams );

        Assert.assertNotNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, creditCardDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteCreditCardWithErrorEnabled" );
    }

    /**
     * Test delete creditCards by creditCard ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCreditCardsByCreditCardIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCreditCardsByCreditCardIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.deleteCreditCardsByCardIds( testIds, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), creditCardDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCreditCardsByCreditCardIdsErrorEnabled" );
    }

    /**
     * Test delete creditCards by creditCard ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteCreditCardsByCreditCardIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCreditCardsByCreditCardIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.deleteCreditCardsByCardIds( testIds, requestParams );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), creditCardDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteCreditCardsByCreditCardIdsErrorDisabled" );
    }


    /**
     * Test delete all creditCard with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCreditCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCreditCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.deleteAllCreditCards( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, creditCardDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCreditCardWithErrorEnabled" );
    }

    /**
     * Test delete all creditCard with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCreditCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCreditCardWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO
                .deleteAllCreditCards( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, creditCardDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCreditCardWithErrorDisabled" );
    }

    /**
     * Test delete all creditCard with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCreditCardWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCreditCardWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO
                .deleteAllCreditCards( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCreditCardWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all creditCard with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllCreditCardWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllCreditCardWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO
                .deleteAllCreditCards( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllCreditCardWithDeleteAllTrueWithErrorDisabled" );
    }
}