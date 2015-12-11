package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.client.ItemDiscount;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The class ItemDiscountDiscountDomainDTOConverter is for todo.
 * Created by BLOOM on 9/20/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class ItemDiscountDomainDTOConverter {


    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemDiscountDomainDTOConverter.class );
    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;

    /**
     * Gets converted itemDiscount dTO from itemDiscount domain.
     *
     * @param itemDiscountDTO    the itemDiscount dTO
     * @param itemDiscountDomain the itemDiscount domain
     *
     * @return the converted itemDiscount dTO from itemDiscount domain
     */
    public static ItemDiscount getConvertedDTOFromDomain( ItemDiscount itemDiscountDTO, com.mana.innovative.domain.client.ItemDiscount itemDiscountDomain ) {

        if ( itemDiscountDomain == null ) {
            String message = "Parameter itemDiscountDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( itemDiscountDTO == null ) {
            itemDiscountDTO = new ItemDiscount( );
            logger.warn( " Creating itemDiscountDTO, received null object" );
        }

        if ( itemDiscountDomain.getItemDiscountId( ) >= ZERO ) {
            itemDiscountDTO.setItemDiscountId( itemDiscountDomain.getItemDiscountId( ) );
        }
        if ( !StringUtils.isEmpty( itemDiscountDomain.getDiscountPercent( ) ) ) {
            itemDiscountDTO.setDiscountPercent( itemDiscountDomain.getDiscountPercent( ) );
        }
        if ( itemDiscountDomain.getDiscountType( ) != null ) {
            itemDiscountDTO.setDiscountType( itemDiscountDomain.getDiscountType( ) );
        }
        if ( !StringUtils.isEmpty( itemDiscountDomain.getUserRole( ) ) ) {
            itemDiscountDTO.setUserRole( itemDiscountDomain.getUserRole( ) );
        }
        if ( !StringUtils.isEmpty( itemDiscountDomain.getStartDate( ) ) ) {
            itemDiscountDTO.setStartDate( itemDiscountDomain.getStartDate( ) );
        } else
            itemDiscountDTO.setStartDate( null );

        if ( !StringUtils.isEmpty( itemDiscountDomain.getEndDate( ) ) )
            itemDiscountDTO.setEndDate( itemDiscountDomain.getEndDate( ) );
        else
            itemDiscountDTO.setEndDate( null );

//            itemDiscount.setShopItemDiscount();
        return itemDiscountDTO;
    }

    /**
     * Gets converted itemDiscount dTO list.
     *
     * @param itemDiscounts the itemDiscounts
     *
     * @return the converted itemDiscount dTO list
     */
    public static List< ItemDiscount > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.client.ItemDiscount > itemDiscounts ) {

        List< ItemDiscount > itemDiscountDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.client.ItemDiscount itemDiscount : itemDiscounts ) {

            ItemDiscount itemDiscountDTO = new ItemDiscount( );
            itemDiscountDTO = getConvertedDTOFromDomain( itemDiscountDTO, itemDiscount );
            itemDiscountDTOList.add( itemDiscountDTO );
        }
        return itemDiscountDTOList;
    }

    /**
     * Gets converted itemDiscount domain from itemDiscount dTO.
     *
     * @param itemDiscountDomain the itemDiscount domain
     * @param itemDiscountDTO    the itemDiscount dTO
     *
     * @return the converted itemDiscount domain from itemDiscount dTO
     */
    public static com.mana.innovative.domain.client.ItemDiscount
    getConvertedDomainFromDTO( com.mana.innovative.domain.client.ItemDiscount itemDiscountDomain, ItemDiscount itemDiscountDTO ) {


        if ( itemDiscountDTO == null ) {
            String message = "Parameter itemDiscountDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        if ( itemDiscountDomain == null ) {
            itemDiscountDomain = new com.mana.innovative.domain.client.ItemDiscount( );
            logger.warn( "Creating itemDiscountDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( itemDiscountDTO.getDiscountType( ) ) ) {
            itemDiscountDomain.setDiscountType( itemDiscountDTO.getDiscountType( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemDiscountType," );
        }
        if ( itemDiscountDTO.getDiscountPercent( ) > ZERO ) {
            itemDiscountDomain.setDiscountPercent( itemDiscountDTO.getDiscountPercent( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemDiscountPercent," );
        }
        if ( !StringUtils.isEmpty( itemDiscountDTO.getUserRole( ) ) ) {
            itemDiscountDomain.setUserRole( itemDiscountDTO.getUserRole( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemDiscountUserRole," );
        }
        if ( !StringUtils.isEmpty( itemDiscountDTO.getStartDate( ) ) ) {
            itemDiscountDomain.setStartDate( itemDiscountDTO.getStartDate( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemDiscountStartDate," );
        }

        if ( itemDiscountDTO.getEndDate( ) != null ) {
            itemDiscountDomain.setEndDate( itemDiscountDTO.getEndDate( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemDiscountEndDate," );
        }

        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return itemDiscountDomain;
    }

    /**
     * Gets converted itemDiscount domain list from itemDiscount dTO list.
     *
     * @param itemDiscountDTOList the itemDiscount dTO list
     *
     * @return the converted itemDiscount domain list from itemDiscount dTO list
     */
    public static List< com.mana.innovative.domain.client.ItemDiscount > getConvertedListDomainFromDTO( List< ItemDiscount > itemDiscountDTOList ) {

        List< com.mana.innovative.domain.client.ItemDiscount > itemDiscountDomainList = new ArrayList<>( );
        for ( ItemDiscount itemDiscountDTO : itemDiscountDTOList ) {
            com.mana.innovative.domain.client.ItemDiscount itemDiscountDomain = new com.mana.innovative.domain.client.ItemDiscount( );
            itemDiscountDomain = getConvertedDomainFromDTO( itemDiscountDomain, itemDiscountDTO );
            itemDiscountDomainList.add( itemDiscountDomain );
        }
        return itemDiscountDomainList;
    }
}
