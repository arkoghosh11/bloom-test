package com.mana.innovative.dto.client.payload;

import com.mana.innovative.dto.client.Gemstone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * The type GemstonesPayload.
 * <p/>
 * Created by Bloom/Rono on 2/14/2016 10:21 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlSeeAlso( { Gemstone.class } )
@XmlRootElement( name = "gemstones_payload" )
public class GemstonesPayload {
	
	/**
	 * The Gemstones.
	 */
	private List< Gemstone > gemstones;
	/**
	 * The Total count.
	 */
	private int totalCount;
	
	
	/**
	 * This method returns a list of gemstones
	 *
	 * @return Return a list of gemstones
	 */
	@XmlElementWrapper( name = "gemstones" )
	@XmlElement( name = "gemstone" )
	public List< Gemstone > getGemstones( ) {
		return gemstones;
	}
	
	/**
	 * This method sets a list of gemstones to its class property
	 *
	 * @param gemstones A list of gemstones
	 */
	public void setGemstones( List< Gemstone > gemstones ) {
		this.gemstones = gemstones;
	}
	
	/**
	 * Gets total count.
	 *
	 * @return the total count
	 */
	public int getTotalCount( ) {
		return totalCount;
	}
	
	/**
	 * Sets total count.
	 *
	 * @param totalCount the total count
	 */
	@XmlElement( name = "gemstone_count" )
	public void setTotalCount( final int totalCount ) {
		this.totalCount = totalCount;
	}
}