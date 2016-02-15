package com.mana.innovative.converter.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.constants.ServiceConstants;
import com.mana.innovative.dto.client.Item;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Item domain dTO converter.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class ItemDomainDTOConverter {


	/**
	 * The constant logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger( ItemDomainDTOConverter.class );
	/**
	 * The constant ZERO.
	 */
	private static final int ZERO = DAOConstants.ZERO;

	/**
	 * Gets converted item dTO from item domain.
	 *
	 * @param itemDTO the item dTO
	 * @param itemDomain the item domain
	 *
	 * @return the converted item dTO from item domain
	 */
	public static Item getConvertedDTOFromDomain( Item itemDTO, com.mana.innovative.domain.client.Item itemDomain ) {

		if ( itemDomain == null ) {
			String message = "Parameter itemDomain is required for conversion";
			logger.error( message );
			throw new NullPointerException( message );
		}
		if ( itemDTO == null ) {
			itemDTO = new Item( );
			logger.warn( " Creating itemDTO, received null object" );
		}

		if ( itemDomain.getItemId( ) >= ZERO ) {
			itemDTO.setItemId( itemDomain.getItemId( ) );
		}
		if ( !StringUtils.isEmpty( itemDomain.getItemName( ) ) ) {
			itemDTO.setItemName( itemDomain.getItemName( ) );
		} else {
			logger.warn( "Item Name was empty" );
			itemDTO.setItemName( ServiceConstants.EMPTY );

		}

		itemDTO.setImageCount( itemDomain.getImageCount( ) );

		if ( !StringUtils.isEmpty( itemDomain.getItemOrigin( ) ) ) {
			itemDTO.setItemOrigin( itemDomain.getItemOrigin( ) );
		} else {
			itemDTO.setItemOrigin( ServiceConstants.DEFAULT_ITEM_ORIGIN );
		}

		if ( !StringUtils.isEmpty( itemDomain.getItemDescription( ) ) ) {
			itemDTO.setItemDescription( itemDomain.getItemDescription( ) );
		} else {
			itemDTO.setItemDescription( ServiceConstants.DEFAULT_SHOP_DESCRIPTION );
		}

		if ( itemDomain.getItemPrice( ) != null ) {
			itemDTO.setItemPrice( itemDomain.getItemPrice( ) );
		}
		if ( !StringUtils.isEmpty( itemDomain.getItemPriceCurrency( ) ) ) {
			itemDTO.setItemPriceCurrency( itemDomain.getItemPriceCurrency( ) );
		}
		if ( !StringUtils.isEmpty( itemDomain.getItemType( ) ) ) {
			itemDTO.setItemType( itemDomain.getItemType( ) );
		}

		if ( !StringUtils.isEmpty( itemDomain.getItemSubType( ) ) )
			itemDTO.setItemSubType( itemDomain.getItemSubType( ) );
		else
			itemDTO.setItemSubType( "" );

		if ( itemDomain.getBoughtDate( ) != null ) {
			itemDTO.setBoughtDate( itemDomain.getBoughtDate( ) );
		}
		if ( !StringUtils.isEmpty( itemDomain.getBoughtFrom( ) ) ) {
			itemDTO.setBoughtFrom( itemDomain.getBoughtFrom( ) );
		}

		if ( itemDomain.getQuantity( ) != null ) {
			itemDTO.setQuantity( itemDomain.getQuantity( ) );
		}
		if ( !StringUtils.isEmpty( itemDomain.getQuantityType( ) ) ) {
			itemDTO.setQuantityType( itemDomain.getQuantityType( ) );
		}

		if ( itemDomain.getWeight( ) != null ) {
			itemDTO.setWeight( itemDomain.getWeight( ) );
		}
		if ( !StringUtils.isEmpty( itemDomain.getWeightedUnit( ) ) ) {
			itemDTO.setWeightedUnit( itemDomain.getWeightedUnit( ) );
		}
//            item.setShopItem();
		if ( itemDomain.getItemDiscountList( ) != null ) {
			itemDTO.setItemDiscountList( ItemDiscountDomainDTOConverter.getConvertedListDTOFromDomain( itemDomain
					.getItemDiscountList( ) ) );
		}

		if ( itemDomain.getItemImageList( ) != null ) {
			itemDTO.setItemImageList( ItemImageDomainDTOConverter.getConvertedListDTOFromDomain( itemDomain
					.getItemImageList( ) ) );
		}

		if ( itemDomain.getGemstoneList( ) != null ) {
			itemDTO.setGemstoneList( GemstoneDomainDTOConverter.getConvertedListDTOFromDomain( itemDomain.getGemstoneList( ) ) );
		}

		return itemDTO;
	}

	/**
	 * Gets converted item dTO list.
	 *
	 * @param items the items
	 *
	 * @return the converted item dTO list
	 */
	public static List< Item > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.client.Item > items ) {

		List< Item > itemDTOList = new ArrayList<>( );
		for ( com.mana.innovative.domain.client.Item item : items ) {

			Item itemDTO = new Item( );
			itemDTO = getConvertedDTOFromDomain( itemDTO, item );
			itemDTOList.add( itemDTO );
		}
		return itemDTOList;
	}

	/**
	 * Gets converted item domain from item dTO.
	 *
	 * @param itemDomain the item domain
	 * @param itemDTO the item dTO
	 *
	 * @return the converted item domain from item dTO
	 */
	public static com.mana.innovative.domain.client.Item getConvertedDomainFromDTO( com.mana.innovative.domain.client.Item itemDomain, Item itemDTO ) {

		if ( itemDTO == null ) {
			String message = "Parameter itemDTO is required for conversion";
			logger.error( message );
			throw new NullPointerException( message );
		}

		if ( itemDomain == null ) {
			itemDomain = new com.mana.innovative.domain.client.Item( );
			logger.warn( "Creating itemDomain, received null object" );
		}

		boolean flag = false;
		StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );

		if ( !StringUtils.isEmpty( itemDTO.getItemName( ) ) ) {
			itemDomain.setItemName( itemDTO.getItemName( ) );
		} else {
			flag = true;
			stringBuilder.append( " ItemName," );
		}
		if ( !StringUtils.isEmpty( itemDTO.getItemDescription( ) ) ) {
			itemDomain.setItemDescription( itemDTO.getItemDescription( ) );
		} else {
			itemDomain.setItemDescription( ServiceConstants.DEFAULT_ITEM_DESCRIPTION );
			logger.warn( "Item Description not provided, setting default Description" );
		}

		if ( itemDTO.getItemPrice( ) != null && itemDTO.getItemPrice( ) > ZERO ) {
			itemDomain.setItemPrice( itemDTO.getItemPrice( ) );
		} else {
			flag = true;
			stringBuilder.append( " ItemPrice," );
		}
		if ( !StringUtils.isEmpty( itemDTO.getItemPriceCurrency( ) ) ) {
			itemDomain.setItemPriceCurrency( itemDTO.getItemPriceCurrency( ) );
		} else {
			flag = true;
			stringBuilder.append( " ItemPriceCurrency," );
		}
		if ( !StringUtils.isEmpty( itemDTO.getItemType( ) ) ) {
			itemDomain.setItemType( itemDTO.getItemType( ) );
		} else {
			flag = true;
			stringBuilder.append( " ItemType," );
		}

		if ( !StringUtils.isEmpty( itemDTO.getItemSubType( ) ) ) {
			itemDomain.setItemSubType( itemDTO.getItemSubType( ) );
		} else
			itemDomain.setItemSubType( itemDTO.getItemSubType( ) );

		if ( itemDTO.getBoughtDate( ) != null ) {
			itemDomain.setBoughtDate( itemDTO.getBoughtDate( ) );
		} else {
			flag = true;
			stringBuilder.append( " BoughtDate," );
		}
		if ( !StringUtils.isEmpty( itemDTO.getBoughtFrom( ) ) ) {
			itemDomain.setBoughtFrom( itemDTO.getBoughtFrom( ) );
		} else {
			flag = true;
			stringBuilder.append( " BoughtFrom," );
		}

		if ( itemDTO.getQuantity( ) != null && itemDTO.getQuantity( ) > ZERO ) {
			itemDomain.setQuantity( itemDTO.getQuantity( ) );
		} else {
			flag = true;
			stringBuilder.append( " Quantity," );
		}
		if ( !StringUtils.isEmpty( itemDTO.getQuantityType( ) ) ) {
			itemDomain.setQuantityType( itemDTO.getQuantityType( ) );
		} else {
			flag = true;
			stringBuilder.append( " QuantityType," );
		}
		if ( itemDTO.getWeight( ) != null && itemDTO.getWeight( ) > ZERO ) {
			itemDomain.setWeight( itemDTO.getWeight( ) );
		} else {
			flag = true;
			stringBuilder.append( " Weight," );
		}
		if ( !StringUtils.isEmpty( itemDTO.getWeightedUnit( ) ) ) {
			itemDomain.setWeightedUnit( itemDTO.getWeightedUnit( ) );
		} else {
			flag = true;
			stringBuilder.append( " WeightedUnit," );
		}

		if ( itemDTO.getImageCount( ) > ZERO ) {
			itemDomain.setImageCount( itemDTO.getImageCount( ) );
		} else {
//            itemDomain.setImageCount( ZERO );
//            logger.warn( "Item Image Count was empty" );
			flag = true;
			stringBuilder.append( " ItemImageCount," );
		}

		if ( !StringUtils.isEmpty( itemDTO.getItemOrigin( ) ) ) {
			itemDomain.setItemOrigin( itemDTO.getItemOrigin( ) );
		} else {
			logger.warn( "No Item Origin was provided using default" );
			itemDomain.setItemOrigin( ServiceConstants.DEFAULT_ITEM_ORIGIN );
		}

		try {
			if ( itemDTO.getItemDiscountList( ) != null ) {
				itemDomain.setItemDiscountList( ItemDiscountDomainDTOConverter.getConvertedListDomainFromDTO( itemDTO
						.getItemDiscountList( ) ) );
			}

		} catch ( IllegalArgumentValueException exception ) {
			flag = true;
			stringBuilder.append( exception.getMessage( ) );
		}
		try {
			if ( itemDTO.getItemImageList( ) != null ) {
				itemDomain.setItemImageList( ItemImageDomainDTOConverter.getConvertedListDomainFromDTO( itemDTO
						.getItemImageList( ) ) );
			}

		} catch ( IllegalArgumentValueException exception ) {
			flag = true;
			stringBuilder.append( exception.getMessage( ) );
		}

		try {
			if ( itemDTO.getGemstoneList( ) != null ) {
				itemDomain.setGemstoneList( GemstoneDomainDTOConverter.getConvertedListDomainFromDTO( itemDTO.getGemstoneList
						( ) ) );
			}
		} catch ( IllegalArgumentValueException exception ) {
			flag = true;
			stringBuilder.append( exception.getMessage( ) );
		}

//            item.setShopItem();
		if ( flag ) {
			logger.error( stringBuilder.toString( ) );
			throw new IllegalArgumentValueException( stringBuilder.toString( ) );
		}
		logger.info( stringBuilder.toString( ) );
		return itemDomain;
	}

	/**
	 * Gets converted item domain list from item dTO list.
	 *
	 * @param itemDTOList the item dTO list
	 *
	 * @return the converted item domain list from item dTO list
	 */
	public static List< com.mana.innovative.domain.client.Item > getConvertedListDomainFromDTO( List< Item > itemDTOList ) {

		List< com.mana.innovative.domain.client.Item > itemDomainList = new ArrayList<>( );
		for ( Item itemDTO : itemDTOList ) {
			com.mana.innovative.domain.client.Item itemDomain = new com.mana.innovative.domain.client.Item( );
			itemDomain = getConvertedDomainFromDTO( itemDomain, itemDTO );
			itemDomainList.add( itemDomain );
		}
		return itemDomainList;
	}

}