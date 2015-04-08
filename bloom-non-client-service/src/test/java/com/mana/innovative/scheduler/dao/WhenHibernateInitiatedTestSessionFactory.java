package com.mana.innovative.scheduler.dao;

import junit.framework.Assert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by alex1 on 1/23/2015. This is a domain class
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
public class WhenHibernateInitiatedTestSessionFactory {

    @Resource
    SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger( WhenHibernateInitiatedTestSessionFactory.class );

    public void setUp( ) {
        logger.debug( "Initiating Test" );
    }

    /**
     * This method is to test is Session was instantiated through spring context
     */
    @Test
    public void testSessionFactory( ) {

        logger.debug( "Performing Test" );
        Assert.assertNotNull( sessionFactory );
        Session session = sessionFactory.openSession( );
        Assert.assertNotNull( session );
        try {
            session.close( );
        } catch ( HibernateException e ) {
            logger.error( e.getMessage( ), e );
            Assert.fail( "Exception Occurred" );
        }
    }

    @After
    public void close( ) {

//        logger.debug("Shutting Down Test");
//        sessionFactory.close();
    }
}
