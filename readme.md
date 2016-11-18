1.

2. 添加 swagger2
    2.1. 添加依赖
        <dependency>
          <groupId>io.springfox</groupId>
          <artifactId>springfox-swagger2</artifactId>
          <version>2.2.2</version>
        </dependency>
        <dependency>
          <groupId>io.springfox</groupId>
          <artifactId>springfox-swagger-ui</artifactId>
          <version>2.2.2</version>
        </dependency>
     2.2. 添加配置swagger2类
         @Configuration
         @EnableSwagger2
         public class Swagger2 {
             @Bean
             public Docket createRestApi() {
                 return new Docket(DocumentationType.SWAGGER_2)
                         .apiInfo(apiInfo())
                         .select()
                         .apis(RequestHandlerSelectors.basePackage("com.didispace.web"))
                         .paths(PathSelectors.any())
                         .build();
             }
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

    2.3. 使用
    通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
