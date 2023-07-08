package com.wentnet.auw;


import com.wentnet.exception.BusinessException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Iterator;

@RestControllerAdvice(
        basePackages = {"com.wentnet.controller"}
)
public class ApiExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(ApiExceptionAdvice.class);

    public ApiExceptionAdvice() {
    }

    @ExceptionHandler({Exception.class})
    public ApiResult<?> handle(Exception ex) {
        log.error("发生异常", ex);
        return ApiResult.error();
    }

    @ExceptionHandler({RuntimeException.class})
    public ApiResult<?> handle(RuntimeException ex) {
        return ApiResult.error();
    }

    @ExceptionHandler({ApiBaseException.class})
    public ApiResult<?> handleApiBaseException(ApiBaseException ex) {
        return ApiResult.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    public ApiResult<?> handleBusinessException(BusinessException ex) {
        return ApiResult.error(ex.getMessage());
    }

    @ExceptionHandler({BindException.class})
    public ApiResult<?> handleBindException(BindException ex) {
        return this.handleBindingResult(ex.getBindingResult());
    }

    private ApiResult<?> handleBindingResult(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder("校验失败. ");
        Iterator var3 = bindingResult.getFieldErrors().iterator();

        while (var3.hasNext()) {
            FieldError fieldError = (FieldError) var3.next();
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(". ");
        }

        String msg = sb.toString();
        return StringUtils.hasText(msg) ? ApiResult.error(ApiResultEnum.ERROR_VALIDATE.getCode(), msg) : ApiResult.error(ApiResultEnum.ERROR_VALIDATE);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return this.handleBindingResult(ex.getBindingResult());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ApiResult<?> handleConstraintViolationException(ConstraintViolationException ex) {
        return StringUtils.hasText(ex.getMessage()) ? ApiResult.error(ApiResultEnum.ERROR_VALIDATE.getCode(), ex.getMessage()) : ApiResult.error(ApiResultEnum.ERROR_VALIDATE);
    }

    @ExceptionHandler({MyBatisSystemException.class})
    public ApiResult<?> handleMyBatisSystemException(Exception ex) {
        String message = ex.getMessage();
        if (message.contains("master - Connection")) {
            message = "无法连接上 master 数据库";
        } else if (message.contains("gbase - Connection")) {
            message = "无法连接上 gbase 数据库";
        } else {
            message = "数据库异常";
        }

        return ApiResult.error(message);
    }

    @ExceptionHandler({SQLException.class, BadSqlGrammarException.class})
    public ApiResult<?> handleSqlException(Exception ex) {
        return ApiResult.error("数据库sql执行异常");
    }

    @ExceptionHandler({UncategorizedSQLException.class})
    public ApiResult<?> handleSqlException(UncategorizedSQLException ex) {
        return ex.getSQLException().getErrorCode() == 13001 ? ApiResult.error("该记录已存在") : ApiResult.error("数据库sql执行异常");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ApiResult<?> handleInvalidFormatException(HttpMessageNotReadableException ex) {
        log.error("参数解析异常", ex);
        return ApiResult.error("参数解析异常");
    }

    @ExceptionHandler({HttpMessageNotWritableException.class})
    public ApiResult<?> handleInvalidSerializeException(HttpMessageNotWritableException ex) {
        log.error("结果序列化异常", ex);
        return ApiResult.error("结果序列化异常");
    }
}