package com.mana.innovative.domain.client;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * The type Item.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma, vsssadik@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "items" )
public class Item {

    /**
     * The Item id.
     */
/* */
    @Id
    @Column( name = "item_id", unique = true, updatable = false, nullable = false )
    @GeneratedValue( strategy = GenerationType.TABLE )
//    @GeneratedValue (generator = "gen")
//    @GenericGenerator (name = "gen", strategy = "foreign", parameters = { @Parameter (name = "property", value =
//            "item") })
    private long itemId;
    /**
     * The Item price.
     */
    @Column( name = "item_price" )
    private Double itemPrice;
    /**
     * The Quantity.
     */
    @Column( name = "quantity" )
    private Double quantity;
    /**
     * The Weight.
     */
    @Column( name = "weight" )
    private Double weight;

    /**
     * The Item price currency.
     */
    @Column( name = "item_price_currency" )
    private String itemPriceCurrency;
    /**
     * The Item name.
     */
    @Column( name = "item_name" )
    private String itemName;
    /**
     * The Item type.
     */
    @Column( name = "item_type" )
    private String itemType;
    /**
     * The Item sub type.
     */
    @Column( name = "item_sub_type" )
    private String itemSubType;
    /**
     * The Bought from.
     */
    @Column( name = "bought_from" )
    private String boughtFrom;
    /**
     * The Quantity type.
     */
    @Column( name = "quantity_type" )
    private String quantityType;
    /**
     * The Weighted unit.
     */
    @Column( name = "weighted_unit" )
    private String weightedUnit;

    /**
     * The Bought date.
     */
    @Column( name = "bought_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date boughtDate;
    /**
     * The Created date.
     */
    @Column( name = "created_date", updatable = false )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

    /**
     * The Shop item.
     */
    @ManyToOne( cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH } )
    @JoinColumn( name = "shop_id", updatable = true )
    private Shop shopItem;

    /**
     * Gets item id.
     *
     * @return the item id
     */
    public long getItemId( ) {

        return itemId;
    }

    /**
     * Sets item id.
     *
     * @param itemId the item id
     */
    public void setItemId( long itemId ) {

        this.itemId = itemId;
    }

    /**
     * Gets item price.
     *
     * @return the item price
     */
    public Double getItemPrice( ) {

        return itemPrice;
    }

    /**
     * Sets item price.
     *
     * @param itemPrice the item price
     */
    public void setItemPrice( Double itemPrice ) {

        this.itemPrice = itemPrice;
    }

    /**
     * Gets item price currency.
     *
     * @return the item price currency
     */
    public String getItemPriceCurrency( ) {

        return itemPriceCurrency;
    }

    /**
     * Sets item price currency.
     *
     * @param itemPriceCurrency the item price currency
     */
    public void setItemPriceCurrency( String itemPriceCurrency ) {

        this.itemPriceCurrency = itemPriceCurrency;
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public String getItemName( ) {

        return itemName;
    }

    /**
     * Sets item name.
     *
     * @param itemName the item name
     */
    public void setItemName( String itemName ) {

        this.itemName = itemName;
    }

    /**
     * Gets item type.
     *
     * @return the item type
     */
    public String getItemType( ) {

        return itemType;
    }

    /**
     * Sets item type.
     *
     * @param itemType the item type
     */
    public void setItemType( String itemType ) {

        this.itemType = itemType;
    }

    /**
     * Gets item sub type.
     *
     * @return the item sub type
     */
    public String getItemSubType( ) {
        return itemSubType;
    }

    /**
     * Sets item sub type.
     *
     * @param itemSubType the item sub type
     */
    public void setItemSubType( String itemSubType ) {
        this.itemSubType = itemSubType;
    }

    /**
     * Gets bought from.
     *
     * @return the bought from
     */
    public String getBoughtFrom( ) {
        return boughtFrom;
    }

    /**
     * Sets bought from.
     *
     * @param boughtFrom the bought from
     */
    public void setBoughtFrom( String boughtFrom ) {
        this.boughtFrom = boughtFrom;
    }

    /**
     * Gets bought date.
     *
     * @return the bought date
     */
    public Date getBoughtDate( ) {
        return boughtDate;
    }

    /**
     * Sets bought date.
     *
     * @param boughtDate the bought date
     */
    public void setBoughtDate( final Date boughtDate ) {
        this.boughtDate = boughtDate;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public Double getQuantity( ) {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity( final Double quantity ) {
        this.quantity = quantity;
    }

    /**
     * Gets quantity type.
     *
     * @return the quantity type
     */
    public String getQuantityType( ) {
        return quantityType;
    }

    /**
     * Sets quantity type.
     *
     * @param quantityType the quantity type
     */
    public void setQuantityType( String quantityType ) {
        this.quantityType = quantityType;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Double getWeight( ) {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight( final Double weight ) {
        this.weight = weight;
    }


    /**
     * Gets weighted unit.
     *
     * @return the weighted unit
     */
    public String getWeightedUnit( ) {
        return weightedUnit;
    }

    /**
     * Sets weighted unit.
     *
     * @param weightedUnit the weighted unit
     */
    public void setWeightedUnit( final String weightedUnit ) {
        this.weightedUnit = weightedUnit;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate( ) {

        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate( Date createdDate ) {

        this.createdDate = createdDate != null ? createdDate : new Date( );
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate( ) {

        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate( final Date updatedDate ) {

        this.updatedDate = updatedDate;
    }


    /**
     * Gets shop item.
     *
     * @return the shop item
     */
    public Shop getShopItem( ) {
        return shopItem;
    }

    /**
     * Sets shop item.
     *
     * @param shopItem the shop item
     */
    public void setShopItem( Shop shopItem ) {
        this.shopItem = shopItem;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Item ) ) return false;
        Item item = ( Item ) o;
        return Objects.equals( getItemId( ), item.getItemId( ) ) &&
                Objects.equals( getItemPrice( ), item.getItemPrice( ) ) &&
                Objects.equals( getQuantity( ), item.getQuantity( ) ) &&
                Objects.equals( getWeight( ), item.getWeight( ) ) &&
                Objects.equals( getItemPriceCurrency( ), item.getItemPriceCurrency( ) ) &&
                Objects.equals( getItemName( ), item.getItemName( ) ) &&
                Objects.equals( getItemType( ), item.getItemType( ) ) &&
                Objects.equals( getItemSubType( ), item.getItemSubType( ) ) &&
                Objects.equals( getBoughtFrom( ), item.getBoughtFrom( ) ) &&
                Objects.equals( getQuantityType( ), item.getQuantityType( ) ) &&
                Objects.equals( getWeightedUnit( ), item.getWeightedUnit( ) ) &&
                Objects.equals( getBoughtDate( ), item.getBoughtDate( ) );
    }

    /**
     * Returns a string representation of the object. In general, the {@code toString} method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString( ) {
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
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

}
