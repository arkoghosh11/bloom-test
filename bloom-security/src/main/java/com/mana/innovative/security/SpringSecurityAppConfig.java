package com.mana.innovative.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Bloom/Rono on 7/8/2015 1:41 AM.
 * This class is AppConfig
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Configuration
//@EnableWebMvc
//@ComponentScan( { "com.mana.innovative.security.core.*" } )
@EnableTransactionManagement
@Import( { SpringSecurityConfig.class } )
public class SpringSecurityAppConfig {

    private static final Logger logger = LoggerFactory.getLogger( SpringSecurityAppConfig.class );

    static {
        logger.debug( "Initializing com.mana.innovative.security.SpringSecurityAppConfig" );
    }

}
