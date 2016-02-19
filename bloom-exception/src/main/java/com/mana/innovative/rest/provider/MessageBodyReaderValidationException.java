package com.mana.innovative.rest.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The type MessageBodyReaderValidationException.
 * <p/>
 * Created by Bloom/Rono on 2/18/2016 11:30 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class MessageBodyReaderValidationException extends Exception {

//	private static final Logger logger = LoggerFactory.getLogger( MessageBodyReaderValidationException.class );

	private String message;

	public MessageBodyReaderValidationException( final String s, final Exception e ) {
		this.message = s + " " + e.getMessage( );
	}


	public MessageBodyReaderValidationException( final String message ) {
		this.message = message;
	}

	@Override
	public String toString( ) {

		return message;
	}
}