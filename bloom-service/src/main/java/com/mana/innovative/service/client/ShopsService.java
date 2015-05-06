package com.mana.innovative.service.client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;

/**
 * The interface Shops service.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Service
public interface ShopsService {

    /**
     * Gets shops.
     *
     * @param isError the is error
     * @return the shops
     */
    Response getShops( boolean isError );

    /**
     * Delete all shops.
     *
     * @param isError the is error
     * @param deleteAllShops the delete all shops
     * @return the response
     */
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    Response deleteAllShops( boolean isError, boolean deleteAllShops );
}
