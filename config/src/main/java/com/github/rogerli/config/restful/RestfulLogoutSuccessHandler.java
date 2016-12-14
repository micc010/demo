/**
 * @文件名称： RestfulLogoutSuccessHandler.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/14
 * @作者 ： Roger
 */
package com.github.rogerli.config.restful;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roger
 * @description
 * @create 2016/12/14 15:08
 */
public class RestfulLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }
}
