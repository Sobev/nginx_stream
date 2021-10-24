package com.sobev.nginxauthserver.advice;


import com.sobev.nginxauthserver.common.exception.AuthException;
import com.sobev.nginxauthserver.common.exception.RequestException;
import com.sobev.nginxauthserver.common.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 邓力宾
 * @date 2020/3/119:34
 * @Description: controller 全局异常捕获
 */
@ControllerAdvice(basePackages = {"com.sobev.nginxauthserver.controller"}) // 用于Controller 的aop通知
public class ControllerExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 异常捕获 统一处理
     * @param e
     * @return
     */
    @ResponseBody // 直接返回json
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //设置http响应的状态码为500 表示服务端错误
    public ResponseData<Object> handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
    	
        logger.error( "Business::Controller uri:{}统一捕获异常=>:", request.getRequestURI(), e);
        
        //业务异常
        if (e instanceof AuthException){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseData.fail(((AuthException)e).code, e.getMessage(), ((AuthException)e).data);
        }
        //请求异常
        if (e instanceof RequestException){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseData.requestError(e.getMessage());
        }
        // json参数 格式异常
        if(e instanceof org.springframework.http.converter.HttpMessageNotReadableException){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseData.requestError("json参数格式不正确");
        }
        else{
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseData.fail(500, e.getMessage(), "null");
        }

//        return ResponseData.error("服务端内部异常");
    }
}
