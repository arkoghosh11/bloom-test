package com.mana.innovative.dao.common;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.hibernate.HibernateException;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * This class is a test class for testing class todo...
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteAddressThenTestAddressDAODeleteMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDeleteAddressThenTestAddressDAODeleteMethods.class );

    /**
     * The Address dAO impl.
     */
    @Resource
    private AddressDAO addressDAOImpl;
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
        try {
            Query query = session.createQuery( " from Address where addressId=:address_id" );
            query.setLong( "address_id", DAOConstants.ZERO );
            List< Address > addresses = query.list( );
            Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", addresses.isEmpty( ) );
        } catch ( HibernateException exception ) {
            fail( "Hibernate issue occurred while trying to verify data wasnt modified during test" );
        } finally {
            if ( session != null ) {
                session.flush( );
                session.close( );
            }
        }
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    /**
     * Test address dAO not null.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAddressDAONotNull( ) throws Exception {

        logger.debug( "Starting test for AddressDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOImpl );
        logger.debug( "Finishing test for AddressDAONotNull" );
    }

    /**
     * Test delete address by address id error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressByAddressIdErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressByAddressIdErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl
                .deleteAddressByAddressId( testId, requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( addressDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAddressByAddressIdErrorEnabled" );
    }

    /**
     * Test delete address by address id error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressByAddressIdErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressByAddressIdErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl
                .deleteAddressByAddressId( testId, requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAddressByAddressIdErrorDisabled" );
    }

    /**
     * Test delete addresses by address ids error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressesByAddressIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressesByAddressIdsErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl
                .deleteAddressesByAddressIds( testIds, requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( addressDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAddressesByAddressIdsErrorEnabled" );
    }

    /**
     * Test delete addresses by address ids error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressesByAddressIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressesByAddressIdsErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl
                .deleteAddressesByAddressIds( testIds, requestParams );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAddressesByAddressIdsErrorDisabled" );
    }

    /**
     * Test delete all address with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( addressDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllAddressWithErrorEnabled" );
    }

    /**
     * Test delete all address with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_FALSE );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertFalse( TestConstants.trueMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ZERO, addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllAddressWithErrorDisabled" );
    }

    /**
     * Test delete all address with delete all true with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithDeleteAllTrueWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // check ErrorContainer
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( addressDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllAddressWithDeleteAllTrueWithErrorEnabled" );
    }

    /**
     * Test delete all address with delete all true error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithDeleteAllTrueWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        requestParams.setIsDeleteAll( TestConstants.TEST_TRUE );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, addressDAOResponse );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );
        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.getCount( ) > TestConstants.ONE );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAllAddressWithDeleteAllTrueWithErrorDisabled" );
    }
}