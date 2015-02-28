package com.mana.innovative.constants;

/**
 * Created by Rono on 2/25/2015.
 * This is a class for .. todo
 */
public enum QuantityType {

    UNIT(1),
    DOZEN(12),
    SCORE(20);

    private int value;

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
