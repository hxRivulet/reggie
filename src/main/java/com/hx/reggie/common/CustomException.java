package com.hx.reggie.common;

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException {
    static final long serialVersionUID = -70397190745711139L;

    public CustomException(String message){
        super(message);
    }
}
