package com.nes.springboot;

import io.swagger.models.Operation;
import io.swagger.models.Swagger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import java.util.Map;

/**
 * @author wdq
 * @date 2018-08-09-上午9:17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SwaggerTest {

    @Autowired
    private DocumentationCache documentationCache;
    @Autowired
    private ServiceModelToSwagger2Mapper mapper;

    @Test
    public void testResponseMessage() {
        Documentation user = documentationCache.documentationByGroup("user");
        user.getApiListings();
        Swagger swagger = mapper.mapDocumentation(user);

        Operation operation = swagger.getPaths().get("/user/list").getGet();

        Map<String, Object> stringObjectMap = ExamplesUtil.generateResponseExampleMap(true, operation, swagger.getDefinitions());
        System.out.println(stringObjectMap);



    }
}
