package com.mana.innovative.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Bloom/Rono on 6/7/2015 9:58 PM. This class is CustomUrlController
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Controller( value = "customUrlController" )
@RequestMapping( value = "/home" )
public class CustomUrlController {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( CustomUrlController.class );

    /**
     * Home string.
     *
     * @param locale the locale
     * @param model  the model
     *
     * @return the string
     */
    @RequestMapping( method = RequestMethod.GET )
    public String home( Locale locale, Model model ) {
        logger.info( "Welcome home! The client locale is {}.", locale );
        //adding some time lag to check interceptor execution
        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace( );
        }
        Date date = new Date( );
        DateFormat dateFormat = DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG, locale );

        String formattedDate = dateFormat.format( date );

        model.addAttribute( "serverTime", formattedDate );
        logger.info( "Before returning view page" );
        return "home";
    }

    /**
     * Fallback method.
     *
     * @return the string
     */
    @RequestMapping( "*" )
    @ResponseBody
    public String fallbackMethod( ) {
        return "fallback method";
    }


}
