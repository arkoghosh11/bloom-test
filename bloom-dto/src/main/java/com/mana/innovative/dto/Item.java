package com.mana.innovative.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * The type Item.
 */
@XmlRootElement(name = "item", namespace = "http://localhost:8080/bloom-test/rest/items")
public class Item {

    private long itemId;
    private double itemPrice;
    private double quantity;
    private double weight;

    private String itemPriceCurrency;
    private String itemName;
    private String itemType;
    private String itemSubType;
    private String boughtFrom;
    private String quantityType;
    private String weightedUnit;

    private Date boughtDate;

    /**
     * Gets item id.
     *
     * @return the item id
     */
    @XmlElement(name = "item_id", nillable = false)
    public long getItemId() {

        return itemId;
    }

    /**
     * Sets item id.
     *
     * @param itemId the item id
     */
    public void setItemId(long itemId) {

        this.itemId = itemId;
    }

    /**
     * Gets item price.
     *
     * @return the item price
     */
    @XmlElement(name = "item_price", defaultValue = "0.00", nillable = false)
    public double getItemPrice() {

        return itemPrice;
    }

    /**
     * Sets item price.
     *
     * @param itemPrice the item price
     */
    public void setItemPrice(double itemPrice) {

        this.itemPrice = itemPrice;
    }

    /**
     * Gets item price currency.
     *
     * @return the item price currency
     */
    @XmlElement(name = "item_price_currency", defaultValue = "Dollar", nillable = false)
    public String getItemPriceCurrency() {

        return itemPriceCurrency;
    }

    /**
     * Sets item price currency.
     *
     * @param itemPriceCurrency the item price currency
     */
    public void setItemPriceCurrency(String itemPriceCurrency) {

        this.itemPriceCurrency = itemPriceCurrency;
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    @XmlElement(name = "item_name", defaultValue = "Unknown", nillable = false)
    public String getItemName() {

        return itemName;
    }

    /**
     * Sets item name.
     *
     * @param itemName the item name
     */
    public void setItemName(String itemName) {

        this.itemName = itemName;
    }

    /**
     * Gets item type.
     *
     * @return the item type
     */
    @XmlElement(name = "item_type", defaultValue = "Food", nillable = false)
    public String getItemType() {

        return itemType;
    }

    /**
     * Sets item type.
     *
     * @param itemType the item type
     */
    public void setItemType(String itemType) {

        this.itemType = itemType;
    }

    /**
     * Gets item sub type.
     *
     * @return the item sub type
     */
    @XmlElement(name = "item_sub_type", defaultValue = "None", nillable = false)
    public String getItemSubType() {
        return itemSubType;
    }

    /**
     * Sets item sub type.
     *
     * @param itemSubType the item sub type
     */
    public void setItemSubType(String itemSubType) {
        this.itemSubType = itemSubType;
    }

    /**
     * Gets bought from.
     *
     * @return the bought from
     */
    @XmlElement(name = "bought_from", defaultValue = "SomeStore", nillable = false)
    public String getBoughtFrom() {
        return boughtFrom;
    }

    /**
     * Sets bought from.
     *
     * @param boughtFrom the bought from
     */
    public void setBoughtFrom(String boughtFrom) {
        this.boughtFrom = boughtFrom;
    }

    /**
     * Gets bought date.
     *
     * @return the bought date
     */
    @XmlElement(name = "bought_date", nillable = false)
    public Date getBoughtDate() {
        return boughtDate;
    }

    /**
     * Sets bought date.
     *
     * @param boughtDate the bought date
     */
    public void setBoughtDate(final Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    @XmlElement(name = "quantity", defaultValue = "0", nillable = false)
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(final double quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets quantity type.
     *
     * @return the quantity type
     */
    @XmlElement(name = "quantity_type", defaultValue = "units", nillable = false)
    public String getQuantityType() {
        return quantityType;
    }

    /**
     * Sets quantity type.
     *
     * @param quantityType the quantity type
     */
    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    @XmlElement(name = "weight", defaultValue = "0", nillable = false)
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(final double weight) {
        this.weight = weight;
    }

    /**
     * Gets weighted unit.
     *
     * @return the weighted unit
     */
    @XmlElement(name = "weighted_unit", defaultValue = "ounces", nillable = false)
    public String getWeightedUnit() {
        return weightedUnit;
    }

    /**
     * Sets weighted unit.
     *
     * @param weightedUnit the weighted unit
     */
    public void setWeightedUnit(final String weightedUnit) {
        this.weightedUnit = weightedUnit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (itemId != item.itemId) return false;
        if (Double.compare(item.itemPrice, itemPrice) != 0) return false;
        if (Double.compare(item.quantity, quantity) != 0) return false;
        if (Double.compare(item.weight, weight) != 0) return false;
        if (!boughtDate.equals(item.boughtDate)) return false;
        if (!boughtFrom.equals(item.boughtFrom)) return false;
        if (!itemName.equals(item.itemName)) return false;
        if (!itemPriceCurrency.equals(item.itemPriceCurrency)) return false;
        if (!itemSubType.equals(item.itemSubType)) return false;
        if (!itemType.equals(item.itemType)) return false;
        if (!quantityType.equals(item.quantityType)) return false;
        if (!weightedUnit.equals(item.weightedUnit)) return false;

        return true;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     *
     * @return {@link String}a string representation of the object.
     */
    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", itemPriceCurrency='" + itemPriceCurrency + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemSubType='" + itemSubType + '\'' +
                ", boughtFrom='" + boughtFrom + '\'' +
                ", quantityType='" + quantityType + '\'' +
                ", weightedUnit='" + weightedUnit + '\'' +
                ", boughtDate=" + boughtDate +
                '}';
    }

/**
 *  Note
 *  Don't need to override if need to override use the following lines uncommented. The generated Code adheres to JDK
 *  hashcode overriding from Object super Class
 */
//@Override
//public int hashCode() {
//    int result;
//    long temp;
//    result = itemId;
//    temp = Double.doubleToLongBits(itemPrice);
//    result = 31 * result + (int) (temp ^ (temp >>> 32));
//    result = 31 * result + itemPriceCurrency.hashCode();
//    result = 31 * result + itemName.hashCode();
//    result = 31 * result + itemType.hashCode();
//    result = 31 * result + itemSubType.hashCode();
//    result = 31 * result + boughtFrom.hashCode();
//    result = 31 * result + boughtDate.hashCode();
//    temp = Double.doubleToLongBits(quantity);
//    result = 31 * result + (int) (temp ^ (temp >>> 32));
//    result = 31 * result + quantityType.hashCode();
//    temp = Double.doubleToLongBits(weight);
//    result = 31 * result + (int) (temp ^ (temp >>> 32));
//    result = 31 * result + weightedUnit.hashCode();
//    result = 31 * result + createdDate.hashCode();
//    result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
//    return result;
//}
}
