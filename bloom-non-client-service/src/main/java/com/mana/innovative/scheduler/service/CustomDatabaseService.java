package com.mana.innovative.scheduler.service;

import org.springframework.stereotype.Service;

/**
 * Created by Bloom/Rono on 4/17/2015. This class is CustomDatabaseService
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
@Service
public interface CustomDatabaseService < T, E > extends CustomDatabaseReadService< T, E >, CustomDatabaseSaveService< T > {


}
