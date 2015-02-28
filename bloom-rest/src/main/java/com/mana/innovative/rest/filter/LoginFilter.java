package com.mana.innovative.rest.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by alex1 on 1/29/2015.
 * This is a class for .. todo
 */
//@javax.servlet.annotation.WebFilter (filterName = "loginFilter")
public class LoginFilter implements javax.servlet.Filter {
    
    public void destroy () {
        
    }
    
    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        
        chain.doFilter(req, resp);
    }
    
    public void init (FilterConfig config) throws ServletException {
        
    }
    
}
