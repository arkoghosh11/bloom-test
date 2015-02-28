package com.mana.innovative.service;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.ItemDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Item;
import com.mana.innovative.domain.payload.ItemsPayload;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.builder.ItemResponseBuilder;
import com.mana.innovative.service.container.ItemResponseContainer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * Created by alex1 on 1/28/2015.
 * This is a domain class
 */
@Service
//@Path( "/{tabs : (?i)tabs}" )
public class ItemService {


    @Resource (name = "itemDAO")
    ItemDAO itemDAO;

    private static final Logger logger = Logger.getLogger(ItemService.class);

    @Transactional (propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.DEFAULT)
    public Response getItem (long itemId, boolean isError) {

        logger.debug("Initiating getItem for item_id = "+ itemId+ " , itemDAO injected successfully");

        DAOResponse<Item> itemDAOResponse;
        Response response;
        String location = "";
        ErrorContainer errorContainer = null;

        try {
            itemDAOResponse = itemDAO.getItemByItemId(itemId, isError);
        }
        catch (Exception e) {

            itemDAOResponse = new DAOResponse<>();
            location = this.getClass().getCanonicalName() + DAOConstants.HASH + this.getClass().getEnclosingMethod();
            errorContainer = new ErrorContainer(new Error(location, e));
            itemDAOResponse.setErrorContainer(errorContainer);
            logger.error("Exception occurred in ItemService.getItem(long,boolean) Method", e);
        }
        try {

            ItemResponseContainer<ItemsPayload> itemResponseContainer = ItemResponseBuilder.build(itemDAOResponse,
                    isError);
            response = Response.status(Response.Status.OK).entity(itemResponseContainer).build();
            return response;

        }
        catch (Exception e) {

            itemDAOResponse.setErrorContainer(new ErrorContainer(new com.mana.innovative.exception.response.Error
                    (location, e)));
            ItemResponseContainer<ItemsPayload> itemResponseContainer = ItemResponseBuilder.buildError
                    (errorContainer, isError);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(itemResponseContainer).build();
            return response;

        }
        finally {
            logger.debug(" Response for getItemsByItemId sent Successfully ");
        }
    }

    @Transactional
    public Response createItem(Item item, boolean isError) {
        logger.debug("Initiating createItem for incoming item, itemDAO injected successfully");

        DAOResponse<Item> itemDAOResponse;
        Response response;
        String location = "";
        ErrorContainer errorContainer = null;

        try {
            itemDAOResponse = itemDAO.createItem(item, isError);
        }
        catch (Exception e) {

            itemDAOResponse = new DAOResponse<>();
            location = this.getClass().getCanonicalName() + DAOConstants.HASH + this.getClass().getEnclosingMethod();
            errorContainer = new ErrorContainer(new Error(location, e));
            itemDAOResponse.setErrorContainer(errorContainer);
            logger.error("Exception occurred in ItemService.getItem(long,boolean) Method", e);
        }
        try {

            ItemResponseContainer<ItemsPayload> itemResponseContainer = ItemResponseBuilder.build(itemDAOResponse,
                    isError);
            response = Response.status(Response.Status.OK).entity(itemResponseContainer).build();
            return response;

        }
        catch (Exception e) {

            itemDAOResponse.setErrorContainer(new ErrorContainer(new com.mana.innovative.exception.response.Error
                    (location, e)));
            ItemResponseContainer<ItemsPayload> itemResponseContainer = ItemResponseBuilder.buildError
                    (errorContainer, isError);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(itemResponseContainer).build();
            return response;

        }
        finally {
            logger.debug(" Response for getItemsByItemId sent Successfully ");
        }
    }
}
