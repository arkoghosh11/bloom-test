package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
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

/**
 * Created by Bloom/Rono on 5/4/2015 3:07 PM. This class WhenCreateCardThenTestCardDAOCreateMethods is a test class
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateCardThenTestCardDAOCreateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateCardThenTestCardDAOCreateMethods.class );

    /**
     * The Dummy card.
     */
    private Card dummyCard;
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
        dummyCard = TestDummyDomainObjectGenerator.getTestCardDomainObject( );

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
     * Test create card with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCardWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateCardWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Card > cardDAOResponse = cardDAO.createCard( dummyCard, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isRequestSuccess( ) );

        Card card = cardDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, card );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCard, card );

        logger.debug( "Finishing test for CreateCardWithErrorDisabled" );
    }

    /**
     * Test create card with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateCardWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateCardWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Card > cardDAOResponse = cardDAO.createCard( dummyCard, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, cardDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, cardDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, cardDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, cardDAOResponse.isRequestSuccess( ) );

        Card card = cardDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, card );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyCard, card );

        logger.debug( "Finishing test for CreateCardWithErrorEnabled" );
    }
}