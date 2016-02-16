package com.mana.innovative.logic;

import com.mana.innovative.constants.DAOConstants;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright Date : 10/2/12 Time: 9:58 AM
 * @since: jdk 1.7
 */
public class QueryUtil {

	/**
	 * The constant EQ.
	 */
	public static final String EQ = "eq";
	/**
	 * The constant NE.
	 */
	public static final String NE = "ne";
	/**
	 * The constant GE.
	 */
	public static final String GE = "ge";
	/**
	 * The constant LE.
	 */
	public static final String LE = "le";
	/**
	 * The constant GT.
	 */
	public static final String GT = "gt";
	/**
	 * The constant LT.
	 */
	public static final String LT = "lt";
	/**
	 * The constant LIKE.
	 */
	public static final String LIKE = "like";
	/**
	 * The constant I_LIKE.
	 */
	public static final String I_LIKE = "ilike";
	public static final java.lang.String IN = "in";
	/**
	 * The START _ vALUE.
	 */
	public final String START_VALUE = "start";
	/**
	 * The END _ vALUE.
	 */
	public final String END_VALUE = "end";
	/**
	 * The EXACT _ vALUE.
	 */
	public final String EXACT_VALUE = "exact";

	/**
	 * Add between restriction.
	 *
	 * @param criteria the criteria
	 * @param property the property
	 * @param minValue the min value
	 * @param maxValue the max value
	 *
	 * @return the criteria
	 */
	public Criteria addBetweenRestriction( Criteria criteria, String property, String minValue, String maxValue ) {
		return criteria.add( Restrictions.between( property, minValue, maxValue ) );
	}

	//  Todo implement DB operations like and, or , in and between later on
//    public Criteria addAndRestriction ( Criteria criteria, List<String> properties, List<String> values,
//                                        List<String> operators, String minValue,
//                                        String maxValue ) {
//
//        List<Criterion> criterions = new ArrayList<Criterion>();
//        for ( int i = 0; i < properties.size() && properties.size() == values.size(); i++ ) {
//            criterions.add( this.getAddedRestriction( properties.get( i ), values.get( i ), operators.get( i ) ) );
//        }
//        Criterion criterion = Restrictions.and(criterions);
//        return criteria.add( criterion );
//    }

	/**
	 * Gets added restriction.
	 *
	 * @param property the property
	 * @param value the value
	 * @param operator the operator
	 *
	 * @return the added restriction
	 */
	@SuppressWarnings( "unchecked" )
	public Criterion getAddedRestriction( String property, Object value, String operator ) {

		long longValue = 0L;
		double doubleValue = 0.0;
		boolean flag = false;

		if ( value instanceof String[] ) {
			flag = true;
		}
		if ( !flag ) {
			try {
				longValue = Long.parseLong( value.toString( ) );
				flag = true;
			} catch ( Exception exception ) {
//				exception.printStackTrace( );
			}
		}
		if ( !flag ) {
			try {
				doubleValue = Double.parseDouble( value.toString( ) );
				flag = true;
			} catch ( Exception exception ) {
//			  exception.printStackTrace();
			}
		}
		try {
			switch ( operator ) {
				case GE:
					return Restrictions.ge( property, flag & longValue > -1 ? longValue : flag &
							longValue < 1 ? doubleValue : value );
				case LE:
					return Restrictions.le( property, flag & longValue > -1 ? longValue : flag &
							longValue < 1 ? doubleValue : value );
				case GT:
					return Restrictions.gt( property, flag & longValue > -1 ? longValue : flag &
							longValue < 1 ? doubleValue : value );
				case LT:
					return Restrictions.lt( property, flag & longValue > -1 ? longValue : flag &
							longValue < 1 ? doubleValue : value );
				case EQ:
					return Restrictions.eq( property, flag & longValue > -1 ? longValue : flag &
							longValue < 1 ? doubleValue : value );
				case NE:
					return Restrictions.ne( property, flag & longValue > -1 ? longValue : flag &
							longValue < 1 ? doubleValue : value );
				case IN:
					return Restrictions.in( property, ( ArrayList ) value );
			}
		} catch ( ClassCastException exception ) {
			exception.printStackTrace( );
		}
		return null;
	}

	/**
	 * Gets added restriction.
	 *
	 * @param property the property
	 * @param value the value
	 * @param operator the operator
	 * @param matchType the match type
	 *
	 * @return the added restriction
	 */
	public Criterion getAddedRestriction( String property, Object value, String operator, String matchType ) {

		MatchMode matchMode;
		if ( matchType.equalsIgnoreCase( EXACT_VALUE ) ) {
			matchMode = MatchMode.EXACT;
		} else if ( matchType.equalsIgnoreCase( START_VALUE ) ) {
			matchMode = MatchMode.START;
		} else if ( matchType.equalsIgnoreCase( END_VALUE ) ) {
			matchMode = MatchMode.END;
		} else {
			matchMode = MatchMode.ANYWHERE;
		}
		if ( operator.equals( LIKE ) ) {
			return Restrictions.like( property, value.toString( ), matchMode );
		} else if ( operator.equals( I_LIKE ) ) {
			return Restrictions.ilike( property, value.toString( ), matchMode );
		}
		return null;
	}

	/**
	 * Add in restriction.
	 *
	 * @param property the property
	 * @param values the values
	 *
	 * @return the criterion
	 */
	public Criterion addInRestriction( String property, Collection values ) {

		return Restrictions.in( property, values );
	}

	/**
	 * Limit max results per call.
	 *
	 * @param criteria the criteria
	 * @param maxLimit the max limit
	 *
	 * @return the criteria
	 */
	public Criteria limitMaxResultsPerCall( Criteria criteria, int maxLimit ) {

		return criteria.setMaxResults( maxLimit );
	}

	/**
	 * Starting limit.
	 *
	 * @param criteria the criteria
	 * @param startLimit the start limit
	 *
	 * @return the criteria
	 */
	public Criteria startingLimit( Criteria criteria, int startLimit ) {
		return criteria.setFirstResult( startLimit );
	}

	/**
	 * Gets created order.
	 *
	 * @param property the property
	 * @param ordering the ordering
	 *
	 * @return the created order
	 */
	public Order getCreatedOrder( final String property, final String ordering ) {

		if ( ordering.toLowerCase( ).equals( DAOConstants.ASC ) ) {
			return Order.asc( property );
		} else if ( ordering.toLowerCase( ).equals( DAOConstants.DESC ) ) {
			return Order.desc( property );
		}
		throw new IllegalArgumentException( "Invalid Order" );
	}
}
