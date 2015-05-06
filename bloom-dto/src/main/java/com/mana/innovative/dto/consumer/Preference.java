package com.mana.innovative.dto.consumer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

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
    private Long preferenceId;

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
    public Long getPreferenceId( ) {
        return preferenceId;
    }

    /**
     * Sets preference id.
     *
     * @param preferenceId the preference id
     */
    public void setPreferenceId( final Long preferenceId ) {
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

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Preference ) ) return false;
        Preference that = ( Preference ) o;
        return Objects.equals( isPreferred( ), that.isPreferred( ) ) &&
                Objects.equals( getPreferenceId( ), that.getPreferenceId( ) ) &&
                Objects.equals( getPreferenceName( ), that.getPreferenceName( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Preference{" +
                "preferenceId=" + preferenceId +
                ", preferenceName='" + preferenceName + '\'' +
                ", isPreferred=" + isPreferred +
                '}';
    }
}
