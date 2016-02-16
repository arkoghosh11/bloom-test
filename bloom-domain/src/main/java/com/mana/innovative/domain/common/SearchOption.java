package com.mana.innovative.domain.common;


import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.exception.IllegalSearchListSizeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. This class is for advanced searching of {@link Tab} from DB {@code searchParams,
 * searchConditions, searchOrder, searchMatchType }*** searchParams is for the columns to be search against for values
 * entered by user in DB searchConditions is for applying conditions to each pram above for the column search in DB
 * search Order is the column types on which to add a order to. Order possible is (ASC,DESC, none) searchMatchType is
 * match applied to to a specified column with whichever match the user desires like a user may enter a few letter and
 * do Anywhere or a word and to exact. possible matchTypes are Exact, Anywhere, Start, End
 * <p/>
 * Note This class will be replaced by a generic searching class for any Concrete Domain class searching IMP this class
 * is not  thread safe
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright Date : 10/2/12 Time: 3:35 PM
 * @since: jdk 1.7
 */
// Todo find the correct module and package for this class
public class SearchOption {

	/**
	 * The constant MAX.
	 */
	private static final int MAX = DAOConstants.THREE;
	/**
	 * The constant MIN.
	 */
	private static final int MIN = DAOConstants.ONE;

	/**
	 * The Search condition params.
	 */
	private Map< String, Object > searchConditionParams;
	/**
	 * The Search order with params.
	 */
	private Map< String, String > searchOrderWithParams;
	/**
	 * The Search match type params.
	 */
	private Map< String, Object > searchMatchTypeParams;
	/**
	 * The Search conditions.
	 */
	private Map< String, String > searchConditions;
	/**
	 * The Search match type.
	 */
	private Map< String, String > searchMatchType;

	/**
	 * Instantiates a new Tab search option.
	 */
	public SearchOption( ) {

		searchConditionParams = new HashMap< String, Object >( ); // Note being used
		searchOrderWithParams = new HashMap< String, String >( ); // Note being used
		searchMatchTypeParams = new HashMap< String, Object >( ); // Todo use

		searchConditions = new HashMap< String, String >( );
		searchMatchType = new HashMap< String, String >( );
	}

	/**
	 * Gets search conditions.
	 *
	 * @return the search conditions
	 */
	public Map< String, String > getSearchConditions( ) {
		return searchConditions;
	}

	/**
	 * Sets search conditions.
	 *
	 * @param searchConditions the search conditions
	 */
	public void setSearchConditions( final Map< String, String > searchConditions ) {

		if ( searchConditions.size( ) > MAX || searchConditions.size( ) < MIN ) {
			throw new IllegalSearchListSizeException( " Size invalid" );
		}
		this.searchConditions = searchConditions;
	}

	/**
	 * Gets search match type.
	 *
	 * @return the search match type
	 */
	public Map< String, String > getSearchMatchType( ) {
		return searchMatchType;
	}

	/**
	 * This method is to set the Search Match Type
	 *
	 * @param searchMatchTypes the search match types
	 */
	public void setSearchMatchType( final Map< String, String > searchMatchTypes ) {
		this.searchMatchType = searchMatchTypes;
	}

	/**
	 * Gets search condition params.
	 *
	 * @return the search condition params
	 */
	public Map< String, Object > getSearchConditionParams( ) {
		return searchConditionParams;
	}

	/**
	 * Sets search condition params.
	 *
	 * @param searchConditionParams the search condition params
	 */
	public void setSearchConditionParams( final Map< String, Object > searchConditionParams ) {
		if ( searchConditionParams.size( ) > MAX || searchConditionParams.size( ) < MIN ) {
			throw new IllegalSearchListSizeException( " Size invalid" );
		}
		this.searchConditionParams = searchConditionParams;
	}

	/**
	 * Gets search order with params.
	 *
	 * @return the search order with params
	 */
	public Map< String, String > getSearchOrderWithParams( ) {
		return searchOrderWithParams;
	}

	/**
	 * Sets search order with params.
	 *
	 * @param searchOrderWithParams the search order with params
	 */
	public void setSearchOrderWithParams( final Map< String, String > searchOrderWithParams ) {

		if ( searchOrderWithParams.size( ) > MAX || searchOrderWithParams.size( ) < MIN ) {
			throw new IllegalSearchListSizeException( " Size invalid" );
		}
		this.searchOrderWithParams = searchOrderWithParams;
	}

	/**
	 * Gets search match type params.
	 *
	 * @return the search match type params
	 */
	public Map< String, Object > getSearchMatchTypeParams( ) {
		return searchMatchTypeParams;
	}

	/**
	 * Sets search match type params.
	 *
	 * @param searchMatchTypeParams the search match type params
	 */
	public void setSearchMatchTypeParams( final Map< String, Object > searchMatchTypeParams ) {

		if ( searchMatchTypeParams.size( ) > MAX || searchMatchTypeParams.size( ) < MIN ) {
			throw new IllegalSearchListSizeException( " Size invalid" );
		}
		this.searchMatchTypeParams = searchMatchTypeParams;
	}
}
