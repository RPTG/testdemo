package com.wentnet.auw;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiCostsHandlerInterceptor  extends HandlerInterceptorAdapter {
    public static final String API_COSTS = "ApiCosts";

    public ApiCostsHandlerInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("ApiCosts", System.currentTimeMillis());
        return true;
    }
}