package com.mana.innovative.reader;

import java.io.File;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomExcelFileReader
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
public interface CustomExcelFileReader < T > extends CustomFileReader {

    /**
     * Read excel file reader.
     *
     * @param file the file
     * @return the t
     */
    T readExcelFileReader( File file );
}
