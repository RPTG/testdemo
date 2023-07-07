package com.wentnet.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean({"defaultApi2"})
    public Docket defaultApi2() {
        String groupName = "v1.0";
        return (new Docket(DocumentationType.SWAGGER_2)).globalOperationParameters(this.getGlobalOperationParameters()).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.wentnet.controller")).paths(PathSelectors.any()).build().extensions(this.openApiExtensionResolver.buildExtensions(groupName));
    }

    private List<Parameter> getGlobalOperationParameters() {
        List<Parameter> globalOperationParameters = new ArrayList();
        globalOperationParameters.add((new ParameterBuilder()).name("use-my-compress").description("使用压缩").modelRef(new ModelRef("boolean")).parameterType("header").required(false).defaultValue("false").build());
        globalOperationParameters.add((new ParameterBuilder()).name("use-my-cache").description("压缩使用缓存").modelRef(new ModelRef("boolean")).parameterType("header").required(false).defaultValue("false").build());
        return globalOperationParameters;
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("接口文档").description("接口文档").version("1.0").build();
    }

}
