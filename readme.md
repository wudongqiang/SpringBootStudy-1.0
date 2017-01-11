#1.**配置springboot依赖**
使用springboot内存数据库 h2
##1.1引入jar
    <dependency>  
        <groupId>org.springframework.boot</groupId>  
        <artifactId>spring-boot-starter-data-jpa</artifactId>  
    </dependency>  
    <!-- 内存数据库 -->  
    <dependency>  
        <groupId>com.h2database</groupId>  
        <artifactId>h2</artifactId>  
    </dependency>  

###1.2 springBoot打包
    <build>
         <!--打包后jar的名称-->
         <finalName>api_server</finalName>
         <plugins>
             <!-- spring boot打包插件 -->
             <plugin>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-maven-plugin</artifactId>
             </plugin>
         </plugins>
     </build>
