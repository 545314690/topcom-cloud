package com.topcom.cms.perm.exception;


/**
 * Created by lism on 2017/5/25.
 */
public class UnLoginException extends AuthenticationException {

    public UnLoginException() {
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

    protected UnLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
