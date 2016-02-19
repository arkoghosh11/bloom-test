package com.mana.innovative.dao.client.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.client.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.common.SearchOption;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.utilities.date.NumberCommons;
import com.mana.innovative.utilities.date.StringCommons;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Item dAO impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository( value = "itemDAOImpl" )
/**
 *  Back to our example, this time you are concerned about the database security, so you define
 *  your DAO classes this
 *  way:
 *
 *
 * @Code {User DAO
 * @Transactional(Propagation=MANDATORY)
 * class UserDAO{
 * // some CRUD methods
 * } }
 *
 * Meaning that whenever a DAO object, and hence a potential access to db, is created,
 * we need to reassure that the call was made from inside one of our services,
 * implying that a live transaction should exist; otherwise an exception occurs.
 * Therefor the propagation is of type MANDATORY.
 */
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class ItemDAOImpl extends BasicDAO implements ItemDAO {

	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( ItemDAOImpl.class );


	//    private static final String HASH = DAOConstants.HASH;
	private String listProperty;
	private Map< String, List > valuesListMap = new HashMap<>( );
	private Map< String, Object > valueMap = new HashMap<>( );
	/* IMP UPDATE Functions */
	private Map< String, String > keysUsed = new HashMap<>( );

    /* IMP CREATE Functions */

	/**
	 * Delete all items.
	 *
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
/* IMP DELETE Functions */
	@Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
	public DAOResponse< Item > deleteAllItems( final RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllItems()";
		logger.debug( "Starting " + location );
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		itemDAOResponse.setDelete( true );
		itemDAOResponse.setCount( DAOConstants.ZERO );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		if ( requestParams.isDeleteAll( ) ) {
			try {
				this.openDBTransaction( );
				// Note: Will delete all Item class rows stored in DB will not delete anything from shop class
				// Note: and it should not as a shop may or may not have any items so shop class will still persist
				Query query = session.createQuery( "delete from Item" );
				int count = query.executeUpdate( );
				itemDAOResponse.setCount( count );
				itemDAOResponse.setRequestSuccess( true );
				this.closeDBTransaction( );
			} catch ( Exception exception ) {
				if ( exception instanceof HibernateException ) {
					this.handleExceptions( ( HibernateException ) exception );
				}
				logger.error( "Error occurred while trying to clear items table " + location, exception );
				if ( requestParams.isError( ) ) {
					errorContainer = this.fillErrorContainer( location, exception );
				}
				itemDAOResponse.setRequestSuccess( false );
			}
		}

		itemDAOResponse.setResults( null );
		itemDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return itemDAOResponse;
	}

	/**
	 * Delete item by item id.
	 *
	 * @param itemId the item id
	 * @param requestParams the request params
	 *
	 * @return Returns a boolean value to indicate a successful deletion
	 */
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ )
	public DAOResponse< Item > deleteItemByItemId( long itemId, RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemByItemId()";
		logger.debug( "Starting " + location );

		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		itemDAOResponse.setDelete( true );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );
			Query query = session.createQuery( "delete from Item where itemId=:itemId" );
			query.setParameter( "itemId", itemId );
			itemDAOResponse.setCount( query.executeUpdate( ) );
			this.closeDBTransaction( );
			itemDAOResponse.setRequestSuccess( true );

		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to delete an item " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			itemDAOResponse.setRequestSuccess( false );
		}

		itemDAOResponse.setResults( null );
		itemDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return itemDAOResponse;
	}

	/**
	 * Delete items by item ids.
	 *
	 * @param itemIds the item ids
	 * @param requestParams the request params
	 *
	 * @return the dAO response
	 */
	@Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
	public DAOResponse< Item > deleteItemsByItemIds( List< Long > itemIds, RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + "#" + "deleteItemsByItemIds()";
		logger.debug( "Starting " + location );
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		itemDAOResponse.setDelete( true );
		itemDAOResponse.setCount( DAOConstants.ZERO );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );
			Query query = session.createQuery( "DELETE from Item where itemId in(:itemIds)" );
			query.setParameterList( "itemIds", itemIds );
			itemDAOResponse.setCount( query.executeUpdate( ) );
			itemDAOResponse.setRequestSuccess( true );
			this.closeDBTransaction( );

		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to delete items " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			itemDAOResponse.setRequestSuccess( false );
		}

		itemDAOResponse.setResults( null );
		itemDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return itemDAOResponse;
	}

	/**
	 * This method is to update the DB with the persistence layer to keep the Item value synced
	 *
	 * @param item the item
	 * @param requestParams the request params
	 *
	 * @return Returns a boolean value to indicate a successful update
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
	public DAOResponse< Item > updateItem( Item item, RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateItem()";
		logger.debug( "Starting " + location );
		List< Item > items = new ArrayList<>( );
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		itemDAOResponse.setUpdate( true );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			if ( item.getItemId( ) < 1 ) {
				throw new IllegalArgumentValueException( "Item Id is invalid: " + item.getItemId( ) );
			}
			this.openDBTransaction( );
//            Item dbItem = (Item) session.get(Item.class, item.getItemId());
			Query query = session.createQuery( " from Item where itemId=:itemId" );
			query.setParameter( "itemId", item.getItemId( ) );
			items = query.list( );
			if ( items.size( ) != DAOConstants.ONE ) {
				throw new IllegalSearchListSizeException( "Item List Size is different to expected Size" );
			}
			Item dbItem = items.get( DAOConstants.ZERO );

			this.closeDBTransaction( );
			this.updateShopItem( item, dbItem );
			this.openDBTransaction( );
			session.update( dbItem );
			this.closeDBTransaction( );

			itemDAOResponse.setCount( DAOConstants.ONE );
			itemDAOResponse.setRequestSuccess( Boolean.TRUE );
			items.add( item );
		} catch ( Exception exception ) {
			exception.printStackTrace( );
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to update an item " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			itemDAOResponse.setRequestSuccess( Boolean.FALSE );
			itemDAOResponse.setCount( DAOConstants.ZERO );
		}
		itemDAOResponse.setResults( items );
		itemDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );

		return itemDAOResponse;
	}

    /* IMP Get Functions 1st one is special Search by Param Function */

	/**
	 * This method is to create a Item object and save it in the DB
	 *
	 * @param item the item
	 * @param requestParams the request params
	 *
	 * @return Returns a boolean value to indicate a successful creation
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE )
	public DAOResponse< Item > createItem( Item item, RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createItem()";
		logger.debug( "Starting " + location );
		List< Item > items = new ArrayList<>( );
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		itemDAOResponse.setCreate( true );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );
			session.save( item );
			this.closeDBTransaction( );

			itemDAOResponse.setCount( DAOConstants.ONE );
			itemDAOResponse.setRequestSuccess( true );
			items.add( item );

		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to create an item " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
			itemDAOResponse.setRequestSuccess( Boolean.FALSE );
			itemDAOResponse.setCount( DAOConstants.ZERO );
		}
		itemDAOResponse.setResults( items );
		itemDAOResponse.setErrorContainer( errorContainer );
		logger.debug( "Finishing " + location );
		return itemDAOResponse;
	}

	/**
	 * Gets item by item id.
	 *
	 * @param itemId the item id
	 * @param requestParams the request params
	 *
	 * @return the item by item id
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
	public DAOResponse< Item > getItemByItemId( long itemId, RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemByItemId()";
		logger.debug( "Starting " + location );

		List< Item > items = null;
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );
			Query query = session.createQuery( " from Item where itemId" + " = :item_id" );
			query.setLong( "item_id", itemId );
//            transaction.commit();
			items = query.list( );
			this.closeDBTransaction( );
			if ( !items.isEmpty( ) && items.size( ) > DAOConstants.ONE ) {
				throw new IllegalSearchListSizeException( " Item Size exceeded maximum value " +
						"of " + DAOConstants.ONE );
			}
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to fetch data from items table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
		itemDAOResponse.setResults( items );
		itemDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );

		return itemDAOResponse;
	}

	/**
	 * Gets item by item name.
	 *
	 * @param itemName the item name
	 * @param requestParams the request params
	 *
	 * @return the item by item name
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
	public DAOResponse< Item > getItemByItemName( final String itemName, final RequestParams requestParams ) {
		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItemByItemId()";
		logger.debug( "Starting " + location );

		List< Item > items = null;
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );
			Query query = session.createQuery( " from Item where itemName" + " = :item_Name" );
			query.setString( "item_Name", itemName );
//            transaction.commit();
			items = query.list( );
			this.closeDBTransaction( );
			if ( !items.isEmpty( ) && items.size( ) > DAOConstants.ONE ) {
				throw new IllegalSearchListSizeException( " Item Size exceeded maximum value " +
						"of " + DAOConstants.ONE );
			}
		} catch ( Exception exception ) {
			if ( exception instanceof HibernateException ) {
				this.handleExceptions( ( HibernateException ) exception );
			}
			logger.error( "Error occurred while trying to fetch data from items table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
		itemDAOResponse.setResults( items );
		itemDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );

		return itemDAOResponse;
	}

	/**
	 * This method is to retrieve all the items values from the DB
	 *
	 * @param requestParams the request params
	 *
	 * @return List<Item>    </> Return a list of
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	@Transactional( readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
	public DAOResponse< Item > getItems( RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getItems()";
		logger.debug( "Starting " + location );

		List< Item > items = null;
		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

		try {
			this.openDBTransaction( );

			String tableName = "Item";
			Query query = session.createQuery( this.gePageQuery( requestParams, tableName ) );
			// Note Page size cannot be negative and endlimit must be null for page size to be applied
			// Note otherwise ignore it
			if ( requestParams.getPageSize( ) != null && requestParams.getPageSize( ) > 0 && requestParams.getEndLimit( )
					== null ) {
				query.setMaxResults( requestParams.getPageSize( ) );
			}
			items = ( List< Item > ) query.list( );
			this.closeDBTransaction( );
		} catch ( HibernateException exception ) {
			this.handleExceptions( exception );
			logger.error( "Error occurred while trying to fetch data from items table " + location, exception );
			if ( requestParams.isError( ) ) {
				errorContainer = this.fillErrorContainer( location, exception );
			}
		}
		itemDAOResponse.setCount( items == null ? DAOConstants.ZERO : items.size( ) );
		itemDAOResponse.setResults( items );
		itemDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );
		return itemDAOResponse;
	}

	/**
	 * Gets item by search params.
	 *
	 * @param searchOption the item search option
	 *
	 * @return the item by search params
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" } )
	@Transactional( readOnly = true, propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED )
	public DAOResponse< Item > getItemsBySearchParams( SearchOption searchOption, final RequestParams requestParams ) {

		String location = this.getClass( ).getCanonicalName( ) + "#getItemsBySearchParams()";
		List< Item > itemList = new ArrayList<>( );
		logger.debug( "Starting " + location );

		DAOResponse< Item > itemDAOResponse = new DAOResponse<>( );
		ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

		try {
			this.openDBTransaction( );

			logger.info( searchOption.getSearchConditionParams( ).toString( ) );

			String filterQuery = this.getFilterQueryByParams( searchOption );
			String sortQuery = this.getSortQueryByParams( searchOption );

//          Note Example query for making dynamic parent child joins
//			@IMP to remember how to use below lines to make dynamic join qeries using Hibernate
//			Query query = session.createQuery( "Select item from Item item join item.gemstoneList gemstone join item.itemImageList itemImage" +
//					"  where item.itemType=:item_type1 and gemstone.gemstoneName in(:gemstone_name1) and itemImage.id > 0 " );
			logger.debug( "select item from Item item " + filterQuery + sortQuery );

			Query query = session.createQuery( "select item from Item item " + filterQuery + sortQuery );

			// + filterQuery +	// sortQuery );

			for ( Map.Entry< String, List > stringListEntry : getValuesListMap( ).entrySet( ) ) {
				query.setParameterList( stringListEntry.getKey( ), stringListEntry.getValue( ) );
			}

			for ( Map.Entry< String, Object > stringListEntry : getValueMap( ).entrySet( ) ) {

				Object value = stringListEntry.getValue( );
				String type = NumberCommons.validateTypeOfObject( value );

				switch ( type ) {
					case DAOConstants.LONG:
						query.setLong( stringListEntry.getKey( ), Long.parseLong( String.valueOf( stringListEntry.getValue( ) ) ) );
						break;
					case DAOConstants.DOUBLE:
						query.setDouble( stringListEntry.getKey( ), Double.parseDouble( String.valueOf( stringListEntry.getValue( ) ) ) );
						break;
					default:
						query.setParameter( stringListEntry.getKey( ), stringListEntry.getValue( ) );
						break;
				}
			}

			itemList = query.list( );

			// Note clear the maps for we will get issues with future filter requests
			// @IMP clear the maps for we will get issues with future filter requests
			this.getKeysUsed( ).clear( );
			this.getValueMap( ).clear( );
			this.getValuesListMap( ).clear( );

			this.closeDBTransaction( );
			itemDAOResponse.setRequestSuccess( Boolean.TRUE );

		} catch ( HibernateException exception ) {
			this.handleExceptions( exception );
			itemDAOResponse.setRequestSuccess( Boolean.FALSE );
			if ( requestParams.isError( ) ) {
				errorContainer = fillErrorContainer( location, exception );
			}
			logger.error( "Failed searching for items in database with search params" );
		}
		itemDAOResponse.setResults( itemList );
		itemDAOResponse.setCount( itemList.size( ) );
		itemDAOResponse.setErrorContainer( errorContainer );

		logger.debug( "Finishing " + location );

		return itemDAOResponse;
	}

	/**
	 * Gets filter query by params.
	 * Complex method for filtering by each property of item even its child table props
	 * To add more child tables, please add condition param to {@link #addCustomItemChildrenWithRestriction(String,
	 * String[])}
	 *
	 * @param searchOption the search option
	 *
	 * @return the filter query by params
	 */
	private String getFilterQueryByParams( final SearchOption searchOption ) {

		String filterQuery, initialQuery = "", laterQuery = "";
		Map< String, Object > searchConditionParams = searchOption.getSearchConditionParams( );

		for ( Map.Entry< String, Object > searchConditionParam : searchConditionParams.entrySet( ) ) {

			// get condition value from Map with key
			String key = searchConditionParam.getKey( );
			if ( key == null ) {
				continue;
			}

			// get value of class property from map to query the DB
			Object value = searchConditionParam.getValue( );

			if ( value == null ) {
				continue;
			}
			// add SQL restrictions
			if ( value.toString( ).split( "," ).length > 1 ) {
				key = StringCommons.alterKeyAccToHibMapping( key );
				key = StringCommons.adjustKey( key );

				List values = this.addCustomItemChildrenWithRestriction( key, value.toString( ).split( "," ) );
				String temp[] = this.createJoinFilterQueryAndSaveNAdjustKey( key, getListProperty( ).equals( "child" )
						, values );
				initialQuery += temp[ 0 ];
				laterQuery += temp[ 1 ];
				// Note add each property of child table to a map and let hibernate set it in primary method for
			} else {

				//initial key provided to us here , format e.g. item_id changes to item.item_id
				key = StringCommons.alterKeyAccToHibMapping( key );
				// changed to item.itemId from previous comment
				key = StringCommons.adjustKey( key );
				String temp[] = this.createJoinFilterQueryAndSaveNAdjustKey( key, setIfKeyIsChildList( key ).equals(
						"child" )
						, value );
				initialQuery += temp[ 0 ];
				laterQuery += temp[ 1 ];

			}
			laterQuery += " and ";
		}
		filterQuery = initialQuery + "where " + laterQuery;
		if ( filterQuery.endsWith( " and " ) ) {
			filterQuery = filterQuery.substring( 0, filterQuery.lastIndexOf( "and" ) );
		}

		logger.debug( "** " + filterQuery + " **" );
		return filterQuery;
	}

	private String[] createJoinFilterQueryAndSaveNAdjustKey( String key, boolean flag, final Object data ) {

		List values = null;
		String value = null, eQCondition = null, inCondition = null;
		if ( data instanceof List ) {
			values = ( List ) data;
			inCondition = "in";
		} else if ( data instanceof String ) {
			eQCondition = "=";
			value = ( String ) data;
		}
		String initialQuery = "", laterQuery = "";
		if ( flag ) {
			String paramKey = key.substring( key.indexOf( ServiceConstants.DOT ) + 1 ) + 2;

			boolean isUsedKey = false;
			if ( !getKeysUsed( ).containsKey( key.substring( 0, key.indexOf( ServiceConstants.DOT ) ) ) ) {
				initialQuery += "join item." + key.substring( DAOConstants.ZERO, key.indexOf( ServiceConstants
						.DOT ) ) + DAOConstants.LIST_STRING + " " + key.substring( key.indexOf( ServiceConstants.DOT ) + 1 ) + 1 + " ";
				// Add the first part of the key only so we can check later on for already used key
				getKeysUsed( ).put( key.substring( 0, key.indexOf( ServiceConstants.DOT ) ),
						key.substring( key.indexOf( ServiceConstants.DOT ) + 1 ) );
			} else {
				key = getKeysUsed( ).get( key.substring( 0, key.indexOf( ServiceConstants.DOT )
				) );
				isUsedKey = true;
			}

			// property of the child table with class

			String tempKey;

			if ( isUsedKey ) {
				tempKey = paramKey.substring( 0, paramKey.lastIndexOf( "2" ) );
			} else {
				tempKey = key.substring( key.indexOf( ServiceConstants.DOT ) + 1 );
			}

			key = key.substring( key.indexOf( ServiceConstants.DOT ) + 1 )
					+ 1 + ServiceConstants.DOT
					+ tempKey;

			// Param Key
			if ( inCondition != null && inCondition.equals( "in" ) ) {
				laterQuery += key + " in(:" + paramKey + ")";
				// add the list for each param for the Query to be created in main parent search param method
				this.getValuesListMap( ).put( paramKey, values );
			} else if ( eQCondition != null && eQCondition.equals( "=" ) ) {
				laterQuery += key + "=:" + paramKey + " ";
				this.getValueMap( ).put( paramKey, value );
			}

		} else {
			key = StringCommons.adjustKey( key );
			String paramKey = key.substring( key.indexOf( ServiceConstants.DOT ) + 1 );
			if ( inCondition != null && inCondition.equals( "in" ) ) {
				laterQuery += key + " in(:" + paramKey + 1 + ")";
				this.getValuesListMap( ).put( paramKey + 1, values );
			} else if ( eQCondition != null && eQCondition.equals( "=" ) ) {
				laterQuery += key + " =:" + paramKey + 1 + " ";
				this.getValueMap( ).put( paramKey + 1, value );
			}
		}
		return new String[]{ initialQuery, laterQuery };
	}

	private List addCustomItemChildrenWithRestriction( final String key,
													   final String[] split ) {

		boolean longFlag = false, doubleFlag = false;
		try {
			long test = Long.parseLong( split[ 0 ] );
			longFlag = true;
		} catch ( RuntimeException exception ) {
			logger.debug( "Checking for long value in child property, long value was not found" );
		}
		try {
			double test = Double.parseDouble( split[ 0 ] );
			doubleFlag = true;
		} catch ( RuntimeException exception ) {
			logger.debug( "Checking for double value in child property, double value was not found" );
		}

		if ( longFlag ) {
			Set< Long > longValues = new HashSet<>( );
			Collections.addAll( longValues, NumberCommons.convertToLongArray( split ) );
			setIfKeyIsChildList( key );
			return new ArrayList<>( longValues );
		} else if ( doubleFlag ) {

			List< Double > doubleValues = new ArrayList<>( );
			Collections.addAll( doubleValues, NumberCommons.convertToDoubleArray( split ) );
			this.setIfKeyIsChildList( key );
			return new ArrayList<>( doubleValues );
		} else {
			List< String > stringValues = new ArrayList<>( );
			Collections.addAll( stringValues, split );
			this.setIfKeyIsChildList( key );
			return new ArrayList<>( stringValues );
		}

	}

	/**
	 * Sets if key is child list.
	 * To add more child class filters please add name of the child class in {@link DAOConstants}
	 * Also all class properties for Domain/Hibernate must have the format classname_propertyname as column name for
	 * us to use this generic filter.
	 * Generic level is dependant on Parent class name only for initial query for now as this method needs to know
	 * all the child class names to decipher if the property will be a child class or only a parent class property.
	 *
	 * @param key the key
	 *
	 * @return the if key is child list
	 */
	public String setIfKeyIsChildList( String key ) {

		if ( key.toLowerCase( ).contains( DAOConstants.GEMSTONE_CLASS_IN_STRING ) ) {
			setListProperty( "child" );
			return getListProperty( );

		} else if ( key.toLowerCase( ).contains( DAOConstants.ITEM_IMAGE_CLASS_IN_STRING ) ) {
			setListProperty( "child" );
			return getListProperty( );

		} else if ( key.toLowerCase( ).contains( DAOConstants.ITEM_DISCOUNT_CLASS_IN_STRING ) ) {
			setListProperty( "child" );
			return getListProperty( );
		} else {
			setListProperty( "item" );
			return getListProperty( );
		}
	}

	private String getSortQueryByParams( final SearchOption searchOption ) {

		String orderBy = " order by ", sortQuery = orderBy;
		Map< String, String > searchOrders = searchOption.getSearchOrderWithParams( );

		for ( Map.Entry< String, String > searchOrder : searchOrders.entrySet( ) ) {

			// get condition value from Map with key
			String key = searchOrder.getKey( );
			if ( key == null ) {
				continue;
			}
			key = StringCommons.alterKeyAccToHibMapping( key );
			key = StringCommons.adjustKey( key );
			if ( key.contains( DAOConstants.GEMSTONE_CLASS_IN_STRING ) ||
					key.contains( DAOConstants.ITEM_DISCOUNT_CLASS_IN_STRING ) ||
					key.contains( DAOConstants.ITEM_IMAGE_CLASS_IN_STRING )
					) {
				logger.warn( "child class property not available for sorting" );
				continue;
			}
			// get value of class property from map to query the DB
			String value = searchOrder.getValue( );
			if ( value == null ) {
				continue;
			}
			sortQuery += key + " " + value.toUpperCase( ) + ",";
			// add SQL restrictions
//			detachedCriteria.addOrder( queryUtil.getCreatedOrder( key, value ) );
		}
		int lastIndex = sortQuery.lastIndexOf( "," );

		if ( lastIndex > 0 ) {
			sortQuery = sortQuery.substring( 0, lastIndex );
		}
		return sortQuery.equalsIgnoreCase( orderBy ) ? "" : sortQuery;
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

	private String getListProperty( ) {
		return listProperty;
	}

	private void setListProperty( final String listProperty ) {
		this.listProperty = listProperty;
	}

	public Map< String, String > getKeysUsed( ) {
		return keysUsed;
	}

	private Map< String, List > getValuesListMap( ) {
		return valuesListMap;
	}

	private Map< String, Object > getValueMap( ) {
		return valueMap;
	}

}
