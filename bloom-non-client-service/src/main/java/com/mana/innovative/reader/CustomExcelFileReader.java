package com.mana.innovative.reader;

import java.io.File;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomExcelFileReader
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public interface CustomExcelFileReader < T > extends CustomFileReader {

    T readExcelFileReader( File file );
}
