package com.mana.innovative.utilities;

import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dto.Address;
import com.mana.innovative.dto.Item;
import com.mana.innovative.dto.Shop;
import com.mana.innovative.dto.WorkingHour;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Test dummy dTO object generator.
 */
public class TestDummyDTOObjectGenerator {

    private static final Logger logger = Logger.getLogger( TestDummyDTOObjectGenerator.class );

    /**
     * Gets test shop dTO object.
     *
     * @param shop the shop
     *
     * @return the test shop dTO object
     */
    public static Shop getTestShopDTOObject( Shop shop ) {

        shop.setShopId( TestConstants.TEST_ID );
        shop.setShopOwnId( TestConstants.TEST_OWN_ID );
        shop.setShopName( TestConstants.TEST_NAME );
        shop.setShopWebLink( TestConstants.TEST_WEB_LINK );

        // WorkingHour
        final List< WorkingHour > workingHours = new ArrayList<>( );
        workingHours.add( getTestWorkingHourDTOObject( new WorkingHour( ) ) );
        shop.setWorkingHours( workingHours );

        // Address
        shop.setAddress( getTestAddressDTOObject( new Address( ) ) );
        // Item
        List< Item > items = new ArrayList< Item >( );
        items.add( getTestItemDTOObject( new Item( ) ) );
        shop.setItems( items );

        return shop;
    }

    /**
     * Gets test working hour dTO object.
     *
     * @param workingHour the working hour
     *
     * @return the test working hour dTO object
     */
    public static WorkingHour getTestWorkingHourDTOObject( final WorkingHour workingHour ) {

        workingHour.setWorkingHourId( TestConstants.TEST_ID );
        workingHour.setHoliday( TestConstants.TEST_IS_HOLIDAY );
        workingHour.setWeekend( TestConstants.TEST_IS_WEEKEND );
        workingHour.setOffline( TestConstants.TEST_IS_CLOSED );

        workingHour.setStartTime( TestConstants.TEST_START_TIME );
        workingHour.setEndTime( TestConstants.TEST_END_TIME );
        workingHour.setDay( TestConstants.TEST_DAY );

        return workingHour;
    }

    /**
     * Gets test item dTO object.
     *
     * @param dummyItem the dummy item
     *
     * @return the test item dTO object
     */
    public static Item getTestItemDTOObject( final Item dummyItem ) {

        dummyItem.setItemId( TestConstants.TEST_ID );
        dummyItem.setItemName( TestConstants.TEST_VALUE );
        dummyItem.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
        dummyItem.setItemType( TestConstants.TEST_VALUE );
        dummyItem.setItemSubType( TestConstants.TEST_ITEM_TYPE );
        dummyItem.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

        dummyItem.setItemPrice( ( double ) TestConstants.THREE );
        dummyItem.setWeight( TestConstants.TEST_WEIGHT );
        dummyItem.setQuantity( TestConstants.TEST_QUANTITY );

        dummyItem.setQuantityType( QuantityType.UNIT.toString( ) );
        dummyItem.setWeightedUnit( WeightedUnit.POUND.toString( ) );

        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );

        try {
            dummyItem.setBoughtDate( dateFormat.parse( TestConstants.TEST_BOUGHT_DATE ) );
        } catch ( ParseException exception ) {
            logger.debug( "Date Parse Exception", exception );
        }


        return dummyItem;
    }

    /**
     * Gets test address dTO object.
     *
     * @param address the address
     *
     * @return the test address dTO object
     */
    public static Address getTestAddressDTOObject( final Address address ) {

        address.setAddressId( TestConstants.TEST_ID );
        address.setAddress1( TestConstants.TEST );
        address.setAddress2( TestConstants.TEST );
        address.setDistrict( TestConstants.TEST_DISTRICT );
        address.setCity( TestConstants.TEST_CITY );
        address.setState( TestConstants.TEST_STATE );
        address.setZipCode( TestConstants.TEST_ZIPCODE );

//        address.setLocation( new Location() );
        return address;
    }

    /**
     * Gets test shop DTO ZERO ID object.
     *
     * @param shop the shop
     *
     * @return the test shop DTO ZERO ID object
     */
    public static Shop getTestShopDTOZEROIDObject( final Shop shop ) {
        shop.setShopId( ( long ) TestConstants.ZERO );
        getTestAddressDTOZEROIDObject( shop.getAddress( ) );
        getTestItemDTOZEROIDObject( shop.getItems( ).get( TestConstants.ZERO ) );
        getTestWorkingHourDTOZEROIDObject( shop.getWorkingHours( ).get( TestConstants.ZERO ) );
        return shop;
    }

    /**
     * Gets test address DTO ZERO ID object.
     *
     * @param address the address
     *
     * @return the test address DTO ZERO ID object
     */
    public static Address getTestAddressDTOZEROIDObject( final Address address ) {
        address.setAddressId( ( long ) TestConstants.ZERO );
        return address;
    }

    /**
     * Gets test item dTO ZERO ID object.
     *
     * @param item the item
     *
     * @return the test item dTO ZERO ID object
     */
    public static Item getTestItemDTOZEROIDObject( final Item item ) {

        item.setItemId( ( long ) TestConstants.ZERO );
        return item;
    }

    /**
     * Gets test working hour DTO ZERO ID object.
     *
     * @param workingHour the working hour
     *
     * @return the test working hour DTO ZERO ID object
     */
    public static WorkingHour getTestWorkingHourDTOZEROIDObject( final WorkingHour workingHour ) {

        workingHour.setWorkingHourId( ( long ) TestConstants.ZERO );
        return workingHour;
    }
}
