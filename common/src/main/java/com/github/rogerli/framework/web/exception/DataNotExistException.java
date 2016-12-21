package com.github.rogerli.framework.web.exception;

/**
 * @author roger.li
 */
public class DataNotExistException extends RuntimeException {
    
    private static final long serialVersionUID = 4997067247887400295L;

    public DataNotExistException() {
        super();
    }

    public DataNotExistException(String message) {
        super(message);
    }

    public DataNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotExistException(Throwable cause) {
        super(cause);
    }

    protected DataNotExistException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
