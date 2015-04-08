package com.mana.innovative.dao.consumer.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.consumer.CustomerDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.domain.common.Phone;
import com.mana.innovative.domain.consumer.CreditCard;
import com.mana.innovative.domain.consumer.Customer;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
    private static final Logger logger = Logger.getLogger( CustomerDAOImpl.class );

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
     * @param customer      the customer
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Customer > createCustomer( Customer customer, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCustomer()";
        logger.debug( "Starting " + location );

        Address customerShippingAddress = customer.getShippingAddress( );
        List< CreditCard > customerCreditCards = customer.getCards( );
        List< Phone > phones = customer.getPhones( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.createCustomerAddress( customerShippingAddress );
        } catch ( Exception exception ) {
            logger.error( "Failed to create Address in table address", exception );
            if ( errorContainer != null ) {
                errorContainer.addError( fillErrorContainer( location, exception )
                        .getErrors( )
                        .get( DAOConstants.ZERO ) );
            }
            throw exception;
        }
        for ( CreditCard creditCard : customerCreditCards ) {
            try {
                this.createCard( creditCard );
            } catch ( Exception exception ) {
                logger.error( "Failed to create credit cards in table cards", exception );
                if ( errorContainer != null ) {
                    errorContainer.addError( fillErrorContainer( location, exception )
                            .getErrors( )
                            .get( DAOConstants.ZERO ) );
                }
                throw exception;
            }
        }

        for ( Phone phone : phones ) {
            try {
                this.createPhone( phone );
            } catch ( Exception exception ) {
                logger.error( "Failed to create phone in table phones", exception );
                if ( errorContainer != null ) {
                    errorContainer.addError( fillErrorContainer( location, exception )
                            .getErrors( )
                            .get( DAOConstants.ZERO ) );
                }
                throw exception;
            }
        }

        DAOResponse< User > userDAOResponse = super.createUser( customer, requestParams );
        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );

        customerDAOResponse.setRequestSuccess( userDAOResponse.isRequestSuccess( ) );
        customerDAOResponse.setCreate( userDAOResponse.isCreate( ) );
        customerDAOResponse.setCount( userDAOResponse.getCount( ) );
        customerDAOResponse.setErrorContainer( userDAOResponse.getErrorContainer( ) );

        List< Customer > customerList = new ArrayList<>( );
        customerList.add( customer );
        customerDAOResponse.setResults( customerList );


        logger.debug( "Finishing " + location );

        return customerDAOResponse;
    }


    /**
     * Gets customers.
     *
     * @param requestParams the request params
     *
     * @return the customers
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Customer > getCustomers( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getCustomers()";

        logger.debug( "Starting " + location );
        DAOResponse< Customer > customerDAOResponse = new DAOResponse<>( );
        List< Customer > customerList = new ArrayList<>( );
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

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                customerDAOResponse.setErrorContainer( errorContainer );
            }
        }

        customerDAOResponse.setCount( customerList.size( ) );
        customerDAOResponse.setResults( customerList );

        logger.debug( "Finishing " + location );
        return customerDAOResponse;
    }

    /**
     * Delete customer.
     *
     * @param customerId the customer id
     *
     * @return the boolean
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Customer > deleteCustomer( Long customerId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteCustomer()";

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


    /**
     * Create customer address.
     *
     * @param customerAddress the customer address
     *
     * @return the boolean
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    private Address createCustomerAddress( Address customerAddress ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCustomerAddress()";
        logger.debug( "Starting " + location );

        try {
            this.openDBTransaction( );
            session.save( customerAddress );
            this.closeDBTransaction( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for address table", exception );
            throw exception;

        }

        logger.debug( "Finishing " + location );

        return customerAddress;
    }

    /**
     * Create card.
     *
     * @param customerCreditCard the card
     *
     * @return the boolean
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    private CreditCard createCard( final CreditCard customerCreditCard ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createCard()";
        logger.debug( "Starting " + location );

        try {
            this.openDBTransaction( );
            session.save( customerCreditCard );
            this.closeDBTransaction( );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for cards table", exception );
            throw exception;

        }
        logger.debug( "Finishing " + location );
        return customerCreditCard;
    }

    /**
     * Create phone.
     *
     * @param customerPhone the phone
     *
     * @return the phone
     */
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    private Phone createPhone( final Phone customerPhone ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createPhone()";
        logger.debug( "Starting " + location );

        try {
            this.openDBTransaction( );
            session.save( customerPhone );
            this.closeDBTransaction( );
        } catch ( Exception exception ) {

            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Exception occurred while creating data for phones table", exception );
            throw exception;

        }
        logger.debug( "Finishing " + location );

        return customerPhone;
    }
}
