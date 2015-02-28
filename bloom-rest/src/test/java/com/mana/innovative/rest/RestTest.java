package com.mana.innovative.rest;/**
 * Created by Rono on 2/26/2015.
 * This is a class for .. todo 
 */

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import javax.ws.rs.core.Response;

/**
 * Created by Bloom on 2/26/2015 : 9:00 PM
 * todo This class is for ...
 */
public class RestTest extends JerseyTest {

    @Override
    protected AppDescriptor configure() {

//      IMP the param value in Builder must be the package for rest web service classes
        return new WebAppDescriptor.Builder("com.mana.innovative.rest")
//              IMP context path must be a logical name and not /
                .contextPath("bloom")
//              IMP all the spring config files in webapp resources with no line break only 1 space minimum
                .contextParam("contextConfigLocation", "classpath*:/db_config.xml" +
                        "  classpath*:/service_config.xml" +
                        "  classpath*:web_config.xml")
//                        "file:./src/main/resources/db_config.xml;" +
//                        " file:./src/main/resources/service_config.xml;" +
//                        " file:./src/main/resources/web_config.xml")
//              IMP Resource and Provider classes for spring-jersey servlet
                .initParam("com.sun.jersey.config.property.packages", "com.mana.innovative.rest;org.codehaus.jackson.jaxrs")
//                .contextParam("contextClass", "com.kohls.openapi.v1.common.test.MockableContext")
//              NOTE Servlet class to be used servlets can be chained with filter
                .servletClass(SpringServlet.class)
//              Note context listener class , using spring's
                .contextListenerClass(ContextLoaderListener.class)
//              Note request listener class , using spring's
                .requestListenerClass(RequestContextListener.class)
                .build();
    }
}
