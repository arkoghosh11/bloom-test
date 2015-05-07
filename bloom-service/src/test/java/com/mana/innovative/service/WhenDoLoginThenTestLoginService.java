package com.mana.innovative.service;/**
 * Created by alex1 on 1/30/2015. This is a class for .. todo
 */

import com.mana.innovative.authentication.LoginService;
import com.mana.innovative.constants.TestConstants;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Bloom on 1/30/2015 : 12:20 AM
 * todo This class is for ...
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/service-config-test.xml", "/db-config-test.xml" } )
public class WhenDoLoginThenTestLoginService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenDoLoginThenTestLoginService.class );

    /**
     * The Login service.
     */
    @Resource
    private LoginService loginService;

    /**
     * Set up.
     */
    @Before
    public void setUp( ) {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
    }

    /**
     * Test login service object.
     */
    @Test
    public void testLoginServiceObject( ) {

        Assert.assertNotNull( "Login Service is null", loginService );
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown( ) {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }
}
