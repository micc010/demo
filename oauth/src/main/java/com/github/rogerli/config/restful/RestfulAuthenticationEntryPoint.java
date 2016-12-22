/**
 * @文件名称： RestfulAuthenticationEntryPoint.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/14
 * @作者 ： Roger
 */
package com.github.rogerli.config.restful;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Roger
 * @description
 * @create 2016/12/14 15:43
 */
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        PrintWriter writer;
        String returnStr = "{exception3:{name:'" + authException.getClass()
                + "',message:'" + authException.getMessage() + "'}}";
        System.out.println(this.getClass().toString() + ":" + returnStr);
        writer = response.getWriter();
        writer.write(returnStr);
        writer.flush();
        writer.close();
    }
}
