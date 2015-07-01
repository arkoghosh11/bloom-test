package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Phone;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/15/2015 7:33 PM. This class WhenDeletePhoneThenTestPhoneDAODeleteMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
//@Transactional   // If required
public class WhenDeletePhoneThenTestPhoneDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeletePhoneThenTestPhoneDAODeleteMethods.class );

    /**
     * The Phone dAO impl.
     */
    @Resource
    private PhoneDAO phoneDAOImpl;
    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * The Request params.
     */
    private RequestParams requestParams;

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
        testIds.add( ( long ) TestConstants.ZERO );
        testIds.add( testId );
        requestParams = new RequestParams( );
        logger.debug( "Test SetUp completed" );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    @SuppressWarnings( "unchecked" )
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Phone where phoneId=:phone_id" );
        query.setLong( "phone_id", testId );
        List< Phone > phones = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", phones.isEmpty( ) );
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test phone dAO not null.
     *
     * @throws Exception the exception
     */
    @Test
    public void testPhoneDAONotNull( ) throws Exception {

        logger.debug( "Starting test for PhoneDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOImpl );
        logger.debug( "Finishing test for PhoneDAONotNull" );
    }

    /**
     * Test delete phone by phone id error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeletePhoneByPhoneIdErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeletePhoneByPhoneIdErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deletePhoneByPhoneId( testId, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, phoneDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeletePhoneByPhoneIdErrorEnabled" );
    }

    /**
     * Test delete phone by phone id error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeletePhoneByPhoneIdErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeletePhoneByPhoneIdErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl
                .deletePhoneByPhoneId( testId, requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, phoneDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeletePhoneByPhoneIdErrorDisabled" );
    }

    /**
     * Test delete phones by phone ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeletePhonesByPhoneIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeletePhonesByPhoneIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deletePhonesByPhoneIds( testIds, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), phoneDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeletePhonesByPhoneIdsErrorEnabled" );
    }

    /**
     * Test delete phones by phone ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeletePhonesByPhoneIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeletePhonesByPhoneIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deletePhonesByPhoneIds( testIds, requestParams );

        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), phoneDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeletePhonesByPhoneIdsErrorDisabled" );
    }

    /**
     * Test delete all phone with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPhoneWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPhoneWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deleteAllPhones( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, phoneDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPhoneWithErrorEnabled" );
    }

    /**
     * Test delete all phone with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPhoneWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPhoneWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );

        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deleteAllPhones( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, phoneDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPhoneWithErrorDisabled" );
    }

    /**
     * Test delete all phone with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPhoneWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPhoneWithDeleteAllTrueWithErrorEnabled" );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deleteAllPhones( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPhoneWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all phone with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllPhoneWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllPhoneWithDeleteAllTrueWithErrorDisabled" );
        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );

        DAOResponse< Phone > phoneDAOResponse = phoneDAOImpl.deleteAllPhones( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.isUpdate( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllPhoneWithDeleteAllTrueWithErrorDisabled" );
    }
}