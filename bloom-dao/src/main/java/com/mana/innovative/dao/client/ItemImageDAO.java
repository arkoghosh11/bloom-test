package com.mana.innovative.dao.client;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.ItemImage;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class ItemImageImageDAO is for todo.
 * Created by BLOOM on 9/22/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface ItemImageDAO {

    /**
     * Delete all itemImages.
     *
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemImage > deleteAllItemImages( RequestParams requestParams );

    /**
     * Delete itemImage by itemImage id.
     *
     * @param itemImageId   the itemImage id
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemImage > deleteItemImageByItemImageId( long itemImageId, RequestParams requestParams );

    /**
     * Delete itemImages by itemImage ids.
     *
     * @param itemImageIds  the itemImage ids
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemImage > deleteItemImagesByItemImageIds( List< Long > itemImageIds, RequestParams requestParams );

    /* IMP UPDATE Functions */

    /**
     * Update itemImage.
     *
     * @param itemImage     the itemImage
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemImage > updateItemImage( ItemImage itemImage, RequestParams requestParams );

    /* IMP CREATE Functions */

    /**
     * Create itemImage.
     *
     * @param itemImage     the itemImage
     * @param requestParams the request params
     *
     * @return the dAO response
     */
    DAOResponse< ItemImage > createItemImage( ItemImage itemImage, RequestParams requestParams );

    /**
     * Gets itemImage by itemImage id.
     *
     * @param itemImageId   the itemImage id
     * @param requestParams the request params
     *
     * @return the itemImage by itemImage id
     */
    DAOResponse< ItemImage > getItemImageByItemImageId( long itemImageId, RequestParams requestParams );

    /**
     * Gets itemImages.
     *
     * @param requestParams the request params
     *
     * @return the itemImages
     */
    DAOResponse< ItemImage > getItemImages( RequestParams requestParams );

    DAOResponse< ItemImage > getItemImagesByParams( String key, List values, final RequestParams requestParams );
}
