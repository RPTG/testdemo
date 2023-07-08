package com.wentnet.auw;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class ApiResult<T> {

    private String code;
    private String message;
    private T data;
    private Long costs;

    public ApiResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult(ApiResultEnum.SUCCESS.getCode(), ApiResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult(ApiResultEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> ApiResult<T> error() {
        return new ApiResult(ApiResultEnum.ERROR.getCode(), ApiResultEnum.ERROR.getMessage(), (Object) null);
    }

    public static <T> ApiResult<T> error(String message) {
        return StringUtils.isBlank(message) ? error() : new ApiResult(ApiResultEnum.ERROR.getCode(), message, (Object) null);
    }

    public static <T> ApiResult<T> error(ApiResultEnum apiResultEnum) {
        return new ApiResult(apiResultEnum.getCode(), apiResultEnum.getMessage(), (Object) null);
    }

    public static <T> ApiResult<T> error(String code, String message) {
        return new ApiResult(code, message, (Object) null);
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public Long getCosts() {
        return this.costs;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCosts(Long costs) {
        this.costs = costs;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ApiResult)) {
            return false;
        } else {
            ApiResult<?> other = (ApiResult) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$costs = this.getCosts();
                    Object other$costs = other.getCosts();
                    if (this$costs == null) {
                        if (other$costs == null) {
                            break label59;
                        }
                    } else if (this$costs.equals(other$costs)) {
                        break label59;
                    }

                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ApiResult;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $costs = this.getCosts();
        result = result * 59 + ($costs == null ? 43 : $costs.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "ApiResult(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", costs=" + this.getCosts() + ")";
    }

    public ApiResult() {
    }

    public ApiResult(String code, String message, T data, Long costs) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.costs = costs;
    }
}
