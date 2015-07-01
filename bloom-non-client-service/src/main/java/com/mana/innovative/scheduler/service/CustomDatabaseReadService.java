package com.mana.innovative.scheduler.service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseReadService
 *
 * @param <T>   the type parameter
 * @param <E>   the type parameter Created by Bloom/Rono on $
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
public interface CustomDatabaseReadService < T, E > extends DatabaseService {

    /**
     * Read data.
     *
     * @param e the e
     * @return the t
     */
    T readData( E e );
}
