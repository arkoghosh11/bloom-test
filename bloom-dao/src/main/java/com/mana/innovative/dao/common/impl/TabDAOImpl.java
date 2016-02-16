package com.mana.innovative.dao.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.common.TabDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.SearchOption;
import com.mana.innovative.domain.common.Tab;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/10/2015. This class is TabDAOImpl
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class TabDAOImpl extends BasicDAO implements TabDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( TabDAOImpl.class );

    /**
     * The Session factory.
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * This method is to retrieve all the tabs values from the DB
     *
     * @param requestParams the request params
     * @return List<Tab>    </> Return a list of
     */
    @Override
    @SuppressWarnings( value = "unchecked" )
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Tab > getTabs( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getTabs()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
        try {
            this.openDBTransaction( );

            String tableName = "Tab";
            Query query = session.createQuery( this.gePageQuery( requestParams, tableName ) );
            // Note Page size cannot be negative and endlimit must be null for page size to be applied
            // Note otherwise ignore it
            if ( requestParams.getPageSize( ) != null && requestParams.getPageSize( ) > 0 && requestParams.getEndLimit( )
                    == null ) {
                query.setMaxResults( requestParams.getPageSize( ) );
            }
            tabList = query.list( );

            this.closeDBTransaction( );
            tabDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while getting data from tabs table", exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * Delete tab by tab id.
     *
     * @param tabId the tab id
     * @param requestParams the request params
     * @return Returns a boolean value to indicate a successful deletion
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > deleteTabByTabId( int tabId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteTabByTabId()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

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

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        tabDAOResponse.setDelete( true );
        tabDAOResponse.setResults( null );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * This method is to update the DB with the persistence layer to keep the Tab value synced
     *
     * @param tab the tab
     * @param requestParams the request params
     * @return Returns a boolean value to indicate a successful update
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > updateTab( Tab tab, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#updateTab()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

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

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        tabDAOResponse.setUpdate( true );
        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * This method is to create a Tab object and save it in the DB
     *
     * @param tab the tab
     * @param requestParams the request params
     * @return Returns a boolean value to indicate a successful creation
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > createTab( Tab tab, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#createTab()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        List< Tab > tabList = new ArrayList<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

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

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        tabDAOResponse.setCreate( true );
        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * Gets tab by search params.
     *
     * @param searchOption the tab search option
     * @param requestParams the request params
     * @return the tab by search params
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > getTabBySearchParams( SearchOption searchOption, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getTabBySearchParams()";
        List< Tab > tabList = new ArrayList<>( );
        logger.debug( "Starting " + location );

        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

        try {
            this.openDBTransaction( );

            DetachedCriteria detachedCriteria = this.getDetachedCriteriaBySearchParams( searchOption );

            tabList = detachedCriteria.getExecutableCriteria( session ).list( );
            this.closeDBTransaction( );
            tabDAOResponse.setRequestSuccess( Boolean.TRUE );

        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
            logger.error( "Failed searching for tabs in database with search params" );
        }
        tabDAOResponse.setResults( tabList );
        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return tabDAOResponse;
    }

    /**
     * Gets tab by tab id.
     *
     * @param tabId the tab id
     * @param requestParams the request params
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
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;

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
                errorContainer = fillErrorContainer( location, exception );
            }
            logger.error( "Failed to get data from database for tab", exception );
        }
        tabDAOResponse.setCount( tabList.size( ) );
        tabDAOResponse.setResults( tabList );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return tabDAOResponse;
    }

    /**
     * Delete tabs.
     *
     * @param tabIds the tab ids
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Tab > deleteTabs( List< Integer > tabIds, RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( ) + "#deleteTabs()";

        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( ) : null;
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

            if ( requestParams.isError( ) ) {
                errorContainer = fillErrorContainer( location, exception );
            }
        }
        tabDAOResponse.setDelete( true );
        tabDAOResponse.setResults( null );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    // Note below methods are for making a search query on the database based on each field

    /**
     * Delete all tabs.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    @Override
    public DAOResponse< Tab > deleteAllTabs( final RequestParams requestParams ) {
        String location = this.getClass( ).getCanonicalName( )
                + "#deleteAllTabs()";
        logger.debug( "Starting " + location );
        DAOResponse< Tab > tabDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = requestParams.isError( ) ? new ErrorContainer( )
                : null;

        try {
            if ( requestParams.isDeleteAll( ) ) {
                this.openDBTransaction( );

                Query query = session.createQuery( "delete  from Tab" );
                int count = query.executeUpdate( );

                this.closeDBTransaction( );

                tabDAOResponse.setRequestSuccess( Boolean.TRUE );
                tabDAOResponse.setCount( count );
            }

        } catch ( HibernateException exception ) {

            this.handleExceptions( exception );
            logger.error( "Failed while deleting data from tabs table",
                    exception );
            tabDAOResponse.setRequestSuccess( Boolean.FALSE );
            tabDAOResponse.setCount( DAOConstants.ZERO );

            if ( requestParams.isError( ) ) {

                errorContainer = fillErrorContainer( location, exception );
            }
        }

        tabDAOResponse.setDelete( Boolean.TRUE );
        tabDAOResponse.setResults( null );
        tabDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return tabDAOResponse;
    }

    /**
     * This method is to create a detached criteria
     *
     * @param searchOption the tab search option
     * @return A detached criteria object
     */
    private DetachedCriteria getDetachedCriteriaBySearchParams( SearchOption searchOption ) {

//        Map< String, Object > searchConditionParams = searchOption.getSearchConditionParams( );
//        Map< String, String > searchOrderWithParams = searchOption.getSearchOrderWithParams( );
//        Map< String, Object > searchMatchTypeParams = searchOption.getSearchMatchTypeParams( );
//
//        Map< String, String >  searchConditions = searchOption.getSearchConditions( );
//        Map< String, String >  searchMatchType = searchOption.getSearchMatchType( );
//        List< String > keys;

//        if ( searchMatchType.isEmpty( ) ) {
//            keys = this.getKeysForSearch( searchConditions );
//            detachedCriteria = this.addConditionParams( detachedCriteria, searchConditionParams, searchConditions,
//                    keys );
//        }
//        if ( !searchOrderWithParams.isEmpty( ) ) {
//            keys = this.getKeysForSearch( searchOrderWithParams );
//            detachedCriteria = this.addOrderParams( detachedCriteria, searchOrderWithParams, keys );
//        }
//        if ( !searchMatchType.isEmpty( ) ) {
//            keys = this.getKeysForSearch( searchMatchType );
//            detachedCriteria = this.addMatchTypeParams( detachedCriteria, searchMatchTypeParams, searchMatchType,
//                    searchConditions, keys );
//        }
        return DetachedCriteria.forClass( Tab.class );
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
