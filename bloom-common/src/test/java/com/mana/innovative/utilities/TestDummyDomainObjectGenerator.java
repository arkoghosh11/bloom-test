package com.mana.innovative.utilities;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.domain.Address;
import com.mana.innovative.domain.Item;
import com.mana.innovative.domain.Shop;
import com.mana.innovative.domain.WorkingHour;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bloom/Rono on 3/7/2015. This class is for .. ToDo
 */
public class TestDummyDomainObjectGenerator {

    private static final Logger logger = Logger.getLogger( TestDummyDomainObjectGenerator.class );

    /**
     * Gets test shop dTO object.
     *
     * @param shop the shop
     *
     * @return the test shop dTO object
     */
    public static Shop getTestShopDomainObject( Shop shop ) {

        shop.setShopId( TestConstants.TEST_ID );
        shop.setShopOwnId( TestConstants.TEST_OWN_ID );
        shop.setShopName( TestConstants.TEST_NAME );
        shop.setShopWebLink( TestConstants.TEST_WEB_LINK );

        // WorkingHour
        final List< WorkingHour > workingHours = new ArrayList<>( );
        workingHours.add( getTestWorkingHourDomainObject( new WorkingHour( ) ) );
        shop.setWorkingHours( workingHours );

        // Address
        shop.setAddress( getTestAddressDomainObject( new Address( ) ) );
        // Item
        List< Item > items = new ArrayList<>( );
        items.add( getTestItemDomainObject( new Item( ) ) );
        shop.setItems( items );

        return shop;
    }

    public static WorkingHour getTestWorkingHourDomainObject( final WorkingHour workingHour ) {

        workingHour.setWorkingHourId( TestConstants.TEST_ID );
        workingHour.setHoliday( TestConstants.TEST_IS_HOLIDAY );
        workingHour.setWeekend( TestConstants.TEST_IS_WEEKEND );
        workingHour.setOffline( TestConstants.TEST_IS_CLOSED );

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_TIME_FORMAT );
        Date time, today = new Date( );
        try {
            time = simpleDateFormat.parse( TestConstants.TEST_START_TIME );
            workingHour.setStartTime( time );
        } catch ( ParseException e ) {
            workingHour.setStartTime( today );
        }
        try {
            time = simpleDateFormat.parse( TestConstants.TEST_END_TIME );
            workingHour.setEndTime( time );
        } catch ( ParseException e ) {
            workingHour.setEndTime( today );
        }
        workingHour.setDay( TestConstants.TEST_DAY );

        return workingHour;
    }

    /**
     * Gets test item dTO object.
     *
     * @param item the dummy item
     *
     * @return the test item dTO object
     */
    public static Item getTestItemDomainObject( final Item item ) {

        item.setItemId( TestConstants.TEST_ID );
        item.setItemName( TestConstants.TEST_VALUE );
        item.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
        item.setItemType( TestConstants.TEST_VALUE );
        item.setItemSubType( TestConstants.TEST_ITEM_TYPE );
        item.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

        item.setItemPrice( ( double ) TestConstants.THREE );
        item.setWeight( TestConstants.TEST_WEIGHT );
        item.setQuantity( TestConstants.TEST_QUANTITY );

        item.setQuantityType( QuantityType.UNIT.toString( ) );
        item.setWeightedUnit( WeightedUnit.POUND.toString( ) );

        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );

        try {
            item.setBoughtDate( dateFormat.parse( TestConstants.TEST_BOUGHT_DATE ) );
        } catch ( ParseException exception ) {
            logger.debug( "Date Parse Exception", exception );
        }

        return item;
    }

    /**
     * Gets test address dTO object.
     *
     * @param address the address
     *
     * @return the test address dTO object
     */
    public static Address getTestAddressDomainObject( final Address address ) {

        address.setAddressId( TestConstants.TEST_ID );
        address.setAddress1( TestConstants.TEST );
        address.setAddress2( TestConstants.TEST );
        address.setDistrict( TestConstants.TEST_DISTRICT );
        address.setCity( TestConstants.TEST_CITY );
        address.setState( TestConstants.TEST_STATE );
        address.setZipCode( TestConstants.TEST_ZIPCODE );

//        address.setLocation( new Location( ) );
        return address;
    }

    /**
     * Gets test shop Domain ZERO ID object.
     *
     * @param shop the shop
     *
     * @return the test shop Domain ZERO ID object
     */
    public static Shop getTestShopDomainZEROIDObject( final Shop shop ) {

        shop.setShopId( TestConstants.ZERO );
        getTestAddressDomainZEROIDObject( shop.getAddress( ) );
        getTestItemDomainZEROIDObject( shop.getItems( ).get( TestConstants.ZERO ) );
        getTestWorkingHourDomainZEROIDObject( shop.getWorkingHours( ).get( TestConstants.ZERO ) );

        return shop;
    }

    /**
     * Gets test address Domain ZERO ID object.
     *
     * @param address the address
     *
     * @return the test address Domain ZERO ID object
     */
    public static Address getTestAddressDomainZEROIDObject( final Address address ) {

        address.setAddressId( TestConstants.ZERO );
        return address;
    }

    /**
     * Gets test item dTO ZERO ID object.
     *
     * @param item the item
     *
     * @return the test item dTO ZERO ID object
     */
    public static Item getTestItemDomainZEROIDObject( final Item item ) {

        item.setItemId( TestConstants.ZERO );
        return item;
    }

    /**
     * Gets test working hour Domain ZERO ID object.
     *
     * @param workingHour the working hour
     *
     * @return the test working hour Domain ZERO ID object
     */
    public static WorkingHour getTestWorkingHourDomainZEROIDObject( final WorkingHour workingHour ) {

        workingHour.setWorkingHourId( TestConstants.ZERO );
        return workingHour;
    }
}
