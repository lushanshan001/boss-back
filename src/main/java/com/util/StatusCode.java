package com.util;

public enum StatusCode {
    REGISTER_SUCCESS(100, "注册成功"),
    REGISTER_FAIL(101, "注册失败"),
    PASSWORD_NOT_SAME(120,"密码不一致"),
    VERIFICATION_ERROR(130,"验证码错误"),
    EMAIL_HAS_EXIST(140,"邮箱已注册"),
    LOGIN_SUCCESS(150, "登录成功"),
    LOGIN_FAIL(151, "登录失败"),
    ;


    private final int code;
    private final String result;

    StatusCode(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }
}
