/**
 * @文件名称： AbstractController.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/2
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rogerli.utils.RestfulUtils;
import com.github.rogerli.utils.error.ErrorCode;
import com.github.rogerli.utils.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Roger
 * @create 2016/12/2 16:58
 */
public abstract class AbstractController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    /**
     * 统一错误处理
     *
     * @param request   http请求
     * @param response  http响应
     * @param exception 异常
     * @return 异常页面
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                   Exception exception) {
        LOGGER.error("exceptionHandler", exception);
        if (RestfulUtils.isAjax(request)) {
            return jsonExceptionHandler(request, response, exception);
        } else {
            return htmlExceptionHandler(request, exception);
        }
    }

    /**
     * @param response  响应
     * @param exception 异常
     * @return 页面
     */
    private String jsonExceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                        Exception exception) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            objectMapper.writeValue(response.getWriter(), ErrorResponse.of(exception.getMessage(),
                    ErrorCode.SERVER_ERROR, getStatus(request)));
            return null;
        } catch (JsonGenerationException var9) {
            LOGGER.error("JsonGenerationException", var9);
            var9.printStackTrace();
            return null;
        } catch (JsonMappingException var10) {
            LOGGER.error("JsonMappingException", var10);
            var10.printStackTrace();
            return null;
        } catch (IOException var11) {
            LOGGER.error("IOException", var11);
            var11.printStackTrace();
            return null;
        }
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * @param request   请求
     * @param exception 异常
     * @return 页面
     */
    private String htmlExceptionHandler(HttpServletRequest request,
                                        Exception exception) {
        request.setAttribute("status", getStatus(request).value());
        request.setAttribute("message", exception.getMessage());
        request.setAttribute("code", ErrorCode.SERVER_ERROR.getCode());
        request.setAttribute("timestamp", new Date());
        return "error";
    }

}
