package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.CardType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.consumer.CreditCard;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by Bloom/Rono on 4/29/2015 2:16 PM. This class WhenCreditCardConversionThenTestCCConverterMethods is a test
 * class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenCreditCardConversionThenTestCCConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreditCardConversionThenTestCCConverterDomainDTOMethods.class );


    /**
     * The CreditCard dTO.
     */
    private CreditCard creditCardDTO, /**
     * The CreditCard dTO 2.
     */
    creditCardDTO2;
    /**
     * The CreditCard domain.
     */
    private com.mana.innovative.domain.consumer.CreditCard creditCardDomain,
    /**
     * The CreditCard domain 2.
     */
    creditCardDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        creditCardDomain2 = new com.mana.innovative.domain.consumer.CreditCard( );
        creditCardDTO2 = new CreditCard( );

        // Set Values for tempValues
        creditCardDTO = TestDummyDTOObjectGenerator.getTestCreditCardDTOObject( );
        creditCardDomain = TestDummyDomainObjectGenerator
                .getTestCreditCardDomainObject( );


    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );

    }

    /**
     * Test get converted dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomain( ) throws Exception {

        logger.debug( "Starting test for GetConvertedDTOFromDomain" );

        creditCardDTO2 = CreditCardDomainDTOConverter.getConvertedDTOFromDomain( creditCardDTO2, creditCardDomain );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDTO, creditCardDTO2 );

        logger.debug( "Finishing test for GetConvertedDTOFromDomain" );
    }

    /**
     * Test get converted list dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDTOFromDomain( ) throws Exception {

        logger.debug( "Starting test for GetConvertedListDTOFromDomain" );
        List< CreditCard > creditCardDTOList;
        List< com.mana.innovative.domain.consumer.CreditCard > creditCardDomainList = new ArrayList<>( );
        creditCardDomainList.add( creditCardDomain );
        if ( creditCardDomain.getCardId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestCardDomainZEROIDObject( creditCardDomain );
        }
        creditCardDTOList = CreditCardDomainDTOConverter.getConvertedListDTOFromDomain( creditCardDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDTOList );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDTO, creditCardDTOList.get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetConvertedListDTOFromDomain" );
    }

    /**
     * Test get converted domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTO( ) throws Exception {

        logger.debug( "Starting test for GetConvertedDomainFromDTO" );


        creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( creditCardDomain2, creditCardDTO );
        TestDummyDomainObjectGenerator.setTestCardDomainZEROIDObject( creditCardDomain );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDomain, creditCardDomain2 );

        logger.debug( "Finishing test for GetConvertedDomainFromDTO" );
    }

    /**
     * Test get converted list domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDomainFromDTO( ) throws Exception {

        logger.debug( "Starting test for GetConvertedListDomainFromDTO" );

        List< CreditCard > creditCardDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.CreditCard > creditCardDomainList;
        creditCardDTOList.add( creditCardDTO );
        TestDummyDomainObjectGenerator.setTestCardDomainZEROIDObject( creditCardDomain );

        creditCardDomainList = CreditCardDomainDTOConverter.getConvertedListDomainFromDTO( creditCardDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, creditCardDomainList );
        Assert.assertFalse( TestConstants.trueMessage, creditCardDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, creditCardDomain, creditCardDomainList.get( TestConstants.ZERO ) );


        logger.debug( "Finishing test for GetConvertedListDomainFromDTO" );
    }

    /**
     * Test get converted domain from dTO for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOForError" );

        CreditCard creditCard = new CreditCard( );

        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( null, creditCard );

        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
    }

    /**
     * Test get converted domain from dTO with cC property errors.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOWithCCPropertyErrors( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOWithCCPropertyErrors" );

        CreditCard creditCard = new CreditCard( );

        IllegalArgumentValueException illegalArgumentValueException = null;

        // Test for Credit Card Properties
        creditCard.setCardNumber( TestConstants.VALID_CC_NUMBER );
        creditCard.setLastName( TestConstants.TEST_NAME );
        creditCard.setFirstName( TestConstants.TEST_NAME );
        creditCard.setPictureLocation( null );
        creditCard.setCardHasCustomerPic( TestConstants.TEST_FALSE );

        try {
            creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( null, creditCard );

        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        // Test with domain CC null
        illegalArgumentValueException = null;
        try {
            creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( null, new CreditCard( ) );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOWithCCPropertyErrors" );
    }

    /**
     * Test get converted domain from dTO with invalid cVV.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOWithInvalidCVV( ) throws Exception {

        logger.debug( "Starting test for GetConvertedDomainFromDTOWithInvalidCVV" );

        CreditCard creditCard = creditCardDTO;

        IllegalArgumentValueException illegalArgumentValueException = null;

        // Test for Credit Card Properties
        creditCard.setCVV( "xe345" );

        try {
            creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( null, creditCard );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        logger.debug( "Finishing test for GetConvertedDomainFromDTOWithInvalidCVV" );
    }

    /**
     * Test get converted domain from dTO with null create or modified date.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOWithNullCreateOrModifiedDate( ) throws Exception {

        logger.debug( "Starting test for GetConvertedDomainFromDTOWithNullCreateOrModifiedDate" );

        CreditCard creditCard = creditCardDTO;

        IllegalArgumentValueException illegalArgumentValueException = null;

        // Test for Credit Card Properties
        creditCard.setCreateOrModified( null );

        try {
            creditCardDomain2 = CreditCardDomainDTOConverter.getConvertedDomainFromDTO( null, creditCard );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNull( TestConstants.notNullMessage, illegalArgumentValueException );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDomain2.getCreateOrModified( ) );

        logger.debug( "Finishing test for GetConvertedDomainFromDTOWithNullCreateOrModifiedDate" );
    }

    /**
     * Test get converted dTO from domain for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        creditCardDTO2 = CreditCardDomainDTOConverter.getConvertedDTOFromDomain( null, creditCardDomain );
        Assert.assertNotNull( TestConstants.nullMessage, creditCardDTO2 );
        NullPointerException nullPointerException = null;
        try {
            creditCardDTO2 = CreditCardDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }

    /**
     * Need to fix this method, break it into different units todo make a class with separate tests for this complex
     * test todo also make the date dynamic is something to look for
     *
     * @throws Exception the exception
     */
    @Test
    public void testIsValidCardExpiryDate( ) throws Exception {

        logger.debug( "Starting test for IsValidCardExpiryDate" );

        String expiryDate = "12:18";
        Assert.assertTrue( TestConstants.falseMessage, CreditCardDomainDTOConverter.isValidCardExpiryDate( expiryDate
        ) );
        expiryDate = "12:10";
        Assert.assertFalse( TestConstants.trueMessage, CreditCardDomainDTOConverter.isValidCardExpiryDate( expiryDate
        ) );
        expiryDate = "13:18";
        Assert.assertFalse( TestConstants.trueMessage, CreditCardDomainDTOConverter.isValidCardExpiryDate( expiryDate
        ) );
        expiryDate = "133:13";
        Assert.assertFalse( TestConstants.trueMessage, CreditCardDomainDTOConverter.isValidCardExpiryDate( expiryDate
        ) );
        Calendar calendar = Calendar.getInstance( );
        expiryDate = calendar.get( Calendar.MONTH ) + ":" + ( calendar.get( Calendar.YEAR ) % 100 );
        Assert.assertTrue( TestConstants.falseMessage, CreditCardDomainDTOConverter.isValidCardExpiryDate( expiryDate
        ) );
        logger.debug( "Finishing test for IsValidCardExpiryDate" );
    }

    /**
     * Need to fix this method, break it into different units todo make a class with separate tests for this complex
     * test
     *
     * @throws Exception the exception
     */
    @Test( expected = Exception.class )
    public void testValidateCVVWithCardType( ) throws Exception {

        logger.debug( "Starting test for ValidateCVVWithCardType" );

        Exception exception = null;
        CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );

        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV + TestConstants.TWO );
            creditCardDTO.setCardType( CardType.MasterCard.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV );
            creditCardDTO.setCardType( CardType.MasterCard.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
            fail( "Exception occurred" );
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV + TestConstants.TWO );
            creditCardDTO.setCardType( CardType.Visa.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV );
            creditCardDTO.setCardType( CardType.Visa.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
            fail( "Exception occurred" );
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV + TestConstants.TWO );
            creditCardDTO.setCardType( CardType.AmericanExpress.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
            fail( "Exception occurred" );
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV );
            creditCardDTO.setCardType( CardType.AmericanExpress.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV );
            creditCardDTO.setCardType( CardType.Discover.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
            fail( "Exception occurred" );
        }
        try {
            creditCardDTO.setCVV( "" + TestConstants.TEST_CVV + TestConstants.TWO );
            creditCardDTO.setCardType( CardType.Discover.toString( ) );
            CreditCardDomainDTOConverter.validateCVVWithCardType( creditCardDTO );
        } catch ( Exception exception1 ) {
            exception = exception1;
        }
        if ( exception != null ) {
            logger.debug( "Finishing test for ValidateCVVWithCardType" );
            throw exception;
        }
    }

    /**
     * Test validate card number with card type.
     *
     * @throws Exception the exception
     */
    @Test( expected = IllegalArgumentValueException.class )
    public void testValidateCardNumberWithCardType( ) throws Exception {

        logger.debug( "Starting test for ValidateCardNumberWithCardType" );

        IllegalArgumentValueException exception1 = null;
        String cardNumber = TestConstants.CC_NUMBER,
                cardType = CardType.Visa.toString( );
        //test Visa with Master
        try {
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
            fail( "Failed Error " );
        }
        // test Visa with Visa
        try {
            cardType = CardType.MasterCard.toString( );
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
        }
        try {
            cardNumber = cardNumber.replaceFirst( "4", "5" );

            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
            fail( "Failed Error " );
        }

        try {
            cardNumber = cardNumber.replaceFirst( "54", "65" );
            cardType = CardType.Discover.toString( );
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
            fail( "Failed Error " );
        }

        try {
            cardType = CardType.Diners.toString( );
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
        }
        try {
            cardNumber = cardNumber.replaceFirst( "65", "38" );
            cardNumber = cardNumber.substring( 0, cardNumber.length( ) - 2 );
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
            fail( "Failed Error" );
        }

        try {
            cardNumber = cardNumber.replaceFirst( "38", "37" );
            cardNumber = cardNumber + 5;
            cardType = CardType.AmericanExpress.toString( );
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
            fail( "Failed Error" );
        }
        try {
            cardType = CardType.JCB.toString( );
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
        }
        try {
            cardNumber = cardNumber.replaceFirst( "37", "35" );
            cardNumber = cardNumber + 5;
            CreditCardDomainDTOConverter.validateCardNumberWithCardType( cardNumber, cardType );
        } catch ( IllegalArgumentValueException exception ) {
            exception1 = exception;
            fail( "Failed Error" );
        }

        logger.debug( "Finishing test for ValidateCardNumberWithCardType" );
        throw exception1;

    }
}