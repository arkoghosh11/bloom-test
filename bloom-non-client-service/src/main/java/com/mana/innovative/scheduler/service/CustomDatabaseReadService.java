package com.mana.innovative.scheduler.service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseReadService
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public interface CustomDatabaseReadService < T, E > extends DatabaseService {

    T readData( E e );
}
