package com.mana.innovative.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Item.
 */
@Entity
@Table( name = "items" )
public class Item {

    /* */
    @Id
    @Column( name = "item_id", unique = true, updatable = false, nullable = false )
    @GeneratedValue( strategy = GenerationType.TABLE )
//    @GeneratedValue (generator = "gen")
//    @GenericGenerator (name = "gen", strategy = "foreign", parameters = { @Parameter (name = "property", value =
//            "item") })
    private long itemId;
    @Column( name = "item_price" )
    private double itemPrice;
    @Column( name = "quantity" )
    private double quantity;
    @Column( name = "weight" )
    private double weight;

    @Column( name = "item_price_currency" )
    private String itemPriceCurrency;
    @Column( name = "item_name" )
    private String itemName;
    @Column( name = "item_type" )
    private String itemType;
    @Column( name = "item_sub_type" )
    private String itemSubType;
    @Column( name = "bought_from" )
    private String boughtFrom;
    @Column( name = "quantity_type" )
    private String quantityType;
    @Column( name = "weighted_unit" )
    private String weightedUnit;

    @Column( name = "bought_date", columnDefinition = "DATETIME" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date boughtDate;
    @Column( name = "created_date", columnDefinition = "DATETIME", updatable = false )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date createdDate;
    @Column( name = "updated_date", columnDefinition = "DATETIME" )
    @Temporal( value = TemporalType.TIMESTAMP )
    private Date updatedDate;

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
    public double getItemPrice( ) {

        return itemPrice;
    }

    /**
     * Sets item price.
     *
     * @param itemPrice the item price
     */
    public void setItemPrice( double itemPrice ) {

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
    public double getQuantity( ) {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity( final double quantity ) {
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
    public double getWeight( ) {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight( final double weight ) {
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

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Item ) ) return false;

        final Item item = ( Item ) o;

        if ( itemId != item.itemId ) return false;
        if ( Double.compare( item.itemPrice, itemPrice ) != 0 ) return false;
        if ( Double.compare( item.quantity, quantity ) != 0 ) return false;
        if ( Double.compare( item.weight, weight ) != 0 ) return false;
        if ( boughtDate != null ? !boughtDate.equals( item.boughtDate ) : item.boughtDate != null ) return false;
        if ( boughtFrom != null ? !boughtFrom.equals( item.boughtFrom ) : item.boughtFrom != null ) return false;
        if ( itemName != null ? !itemName.equals( item.itemName ) : item.itemName != null ) return false;
        if ( itemPriceCurrency != null ? !itemPriceCurrency.equals( item.itemPriceCurrency ) : item.itemPriceCurrency != null )
            return false;
        if ( itemSubType != null ? !itemSubType.equals( item.itemSubType ) : item.itemSubType != null ) return false;
        if ( itemType != null ? !itemType.equals( item.itemType ) : item.itemType != null ) return false;
        if ( quantityType != null ? !quantityType.equals( item.quantityType ) : item.quantityType != null )
            return false;
        if ( shopItem != null && item.getShopItem( ) != null ) {
            if ( !shopItem.equals( item.shopItem ) ) return false;
        }
        if ( weightedUnit != null ? !weightedUnit.equalsIgnoreCase( item.weightedUnit ) : item.weightedUnit != null )
            return false;
        if ( createdDate != null ? !createdDate.equals( item.createdDate ) : item.createdDate != null ) return true;
        if ( updatedDate != null ? !updatedDate.equals( item.updatedDate ) : item.updatedDate != null ) return true;

        return true;
    }

    /**
     * Returns a string representation of the object. In general, the {@code toString} method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read.
     *
     * @return {@link String}a string representation of the object.
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
