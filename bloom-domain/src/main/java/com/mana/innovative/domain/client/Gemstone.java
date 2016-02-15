package com.mana.innovative.domain.client;

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
 * The type Gemstone.
 * <p/>
 * Created by Bloom/Rono on 2/12/2016 9:04 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "gemstones", uniqueConstraints = { @UniqueConstraint( name = "UK_gemstone_name", columnNames =
		"gemstone_name" ) } )
public class Gemstone {

	/**
	 * The Gemstone id.
	 */
	@Id
	@Column( name = "gemstone_id", nullable = false )
	@GeneratedValue( strategy = GenerationType.TABLE )
	private long gemstoneId;

	/**
	 * The Gemstone name.
	 */
	@Column( name = "gemstone_name" )
	private String gemstoneName;

	/**
	 * The Gemstone description.
	 */
	@Column( name = "gemstone_description" )
	private String gemstoneDescription;

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
	 * The Items.
	 */
	@ManyToMany( cascade = { CascadeType.MERGE, CascadeType.PERSIST } )
	@JoinTable( name = "item_gemstones",
			joinColumns = { @JoinColumn( name = "gemstone_id" ) },
			inverseJoinColumns = { @JoinColumn( name = "item_id", referencedColumnName = "item_id" ) },
			uniqueConstraints = { @UniqueConstraint( name = "item_gemstone_id",
					columnNames = { "gemstone_id", "item_id" } ) } )
	private List< Item > itemList;


	/**
	 * Gets gemstone id.
	 *
	 * @return the gemstone id
	 */
	public long getGemstoneId( ) {
		return gemstoneId;
	}

	/**
	 * Sets gemstone id.
	 *
	 * @param gemstoneId the gemstone id
	 */
	public void setGemstoneId( final long gemstoneId ) {
		this.gemstoneId = gemstoneId;
	}

	/**
	 * Gets gemstone name.
	 *
	 * @return the gemstone name
	 */
	public String getGemstoneName( ) {
		return gemstoneName;
	}

	/**
	 * Sets gemstone name.
	 *
	 * @param gemstoneName the gemstone name
	 */
	public void setGemstoneName( final String gemstoneName ) {
		this.gemstoneName = gemstoneName;
	}

	/**
	 * Gets gemstone description.
	 *
	 * @return the gemstone description
	 */
	public String getGemstoneDescription( ) {
		return gemstoneDescription;
	}

	/**
	 * Sets gemstone description.
	 *
	 * @param gemstoneDescription the gemstone description
	 */
	public void setGemstoneDescription( final String gemstoneDescription ) {
		this.gemstoneDescription = gemstoneDescription;
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
	 * Gets items.
	 *
	 * @return the items
	 */
	public List< Item > getItemList( ) {
		return itemList;
	}

	/**
	 * Sets items.
	 *
	 * @param items the items
	 */
	public void setItemList( final List< Item > items ) {
		this.itemList = items;
	}

	/**
	 * Hash code int.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode( ) {
		return Objects.hash( getGemstoneId( ), getGemstoneName( ), getGemstoneDescription( ) );
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
		if ( !( o instanceof Gemstone ) ) return false;
		Gemstone gemstone = ( Gemstone ) o;
		return Objects.equals( getGemstoneId( ), gemstone.getGemstoneId( ) ) &&
				Objects.equals( getGemstoneName( ), gemstone.getGemstoneName( ) ) &&
				Objects.equals( getGemstoneDescription( ), gemstone.getGemstoneDescription( ) );
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString( ) {
		return "Gemstone{" +
				"gemstoneId=" + gemstoneId +
				", gemstoneName='" + gemstoneName + '\'' +
				", gemstoneDescription='" + gemstoneDescription + '\'' +
				'}';
	}
}
