package com.wentnet.auw;

public enum ApiResultEnum {
    SUCCESS("200", "ok"),
    ERROR("500", "接口调用失败"),
    ERROR_VALIDATE("400", "参数校验失败");

    private final String code;
    private final String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ApiResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
