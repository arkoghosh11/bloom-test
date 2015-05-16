package com.mana.innovative.dto.adapter;

import com.mana.innovative.constants.DAOConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger( DateFormatAdapter.class );

    /**
     * The Simple date format.
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( DAOConstants.DEFAULT_DATE_FORMAT );

    /**
     * Convert a value type to a bound type.
     *
     * @param stringDate The value to be converted. Can be null.
     *
     * @return the date
     *
     * @throws Exception if there's an error during the conversion. The caller is responsible for reporting the error to
     *                   the user through .
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
     * @return the string
     *
     * @throws Exception if there's an error during the conversion. The caller is responsible for reporting the error to
     *                   the user through .
     */
    @Override
    public String marshal( final Date date ) throws Exception {

        logger.debug( "Marshalling Date " + date );
        return simpleDateFormat.format( date );
    }
}
