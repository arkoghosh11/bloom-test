package com.mana.innovative.dto.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The type Item image.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@XmlRootElement( name = "item_image", namespace = "/Bloom/rest/items/" )
public class ItemImage {

    private long itemImageId;
    private int imagePriority;
    private String imageLocation;
    /**
     * Note Height of image in pixels(px)
     */
    private double imageHeight;

    /**
     * Note Width of image in pixels(px)
     */
    private double imageWidth;

    /**
     * IMP service layer will fill this value beforesending response by verifying if the file exists at the specified
     * location.
     */
    private boolean isValidLocation;


    /**
     * Gets itemImageId.
     *
     * @return the itemImageId
     */
    @XmlElement( name = "item_image_id" )
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
    @XmlElement( name = "image_priority" )
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
    @XmlElement( name = "image_location" )
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

    @XmlElement( name = "image_height" )
    public double getImageHeight( ) {
        return imageHeight;
    }

    public void setImageHeight( final double imageHeight ) {
        this.imageHeight = imageHeight;
    }

    @XmlElement( name = "image_width" )
    public double getImageWidth( ) {
        return imageWidth;
    }

    public void setImageWidth( final double imageWidth ) {
        this.imageWidth = imageWidth;
    }

    @XmlElement( name = "is_valid_location" )
    public boolean isValidLocation( ) {
        return isValidLocation;
    }

    public void setIsValidLocation( final boolean isValidLocation ) {
        this.isValidLocation = isValidLocation;
    }

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
                ", isValidLocation=" + isValidLocation +
                '}';
    }
}
