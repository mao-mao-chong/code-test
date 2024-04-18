package org.com.bmw.util;

public enum Constant {

    //错误码
    SUCCESS("0000","交易成功"),//成功
    FAILED("-9999","交易失败"),//失败

    SYSTEM_ERROR("-4444","系统异常"),
    LOGIN_NO_USER("-9998","登录失败，用户名不存在"),//登录-无用户
    LOGIN_PASS_ERROR("-9997","登录失败，用户或密码错误"),//登录-密码错误
    USER_NO_PERMISSION("-9996", "无法访问，用户权限不足"),

    ACCOUNT_DISABLE("-9995","登录失败，账户不可用"),

    NO_LOGIN("-9994","用户未登录"),

    //注册错误码
    USER_EXIST("-9993","用户已存在"),
    STORE_EXIST("-9992","商户已存在");
    private String code;
    private String message;

    Constant(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Constant setMessage(String message) {
        this.message = message;
        return this;
    }
}
