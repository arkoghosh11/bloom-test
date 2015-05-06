package com.mana.innovative.domain.consumer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/23/12 Time: 11:45 AM
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 * @since: jdk 1.7
 */
@Entity
@Table( name = "preferences" )
public class Preference {

    /**
     * The Preference id.
     */
    @Id
    @Column( name = "preference_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long preferenceId;

    /**
     * The Preference name.
     */
    @Column( name = "preference_name" )
    private String preferenceName;

    /**
     * The Is preferred.
     */
    @Column( name = "is_preferred" )
    private boolean isPreferred;

    /**
     * The Customer preferences.
     */
    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "customer_preference", joinColumns = { @JoinColumn( name = "preference_id" ) },
            inverseJoinColumns = { @JoinColumn( name = "customer_id", referencedColumnName = "user_id" ) },
            uniqueConstraints = { @UniqueConstraint( name = "customer_preference_id",
                    columnNames = { "preference_id", "customer_id" } ) } )
    private List< Customer > customerPreferences;

    /**
     * The Created date.
     */
    @Column( name = "created_date", updatable = false )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    /**
     * Gets preference id.
     *
     * @return the preference id
     */
    public long getPreferenceId( ) {
        return preferenceId;
    }

    /**
     * Sets preference id.
     *
     * @param preferenceId the preference id
     */
    public void setPreferenceId( final long preferenceId ) {
        this.preferenceId = preferenceId;
    }

    /**
     * Is preferred.
     *
     * @return the boolean
     */
    public boolean isPreferred( ) {
        return isPreferred;
    }

    /**
     * Sets is preferred.
     *
     * @param isPreferred the is preferred
     */
    public void setIsPreferred( final boolean isPreferred ) {
        this.isPreferred = isPreferred;
    }

    /**
     * Gets preference name.
     *
     * @return the preference name
     */
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
     * Gets customer preferences.
     *
     * @return the customer preferences
     */
    public List< Customer > getCustomerPreferences( ) {
        return customerPreferences;
    }

    /**
     * Sets customer preferences.
     *
     * @param customerPreferences the customer preferences
     */
    public void setCustomerPreferences( final List< Customer > customerPreferences ) {
        this.customerPreferences = customerPreferences;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate( ) {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
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
        return Objects.equals( getPreferenceId( ), that.getPreferenceId( ) ) &&
                Objects.equals( isPreferred( ), that.isPreferred( ) ) &&
                Objects.equals( getPreferenceName( ), that.getPreferenceName( ) );
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "Preference {" +
                " preferenceId=" + preferenceId +
                ", preferenceName= " + preferenceName +
                ", isPreferred=" + isPreferred +
                ", customerPreferences=" + customerPreferences +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
