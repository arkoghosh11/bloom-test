package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.consumer.Card;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is CardDomainDTOConverter
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class CardDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CardDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final long ZERO = DAOConstants.ZERO;

    /**
     * The constant EMPTY_STRING.
     */
    private static final String EMPTY_STRING = DAOConstants.EMPTY_STRING;
    /**
     * The constant MONTH_MAX.
     */
    private static final int MONTH_MAX = 12;
    /**
     * The constant MAX_MIN_LIMIT.
     */
    private static final int MAX_MIN_LIMIT = 25;

    /**
     * Gets converted card dTO from card domain.
     *
     * @param cardDTO the card dTO
     * @param cardDomain the card domain
     * @return the converted card dTO from card domain
     */
    public static Card getConvertedDTOFromDomain( Card cardDTO, com.mana.innovative.domain.consumer.Card cardDomain ) {

        if ( cardDomain == null ) {
            String message = "Parameter cardDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( cardDTO == null ) {
            cardDTO = new Card( );
            logger.warn( " Creating cardDTO, received null object" );
        }
        if ( cardDomain.getCardId( ) >= ZERO ) {
            cardDTO.setCardId( cardDomain.getCardId( ) );
        }

        cardDTO.setFirstName( cardDomain.getFirstName( ) );
        cardDTO.setLastName( cardDomain.getLastName( ) );
        cardDTO.setCardNumber( cardDomain.getCardNumber( ) );
        cardDTO.setIssueDate( cardDomain.getIssueDate( ) );
        cardDTO.setCardHasCustomerPic( cardDomain.isCardHasCustomerPic( ) );
        if ( cardDomain.isCardHasCustomerPic( ) ) {
            cardDTO.setPictureLocation( cardDomain.getPictureLocation( ) );
        } else {
            cardDTO.setPictureLocation( EMPTY_STRING );
        }
//            card.setShopCard();
        return cardDTO;
    }

    /**
     * Gets converted card dTO list.
     *
     * @param cardDomainList the card domain list
     * @return the converted card dTO list
     */
    public static List< com.mana.innovative.dto.consumer.Card > getConvertedListDTOFromDomain( List< com.mana
            .innovative.domain.consumer.Card > cardDomainList ) {
        List< Card > cardDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.Card card : cardDomainList ) {
            Card cardDTO = new Card( );
            cardDTO = getConvertedDTOFromDomain( cardDTO, card );
            cardDTOList.add( cardDTO );
        }
        return cardDTOList;
    }

    /**
     * Gets converted card domain from card dTO.
     *
     * @param cardDomain the card domain
     * @param cardDTO the card dTO
     * @return the converted card domain from card dTO
     */
    public static com.mana.innovative.domain.consumer.Card getConvertedDomainFromDTO( com
                                                                                              .mana.innovative.domain.consumer.Card cardDomain, final Card cardDTO ) {

        if ( cardDTO == null ) {
            String message = "Parameter cardDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( cardDomain == null ) {
            cardDomain = new com.mana.innovative.domain.consumer.Card( );
            logger.warn( "Creating cardDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( cardDTO.getCardNumber( ) ) && isValidCardNumber( cardDTO.getCardNumber( ) ) ) {
            cardDomain.setCardNumber( cardDTO.getCardNumber( ) );
        } else {
            flag = true;
            stringBuilder.append( "CardNumber" );
        }

        if ( !StringUtils.isEmpty( cardDTO.getFirstName( ) ) ) {
            cardDomain.setFirstName( cardDTO.getFirstName( ) );
        } else {
            flag = true;
            stringBuilder.append( "FirstName " );
        }

        if ( !StringUtils.isEmpty( cardDTO.getLastName( ) ) ) {
            cardDomain.setLastName( cardDTO.getLastName( ) );
        } else {
            flag = true;
            stringBuilder.append( "LastName " );
        }

        if ( !StringUtils.isEmpty( cardDTO.getIssueDate( ) ) && isValidCardIssueDate( cardDTO.getIssueDate( ) ) ) {
            cardDomain.setIssueDate( cardDTO.getIssueDate( ) );
        } else {
            cardDomain.setIssueDate( EMPTY_STRING );
        }

        if ( cardDTO.isCardHasCustomerPic( ) ) {
            cardDomain.setCardHasCustomerPic( cardDTO.isCardHasCustomerPic( ) );
            if ( !StringUtils.isEmpty( cardDTO.getPictureLocation( ) ) ) {
                cardDomain.setPictureLocation( cardDTO.getPictureLocation( ) );
            } else {
                flag = true;
                stringBuilder.append( "PictureLocation " );
            }
        }

//            card.setShopCard();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return cardDomain;
    }

    /**
     * Gets converted card domain list from card dTO list.
     *
     * @param cardDTOList the card dTO list
     * @return the converted card domain list from card dTO list
     */
    public static List< com.mana.innovative.domain.consumer.Card > getConvertedListDomainFromDTO( List< Card >
                                                                                                          cardDTOList ) {

        List< com.mana.innovative.domain.consumer.Card > cardDomainList = new ArrayList<>( );
        for ( Card cardDTO : cardDTOList ) {
            com.mana.innovative.domain.consumer.Card cardDomain = new com.mana.innovative.domain.consumer.Card( );
            cardDomain = getConvertedDomainFromDTO( cardDomain, cardDTO );
            cardDomainList.add( cardDomain );
        }
        return cardDomainList;
    }

    /**
     * Is valid issue expiry date.
     *
     * @param issuedDate the date format
     * @return the boolean
     */
    public static boolean isValidCardIssueDate( String issuedDate ) {

        String data[] = issuedDate.split( ":" );
        if ( data.length == 2 ) {
            Calendar calendar = Calendar.getInstance( );
            int month = Integer.parseInt( data[ 0 ] ),
                    year = Integer.parseInt( data[ 1 ] ),
                    currentMonth = calendar.get( Calendar.MONTH ),
                    currentYear = calendar.get( Calendar.YEAR );
            currentYear = currentYear % 100;

            if ( month > ZERO && month <= MONTH_MAX ) {

                if ( year < currentYear && year > ( currentYear - MAX_MIN_LIMIT ) && ( currentYear - MAX_MIN_LIMIT ) <= 0 ) {
                    return true;
                } else if ( year == currentYear && month <= currentMonth ) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Is valid card number format.
     *
     * @param cardNumber the card number
     * @return the boolean
     */
    public static boolean isValidCardNumber( String cardNumber ) {

        int sum, oddSum = 0, evenSum = 0;
        for ( int i = cardNumber.length( ); i > 0; i-- ) {
            int temp = Integer.parseInt( cardNumber.substring( i - 1, i ) );
            if ( i % 2 == 0 ) {
                evenSum += temp;
            } else {
                temp = temp * 2;
                if ( temp > 9 ) {
                    temp = getSumOfDigits( temp );
                }
                oddSum += temp;
            }
        }
        sum = oddSum + evenSum;

        return ( sum % 10 == 0 );
    }

    /**
     * Gets sum of digits.
     *
     * @param number the number
     * @return the sum of digits
     */
    public static int getSumOfDigits( int number ) {

        int temp = 0;

        while ( number > 0 ) {
            temp += number % 10;
            number = number / 10;
        }
        return temp;
    }

}
