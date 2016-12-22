package com.github.rogerli.config.restful;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RestfulAuthenticationFailureHandler implements
        AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        PrintWriter writer;
        String returnStr = "{exception1:{name:'" + exception.getClass()
                + "',message:'" + exception.getMessage() + "'}}";
        System.out.println(this.getClass().toString() + ":" + returnStr);
        writer = response.getWriter();
        writer.write(returnStr);
        writer.flush();
        writer.close();
    }

}  

