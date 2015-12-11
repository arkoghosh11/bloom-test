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

//    @Resource
//    private DriverManagerDataSource dataSource;

//    @Bean( name = "springSecurityFilterChain" )
//    public DelegatingFilterProxy getDelegatingFilterProxy( ) {
//        return new DelegatingFilterProxy( );
//    }

    @Resource
    public void configAuthentication( AuthenticationManagerBuilder auth ) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username,password, enabled from users1 where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, role from user_roles1 where username=?");
        logger.debug( "Starting SpringSecurityConfig#configAuthentication" );
        auth.userDetailsService( loginService ).passwordEncoder( getPasswordEncoder( ) );
        logger.debug( "Finishing SpringSecurityConfig#configAuthentication" );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        logger.debug( "Starting SpringSecurityConfig#configure" );
        http.authorizeRequests( ).antMatchers( "/view/hello/admin", "/view/hello/admin/**" )
                .access( "hasRole('ROLE_ADMIN')" ).and( ).formLogin( )
                .loginPage( "/view/hello/login" ).failureUrl( "/view/hello/login?error" )
                .usernameParameter( "username" )
                .passwordParameter( "password" )
                .and( ).logout( ).logoutSuccessUrl( "/view/hello/login?logout" )
                .and( ).exceptionHandling( ).accessDeniedPage( "/view/hello/error" )
                .and( ).csrf( );
        logger.debug( "Finishing SpringSecurityConfig#configure" );
    }

    @Bean
    public PasswordEncoder getPasswordEncoder( ) {
        return new BCryptPasswordEncoder( );
    }

}
