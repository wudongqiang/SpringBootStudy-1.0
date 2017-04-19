package com.nes.springboot;

import com.nes.springboot.domain.Resource;
import com.nes.springboot.servcice.ResourceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
/**
 * Created by wdq on 16-11-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class StudyTest {

    private MockMvc mvc;

    @Autowired
    private ResourceService resourceService;

    public static void main(String[] args) {
        String a = "a";
        change(a);
        System.out.println(a);
    }

    static void change(String a){
        a += "b";
        System.out.println(a);
    }

    @Test
    public void testServiceCall() {
        List<Resource> result = resourceService.findResourceAll();
        Assert.assertEquals("this is show method...", result);

    }

    @Test
    public void testDeleteTransaction() {
        List<Resource> allDb = resourceService.findResourceAllDb();
        resourceService.deleteResourceDb(allDb.get(0).getUuId());
    }
}
