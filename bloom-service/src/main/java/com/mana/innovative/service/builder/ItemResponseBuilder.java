package com.mana.innovative.service.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.Item;
import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.container.ItemResponseContainer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 */
public class ItemResponseBuilder {

    private static final Logger logger = Logger.getLogger(ItemResponseBuilder.class);

    public static ItemResponseContainer<ItemsPayload> build (DAOResponse<Item> itemDAOResponse, boolean isError) {


        /**
         * If these lines are executed an error has occurred is indicated normally
         */
        if(itemDAOResponse.getResults() == null || itemDAOResponse.getResults().isEmpty()) {

            itemDAOResponse.setCount(DAOConstants.ZERO);
            itemDAOResponse.setResults(new ArrayList<Item>());
        }

        List<Item> items = itemDAOResponse.getResults();
        ItemResponseContainer<ItemsPayload> itemResponseContainer = new ItemResponseContainer<>();

        ItemsPayload itemsPayload = new ItemsPayload();

        itemsPayload.setItems(items);
        itemsPayload.setTotalCount(itemDAOResponse.getCount());
        itemResponseContainer.setPayload(itemsPayload);
        itemResponseContainer.setCount(itemDAOResponse.getCount());

        ErrorContainer errorContainer = itemDAOResponse.getErrorContainer();

        if (errorContainer == null) {
            errorContainer = new ErrorContainer(null);
        }

        itemResponseContainer.setErrorContainer(isError ? errorContainer:null);
        return itemResponseContainer;
    }

    public static ItemResponseContainer<ItemsPayload> buildError (final ErrorContainer errorContainer, final boolean isError) {

        ItemsPayload itemsPayload = new ItemsPayload();

        itemsPayload.setItems(new ArrayList<Item>());


        ItemResponseContainer<ItemsPayload> itemResponseContainer = new ItemResponseContainer<>();
        itemResponseContainer.setErrorContainer(errorContainer);
        itemResponseContainer.setCount(DAOConstants.ZERO);

        return null;
    }

}
