package com.github.rogerli.security.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Autowired
    WebApplicationContext webApplicationContext;

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException,
            ServletException {
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            request.getSession().setAttribute("user", principal);
            logger.debug(((CustomUserDetails) principal).getUsername() + " authentication success");

            AuthenticationSuccessEvent authenticationSuccessEvent = new AuthenticationSuccessEvent(authentication);
            if (authenticationSuccessEvent != null) {
                webApplicationContext.publishEvent(authenticationSuccessEvent);
            }
            logger.debug("Publish authentication success event");
        } else {
            logger.warn("Principal is not CustomUserDetails");
        }
        String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
        if (StringUtils.isEmpty(redirectUrl)) {
            response.sendRedirect("index");
        } else {
            response.sendRedirect(redirectUrl);
        }
    }

}
