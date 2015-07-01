package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Card;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 5:27 PM. This class is CardDAO
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface CardDAO {

    /**
     * Create card.
     *
     * @param card the card
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Card > createCard( Card card, RequestParams requestParams );

    /**
     * Gets cards.
     *
     * @param requestParams the request params
     * @return the cards
     */
    DAOResponse< Card > getCards( RequestParams requestParams );

    /**
     * Gets card.
     *
     * @param cardId the card id
     * @param requestParams the request params
     * @return the card
     */
    DAOResponse< Card > getCardByCardId( long cardId, RequestParams requestParams );

    /**
     * Update card.
     *
     * @param card the card
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Card > updateCard( Card card, RequestParams requestParams );

    /**
     * Delete card by card id.
     *
     * @param cardId the card id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Card > deleteCardByCardId( long cardId, RequestParams requestParams );

    /**
     * Delete cards by cards id.
     *
     * @param cardIds the card ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Card > deleteCardsByCardIds( List< Long > cardIds, RequestParams requestParams );

    /**
     * Delete all cards.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Card > deleteAllCards( RequestParams requestParams );
}
