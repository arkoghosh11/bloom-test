package com.mana.innovative.reader;

import java.io.File;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomExcelFileReader
 *
 * @param <T> the type parameter Created by Bloom/Rono on $ { DATE } $ { TIME } .
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public interface CustomExcelFileReader < T > extends CustomFileReader {

    /**
     * Read excel file reader.
     *
     * @param file the file
     *
     * @return the t
     */
    T readExcelFileReader( File file );
}
