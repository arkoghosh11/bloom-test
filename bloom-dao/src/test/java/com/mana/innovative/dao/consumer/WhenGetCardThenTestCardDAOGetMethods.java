package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Card;
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
 * Created by Bloom/Rono on 5/2/2015 6:14 PM. This class WhenGetCardThenTestCardDAOGetMethods is a test class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@Transactional   // If required
public class WhenGetCardThenTestCardDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetCardThenTestCardDAOGetMethods.class );

    /**
     * The Card dAO.
     */
    @Resource
    private CardDAO cardDAO;
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
     * Test get cards with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCardsWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCardsWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Card > cardDAOResponse = cardDAO.getCards( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.getResults( ).isEmpty( ) );
        // card list and its size with DAOResponse<T> class count
        List< Card > cards = cardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, cards );
        Assert.assertFalse( TestConstants.trueMessage, cards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDAOResponse.getCount( ), cards.size( ) );

        for ( Card card : cards ) {
            Assert.assertNotNull( TestConstants.nullMessage, card );
        }

        logger.debug( "Finishing test for GetCardsWithErrorDisabled" );
    }

    /**
     * Test get card with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetCardWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Card > cardDAOResponse = cardDAO.getCardByCardId( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.getResults( ).isEmpty( ) );
        // card list and its size with DAOResponse<T> class count
        List< Card > cards = cardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, cards );
        Assert.assertFalse( TestConstants.trueMessage, cards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDAOResponse.getCount( ), cards.size( ) );
        Assert.assertEquals( TestConstants.ONE, cards.size( ) );
        // test card
        Card card = cards.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, card );
        Assert.assertNotNull( TestConstants.nullMessage, card.getCardId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getCardNumber( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getIssueDate( ) );

        Assert.assertFalse( TestConstants.trueMessage, card.isCardHasCustomerPic( ) );
        System.out.println( card.getPictureLocation( ) );
        Assert.assertTrue( TestConstants.falseMessage, StringUtils.isEmpty( card.getPictureLocation( ).trim( ) ) );

        Assert.assertNotNull( TestConstants.nullMessage, card.getFirstName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getLastName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, card.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetCardWithErrorDisabled" );
    }

    /**
     * Test get cards with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCardsWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCardsWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Card > cardDAOResponse = cardDAO.getCards( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.getResults( ).isEmpty( ) );

        // card list and its size with DAOResponse<T> class count
        List< Card > cards = cardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, cards );
        Assert.assertFalse( TestConstants.trueMessage, cards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDAOResponse.getCount( ), cards.size( ) );

        for ( Card card : cards ) {
            Assert.assertNotNull( TestConstants.nullMessage, card );
        }

        logger.debug( "Finishing test for GetCardsWithErrorEnabled" );
    }

    /**
     * Test get card with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Card > cardDAOResponse = cardDAO.getCardByCardId( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.getResults( ).isEmpty( ) );

        // card list and its size with DAOResponse<T> class count
        List< Card > cards = cardDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, cards );
        Assert.assertFalse( TestConstants.trueMessage, cards.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, cardDAOResponse.getCount( ), cards.size( ) );
        Assert.assertEquals( TestConstants.ONE, cards.size( ) );
        // test card
        Card card = cards.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, card );
        Assert.assertNotNull( TestConstants.nullMessage, card.getCardId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getCardNumber( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getIssueDate( ) );

        Assert.assertFalse( TestConstants.trueMessage, card.isCardHasCustomerPic( ) );
        Assert.assertTrue( TestConstants.falseMessage, StringUtils.isEmpty( card.getPictureLocation( ).trim( ) ) );

        Assert.assertNotNull( TestConstants.nullMessage, card.getFirstName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getLastName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, card.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, card.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetCardWithErrorEnabled" );
    }
}