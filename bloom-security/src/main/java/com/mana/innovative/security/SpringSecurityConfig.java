package com.mana.innovative.security;

import com.mana.innovative.authentication.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.annotation.Resource;

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
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger( SpringSecurityConfig.class );
    @Resource
    private LoginService loginService;

    @Bean( name = "springSecurityFilterChain" )
    public DelegatingFilterProxy getDelegatingFilterProxy( ) {
        return new DelegatingFilterProxy( );
    }

    @Resource
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( loginService ).passwordEncoder( getPasswordEncoder( ) );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests( ).antMatchers( "view/admin/**" )
                .access( "hasRole('ROLE_ADMIN')" ).and( ).formLogin( )
                .loginPage( "/login" ).failureUrl( "/login?error" )
                .usernameParameter( "username" )
                .passwordParameter( "password" )
                .and( ).logout( ).logoutSuccessUrl( "/login?logout" )
                .and( ).csrf( )
                .and( ).exceptionHandling( ).accessDeniedPage( "/error" );
    }

    @Bean
    public PasswordEncoder getPasswordEncoder( ) {
        return new BCryptPasswordEncoder( );
    }
}
