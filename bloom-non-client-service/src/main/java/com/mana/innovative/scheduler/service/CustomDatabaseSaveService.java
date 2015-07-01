package com.mana.innovative.scheduler.service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseSaveService
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
public interface CustomDatabaseSaveService < T > extends DatabaseService {

    /**
     * Create data.
     *
     * @param t the t
     * @return the boolean
     */
    boolean createData( T t );

    /**
     * Update data.
     *
     * @param eventDate the event date
     * @param isScheduler the is scheduler
     * @return the boolean
     */
    boolean updateData( T eventDate, boolean isScheduler );
}
