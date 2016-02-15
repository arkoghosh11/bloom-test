package com.mana.innovative.dao;

import com.mana.innovative.constants.CardType;
import com.mana.innovative.constants.QuantityType;
import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.constants.WeightedUnit;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.domain.client.Item;
import com.mana.innovative.domain.client.ItemDiscount;
import com.mana.innovative.domain.client.ItemImage;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.domain.client.WorkingHour;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.domain.common.CalendarEvent;
import com.mana.innovative.domain.common.Phone;
import com.mana.innovative.domain.common.SidebarType;
import com.mana.innovative.domain.common.Tab;
import com.mana.innovative.domain.consumer.Card;
import com.mana.innovative.domain.consumer.CreditCard;
import com.mana.innovative.domain.consumer.Customer;
import com.mana.innovative.domain.consumer.Preference;
import com.mana.innovative.domain.consumer.Privilege;
import com.mana.innovative.domain.consumer.User;
import com.mana.innovative.domain.consumer.UserRole;
import com.mana.innovative.utilities.date.DateCommons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bloom/Rono on 3/7/2015. This class is for ..
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class TestDummyDomainObjectGenerator {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( TestDummyDomainObjectGenerator.class );

    /**
     * Gets test shop dTO object.
     *
     * @return the test shop dTO object
     */
    public static Shop getTestShopDomainObject( ) {

        Shop shop = new Shop( );
//        shop.setShopId( TestConstants.TEST_ID );
        shop.setShopOwnId( TestConstants.TEST_OWN_ID );
        shop.setShopDescription( TestConstants.TEST_DESCRIPTION );
        shop.setShopName( TestConstants.TEST_NAME );
        shop.setShopWebLink( TestConstants.TEST_WEB_LINK );

        // WorkingHour
        final List< WorkingHour > workingHours = new ArrayList<>( );
        workingHours.add( getTestWorkingHourDomainObject( ) );
        shop.setWorkingHours( workingHours );

        // Address
        shop.setAddress( getTestAddressDomainObject( ) );
        // Item
        List< Item > items = new ArrayList<>( );
        items.add( getTestItemDomainObject( ) );
        shop.setItems( items );

        return shop;
    }

    /**
     * Gets test working hour domain object.
     *
     * @return the test working hour domain object
     */
    public static WorkingHour getTestWorkingHourDomainObject( ) {

        WorkingHour workingHour = new WorkingHour( );
//        workingHour.setWorkingHourId( TestConstants.TEST_ID );
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
     * @return the test item dTO object
     */
    public static Item getTestItemDomainObject( ) {

        final Item item = new Item( );
//        item.setItemId( TestConstants.TEST_ID );
        item.setItemName( TestConstants.TEST_VALUE );
        item.setItemPriceCurrency( TestConstants.TEST_PRICE_CURRENCY );
        item.setItemType( TestConstants.TEST_VALUE );
        item.setItemDescription( TestConstants.TEST_DESCRIPTION );
        item.setItemSubType( TestConstants.TEST_ITEM_TYPE );
        item.setBoughtFrom( TestConstants.TEST_BROUGHT_FROM );

        item.setItemPrice( ( double ) TestConstants.THREE );
        item.setWeight( TestConstants.TEST_WEIGHT );
        item.setQuantity( TestConstants.TEST_QUANTITY );

        item.setQuantityType( QuantityType.UNIT.toString( ) );
        item.setWeightedUnit( WeightedUnit.POUND.toString( ) );

        item.setImageCount( TestConstants.TEST_IMAGE_COUNT );

        List< ItemDiscount > itemDiscountList = new ArrayList<>( );

        itemDiscountList.add( getTestItemDiscountDomainObject( ) );
        item.setItemDiscountList( itemDiscountList );

        List< ItemImage > itemImageList = new ArrayList<>( );
        itemImageList.add( getTestItemImageDomainObject( null ) );
        item.setItemImageList( itemImageList );

        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

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
     * @return the test address dTO object
     */
    public static Address getTestAddressDomainObject( ) {

        final Address address = new Address( );
//        address.setAddressId( TestConstants.TEST_ID );
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
     * Gets test address Domain ZERO ID object.
     *
     * @param address the address
     *
     * @return the test address Domain ZERO ID object
     */
    public static Address setTestAddressDomainZEROIDObject( final Address address ) {

        address.setAddressId( TestConstants.ZERO );
        return address;
    }

    /**
     * Gets test tab domain object.
     *
     * @return the test tab domain object
     */
    public static Tab getTestTabDomainObject( ) {
        return getNCreateTabDomainList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test user domain object.
     *
     * @return the test user domain object
     */
    public static User getTestUserDomainObject( ) {
        return getNCreateDomainUserList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test customer domain object.
     *
     * @return the test customer domain object
     */
    public static Customer getTestCustomerDomainObject( ) {
        return getNCreateDomainCustomerList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test card domain object.
     *
     * @return the test card domain object
     */
    public static Card getTestCardDomainObject( ) {
        return getNCreateDomainCardsList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test credit card domain object.
     *
     * @return the test credit card domain object
     */
    public static CreditCard getTestCreditCardDomainObject( ) {

        return getNCreateDomainCreditCardsList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test preference domain object.
     *
     * @return the test preference domain object
     */
    public static Preference getTestPreferenceDomainObject( ) {

        return getNCreateDomainPreferenceList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test phone domain object.
     *
     * @return the test phone domain object
     */
    public static Phone getTestPhoneDomainObject( ) {

        return getNCreateDomainPhoneList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets test sidebar type domain object.
     *
     * @return the test sidebar type domain object
     */
    public static SidebarType getTestSidebarTypeDomainObject( ) {

        return getNCreateSidebarDomainList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test calendar event domain object.
     *
     * @return the test calendar event domain object
     */
    public static CalendarEvent getTestCalendarEventDomainObject( ) {

        return getNCreateDomainCalendarEventList( ).get( TestConstants.ZERO );

    }

    /**
     * Gets n create tab domain data.
     *
     * @return the n create tab domain data
     */
    public static List< Tab > getNCreateTabDomainList( ) {

        List< Tab > tabList = new ArrayList<>( );
        Tab tab;
        for ( int i = 0; i < 10; i++ ) {
            tab = new Tab( );
//            tab.setTabId( i );
            tab.setTabContent( "Content " + i );
            tab.setTabName( "name " + i );
            tab.setTabColor( "#00000 " + i );
            tab.setTabPosition( "top " + i );
            tabList.add( tab );
        }
        return tabList;
    }

    /**
     * Gets n create domain user data.
     *
     * @return the n create domain user data
     */
    private static List< User > getNCreateDomainUserList( ) {

        List< User > userList = new ArrayList<>( );
        User user;
//        Address address;
        for ( int i = 0; i < 10; i++ ) {
            user = new User( );

//            user.setUserId( i );
            user.setEmail( i + TestConstants.TEST_EMAIL );
            user.setPassword( TestConstants.TEST_PASS + i );
            user.setUserName( TestConstants.TEST_NAME + i );

            userList.add( user );
        }
        return userList;
    }

    /**
     * Gets n create domain customer data.
     *
     * @return the n create domain customer data
     */
    private static List< Customer > getNCreateDomainCustomerList( ) {

        List< Customer > customerList = new ArrayList<>( );
        Customer customer;
        for ( int i = 0; i < 10; i++ ) {
            customer = new Customer( );
//            customer.setUserId( i );
//           Set User Id
            customer.setEmail( i + TestConstants.TEST_EMAIL );
            customer.setPassword( TestConstants.TEST_PASS + i );
            customer.setUserName( TestConstants.TEST_NAME + i );
//          Set Customer Id
            customer.setFirstName( TestConstants.TEST_NAME + i );
            customer.setLastName( TestConstants.TEST_NAME + i );
            customer.setMiddleName( TestConstants.TEST_NAME + i );

            customer.setCards( getNCreateDomainCreditCardsList( ).subList( TestConstants.ZERO, TestConstants.ONE ) );
            customer.setPhones( getNCreateDomainPhoneList( ).subList( TestConstants.ZERO, TestConstants.ONE ) );
            customer.setPreferences( getNCreateDomainPreferenceList( ).subList( TestConstants.ZERO, TestConstants.ONE ) );

            List< Address > addressList = new ArrayList<>( );
            addressList.add( getTestAddressDomainObject( ) );
            customer.setShippingAddress( addressList );
            setTestAddressDomainZEROIDObject( customer.getShippingAddress( ).get( TestConstants.ZERO ) );
            customerList.add( customer );

        }
        return customerList;
    }

    /**
     * Gets n create domain cards list.
     *
     * @return the n create domain cards list
     */
    public static List< Card > getNCreateDomainCardsList( ) {

        List< Card > cardList = new ArrayList<>( );
        Card card;
        for ( int i = 0; i < 10; i++ ) {
            card = new Card( );
//            card.setCardId( i );

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
     * Gets n create domain cards data.
     *
     * @return the n create domain cards data
     */
    public static List< CreditCard > getNCreateDomainCreditCardsList( ) {

        List< CreditCard > creditCardList = new ArrayList<>( );
        CreditCard creditCard;
        for ( int i = 0; i < 10; i++ ) {
            creditCard = new CreditCard( );
//            creditCard.setCardId( i );

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
                creditCard.setCardNumber( "556373653467325" + i );
                creditCard.setCardType( CardType.MasterCard.toString( ) );

            } else if ( i >= 6 && i < 8 ) {
                creditCard.setCardNumber( "656373653467325" + i );
                creditCard.setCardType( CardType.Discover.toString( ) );

            } else {
                creditCard.setCardNumber( "356373653467324" + i );
                creditCard.setCardType( CardType.AmericanExpress.toString( ) );
            }

            creditCard.setCVV( TestConstants.TEST_CVV );
            creditCardList.add( creditCard );

        }
        return creditCardList;
    }

    /**
     * Gets n create domain phone data.
     *
     * @return the n create domain phone data
     */
    public static List< Phone > getNCreateDomainPhoneList( ) {

        List< Phone > phoneList = new ArrayList<>( );
        Phone phone;
        for ( int i = 0; i < 10; i++ ) {

            phone = new Phone( );

//            phone.setPhoneId( i );
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

            phoneList.add( phone );
        }
        return phoneList;
    }

    /**
     * Gets n create domain preference data.
     *
     * @return the n create domain preference data
     */
    public static List< Preference > getNCreateDomainPreferenceList( ) {

        List< Preference > preferenceList = new ArrayList<>( );
        Preference preference;
        for ( int i = 0; i < 10; i++ ) {
            preference = new Preference( );

//            preference.setPreferenceId( i );
            preference.setPreferenceName( TestConstants.TEST_NAME + i );
            preference.setIsPreferred( i % 2 == 0 );

            preferenceList.add( preference );
        }
        return preferenceList;
    }

    /**
     * Gets n create domain calendar event.
     *
     * @return the n create domain calendar event
     */
    public static List< CalendarEvent > getNCreateDomainCalendarEventList( ) {

        List< CalendarEvent > calendarEventList = new ArrayList<>( );
        CalendarEvent calendarEvent;
        for ( int i = 0; i < 10; i++ ) {

            calendarEvent = new CalendarEvent( );

//            calendarEvent.setCalendarEventId( i );
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
            calendarEventList.add( calendarEvent );
        }
        return calendarEventList;
    }

    /**
     * Gets n create sidebar domain list.
     *
     * @return the n create sidebar domain list
     */
    public static List< SidebarType > getNCreateSidebarDomainList( ) {

        List< SidebarType > sidebarTypeList = new ArrayList<>( );
        SidebarType sidebarType;

        for ( int i = 0; i < 10; i++ ) {
            sidebarType = new SidebarType( );

//            sidebarType.setSidebarTypeId( ( long ) i );
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

    /**
     * Gets test user role domain object.
     *
     * @return the test user role domain object
     */
    public static UserRole getTestUserRoleDomainObject( ) {

        UserRole userRole = new UserRole( );

//        userRole.setUserRoleId( TestConstants.ZERO );
        userRole.setUserRoleName( TestConstants.DEFAULT + TestConstants.ONE );
        userRole.setIsActive( TestConstants.TEST_TRUE );
        userRole.setIsExpired( TestConstants.TEST_FALSE );
        userRole.setIsLocked( TestConstants.TEST_FALSE );
        userRole.setPrivileges( getNCreatePrivilegeDomainList( ) );

        return userRole;
    }

    /**
     * Gets n create privilege domain list.
     *
     * @return the n create privilege domain list
     */
    public static List< Privilege > getNCreatePrivilegeDomainList( ) {

        List< Privilege > privilegeList = new ArrayList<>( );
        Privilege privilege;

        privilege = new Privilege( );
//        privilege.setPrivilegeId( 0 );
        privilege.setPrivilegeName( TestConstants.TEST_NAME );
        privilege.setAccessible( TestConstants.TEST_TRUE );
        privilege.setCreatedDate( new Date( ) );
        privilege.setUpdatedDate( new Date( ) );
        privilegeList.add( privilege );

        return privilegeList;
    }

    /**
     * Gets test privilege domain object.
     *
     * @return the test privilege domain object
     */
    public static Privilege getTestPrivilegeDomainObject( ) {

        return getNCreatePrivilegeDomainList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets test shop Domain ZERO ID object.
     *
     * @param itemDiscount the ItemDiscount
     *
     * @return the test shop Domain ZERO ID object
     */
    public static ItemDiscount setTestItemDiscountDomainZEROIDObject( final ItemDiscount itemDiscount ) {

        itemDiscount.setItemDiscountId( TestConstants.ZERO );
        return itemDiscount;
    }

    /**
     * Gets test item discount domain object.
     *
     * @return the test item discount domain object
     */
    public static ItemDiscount getTestItemDiscountDomainObject( ) {

        ItemDiscount itemDiscount = new ItemDiscount( );
        itemDiscount.setItemDiscountId( TestConstants.ZERO );
        itemDiscount.setDiscountPercent( TestConstants.DEFAULT_ITEM_DISCOUNT_PERCENT );
        itemDiscount.setDiscountType( TestConstants.DEFAULT_ITEM_DISCOUNT_TYPE );
        itemDiscount.setUserRole( TestConstants.DEFAULT_USER_ROLE_NAME );
        itemDiscount.setIsActive( TestConstants.DEFAULT_IS_ACTIVE );

        try {
            itemDiscount.setStartDate( DateCommons.getDateFromDateString( TestConstants.TEST_START_DATE, TestConstants.TEST_DATE_FORMAT ) );
            itemDiscount.setEndDate( DateCommons.getDateFromDateString( TestConstants.TEST_END_DATE, TestConstants.TEST_DATE_FORMAT ) );
        } catch ( Exception exception ) {
            logger.debug( "Date Parse Exception", exception );
        }
        return itemDiscount;
    }

    /**
     * Gets n create item discount domain list.
     *
     * @return the n create item discount domain list
     */
    public static List< ItemDiscount > getNCreateItemDiscountDomainList( ) {

        List< ItemDiscount > itemDiscountList = new ArrayList<>( );
        ItemDiscount itemDiscount;
        for ( int i = 1; i < 5; i++ ) {
            itemDiscount = new ItemDiscount( );
            itemDiscount.setItemDiscountId( i );
            itemDiscount.setDiscountPercent( TestConstants.DEFAULT_ITEM_DISCOUNT_PERCENT );
            itemDiscount.setDiscountType( TestConstants.DEFAULT_ITEM_DISCOUNT_TYPE + i );
            itemDiscount.setUserRole( TestConstants.DEFAULT_USER_ROLE_NAME );
            itemDiscount.setIsActive( i % 2 == 0 );

            DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

            try {
                itemDiscount.setStartDate( dateFormat.parse( TestConstants.TEST_START_DATE ) );
                itemDiscount.setEndDate( dateFormat.parse( TestConstants.TEST_END_DATE ) );
            } catch ( ParseException exception ) {
                logger.debug( "Date Parse Exception", exception );
            }

            itemDiscountList.add( itemDiscount );
        }
        return itemDiscountList;
    }

    /**
     * Gets test item image domain object.
     *
     * @return the test item image domain object
     */
    public static ItemImage getTestItemImageDomainObject( Integer id ) {

        ItemImage itemImage = new ItemImage( );
        itemImage.setItemImageId( id == null ? TestConstants.MINUS_ONE : id );
        itemImage.setImageLocation( id == null || id == 0 ? TestConstants.DEFAULT : TestConstants.DEFAULT_IMAGE_LOCATION );
        itemImage.setImagePriority( id == null || id == 0 ? TestConstants.ZERO : TestConstants.DEFAULT_IMAGE_PRIORITY );
        itemImage.setImageHeight( id == null || id == 0 ? TestConstants.ZERO : TestConstants.DEFAULT_IMAGE_HEIGHT );
        itemImage.setImageWidth( id == null || id == 0 ? TestConstants.ZERO : TestConstants.DEFAULT_IMAGE_WIDTH );

        return itemImage;
    }

    /**
     * Gets n create item image domain list.
     *
     * @return the n create item image domain list
     */
    public static List< ItemImage > getNCreateItemImageDomainList( ) {
        List< ItemImage > itemImageList = new ArrayList<>( );
        ItemImage itemImage;
        for ( int i = 1; i < 5; i++ ) {
            itemImage = new ItemImage( );
            itemImage.setItemImageId( i );
            itemImage.setImageLocation( TestConstants.DEFAULT_IMAGE_LOCATION + i );
            itemImage.setImagePriority( TestConstants.DEFAULT_IMAGE_PRIORITY );
            itemImage.setImageHeight( TestConstants.DEFAULT_IMAGE_HEIGHT + i );
            itemImage.setImageWidth( TestConstants.DEFAULT_IMAGE_WIDTH + i );

            itemImageList.add( itemImage );
        }
        return itemImageList;
    }

    /**
     * Sets test item image domain Zero Id object.
     *
     * @param gemstoneDomain the item image domain
     *
     * @return the test item image domain Zero Id object
     */
    public static Gemstone setTestGemstoneDomainZEROIDObject( final Gemstone gemstoneDomain ) {
        gemstoneDomain.setGemstoneId( TestConstants.ZERO );
        return gemstoneDomain;
    }

    /**
     * Gets test gemstone dTO object.
     *
     * @return the test gemstone dTO object
     */
    public static Gemstone getTestGemstoneDomainObject( ) {
        return getNCreateGemstoneDomainList( ).get( TestConstants.ZERO );
    }

    /**
     * Gets n create gemstone dTO list.
     *
     * @return the created gemstone dTO list
     */
    public static List< Gemstone > getNCreateGemstoneDomainList( ) {
        List< Gemstone > gemstoneList = new ArrayList<>( );
        Gemstone gemstone;
        for ( int i = 1; i < 5; i++ ) {
            gemstone = new Gemstone( );
            gemstone.setGemstoneId( i );
            gemstone.setGemstoneName( TestConstants.DEFAULT_GEM_NAME + i );
            gemstone.setGemstoneDescription( TestConstants.DEFAULT_DESCRIPTION );

            gemstoneList.add( gemstone );
        }
        return gemstoneList;
    }
}
