package com.mana.innovative.constants;

/**
 * The enum Weighted unit.
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public enum WeightedUnit {

    /**
     * The KG.
     */
    KG,
    /**
     * The POUND.
     */
    POUND,
    /**
     * The GRAM.
     */
    GRAM,
    /**
     * The OUNCES.
     */
    OUNCES;

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }
}
