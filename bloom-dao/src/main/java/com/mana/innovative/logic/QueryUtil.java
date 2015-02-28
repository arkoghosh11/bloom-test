package com.mana.innovative.logic;

import com.mana.innovative.constants.DAOConstants;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom
 *         Date: 10/2/12
 *         Time: 9:58 AM
 * @since: jdk 1.7
 */
public class QueryUtil {

    public final String START_VALUE = "start";
    public final String END_VALUE = "end";
    public final String EXACT_VALUE = "exact";

    private static final String EQ = "eq";
    private static final String NE = "ne";
    private static final String GE = "ge";
    private static final String LE = "le";
    private static final String GT = "gt";
    private static final String LT = "lt";
    private static final String LIKE = "like";
    private static final String I_LIKE = "ilike";

    public Criteria addBetweenRestriction ( Criteria criteria, String property, String minValue, String maxValue ) {
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

    public Criterion getAddedRestriction ( String property, Object value, String operator ) {

//        switch ( operator ) {
//            case GE:
//                return Restrictions.ge( property, value );
//            case LE:
//                return Restrictions.le( property, value );
//            case GT:
//                return Restrictions.gt( property, value );
//            case LT:
//                return Restrictions.lt( property, value );
//            case EQ:
//                return Restrictions.eq( property, value );
//            case NE:
//                return Restrictions.ne( property, value );
//        }
        return null;
    }

    public Criterion getAddedRestriction ( String property, Object value, String operator, String matchType ) {

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
            return Restrictions.like( property, value.toString(), matchMode );
        } else if ( operator.equals( I_LIKE ) ) {
            return Restrictions.ilike( property, value.toString(), matchMode );
        }
        return null;
    }

    public Criterion addInRestriction ( String property, Collection values ) {

        return Restrictions.in( property, values );
    }

    public Criteria limitMaxResultsPerCall ( Criteria criteria, int maxLimit ) {

        return criteria.setMaxResults( maxLimit );
    }

    public Criteria startingLimit ( Criteria criteria, int startLimit ) {
        return criteria.setFirstResult( startLimit );
    }

    public Order getCreatedOrder ( final String property, final String ordering ) {

        if ( ordering.toLowerCase().equals( DAOConstants.ASC ) ) {
            return Order.asc( property );
        } else if ( ordering.toLowerCase().equals( DAOConstants.DESC ) ) {
            return Order.desc( property );
        }
        throw new IllegalArgumentException( "Invalid Order" );
    }
}
