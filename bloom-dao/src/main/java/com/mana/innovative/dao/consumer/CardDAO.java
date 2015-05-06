package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Card;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

/**
 * Created by Bloom/Rono on 5/2/2015 5:27 PM. This class is CardDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
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
    DAOResponse< Card > getCard( long cardId, RequestParams requestParams );

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
    DAOResponse< Card > deleteCardByCardId( Long cardId, RequestParams requestParams );

    /**
     * Delete all cards.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Card > deleteAllCards( RequestParams requestParams );
}
