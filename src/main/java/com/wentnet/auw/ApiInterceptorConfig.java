package com.wentnet.auw;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiInterceptorConfig implements WebMvcConfigurer {
    public ApiInterceptorConfig() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiCostsHandlerInterceptor()).addPathPatterns(new String[]{"/**"}).excludePathPatterns(new String[]{"/doc/**"});
    }
}
