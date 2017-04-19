package com.nes.springboot.util;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ParameterNameTest {

    public static void main(String[] args) throws Exception {
        Class<ParameterNameTest> clazz = ParameterNameTest.class;
        Method method = clazz.getDeclaredMethod("method1", String.class, String.class);
        String[] parameterNames = ParameterNameUtils.getMethodParameterNamesByAsm4(clazz, method);
        System.out.println(Arrays.toString(parameterNames));
    }

    public void method1(String param1, String param2) {
        System.out.println(param1 + param2);
    }

}  