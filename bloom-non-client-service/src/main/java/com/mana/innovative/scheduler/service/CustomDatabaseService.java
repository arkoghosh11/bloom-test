package com.mana.innovative.scheduler.service;

import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseService
 *
 * @param <T> the type parameter
 * @param <E> the type parameter Created by Bloom/Rono on $ { DATE } $ { TIME } .
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface CustomDatabaseService < T, E > extends CustomDatabaseReadService< T, E >, CustomDatabaseSaveService< T > {


}
