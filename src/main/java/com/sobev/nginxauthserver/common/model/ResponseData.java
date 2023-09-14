package com.sobev.nginxauthserver.common.model;

import com.sobev.nginxauthserver.common.enums.HttpStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project
 * @Module
 * @JDK_Vserion 1.8
 * @Author DengLibin
 * @Email dlb@smarthse.cn
 * @Desc 接口统一返回对象
 * @since 2020/3/12 0012 15:02
 */
@Data

public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 6115763584010270155L;

    //	@ApiModelProperty(value = "结果码", required = true, example = "1")
    private Integer status; // 结果码
    //    @ApiModelProperty(value = "提示信息", required = false)
    private String message; // 提示信息
    //    @ApiModelProperty(value = "结果数据", required = false)
    private T data; // 结果数据

    // 写一个空参数的构造函数，用于反序列化
    public ResponseData() {

    }

    private ResponseData(HttpStatusEnum codeEnum, T data) {
        this.status = codeEnum.getCode();
        this.message = codeEnum.getMsg();
        this.data = data;
    }

    private ResponseData(HttpStatusEnum codeEnum, String message) {
        this.status = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }

    private ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data
     * @param <W>
     * @return
     */
    public static <W> ResponseData<W> success(W data) {
        return new ResponseData<>(HttpStatusEnum.SUCCESS, data);
    }

    /**
     * 成功(自定义，前端不会拦截，由具体业务决定如何处理)
     *
     * @param message : 业务提示消息
     * @param data    : 可以放自定义业务状态标志位
     * @return
     */
    public static <W> ResponseData<W> success(String message, W data) {
        return new ResponseData<>(HttpStatusEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 业务失败
     *
     * @param message : 失败后的提示数据
     * @return
     */
    public static <T> ResponseData<T> fail(String message) {
        return new ResponseData<>(HttpStatusEnum.FAIL.getCode(), message, null);
    }

    /**
     * 业务失败
     *
     * @param data : 失败操作后返回的业务数据
     * @return
     */
    public static <T> ResponseData<T> fail(T data) {
        return new ResponseData<>(HttpStatusEnum.FAIL.getCode(), null, data);
    }

    /**
     * 业务失败
     *
     * @param code    : 业务失败状态码 HttpStatusEnum
     * @param message : 提示
     * @param data    : 失败操作后返回的业务数据
     * @return
     */
    public static <T> ResponseData<T> fail(int code, String message, T data) {
        return new ResponseData<>(code, message, data);
    }

    /**
     * 服务端异常
     *
     * @param message
     * @return
     */
    public static <T> ResponseData<T> error(String message) {
        return new ResponseData<>(HttpStatusEnum.ERROR.getCode(), message, null);
    }

    /**
     * 请求错误
     *
     * @param
     * @return
     */
    public static ResponseData<Object> requestError() {
        return new ResponseData<>(HttpStatusEnum.REQUEST_ERROR, null);
    }

    /**
     * 请求错误
     *
     * @param message 提示信息
     * @return
     */
    public static <T> ResponseData<T> requestError(String message) {
        return new ResponseData<>(HttpStatusEnum.REQUEST_ERROR.getCode(), message, null);
    }


    /**
     * 禁止访问错误
     *
     * @return
     */
    public static ResponseData<Object> forbiddenError() {
        return new ResponseData<>(HttpStatusEnum.FORBIDDEN_ERROR, null);
    }

    /**
     * 账号在其他地方登录
     *
     * @return
     */
    public static ResponseData<Object> forceOffline() {
        return new ResponseData<>(HttpStatusEnum.FORCE_OFFLINE, null);
    }

    /**
     * session 失效（过期或被强制下线）
     *
     * @return
     */
    public static ResponseData<Object> invalidSession() {
        return new ResponseData<>(HttpStatusEnum.INVALID_SESSION, null);
    }

    /**
     * 登录失败-用户名或密码错误
     *
     * @return
     */
    public static ResponseData<Object> loginFail() {
        return new ResponseData<>(HttpStatusEnum.FAIL.getCode(), "登录失败请检查用户名密码", null);
    }

    /**
     * 登录失败-验证码错误
     *
     * @return
     */
    public static ResponseData<Object> verCodeError() {
        return new ResponseData<>(HttpStatusEnum.FAIL.getCode(), "验证码错误或已失效", null);
    }

    /**
     * 未登录
     *
     * @return
     */
    public static ResponseData<Object> noLogin() {
        return new ResponseData<>(HttpStatusEnum.NO_LOGIN, null);
    }

    /**
     * 重复提交
     *
     * @return ResponseData<Object>
     */
    public static ResponseData<Object> repeatRequest() {

        return new ResponseData<>(HttpStatusEnum.REPEAT_REQUEST, null);
    }

    /**
     * 登录数量超过限制
     *
     * @return ResponseData<Object>
     */
    public static ResponseData<Object> loginTimeOver() {
        return new ResponseData<>(HttpStatusEnum.LOGIN_TIMSE_OVER, "已有5人登录，目前暂时无法登录");
    }
}
