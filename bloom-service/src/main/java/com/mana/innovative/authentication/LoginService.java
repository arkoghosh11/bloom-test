package com.mana.innovative.authentication;/**
 * Created by alex1 on 1/29/2015.
 * This is a class for .. todo 
 */

import com.mana.innovative.constants.ServiceConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Bloom on 1/29/2015 : 11:10 PM
 * todo This class is for ...
 */
@Service
public class LoginService {

    private static final Logger logger = Logger.getLogger(LoginService.class);

    @Value (value = "${loginVariable}")
    private String loginParameterNameForSession;

    public boolean doLogin (HttpServletRequest httpRequest, HttpSession httpSession) {

        if (loginParameterNameForSession == null) {
            loginParameterNameForSession = "isLoginValid";
        }
        Boolean isAuthenticated = (Boolean) httpSession.getAttribute(loginParameterNameForSession);
        if (isAuthenticated != null && isAuthenticated) {
            return true;
        }
        String envLocation = httpRequest.getServletContext().getAttribute("location").toString();
        envLocation = envLocation.startsWith("file:/") ? envLocation.replaceFirst("^file:/", "") : envLocation;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(envLocation));
        }
        catch (IOException e) {
            logger.error("Failed to read or load Properties from system located file at " + envLocation, e);
            return false;
        }

        String user = httpRequest.getParameter("user_name");
        String password = httpRequest.getParameter("password");
        String appKeyValue = httpRequest.getParameter("app_key");

        int loginFlag = -1;
        for (Map.Entry<Object, Object> map : properties.entrySet()) {
            if (map.getKey().toString().contains("user")) {
                if (map.getValue().toString().equalsIgnoreCase(user)) {
                    loginFlag++;
                }
            }

            if (map.getKey().toString().contains("pass")) {
                if (map.getValue().toString().equalsIgnoreCase(password)) {
                    loginFlag++;
                }
            }

            if (map.getKey().toString().equals("app_key")) {
                if (map.getValue().toString().equalsIgnoreCase(appKeyValue)) {
                    loginFlag++;
                }
            }
        }

        if (loginFlag < 1) {
            return false;
        }

        httpSession.setAttribute("user_name", user);
        httpRequest.setAttribute(loginParameterNameForSession, Boolean.TRUE);
        httpSession.setMaxInactiveInterval(ServiceConstants.HALF_HOUR);

        return true;
    }

    public boolean checkLogin (final HttpServletRequest httpRequest) {

        final HttpSession httpSession = httpRequest.getSession();
        Boolean isAuthenticated = (Boolean) httpSession.getAttribute(loginParameterNameForSession);
        return isAuthenticated != null && isAuthenticated;
    }
}
