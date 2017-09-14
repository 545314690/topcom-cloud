package com.topcom.cms.exception;


/**
 * Created by lism on 2017/5/25.
 */
public class NoHandlerFoundException extends BusinessException {

    public NoHandlerFoundException() {
    }

    public NoHandlerFoundException(String message) {
        super(message);
    }

    public NoHandlerFoundException(Throwable cause) {
        super(cause);
    }

    public NoHandlerFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
