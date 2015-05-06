package com.mana.innovative.dao.common;

import com.mana.innovative.constants.TestConstants;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 6:19 PM. This class WhenGetPhoneDAOThenTestGetPhoneGetMethods is a test class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@Transactional   // If required
public class WhenGetPhoneDAOThenTestGetPhoneGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetPhoneDAOThenTestGetPhoneGetMethods.class );

    /**
     * The Phone dAO.
     */
    @Resource
    private PhoneDAO phoneDAO;
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
     * Test get phones with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPhonesWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetPhonesWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Phone > phoneDAOResponse = phoneDAO.getPhones( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.getResults( ).isEmpty( ) );
        // phone list and its size with DAOResponse<T> class count
        List< Phone > phones = phoneDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, phones );
        Assert.assertFalse( TestConstants.trueMessage, phones.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDAOResponse.getCount( ), phones.size( ) );

        for ( Phone phone : phones ) {
            Assert.assertNotNull( TestConstants.nullMessage, phone );
        }

        logger.debug( "Finishing test for GetPhonesWithErrorDisabled" );
    }

    /**
     * Test get phone with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPhoneWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetPhoneWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Phone > phoneDAOResponse = phoneDAO.getPhone( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, phoneDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.getResults( ).isEmpty( ) );
        // phone list and its size with DAOResponse<T> class count
        List< Phone > phones = phoneDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, phones );
        Assert.assertFalse( TestConstants.trueMessage, phones.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDAOResponse.getCount( ), phones.size( ) );
        Assert.assertEquals( TestConstants.ONE, phones.size( ) );
        // test phone
        Phone phone = phones.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, phone );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneCarrier( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneNumber( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneType( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneModel( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getBoughtDate( ) );

        Assert.assertNotNull( TestConstants.nullMessage, phone.getCustomerPhone( ) );

        Assert.assertNotNull( TestConstants.nullMessage, phone.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetPhoneWithErrorDisabled" );
    }

    /**
     * Test get phones with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPhonesWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetPhonesWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Phone > phoneDAOResponse = phoneDAO.getPhones( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.getResults( ).isEmpty( ) );

        // phone list and its size with DAOResponse<T> class count
        List< Phone > phones = phoneDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, phones );
        Assert.assertFalse( TestConstants.trueMessage, phones.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDAOResponse.getCount( ), phones.size( ) );

        for ( Phone phone : phones ) {
            Assert.assertNotNull( TestConstants.nullMessage, phone );
        }

        logger.debug( "Finishing test for GetPhonesWithErrorEnabled" );
    }

    /**
     * Test get phone with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPhoneWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetPhoneWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Phone > phoneDAOResponse = phoneDAO.getPhone( TestConstants.TEST_ID, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, phoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, phoneDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, phoneDAOResponse.getResults( ).isEmpty( ) );

        // phone list and its size with DAOResponse<T> class count
        List< Phone > phones = phoneDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, phones );
        Assert.assertFalse( TestConstants.trueMessage, phones.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, phoneDAOResponse.getCount( ), phones.size( ) );
        Assert.assertEquals( TestConstants.ONE, phones.size( ) );

        // test phone
        Phone phone = phones.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, phone );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneName( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneCarrier( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneNumber( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneType( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getPhoneModel( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getBoughtDate( ) );

        Assert.assertNotNull( TestConstants.nullMessage, phone.getCustomerPhone( ) );

        Assert.assertNotNull( TestConstants.nullMessage, phone.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, phone.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetPhoneWithErrorEnabled" );
    }
}