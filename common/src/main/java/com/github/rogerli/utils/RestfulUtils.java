package com.github.rogerli.utils;

import com.github.rogerli.framework.web.exception.IllegalValidateException;
import com.github.rogerli.utils.error.ErrorCode;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author vladimir.stankovic
 *         <p>
 *         Aug 3, 2016
 */
public class RestfulUtils {

    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private static final String TIMESTAMP = "timestamp";
    private static final String CODE = "code";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    private RestfulUtils() {

    }

    /**
     * 将HTTP状态码写入到Map中
     *
     * @param jsonMap 用于绑定的Map集合
     * @param status HTTP状态
     * @see HttpStatus
     */
    public static void fillOk(Map<String, Object> jsonMap, HttpStatus status) {
        fillError(jsonMap, status, null, null);
    }

    /**
     * 将HTTP状态码写入到Map中
     *
     * @param jsonMap 用于绑定的Map集合
     * @param status HTTP状态
     * @see HttpStatus
     */
    public static void fillOk(Map<String, Object> jsonMap, HttpStatus status, Object data) {
        fill(jsonMap, status, data, null, null);
    }

    /**
     * 将HTTP状态码写入到Map中
     *
     * @param jsonMap 用于绑定的Map集合
     * @param status HTTP状态
     * @see HttpStatus
     */
    public static void fillOk(Map<String, Object> jsonMap, HttpStatus status, Object data, String message) {
        fill(jsonMap, status, data, null, message);
    }

    /**
     * 绑定至Map中
     *
     * @param jsonMap
     * @param code
     * @param status
     * @param message
     */
    public static void fillError(Map<String, Object> jsonMap, HttpStatus status, ErrorCode code,
                                 String message) {
        fill(jsonMap, status, null, code, message);
    }

    /**
     * 将错误绑定至Map中
     *
     * @param jsonMap
     * @param status
     * @param message
     */
    private static void fill(Map<String, Object> jsonMap, HttpStatus status, Object data,
                            ErrorCode code, String message) {
        jsonMap.put(TIMESTAMP, new Date());
        jsonMap.put(STATUS, status.value());
        if (code != null) {
            jsonMap.put(CODE, code.getCode());
        }
        if (message != null && !message.trim().equals("")) {
            jsonMap.put(MESSAGE, message);
        }
        if (data != null) {
            jsonMap.put(DATA, data);
        }
    }

    /**
     * 用户检验实体合法性的辅助方法, 自动向Map封装错误信息
     *
     * @param result  Spring MVC中与@Valid成对出现的BindingResult, 用于绑定错误信息
     * @param jsonMap 用户存放各类信息的Map集合
     * @throws IllegalValidateException 实体校验失败异常
     */
    public static void bindErrors(Map<String, Object> jsonMap, BindingResult result) throws IllegalValidateException {
        // 默认为true, 检测到错误时赋值为false
        boolean flag = true;
        if (result.getErrorCount() > 0) {
            flag = false;
            final ImmutableMap.Builder<String, Object> errorBuilder = ImmutableMap.builder();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorBuilder.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            jsonMap.put(MESSAGE, errorBuilder.build());
        }
        if (!flag) {
            throw new IllegalValidateException("Illegal entry");
        }
    }

    /**
     * 检查排序语法是否正确, 若不为{@code asc}或者{@code desc}则抛出运行时异常IllegalArgumentException
     *
     * @param order
     * @return
     */
    public static String checkOrder(String order) {
        final boolean desc = "desc".equalsIgnoreCase(order.trim());
        final boolean asc = "asc".equalsIgnoreCase(order.trim());
        final boolean flag = (!desc && !asc);
        if (flag) {
            throw new IllegalArgumentException(order + " Illegal entry");
        }
        return order;
    }

    /**
     * 用户检查排序字符串拼接时, sort字符串的合法性过滤
     *
     * @param buffer   用于校验标注的数组
     * @param property 被检查的字符串
     * @return 若检查正确返回被检查的字符串
     * @throws IllegalArgumentException
     */
    public static String checkLegality(String[] buffer, String property) {
        boolean flag = false;
        for (String str : buffer) {
            if (!flag && property.equalsIgnoreCase(str)) {
                flag = true;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException(property + " Illegal entry");
        }
        return property;
    }

    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    public static boolean isAjax(SavedRequest request) {
        return request.getHeaderValues(X_REQUESTED_WITH).contains(XML_HTTP_REQUEST);
    }

    public static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(CONTENT_TYPE).contains(CONTENT_TYPE_JSON);
    }
}
