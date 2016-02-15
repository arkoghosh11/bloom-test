package com.mana.innovative.dto.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement( name = "gemstone" )
public class Gemstone {

	/**
	 * The Gemstone id.
	 */

	private long gemstoneId;

	/**
	 * The Gemstone name.
	 */

	private String gemstoneName;

	/**
	 * The Gemstone description.
	 */

	private String gemstoneDescription;

	/**
	 * Gets gemstone id.
	 *
	 * @return the gemstone id
	 */
	@XmlElement( name = "gemstone_id" )
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
	@XmlElement( name = "gemstone_name" )
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
	@XmlElement( name = "gemstone_description" )
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
