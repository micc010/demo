/**
 * @文件名称： AjaxResponseHelper.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/19
 * @作者 ： Roger
 */
package com.github.rogerli.config.jwt.auth.ajax;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Roger
 * @create 2017/1/19 17:56
 */
public class AjaxResponseHelper {

    public static void addOrigins(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        return;
    }

}
