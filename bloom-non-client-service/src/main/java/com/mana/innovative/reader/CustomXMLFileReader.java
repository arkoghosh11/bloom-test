package com.mana.innovative.reader;

import java.io.File;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomXMLFileReader
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public interface CustomXMLFileReader < T > extends CustomFileReader {

    T readXMLFile( File file );

}
