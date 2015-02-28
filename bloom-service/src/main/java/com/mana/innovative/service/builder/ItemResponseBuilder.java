package com.mana.innovative.service.builder;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.dto.Item;
import com.mana.innovative.dto.payload.ItemsPayload;
import com.mana.innovative.exception.response.ErrorContainer;
import com.mana.innovative.service.container.ItemResponseContainer;
import com.mana.innovative.utilities.response.ItemDomainToDTO;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The type Item response builder.
 */
public class ItemResponseBuilder {

    private static final Logger logger = Logger.getLogger(ItemResponseBuilder.class);

    /**
     * Build item response container.
     *
     * @param itemDAOResponse the item dAO response
     * @param isError         the is error
     * @return the item response container
     */
    public static ItemResponseContainer<ItemsPayload> build(DAOResponse<com.mana.innovative.domain.Item> itemDAOResponse, boolean isError) {


        /**
         * If these lines are executed an error has occurred is indicated normally
         */
        if (itemDAOResponse.getResults() == null || itemDAOResponse.getResults().isEmpty()) {

            itemDAOResponse.setCount(DAOConstants.ZERO);
            itemDAOResponse.setResults(new ArrayList<com.mana.innovative.domain.Item>());
        }

        List<com.mana.innovative.domain.Item> items = itemDAOResponse.getResults();
        ItemResponseContainer<ItemsPayload> itemResponseContainer = new ItemResponseContainer<>();

        ItemsPayload itemsPayload = new ItemsPayload();

        itemsPayload.setItems(ItemDomainToDTO.getConvertedItemDTOList(items));
        itemsPayload.setTotalCount(itemDAOResponse.getCount());
        itemResponseContainer.setPayload(itemsPayload);
        itemResponseContainer.setCount(itemDAOResponse.getCount());

        ErrorContainer errorContainer = itemDAOResponse.getErrorContainer();

        if (errorContainer == null) {
            errorContainer = new ErrorContainer(null);
        }

        itemResponseContainer.setErrorContainer(isError ? errorContainer : null);
        return itemResponseContainer;
    }


    /**
     * This method is to build error build error.
     *
     * @param errorContainer the error container
     * @param isError        the is error
     * @return the item response container
     */
    public static ItemResponseContainer<ItemsPayload> buildError(final ErrorContainer errorContainer, final boolean isError) {

        ItemsPayload itemsPayload = new ItemsPayload();

        itemsPayload.setItems(new ArrayList<Item>());


        ItemResponseContainer<ItemsPayload> itemResponseContainer = new ItemResponseContainer<>();
        itemResponseContainer.setErrorContainer(errorContainer);
        itemResponseContainer.setCount(DAOConstants.ZERO);

        return null;
    }

}
