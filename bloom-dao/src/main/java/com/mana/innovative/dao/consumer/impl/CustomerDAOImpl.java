package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.dao.consumer.CustomerDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Customer;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer dAO impl.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository( value = "customerDAO" )
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class CustomerDAOImpl extends UserDAOImpl implements CustomerDAO {

    /**
     * The constant log.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomerDAOImpl.class );

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;


    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory( ) {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory( final SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create customer.
     *
     * @param customer the customer
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Customer > createCustomer( Customer customer, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCustomer()";
        logger.debug( "Starting " + location );

        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        List< Customer > customerList = new ArrayList<>( );
        customerDAOResponse.setCreate( true );
        try {
            this.openDBTransaction( );
            session.save( customer );
            this.closeDBTransaction( );

            customerList.add( customer );
            customerDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {

            customerDAOResponse.setRequestSuccess( false );
            logger.error( "Failed to create customer in table users", exception );
            if ( errorContainer != null ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        customerDAOResponse.setResults( customerList );
        customerDAOResponse.setCount( customerList.size( ) );
        customerDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customerDAOResponse;
    }

    /**
     * Gets customers.
     *
     * @param requestParams the request params
     * @return the customers
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Customer > getCustomers( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCustomers()";

        logger.debug( "Starting " + location );
        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );
        List< Customer > customerList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Customer " );
            customerList = query.list( );

            this.closeDBTransaction( );
            customerDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from users table for customers ", exception );
            customerDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        customerDAOResponse.setCount( customerList.size( ) );
        customerDAOResponse.setResults( customerList );
        customerDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return customerDAOResponse;
    }

    /**
     * Gets customer.
     *
     * @param customerId the customer id
     * @param requestParams the request params
     * @return the customer
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Customer > getCustomerByUserId( final long customerId, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#getCustomerByUserId()";

        logger.debug( "Starting " + location );
        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );
        List< Customer > customerList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Customer where userId=:customerId" );
            query.setParameter( "customerId", customerId );
            Customer customer = ( Customer ) query.uniqueResult( );

            this.closeDBTransaction( );
            customerList.add( customer );
            customerDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from users table for customers ", exception );
            customerDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        customerDAOResponse.setCount( customerList.size( ) );
        customerDAOResponse.setResults( customerList );
        customerDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customerDAOResponse;
    }

    /**
     * Update customer.
     *
     * @param customer the customer
     * @param requestParams the request params
     * @return the dAO response
     */
//    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Customer > updateCustomer( final Customer customer, final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#updateCustomer()";
        logger.debug( "Starting " + location );

        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );
        List< Customer > customerList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        customerDAOResponse.setUpdate( true );

        try {
            this.openDBTransaction( );
            session.saveOrUpdate( customer );
            this.closeDBTransaction( );
            customerList.add( customer );

            customerDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from users table for customers ", exception );
            customerDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }

        customerDAOResponse.setCount( customerList.size( ) );
        customerDAOResponse.setResults( customerList );
        customerDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return customerDAOResponse;
    }

    /**
     * Delete customer.
     *
     * @param customerId the customer id
     * @param requestParams the request params
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Customer > deleteCustomerByUserId( Long customerId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteCustomerByUserId()";

        logger.debug( "Starting " + location );
        DAOResponse< User > userDAOResponse = super.deleteUserByUserId( customerId, requestParams, "Customer" );
        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );

        customerDAOResponse.setRequestSuccess( userDAOResponse.isRequestSuccess( ) );
        customerDAOResponse.setCreate( userDAOResponse.isCreate( ) );
        customerDAOResponse.setCount( userDAOResponse.getCount( ) );
        customerDAOResponse.setErrorContainer( userDAOResponse.getErrorContainer( ) );
        customerDAOResponse.setResults( null );

        logger.debug( "Finishing " + location );

        return customerDAOResponse;
    }

}
