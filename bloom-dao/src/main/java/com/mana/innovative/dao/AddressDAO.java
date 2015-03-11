package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Address;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bloom/Rono on 3/3/2015. This class is for .. ToDo
 */
@Repository
@Transactional
public class AddressDAO extends BasicDAO {

    private static final Logger log = Logger.getLogger( AddressDAO.class );

    static {
        log.setLevel( Level.INFO );
    }

    /**
     * Gets address by address id.
     *
     * @param addressId the address id
     * @param isError   the is error
     *
     * @return the address by address id
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Address > getAddressByAddressId( long addressId, boolean isError ) {

        List< Address > address = null;
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;

        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }

        log.info( "**Inside itemDAO.getAddress()***" );
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
            log.error( "Error occurred while trying to fetch data from items table for addressDAO" + ".getItems()",
                    exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#getAddressByAddressId()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        addressDAOResponse.setCount( address == null ? 0 : address.size( ) );
        addressDAOResponse.setResults( address );
        addressDAOResponse.setErrorContainer( errorContainer );
        return addressDAOResponse;
    }

    /**
     * This method is to retrieve all the address values from the DB
     *
     * @return List<Address></> Return a list of {@link Address}
     */

    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Address > getAddress( boolean isError ) {

        List< Address > address = null;
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = null;

        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }

        log.info( "**Inside addressDAO.getAddress()***" );
        try {
            this.openDBTransaction( );
            address = ( List< Address > ) session.createQuery( " from Address" ).list( );
//            transaction.commit();
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            log.error( "Error occurred while trying to fetch data from address table for addressDAO" + ".getAddress()",
                    exception );
            if ( isError ) {
                String location = this.getClass( ).getCanonicalName( ) + "#getAddress()";
                errorContainer = this.fillErrorContainer( location, exception );
            }
        } finally {
            this.closeDBTransaction( );
        }
        addressDAOResponse.setCount( address == null ? 0 : address.size( ) );
        addressDAOResponse.setResults( address );
        addressDAOResponse.setErrorContainer( errorContainer );
        return addressDAOResponse;
    }
}
