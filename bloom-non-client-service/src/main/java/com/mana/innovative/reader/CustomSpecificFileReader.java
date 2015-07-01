package com.mana.innovative.reader;

import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomSpecificFileReader
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
@Service
public interface CustomSpecificFileReader < T > extends CustomXMLFileReader< T >, CustomExcelFileReader< T > {

}
