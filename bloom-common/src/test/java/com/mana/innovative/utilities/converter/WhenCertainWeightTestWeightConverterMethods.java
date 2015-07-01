package com.mana.innovative.utilities.converter;/**
 * Created by Rono on 2/19/2015. This is a class for .. todo
 */

import com.mana.innovative.constants.TestConstants;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * The type When certain weight test weight converter methods.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "/weight-converter-test-config.xml" )
public class WhenCertainWeightTestWeightConverterMethods {

    /**
     * The Weight converter.
     */
    @Resource
    private WeightConverter weightConverter;

    /**
     * The Default value.
     */
    private double defaultValue;
    /**
     * The Expected result.
     */
    private double expectedResult;
    /**
     * The Fail message.
     */
    private String failMessage;

    /**
     * Sets up.
     */
    @Before
    public void setUp( ) {

        defaultValue = 10;
        failMessage = TestConstants.FAIL_MESSAGE;
    }

    /**
     * Test convert ounce to pound.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertOunceToPound( ) throws Exception {

        double result = weightConverter.convertFromOunceToPound( defaultValue );
        expectedResult = ( defaultValue * weightConverter.getOunceToPoundRatio( ) );
        Assert.assertEquals( failMessage, expectedResult, result );

    }

    /**
     * Test convert from ounce to gram.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromOunceToGram( ) throws Exception {

        double result = weightConverter.convertFromOunceToGram( defaultValue );
        expectedResult = ( defaultValue * weightConverter.getOunceToGramRatio( ) );
        Assert.assertEquals( failMessage, expectedResult, result );

    }

    /**
     * Test convert from gram to ounce.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromGramToOunce( ) throws Exception {

        double result = weightConverter.convertFromGramToOunce( defaultValue );
        expectedResult = ( defaultValue * ( 1 / weightConverter.getOunceToGramRatio( ) ) );
        Assert.assertEquals( failMessage, expectedResult, result );

    }

    /**
     * Test convert from gram to pound.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromGramToPound( ) throws Exception {

        double result = weightConverter.convertFromGramToPound( defaultValue );
        expectedResult = ( defaultValue * ( weightConverter.getGramToPoundRatio( ) ) );
        Assert.assertEquals( failMessage, expectedResult, result );

    }


    /**
     * Test convert from kilogram to pound.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromKilogramToPound( ) throws Exception {

        double result = weightConverter.convertFromKilogramToPound( defaultValue );
        expectedResult = ( defaultValue * ( weightConverter.getKilogramToPoundRatio( ) ) );
        Assert.assertEquals( failMessage, expectedResult, result );

    }

    /**
     * Test convert from pound to kilogram.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromPoundToKilogram( ) throws Exception {

        double result = weightConverter.convertFromPoundToKilogram( defaultValue );
        expectedResult = ( defaultValue * ( 1 / weightConverter.getKilogramToPoundRatio( ) ) );
        Assert.assertEquals( failMessage, expectedResult, result );
    }

    /**
     * Test convert from pound to gram.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromPoundToGram( ) throws Exception {

        double result = weightConverter.convertFromPoundToGram( defaultValue );
        expectedResult = ( defaultValue * ( 1 / weightConverter.getGramToPoundRatio( ) ) );
        Assert.assertEquals( failMessage, expectedResult, result );
    }

    /**
     * Test convert from pound to ounce.
     *
     * @throws Exception the exception
     */
    @Test
    public void testConvertFromPoundToOunce( ) throws Exception {

        double result = weightConverter.convertFromPoundToOunce( defaultValue );
        expectedResult = ( defaultValue * ( 1 / weightConverter.getOunceToPoundRatio( ) ) );
        Assert.assertEquals( failMessage, expectedResult, result );
    }

    /**
     * Test custom converter with given ratio.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCustomConverterWithGivenRation( ) throws Exception {

        double tempRatio = 50.0247;
        double result = weightConverter.customConverterWithGivenRatio( defaultValue, tempRatio );
        expectedResult = ( defaultValue * tempRatio );
        Assert.assertEquals( failMessage, expectedResult, result );
    }

}
