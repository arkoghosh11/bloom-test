package com.mana.innovative.dao.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.AddressDAO;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Address;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Address dAO impl.
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class AddressDAOImpl extends BasicDAO implements AddressDAO {

    private static final Logger logger = Logger.getLogger( AddressDAOImpl.class );

    /**
     * Gets address by address id.
     *
     * @param addressId the address id
     * @param isError   the is error
     *
     * @return the address by address id
     */
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Address > getAddressByAddressId( long addressId, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getAddressByAddressId()";
        logger.debug( "Starting " + location );

        List< Address > address = null;
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Address where addressId" + " = :address_id" );
            query.setLong( "address_id", addressId );
//            transaction.commit();
            address = query.list( );
            if ( !address.isEmpty( ) && address.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " Address Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            exception.printStackTrace( );
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from items table for " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        addressDAOResponse.setCount( address == null ? DAOConstants.ZERO : address.size( ) );
        addressDAOResponse.setResults( address );
        addressDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

    /**
     * This method is to retrieve all the address values from the DB
     *
     * @return List<Address></> Return a list of {@link Address}
     */
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Address > getAddress( boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getAddress()";
        logger.debug( "Starting " + location );

        List< Address > address = null;
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            address = session.createQuery( " from Address" ).list( );
//            transaction.commit();
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from address table for " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        addressDAOResponse.setCount( address == null ? DAOConstants.ZERO : address.size( ) );
        addressDAOResponse.setResults( address );
        addressDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return addressDAOResponse;
    }

    /**
     * Delete working hour by working hr ids.
     *
     * @param addressId the working hour id
     * @param isError   the is error
     *
     * @return the dAO response
     */
    @Override
    public DAOResponse< Address > deleteAddressByAddressId( final long addressId, final boolean
            isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAddressByAddressId()";
        logger.debug( "Starting " + location );

        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from Address where addressId=:addressId" );
            query.setParameter( "addressId", addressId );
            addressDAOResponse.setCount( query.executeUpdate( ) );
            addressDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to delete address", exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        addressDAOResponse.setResults( null );
        addressDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return null;
    }

    /**
     * Delete working hours by working hr ids.
     *
     * @param addressIds the working hour ids
     * @param isError    the is error
     *
     * @return the dAO response
     */
    @Override
    public DAOResponse< Address > deleteAddressesByAddressIds( List< Long > addressIds, boolean isError ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAddressesByAddressIds()";
        logger.debug( "Starting " + location );
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !isError ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from Address where addressId in (:addressIds)" );
            query.setParameterList( "addressIds", addressIds );
            addressDAOResponse.setCount( query.executeUpdate( ) );
            addressDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            log.error( "Failed to delete address with given ids " + location, exception );
            if ( isError ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            addressDAOResponse.setRequestSuccess( false );
        } finally {
            this.closeDBTransaction( );
        }
        addressDAOResponse.setResults( null );
        addressDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return null;
    }

}
