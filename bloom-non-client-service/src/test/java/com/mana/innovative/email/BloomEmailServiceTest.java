package com.mana.innovative.email;/**
 * Created by Rono on 2/10/2015. This is a class for .. todo
 */

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Bloom on 2/10/2015 : 3:59 PM
 * todo This class is for ...
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/email-config-test.xml" } )
public class BloomEmailServiceTest {

    @Resource( name = "bloomService" )
    private BloomEmailService bloomEmailService;

    @Value( value = "${email_receiver}" )
    private String emailIds;

    @Test
    public void testMailService( ) {
        Assert.assertNotNull( emailIds );
//        //Create the application context
//        ApplicationContext context = new FileSystemXmlApplicationContext("application-context.xml");

//        //Get the mailer instance
//        ApplicationMailer mailer = (ApplicationMailer) context.getBean("mailService");
//        meankur1@gmail.com = ankur email
//        ruchi.joshi@ndsu.edu = email for ruchi joshi
        //Send a composed mail
        bloomEmailService.sendMail( "arkogh@gmail.com,arkoghosh@hotmail.com", "Test Subject", "Testing body" );

//        Send a pre-configured mail
        bloomEmailService.sendPreConfiguredMail( "Hello. How are you ????" );
    }
}
