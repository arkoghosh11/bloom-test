package com.mana.innovative.constants;

/**
 * The enum Quantity type.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public enum QuantityType {

    /**
     * The UNIT.
     */
    UNIT( 1 ),
    /**
     * The DOZEN.
     */
    DOZEN( 12 ),
    /**
     * The SCORE.
     */
    SCORE( 20 );

    /**
     * The Value.
     */
    private final int value;

    /**
     * Instantiates a new Quantity type.
     *
     * @param value the value
     */
    QuantityType( int value ) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue( ) {
        return value;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString( ) {
        return super.toString( ).toLowerCase( );
    }

//    public String getValue(QuantityType quantityType) {
//
//        if (quantityType == KG) return "KG";
//        if (quantityType == POUND) return "Pound";
//        if (quantityType == GRAM) return "Gram";
//        if (quantityType == OUNCES) return "Ounces";
//        return "Unknown";
//    }
}
