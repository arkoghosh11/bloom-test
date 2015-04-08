package com.mana.innovative.domain.consumer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Bloom Date: 10/23/12 Time: 11:45 AM
 * @since: jdk 1.7
 */
@Entity
@Table( name = "preferences" )
public class Preference {

    @Id
    @Column( name = "preference_id" )
    private String preferenceId;

    @Column( name = "preference_name" )
    private String preferenceName;

    @Column( name = "is_preferred" )
    private boolean isPreferred;

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "customer_preference", joinColumns = { @JoinColumn( name = "preference_id" ) },
            inverseJoinColumns = { @JoinColumn( name = "user_id" ) },
            uniqueConstraints = { @UniqueConstraint( name = "user_preference_id",
                    columnNames = { "preference_id", "user_id" } ) } )
    private List< Customer > customerPreferences;

    public String getPreferenceId( ) {
        return preferenceId;
    }

    public void setPreferenceId( final String preferenceId ) {
        this.preferenceId = preferenceId;
    }

    public boolean isPreferred( ) {
        return isPreferred;
    }

    public void setPreferred( final boolean preferred ) {
        isPreferred = preferred;
    }

    public String getPreferenceName( ) {
        return preferenceName;
    }

    public void setPreferenceName( final String preferenceName ) {
        this.preferenceName = preferenceName;
    }

    public List< Customer > getCustomerPreference( ) {
        return customerPreferences;
    }

    public void setCustomerPreference( final List< Customer > customerPreferences ) {
        this.customerPreferences = customerPreferences;
    }
}
