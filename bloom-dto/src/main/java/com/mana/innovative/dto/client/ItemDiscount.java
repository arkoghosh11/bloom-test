package com.mana.innovative.dto.client;

import com.mana.innovative.dto.adapter.DateFormatAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
@XmlRootElement( name = "item_discount", namespace = "/Bloom/rest/items" )
public class ItemDiscount {

    private long itemDiscountId;

    private double discountPercent;

    /**
     * IMP {@link UserRole this#userRoleName()} The total number of discounts for a particular item cannot exceed 7 |
     * IMP MAX_USER_ROLES and CANNOT BE LESS THAN ZERO
     */

    private String userRole;

    private String discountType;

    private boolean isActive;

    private Date startDate;

    private Date endDate;


    /**
     * Gets item discount id.
     *
     * @return the item discount id
     */
    @XmlElement( name = "item_discount_id" )
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
    @XmlElement( name = "discount_percent" )
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
    @XmlElement( name = "user_role_name" )
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
    @XmlElement( name = "discount_type" )
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
    @XmlElement( name = "is_active" )
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
    @XmlJavaTypeAdapter( DateFormatAdapter.class )
    @XmlElement( name = "start_Date", type = String.class, nillable = false )
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
    @XmlJavaTypeAdapter( DateFormatAdapter.class )
    @XmlElement( name = "end_date", type = String.class, nillable = false )
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

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof ItemDiscount ) ) return false;
        ItemDiscount that = ( ItemDiscount ) o;
        return Objects.equals( getItemDiscountId( ), that.getItemDiscountId( ) ) &&
                Objects.equals( getDiscountPercent( ), that.getDiscountPercent( ) ) &&
                Objects.equals( isActive( ), that.isActive( ) ) &&
                Objects.equals( getUserRole( ), that.getUserRole( ) ) &&
                Objects.equals( getDiscountType( ), that.getDiscountType( ) ) &&
                Objects.equals( getStartDate( ), that.getStartDate( ) ) &&
                Objects.equals( getEndDate( ), that.getEndDate( ) );
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
                '}';
    }
}
