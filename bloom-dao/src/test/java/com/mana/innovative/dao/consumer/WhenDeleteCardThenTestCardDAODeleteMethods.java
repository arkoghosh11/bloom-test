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

import static org.junit.Assert.fail;

/**
 * Deleted by Bloom/Rono on 5/4/2015 3:07 PM. This class WhenDeleteCardThenTestCardDAODeleteMethods is a test class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteCardThenTestCardDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteCardThenTestCardDAODeleteMethods.class );

    /**
     * The Dummy card.
     */
    private long dummyCard;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Card dAO.
     */
    @Resource
    private CardDAO cardDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyCard = ( long ) TestConstants.ZERO;

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
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        try {
            DAOResponse< Card > cardDAOResponse = cardDAO.getCard( 0, requestParams );
            Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
            Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, cardDAOResponse.getCount( ) );
        } catch ( Exception exception ) {
            logger.error( "Error occurred while trying to test if row was deleted during test", exception );
            fail( "Failed to test if row was deleted during test" );
        }
    }

    /**
     * Test delete card with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCardWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Card > cardDAOResponse = cardDAO.deleteCardByCardId( dummyCard, requestParams );

        Assert.assertNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ) );

        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, cardDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteCardWithErrorDisabled" );
    }

    /**
     * Test delete card with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testDeleteCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Card > cardDAOResponse = cardDAO.deleteCardByCardId( dummyCard, requestParams );

        Assert.assertNotNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isRequestSuccess( ) );

        Assert.assertNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, cardDAOResponse.getCount( ) );

        logger.debug( "Finishing test for DeleteCardWithErrorEnabled" );
    }
}