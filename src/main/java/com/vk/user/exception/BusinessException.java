package com.vk.user.exception;

import com.vk.user.constant.ErrorCode;

public class BusinessException extends RuntimeException{

    protected Integer code;
    protected String message;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(ErrorCode code, String message) {
        super(message);
        this.code = code.getValue();
        this.message = message;
    }

    public BusinessException(ErrorCode code) {
        super();
        this.code = code.getValue();
    }
}
