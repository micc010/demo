/**
 * @文件名称： ErrorCode.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/17
 * @作者 ： Roger
 */
package com.github.rogerli.utils.error;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Roger
 * @create 2017/1/17 21:25
 */
public enum ErrorCode {

    AUTH_ERROR(2),
    UN_AUTHENTICED(20),
    JWT_TOKEN_EXPIRED(21),
    INVALID_JWT_TOKEN(22),
    INVALID_USER_PASSWORD(23),

    NOT_FOUND(4),

    SERVER_ERROR(5);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return this.code;
    }

}
