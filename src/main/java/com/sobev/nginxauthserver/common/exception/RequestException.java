package com.sobev.nginxauthserver.common.exception;

/**
* @author luojx
* @date 2021/10/17 14:44
* @desc 自定义请求异常的父类 这类异常由前端开发处理，如 参数值非法，必要参数为空等。
*
*/
public  class RequestException extends RuntimeException {
    private static final long serialVersionUID = -4496344066905795004L;

    public RequestException(String msg){
        super(msg);
    }
}