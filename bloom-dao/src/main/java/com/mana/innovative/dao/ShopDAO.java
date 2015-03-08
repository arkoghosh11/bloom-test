package com.mana.innovative.dao;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.Shop;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bloom/Rono on 3/3/2015.
 * This class is for .. ToDo
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT)
public class ShopDAO extends BasicDAO {

    private static final Logger log = Logger.getLogger(ShopDAO.class);

    static {
        log.setLevel(Level.INFO);
    }

    /**
     * Gets shop by shop id.
     *
     * @param shopId  the shop id
     * @param isError the is error
     * @return the shop by shop id
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
    public DAOResponse<Shop> getShopByShopId(long shopId, boolean isError) {

        List<Shop> shops = null;
        DAOResponse<Shop> shopDAOResponse = new DAOResponse<>();
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info( "**Inside shopDAO.getShops()***" );
        try {
            this.openDBTransaction();
            Query query = session.createQuery(" from Shop where shopId" + " = :shop_id");
            query.setLong("shop_id", shopId);
//            transaction.commit();
            shops = query.list();
            if (!shops.isEmpty() && shops.size() > DAOConstants.ONE) {
                throw new IllegalSearchListSizeException( " Shop Size exceeded maximum value " +
                        "of " + DAOConstants.ONE);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            if (exception instanceof HibernateException) {
                this.handleExceptions((HibernateException) exception);
            }
            log.error( "Error occurred while trying to fetch data from shops table for shopDAO" + ".getShops()",
                    exception);
            if (isError) {
                String location = this.getClass().getCanonicalName() + "#getShopByShopId()";
                errorContainer = this.fillErrorContainer(location, exception);
            }
        } finally {
            this.closeDBTransaction();
        }
        shopDAOResponse.setCount(shops == null ? 0 : shops.size());
        shopDAOResponse.setResults(shops);
        shopDAOResponse.setErrorContainer(errorContainer);
        return shopDAOResponse;
    }

    /**
     * This method is to retrieve all the shops values from the DB
     *
     * @return List<Shop></> Return a list of {@link Shop}
     */

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public DAOResponse<Shop> getShops(boolean isError) {

        List<Shop> shops = null;
        DAOResponse<Shop> shopDAOResponse = new DAOResponse<>();
        ErrorContainer errorContainer = null;
        if ( isError ) {
            errorContainer = new ErrorContainer( );
        }
        log.info("**Inside shopDAO.getShops()***");
        try {
            this.openDBTransaction();
            shops = (List<Shop>) session.createQuery(" from Shop").list();
//            transaction.commit();
        } catch (HibernateException exception) {
            this.handleExceptions(exception);
            log.error("Error occurred while trying to fetch data from shops table for shopDAO" + ".getShops()",
                    exception);
            if (isError) {
                String location = this.getClass().getCanonicalName() + "#getShops()";
                errorContainer = this.fillErrorContainer(location, exception);
            }
        } finally {
            this.closeDBTransaction();
        }
        shopDAOResponse.setCount(shops == null ? 0 : shops.size());
        shopDAOResponse.setResults(shops);
        shopDAOResponse.setErrorContainer(errorContainer);
        return shopDAOResponse;
    }

}
