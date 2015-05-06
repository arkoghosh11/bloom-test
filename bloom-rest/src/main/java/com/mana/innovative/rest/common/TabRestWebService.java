/**
 * @author Bloom This Class TabService.java is for Created at Aug 30, 2012 10:32:31 AM
 */
package com.mana.innovative.rest.common;


import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.common.TabService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * The type Tab rest web service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{tab : (?i)tab}" ) //@Note Path regex for allowing any case for the web service
@Validated()
public class TabRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( TabRestWebService.class );

    /**
     * The Tab service.
     */
    @Resource
    private TabService tabService;

    /**
     * Gets tab.
     *
     * @param tabId the tab id
     * @param isError the is error
     * @return the tab
     */
    @GET
    @Path( "/{tabId}" )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getTab(
            @PathParam( "tabId" ) int tabId,
            @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return tabService.getTab( tabId, requestParams );
    }

    /**
     * Update tab.
     *
     * @param tab the tab
     * @param tabId the tab id
     * @param isError the is error
     * @return the response
     */
    @PUT
    @Path( "/{tabId}" )
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response updateTab( Tab tab,
                               @PathParam( "tabId" ) int tabId,
                               @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return tabService.updateTab( tab, requestParams );
    }

    /**
     * Delete tab.
     *
     * @param tabId the tab id
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Path( "/{tabId}" )
    public Response deleteTab( @PathParam( "tabId" ) int tabId,
                               @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return tabService.deleteTab( tabId, requestParams );
    }

    /**
     * Create tab.
     *
     * @param tab the tab
     * @param isError the is error
     * @return the response
     */
    @POST
    @Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response createTab( Tab tab,
                               @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        try {
            if ( tab == null ) {
                throw new NullPointerException( "Tab for creation is null" );
            }
        } catch ( Exception e ) {

            return Response.status( Status.BAD_REQUEST ).build( );
        }
        // TODO Auto-generated method stub
        Response response = null;
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        try {
            tabService.createTab( tab, requestParams );
        } catch ( Exception exception ) {
            response = Response.serverError( ).build( );
            logger.error( "Error occured while generating response ", exception );
        }
        return response;
    }
//
//	@POST
//	@Consumes( { MediaType.APPLICATION_FORM_URLENCODED } )
//	@NotNull
//	public Response getFormParams ( @NotBlank @FormParam( "userId" ) @DefaultValue( "" ) String userId,
//									@NotBlank @FormParam( "userName" ) @DefaultValue( "" ) String userName,
//									@NotBlank @FormParam( "password" ) @DefaultValue( "" ) String password,
//									@NotBlank @Email @FormParam( "email" ) @DefaultValue( "" ) String email ) {
//		try {
////            map = uriInfo.getPathParameters();
//			System.out.println( userId );
//			System.out.println( "Form params" );
//			System.out.println( userName + " " + password + " " + email );
//		} catch ( NullPointerException ex ) {
//			ex.printStackTrace();
//			return Response.status( Status.BAD_REQUEST ).build();
//		} catch ( Exception ex ) {
//			ex.printStackTrace();
//		}
//
//		return Response.ok( "Success" ).build();
//	}
}
