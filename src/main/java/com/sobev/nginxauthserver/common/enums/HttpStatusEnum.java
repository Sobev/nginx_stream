package com.sobev.nginxauthserver.common.enums;

public enum HttpStatusEnum {
    ERROR(500, "服务端异常"), // 程序员处理（正常情况下不应该有该错误）

    RESOURCE_OFF_SHELF(-1, "资源被下架"),// 由前端处理

    SUCCESS(0, "成功"),

    FAIL(1, "业务失败"), // 前端可以直接展示返回提示信息给用户

    REPEAT_REQUEST(2, "重复提交"),//重复提交

    NO_LOGIN(3, "未登录认证"), // 用户未登录

    LOGIN_TIMSE_OVER(4, "登录数量超过限制"),

    REQUEST_ERROR(400, "请求参数错误"), // 由程序员处理

    FORBIDDEN_ERROR(403, "无权限，禁止访问"), // 无权限访问的接口，由程序员处理（对于无权限的资源页面上不应设置访问入口）

    FORCE_OFFLINE(405, "账号在其他地方登录被强制下线"), // 由程序员处理（如 弹框提示）

    INVALID_SESSION(406, "会话过期,登录失效");// 由程序员处理（如 弹框提示）

    private int code; //状态码

    private String msg; //提示信息

    HttpStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
