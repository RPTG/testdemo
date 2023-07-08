package com.wentnet.auw;

public class ApiBaseException extends RuntimeException {
    private static final long serialVersionUID = -4695362307734560917L;
    private String code;

    public ApiBaseException(String code, String message) {
        super(message);
        this.code = ApiResultEnum.ERROR.getCode();
        this.code = code;
    }

    public ApiBaseException(String message) {
        super(message);
        this.code = ApiResultEnum.ERROR.getCode();
    }

    public ApiBaseException(Throwable cause) {
        super(cause);
        this.code = ApiResultEnum.ERROR.getCode();
    }

    public ApiBaseException() {
        this.code = ApiResultEnum.ERROR.getCode();
    }

    public String getCode() {
        return this.code;
    }
}
