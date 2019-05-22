package com.nes.springboot.aspects;



public aspect AspectTest {

    //声明切点
    pointcut testPointCut() : execution(* com.nes.springboot.domain.Teacher.setName(..));


    before() : testPointCut(){
        System.out.println("Hello world");
    }

    after() returning(Object o) : testPointCut() {
        System.out.println("Entering : " + thisJoinPoint.getSourceLocation());
        System.out.println("params :"+thisJoinPoint.getArgs()[0]);
        System.out.println("xxxxxxxxxx"+o);
    }

   // around() returning(Object o) : testPointCut(){}
}
