package com.github.rogerli.utils.error;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Error model for interacting with client.
 *
 * @author vladimir.stankovic
 *         <p>
 *         Aug 3, 2016
 */
public class ErrorResponse {

    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    private final ErrorCode code;

    private final Date timestamp;

    protected ErrorResponse(final String message, ErrorCode code, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.timestamp = new Date();
    }

    public static ErrorResponse of(final String message, ErrorCode code, HttpStatus status) {
        return new ErrorResponse(message, code, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getCode() {
        return code.getCode();
    }
}
