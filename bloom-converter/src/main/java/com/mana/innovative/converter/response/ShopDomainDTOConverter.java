package com.mana.innovative.converter.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.client.Shop;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Shop domain dTO converter.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class ShopDomainDTOConverter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory
            .getLogger( ShopDomainDTOConverter.class );
    /**
     * The constant ZERO.
     */
    private static final int ZERO = DAOConstants.ZERO;

    // private static final int ONE = DAOConstants.ONE;

    /**
     * Gets converted shop dTO from shop domain.
     *
     * @param shopDTO the shop dTO
     * @param shopDomain the shop
     * @return the converted shop dTO from shop domain
     */
    public static Shop getConvertedDTOFromDomain( Shop shopDTO,
                                                  com.mana.innovative.domain.client.Shop shopDomain ) {

        if ( shopDTO == null ) {
            shopDTO = new Shop( );
            logger.warn( "Creating shopDTO for conversion as was null " );
        }
        if ( shopDomain == null ) {
            String message = "Parameter shopDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        if ( shopDomain.getShopId( ) >= ZERO ) {
            shopDTO.setShopId( shopDomain.getShopId( ) );
        }

        if ( !StringUtils.isEmpty( shopDomain.getShopName( ) ) ) {
            shopDTO.setShopName( shopDomain.getShopName( ) );
        } else {
            logger.warn( "Shop Name was empty" );
            shopDTO.setShopName( ServiceConstants.EMPTY );
        }

        if ( !StringUtils.isEmpty( shopDomain.getShopDescription( ) ) ) {
            shopDTO.setShopDescription( shopDomain.getShopDescription( ) );
        } else {
            shopDTO.setShopDescription( ServiceConstants.DEFAULT_SHOP_DESCRIPTION );
        }
        if ( shopDomain.getShopOwnId( ) != null )
            shopDTO.setShopOwnId( shopDomain.getShopOwnId( ) );
        if ( !StringUtils.isEmpty( shopDomain.getShopWebLink( ) ) ) {
            shopDTO.setShopWebLink( shopDomain.getShopWebLink( ) );
        }
        /** {@link WorkingHour} List */
        if ( shopDomain.getWorkingHours( ) != null
                && !shopDomain.getWorkingHours( ).isEmpty( ) ) {
            shopDTO.setWorkingHours( WorkingHourDomainDTOConverter
                    .getConvertedListDTOFromDomain( shopDomain.getWorkingHours( ) ) );
        }
        /** {@link Address, com.mana.innovative.dto.common.Address} */
        if ( shopDomain.getAddress( ) != null ) {
            shopDTO.setAddress( AddressDomainDTOConverter
                    .getConvertedDTOFromDomain( shopDomain.getAddress( ) ) );
        }
        /** {@link Item, com.mana.innovative.dto.client.Item} */
        if ( shopDomain.getItems( ) != null ) {
            shopDTO.setItems( ItemDomainDTOConverter
                    .getConvertedListDTOFromDomain( shopDomain.getItems( ) ) );
        }
        return shopDTO;
    }

    /**
     * Gets converted shop dTO list.
     *
     * @param shops the shops
     * @return the converted shop dTO list
     */
    public static List< Shop > getConvertedListDTOFromDomain(
            List< com.mana.innovative.domain.client.Shop > shops ) {

        List< Shop > shopDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.client.Shop shop : shops ) {

            Shop shopDTO = new Shop( );
            shopDTO = getConvertedDTOFromDomain( shopDTO, shop );
            shopDTOList.add( shopDTO );
        }
        return shopDTOList;
    }

    /**
     * Gets converted shop domain from shop dTO.
     *
     * @param shopDomain the shop domain
     * @param shopDTO the shop dTO
     * @return the converted shop domain from shop dTO
     */
    public static com.mana.innovative.domain.client.Shop getConvertedDomainFromDTO(
            com.mana.innovative.domain.client.Shop shopDomain, Shop shopDTO ) {

        if ( shopDomain == null ) {
            shopDomain = new com.mana.innovative.domain.client.Shop( );
            logger.warn( "Creating shopDomain for conversion as was null " );
        }
        if ( shopDTO == null ) {
            String message = "Parameter shopDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }

        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder(
                " Value must not be null for " );
        if ( shopDTO.getShopOwnId( ) != null && shopDTO.getShopOwnId( ) > ZERO ) {
            shopDomain.setShopOwnId( shopDTO.getShopOwnId( ) );
        } else {
            flag = true;
            stringBuilder.append( " ShopOwnID," );
        }
        // shopDomain.setShopId(shopDTO.getShopId());
        if ( shopDTO.getShopName( ) != null && !shopDTO.getShopName( ).isEmpty( ) ) {
            shopDomain.setShopName( shopDTO.getShopName( ) );
        } else {
            flag = true;
            stringBuilder.append( " ShopName," );
        }

        if ( !StringUtils.isEmpty( shopDTO.getShopDescription( ) ) ) {
            shopDomain.setShopDescription( shopDTO.getShopDescription( ) );
        } else {
            shopDomain.setShopDescription( ServiceConstants.DEFAULT_SHOP_DESCRIPTION );
            logger.warn( "Shop Description not provided, setting default Description" );
        }


        if ( !StringUtils.isEmpty( shopDTO.getShopWebLink( ) ) ) {
            shopDomain.setShopWebLink( shopDTO.getShopWebLink( ) );
        } else {
            flag = true;
            stringBuilder.append( " ShopWebLink," );
        }
        // check Address
        if ( shopDTO.getAddress( ) != null ) {
            shopDomain.setAddress( AddressDomainDTOConverter
                    .getConvertedDomainFromDTO( shopDTO.getAddress( ) ) );
        } else {
            stringBuilder.append( " Address," );
        }
        // check Working Hours
        if ( shopDTO.getWorkingHours( ) != null
                && !shopDTO.getWorkingHours( ).isEmpty( ) ) {
            shopDomain.setWorkingHours( WorkingHourDomainDTOConverter
                    .getConvertedListDomainFromDTO( shopDTO.getWorkingHours( ) ) );
        } else {
            flag = true;
            stringBuilder.append( " Working Hours," );
        }
        // check Items
        if ( shopDTO.getItems( ) != null && !shopDTO.getItems( ).isEmpty( ) ) {
            shopDomain.setItems( ItemDomainDTOConverter
                    .getConvertedListDomainFromDTO( shopDTO.getItems( ) ) );
        } else {
            stringBuilder.append( " Items" );
        }
        // shop.setShopShop();
        if ( flag ) {
            logger.error( stringBuilder.toString( ) );
            throw new IllegalArgumentValueException( );
        }
        logger.info( stringBuilder.toString( ) );
        return shopDomain;
    }

    /**
     * Gets converted shop domain list from shop dTO list.
     *
     * @param shopDTOList the shop dTO list
     * @return the converted shop domain list from shop dTO list
     */
    public static List< com.mana.innovative.domain.client.Shop > getConvertedListDomainFromDTO(
            List< Shop > shopDTOList ) {

        List< com.mana.innovative.domain.client.Shop > shopDomainList = new ArrayList<>( );
        for ( Shop shopDTO : shopDTOList ) {
            com.mana.innovative.domain.client.Shop shopDomain = new com.mana.innovative.domain.client.Shop( );
            shopDomain = getConvertedDomainFromDTO( shopDomain, shopDTO );
            shopDomainList.add( shopDomain );
        }
        return shopDomainList;
    }
}