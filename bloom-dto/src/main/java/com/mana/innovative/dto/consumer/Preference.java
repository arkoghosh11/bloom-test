package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Preference.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "preference" )
public class Preference {

    /**
     * The Preference id.
     */
    private String preferenceId;

    /**
     * The Preference name.
     */
    private String preferenceName;

    /**
     * The Is preferred.
     */
    private boolean isPreferred;

    //    Note getters and setters start from here

    /**
     * Gets preference id.
     *
     * @return the preference id
     */
    @XmlElement( name = "preference_id" )
    public String getPreferenceId( ) {
        return preferenceId;
    }

    /**
     * Sets preference id.
     *
     * @param preferenceId the preference id
     */
    public void setPreferenceId( final String preferenceId ) {
        this.preferenceId = preferenceId;
    }

    /**
     * Is preferred.
     *
     * @return the boolean
     */
    @XmlElement( name = "is_preferred" )
    public boolean isPreferred( ) {
        return isPreferred;
    }

    /**
     * Sets preferred.
     *
     * @param preferred the preferred
     */
    public void setPreferred( final boolean preferred ) {
        isPreferred = preferred;
    }

    /**
     * Gets preference name.
     *
     * @return the preference name
     */
    @XmlElement( name = "preference_name" )
    public String getPreferenceName( ) {
        return preferenceName;
    }

    /**
     * Sets preference name.
     *
     * @param preferenceName the preference name
     */
    public void setPreferenceName( final String preferenceName ) {
        this.preferenceName = preferenceName;
    }
}
