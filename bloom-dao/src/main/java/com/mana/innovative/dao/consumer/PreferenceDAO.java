package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.Preference;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

/**
 * Created by Bloom/Rono on 5/2/2015 5:28 PM. This class is PreferenceDAO
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Repository
public interface PreferenceDAO {

    /**
     * Create preference.
     *
     * @param preference the preference
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Preference > createPreference( Preference preference, RequestParams requestParams );

    /**
     * Gets preferences.
     *
     * @param requestParams the request params
     * @return the preferences
     */
    DAOResponse< Preference > getPreferences( RequestParams requestParams );

    /**
     * Gets preference.
     *
     * @param preferenceId the preference id
     * @param requestParams the request params
     * @return the preference
     */
    DAOResponse< Preference > getPreference( long preferenceId, RequestParams requestParams );

    /**
     * Update preference.
     *
     * @param preference the preference
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Preference > updatePreference( Preference preference, RequestParams requestParams );

    /**
     * Delete preference by preference id.
     *
     * @param preferenceId the preference id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Preference > deletePreferenceByPreferenceId( Long preferenceId, RequestParams requestParams );

    /**
     * Delete all preferences.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< Preference > deleteAllPreferences( RequestParams requestParams );

}
