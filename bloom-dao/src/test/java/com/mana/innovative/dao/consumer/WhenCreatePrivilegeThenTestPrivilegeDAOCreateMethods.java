package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
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
 * Created by Bloom/Rono on 5/15/2015 12:21 PM. This class WhenCreatePrivilegeThenTestPrivilegeDAOMethods is a test
 * class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreatePrivilegeThenTestPrivilegeDAOCreateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreatePrivilegeThenTestPrivilegeDAOCreateMethods.class );

    /**
     * The Dummy privilege.
     */
    private Privilege dummyPrivilege;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The Privilege dAO.
     */
    @Resource
    private PrivilegeDAO privilegeDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyPrivilege = TestDummyDomainObjectGenerator.getTestPrivilegeDomainObject( );

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
     * Test create privilege with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreatePrivilegeWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreatePrivilegeWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< Privilege > privilegeDAOResponse = privilegeDAO.createPrivilege( dummyPrivilege, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, privilegeDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.isRequestSuccess( ) );

        Privilege privilege = privilegeDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, privilege );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyPrivilege, privilege );

        logger.debug( "Finishing test for CreatePrivilegeWithErrorDisabled" );
    }

    /**
     * Test create privilege with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreatePrivilegeWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreatePrivilegeWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< Privilege > privilegeDAOResponse = privilegeDAO.createPrivilege( dummyPrivilege, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, privilegeDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, privilegeDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, privilegeDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, privilegeDAOResponse.isRequestSuccess( ) );

        Assert.assertFalse( TestConstants.trueMessage, privilegeDAOResponse.getResults( ).isEmpty( ) );

        Privilege privilege = privilegeDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, privilege );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyPrivilege, privilege );

        logger.debug( "Finishing test for CreatePrivilegeWithErrorEnabled" );
    }
}