package com.mana.innovative.service;/**
 * Created by alex1 on 1/29/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.builder.ItemResponseBuilder;
import com.mana.innovative.service.container.ItemResponseContainer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * Created by Bloom on 1/29/2015 : 6:08 PM
 * todo This class is for ...
 */
@Service
public class ItemsService {

    @Resource
    private ItemDAO itemDAO;

    private static final Logger logger = Logger.getLogger(ItemsService.class);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Response getItems(boolean isError) {

        DAOResponse<Item> itemDAOResponse;
        ErrorContainer errorContainer = null;
        try {
            itemDAOResponse = itemDAO.getItems(isError);
        } catch (Exception e) {

            itemDAOResponse = new DAOResponse<>();
            final String location = this.getClass().getCanonicalName() + DAOConstants.HASH + "getItems()";
            errorContainer = new ErrorContainer(new com.mana.innovative.exception.response.Error(location, e));
            itemDAOResponse.setErrorContainer(errorContainer);

            logger.error("Exception occurred in ItemsService.getItems() Method", e);
        }
        Response response;
//        Exception exception = null;
        try {

            ItemResponseContainer<ItemsPayload> itemResponseContainer = ItemResponseBuilder.build(itemDAOResponse, isError);
            response = Response.status(Response.Status.OK).entity(itemResponseContainer).build();
            return response;

        } catch (Exception e) {
            ItemResponseContainer<ItemsPayload> itemResponseContainer = ItemResponseBuilder.buildError(errorContainer, isError);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(itemResponseContainer).build();
            return response;
        } finally {
            logger.info("Response sent Successfully");
//            if (exception != null) {
//                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Sorry Server Encountered a
// severe issue").build();
//            }
        }
    }
}
