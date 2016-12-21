package com.github.rogerli.config.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rogerli.framework.web.RestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmartInvalidSessionStrategy implements InvalidSessionStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String headerName = "x-auth-token";
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private boolean createNewSession = true;
    private String invalidSessionUrl;

    public void setInvalidSessionUrl(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request,
                                         HttpServletResponse response) throws IOException, ServletException {

        String header = request.getHeader(headerName);
        if (header != null && !header.isEmpty()) {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("redirecUrl", invalidSessionUrl);
            RestHelper.fill(jsonMap, HttpStatus.UNAUTHORIZED, "");
            ObjectMapper stringWriter = new ObjectMapper();
            response.getWriter().write(stringWriter.writeValueAsString(jsonMap));
            response.flushBuffer();
        } else {
            logger.debug("======Starting new session (if required) and redirecting to '"
                    + invalidSessionUrl + "'======");
            if (createNewSession) {
                request.getSession();
            }
            redirectStrategy.sendRedirect(request, response, invalidSessionUrl);
        }
    }

    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }

}