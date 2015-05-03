package com.mana.innovative.utilities;

import com.mana.innovative.constants.CardType;
import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.dto.client.Item;
import com.mana.innovative.dto.client.Shop;
import com.mana.innovative.dto.client.WorkingHour;
import com.mana.innovative.dto.common.Address;
import com.mana.innovative.dto.common.CalendarEvent;
import com.mana.innovative.dto.common.Phone;
import com.mana.innovative.dto.common.SidebarType;
import com.mana.innovative.dto.common.Tab;
import com.mana.innovative.dto.consumer.Card;
import com.mana.innovative.dto.consumer.CreditCard;
import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.Preference;
import com.mana.innovative.dto.consumer.User;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Test dummy dTO object generator.
 * <p/>
 * Created by Bloom/Rono on 04/29/2015 14:08 PM..
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class TestDummyDTOObjectGenerator {

    /**
     * The constant logger.
     */
    private static final Logger logger = Logger.getLogger( TestDummyDTOObjectGenerator.class );

    /**
     * Gets test shop dTO object.
     *
     * @return the test shop dTO object
     */
    public static Shop getTestShopDTOObject( ) {

        Shop shop = new Shop( );
        shop.setShopId( TestConstants.TEST_ID );
        shop.setShopOwnId( TestConstants.TEST_OWN_ID );
        shop.setShopName( TestConstants.TEST_NAME );
        shop.setShopWebLink( TestConstants.TEST_WEB_LINK );

        // WorkingHour
        final List< WorkingHour > workingHours = new ArrayList<>( );
        workingHours.add( getTestWorkingHourDTOObject( ) );
        shop.setWorkingHours( workingHours );

        // Address
        shop.setAddress( getTestAddressDTOObject( ) );
        // Item
        List< Item > items = new ArrayList<>( );
        items.add( getTestItemDTOObject( ) );
        shop.setItems( items );

        return shop;
    }

    /**
     * Gets test working hour dTO object.
     *
     * @return the test working hour dTO object
     */
    public static WorkingHour getTestWorkingHourDTOObject( ) {

        final WorkingHour workingHour = new WorkingHour( );
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
     * @return the test item dTO object
     */
    public static Item getTestItemDTOObject( ) {

        final Item dummyItem = new Item( );
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

        DateFormat dateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );

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
     * @return the test address dTO object
     */
    public static Address getTestAddressDTOObject( ) {

        final Address address = new Address( );
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
     * Gets test address DTO ZERO ID object.
     *
     * @param address the address
     *
     * @return the test address DTO ZERO ID object
     */
    public static Address setTestAddressDTOZEROIDObject( final Address address ) {
        address.setAddressId( ( long ) TestConstants.ZERO );
        return address;
    }

    /**
     * Gets test user domain object.
     *
     * @return the test user domain object
     */
    public static User getTestUserDTOObject( ) {

        return getNCreateDTOUserList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test tab dTO object.
     *
     * @return the test tab dTO object
     */
    public static Tab getTestTabDTOObject( ) {
        return getNCreateDTOTabList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test customer dTO object.
     *
     * @return the test customer dTO object
     */
    public static Customer getTestCustomerDTOObject( ) {

        return getNCreateDTOCustomerList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test card dTO object.
     *
     * @return the test card dTO object
     */
    public static Card getTestCardDTOObject( ) {
        return getNCreateDTOCardsList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test credit card dTO object.
     *
     * @return the test credit card dTO object
     */
    public static CreditCard getTestCreditCardDTOObject( ) {

        return getNCreateDTOCreditCardsList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test preference dTO object.
     *
     * @return the test preference dTO object
     */
    public static Preference getTestPreferenceDTOObject( ) {

        return getNCreateDTOPreferenceList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test preference dTO object.
     *
     * @return the test preference dTO object
     */
    public static Phone getTestPhoneDTOObject( ) {

        return getNCreateDTOPhoneList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test phone dTO object.
     *
     * @return the test phone dTO object
     */
    public static CalendarEvent getTestCalendarEventDTOObject( ) {

        return getNCreateDTOCalendarEventList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets n create dTO tab list.
     *
     * @return the n create dTO tab list
     */
    public static List< Tab > getNCreateDTOTabList( ) {

        List< Tab > tabList = new ArrayList<>( );
        Tab tab;

        for ( int i = 0; i < 10; i++ ) {
            tab = new Tab( );
            tab.setTabId( i );
            tab.setTabContent( "Content " + i );
            tab.setTabName( "name " + i );
            tabList.add( tab );
        }
        return tabList;
    }

    /**
     * Gets n create dTO user list.
     *
     * @return the n create dTO user list
     */
    private static List< User > getNCreateDTOUserList( ) {

        List< User > userList = new ArrayList<>( );
        User user;

        for ( int i = 0; i < 10; i++ ) {
            user = new com.mana.innovative.dto.consumer.User( );

            user.setUserId( ( long ) i );
            user.setEmail( i + TestConstants.TEST_EMAIL );
            user.setPassword( TestConstants.TEST_PASS + i );
            user.setUserName( TestConstants.TEST_NAME + i );

            userList.add( user );
        }
        return userList;
    }

    /**
     * Gets n create dTO customer list.
     *
     * @return the n create dTO customer list
     */
    private static List< Customer > getNCreateDTOCustomerList( ) {

        List< Customer > customerList = new ArrayList<>( );
        Customer customer;
        for ( int i = 0; i < 10; i++ ) {
            customer = new Customer( );
            customer.setUserId( ( long ) i );
            customer.setEmail( i + TestConstants.TEST_EMAIL );
            customer.setPassword( TestConstants.TEST_PASS + i );
            customer.setUserName( TestConstants.TEST_NAME + i );

            customer.setFirstName( TestConstants.TEST_NAME + i );
            customer.setLastName( TestConstants.TEST_NAME + i );
            customer.setMiddleName( TestConstants.TEST_NAME + i );

            customer.setCards( getNCreateDTOCreditCardsList( ).subList( TestConstants.ZERO, TestConstants.ONE ) );
            customer.setPhones( getNCreateDTOPhoneList( ).subList( TestConstants.ZERO, TestConstants.ONE ) );
            customer.setPreferences( getNCreateDTOPreferenceList( ).subList( TestConstants.ZERO, TestConstants.ONE ) );

            List< Address > addressList = new ArrayList<>( );
            addressList.add( getTestAddressDTOObject( ) );
            customer.setShippingAddress( addressList );
            setTestAddressDTOZEROIDObject( customer.getShippingAddress( ).get( TestConstants.ZERO ) );
            customerList.add( customer );

        }
        return customerList;
    }

    public static List< Card > getNCreateDTOCardsList( ) {

        List< Card > cardList = new ArrayList<>( );
        Card card;
        for ( int i = 0; i < 10; i++ ) {
            card = new Card( );
            card.setCardId( ( long ) i );

            card.setFirstName( TestConstants.TEST_NAME + i );
            card.setLastName( TestConstants.TEST_NAME + i );

            card.setIssueDate( TestConstants.TEST_ISSUE );

            if ( i % 2 == 0 ) {
                card.setCardHasCustomerPic( TestConstants.TEST_TRUE );
                card.setPictureLocation( TestConstants.TEST );
            } else {
                card.setCardHasCustomerPic( false );
                card.setPictureLocation( "" );
            }

            if ( i < 3 ) {
                card.setCardNumber( TestConstants.VALID_CC_NUMBER );
            } else if ( i >= 3 && i < 6 ) {
                card.setCardNumber( "556373653467325" + i );

            } else if ( i >= 6 && i < 8 ) {
                card.setCardNumber( "656373653467325" + i );

            } else {
                card.setCardNumber( "356373653467324" + i );
            }
            cardList.add( card );
        }
        return cardList;
    }

    /**
     * Gets n create dTO credit cards list.
     *
     * @return the n create dTO credit cards list
     */
    public static List< CreditCard > getNCreateDTOCreditCardsList( ) {

        List< CreditCard > creditCardList = new ArrayList<>( );
        CreditCard creditCard;
        for ( int i = 0; i < 1; i++ ) {
            creditCard = new CreditCard( );
            creditCard.setCardId( ( long ) i );

            creditCard.setFirstName( TestConstants.TEST_NAME + i );
            creditCard.setLastName( TestConstants.TEST_NAME + i );
            creditCard.setMiddleName( TestConstants.TEST_NAME + i );

            try {
                creditCard.setCreateOrModified( new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT ).parse(
                        TestConstants.DEFAULT_DATE ) );
            } catch ( ParseException exception ) {
                logger.error( "Failed to create dummy creditCard createOrModified date", exception );
            }

            creditCard.setExpiryDate( TestConstants.TEST_EXPIRY );
            creditCard.setIssueDate( TestConstants.TEST_ISSUE );

            if ( i % 2 == 0 ) {
                creditCard.setCardHasCustomerPic( TestConstants.TEST_TRUE );
                creditCard.setPictureLocation( TestConstants.TEST );
            } else {
                creditCard.setCardHasCustomerPic( false );
                creditCard.setPictureLocation( "" );
            }

            if ( i < 3 ) {
                creditCard.setCardNumber( TestConstants.VALID_CC_NUMBER );
                creditCard.setCardType( CardType.Visa.toString( ) );
            } else if ( i >= 3 && i < 6 ) {
                creditCard.setCardNumber( "55637365346732" + i );
                creditCard.setCardType( CardType.MasterCard.toString( ) );

            } else if ( i >= 6 && i < 8 ) {
                creditCard.setCardNumber( "65637365346732" + i );
                creditCard.setCardType( CardType.Discover.toString( ) );

            } else {
                creditCard.setCardNumber( "35637365346732" + i );
                creditCard.setCardType( CardType.AmericanExpress.toString( ) );
            }

            creditCard.setCVV( "" + TestConstants.TEST_CVV );
            creditCardList.add( creditCard );

        }
        return creditCardList;
    }

    /**
     * Gets n create dTO phone list.
     *
     * @return the n create dTO phone list
     */
    public static List< Phone > getNCreateDTOPhoneList( ) {

        List< Phone > phoneList = new ArrayList<>( );
        Phone phone;
        for ( int i = 0; i < 10; i++ ) {
            phone = new Phone( );
            phone.setPhoneNumber( i + TestConstants.TEST_PH_NO );
            phone.setPhoneName( TestConstants.TEST_NAME );
            phone.setPhoneCarrier( TestConstants.TEST + i );

            try {
                phone.setBoughtDate( new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT ).parse(
                        TestConstants.TEST_BOUGHT_DATE ) );
            } catch ( ParseException exception ) {
                logger.error( "Failed to create dummy phone boughtDate", exception );
            }
            phone.setPhoneModel( TestConstants.TEST + i );
            phone.setPhoneType( TestConstants.TEST + i );

            phone.setPhoneId( ( long ) i );
            phoneList.add( phone );
        }
        return phoneList;
    }

    /**
     * Gets n create dTO preference list.
     *
     * @return the n create dTO preference list
     */
    public static List< Preference > getNCreateDTOPreferenceList( ) {

        List< Preference > preferenceList = new ArrayList<>( );
        Preference preference;
        for ( int i = 0; i < 10; i++ ) {
            preference = new Preference( );

            preference.setPreferenceId( ( long ) i );
            preference.setPreferenceName( TestConstants.TEST_NAME + i );
            preference.setPreferred( i % 2 == 0 );

            preferenceList.add( preference );
        }
        return preferenceList;
    }

    /**
     * Gets n create dTO calendar event list.
     *
     * @return the n create dTO calendar event list
     */
    public static List< CalendarEvent > getNCreateDTOCalendarEventList( ) {

        List< CalendarEvent > calendarEventList = new ArrayList<>( );
        CalendarEvent calendarEvent;
        for ( int i = 0; i < 10; i++ ) {

            calendarEvent = new CalendarEvent( );
            calendarEvent.setName( TestConstants.TEST_NAME + i );
            calendarEvent.setOptional( TestConstants.TEST + i );
            calendarEvent.setEventName( TestConstants.TEST_NAME + i );
            calendarEvent.setEventDescription( TestConstants.TEST + i * 2 );
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );
            try {
                calendarEvent.setEventStartDate( simpleDateFormat.parse(
                        TestConstants.DEFAULT_DATE ) );
                calendarEvent.setEventEndDate( simpleDateFormat.parse(
                        TestConstants.DEFAULT_DATE ) );
            } catch ( ParseException exception ) {
                logger.error( "Failed to parse date string " + TestConstants.TEST_DATE_FORMAT, exception );
            }
            calendarEvent.setCalendarEventId( ( long ) i );
            calendarEventList.add( calendarEvent );
        }
        return calendarEventList;
    }

    /**
     * Gets test sidebar type dTO object.
     *
     * @return the test sidebar type dTO object
     */
    public static SidebarType getTestSidebarTypeDTOObject( ) {
        return getNCreateSidebarList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets n create sidebar list.
     *
     * @return the n create sidebar list
     */
    public static List< SidebarType > getNCreateSidebarList( ) {

        List< SidebarType > sidebarTypeList = new ArrayList<>( );
        SidebarType sidebarType;

        for ( int i = 0; i < 10; i++ ) {
            sidebarType = new SidebarType( );

            sidebarType.setSidebarTypeId( ( long ) i );
            sidebarType.setTypePriority( i * 100 + i );
            sidebarType.setSuccess( TestConstants.TEST_TRUE );

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_DATE_FORMAT );
            try {
                sidebarType.setDueDate( simpleDateFormat.parse( TestConstants.DEFAULT_DATE ) );
            } catch ( Exception exception ) {
                logger.error( "Parse exception for date testing", exception );
            }
            sidebarType.setItemType( TestConstants.TEST_ITEM_TYPE );
            sidebarType.setSubject( TestConstants.TEST_SUBJECT );
            sidebarType.setGroupCount( i );
            sidebarType.setGroupPriority( TestConstants.ZERO );
            sidebarType.setGroupType( TestConstants.TEST );

            sidebarTypeList.add( sidebarType );
        }

        return sidebarTypeList;
    }

}
