package com.nes.springboot.aspects;

/**
 * Created by wdq on 16-12-9.
 */
public aspect TestAspect {

    public pointcut xxx() : execution(* com.nes.springboot.aspects.DemoAspect.main));

    before() : xxx(){
        System.out.println("Hello world");
    }
}
