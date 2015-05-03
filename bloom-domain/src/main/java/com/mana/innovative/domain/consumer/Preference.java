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
 * @since: jdk 1.7
 */
@Entity
@Table( name = "preferences" )
public class Preference {

    @Id
    @Column( name = "preference_id" )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long preferenceId;

    @Column( name = "preference_name" )
    private String preferenceName;

    @Column( name = "is_preferred" )
    private boolean isPreferred;

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

    public long getPreferenceId( ) {
        return preferenceId;
    }

    public void setPreferenceId( final long preferenceId ) {
        this.preferenceId = preferenceId;
    }

    public boolean isPreferred( ) {
        return isPreferred;
    }

    public void setIsPreferred( final boolean isPreferred ) {
        this.isPreferred = isPreferred;
    }

    public String getPreferenceName( ) {
        return preferenceName;
    }

    public void setPreferenceName( final String preferenceName ) {
        this.preferenceName = preferenceName;
    }

    public List< Customer > getCustomerPreferences( ) {
        return customerPreferences;
    }

    public void setCustomerPreferences( final List< Customer > customerPreferences ) {
        this.customerPreferences = customerPreferences;
    }

    public Date getCreatedDate( ) {
        return createdDate;
    }

    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate( ) {
        return updatedDate;
    }

    public void setUpdatedDate( final Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Preference ) ) return false;
        Preference that = ( Preference ) o;
        return Objects.equals( getPreferenceId( ), that.getPreferenceId( ) ) &&
                Objects.equals( isPreferred( ), that.isPreferred( ) ) &&
                Objects.equals( getPreferenceName( ), that.getPreferenceName( ) );
    }


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
