package com.mana.innovative.converter.response;

import com.mana.innovative.constants.CardType;
import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.consumer.CreditCard;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bloom/Rono on 4/23/2015. This class is CreditCardDomainDTOConverter
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class CreditCardDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CreditCardDomainDTOConverter.class );

    /**
     * The constant ZERO.
     */
    private static final long ZERO = DAOConstants.ZERO;

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
     * @param creditCardDTO the card dTO
     * @param creditCardDomain the card domain
     * @return the converted card dTO from card domain
     */
    public static CreditCard getConvertedDTOFromDomain( CreditCard creditCardDTO,
                                                        com.mana.innovative.domain.consumer.CreditCard creditCardDomain ) {

        if ( creditCardDomain == null ) {
            String message = "Parameter cardDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( creditCardDTO == null ) {
            creditCardDTO = new CreditCard( );
            logger.warn( " Creating cardDTO, received null object" );
        }
        if ( creditCardDomain.getCardId( ) >= ZERO ) {
            creditCardDTO.setCardId( creditCardDomain.getCardId( ) );
        }
        creditCardDTO = ( CreditCard ) CardDomainDTOConverter.getConvertedDTOFromDomain( creditCardDTO, creditCardDomain );

        creditCardDTO.setMiddleName( creditCardDomain.getMiddleName( ) );
        creditCardDTO.setCVV( String.valueOf( creditCardDomain.getCVV( ) ) );
        creditCardDTO.setCreateOrModified( creditCardDomain.getCreateOrModified( ) );
        creditCardDTO.setCardType( creditCardDomain.getCardType( ) );
        creditCardDTO.setExpiryDate( creditCardDomain.getExpiryDate( ) );


//            card.setShopCreditCard();
        return creditCardDTO;
    }

    /**
     * Gets converted card dTO list.
     *
     * @param cardDomainList the card domain list
     * @return the converted card dTO list
     */
    public static List< CreditCard > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.consumer
            .CreditCard > cardDomainList ) {

        List< CreditCard > cardDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.consumer.CreditCard card : cardDomainList ) {
            CreditCard cardDTO = new CreditCard( );
            cardDTO = getConvertedDTOFromDomain( cardDTO, card );
            cardDTOList.add( cardDTO );
        }
        return cardDTOList;
    }

    /**
     * Gets converted card domain from card dTO.
     *
     * @param creditCardDomain the card domain
     * @param creditCardDTO the card dTO
     * @return the converted card domain from card dTO
     */
    public static com.mana.innovative.domain.consumer.CreditCard getConvertedDomainFromDTO
    ( com.mana.innovative.domain.consumer.CreditCard creditCardDomain, final CreditCard creditCardDTO ) {

        if ( creditCardDTO == null ) {
            String message = "Parameter cardDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( creditCardDomain == null ) {
            creditCardDomain = new com.mana.innovative.domain.consumer.CreditCard( );
            logger.warn( "Creating cardDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );
        creditCardDomain = ( com.mana.innovative.domain.consumer.CreditCard ) CardDomainDTOConverter
                .getConvertedDomainFromDTO( creditCardDomain, creditCardDTO );

        if ( !StringUtils.isEmpty( creditCardDTO.getMiddleName( ) ) ) {
            creditCardDomain.setMiddleName( creditCardDTO.getMiddleName( ) );
        } else {
            flag = true;
            stringBuilder.append( "Middle Name, " );
        }

        if ( !StringUtils.isEmpty( creditCardDTO.getCardType( ) ) ) {
            creditCardDomain.setCardType( creditCardDTO.getCardType( ) );
        } else {
            flag = true;
            stringBuilder.append( "CardType, " );
        }

        if ( !StringUtils.isEmpty( creditCardDTO.getExpiryDate( ) ) && isValidCardExpiryDate( creditCardDTO.getExpiryDate( ) ) ) {
            creditCardDomain.setExpiryDate( creditCardDTO.getExpiryDate( ) );
        } else {
            flag = true;
            stringBuilder.append( "ExpiryDate, " );
        }
        if ( creditCardDTO.getCVV( ) != null ) {
            try {
                creditCardDomain.setCVV( Integer.parseInt( creditCardDTO.getCVV( ) ) );
            } catch ( NumberFormatException exception ) {
                logger.error( "Failed to convert CVV into integer", exception );
                flag = true;
                stringBuilder.append( "CVV " );
            }
        } else {
            flag = true;
            stringBuilder.append( "CVV, " );
        }

        if ( !flag ) {
            validateCardNumberWithCardType( creditCardDTO.getCardNumber( ), creditCardDTO.getCardType( ) );
            validateCVVWithCardType( creditCardDTO );
        }
        if ( creditCardDTO.getCreateOrModified( ) != null ) {
            creditCardDomain.setCreateOrModified( creditCardDTO.getCreateOrModified( ) );
        } else creditCardDomain.setCreateOrModified( new Date( ) );

//            card.setShopCreditCard();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return creditCardDomain;
    }


    /**
     * Gets converted card domain list from card dTO list.
     *
     * @param cardDTOList the card dTO list
     * @return the converted card domain list from card dTO list
     */
    public static List< com.mana.innovative.domain.consumer.CreditCard > getConvertedListDomainFromDTO( List< CreditCard >
                                                                                                                cardDTOList ) {

        List< com.mana.innovative.domain.consumer.CreditCard > cardDomainList = new ArrayList<>( );
        for ( CreditCard cardDTO : cardDTOList ) {
            com.mana.innovative.domain.consumer.CreditCard cardDomain = new com.mana.innovative.domain.consumer.CreditCard( );
            cardDomain = getConvertedDomainFromDTO( cardDomain, cardDTO );
            cardDomainList.add( cardDomain );
        }
        return cardDomainList;
    }

    /**
     * Is valid card expiry date.
     *
     * @param formattedDate the date format
     * @return the boolean
     */
    public static boolean isValidCardExpiryDate( String formattedDate ) {

        String data[] = formattedDate.split( ":" );
        if ( data.length == 2 ) {
            Calendar calendar = Calendar.getInstance( );
            int month = Integer.parseInt( data[ 0 ] ),
                    year = Integer.parseInt( data[ 1 ] ),
                    currentMonth = calendar.get( Calendar.MONTH ),
                    currentYear = calendar.get( Calendar.YEAR );
            currentYear = currentYear % 100;

            if ( month > ZERO && month <= MONTH_MAX ) {

                if ( year > currentYear && year < ( currentYear + MAX_MIN_LIMIT ) ) {
                    return true;
                } else if ( year == currentYear && month >= currentMonth ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Validate cVV with card type.
     *
     * @param creditCardDTO the credit card dTO
     */
    public static void validateCVVWithCardType( CreditCard creditCardDTO ) {
        if ( creditCardDTO.getCardType( ).equalsIgnoreCase( CardType.AmericanExpress.toString( ) ) ) {
            if ( creditCardDTO.getCVV( ).length( ) != 4 ) {
                throw new IllegalArgumentValueException( "Wrong CVV with Card Type" );
            }
        } else {
            if ( creditCardDTO.getCVV( ).length( ) != 3 ) {
                throw new IllegalArgumentValueException( "Wrong CVV with Card Type" );
            }
        }
    }


    /**
     * Validate card number with card type. <p> Visa : 13 or 16 digits, starting with 4. MasterCard : 16 digits,
     * starting with 51 through 55. Discover : 16 digits, starting with 6011 or 65. American Express : 15 digits,
     * starting with 34 or 37. Diners Club : 14 digits, starting with 300 through 305, 36, or 38. JCB : 15 digits,
     * starting with 2131 or 1800, or 16 digits starting with 35. </p>
     *
     * @param cardNumber the card number
     * @param cardType the card type
     */
    public static void validateCardNumberWithCardType( String cardNumber, String cardType ) {

        Matcher matcher;

        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14})|" +
                "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
                "(?<americanexpress>3[47][0-9]{13})|" +
                "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
                "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

        Pattern pattern = Pattern.compile( regex );

        matcher = pattern.matcher( cardNumber );
        String groupName = null;
        if ( matcher.matches( ) ) {

            //If card is valid then verify which group it belong
            if ( matcher.group( CardType.Visa.toString( ).toLowerCase( ) ) == null ) {
                if ( matcher.group( CardType.MasterCard.toString( ).toLowerCase( ) ) == null ) {
                    if ( matcher.group( CardType.AmericanExpress.toString( ).toLowerCase( ) ) == null ) {
                        if ( matcher.group( CardType.Discover.toString( ).toLowerCase( ) ) == null ) {
                            if ( matcher.group( CardType.Diners.toString( ).toLowerCase( ) ) == null ) {
                                groupName = CardType.JCB.toString( );
                            } else groupName = CardType.Diners.toString( );
                        } else groupName = CardType.Discover.toString( );
                    } else groupName = CardType.AmericanExpress.toString( );
                } else groupName = CardType.MasterCard.toString( );
            } else groupName = CardType.Visa.toString( );
        }
        if ( groupName == null || !groupName.equalsIgnoreCase( cardType ) ) {
            throw new IllegalArgumentValueException( "CardNumber and CardType don't match" );
        }
    }
}
