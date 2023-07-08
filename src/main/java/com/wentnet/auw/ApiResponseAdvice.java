package com.wentnet.auw;

import com.wentnet.exception.BusinessException;
import com.wentnet.utils.JsonUtil;
import com.wentnet.utils.RequestGzipUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestControllerAdvice(
        basePackages = {"com.wentnet.controller"}
)
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {
    public ApiResponseAdvice() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getContainingClass().getAnnotation(ApiWrapIgnore.class) == null && returnType.getMethodAnnotation(ApiWrapIgnore.class) == null;
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ApiResult apiResult;
        if (body instanceof ApiResult) {
            apiResult = (ApiResult) body;
            if (returnType.getContainingClass().getAnnotation(ApiWrapIgnoreCompress.class) == null && returnType.getMethodAnnotation(ApiWrapIgnoreCompress.class) == null && ApiResultEnum.SUCCESS.getCode().equals(apiResult.getCode())) {
                try {
                    Object data = apiResult.getData();
                    apiResult = ApiResult.success(RequestGzipUtil.compress(() -> {
                        return data;
                    }));
                } catch (Exception var13) {
                    throw new BusinessException(var13);
                }
            }
        } else {
            apiResult = ApiResult.success(body);
            if (returnType.getContainingClass().getAnnotation(ApiWrapIgnoreCompress.class) == null && returnType.getMethodAnnotation(ApiWrapIgnoreCompress.class) == null) {
                try {
                    apiResult = ApiResult.success(RequestGzipUtil.compress(() -> {
                        return body;
                    }));
                } catch (Exception var12) {
                    throw new BusinessException(var12);
                }
            }
        }

        if (returnType.getContainingClass().getAnnotation(ApiWrapIgnoreCache.class) == null && returnType.getMethodAnnotation(ApiWrapIgnoreCache.class) == null && ApiResultEnum.SUCCESS.getCode().equals(apiResult.getCode())) {
            RequestGzipUtil.setCache(7L, TimeUnit.DAYS);
        }

        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        Object apiCostsStartTime = httpServletRequest.getAttribute("ApiCosts");
        if (apiCostsStartTime != null) {
            long costs = System.currentTimeMillis() - (Long) apiCostsStartTime;
            apiResult.setCosts(costs);
        }

        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JsonUtil.encode(apiResult);
        } else {
            return apiResult;
        }
    }
}