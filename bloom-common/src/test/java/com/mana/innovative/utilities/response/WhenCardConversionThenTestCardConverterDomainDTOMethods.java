package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.consumer.Card;
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

/**
 * Created by Bloom/Rono on 4/30/2015 11:18 PM. This class WhenCardConversionThenTestCardConverterDomainDTOMethods is a
 * test class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenCardConversionThenTestCardConverterDomainDTOMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCardConversionThenTestCardConverterDomainDTOMethods.class );

    /**
     * The Card dTO.
     */
    private Card cardDTO, /**
     * The Card dTO 2.
     */
    cardDTO2;
    /**
     * The Card domain.
     */
    private com.mana.innovative.domain.consumer.Card cardDomain,
    /**
     * The Card domain 2.
     */
    cardDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        cardDomain2 = new com.mana.innovative.domain.consumer.Card( );
        cardDTO2 = new Card( );

        // Set Values for tempValues
        cardDTO = TestDummyDTOObjectGenerator.getTestCardDTOObject( );
        cardDomain = TestDummyDomainObjectGenerator.getTestCardDomainObject( );


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

        cardDTO2 = CardDomainDTOConverter.getConvertedDTOFromDomain( cardDTO2, cardDomain );

        Assert.assertNotNull( TestConstants.nullMessage, cardDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDTO, cardDTO2 );

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
        List< Card > cardDTOList;
        List< com.mana.innovative.domain.consumer.Card > cardDomainList = new ArrayList<>( );
        cardDomainList.add( cardDomain );
        if ( cardDomain.getCardId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.setTestCardDomainZEROIDObject( cardDomain );
        }
        cardDTOList = CardDomainDTOConverter.getConvertedListDTOFromDomain( cardDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, cardDTOList );
        Assert.assertFalse( TestConstants.trueMessage, cardDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, cardDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDTO, cardDTOList.get( TestConstants.ZERO ) );


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


        cardDomain2 = CardDomainDTOConverter.getConvertedDomainFromDTO( cardDomain2, cardDTO );
        TestDummyDomainObjectGenerator.setTestCardDomainZEROIDObject( cardDomain );

        Assert.assertNotNull( TestConstants.nullMessage, cardDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDomain, cardDomain2 );

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

        List< Card > cardDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.consumer.Card > cardDomainList;
        cardDTOList.add( cardDTO );
        TestDummyDomainObjectGenerator.setTestCardDomainZEROIDObject( cardDomain );

        cardDomainList = CardDomainDTOConverter.getConvertedListDomainFromDTO( cardDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, cardDomainList );
        Assert.assertFalse( TestConstants.trueMessage, cardDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, cardDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDomain, cardDomainList.get( TestConstants.ZERO ) );


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
        Card card = new Card( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            cardDomain2 = CardDomainDTOConverter.getConvertedDomainFromDTO( null, card );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        illegalArgumentValueException = null;

        try {
            cardDTO.setCardHasCustomerPic( true );
            cardDTO.setPictureLocation( "" );
            cardDomain2 = CardDomainDTOConverter.getConvertedDomainFromDTO( null, cardDTO );
        } catch ( IllegalArgumentValueException exception ) {
            illegalArgumentValueException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            cardDomain2 = CardDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );

        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
    }

    /**
     * Test get converted dTO from domain for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );

        NullPointerException nullPointerException = null;
        cardDomain.setCardHasCustomerPic( TestConstants.TEST_FALSE );
        cardDomain.setPictureLocation( null );
        cardDTO2 = CardDomainDTOConverter.getConvertedDTOFromDomain( null, cardDomain );

        Assert.assertNotNull( TestConstants.nullMessage, cardDTO2.getPictureLocation( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, "", cardDTO2.getPictureLocation( ) );
        try {
            cardDTO2 = CardDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException exception ) {
            nullPointerException = exception;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }

    /**
     * Test is valid card issue date.
     *
     * @throws Exception the exception
     */
    @Test
    public void testIsValidCardIssueDate( ) throws Exception {

        logger.debug( "Starting test for IsValidCardIssueDate" );

        String issueDate = "1:10";
        Assert.assertTrue( TestConstants.falseMessage, CardDomainDTOConverter.isValidCardIssueDate( issueDate ) );
        issueDate = "13:00";
        Assert.assertFalse( TestConstants.trueMessage, CardDomainDTOConverter.isValidCardIssueDate( issueDate ) );
        issueDate = "1:45";
        Assert.assertFalse( TestConstants.trueMessage, CardDomainDTOConverter.isValidCardIssueDate( issueDate ) );

        Calendar calendar = Calendar.getInstance( );
        issueDate = calendar.get( Calendar.MONTH ) + ":" + ( calendar.get( Calendar.YEAR ) % 100 );
        Assert.assertTrue( TestConstants.falseMessage, CardDomainDTOConverter.isValidCardIssueDate( issueDate ) );


        logger.debug( "Finishing test for IsValidCardIssueDate" );
    }

    /**
     * Test is valid card number.
     *
     * @throws Exception the exception
     */
    @Test
    public void testIsValidCardNumber( ) throws Exception {

        logger.debug( "Starting test for IsValidCardNumber" );

        String validNumber = TestConstants.VALID_CC_NUMBER;
        Assert.assertTrue( TestConstants.falseMessage, CardDomainDTOConverter.isValidCardNumber( validNumber ) );
        validNumber = TestConstants.CC_NUMBER;
        Assert.assertFalse( TestConstants.trueMessage, CardDomainDTOConverter.isValidCardNumber( validNumber ) );

        logger.debug( "Finishing test for IsValidCardNumber" );
    }

    /**
     * Test get sum of digits.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetSumOfDigits( ) throws Exception {

        logger.debug( "Starting test for GetSumOfDigits" );

        int number = 34;
        Assert.assertEquals( TestConstants.notEqualsMessage, 7, CardDomainDTOConverter.getSumOfDigits( number ) );
        number = -34;
        Assert.assertEquals( TestConstants.notEqualsMessage, 0, CardDomainDTOConverter.getSumOfDigits( number ) );
        number = 0;
        Assert.assertEquals( TestConstants.notEqualsMessage, 0, CardDomainDTOConverter.getSumOfDigits( number ) );

        logger.debug( "Finishing test for GetSumOfDigits" );
    }
}