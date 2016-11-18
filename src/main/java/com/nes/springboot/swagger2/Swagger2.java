package com.nes.springboot.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.*;


@Configuration
@EnableSwagger2 //启用swagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .apiInfo(apiInfo())
               // .forCodeGeneration(false)
                .select()
                //配置api生成base package
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.nes.springboot.study"))
                .build();
    }
/*
return new Docket(DocumentationType.SWAGGER_2)
                .groupName("监控")
                .apiInfo(monitorInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(MonitorSwagger.class))
                .paths(any())
                .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .globalOperationParameters(globalParameters)
                .useDefaultResponseMessages(true)
                .enableUrlTemplating(false)
                .produces(newHashSet("application/json"));
 */

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("程序猿DD")
                .version("1.0")
                .build();
    }
}