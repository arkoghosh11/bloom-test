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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 6:18 PM. This class WhenGetCreditCardDAOThenTestGetCreditCardGetMethods is a test
 * class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetCreditCardDAOThenTestGetCreditCardGetMethods {

    private static final Logger logger = LoggerFactory.getLogger( WhenGetCreditCardDAOThenTestGetCreditCardGetMethods.class );

    @Resource
    private CreditCardDAO creditCardDAO;
    private RequestParams requestParams;

    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        requestParams = new RequestParams( );
    }

    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCreditCardsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCreditCardsWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.getCreditCards( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.getResults( ).isEmpty( ) );
        // creditCard list and its size with DAOResponse<T> class count
        List< CreditCard > creditCards = creditCardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, creditCards );
        Assert.assertFalse( TestConstants.trueMessage, creditCards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDAOResponse.getCount( ), creditCards.size( ) );

        for ( CreditCard creditCard : creditCards ) {
            Assert.assertNotNull( TestConstants.nullMessage, creditCard );
        }

        logger.debug( "Finishing test for GetCreditCardsWithErrorDisabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCreditCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCreditCardWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.getCreditCard( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, creditCardDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.getResults( ).isEmpty( ) );
        // creditCard list and its size with DAOResponse<T> class count
        List< CreditCard > creditCards = creditCardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, creditCards );
        Assert.assertFalse( TestConstants.trueMessage, creditCards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDAOResponse.getCount( ), creditCards.size( ) );
        Assert.assertEquals( TestConstants.ONE, creditCards.size( ) );
        // test creditCard
        CreditCard creditCard = creditCards.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, creditCard );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCardId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCardNumber( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getIssueDate( ) );

        Assert.assertFalse( TestConstants.trueMessage, creditCard.isCardHasCustomerPic( ) );
        Assert.assertTrue( TestConstants.falseMessage, StringUtils.isEmpty( creditCard.getPictureLocation( ).trim( ) ) );

        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getFirstName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getLastName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getUpdatedDate( ) );

        // Test Credit card
//        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCreateOrModified( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getExpiryDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getMiddleName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCardType( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCVV( ) );

        // Test CC has a customer associated
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCustomerCard( ) );

        logger.debug( "Finishing test for GetCreditCardWithErrorDisabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCreditCardsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCreditCardsWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.getCreditCards( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.getResults( ).isEmpty( ) );

        // creditCard list and its size with DAOResponse<T> class count
        List< CreditCard > creditCards = creditCardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, creditCards );
        Assert.assertFalse( TestConstants.trueMessage, creditCards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDAOResponse.getCount( ), creditCards.size( ) );

        for ( CreditCard creditCard : creditCards ) {
            Assert.assertNotNull( TestConstants.nullMessage, creditCard );
        }

        logger.debug( "Finishing test for GetCreditCardsWithErrorEnabled" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCreditCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCreditCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< CreditCard > creditCardDAOResponse = creditCardDAO.getCreditCard( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, creditCardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDAOResponse.getResults( ).isEmpty( ) );

        // creditCard list and its size with DAOResponse<T> class count
        List< CreditCard > creditCards = creditCardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, creditCards );
        Assert.assertFalse( TestConstants.trueMessage, creditCards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDAOResponse.getCount( ), creditCards.size( ) );
        Assert.assertEquals( TestConstants.ONE, creditCards.size( ) );

        // test Card
        CreditCard creditCard = creditCards.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, creditCard );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCardId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCardNumber( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getIssueDate( ) );

        Assert.assertFalse( TestConstants.trueMessage, creditCard.isCardHasCustomerPic( ) );
        Assert.assertTrue( TestConstants.falseMessage, StringUtils.isEmpty( creditCard.getPictureLocation( ).trim( ) ) );

        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getFirstName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getLastName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getUpdatedDate( ) );

        // Test Credit card
//        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCreateOrModified( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getExpiryDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getMiddleName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCardType( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCVV( ) );

        // Test CC has a customer associated
        Assert.assertNotNull( TestConstants.nullMessage, creditCard.getCustomerCard( ) );

        logger.debug( "Finishing test for GetCreditCardWithErrorEnabled" );
    }
}