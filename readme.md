#1.**配置springboot依赖**
    配置编码和版本
    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    使用parent引入spring boot 
    <!-- Inherit defaults from Spring Boot -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.1.RELEASE</version>
    </parent>
    添加spring boot
    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter</artifactId>
    </dependency>

#2.添加 swagger2
###2.1. 添加依赖
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
###2.2. 添加配置swagger2类
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

###2.3. 使用
    通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。

#3.添加aop依赖
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>

###3.1关于是否需要引入注解EnableAspectJAutoProxy
####先看看AOP的默认配置属性:
    其中spring.aop.auto属性默认是开启的，也就是说只要引入了AOP依赖后，默认已经增加了@EnableAspectJAutoProxy。
        spring.aop.auto=true # Add @EnableAspectJAutoProxy. 
        spring.aop.proxy-target-class=false # false表示使用jdk的动态代理(接口代理),true表示使用cglib代理(类代理)
####所以其实是可以不需要在主配置类上加上EnableAspectJAutoProxy此注解
    当我们需要使用cglib代理的时候就需要使用spring.aop.proxy-target-class=true,或者注解上指定@EnableAspectJAutoProxy(proxyTargetClass = true)
#实现Web层的日志切面
###实现AOP的切面主要有以下几个要素：    
>    使用@Aspect注解将一个java类定义为切面类
>    使用@Pointcut定义一个切入点，可以是一个正则表达式，比如某个package下的所有函数，也可以是一个注解等。
>    根据需要在切入点不同位置的切入内容
>    使用@Before在切入点开始处切入内容
>    使用@After在切入点结尾处切入内容
>    使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
>    使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
>    使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
##### 实例
    @Aspect
    @Component
    public class WebLogAspect {
        private Logger logger = Logger.getLogger(getClass());
        @Pointcut("execution(public * com.nes.springboot..*.*(..))")
        public void webLog(){}
        @Before("webLog()")
        public void doBefore(JoinPoint joinPoint) throws Throwable {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            logger.info("URL : " + request.getRequestURL().toString());
            logger.info("HTTP_METHOD : " + request.getMethod());
            logger.info("IP : " + request.getRemoteAddr());
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        }
        @AfterReturning(returning = "ret", pointcut = "webLog()")
        public void doAfterReturning(Object ret) throws Throwable {
            // 处理完请求，返回内容
            logger.info("RESPONSE : " + ret);
        }
    }
### AOP切面的优先级
     由于通过AOP实现，程序得到了很好的解耦，但是也会带来一些问题，比如：我们可能会对Web层做多个切面，校验用户，校验头信息等等，这个时候经常会碰到切面的处理顺序问题。
     所以，我们需要定义每个切面的优先级，我们需要@Order(i)注解来标识切面的优先级。i的值越小，优先级越高。
     假设我们还有一个切面是CheckNameAspect用来校验name必须为didi，我们为其设置@Order(10)，而上文中WebLogAspect设置为@Order(5)，所以WebLogAspect有更高的优先级，这个时候执行顺序是这样的：
     在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
     在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容