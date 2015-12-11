package com.mana.innovative.domain.client;

import com.mana.innovative.domain.consumer.UserRole;

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
 * The class ItemDiscount is for todo.
 * Created by BLOOM on 9/19/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Entity
@Table( name = "item_discounts",
        uniqueConstraints = @UniqueConstraint( name = "FK_items_item_discounts_key", columnNames = { "item_id" } ) )
public class ItemDiscount {

    /**
     * The Item discount id.
     */
    @Id
    @Column( name = "item_discount_id", length = 20 )
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long itemDiscountId;

    /**
     * The Discount percent.
     */
    @Column( name = "discount_percent", length = 20, precision = 10 )
    private double discountPercent;

    /**
     * IMP {@link UserRole this#userRoleName()} The total number of discounts for a particular item cannot exceed 7 |
     * IMP MAX_USER_ROLES and CANNOT BE LESS THAN ZERO
     */
    @Column( name = "user_role_name", length = 20 )
    private String userRole;

    /**
     * The Discount type.
     */
    @Column( name = "discount_type" )
    private String discountType;

    /**
     * The Is active.
     */
    @Column( name = "is_active" )
    private boolean isActive;
    /**
     * The Start date.
     */
    @Column( name = "start_date", columnDefinition = "timestamp" )
    private Date startDate;
    /**
     * The End date.
     */
    @Column( name = "end_date", columnDefinition = "timestamp" )
    private Date endDate;

    /**
     * The Created date.
     */
    @Column( name = "created_date", columnDefinition = "timestamp" )
    private Date createdDate;
    /**
     * The Updated date.
     */
    @Column( name = "updated_date", columnDefinition = "timestamp" )
    private Date updatedDate;

    /**
     * The Item.
     */
    @ManyToOne( cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE } )
    @JoinColumn( name = "item_id", unique = true, referencedColumnName = "item_id" )
    private Item item;

    /**
     * Gets item discount id.
     *
     * @return the item discount id
     */
    public long getItemDiscountId( ) {
        return itemDiscountId;
    }

    /**
     * Sets item discount id.
     *
     * @param itemDiscountId the item discount id
     */
    public void setItemDiscountId( final long itemDiscountId ) {
        this.itemDiscountId = itemDiscountId;
    }

    /**
     * Gets discount percent.
     *
     * @return the discount percent
     */
    public double getDiscountPercent( ) {
        return discountPercent;
    }

    /**
     * Sets discount percent.
     *
     * @param discountPercent the discount percent
     */
    public void setDiscountPercent( final double discountPercent ) {
        this.discountPercent = discountPercent;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public String getUserRole( ) {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole( final String userRole ) {
        this.userRole = userRole;
    }

    /**
     * Gets discount type.
     *
     * @return the discount type
     */
    public String getDiscountType( ) {
        return discountType;
    }

    /**
     * Sets discount type.
     *
     * @param discountType the discount type
     */
    public void setDiscountType( final String discountType ) {
        this.discountType = discountType;
    }

    /**
     * Is active.
     *
     * @return the boolean
     */
    public boolean isActive( ) {
        return isActive;
    }

    /**
     * Sets is active.
     *
     * @param isActive the is active
     */
    public void setIsActive( final boolean isActive ) {
        this.isActive = isActive;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate( ) {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate( final Date startDate ) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate( ) {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate( final Date endDate ) {
        this.endDate = endDate;
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
        if ( !( o instanceof ItemDiscount ) ) return false;
        ItemDiscount itemDiscount = ( ItemDiscount ) o;

        return Objects.equals( getItemDiscountId( ), itemDiscount.getItemDiscountId( ) ) &&
                Objects.equals( getDiscountPercent( ), itemDiscount.getDiscountPercent( ) ) &&
                Objects.equals( isActive( ), itemDiscount.isActive( ) ) &&
                Objects.equals( getUserRole( ), itemDiscount.getUserRole( ) ) &&
                Objects.equals( getDiscountType( ), itemDiscount.getDiscountType( ) ) &&
                Objects.equals( getStartDate( ), itemDiscount.getStartDate( ) ) &&
                Objects.equals( getEndDate( ), itemDiscount.getEndDate( ) );
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return "ItemDiscount{" +
                "itemDiscountId=" + itemDiscountId +
                ", discountPercent=" + discountPercent +
                ", userRole='" + userRole + '\'' +
                ", discountType='" + discountType + '\'' +
                ", isActive=" + isActive +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
