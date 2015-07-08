package com.mana.innovative.security;

import com.mana.innovative.authentication.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
@EnableWebSecurity
public class SpringSecurityAppConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger( SpringSecurityAppConfig.class );

    @Resource
    LoginService loginService;

    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( loginService ).passwordEncoder( passwordEncoder( ) );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests( ).antMatchers( "/admin/**" )
                .access( "hasRole('ROLE_ADMIN')" ).and( ).formLogin( )
                .loginPage( "/login" ).failureUrl( "/login?error" )
                .usernameParameter( "username" )
                .passwordParameter( "password" )
                .and( ).logout( ).logoutSuccessUrl( "/login?logout" )
                .and( ).csrf( )
                .and( ).exceptionHandling( ).accessDeniedPage( "/403" );
    }

    @Bean
    public PasswordEncoder passwordEncoder( ) {
        return new BCryptPasswordEncoder( );
    }
}
