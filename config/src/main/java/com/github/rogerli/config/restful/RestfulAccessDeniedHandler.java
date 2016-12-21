package com.github.rogerli.config.restful;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        PrintWriter writer;
        String returnStr = "{exception2:{name:'" + accessDeniedException.getClass()
                + "',message:'" + accessDeniedException.getMessage() + "'}}";
        System.out.println(this.getClass().toString() + ":" + returnStr);
        writer = response.getWriter();
        writer.write(returnStr);
        writer.flush();
        writer.close();
    }

}  