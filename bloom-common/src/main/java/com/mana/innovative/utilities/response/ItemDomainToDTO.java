package com.mana.innovative.utilities.response;

import com.mana.innovative.dto.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bloom/Rono on 3/3/2015.
 * This class is for .. ToDo
 */
public class ItemDomainToDTO {


    /**
     * Get converted itemDTO from item domain.
     *
     * @param itemDTO the itemDTO {@link Item}
     * @param item    the item {@link com.mana.innovative.domain.Item}
     * @return {@link com.mana.innovative.dto.Item} The item
     */
    public static Item getConvertedItemDTOFromItemDomain(Item itemDTO, com.mana.innovative.domain.Item item) {

        if (item.getItemId() > 1) {
            itemDTO.setItemId(item.getItemId());
        }
        if (item.getItemName() != null && !item.getItemName().isEmpty()) {
            itemDTO.setItemName(item.getItemName());
        }
        if (item.getItemPrice() > 0) {
            itemDTO.setItemPrice(item.getItemPrice());
        }
        if (item.getItemPriceCurrency() != null && !item.getItemPriceCurrency().isEmpty()) {
            itemDTO.setItemPriceCurrency(item.getItemPriceCurrency());
        }
        if (item.getItemType() != null && !item.getItemType().isEmpty()) {
            itemDTO.setItemType(item.getItemType());
        }

        if (item.getItemSubType() == null) itemDTO.setItemSubType("");
        else
            itemDTO.setItemSubType(item.getItemSubType());

        if (item.getBoughtDate() != null) {
            itemDTO.setBoughtDate(item.getBoughtDate());
        }
        if (item.getBoughtFrom() != null && !item.getBoughtFrom().isEmpty()) {
            itemDTO.setBoughtFrom(item.getBoughtFrom());
        }

        if (item.getQuantity() > 0) {
            itemDTO.setQuantity(item.getQuantity());
        }
        if (item.getQuantityType() != null && !item.getQuantityType().isEmpty()) {
            itemDTO.setQuantityType(item.getQuantityType());
        }

        if (item.getWeight() > 0) {
            itemDTO.setWeight(item.getWeight());
        }
        if (item.getWeightedUnit() != null && !item.getWeightedUnit().isEmpty()) {
            itemDTO.setWeightedUnit(item.getWeightedUnit());
        }
//            item.setShopItem();
        return itemDTO;
    }

    /**
     * Gets converted itemDTO list.
     *
     * @param items the items
     * @return the converted itemDTO list
     */
    public static List<Item> getConvertedItemDTOList(List<com.mana.innovative.domain.Item> items) {

        List<Item> itemDTOList = new ArrayList<>();
        for (com.mana.innovative.domain.Item item : items) {

            Item itemDTO = new Item();
            itemDTO = getConvertedItemDTOFromItemDomain(itemDTO, item);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }
}
