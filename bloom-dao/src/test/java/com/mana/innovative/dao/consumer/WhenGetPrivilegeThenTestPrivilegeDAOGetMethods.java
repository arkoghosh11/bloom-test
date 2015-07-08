package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Privilege;
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
 * Created by Bloom/Rono on 5/15/2015 12:20 PM. This class WhenGetPrivilegeThenTestPrivilegeDAOMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetPrivilegeThenTestPrivilegeDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetPrivilegeThenTestPrivilegeDAOGetMethods.class );

    /**
     * The Privilege dAO.
     */
    @Resource
    private PrivilegeDAO privilegeDAO;
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
     * Test get privileges with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPrivilegesWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetPrivilegesWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Privilege > privilegeDAOResponse = privilegeDAO.getPrivileges( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, privilegeDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.getResults( ).isEmpty( ) );
        // privilege list and its size with DAOResponse<T> class count
        List< Privilege > privileges = privilegeDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, privileges );
        Assert.assertFalse( TestConstants.trueMessage, privileges.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDAOResponse.getCount( ), privileges.size( ) );

        for ( Privilege privilege : privileges ) {
            Assert.assertNotNull( TestConstants.nullMessage, privilege );
        }

        logger.debug( "Finishing test for GetPrivilegesWithErrorDisabled" );
    }

    /**
     * Test get privilege with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPrivilegeWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetPrivilegeWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Privilege > privilegeDAOResponse = privilegeDAO.getPrivilegeByPrivilegeId( TestConstants.ONE,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, privilegeDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.getResults( ).isEmpty( ) );
        // privilege list and its size with DAOResponse<T> class count
        List< Privilege > privileges = privilegeDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, privileges );
        Assert.assertFalse( TestConstants.trueMessage, privileges.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDAOResponse.getCount( ), privileges.size( ) );
        Assert.assertEquals( TestConstants.ONE, privileges.size( ) );
        // test privilege
        Privilege privilege = privileges.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, privilege );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getPrivilegeId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getPrivilegeId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getPrivilegeName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, privilege.getUserRoles( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilege.getUserRoles( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, privilege.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetPrivilegeWithErrorDisabled" );
    }

    /**
     * Test get privileges with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPrivilegesWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetPrivilegesWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Privilege > privilegeDAOResponse = privilegeDAO.getPrivileges( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.getResults( ).isEmpty( ) );

        // privilege list and its size with DAOResponse<T> class count
        List< Privilege > privileges = privilegeDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, privileges );
        Assert.assertFalse( TestConstants.trueMessage, privileges.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDAOResponse.getCount( ), privileges.size( ) );

        for ( Privilege privilege : privileges ) {
            Assert.assertNotNull( TestConstants.nullMessage, privilege );
        }

        logger.debug( "Finishing test for GetPrivilegesWithErrorEnabled" );
    }

    /**
     * Test get privilege with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetPrivilegeWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetPrivilegeWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Privilege > privilegeDAOResponse = privilegeDAO.getPrivilegeByPrivilegeId( TestConstants.ONE,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.getResults( ).isEmpty( ) );

        // privilege list and its size with DAOResponse<T> class count
        List< Privilege > privileges = privilegeDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, privileges );
        Assert.assertFalse( TestConstants.trueMessage, privileges.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, privilegeDAOResponse.getCount( ), privileges.size( ) );
        Assert.assertEquals( TestConstants.ONE, privileges.size( ) );

        // test privilege
        Privilege privilege = privileges.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, privilege );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getPrivilegeId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getPrivilegeId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getPrivilegeName( ) );

        Assert.assertNotNull( TestConstants.nullMessage, privilege.getUserRoles( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilege.getUserRoles( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, privilege.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, privilege.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetPrivilegeWithErrorEnabled" );
    }
}