package com.mana.innovative.dao;

import junit.framework.Assert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/dbConfig-test.xml"})
@TransactionConfiguration( defaultRollback = false, transactionManager = "transactionManager" )
@Transactional
public class WhenHibernateInitiatedTestSessionFactory {

    /**
     * The Session factory.
     */
    @Resource
    SessionFactory sessionFactory;
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger( WhenHibernateInitiatedTestSessionFactory.class );

    /**
     * Sets up.
     */
    public void setUp() {
         logger.debug("Initiating Test");
    }

    /**
     * This method is to test is Session was instantiated through spring context
     */
    @Test
    @Rollback( value = false )
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testSessionFactory() {

        logger.debug( "Performing Test" );
        Assert.assertNotNull(sessionFactory);
        Session session = sessionFactory.openSession();
        Assert.assertNotNull(session);
        try {
            session.close();
        } catch ( HibernateException exception ) {
            logger.error( exception.getMessage( ), exception );
            Assert.fail("Exception Occurred");
        }
        logger.debug( "Completing Test" );
    }

    /**
     * Close void.
     */
    @After
    public void close() {

//        logger.debug("Shutting Down Test");
//        sessionFactory.close();
    }
}
