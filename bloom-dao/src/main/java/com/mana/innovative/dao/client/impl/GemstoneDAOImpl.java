package com.mana.innovative.dao.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.client.GemstoneDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The type GemstoneDAOImpl.
 * <p/>
 * Created by Bloom/Rono on 2/13/2016 5:56 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class GemstoneDAOImpl extends BasicDAO implements GemstoneDAO {

	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( GemstoneDAOImpl.class );


//    private static final String HASH = DAOConstants.HASH;
	
	/**
	 * Delete all gemstones.
	 *
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
/* IMP DELETE Functions */
	@Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
	public DAOResponse< Gemstone > deleteAllGemstones( final RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllGemstones()";
		logger.debug( "Starting " + location );
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		gemstoneDAOResponse.setDelete( true );
		gemstoneDAOResponse.setCount( DAOConstants.ZERO );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		if ( requestParams.isDeleteAll( ) ) {
			try {
				this.openDBTransaction( );
				// Note: Will delete all Gemstone class rows stored in DB will not delete anything from shop class
				// Note: and it should not as a shop may or may not have any gemstones so shop class will still persist
				Query query = session.createQuery( "delete from Gemstone" );
				int count = query.executeUpdate( );
				gemstoneDAOResponse.setCount( count );
				gemstoneDAOResponse.setRequestSuccess( true );
				this.closeDBTransaction( );
			} catch ( Exception exception ) {
				if ( exception instanceof HibernateException ) {
					this.handleExceptions( ( HibernateException ) exception );
				}
				logger.error( "Error occurred while trying to clear gemstones table " + location, exception );
				if ( requestParams.isError( ) ) {
					errorContainer = this.fillErrorContainer( location, exception );
				}
				gemstoneDAOResponse.setRequestSuccess( false );
			}
		}
		
		gemstoneDAOResponse.setResults( null );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return gemstoneDAOResponse;
	}
	
	/**
	 * Delete gemstone by gemstone id.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return Returns a boolean value to indicate a successful deletion
	 */
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
	public DAOResponse< Gemstone > deleteGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteGemstoneByGemstoneId()";
		logger.debug( "Starting " + location );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		gemstoneDAOResponse.setDelete( true );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		try {
			this.openDBTransaction( );
			Query query = session.createQuery( "delete from Gemstone where gemstoneId=:gemstoneId" );
			query.setParameter( "gemstoneId", gemstoneId );
			gemstoneDAOResponse.setCount( query.executeUpdate( ) );
			this.closeDBTransaction( );
			gemstoneDAOResponse.setRequestSuccess( true );
			
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to delete an gemstone " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			gemstoneDAOResponse.setRequestSuccess( false );
		}
		
		gemstoneDAOResponse.setResults( null );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return gemstoneDAOResponse;
	}
	
	/**
	 * Delete gemstones by gemstone ids.
	 *
	 * @param gemstoneIds the gemstone ids
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	@Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
	public DAOResponse< Gemstone > deleteGemstonesByGemstoneIds( List< Long > gemstoneIds, RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteGemstonesByGemstoneIds()";
		logger.debug( "Starting " + location );
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		gemstoneDAOResponse.setDelete( true );
		gemstoneDAOResponse.setCount( DAOConstants.ZERO );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		try {
			this.openDBTransaction( );
			Query query = session.createQuery( "DELETE from Gemstone where gemstoneId in(:gemstoneIds)" );
			query.setParameterList( "gemstoneIds", gemstoneIds );
			gemstoneDAOResponse.setCount( query.executeUpdate( ) );
			gemstoneDAOResponse.setRequestSuccess( true );
			this.closeDBTransaction( );
			
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to delete gemstones " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			gemstoneDAOResponse.setRequestSuccess( false );
		}
		
		gemstoneDAOResponse.setResults( null );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return gemstoneDAOResponse;
	}
   /* IMP UPDATE Functions */
	
	/**
	 * This method is to update the DB with the persistence layer to keep the Gemstone value synced
	 *
	 * @param gemstone the gemstone
	 * @param requestParams the request params
	 *
	 * @return Returns a boolean value to indicate a successful update
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
	public DAOResponse< Gemstone > updateGemstone( Gemstone gemstone, RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateGemstone()";
		logger.debug( "Starting " + location );
		List< Gemstone > gemstones = new ArrayList<>( );
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		gemstoneDAOResponse.setUpdate( true );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		try {
			if ( gemstone.getGemstoneId( ) < 1 ) {
				throw new IllegalArgumentValueException( "Gemstone Id is invalid: " + gemstone.getGemstoneId( ) );
			}
			
			this.openDBTransaction( );
			session.update( gemstone );
			this.closeDBTransaction( );
			
			gemstoneDAOResponse.setCount( DAOConstants.ONE );
			gemstoneDAOResponse.setRequestSuccess( Boolean.TRUE );
			gemstones.add( gemstone );
			
		} catch ( Exception exception ) {
			
			exception.printStackTrace( );
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to update an gemstone " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			gemstoneDAOResponse.setRequestSuccess( Boolean.FALSE );
			gemstoneDAOResponse.setCount( DAOConstants.ZERO );
		}
		gemstoneDAOResponse.setResults( gemstones );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		
		logger.debug( "Finishing " + location );
		
		return gemstoneDAOResponse;
	}

    /* IMP CREATE Functions */
	
	/**
	 * This method is to create a Gemstone object and save it in the DB
	 *
	 * @param gemstone the gemstone
	 * @param requestParams the request params
	 *
	 * @return Returns a boolean value to indicate a successful creation
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
	public DAOResponse< Gemstone > createGemstone( Gemstone gemstone, RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createGemstone()";
		logger.debug( "Starting " + location );
		List< Gemstone > gemstones = new ArrayList<>( );
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		gemstoneDAOResponse.setCreate( true );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		try {
			this.openDBTransaction( );
			session.save( gemstone );
			this.closeDBTransaction( );
			
			gemstoneDAOResponse.setCount( DAOConstants.ONE );
			gemstoneDAOResponse.setRequestSuccess( true );
			gemstones.add( gemstone );
			
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to create an gemstone " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			gemstoneDAOResponse.setRequestSuccess( Boolean.FALSE );
			gemstoneDAOResponse.setCount( DAOConstants.ZERO );
		}
		gemstoneDAOResponse.setResults( gemstones );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return gemstoneDAOResponse;
	}
	
	
	/**
	 * Gets gemstone by gemstone id.
	 *
	 * @param gemstoneId the gemstone id
	 * @param requestParams the request params
	 *
	 * @return the gemstone by gemstone id
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
	public DAOResponse< Gemstone > getGemstoneByGemstoneId( long gemstoneId, RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstoneByGemstoneId()";
		logger.debug( "Starting " + location );
		
		List< Gemstone > gemstones = null;
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		try {
			this.openDBTransaction( );
			Query query = session.createQuery( " from Gemstone where gemstoneId" + " = :gemstone_id" );
			query.setLong( "gemstone_id", gemstoneId );
//            transaction.commit();
			gemstones = query.list( );
			this.closeDBTransaction( );
			if ( !gemstones.isEmpty( ) && gemstones.size( ) > DAOConstants.ONE ) {
				throw new IllegalSearchListSizeException( " Gemstone Size exceeded maximum value " +
						"of " + DAOConstants.ONE );
			}
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to fetch data from gemstones table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		gemstoneDAOResponse.setCount( gemstones == null ? DAOConstants.ZERO : gemstones.size( ) );
		gemstoneDAOResponse.setResults( gemstones );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		
		logger.debug( "Finishing " + location );
		
		return gemstoneDAOResponse;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
	public DAOResponse< Gemstone > getGemstoneByGemstoneName( String gemstoneName, RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstoneByGemstoneName()";
		logger.debug( "Starting " + location );

		List< Gemstone > gemstones = null;
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );
			Query query = session.createQuery( " from Gemstone where gemstoneName" + " = :gemstone_name" );
			query.setString( "gemstone_name", gemstoneName );
//            transaction.commit();
			gemstones = query.list( );
			this.closeDBTransaction( );
			if ( !gemstones.isEmpty( ) && gemstones.size( ) > DAOConstants.ONE ) {
				throw new IllegalSearchListSizeException( " Gemstone Size exceeded maximum value " +
						"of " + DAOConstants.ONE );
			}
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to fetch data from gemstones table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		gemstoneDAOResponse.setCount( gemstones == null ? DAOConstants.ZERO : gemstones.size( ) );
		gemstoneDAOResponse.setResults( gemstones );
		gemstoneDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );

		return gemstoneDAOResponse;
	}

	/**
	 * This method is to retrieve all the gemstones values from the DB
	 *
	 * @param requestParams the request params
	 *
	 * @return List<Gemstone>    </> Return a list of
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
	public DAOResponse< Gemstone > getGemstones( RequestParams requestParams ) {
		
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstones()";
		logger.debug( "Starting " + location );
		
		List< Gemstone > gemstones = null;
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
		
		try {
			this.openDBTransaction( );

			String tableName = "Gemstone";
			Query query = session.createQuery( this.gePageQuery( requestParams, tableName ) );
			// Note Page size cannot be negative and endlimit must be null for page size to be applied
			// Note otherwise ignore it
			if ( requestParams.getPageSize( ) != null && requestParams.getPageSize( ) > 0 && requestParams.getEndLimit( )
					== null ) {
				query.setMaxResults( requestParams.getPageSize( ) );
			}

			this.closeDBTransaction( );
		} catch ( HibernateException exception ) {
			this.handleExceptions( exception );
			logger.error( "Error occurred while trying to fetch data from gemstones table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		gemstoneDAOResponse.setCount( gemstones == null ? DAOConstants.ZERO : gemstones.size( ) );
		gemstoneDAOResponse.setResults( gemstones );
		gemstoneDAOResponse.setErrorContainer( errorContainer );
		
		logger.debug( "Finishing " + location );
		return gemstoneDAOResponse;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
	public DAOResponse< Gemstone > getGemstonesByParams( final String key, final List values, RequestParams requestParams
	) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getGemstonesByParams()";
		logger.debug( "Starting " + location );

		List< Gemstone > gemstones = null;
		DAOResponse< Gemstone > gemstoneDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );

			Query query = session.
					createQuery( " from Gemstone where " + key.substring(
							key.indexOf( ServiceConstants.DOT ) + 1 ) + " in " + "(:value)" );
			query.setParameterList( "value", values );
			gemstones = query.list( );
			gemstoneDAOResponse.setRequestSuccess( Boolean.TRUE );

			this.closeDBTransaction( );
		} catch ( HibernateException exception ) {
			this.handleExceptions( exception );
			logger.error( "Error occurred while trying to fetch data from gemstones table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		gemstoneDAOResponse.setCount( gemstones == null ? DAOConstants.ZERO : gemstones.size( ) );
		gemstoneDAOResponse.setResults( gemstones );
		gemstoneDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );
		return gemstoneDAOResponse;
	}
	
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
	public void setSessionFactory( SessionFactory sessionFactory ) {
		
		this.sessionFactory = sessionFactory;
	}
}