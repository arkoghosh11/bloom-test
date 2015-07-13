package com.mana.innovative.controller;

import eg.java.net.web.jspx.engine.annotation.JspxWebControl;
import eg.java.net.web.jspx.ui.controls.html.elements.Label;
import eg.java.net.web.jspx.ui.pages.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Bloom/Rono on 7/10/2015 11:59 AM.
 * This class is DemoTestPageController
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
public class DemoTestPageController extends Page {

    private static final Logger logger = LoggerFactory.getLogger( DemoTestPageController.class );


    @JspxWebControl
    private Label result;

    @Override
    public void pageLoaded( ) {

        result.setText( "New Message" );
    }
}
