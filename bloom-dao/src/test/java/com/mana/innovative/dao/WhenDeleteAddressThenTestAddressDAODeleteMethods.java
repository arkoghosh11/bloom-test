package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.common.AddressDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenDeleteAddressThenTestAddressDAODeleteMethods {

    private static final Logger logger = Logger.getLogger( WhenDeleteAddressThenTestAddressDAODeleteMethods.class );

    @Resource
    private AddressDAO addressDAOImpl;
    @Resource
    private SessionFactory sessionFactory;

    private long testId;
    private List< Long > testIds;

    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        testId = TestConstants.TEST_ID;
        testIds = new ArrayList<>( );
        testIds.add( ( long ) TestConstants.ZERO );
        testIds.add( testId );
    }

    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {

        Session session = sessionFactory.openSession( );
        Query query = session.createQuery( " from Address where addressId=:address_id" );
        query.setLong( "address_id", DAOConstants.ZERO );
        List< Address > addresses = query.list( );
        Assert.assertFalse( " List is Empty, Hib deleted the default row Row ", addresses.isEmpty( ) );
        logger.debug( TestConstants.setUpMethodLoggerMsg );

    }

    @Test
    public void testAddressDAONotNull( ) throws Exception {

        logger.debug( "Starting test for AddressDAONotNull" );
        Assert.assertNotNull( TestConstants.nullMessage, addressDAOImpl );
        logger.debug( "Finishing test for AddressDAONotNull" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressByAddressIdErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressByAddressIdErrorEnabled" );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAddressByAddressId( testId, TestConstants.IS_ERROR_TRUE );
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

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressByAddressIdErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressByAddressIdErrorDisabled" );

        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAddressByAddressId( testId, TestConstants.IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, TestConstants.ONE, addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAddressByAddressIdErrorDisabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressesByAddressIdsErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressesByAddressIdsErrorEnabled" );
        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAddressesByAddressIds( testIds, TestConstants
                .IS_ERROR_TRUE );
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

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAddressesByAddressIdsErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAddressesByAddressIdsErrorDisabled" );

        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAddressesByAddressIds( testIds, TestConstants
                .IS_ERROR );
        // check ErrorContainer
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getErrorContainer( ) );

        // check DAOResponse
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, addressDAOResponse.isRequestSuccess( ) );
        Assert.assertEquals( TestConstants.falseMessage, testIds.size( ), addressDAOResponse.getCount( ) );
        Assert.assertNull( TestConstants.notNullMessage, addressDAOResponse.getResults( ) );

        logger.debug( "Finishing test for DeleteAddressesByAddressIdsErrorDisabled" );
    }

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithErrorEnabled" );

        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( TestConstants.IS_DELETE_ALL, TestConstants
                .IS_ERROR_TRUE );
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

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithErrorDisabled" );

        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( TestConstants.IS_DELETE_ALL, TestConstants
                .IS_ERROR );
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

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithDeleteAllTrueWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithDeleteAllTrueWithErrorEnabled" );

        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( TestConstants.IS_DELETE_ALL_TRUE, TestConstants
                .IS_ERROR_TRUE );
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

    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void testDeleteAllAddressWithDeleteAllTrueErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for DeleteAllAddressWithDeleteAllTrueWithErrorDisabled" );

        DAOResponse< Address > addressDAOResponse = addressDAOImpl.deleteAllAddress( TestConstants.IS_DELETE_ALL_TRUE, TestConstants
                .IS_ERROR );
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