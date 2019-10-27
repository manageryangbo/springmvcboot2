package com.utils.exception;


import com.utils.http.HttpCode;

/**
 * @author gingerjie
 * @since 2018年8月26日 下午12:00:51
 */
@SuppressWarnings("serial")
public class ValidateException extends BaseException {
    public ValidateException() {
    }

    public ValidateException(Throwable ex) {
        super(ex);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable ex) {
        super(message, ex);
    }

    @Override
    protected HttpCode getCode() {
        return HttpCode.PRECONDITION_FAILED;
    }
}
