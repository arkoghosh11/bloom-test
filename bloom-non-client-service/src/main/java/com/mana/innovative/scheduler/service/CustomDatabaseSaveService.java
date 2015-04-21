package com.mana.innovative.scheduler.service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseSaveService
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public interface CustomDatabaseSaveService < T > extends DatabaseService {

    boolean createData( T t );

    boolean updateData( T eventDate, boolean isScheduler );
}
