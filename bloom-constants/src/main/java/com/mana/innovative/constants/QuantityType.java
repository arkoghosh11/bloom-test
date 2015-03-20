package com.mana.innovative.constants;

/**
 * The enum Quantity type.
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public enum QuantityType {

    UNIT(1),
    DOZEN(12),
    SCORE(20);

    private final int value;

    QuantityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
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
