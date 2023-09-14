package com.sobev.nginxauthserver.common.exception;

/**
 * @author luojx
 * @date 2021/10/17 14:44
 * @desc 自定义业务异常 这类异常的信息可以直接展示给用户，如用户名已存在
 */
public class AuthException extends RuntimeException {
    private static final long serialVersionUID = 8687206806741845617L;

    /**
     * 错误编码，以 StatusCodeEnum 为准
     */
    public int code;

    /**
     * 业务错误后可能需要返回某些数据
     */
    public Object data;

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public AuthException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.data = data;
    }
}
