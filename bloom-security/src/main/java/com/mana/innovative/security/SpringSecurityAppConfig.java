package com.mana.innovative.security;

import com.mana.innovative.interceptor.RequestProcessingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;

/**
 * Created by Bloom/Rono on 7/8/2015 1:41 AM.
 * This class is AppConfig
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh@hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Configuration
@EnableWebMvc
@ComponentScan( { "com.mana.innovative.controller",
        "com.mana.innovative.interceptor" } )
@EnableTransactionManagement
@Import( { SpringSecurityConfig.class } )
public class SpringSecurityAppConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger( SpringSecurityAppConfig.class );

    static {
        logger.debug( "Initializing com.mana.innovative.security.SpringSecurityAppConfig" );
    }

    @Resource
    private RequestProcessingInterceptor requestProcessingInterceptor;

    @Bean
    public InternalResourceViewResolver viewResolver( ) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver( );
        viewResolver.setViewClass( JstlView.class );
        viewResolver.setPrefix( "/WEB-INF/jsps/" );
        viewResolver.setSuffix( ".jsp" );
        return viewResolver;
    }

    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        registry.addInterceptor( requestProcessingInterceptor ).addPathPatterns( "/home" );
    }

}
