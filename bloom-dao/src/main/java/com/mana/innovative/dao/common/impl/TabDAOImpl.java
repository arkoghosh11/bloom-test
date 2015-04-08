package com.mana.innovative.dao.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.common.TabDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Tab;
import com.mana.innovative.domain.common.TabSearchOption;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalItemSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.logic.QueryUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bloom/Rono on 4/10/2015. This class is TabDAOImpl
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class TabDAOImpl implements TabDAO {

    private static final Logger logger = Logger.getLogger( TabDAOImpl.class );

    static {
        logger.setLevel( Level.DEBUG );
    }

    private QueryUtil queryUtil = new QueryUtil( );
    private Session session;

    @Resource
    private SessionFactory sessionFactory;


    /**
     * Open dB transaction.
     */
    private void openDBTransaction( ) {

        logger.debug( " Trying to open Hibernate DB Transaction " );
        try {
            if ( sessionFactory == null ) {
                NullPointerException exception = new NullPointerException( "Session " + "Factory is Null" );
                logger.error( "Session Factory inject is Null", exception );
                throw exception;
            }
            session = sessionFactory.getCurrentSession( );
//            Note Hib transaction vs spring Transaction
//            transaction = session.beginTransaction();
        } catch ( Exception e ) {
            logger.error( "Current Session error from Session Factory, either Transaction Manager Config issue " +
                    "or no DB Connection ", e );
        }
        logger.debug( "Hibernate DB Transaction Opened" );
    }

    /**
     * Close dB transaction.
     */
    private void closeDBTransaction( ) {

        logger.debug( "Trying to Flush Hibernate Transaction" );
        if ( session != null ) {
            session.flush( );
        }
        logger.debug( "Flushed Hibernate DB Transaction" );
    }

    /**
     * Handle exceptions.
     *
     * @param exception the exception
     */
    private void handleExceptions( HibernateException exception ) {
//        if (transaction != null) {
//            transaction.rollback();
//        }
        logger.error( "Hibernate Exception occurred with \nmessage: " + exception.getMessage( ), exception );
    }

    /**
     * Fill error container.
     *
     * @param location  the location
     * @param exception the exception
     *
     * @return the error container
     */
    private ErrorContainer fillErrorContainer( String location, Exception exception ) {

        logger.debug( "**** Recording Error Container Object" );
        ErrorContainer errorContainer = new ErrorContainer( );
        errorContainer.addError( new com.mana.innovative.exception.response.Error( location, exception ) );
        return errorContainer;
    }

    /**
     * This method is to retrieve all the tabs values from the DB
     *
     * @return List<Tab></> Return a list of {@link Tab}
     */
    @Override
    @SuppressWarnings( value = "unchecked" )
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Tab > getTabs( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getTabs()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "from Tab" );
            tabList = query.list( );

            this.closeDBTransaction( );
            tabDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from tabs table", exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
        }

        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * @param tabId {@link Integer}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful deletion
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > deleteTabByTabId( int tabId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createTab()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Tab where tabId=:tabId" );
            query.setParameter( "tabId", tabId );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            tabDAOResponse.setRequestSuccess( Boolean.TRUE );
            tabDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from tabs table", exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            tabDAOResponse.setCount( DAOConstants.ZERO );

            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
        }

        tabDAOResponse.setResults( null );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * This method is to update the DB with the persistence layer to keep the Tab value synced
     *
     * @param tab {@link Tab}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful update
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > updateTab( Tab tab, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updateTab()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            session.update( tab );

            this.closeDBTransaction( );

            tabList.add( tab );
            tabDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while updating data for tabs table", exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            tabDAOResponse.setCount( DAOConstants.ZERO );

            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
        }

        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * This method is to create a Tab object and save it in the DB
     *
     * @param tab {@link Tab}
     *
     * @return {@link Boolean} Returns a boolean value to indicate a successful creation
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > createTab( Tab tab, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createTab()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            session.save( tab );

            this.closeDBTransaction( );

            tabList.add( tab );
            tabDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while creating data for tabs table", exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            tabDAOResponse.setCount( DAOConstants.ZERO );

            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
        }

        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * Gets tab by search params.
     *
     * @param tabSearchOption the tab search option
     *
     * @return the tab by search params
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > getTabBySearchParams( TabSearchOption tabSearchOption, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getTabBySearchParams()";
        List< Tab > tabList = new ArrayList<>( );
        logger.debug( "Starting " + location );

        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        try {
            this.openDBTransaction( );

            DetachedCriteria detachedCriteria = this.getDetachedCriteriaBySearchParams( tabSearchOption );

            tabList = detachedCriteria.getExecutableCriteria( session ).list( );
            this.closeDBTransaction( );
            tabDAOResponse.setRequestSuccess( Boolean.TRUE );
        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( requestParams.isError( ) ) {
                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
            logger.error( "Failed searching for tabs in database with search params" );
        }
        tabDAOResponse.setResults( tabList );
        tabDAOResponse.setCount( tabList.size( ) );

        logger.debug( "Finishing " + location );

        return tabDAOResponse;
    }

    /**
     * Gets tab by tab id.
     *
     * @param tabId         the tab id
     * @param requestParams the request params
     *
     * @return the tab by tab id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Tab > getTabByTabId( int tabId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getTabByTabId()";
        logger.debug( "Starting " + location );

        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );

        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "  from Tab where tabId = :tabId" );
            query.setParameter( "tabId", tabId );
            tabList = query.list( );

            this.closeDBTransaction( );

            tabDAOResponse.setRequestSuccess( Boolean.TRUE );
        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );

            if ( requestParams.isError( ) ) {
                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
            logger.error( "Failed to get data from database for tab", exception );
        }
        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );
        logger.debug( "Finishing " + location );

        return tabDAOResponse;
    }

    /**
     * Delete tabs.
     *
     * @param tabIds        the tab ids
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > deleteTabs( List< Integer > tabIds, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#createTab()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        boolean isError = requestParams.isError( );
        try {
            this.openDBTransaction( );

            Query query = session.createQuery( "delete  from Tab where tabId in(:tabIds)" );
            query.setParameterList( "tabIds", tabIds );
            int count = query.executeUpdate( );

            this.closeDBTransaction( );

            tabDAOResponse.setRequestSuccess( Boolean.TRUE );
            tabDAOResponse.setCount( count );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from tabs table", exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            tabDAOResponse.setCount( DAOConstants.ZERO );

            if ( isError ) {

                ErrorContainer errorContainer = fillErrorContainer( location, exception );
                tabDAOResponse.setErrorContainer( errorContainer );
            }
        }

        tabDAOResponse.setResults( null );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    // Note below methods are for making a search query on the database based on each field

    /**
     * This method is to create a detached criteria
     *
     * @param tabSearchOption {@link TabSearchOption}
     *
     * @return {@link DetachedCriteria} A detached criteria object
     */
    private DetachedCriteria getDetachedCriteriaBySearchParams( TabSearchOption tabSearchOption ) {

        List< Map< String, Object > > searchConditionParams = tabSearchOption.getSearchConditionParams( );
        List< Map< String, String > > searchOrderWithParams = tabSearchOption.getSearchOrderWithParams( );
        List< Map< String, Object > > searchMatchTypeParams = tabSearchOption.getSearchMatchTypeParams( );

        List< Map< String, String > > searchConditions = tabSearchOption.getSearchConditions( );
        List< Map< String, String > > searchMatchType = tabSearchOption.getSearchMatchType( );
        List< String > keys;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass( Tab.class );

        if ( searchMatchType.isEmpty( ) ) {
            keys = this.getKeysForSearch( searchConditions );
            detachedCriteria = this.addConditionParams( detachedCriteria, searchConditionParams, searchConditions,
                    keys );
        }
        if ( !searchOrderWithParams.isEmpty( ) ) {
            keys = this.getKeysForSearch( searchOrderWithParams );
            detachedCriteria = this.addOrderParams( detachedCriteria, searchOrderWithParams, keys );
        }
        if ( !searchMatchType.isEmpty( ) ) {
            keys = this.getKeysForSearch( searchMatchType );
            detachedCriteria = this.addMatchTypeParams( detachedCriteria, searchMatchTypeParams, searchMatchType,
                    searchConditions, keys );
        }
        return detachedCriteria;
    }

    /**
     * Add condition params.
     *
     * @param detachedCriteria      the detached criteria
     * @param searchConditionParams the search condition params
     * @param searchConditions      the search conditions
     * @param keys                  the keys
     *
     * @return the detached criteria
     */
    private DetachedCriteria addConditionParams( DetachedCriteria detachedCriteria, List< Map< String,
            Object > > searchConditionParams, List< Map< String, String > > searchConditions, List< String > keys ) {

        for ( int i = 0; i < searchConditions.size( ) && searchConditionParams.size( ) == searchConditions.size( ); i++
                ) {

            // get condition value from Map with key
            String condition = searchConditions.get( i ).get( keys.get( i ) );
            // get value of class property from map to query the DB
            Object value = searchConditionParams.get( i ).get( keys.get( i ) );
            // add SQL restrictions
            detachedCriteria.add( queryUtil.getAddedRestriction( keys.get( i ), value, condition ) );
        }
        return detachedCriteria;
    }

    /**
     * Add order params.
     *
     * @param detachedCriteria the detached criteria
     * @param searchOrders     the search orders
     * @param keys             the keys
     *
     * @return the detached criteria
     */
    private DetachedCriteria addOrderParams( DetachedCriteria detachedCriteria, List< Map< String, String > > searchOrders,
                                             List< String > keys ) {

        for ( int i = 0; i < searchOrders.size( ); i++ ) {
            String ordering = searchOrders.get( i ).get( keys.get( i ) );
            if ( ordering != null ) {
                detachedCriteria.addOrder( queryUtil.getCreatedOrder( keys.get( i ), ordering ) );
            }
        }
        return detachedCriteria;
    }

    /**
     * Add match type params.
     *
     * @param detachedCriteria the detached criteria
     * @param searchParams     the search params
     * @param searchMatchTypes the search match types
     * @param searchConditions the search conditions
     * @param keys             the keys
     *
     * @return the detached criteria
     */
    private DetachedCriteria addMatchTypeParams( DetachedCriteria detachedCriteria, List< Map< String,
            Object > > searchParams, List< Map< String, String > > searchMatchTypes, List< Map< String,
            String > > searchConditions, List< String > keys ) {

        logger.debug( searchParams + "\n" + searchParams.size( )
                + " params " + searchParams.isEmpty( )
                + " matchType " + "" + searchMatchTypes.isEmpty( )
                + " conditions " + searchConditions.isEmpty( )
                + " keys " + keys.isEmpty( ) );
        if ( searchParams.isEmpty( ) || searchConditions.isEmpty( ) || searchMatchTypes.isEmpty( ) || keys.isEmpty( ) ) {
            throw new NullPointerException( "One of the Lists in parameters is Empty" );
        }
        for ( int i = 0; i < searchMatchTypes.size( ); i++ ) {

            // get condition value from Map with key
            String operator = searchConditions.get( i ).get( keys.get( i ) );
            // get value of class property from map to query the DB
            Object value = searchParams.get( i ).get( keys.get( i ) );

            String matchType = searchMatchTypes.get( i ).get( keys.get( i ) );

            detachedCriteria.add( queryUtil.getAddedRestriction( keys.get( i ), value, operator, matchType ) );
        }
        return detachedCriteria;
    }

    /**
     * This method is for getting the keys for searching
     *
     * @param searchConditions {@link List<Map>} A list of type Map of type {@link Map<String></>}
     *
     * @return {@link List<String>} A list of type String
     */
    private List< String > getKeysForSearch( final List< Map< String, String > > searchConditions ) {

        List< String > keys = new ArrayList<>( );
        for ( Map< String, String > searchCondition : searchConditions ) {
            if ( searchCondition.size( ) != DAOConstants.ONE ) {
                throw new IllegalItemSearchListSizeException( "Map Size must be ONE (1)" );
            }
            for ( Map.Entry< String, String > entry : searchCondition.entrySet( ) ) {
                keys.add( entry.getKey( ) );
            }
        }
        return keys;
    }

    public SessionFactory getSessionFactory( ) {
        return sessionFactory;
    }

    public void setSessionFactory( SessionFactory sessionFactory ) {

        this.sessionFactory = sessionFactory;
    }
}
