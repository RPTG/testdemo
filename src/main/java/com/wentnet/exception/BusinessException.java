package com.wentnet.exception;

import com.wentnet.auw.ApiBaseException;

public class BusinessException extends ApiBaseException {
    private static final long serialVersionUID = 7185459212952794706L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException() {
    }
}