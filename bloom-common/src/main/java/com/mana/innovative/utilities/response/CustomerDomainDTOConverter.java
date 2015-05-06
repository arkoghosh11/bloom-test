package com.mana.innovative.utilities.response;

import com.mana.innovative.dto.common.Address;
import com.mana.innovative.dto.common.Phone;
import com.mana.innovative.dto.consumer.CreditCard;
import com.mana.innovative.dto.consumer.Customer;
import com.mana.innovative.dto.consumer.Preference;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is CustomerDomainDTOConverter
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class CustomerDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomerDomainDTOConverter.class );


    /**
     * Gets converted customer dTO from customer domain.
     *
     * @param customerDTO  the customer dTO
     * @param customerDomain  the customer domain
     * @return the converted customer dTO from customer domain
     */
    public static Customer getConvertedDTOFromDomain( Customer customerDTO, com.mana.innovative.domain.consumer.Customer customerDomain ) {


        if ( customerDomain == null ) {
            String message = "Parameter customerDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( customerDTO == null ) {
            customerDTO = new Customer( );
            logger.warn( " Creating customerDTO, received null object" );
        }
        customerDTO = ( Customer ) UserDomainDTOConverter.getConvertedDTOFromDomain( customerDTO, customerDomain );

        if ( !StringUtils.isEmpty( customerDomain.getFirstName( ) ) ) {
            customerDTO.setFirstName( customerDomain.getFirstName( ) );
        }

        if ( !StringUtils.isEmpty( customerDomain.getLastName( ) ) ) {
            customerDTO.setLastName( customerDomain.getLastName( ) );
        }
        customerDTO.setMiddleName( customerDomain.getMiddleName( ) );

        if ( customerDomain.getCards( ) != null && !customerDomain.getCards( ).isEmpty( ) ) {
            List< CreditCard > creditCardsDTOList = CreditCardDomainDTOConverter.getConvertedListDTOFromDomain( customerDomain.getCards( ) );
            customerDTO.setCards( creditCardsDTOList );
        } else {
            customerDTO.setCards( new ArrayList< CreditCard >( ) );
        }

        if ( customerDomain.getPhones( ) != null && !customerDomain.getPhones( ).isEmpty( ) ) {

            List< Phone > phones = PhoneDomainDTOConverter.getConvertedListDTOFromDomain( customerDomain.getPhones( ) );
            customerDTO.setPhones( phones );
        } else {
            customerDTO.setPhones( new ArrayList< Phone >( ) );
        }
        if ( customerDomain.getPreferences( ) != null && !customerDomain.getPreferences( ).isEmpty( ) ) {
            List< Preference > preferences = PreferenceDomainDTOConverter.getConvertedListDTOFromDomain( customerDomain.getPreferences( ) );
            customerDTO.setPreferences( preferences );
        } else {
            customerDTO.setPreferences( new ArrayList< Preference >( ) );
        }
        if ( customerDomain.getShippingAddress( ) != null ) {
            customerDTO.setShippingAddress( AddressDomainDTOConverter.getConvertedListDTOFromDomain( customerDomain.getShippingAddress( ) ) );
        } else {
            customerDTO.setShippingAddress( new ArrayList< Address >( ) );
        }


        return customerDTO;
    }

    /**
     * Gets converted customer dTO list.
     *
     * @param customers the customers
     * @return the converted customer dTO list
     */
    public static List< Customer > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.consumer.Customer > customers ) {

        List< Customer > customerDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.Customer customer : customers ) {

            Customer customerDTO = new Customer( );
            customerDTO = getConvertedDTOFromDomain( customerDTO, customer );
            customerDTOList.add( customerDTO );
        }
        return customerDTOList;
    }

    /**
     * Gets converted customer domain from customer dTO.
     *
     * @param customerDomain the customer domain
     * @param customerDTO the customer dTO
     * @return the converted customer domain from customer dTO
     */
    public static com.mana.innovative.domain.consumer.Customer getConvertedDomainFromDTO( com.mana.innovative.domain.consumer.Customer customerDomain, Customer customerDTO ) {

        if ( customerDTO == null ) {
            String message = "Parameter customerDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( customerDomain == null ) {
            customerDomain = new com.mana.innovative.domain.consumer.Customer( );
            logger.warn( "Creating customerDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        customerDomain = ( com.mana.innovative.domain.consumer.Customer ) UserDomainDTOConverter
                .getConvertedDomainFromDTO( customerDomain, customerDTO );

        if ( !StringUtils.isEmpty( customerDTO.getFirstName( ) ) ) {
            customerDomain.setFirstName( customerDTO.getFirstName( ) );
        } else {
            flag = true;
            stringBuilder.append( " FirstName," );
        }
        if ( !StringUtils.isEmpty( customerDTO.getLastName( ) ) ) {
            customerDomain.setLastName( customerDTO.getLastName( ) );
        } else {
            flag = true;
            stringBuilder.append( " LastName," );
        }
        if ( !StringUtils.isEmpty( customerDTO.getMiddleName( ) ) ) {
            customerDomain.setMiddleName( customerDTO.getMiddleName( ) );
        } else {
            flag = true;
            stringBuilder.append( " MiddleName," );
        }

        try {
            customerDomain.setShippingAddress( AddressDomainDTOConverter.getConvertedListDomainFromDTO( customerDTO
                    .getShippingAddress( ) ) );
            customerDomain.setCards( CreditCardDomainDTOConverter.getConvertedListDomainFromDTO( customerDTO.getCards( ) ) );
            customerDomain.setPreferences( PreferenceDomainDTOConverter.getConvertedListDomainFromDTO( customerDTO
                    .getPreferences( ) ) );
            customerDomain.setPhones( PhoneDomainDTOConverter.getConvertedListDomainFromDTO( customerDTO.getPhones( ) ) );
        } catch ( IllegalArgumentValueException exception ) {

            flag = true;
            stringBuilder.append( exception.getMessage( ) );
        }

//            customer.setShopCustomer();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return customerDomain;
    }

    /**
     * Gets converted customer domain list from customer dTO list.
     *
     * @param customerDTOList the customer dTO list
     * @return the converted customer domain list from customer dTO list
     */
    public static List< com.mana.innovative.domain.consumer.Customer > getConvertedListDomainFromDTO( List< Customer > customerDTOList ) {

        List< com.mana.innovative.domain.consumer.Customer > customerDomainList = new ArrayList<>( );
        for ( Customer customerDTO : customerDTOList ) {
            com.mana.innovative.domain.consumer.Customer customerDomain = new com.mana.innovative.domain.consumer.Customer( );
            customerDomain = getConvertedDomainFromDTO( customerDomain, customerDTO );
            customerDomainList.add( customerDomain );
        }
        return customerDomainList;
    }

}
