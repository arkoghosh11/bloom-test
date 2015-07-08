package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.TestDummyDomainObjectGenerator;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.UserRole;
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
 * Created by Bloom/Rono on 5/15/2015 12:22 PM. This class WhenCreateUserRoleThenTestUserRoleDAOMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
@TransactionConfiguration( defaultRollback = true ) // If required
@Transactional   // If required
public class WhenCreateUserRoleThenTestUserRoleDAOCreateMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateUserRoleThenTestUserRoleDAOCreateMethods.class );

    /**
     * The Dummy userRole.
     */
    private UserRole dummyUserRole;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * The UserRole dAO.
     */
    @Resource
    private UserRoleDAO userRoleDAO;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        dummyUserRole = TestDummyDomainObjectGenerator.getTestUserRoleDomainObject( );

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
     * Test create userRole with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateUserRoleWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateUserRoleWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.createUserRole( dummyUserRole, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertNull( TestConstants.notNullMessage, userRoleDAOResponse.getErrorContainer( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.isRequestSuccess( ) );

        UserRole userRole = userRoleDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, userRole );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyUserRole, userRole );

        logger.debug( "Finishing test for CreateUserRoleWithErrorDisabled" );
    }

    /**
     * Test create userRole with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback( value = true )
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ )
    public void testCreateUserRoleWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateUserRoleWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.createUserRole( dummyUserRole, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );

        Assert.assertNotNull( TestConstants.notNullMessage, userRoleDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.isCreate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.isDelete( ) );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.isRequestSuccess( ) );

        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );

        UserRole userRole = userRoleDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, userRole );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyUserRole, userRole );

        logger.debug( "Finishing test for CreateUserRoleWithErrorEnabled" );
    }
}