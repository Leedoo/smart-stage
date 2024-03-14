package com.smart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonException extends RuntimeException {

    private String code;

    public CommonException(String code) {
        super();
        this.code = code;
    }

    public CommonException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}