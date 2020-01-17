package com.gdz.demo.exception;

/**
 * @Author: guandezhi
 * @Date: 2019/12/17 17:13
 */
public class ParamValidateException extends RuntimeException {

    public ParamValidateException(String errorMsg) {
        super(errorMsg);
    }

    public ParamValidateException(String errorMsg, Throwable e) {
        super(errorMsg, e);
    }
}
