package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dto.client.ItemImage;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The class ItemImageDomainDTOConverter is for todo.
 * Created by BLOOM on 9/20/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class ItemImageDomainDTOConverter {


    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( ItemImageDomainDTOConverter.class );
    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;

    /**
     * Gets converted item dTO from item domain.
     *
     * @param itemImageDTO    the item dTO
     * @param itemImageDomain the item domain
     *
     * @return the converted item dTO from item domain
     */
    public static ItemImage getConvertedDTOFromDomain( ItemImage itemImageDTO, com.mana.innovative.domain.client.ItemImage itemImageDomain ) {

        if ( itemImageDomain == null ) {
            String message = "Parameter itemImageDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( itemImageDTO == null ) {
            itemImageDTO = new ItemImage( );
            logger.warn( " Creating itemImageDTO, received null object" );
        }

        if ( itemImageDomain.getItemImageId( ) >= ZERO ) {
            itemImageDTO.setItemImageId( itemImageDomain.getItemImageId( ) );
        }
        if ( !StringUtils.isEmpty( itemImageDomain.getImageLocation( ) ) ) {
            itemImageDTO.setImageLocation( itemImageDomain.getImageLocation( ) );
        }
        if ( itemImageDomain.getImagePriority( ) >= 0 ) {
            itemImageDTO.setImagePriority( itemImageDomain.getImagePriority( ) );
        }
        if ( !StringUtils.isEmpty( itemImageDomain.getItemImageId( ) > 0 ) ) {
            itemImageDTO.setItemImageId( itemImageDomain.getItemImageId( ) );
        }
        if ( !StringUtils.isEmpty( itemImageDomain.getImageHeight( ) > 0 ) ) {
            itemImageDTO.setImageHeight( itemImageDomain.getImageHeight( ) );
        }
        if ( !StringUtils.isEmpty( itemImageDomain.getImageWidth( ) > 0 ) ) {
            itemImageDTO.setImageWidth( itemImageDomain.getImageWidth( ) );
        }

//            item.setShopItemImage();
        return itemImageDTO;
    }

    /**
     * Gets converted item dTO list.
     *
     * @param items the items
     *
     * @return the converted item dTO list
     */
    public static List< ItemImage > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.client.ItemImage > items ) {

        List< ItemImage > itemImageDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.client.ItemImage item : items ) {

            ItemImage itemImageDTO = new ItemImage( );
            itemImageDTO = getConvertedDTOFromDomain( itemImageDTO, item );
            itemImageDTOList.add( itemImageDTO );
        }
        return itemImageDTOList;
    }

    /**
     * Gets converted item domain from item dTO.
     *
     * @param itemImageDomain the item domain
     * @param itemImageDTO    the item dTO
     *
     * @return the converted item domain from item dTO
     */
    public static com.mana.innovative.domain.client.ItemImage
    getConvertedDomainFromDTO( com.mana.innovative.domain.client.ItemImage itemImageDomain, ItemImage itemImageDTO ) {


        if ( itemImageDTO == null ) {
            String message = "Parameter itemImageDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        if ( itemImageDomain == null ) {
            itemImageDomain = new com.mana.innovative.domain.client.ItemImage( );
            logger.warn( "Creating itemImageDomain, received null object" );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

        if ( !StringUtils.isEmpty( itemImageDTO.getImageLocation( ) ) ) {
            itemImageDomain.setImageLocation( itemImageDTO.getImageLocation( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemImageLocation," );
        }
        if ( itemImageDTO.getImagePriority( ) > ZERO ) {
            itemImageDomain.setImagePriority( itemImageDTO.getImagePriority( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemImagePriority," );
        }

        if ( itemImageDTO.getImageHeight( ) > ZERO ) {
            itemImageDomain.setImageHeight( itemImageDTO.getImageHeight( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemImageHeight," );
        }

        if ( itemImageDTO.getImageWidth( ) > ZERO ) {
            itemImageDomain.setImageWidth( itemImageDTO.getImageWidth( ) );
        } else {
            flag = true;
            stringBuilder.append( " ItemImageWidth," );
        }

//            item.setShopItemImage();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( stringBuilder.toString( ) );
        }
        logger.info( stringBuilder.toString( ) );
        return itemImageDomain;
    }

    /**
     * Gets converted item domain list from item dTO list.
     *
     * @param itemImageDTOList the item dTO list
     *
     * @return the converted item domain list from item dTO list
     */
    public static List< com.mana.innovative.domain.client.ItemImage > getConvertedListDomainFromDTO( List< ItemImage > itemImageDTOList ) {

        List< com.mana.innovative.domain.client.ItemImage > itemImageDomainList = new ArrayList<>( );
        for ( ItemImage itemImageDTO : itemImageDTOList ) {
            com.mana.innovative.domain.client.ItemImage itemImageDomain = new com.mana.innovative.domain.client.ItemImage( );
            itemImageDomain = getConvertedDomainFromDTO( itemImageDomain, itemImageDTO );
            itemImageDomainList.add( itemImageDomain );
        }
        return itemImageDomainList;
    }
}
