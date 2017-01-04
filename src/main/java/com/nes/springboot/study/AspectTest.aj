package com.nes.springboot.study;


/**
 * 定义一个切面：　
 *  1、关键字aspect。　这定义Java类的语法类似。
    ２、定义pointcut：　　［修饰符(public,protected.....)］　pointcut  poincut名字()　:  表达式;
    3、定义advice：　　　通知类型()　：　pointcut名字(){ .......逻辑}

 before( Formals )
 after( Formals ) returning [ ( Formal ) ]
 after( Formals ) throwing [ ( Formal ) ]
 after( Formals ) 
 Type around( Formals )

 
 *
 */
public aspect AspectTest {

	//声明切点
	pointcut HelloWorldPointCut() : execution(* com.nes.asjpect.Test.main(..));

	
	before() : HelloWorldPointCut(){
		System.out.println("Hello world");
	}
	
	after() returning(Object o) : HelloWorldPointCut() {
		 System.out.println("Entering : " + thisJoinPoint.getSourceLocation());  
		System.out.println("xxxxxxxxxx"+o);
	}
}
