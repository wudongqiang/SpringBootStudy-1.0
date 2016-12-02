package com.nes.springboot.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by wdq on 16-12-2.
 */
@Aspect //标注为切面
@Component //标注为bean,让其spring管理
public class WebLogAspect {

    private Logger logger = Logger.getLogger(WebLogAspect.class);

    @Before("execution(public * com.nes.springboot..*.*(..))")
    public void doBefore(JoinPoint joinPoint){
        logger.info("------WebLogAspect.doBefore--------");
    }



}

