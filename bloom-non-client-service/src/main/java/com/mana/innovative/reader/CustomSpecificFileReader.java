package com.mana.innovative.reader;

import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomSpecificFileReader
 *
 * @param <T> the type parameter Created by Bloom/Rono on $ { DATE } $ { TIME } .
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface CustomSpecificFileReader < T > extends CustomXMLFileReader< T >, CustomExcelFileReader< T > {

}
