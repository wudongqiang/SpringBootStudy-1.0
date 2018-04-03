package com.nes.springboot.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by wdq on 16-12-2.
 */
@Aspect //标注为切面
@Component //标注为bean,让其spring管理
public class WebLogAspect {

    private Logger logger = Logger.getLogger(WebLogAspect.class);

    @Before("execution(public * com.nes.springboot.service.*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("------方法执行前--------");
    }


    @After("execution(public * com.nes.springboot.service.*.*(..))")
    public void after() {
        System.out.println("--------方法执行后--------");
    }

    @Around("execution(public * com.nes.springboot.service.*.*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("--------环绕前--------");
        System.out.println("方法" + jp.getSignature());
        Object result = jp.proceed();
        System.out.println("--------环绕后--------");
        return result;
    }

    /**
     * 拦截service层异常，记录异常日志，并设置对应的异常信息
     * 目前只拦截Exception，是否要拦截Error需再做考虑
     *
     * @param e 异常对象
     */
    @AfterThrowing(value = "execution(public * com.nes.springboot.service.*.*(..))", throwing = "e")
    public void handle(JoinPoint point, Exception e) {
        System.out.println("---------异常环绕1----------");
        //e.printStackTrace();

        String signature = point.getSignature().toString();
        System.out.println(signature);
        System.out.println("---------异常环绕2----------");
        //throw new RuntimeException();
    }
}

