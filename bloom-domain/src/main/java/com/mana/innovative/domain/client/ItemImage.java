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
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.Objects;

/**
 * The type Item image.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "item_images",
        uniqueConstraints = @UniqueConstraint( name = "FK_items_item_images_key", columnNames = { "item_id" } ) )
public class ItemImage {

    /**
     * The Item image id.
     */
    @Id
    @Column( name = "item_image_id", length = 20 )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long itemImageId;

    /**
     * The Image priority.
     */
    @Column( name = "image_priority", length = 20 )
    private int imagePriority;

    /**
     * The Image location.
     */
    @Column( name = "image_location" )
    private String imageLocation;

    /**
     * Note Height of image in pixels(px)
     */
    @Column( name = "image_height", nullable = false, length = 10, precision = 5 )
    private double imageHeight;

    /**
     * Note Width of image in pixels(px)
     */
    @Column( name = "image_width", nullable = false, length = 10, precision = 5 )
    private double imageWidth;

    /**
     * The Created date.
     */
    @Column( name = "created_date", nullable = false, columnDefinition = "timestamp" )
    private Date createdDate;

    /**
     * The Updated date.
     */
    @Column( name = "updated_date", nullable = false, columnDefinition = "timestamp" )
    private Date updatedDate;

    /**
     * The Item.
     */
    @ManyToOne( cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE } )
    @JoinColumn( name = "item_id", updatable = true )
    private Item item;

    /**
     * Gets itemImageId.
     *
     * @return the itemImageId
     */
    public long getItemImageId( ) {
        return itemImageId;
    }

    /**
     * Sets itemImageId.
     *
     * @param itemImageId the itemImageId
     */
    public void setItemImageId( final long itemImageId ) {
        this.itemImageId = itemImageId;
    }

    /**
     * Gets image priority.
     *
     * @return the image priority
     */
    public int getImagePriority( ) {
        return imagePriority;
    }

    /**
     * Sets image priority.
     *
     * @param imagePriority the image priority
     */
    public void setImagePriority( final int imagePriority ) {
        this.imagePriority = imagePriority;
    }

    /**
     * Gets image location.
     *
     * @return the image location
     */
    public String getImageLocation( ) {
        return imageLocation;
    }

    /**
     * Sets image location.
     *
     * @param imageLocation the image location
     */
    public void setImageLocation( final String imageLocation ) {
        this.imageLocation = imageLocation;
    }

    /**
     * Gets image height.
     *
     * @return the image height
     */
    public double getImageHeight( ) {
        return imageHeight;
    }

    /**
     * Sets image height.
     *
     * @param imageHeight the image height
     */
    public void setImageHeight( final double imageHeight ) {
        this.imageHeight = imageHeight;
    }

    /**
     * Gets image width.
     *
     * @return the image width
     */
    public double getImageWidth( ) {
        return imageWidth;
    }

    /**
     * Sets image width.
     *
     * @param imageWidth the image width
     */
    public void setImageWidth( final double imageWidth ) {
        this.imageWidth = imageWidth;
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
    public void setCreatedDate( final Date createdDate ) {
        this.createdDate = createdDate;
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
     * Gets item.
     *
     * @return the item
     */
    public Item getItem( ) {
        return item;
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem( final Item item ) {
        this.item = item;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof ItemImage ) ) return false;
        ItemImage itemImage = ( ItemImage ) o;
        return Objects.equals( getItemImageId( ), itemImage.getItemImageId( ) ) &&
                Objects.equals( getImagePriority( ), itemImage.getImagePriority( ) ) &&
                Objects.equals( getImageLocation( ), itemImage.getImageLocation( ) ) &&
                Objects.equals( getImageHeight( ), itemImage.getImageHeight( ) ) &&
                Objects.equals( getImageWidth( ), itemImage.getImageWidth( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "ItemImage{" +
                "itemImageId=" + itemImageId +
                ", imagePriority=" + imagePriority +
                ", imageLocation='" + imageLocation + '\'' +
                ", imageHeight=" + imageHeight +
                ", imageWidth=" + imageWidth +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
