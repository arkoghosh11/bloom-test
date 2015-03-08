package com.mana.innovative.dao;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.domain.Address;
import com.mana.innovative.domain.Item;
import com.mana.innovative.domain.Shop;
import com.mana.innovative.domain.WorkingHour;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Bloom/Rono on 3/3/2015.
 * This class is for .. ToDo
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true, transactionManager = "transactionManager" )
@Transactional()
public class WhenCreateAShopTestShopDAOCreateShop {

    @Resource
    private ShopDAO shopDAO;

    @Resource
    private AddressDAO addressDAO;

    @Resource
    private WorkingHourDAO workingHourDAO;

    private Shop dummyShop;
    private Item dummyItem;
    private Address address;
    private WorkingHour workingHour;

    @Before
    public void setUp( ) throws Exception {

        dummyShop = new Shop( );
        dummyItem = new Item( );


        dummyItem.setItemId( TestConstants.MINUS_ONE );
        dummyItem.setItemName( TestConstants.TEST_VALUE );
        dummyItem.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
        dummyItem.setItemType( TestConstants.TEST_VALUE );
        dummyItem.setItemSubType( TestConstants.TEST_ITEM_TYPE );
        dummyItem.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

        dummyItem.setItemPrice( TestConstants.THREE );
        dummyItem.setWeight( TestConstants.TEST_WEIGHT );
        dummyItem.setQuantity( TestConstants.TEST_QUANTITY );

        dummyItem.setQuantityType( QuantityType.UNIT.toString( ) );
        dummyItem.setWeightedUnit( WeightedUnit.POUND.toString( ) );

        dummyItem.setCreatedDate( new Date( ) );
        dummyItem.setBoughtDate( new Date( ) );

        dummyShop.setShopId( TestConstants.MINUS_ONE );
        dummyShop.setShopOwnId( TestConstants.TEST_OWN_ID );
        dummyShop.setShopName( TestConstants.TEST_NAME );
        dummyShop.setShopWebLink( TestConstants.TEST_WEB_LINK );
    }

    private void getNSetAddress( ) {

    }

    private void getNSetWorkingHours( ) {

    }

    @Test
    public void testCreateShopWithExistingItem( ) throws Exception {


    }

    @Test
    public void testCreateShopWithNewItems( ) throws Exception {

    }

    @Test
    public void testCreateShopWithNoItems( ) throws Exception {

    }
}
