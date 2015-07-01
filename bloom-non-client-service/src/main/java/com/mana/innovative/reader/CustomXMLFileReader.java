package com.mana.innovative.reader;

import java.io.File;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomXMLFileReader
 *
 * @param <T>   the type parameter Created by Bloom/Rono on $
 *{
 * DATE
 *}
 * $
 *{
 * TIME
 *}
 * .
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public interface CustomXMLFileReader < T > extends CustomFileReader {

    /**
     * Read xML file.
     *
     * @param file the file
     * @return the t
     */
    T readXMLFile( File file );

}
