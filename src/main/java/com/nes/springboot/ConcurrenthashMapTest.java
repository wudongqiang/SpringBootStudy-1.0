package com.nes.springboot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk1.8 ConcurrentHashMap 死循环案例
 */
public class ConcurrenthashMapTest {

    public static void main(String[] args) {
        System.out.println("kkkk");
        Map<String,Integer> map = new ConcurrentHashMap<>();
       /* map.computeIfAbsent("AaAa",k->
            map.computeIfAbsent("BBBB",k2->12)
        );
        System.out.println("ssssssss");*/

        //解决方法，先get,再putIfAbsent
        Integer aaAa = map.get("AaAa");
        if(aaAa==null){
            map.putIfAbsent("BBBB",12);
            System.out.println(map.get("BBBB"));
        }
    }
}
