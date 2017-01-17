package com.github.rogerli.config.jwt.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rogerli.utils.error.ErrorCode;
import com.github.rogerli.utils.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author vladimir.stankovic
 *         <p>
 *         Aug 4, 2016
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
            throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        mapper.writeValue(response.getWriter(), ErrorResponse.of("Unauthorized", ErrorCode.UN_AUTHENTICED, HttpStatus.UNAUTHORIZED));

//		response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }
}
