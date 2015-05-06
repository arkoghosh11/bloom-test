package com.mana.innovative.utilities.converter;/**
 * Created by Rono on 2/19/2015.
 * This is a class for .. todo 
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type Weight converter.

 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
public final class WeightConverter {

    /**
     * The Ounce to gram ratio.
     */
    @Value(value = "${ounce_to_gram}")
    private double ounceToGramRatio;
    /**
     * The Kilogram to pound ratio.
     */
    @Value(value = "${kilogram_to_pound}")
    private double kilogramToPoundRatio;
    /**
     * The Gram to pound ratio.
     */
    @Value(value = "${gram_to_pound}")
    private double gramToPoundRatio;
    /**
     * The Ounce to pound ratio.
     */
    @Value(value = "${ounce_to_pound}")
    private double ounceToPoundRatio;

    /**
     * Gets ounce to gram ratio.
     *
     * @return the ounce to gram ratio
     */
    public double getOunceToGramRatio() {
        return ounceToGramRatio;
    }

    /**
     * Gets kilogram to pound ratio.
     *
     * @return the kilogram to pound ratio
     */
    public double getKilogramToPoundRatio() {
        return kilogramToPoundRatio;
    }

    /**
     * Gets gram to pound ratio.
     *
     * @return the gram to pound ratio
     */
    public double getGramToPoundRatio() {
        return gramToPoundRatio;
    }

    /**
     * Gets ounce to pound ratio.
     *
     * @return the ounce to pound ratio
     */
    public double getOunceToPoundRatio() {
        return ounceToPoundRatio;
    }

    /**
     * Convert from ounce to gram.
     *
     * @param amountInOunce the amount in ounce
     * @return the double
     */
    public double convertFromOunceToGram(final double amountInOunce) {
        return amountInOunce * this.ounceToGramRatio;
    }

    /**
     * Convert from kilogram to pound.
     *
     * @param amountInKiloGram the amount in kilo gram
     * @return the double
     */
    public double convertFromKilogramToPound(final double amountInKiloGram) {
        return amountInKiloGram * this.kilogramToPoundRatio;
    }

    /**
     * Convert from gram to pound.
     *
     * @param amountInGram the amount in gram
     * @return the double
     */
    public double convertFromGramToPound(final double amountInGram) { return amountInGram * this.gramToPoundRatio; }

    /**
     * Convert from ounce to pound.
     *
     * @param amountInOunce the amount in ounce
     * @return the double
     */
    public double convertFromOunceToPound(final double amountInOunce) {
        return amountInOunce * this.ounceToPoundRatio;
    }

//    @Note now methods to convert the other way

    /**
     * Convert from gram to ounce.
     *
     * @param amountInOunce the amount in ounce
     * @return the double
     */
    public double convertFromGramToOunce(final double amountInOunce) {
        return amountInOunce * (1 / this.ounceToGramRatio);
    }

    /**
     * Convert from pound to kilogram.
     *
     * @param amountInPound the amount in pound
     * @return the double
     */
    public double convertFromPoundToKilogram(final double amountInPound) {
        return amountInPound * (1 / this.kilogramToPoundRatio);
    }

    /**
     * Convert from pound to gram.
     *
     * @param amountInPound the amount in pound
     * @return the double
     */
    public double convertFromPoundToGram(final double amountInPound) {
        return amountInPound * (1 / this.gramToPoundRatio);
    }

    /**
     * Convert from pound to ounce.
     *
     * @param amountInPound the amount in pound
     * @return the double
     */
    public double convertFromPoundToOunce(final double amountInPound) {
        return amountInPound * (1 / this.ounceToPoundRatio);
    }

    /**
     * Custom converter with given ratio.
     *
     * @param dataToConvert the data to convert
     * @param ratioForConversion the ratio for conversion
     * @return the double
     */
    public double customConverterWithGivenRatio(final double dataToConvert, final double ratioForConversion) {
        return dataToConvert * ratioForConversion;
    }

}
