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
            address = ( List< Address > ) session.createQuery( " from Address" ).list( );
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

}
