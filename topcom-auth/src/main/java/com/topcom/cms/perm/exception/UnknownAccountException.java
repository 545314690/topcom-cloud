package com.topcom.cms.perm.exception;

/**
 * Created by lism on 17-6-14.
 */
public class UnknownAccountException extends AuthenticationException {
    public UnknownAccountException() {
        super();
    }

    public UnknownAccountException(String message) {
        super(message);
    }

    public UnknownAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownAccountException(Throwable cause) {
        super(cause);
    }

    protected UnknownAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
