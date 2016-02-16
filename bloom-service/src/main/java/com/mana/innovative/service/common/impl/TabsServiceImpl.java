/**
 * @author Bloom This Class TabServiceImpl.java is for Created at Aug 19, 2012 5:21:25 PM
 */
package com.mana.innovative.service.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dao.common.TabDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.SearchOption;
import com.mana.innovative.domain.common.Tab;
import com.mana.innovative.dto.common.payload.TabsPayload;
import com.mana.innovative.dto.request.FilterSortParams;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.logic.ItemSearchOption;
import com.mana.innovative.service.common.TabsService;
import com.mana.innovative.service.common.builder.TabResponseBuilder;
import com.mana.innovative.service.common.container.TabResponseContainer;
import com.mana.innovative.utilities.response.ResponseUtility;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Tabs service impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public class TabsServiceImpl implements TabsService {

	/**
	 * The constant log.
	 */
	private static final Logger log = LoggerFactory.getLogger( TabsServiceImpl.class );

	/**
	 * The Tab dAO.
	 */
	@Resource
	private TabDAO tabDAO;

	/**
	 * Gets all tabs.
	 *
	 * @param requestParams the request params
	 *
	 * @return the all tabs
	 */
	@Override
	@Cacheable( value = ServiceConstants.TABS_CACHE, key = ServiceConstants.KEY_NAME )
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
	public Response getAllTabs( RequestParams requestParams ) {

		log.debug( "Initiating getAllTabs() , tabDAO injected successfully" );
		final String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "getAllTabs()";

		TabResponseContainer< TabsPayload > tabResponseContainer;
		DAOResponse< Tab > tabDAOResponse;

		if ( !requestParams.validatePaging( ) ) {
			return ResponseUtility.badRequest( "Invalid page params provided either none or valid params are " +
					"required" );
		}

		try {
			tabDAOResponse = tabDAO.getTabs( requestParams );

		} catch ( Exception exception ) {

			if ( exception instanceof HibernateException ) {
				log.error( "Hibernate Exception occurred while trying fetch data from DB " + location, exception );
			} else
				log.error( "Exception occurred in" + location, exception );
			tabResponseContainer = TabResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( tabResponseContainer ).build( );

		}

		try {
			tabResponseContainer = TabResponseBuilder.build( tabDAOResponse, requestParams.isError( ) );
			return Response.status( Response.Status.OK ).entity( tabResponseContainer ).build( );

		} catch ( Exception exception ) {

			log.error( "Exception occurred while fetching all tabs ", exception );
			tabResponseContainer = TabResponseBuilder.buildError( location, requestParams.isError( ), exception );
			return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( tabResponseContainer ).build( );

		} finally {
			log.debug( " Finished " + location );
		}

	}

	/**
	 * Get all searched results of tabs.
	 *
	 * @param searchParams the search params
	 * @param requestParams the request params
	 *
	 * @return the all tabs
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
	public Response getTabsSearchedByParams( FilterSortParams searchParams, RequestParams requestParams ) {

		log.debug( " Executing #getTabsSearchedByParams() for tabsService " );
		Response response;
		TabResponseContainer< TabsPayload > tabResponseContainer;

		try {
			SearchOption searchOption = new SearchOption( );

			searchOption.getSearchConditionParams( ).putAll( searchParams.getFilter( ).getKeyValueMap( ) );
			searchOption.getSearchOrderWithParams( ).putAll( searchParams.getSort( ).getKeyValueMap( ) );
			DAOResponse< com.mana.innovative.domain.common.Tab > tabDAOResponse =
					tabDAO.getTabBySearchParams( searchOption, requestParams );
			tabResponseContainer = TabResponseBuilder.build( tabDAOResponse, requestParams.isError( ) );
			response = Response.ok( tabResponseContainer ).build( );
		} catch ( Exception exception ) {
			response = ResponseUtility.internalServerErrorMsg( null );
			log.error( "Exception occurred while fetching all tabs ", exception );
		}
		log.debug( " Finished #getTabsSearchedByParams() for tabsService" );
		return response;
	}

	/**
	 * Delete tabs.
	 *
	 * @param tabIds the tab ids
	 * @param requestParams the request params
	 *
	 * @return the response
	 */
	@Override
	@Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
	public Response deleteTabs( List< Integer > tabIds, RequestParams requestParams ) {

		tabDAO.deleteTabs( tabIds, requestParams );
		return null;
	}
}
