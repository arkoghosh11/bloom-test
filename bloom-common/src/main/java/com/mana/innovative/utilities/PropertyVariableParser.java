package com.mana.innovative.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * The type Property variable parser.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class PropertyVariableParser {

    /**
     * The Log.
     */
    private final Logger log = LoggerFactory.getLogger( PropertyVariableParser.class );

    /**
     * Replaces variables with the pattern ${aSystemPropertyValue}.
     *
     * @param propertyValue a string with the pattern $
     *{
     *ASystem.property.value
     *}
     * @return replacement of propertyValue with the value of System.getEnv(propertyValue);
     */
    public String replaceVariables( String propertyValue ) {
        final Scanner scanner = new Scanner( propertyValue );
        scanner.useDelimiter( "\\$\\{" );

        while ( scanner.hasNext( ) ) {
            String propertyName = scanner.next( );

            final int endIndex = propertyName.indexOf( '}' );

            if ( -1 != endIndex ) {
                propertyName = propertyName.substring( 0, endIndex );
            } else {
                continue;
            }

            final String systemPropertyValue = System.getProperty( propertyName );

            if ( null == systemPropertyValue ) {
                log.warn( "no such system property: " + propertyName );
            } else {
                propertyValue = propertyValue.replace( "${" + propertyName + "}", systemPropertyValue );
            }
        }

        return propertyValue;
    }
}
