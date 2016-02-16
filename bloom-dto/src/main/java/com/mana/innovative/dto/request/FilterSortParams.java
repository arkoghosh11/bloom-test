package com.mana.innovative.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The type Tab.
 * <p/>
 * Created by Bloom/Rono on 2/11/2016.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
//Todo check for invalid criterion and stop execution at service level.
@XmlRootElement( name = "customFilterSorter", namespace = "https:192.168.0.48:8443/bloom" )
public class FilterSortParams {

	/**
	 * The Filter.
	 */
	private Filter filter;
	/**
	 * The Sort.
	 */
	private Sort sort;

	public FilterSortParams( ) {

	}

	/**
	 * Gets filter.
	 *
	 * @return the filter
	 */
	@XmlElement( name = "filter" )
	public Filter getFilter( ) {
		return filter;
	}

	/**
	 * Sets filter.
	 *
	 * @param filter the filter
	 */
	public void setFilter( final Filter filter ) {
		this.filter = filter;
	}

	/**
	 * Gets sort.
	 *
	 * @return the sort
	 */
	@XmlElement( name = "sort" )
	public Sort getSort( ) {
		return sort;
	}

	/**
	 * Sets sort.
	 *
	 * @param sort the sort
	 */
	public void setSort( final Sort sort ) {
		this.sort = sort;
	}

	/**
	 * Hash code int.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode( ) {
		return Objects.hash( filter, sort );
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
		if ( o == null || getClass( ) != o.getClass( ) ) return false;
		FilterSortParams that = ( FilterSortParams ) o;
		return Objects.equals( filter, that.filter ) &&
				Objects.equals( sort, that.sort );
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString( ) {
		return "FilterSortParams{" +
				"filter=" + filter +
				", sort=" + sort +
				'}';
	}
}
