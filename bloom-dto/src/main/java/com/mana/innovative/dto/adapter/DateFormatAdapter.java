package com.mana.innovative.dto.adapter;

import org.apache.log4j.Logger;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Date format adapter.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class DateFormatAdapter extends XmlAdapter< String, Date > {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( DateFormatAdapter.class );

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    /**
     * Convert a value type to a bound type.
     *
     * @param stringDate The value to be converted. Can be null.
     *
     * @throws Exception if there's an error during the conversion. The caller is responsible for reporting the error to
     *                   the user through {@link ValidationEventHandler}.
     */
    @Override
    public Date unmarshal( final String stringDate ) throws Exception {

        logger.debug( "Unmarshalling DateString " + stringDate );
        return simpleDateFormat.parse( stringDate );
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param date The value to be converted. Can be null.
     *
     * @throws Exception if there's an error during the conversion. The caller is responsible for reporting the error to
     *                   the user through {@link ValidationEventHandler}.
     */
    @Override
    public String marshal( final Date date ) throws Exception {

        logger.debug( "Marshalling Date " + date );
        return simpleDateFormat.format( date );
    }
}
