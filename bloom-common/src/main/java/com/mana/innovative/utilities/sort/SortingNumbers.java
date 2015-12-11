package com.mana.innovative.utilities.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class SortingNumbers is for todo.
 * Created by BLOOM on 10/28/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class SortingNumbers {

    private Logger logger = LoggerFactory.getLogger( SortingNumbers.class );
    private String numbers = "69, 25, 23, 61, 27, 36, 32, 104, 33, 83, 42, 43, 71, 72, 52, 80, 31, 102, 103, 24, 99, " +
            "30, 89";

    public static void main( String args[] ) {

        new SortingNumbers( ).sortNumber( );

    }

    public void sortNumber( ) {

        String temp[] = numbers.split( "," );

        List< Integer > integerList = new ArrayList<>( );

        for ( String string : temp ) {
            string = string.trim( );
            int number = Integer.parseInt( string );
            integerList.add( number );
        }

        Collections.sort( integerList );
        System.out.println( integerList );
    }

}
