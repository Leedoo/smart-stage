package com.smart.exception;

import com.smart.core.entity.IErrorCode;

/**
 * 应用服务异常
 */
public class ApplicationException extends CommonException {

    public ApplicationException(IErrorCode e) {
        super(e.getCode(), e.getMessage());
    }

    public ApplicationException(IErrorCode e, Object... args) {
        super(e.getCode(), e.getMessage(args));
    }

    public ApplicationException(IErrorCode e, Throwable cause) {
        super(e.getCode(), e.getMessage(), cause);
    }

    public ApplicationException(IErrorCode e, Throwable cause, Object... args) {
        super(e.getCode(), e.getMessage(args), cause);
    }
}