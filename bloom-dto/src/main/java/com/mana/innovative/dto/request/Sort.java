package com.mana.innovative.dto.request;

import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.response.Error;
import com.mana.innovative.exception.response.ErrorContainer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type Sort.
 * <p/>
 * Created by Bloom/Rono on 2/11/2016.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "sort" )
public class Sort {

	private List< String > params;
	private Map< String, String > keyValueMap = new HashMap<>( );
	private ErrorContainer errorContainer;

	@XmlElement( name = "params" )
	public List< String > getParams( ) {
		return params;
	}

	public void setParams( List< String > params ) {

		this.params = params;
		if ( params != null ) {
			this.setKeyValueMap( params );
		}
	}

	@XmlTransient
	public Map< String, String > getKeyValueMap( ) {
		return keyValueMap;
	}

	private void setKeyValueMap( List< String > params ) {

		String location = this.getClass( ).getCanonicalName( ) + "#setKeyValueMap()";
		this.getKeyValueMap( ).clear( );
		for ( String param : params ) {
			if ( param != null ) {
				String temp[] = param.split( "=" );
				if ( temp.length == 2 ) {

					if ( temp[ 1 ].trim( ).equals( "" ) ||
							!temp[ 1 ].toLowerCase( ).equals( ServiceConstants.ASC ) ||
							!temp[ 1 ].toLowerCase( ).equals( ServiceConstants.DESC ) ) {

						this.errorContainer = new ErrorContainer( );
						Error error = new Error( );
						error.setErrorType( "Invalid Param" );
						error.setErrorLocation( location );
						error.setErrorMessage( "Invalid Sort option provided: " + temp[ 1 ] );
						error.setErrorData( new IllegalArgumentValueException( "Invalid Sort Param option provided" ) );

						this.errorContainer.setCurrentError( error );
					}
					this.getKeyValueMap( ).put( temp[ 0 ], temp[ 1 ] );
				}
				//todo give warnng for invalid sort options
			}
		}
	}

	@Override
	public int hashCode( ) {
		return Objects.hash( params );
	}

	@Override
	public boolean equals( final Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass( ) != o.getClass( ) ) return false;
		Sort that = ( Sort ) o;
		return Objects.equals( params, that.params );
	}

	@Override
	public String toString( ) {
		return "Sort{" +
				"params=" + params +
				", keyValueMap=" + keyValueMap +
				'}';
	}

	public ErrorContainer getErrorContainer( ) {
		return errorContainer;
	}

}
