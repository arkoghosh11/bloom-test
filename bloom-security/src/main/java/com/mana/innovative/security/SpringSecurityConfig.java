package com.mana.innovative.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by Bloom/Rono on 7/8/2015 1:55 AM.
 * This class is SpringSecurityConfig
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger( SpringSecurityConfig.class );

}
