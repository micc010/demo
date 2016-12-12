/**
 * @文件名称： CustomAuthenticationFailureHandler.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/12
 * @作者 ： Roger
 */
package com.github.rogerli.security.spring;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roger
 * @description
 * @create 2016/12/12 0:54
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

    }
}
